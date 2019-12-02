<template>
  <baidu-map  class="map" center="北京" ak="GEhXNIBI6zKpsnemMOEVMxaUvSjQks3o" @ready="handler" @click="addPoint" :scroll-wheel-zoom="true">
    <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
    <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true" :autoLocation="true"></bm-geolocation>
    <bm-map-type :map-types="['BMAP_NORMAL_MAP', 'BMAP_HYBRID_MAP']" anchor="BMAP_ANCHOR_BOTTOM_RIGHT" offset="(300px,200px)"></bm-map-type>
    <bm-marker :position="{lng: 116.504, lat: 39.915}"  @click="infoWindowOpen" animation="BMAP_ANIMATION_BOUNCE">
<!--      <bm-label content="我爱北京天安门" :labelStyle="{color: 'red', fontSize : '24px'}" :offset="{width: -35, height: 30}"/>-->
          <bm-info-window :show="show" @close="infoWindowClose"  @open="infoWindowOpen">我爱北京天安门</bm-info-window>
    </bm-marker>
    <bm-control :offset="{width: '50px', height: '10px'}">
    <bm-auto-complete v-model="keyword" :sugStyle="{zIndex: 1}">
      <a-input-search
      placeholder="请输入文字"
      style="width: 200px"
    />
    </bm-auto-complete>
    </bm-control>
  <bm-local-search :keyword="keyword" :auto-viewport="true" ></bm-local-search>
  </baidu-map>
</template>
<style>
.map {
  width: 100%;
  height: 630px;
}
</style>
<script>
import { BaiduMap, BmScale, BmMapType, BmNavigation,BmGeolocation,BmMarker,BmInfoWindow,BmLabel,
  BmLocalSearch,BmControl,BmAutoComplete,} from 'vue-baidu-map/components'
export default {
  components: {BaiduMap, BmScale, BmMapType, BmNavigation,BmGeolocation,BmMarker,BmInfoWindow,BmLabel,BmLocalSearch,BmControl,BmAutoComplete},
  data () {
    return {
      show: false,
      keyword: ''
    }
  },
  methods: {
    handler({ BMap, map }) {},
    addPoint(e){
      this.BMap.setMarker(e.point)
      // let map = new BMap.Map()
      // var point = new BMap.Point(e.point.lng,e.point.lat)
      // var maker = new BMap.Marker(point)
      // map.addOverlay(maker)
      this.addMarker(marker)
      alert("a")
    },
    infoWindowClose () {
      this.show = false
    },
    infoWindowOpen () {
      this.show = true
    }
  }
}
</script>
<!--&lt;!&ndash;</div>&ndash;&gt;-->

<!--&lt;!&ndash;<div>&ndash;&gt;-->
<!--&lt;!&ndash;    <div class="panel">&ndash;&gt;-->
<!--&lt;!&ndash;      <baidu-map ref="baiduMap" @ready="initNearby"></baidu-map>&ndash;&gt;-->
<!--&lt;!&ndash;    </div>&ndash;&gt;-->
<!--&lt;!&ndash;    <label>关键词：<input v-model="keyword"></label>&ndash;&gt;-->
<!--&lt;!&ndash;    <label>地区：<input v-model="location"></label>&ndash;&gt;-->
<!--&lt;!&ndash;    <baidu-map @click="addPosition" :center="{lng: 116.404, lat: 39.5}">&ndash;&gt;-->
<!--&lt;!&ndash;      <bm-view class="map" width="10%" height="100px"></bm-view>&ndash;&gt;-->
<!--&lt;!&ndash;      <bm-navigation anchor="BMAP_ANCHOR_BOTTOM_LEFT"></bm-navigation>&ndash;&gt;-->
<!--&lt;!&ndash;      <bm-city-list anchor="BMAP_ANCHOR_TOP_LEFT"></bm-city-list>&ndash;&gt;-->
<!--&lt;!&ndash;      <bm-scale anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-scale>&ndash;&gt;-->
<!--&lt;!&ndash;      <bm-local-search :keyword="keyword" :auto-viewport="true" :location="location"></bm-local-search>&ndash;&gt;-->
<!--&lt;!&ndash;    </baidu-map>&ndash;&gt;-->

<!--<baidu-map center="威海" :zoom="map_zoom" @ready="map_handler" :double-click-zoom="false"-->
<!--           @dragend="getMapCenter" class="baidu-map-view" highth="100%" width="3000px"-->
<!--           ak="GEhXNIBI6zKpsnemMOEVMxaUvSjQks3o">-->

<!--          <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>-->
<!--          <bm-scale anchor="BMAP_ANCHOR_BOTTOM_RIGHT"></bm-scale>-->

<!--</baidu-map>-->


<!--&lt;!&ndash;    <div class="panel wrapper">&ndash;&gt;-->
<!--&lt;!&ndash;      <button class="btn btn-custom" @click="getCurrentPosition">获取当前坐标</button>&ndash;&gt;-->
<!--&lt;!&ndash;      坐标：{{position}}&ndash;&gt;-->
<!--&lt;!&ndash;    </div>&ndash;&gt;-->

