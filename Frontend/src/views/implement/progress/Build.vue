<template>
  <div>
    <a-card style="margin-bottom: 16px">
      <a-row>
        <a-col
          :xs="{ span: 24 }"
          :sm="{ span: 24 }"
          :xl="{ span: 24 }">
          <div id="app">
            <div id="echartContainer" style="width: 100%; height: 400px;"></div>
          </div>
        </a-col>
      </a-row>
    </a-card>
    <build-modal
      :record="buildModal.record"
      :visible="buildModal.visible"
      :type="buildModal.type"
      :building_id="buildModal.building_id"
      v-if="buildModal.visible"
      @close="handleBuildClose()">
    </build-modal>

    <a-card style="margin-bottom: 16px">
      <a-row>
        <a-col
          class="item"
          :xs="{ span: 24 }"
          :sm="{ span: 12 ,offset:12}"
          :xl="{ span: 4 ,offset:20}">
          <a-button
            type="primary"
            icon="plus"
            style="width: 100%"
            @click="handleBuildCreate()">
            新建
          </a-button>
        </a-col>
      </a-row>
      <a-spin :spinning="status.listLoading">
        <a-table
          bordered
          :columns="columns"
          :dataSource="matterList"
          rowKey="building_id"
          :pagination="false">
          <template slot="operation" slot-scope="text, record, index">
            <a-button @click="handleBuildEdit(record)">编辑</a-button>
            <a-popconfirm
              title="确认删除吗?"
              @confirm="handleBuildDelete(record)">
              <a-button type="danger">删除</a-button>
            </a-popconfirm>
          </template>
        </a-table>
      </a-spin>
      <a-col
        :xs="{span: 24}"
        :sm="{span: 20, offset: 2}"
        :xl="{span: 16, offset: 4}">
      </a-col>
    </a-card>
  </div>
</template>

<script>
  import echarts from 'echarts'
  import BuildModal from './components/BuildModal'
  import api from '../../../api/implement'
  import {mapGetters} from 'vuex'

  const columns = [
    {
      title: '建筑名称',
      dataIndex: 'name',
      width: '50%',
      align: 'center'
    },
    {
      title: '投资进度比',
      dataIndex: 'progress',
      width: '25%',
      align: 'center'
    },
    {
      title: '编辑',
      dataIndex: 'operation',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ]

  export default {
    name: 'Build',
    components: {
      BuildModal
    },
    props: {
      id: Number
    },
    data() {
      return {
        status: {
          listLoading: true,
          tableLoading: true
        },
        card: {
          save_rate: null,
          bid_price: null,
          control_price: null
        },
        option: {
          tooltip: {},
          xAxis: {
            data: ['0'],
            axisLabel: {
              show: true,
              textStyle: {
                fontSize: 20
              }
            }
          },
          yAxis: {
            max: 100,
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
            data: [0],
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
        chart: null,
        layout: {
          'label-col': {span: 6},
          'wrapper-col': {span: 18}
        },
        form: this.$form.createForm(this),
        buildModal: {
          record: null,
          visible: false,
          type: '1',
          building_id: this.id
        },
        columns,
        matterList: [],
        project_id: this.projectSelected().id
      }
    },
    mounted() {
      this.chart = echarts.init(document.getElementById('echartContainer'))
      this.chart.setOption(this.option, true)
      window.onresize = this.chart.resize
      this.getData()
    },
    methods: {
      ...mapGetters(['projectSelected']),
      getData() {
        api.getBuildList(this.project_id)
          .then(data => {
            this.matterList = data
            this.status.listLoading = false
            this.change()
          })

      },
      handleBuildClose() {
        this.buildModal.type = ''
        this.buildModal.record = null
        this.buildModal.visible = false
        this.getData()
      },
      handleBuildCreate() {
        this.buildModal.type = 'create'
        this.buildModal.record = null
        this.buildModal.visible = true
      },
      handleBuildEdit(record) {
        this.buildModal.type = 'edit'
        this.buildModal.record = record
        this.buildModal.visible = true
      },
      handleBuildDelete(record) {
        api.deleteBuild(record.building_id)
          .then(data => {
            this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
          })
      },
      parseFloat(f) {
        return parseFloat(f)
      },
      change() {
        var arrName = []
        var arrProgress = []
        for (var building of this.matterList) {
          console.log(this.matterList)
          console.log(building)
          arrName.push(building.name)
          arrProgress.push(building.progress)
        }
        // console.log(building)
        // console.log(arrName)
        // console.log(arrProgress)
        this.chart.setOption({
          tooltip: {},
          xAxis: {
            data: arrName,
            axisLabel: {
              show: true,
              textStyle: {
                fontSize: 20
              }
            }
          },
          yAxis: {
            max: 100,
            min: 0,
            axisLabel: {
              show: true,
              textStyle: {
                fontSize: 14
              },
              formatter:'{value}%'
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
            data: arrProgress,
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

      }
    }
  }
</script>

<style scoped lang="less">
  .item {
    margin-bottom: 10px;
  }

  .wrapper {
    width: 7rem;
    height: 5rem;
  }
</style>