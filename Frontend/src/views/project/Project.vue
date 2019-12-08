<template>
  <page-layout title="订单列表">
    <a-card class="project">
      <a-card style="margin-bottom: 16px;">
        <baidu-map class="map" center="威海" ak="GEhXNIBI6zKpsnemMOEVMxaUvSjQks3o" @ready="handler"
                   :scroll-wheel-zoom="true">
          <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
          <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true"
                          :autoLocation="true"></bm-geolocation>
          <bm-map-type :map-types="['BMAP_NORMAL_MAP', 'BMAP_HYBRID_MAP']" anchor="BMAP_ANCHOR_BOTTOM_RIGHT"
                       offset="(300px,200px)"></bm-map-type>
          <bm-marker v-for="item in mapList" :position="{lng: item.longitude, lat: item.latitude}" @click="output(item)" animation="BMAP_ANIMATION_BOUNCE">
            <!--&lt;!&ndash;      <bm-label content="我爱北京天安门" :labelStyle="{color: 'red', fontSize : '24px'}" :offset="{width: -35, height: 30}"/>&ndash;&gt;-->
            <!--<bm-info-window :show="show" @close="infoWindowClose" @open="infoWindowOpen">{{item.data}}</bm-info-window>$router.push({'name': 'detail', params: {id: item.project}})-->
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
      <a-card
        class="card card-selected"
        v-if="project">
        <project-card :project="project"></project-card>
      </a-card>
      <a-card
        class="card card-selected"
        style="text-align: center"
        v-else>
        未选择项目
      </a-card>
<!--      <a-input-group class="tool">-->
<!--        <a-row :gutter="6">-->
<!--          <a-col-->
<!--            class="item"-->
<!--            :xs="{ span: 12 }"-->
<!--            :sm="{ span: 8 }"-->
<!--            :xl="{ span: 4 }">-->
<!--            <a-select-->
<!--              v-model="params.ordering"-->
<!--              style="width: 100%"-->
<!--              @change="getData">-->
<!--              <a-select-option value="starting_date">开始时间正排序</a-select-option>-->
<!--              <a-select-option value="-starting_date">开始时间倒排序</a-select-option>-->
<!--              <a-select-option value="finishing_date">结束时间正排序</a-select-option>-->
<!--              <a-select-option value="-finishing_date">结束时间倒排序</a-select-option>-->
<!--              <a-select-option value="investment">订单金额正排序</a-select-option>-->
<!--              <a-select-option value="-investment">订单金额倒排序</a-select-option>-->
<!--            </a-select>-->
<!--          </a-col>-->
<!--          <a-col-->
<!--            class="item"-->
<!--            :xs="{ span: 12 }"-->
<!--            :sm="{ span: 8 }"-->
<!--            :xl="{ span: 4 }">-->
<!--            <a-select-->
<!--              v-model="filter.investment"-->
<!--              style="width: 100%"-->
<!--              @change="handleFilterByInvestment">-->
<!--              <a-select-option value="">订单金额筛选</a-select-option>-->
<!--              <a-select-option :value="1">100元以下</a-select-option>-->
<!--              <a-select-option :value="2">1000元~5000元</a-select-option>-->
<!--              <a-select-option :value="3">5000元~20000元</a-select-option>-->
<!--              <a-select-option :value="4">20000元以上</a-select-option>-->
<!--            </a-select>-->
<!--          </a-col>-->
<!--          <a-col-->
<!--            class="item"-->
<!--            :xs="{ span: 12 }"-->
<!--            :sm="{ span: 8 }"-->
<!--            :xl="{ span: 4 }">-->
<!--            <a-select-->
<!--              v-model="filter.year"-->
<!--              style="width: 100%"-->
<!--              @change="handleFilterByYear">-->
<!--              <a-select-option value="">订单开始年份筛选</a-select-option>-->
<!--              <a-select-option v-for="(year, index) in yearList" :key="index" :value="index">{{ year }}年-->
<!--              </a-select-option>-->
<!--              <a-select-option :value="4">{{ yearList[yearList.length - 1] }}年之前</a-select-option>-->
<!--            </a-select>-->
<!--          </a-col>-->
<!--          <a-col-->
<!--            class="item"-->
<!--            :xs="{ span: 24 }"-->
<!--            :sm="{ span: 8 }"-->
<!--            :xl="{ span: 4 }">-->
<!--            <a-input-->
<!--              v-model="params.search"-->
<!--              style="width: 100%"-->
<!--              placeholder="项目名称，建设地点">-->
<!--            </a-input>-->
<!--          </a-col>-->
<!--          <a-col-->
<!--            class="item"-->
<!--            :xs="{ span: 12 }"-->
<!--            :sm="{ span: 8 }"-->
<!--            :xl="{ span: 4 }">-->
<!--            <a-button-->
<!--              style="width: 100%"-->
<!--              icon="search"-->
<!--              @click="handleSearch">-->
<!--              搜索-->
<!--            </a-button>-->
<!--          </a-col>-->
<!--          <a-col-->
<!--            class="item"-->
<!--            :xs="{ span: 12 }"-->
<!--            :sm="{ span: 8 }"-->
<!--            :xl="{ span: 4 }">-->
<!--            <a-button-->
<!--              style="width: 100%"-->
<!--              icon="delete"-->
<!--              @click="handleClearParams">-->
<!--              重置-->
<!--            </a-button>-->
<!--          </a-col>-->
<!--        </a-row>-->
<!--      </a-input-group>-->
      <a-spin :spinning="status.loading">
        <!--<a-card-->
          <!--class="card"-->
          <!--style="text-align: center"-->
          <!--v-if="!projectCount">-->
          <!--暂无数据-->
        <!--</a-card>-->
        <a-card
          class="card card-hover"
          v-for="project in projectList"
          :key="project.id"
          @click="handleProjectSelect(project)">
          <project-card :project="project"></project-card>
        </a-card>
      </a-spin>
      <a-pagination
        v-model="params.page"
        :defaultPageSize="10"
        @change="handelePage"
        style="float: right"
        :total="projectCount">
      </a-pagination>
      <div style="margin: 0 20px 10px 0;">共 {{ this.projectCount }} 个</div>
    </a-card>
  </page-layout>
