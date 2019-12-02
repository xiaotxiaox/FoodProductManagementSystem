<template>
  <div>
    <a-card v-if="applicationList">
      <a-row>
        <a-button
          type="primary"
          @click="$router.push({name: 'elseEdit'})" style="float: right;">
          编辑
        </a-button>
        <p>提示：绿色表示完成，黄色表示正在办理，红色表示无法办理，紫色表示不用办理 ，灰色表示尚未办理。</p>
      </a-row>
      <a-row :gutter="60">
        <a-col
          :xs="{span: 20}"
          :sm="{span: 8}"
          :xl="{span: 6}"
          v-for="item in applicationList">
          <card
            :key="item.else_detail_id"
            :id="item.else_detail_id"
            :title="item.title"
            :estimate="item.estimate"
            :status="item.status"
            :actual="item.actual"
            toName="elseDetail">
          </card>
        </a-col>
      </a-row>
      <a-row>
        <total-card
          :estimate="total.estimate"
          :actual="total.actual">
        </total-card>
        <a-col
          :xs="{span: 24}"
          :sm="{span: 20, offset: 2}"
          :xl="{span: 16, offset: 4}">
          <div id="app">
            <!--创建一个echarts的容器-->
            <div id="echartContainer" style="width: 100%; height: 400px;"></div>
          </div>
        </a-col>
      </a-row>
    </a-card>
    <a-card v-else>
      <a-skeleton active/>
    </a-card>
  </div>
</template>

<script>
import Card from '../../decision/components/Card'
import TotalCard from '../../decision/components/TotalCard'
import api from '../../../api/completion'
import echarts from 'echarts'

export default {
  name: 'Else',
  components: {
    Card,
    TotalCard
  },
  data () {
    return {
      applicationList: [],
      option1: {
        tooltip: {},
        xAxis: {
          data: ['预算', '实际', '节约'],
          axisLabel: {
            show: true,
            textStyle: {
              fontSize: 20
            }
          }
        },
        yAxis: {
          axisLabel: {
            show: true,
            textStyle: {
              fontSize: 14
            }
          }
        },
        series: [{
          label: {
            textStyle: {
              fontSize: 50
            }
          },
          name: '总计',
          type: 'bar',
          barWidth: 50,
          data: [0, 0, 0],
          itemStyle: {
            normal: {
              //每根柱子颜色设置
              color: function (params) {
                let colorList = [
                  '#32CD32',
                  '#FF0000',
                  '#FFD700'
                ]
                return colorList[params.dataIndex]
              }
            }
          }
        }]
      },
      chart: null
    }
  },
  beforeMount() {
    this.getData()
  },
  mounted () {

  },
  methods: {
    getData () {
      api.getElseDetailList(this.$store.getters.projectSelected.id)
        .then(data => {
          this.applicationList = data
          this.change()
        })
    },
    change () {
      var arr = []

      this.chart = echarts.init(document.getElementById('echartContainer'))
      arr.push(this.total.estimate)
      arr.push(this.total.actual)
      arr.push(this.total.estimate - this.total.actual)
      console.log(this.total)
      console.log(arr)
      this.chart.setOption({
        tooltip: {},
        xAxis: {
          data: ['预算', '实际', '节约'],
          axisLabel: {
            show: true,
            textStyle: {
              fontSize: 20
            }
          }
        },
        yAxis: {
          axisLabel: {
            show: true,
            textStyle: {
              fontSize: 14
            }
          }
        },
        series: [{
          label: {
            textStyle: {
              fontSize: 50
            }
          },
          name: '总计',
          type: 'bar',
          barWidth: 50,
          data: arr,
          itemStyle: {
            normal: {
              //每根柱子颜色设置
              color: function (params) {
                let colorList = [
                  '#32CD32',
                  '#FF0000',
                  '#FFD700'
                ]
                return colorList[params.dataIndex]
              }
            }
          }
        }]
      }, true)
      window.onresize = this.chart.resize
    }
  },
  computed: {
    total () {
      let ret = {
        estimate: '0',
        actual: '0'
      }
      if (this.applicationList.length >0) {
        ret = {
          estimate: this.applicationList.map((item) => {
            return parseFloat(item.estimate)
          }).reduce((x, y) => {
            return x + y
          }).toFixed(2),
          actual: this.applicationList.map((item) => {
            let actual = item.actual
            if (actual) {
              return parseFloat(actual)
            } else {
              return 0
            }
          }).reduce((x, y) => {
            return x + y
          }).toFixed(2)
        }
      }
      return ret
    }
  }
}
</script>

<style scoped>

</style>