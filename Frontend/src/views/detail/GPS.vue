<template>
  <div>
    <div slot="footer" style="margin-top: 0px;">
      <a-card>
        <a-row :gutter="6">
          <a-col
            class="item"
            :xs="{ span: 24 }"
            :sm="{ span: 8 , offset: 16 }"
            :xl="{ span: 4 , offset: 20 }">
            <a-button
              style="float: right; margin-right: 10px" @click="$router.go(-1)">返回
            </a-button>
            <a-button type="primary" style="float: right" @click="handleOk">保存</a-button>
          </a-col>
        </a-row>
      </a-card>

    </div>
    <a-card style="margin-bottom: 16px;">
      <baidu-map class="map" :center="center" :zoom="zoom" ak="GEhXNIBI6zKpsnemMOEVMxaUvSjQks3o" @ready="handler" @click="getClickInfo"
                 :scroll-wheel-zoom="true" @moving="syncCenterAndZoom" @moveend="syncCenterAndZoom"
                 @zoomend="syncCenterAndZoom">
        <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
        <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true"></bm-geolocation>
        <bm-map-type :map-types="['BMAP_NORMAL_MAP', 'BMAP_HYBRID_MAP']" anchor="BMAP_ANCHOR_BOTTOM_RIGHT"
                     offset="(300px,200px)"></bm-map-type>
        <bm-marker :position="{lng: center.lng, lat: center.lat}" @click="infoWindowOpen"
                   animation="BMAP_ANIMATION_BOUNCE">
          <!--      <bm-label content="我爱北京天安门" :labelStyle="{color: 'red', fontSize : '24px'}" :offset="{width: -35, height: 30}"/>-->
          <bm-info-window :show="show" @close="infoWindowClose" @open="infoWindowOpen">我爱北京天安门</bm-info-window>
        </bm-marker>
        <bm-control :offset="{width: '50px', height: '10px'}">
          <bm-auto-complete v-model="keyword" :sugStyle="{zIndex: 1}">
            <a-input-search
              placeholder="请输入文字"
              style="width: 200px"
            />
          </bm-auto-complete>
        </bm-control>
        <bm-local-search :keyword="keyword" :auto-viewport="true"></bm-local-search>
      </baidu-map>
    </a-card>
  </div>
</template>

<script>
import { mapMutations } from 'vuex'
import { mapGetters } from 'vuex'
import router from '../../router'
import PageLayout from '../../components/page/PageLayout'
import api from '../../api/project'
import ARow from 'ant-design-vue/es/grid/Row'
import {
  BaiduMap, BmScale, BmMapType, BmNavigation, BmGeolocation, BmMarker, BmInfoWindow, BmLabel,
  BmLocalSearch, BmControl, BmAutoComplete,
} from 'vue-baidu-map/components'

export default {
  name: 'GPS',
  components: {
    ARow,
    PageLayout,
    BaiduMap,
    BmScale,
    BmMapType,
    BmNavigation,
    BmGeolocation,
    BmMarker,
    BmInfoWindow,
    BmLabel,
    BmLocalSearch,
    BmControl,
    BmAutoComplete
  },
  props: {
    visible: Boolean,
    type: String,
    record: Object,
    id: Number
    //center:Object
  },
  data () {
    return {
      form: this.$form.createForm(this),
      show: false,
      keyword: '',
      zoom: 15,
      status: {
        loading: true
      },
      center: { lng: 122.1740, lat: 37.2512 },
      layout: {
        xs: { span: 24 },
        sm: { span: 12 },
        xl: { span: 6 }
      },
      projectCount: 0,
      projectList: [],
      mapList: null,
      project_id: this.projectSelected().id,
    }
  },
  mounted () {
    this.getData()
  },
  methods: {
    ...mapGetters(['projectSelected']),
    getData () {
      api.getMapList(this.project_id)
        .then(data => {
          this.mapList = data[0]
          if (this.mapList != undefined) {
            this.center.lng = this.mapList.longitude
            this.center.lat = this.mapList.latitude
          }
        })
    },
    ...mapMutations(['SET_PROJECT']),
    handler ({ BMap, map }) {
    },
    // addPoint(e) {
    //   this.BMap.setMarker(e.point)
    //   // let map = new BMap.Map()
    //   // var point = new BMap.Point(e.point.lng,e.point.lat)
    //   // var maker = new BMap.Marker(point)
    //   // map.addOverlay(maker)
    //   this.addMarker(marker)
    //   alert("a")
    // },
    infoWindowClose () {
      this.show = false
    },
    infoWindowOpen () {
      this.show = true
    },
    //地图点击
    getClickInfo (e) {
      this.center.lng = e.point.lng
      this.center.lat = e.point.lat
    },
    syncCenterAndZoom (e) {
      const { lng, lat } = e.target.getCenter()
      this.center.lng = lng
      this.center.lat = lat
      this.zoom = e.target.getZoom()
    },
    /***
     * 确认
     */
    // confirm: function () {
    //   this.showMapComponent = false
    //   this.$emit('map-confirm', this.center)
    // },
    handleOk () {
      var temp={}
      temp.longitude=this.center.lng
      temp.latitude=this.center.lat
      if(this.mapList!=undefined){
        api.updateMapList(this.mapList.id, temp)
        .then(data => {
          this.$emit('close')
          this.$notification.success({ message: '成功', description: '修改成功', key: 'SUCCESS' })
          console.log(temp)
        })
      }
      else{
        api.createMapList(this.project_id, temp)
        .then(data => {
          this.$emit('close')
          this.$notification.success({ message: '成功', description: '修改成功', key: 'SUCCESS' })
          console.log(temp)
        })
        this.getData()
      }

    },
    /***
     * 取消
     */
    cancel: function () {
      this.showMapComponent = false
      this.$emit('cancel', this.showMapComponent)
    },
    // lngInit(center){
    //   if(center.longitude){
    //     return center.longitude
    //   }
    //   else{
    //     return 116.404
    //   }
    // },
    // latInit(center){
    //   if(center.latitude){
    //     return center.latitude
    //   }
    //   else{
    //     return 39.915
    //   }
    // }
  },
  computed: {
    project () {
      return this.$store.getters.projectSelected
    }

  }
}
</script>

<style lang="less" scoped>
  @import '~ant-design-vue/es/style/themes/default';

  .map {
    width: 100%;
    height: 240px;
  }

</style>