</template>

<script>
  import {mapMutations} from 'vuex'
  import router from '../../router'
  import PageLayout from '../../components/page/PageLayout'
  import ProjectCard from './components/Card'
  import api from '../../api/sale'
  import ARow from 'ant-design-vue/es/grid/Row'
  import {
    BaiduMap, BmScale, BmMapType, BmNavigation, BmGeolocation, BmMarker, BmInfoWindow, BmLabel,
    BmLocalSearch, BmControl, BmAutoComplete,
  } from 'vue-baidu-map/components'

  export default {
    name: 'Project',
    components: {
      ARow,
      PageLayout,
      ProjectCard,
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
    data() {
      return {
        point: { lng: 122.1, lat: 37.2 },
        show: false,
        keyword: '',
        params: {
          min_investment: '',
          max_investment: '',
          min_date: '',
          max_date: '',
          search: '',
          page: 1,
          ordering: '-starting_date'
        },
        filter: {
          investment: '',
          year: ''
        },
        projectStatistics: {},
        status: {
          loading: true
        },
        layout: {
          xs: {span: 24},
          sm: {span: 12},
          xl: {span: 6}
        },
        projectCount: 0,
        projectList:[]
      }
    },
    mounted() {
      this.getData()
    },
    methods: {
      ...mapMutations(['SET_PROJECT']),
      handler({BMap, map}) {
      },
      output(item){
        var project={}
        item.id=item.project
        api.getProject(item.id)
          .then(data => {
            project = data
       this.SET_PROJECT(project)
        const role = this.$store.getters.role
        if (role.permissionList.indexOf('project_selected') === -1) {
          role.permissionList.push('project_selected')
        }
        this.$store.commit('SET_ROLE', role)
        this.$store.dispatch('GenerateRoutes', {roles: role})
        router.addRoutes(this.$store.getters.addRouters)
        router.push({name: 'detail'})
          })

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
      infoWindowClose() {
        this.show = false
      },
      infoWindowOpen() {
        this.show = true
      },
      getData() {
        this.status.loading = true
        this.status.loading = false
        api.getSumList(this.params)
          .then(data => {
            console.log(data)
            this.projectList = data
              for(var item of this.projectList)
                  if(item.state===1)
                      item.status='待付款'
                  else if(item.state===2)
                      item.status='进行中'
                  else if(item.state===3)
                      item.status='退货中'
                  else if(item.state===4)
                      item.status='订单已完成'
                  else if(item.state===5)
                      item.status='退货完成'
                  else if(item.state===6)
                      item.status='异常'
            //this.projectCount = data.count
            this.status.loading = false
          })
        api.getProjectStatistics()
          .then(data => {
            this.projectStatistics = data
          })
      },
      handelePage(page) {
        this.params.page = page
        this.getData()
      },
      handleFilterByInvestment(value) {
        switch (value) {
          case 1:
            this.params.min_investment = ''
            this.params.max_investment = '500'
            break
          case 2:
            this.params.min_investment = '500'
            this.params.max_investment = '1000'
            break
          case 3:
            this.params.min_investment = '1000'
            this.params.max_investment = '2000'
            break
          case 4:
            this.params.min_investment = '2000'
            this.params.max_investment = ''
            break
          default:
            this.params.min_investment = ''
            this.params.max_investment = ''
            this.filter.investment = ''
            break
        }
        this.getData()
      },
      handleFilterByYear(value) {
        switch (value) {
          case 0:
            this.params.min_date = this.yearList[0] + '-01-01'
            this.params.max_date = this.yearList[0] + '-12-31'
            break
          case 1:
            this.params.min_date = this.yearList[1] + '-01-01'
            this.params.max_date = this.yearList[1] + '-12-31'
            break
          case 2:
            this.params.min_date = this.yearList[2] + '-01-01'
            this.params.max_date = this.yearList[2] + '-12-31'
            break
          case 3:
            this.params.min_date = this.yearList[3] + '-01-01'
            this.params.max_date = this.yearList[3] + '-12-31'
            break
          case 4:
            this.params.max_date = this.yearList[3] + '-12-31'
            break
          default:
            this.params.min_date = ''
            this.params.max_date = ''
            this.filter.year = ''
            break
        }
        this.getData()
      },
      handleSearch() {
        this.getData()
      },
      handleClearParams() {
        this.filter.investment = ''
        this.filter.year = ''
        this.params.ordering = 'starting_date'
        this.params.min_investment = ''
        this.params.max_investment = ''
        this.params.min_date = ''
        this.params.max_date = ''
        this.params.search = ''
        this.params.page = 1
        this.getData()
      },
      handleProjectSelect(project) {
        console.log(project)
        this.SET_PROJECT(project)
        const role = this.$store.getters.role
        if (role.permissionList.indexOf('project_selected') === -1) {
          role.permissionList.push('project_selected')
        }
        console.log(role)
        this.$store.commit('SET_ROLE', role)
        this.$store.dispatch('GenerateRoutes', {roles: role})
        router.addRoutes(this.$store.getters.addRouters)
        router.push({name: 'detail'})
      }
    },
    computed: {
      project() {
        return this.$store.getters.projectSelected
      },
      yearList() {
        const ret = []
        const year = (new Date()).getFullYear()
        for (let i = 0; i < 4; i++) {
          ret.push(year - i)
        }
        return ret
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

  .project {
    .tool {
      .item {
        margin-bottom: 16px;
      }
    }

    .card {
      margin-bottom: 16px;
      border-radius: @border-radius-base;
    }

    .card-hover {
      &:hover {
        border-color: @primary-color;
        box-shadow: 0 0 1px @primary-color;
      }
    }

    .card-selected {
      color: #fff;
      background-color: @primary-color;
      border-color: @primary-color;
      box-shadow: 0 0 1px @primary-color;
    }
  }
</style>