<!--&lt;!&ndash;    <div class="panel wrapper">&ndash;&gt;-->
<!--&lt;!&ndash;      <div class="input-group">&ndash;&gt;-->
<!--&lt;!&ndash;        <input type="text" class="form-control" v-model="nearbyKey">&ndash;&gt;-->
<!--&lt;!&ndash;        <span class="input-group-btn">&ndash;&gt;-->
<!--&lt;!&ndash;          <button class="btn btn-default" type="button" @click="searchNearby">搜索周边</button>&ndash;&gt;-->
<!--&lt;!&ndash;        </span>&ndash;&gt;-->
<!--&lt;!&ndash;      </div>&ndash;&gt;-->

<!--&lt;!&ndash;      <div class="list-group list-group-unstyle m-t">&ndash;&gt;-->
<!--&lt;!&ndash;        <div class="list-group-item" v-for="item in nearby">&ndash;&gt;-->
<!--&lt;!&ndash;          <div class="text-lg">{{item.title}}</div>&ndash;&gt;-->
<!--&lt;!&ndash;          <div class="text-muted">{{item.address}}</div>&ndash;&gt;-->
<!--&lt;!&ndash;        </div>&ndash;&gt;-->
<!--&lt;!&ndash;      </div>&ndash;&gt;-->
<!--&lt;!&ndash;    </div>&ndash;&gt;-->

<!--&lt;!&ndash;  </div>&ndash;&gt;-->
<!--</template>-->

<!--<script>-->
<!--import BaiduMap from '../components/baidu-map'-->
<!--export default {-->
<!--  components: {-->
<!--    BaiduMap-->
<!--  },-->
<!--  data: () => ({-->
<!--    position: null,-->
<!--    nearbyKey: null,-->
<!--    nearby: null,-->
<!--    location: '北京',-->
<!--    keyword: '百度'-->
<!--  }),-->
<!--  data () {-->
<!--    return {-->
<!--      location: '北京',-->
<!--      keyword: '百度'-->
<!--    }-->
<!--  },-->
<!--   methods: {-->
<!--    addPosition (e) {-->
<!--      console.log("22321321")-->
<!--      var point = new BMap.Point(e.point.lng,e.point.lat)-->
<!--      var marker = new BMap.Marker(point) // 创建标注-->
<!--      map.addOverlay(marker) // 将标注添加到地图中-->
<!--      var circle = new BMap.Circle(point, 6, { strokeColor: 'Red', strokeWeight: 6, strokeOpacity: 1, Color: 'Red', fillColor: '#f03' })-->
<!--      map.addOverlay(circle)-->
<!--    },-->
<!--    getCurrentPosition () {-->
<!--      if (navigator.geolocation) {-->
<!--        navigator.geolocation.getCurrentPosition(response => {-->
<!--          if (response.coords) {-->
<!--            console.log(response.coords)-->
<!--          }-->
<!--        }, error => {-->
<!--          this.$refs.baiduMap.getCurrentPosition(response => {-->
<!--            this.position = response-->
<!--          })-->
<!--          return Promise.reject(error)-->
<!--        })-->
<!--      }-->
<!--    },-->
<!--     searchNearby () {-->
<!--      this.$refs.baiduMap.searchNearby(this.nearbyKey, response => {-->
<!--        this.nearby = response-->
<!--      })-->
<!--    },-->
<!--    initNearby () {-->
<!--      this.$refs.baiduMap.initNearby(response => {-->
<!--        this.nearby = response-->
<!--      })-->
<!--    }-->
<!--  }-->

<!--}-->
<!--  // import api from '../api/project'-->
<!--  // import ARow from 'ant-design-vue/es/grid/Row'-->
<!--  //  export default {-->
<!--  //    name: 'Project',-->
<!--  //    components: {-->
<!--  //      ARow-->
<!--  //    },-->
<!--  //    data () {-->
<!--  //      return {-->
<!--  //        projectCount: 0,-->
<!--  //        projectList: []-->
<!--  //      }-->
<!--  //    },-->
<!--  //    mounted () {-->
<!--  //      this.getData()-->
<!--  //    },-->
<!--  //    methods: {-->
<!--  //      getData () {-->
<!--  //        api.getProjectList(this.params)-->
<!--  //          .then(data => {-->
<!--  //            console.log(data)-->
<!--  //            this.projectList = data.results-->
<!--  //            this.projectCount = data.count-->
<!--  //          })-->
<!--  //      },-->
<!--  //      getGoodsHref:function(val){-->
<!--	//          return 'http://localhost:63342/ConstructionConsultationSystem/src/demo/maptest.html?_ijt=ts26ob38h7suuvuuee4418ikqi/？id='+val-->
<!--	//      }-->
<!--  //-->
<!--  //-->
<!--  //-->
<!--  //    }-->
<!--  //  }-->

<!--</script>-->
