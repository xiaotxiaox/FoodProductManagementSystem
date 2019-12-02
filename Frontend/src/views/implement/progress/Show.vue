<template>
  <div>
    <gantt-modal
      :record="ganttModal.record"
      :visible="ganttModal.visible"
      :type="ganttModal.type"
      v-if="ganttModal.visible"
      @close="handleGanttClose()">
    </gantt-modal>
    <task-modal
      :record="taskModal.record"
      :visible="taskModal.visible"
      :type="taskModal.type"
      :chart_id="taskModal.chart_id"
      v-if="taskModal.visible"
      @close="handleTaskClose()">
    </task-modal>
    <a-card title="施工进度图"
            style="margin-bottom: 16px">
      <div id="myChart" ref="chart" style="width: 900px; height:400px"></div>
    </a-card>
    <a-card style="margin-bottom: 16px">
      <div>
        <a-button
          type="primary"
          style="margin-left: 4px;margin-bottom:16px;"
          @click="handleGanttEdit()">
          编辑
        </a-button>
        <a-button
          type="primary"
          icon="plus"
          style="margin-bottom:16px;float: right"
          @click="handleTaskCreate()">
          新建
        </a-button>
      </div>
      <a-table
        bordered
        :columns="columns"
        :dataSource="matterList"
        rowKey="building_id"
        :pagination="false">
        <template slot="operation" slot-scope="text, record, index">
          <a-button @click="handleTaskEdit(record)">编辑</a-button>
          <a-popconfirm
            title="确认删除吗?"
            @confirm="handleTaskDelete(record)">
            <a-button type="danger">删除</a-button>
          </a-popconfirm>
        </template>
      </a-table>

      <a-col
        :xs="{span: 24}"
        :sm="{span: 20, offset: 2}"
        :xl="{span: 16, offset: 4}">
      </a-col>
    </a-card>
  </div>
</template>

<script>
  import GanttModal from './components/GanttModal'
  import TaskModal from './components/TaskModal'
  import api from '../../../api/implement'
  import {mapGetters} from 'vuex'
  import moment from 'moment'
  import echarts from 'echarts'

  const columns = [
    {
      title: '任务名称',
      dataIndex: 'name',
      width: '25%',
      align: 'center'
    },
    {
      title: '计划开始日期',
      dataIndex: 'dream_start_date',
      width: '13%',
      align: 'center'
    },
    {
      title: '计划截止日期',
      dataIndex: 'dream_end_date',
      width: '13%',
      align: 'center'
    },
    {
      title: '实际开始日期',
      dataIndex: 'real_start_date',
      width: '13%',
      align: 'center'
    },
    {
      title: '实际截止日期',
      dataIndex: 'real_end_date',
      width: '13%',
      align: 'center'
    },
    {
      title: '状态',
      dataIndex: 'state',
      width: '8%',
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
    name: 'Show',
    components: {
      GanttModal,
      TaskModal
    },
    props: {
      id: Number
    },
    data() {
      return {
        columns,
        chart: null,
        tasks: {
          data: [],
          links: []
        },
        status: {
          listLoading: true,
          tableLoading: true
        },
        layout: {
          'label-col': {span: 6},
          'wrapper-col': {span: 18}
        },
        taskModal: {
          record: null,
          visible: false,
          type: '1',
          chart_id: this.id
        },
        ganttModal: {
          record: null,
          visible: false,
          type: '1',
        },
        matterList: [],
        project_id: this.projectSelected().id
      }
    },
    mounted() {
      this.getData()
      this.chart = echarts.init(document.getElementById('myChart'))
      this.drawLine()
    },
    methods: {
      ...mapGetters(['projectSelected']),
      getData() {
        api.getProgressList(this.project_id)
          .then(data => {
            this.matterList = data
            console.log(2)
            console.log(data)
            for (var i of this.matterList) {
              if (i.state == 1)
                i.state = "关键"
              else
                i.state = "一般"
            }
            console.log(2)
            console.log(this.matterList)
            api.getInitialChart(this.project_id)
              .then(data => {
                var sDate = data[0].start_date.split('-')
                console.log("sDate1")
                console.log(sDate)
                //var color=['#000bbb','#000eee','bbbabd']
                var colorList1=[]
                var TaskY = []
                var TaskX = null
                var fuzhu1 = []
                var fuzhu2 = []
                var end1 = []
                var end2 = []
                var sDate = data[0].start_date.split('-')
                console.log("SDate")
                console.log(sDate)
                var start = new Date(sDate[0], sDate[1], sDate[2])
                var oDate = data[0].end_date.split('-')
                var oDate1 = new Date(oDate[0], oDate[1], oDate[2])
                var iDays = parseInt(Math.abs(oDate1 - start) / 1000 / 60 / 60 / 24)
                var interval = data[0].interval
                interval=parseInt(interval)
                for (var i of this.matterList) {
                  TaskY.push(i.name)
                  if(i.state=="一般")
                    colorList1.push('#32cd32')
                  else
                    colorList1.push('#ff0000')
                  if(i.real_start_date)
                    var realStartDate = i.real_start_date.split('-')
                  var dreamStartDate = i.dream_start_date.split('-')
                  var realStartDate1 = new Date(realStartDate[0], realStartDate[1], realStartDate[2])
                  var dreamStartDate1 = new Date(dreamStartDate[0], dreamStartDate[1], dreamStartDate[2])

                  if(i.real_end_date)
                    var realEndDate = i.real_end_date.split('-')
                  var dreamEndDate = i.dream_end_date.split('-')
                  var realEndDate1 = new Date(realEndDate[0], realEndDate[1], realEndDate[2])
                  var dreamEndDate1 = new Date(dreamEndDate[0], dreamEndDate[1], dreamEndDate[2])
                  fuzhu1.push(parseInt(Math.abs(realStartDate1 - start) / 1000 / 60 / 60 / 24))
                  fuzhu2.push(parseInt(Math.abs(dreamStartDate1 - start) / 1000 / 60 / 60 / 24))
                  if(i.real_end_date&&i.real_start_date)
                    end1.push(parseInt(Math.abs(realEndDate1 - realStartDate1) / 1000 / 60 / 60 / 24))
                  else
                    end1.push(0)
                  end2.push(parseInt(Math.abs(dreamEndDate1 - dreamStartDate1) / 1000 / 60 / 60 / 24))
                  console.log("fuzhu1")
                  console.log(fuzhu1)
                }

                TaskX = iDays

                var option = {
                  title: {
                    text: '',
                  },
                  tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                      type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    },
                    formatter: function (params) {
                      var tar;
                      if (params[1].value != '-') {
                        tar = params[1];
                      } else {
                        tar = params[0];
                      }
                      return tar.name;
                    }
                  },
                  legend: {
                    data: ['计划','实际']
                  },
                  grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                  },

                  yAxis: {
                    type: 'category',
                    splitLine: {show: true},
                    data: TaskY
                  },
                  xAxis: {
                    type: 'value',
                    min:0,
                    max:TaskX,
                    interval:interval,
                    position:bottom,top,
                    axisTick:{
                      interval:interval,
                      show:true
                    },
                    splitLine:{
                      interval:interval,
                      show:true
                    },
                    splitArea:{
                      interval:interval,
                      show:true,
                      areaStyle:{
                        color:['rgba(250,250,250,0.3)']
                      }
                    }
                  },
                  series: [
                    {
                      name: '辅助2',
                      type: 'bar',
                      stack: '计划',
                      itemStyle: {
                        normal: {
                          barBorderColor: 'rgba(0,0,0,0)',
                          color: 'rgba(0,0,0,0)'
                        },
                        emphasis: {
                          barBorderColor: 'rgba(0,0,0,0)',
                          color: 'rgba(0,0,0,0)'
                        }
                      },
                      data: fuzhu2
                    },
                    {
                      name: '辅助1',
                      type: 'bar',
                      stack: '实际',
                      itemStyle: {
                        normal: {
                          barBorderColor: 'rgba(0,0,0,0)',
                          color: 'rgba(0,0,0,0)'
                        },
                        emphasis: {
                          barBorderColor: 'rgba(0,0,0,0)',
                          color: 'rgba(0,0,0,0)'
                        }
                      },
                      data: fuzhu1
                    },
                    {
                      name: '计划',
                      stack: '计划',
                      type: 'bar',
                      itemStyle: {
                        normal: {
                         color:'#ffd700'
                        },
                        emphasis: {
                           color:'#ffd700'
                        }
                      },
                      label: {
                        normal: {
                          show: true,
                          position: 'bottom'
                        }
                      },
                      data: end2
                    },
                    {
                      name: '实际',
                      stack: '实际',
                      type: 'bar',
                      itemStyle: {
                        normal: {
                         color:function (params) {
                           let colorList=colorList1
                           return colorList[params.dataIndex]
                         }
                        },
                        emphasis: {
                           color: ['#000bbb']
                        }
                      },
                      label: {
                        normal: {
                          show: true,
                          position: 'top'
                        }
                      },
                      data: end1
                    }
                  ]
                }
                this.chart.setOption(option)

                data[0].start_date = new moment(data[0].start_date)
                data[0].end_date = new moment(data[0].end_date)
                this.ganttModal.record = data[0]
                this.change()
                this.status.listLoading = false
              })
          })
      },
      drawLine() {
        // 基于准备好的dom，初始化echarts实例
        // var bar_dv = document.getElementById('bar_dv');
        var barDv = this.$refs.chart;
        if (barDv) {
          console.log('bar_dv不为空');
          // let myChart = this.$echarts.init(barDv)
          // 基于准备好的dom，初始化echarts实例
          // let myChart = this.$echarts.init(document.getElementById('myChart'))
          // 绘制图表
          var option = {
            title: {
              text: '',
            },
            tooltip: {
              trigger: 'axis',
              axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
              },
              formatter: function (params) {
                var tar;
                if (params[1].value != '-') {
                  tar = params[1];
                } else {
                  tar = params[0];
                }
                return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
              }
            },
            legend: {
              data: ['计划','实际']
            },
            grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
            },

            yAxis: {
              type: 'category',
              splitLine: {show: false},
              data:null
            },
            xAxis: {
              type: 'value'
            },
            series: [
              {
                name: '辅助2',
                type: 'bar',
                stack: '计划',
                itemStyle: {
                  normal: {
                    barBorderColor: 'rgba(0,0,0,0)',
                    color: 'rgba(0,0,0,0)'
                  },
                  emphasis: {
                    barBorderColor: 'rgba(0,0,0,0)',
                    color: 'rgba(0,0,0,0)'
                  }
                },
                data: null
              },
              {
                name: '辅助1',
                type: 'bar',
                stack: '实际',
                itemStyle: {
                  normal: {
                    barBorderColor: 'rgba(0,0,0,0)',
                    color: 'rgba(0,0,0,0)'
                  },
                  emphasis: {
                    barBorderColor: 'rgba(0,0,0,0)',
                    color: 'rgba(0,0,0,0)'
                  }
                },
                data: null
              },
              {
                name: '计划',
                stack: '计划',
                type: 'bar',
                label: {
                  normal: {
                    show: true,
                    position: 'bottom'
                  }
                },
                data: null
              },
              {
                name: '实际',
                stack: '实际',
                type: 'bar',
                label: {
                  normal: {
                    show: true,
                    position: 'top'
                  }
                },
                data: null
              }
            ]
          };
          this.chart.setOption(option);
        } else {
          console.log('bar_dv为空!');
        }
      },
      change() {
        console.log("1")
        var TaskY = []
        var TaskX = null
        var fuzhu1 = []
        var fuzhu2 = []
        var end1 = []
        var end2 = []
        var sDate = this.ganttModal.record.start_date.split('-')
        console.log("SDate")
        console.log(sDate)
        var start = new Date(sDate[0], sDate[1], sDate[2])
        var oDate = this.ganttModal.record.end_date.split('-')
        var oDate1 = new Date(oDate[0], oDate[1], oDate[2])
        var iDays = parseInt(Math.abs(oDate1 - start) / 1000 / 60 / 60 / 24)
        var interval = this.ganttModal.record.interval
        for (var i of this.matterList) {
          TaskY.append(i.name)
          var realStartDate = i.real_start_date.split('-')
          var dreamStartDate = i.dream_start_date.split('-')
          var realStartDate1 = new Date(realStartDate[0], realStartDate[1], realStartDate[2])
          var dreamStartDate1 = new Date(dreamStartDate[0], dreamStartDate[1], dreamStartDate[2])

          var realEndDate = i.real_end_date.split('-')
          var dreamEndDate = i.dream_end_date.split('-')
          var realEndDate1 = new Date(realEndDate[0], realEndDate[1], realEndDate[2])
          var dreamEndDate1 = new Date(dreamEndDate[0], dreamEndDate[1], dreamEndDate[2])
          fuzhu1.append(parseInt(Math.abs(realEndDate1 - start) / 1000 / 60 / 60 / 24))
          fuzhu2.append(parseInt(Math.abs(dreamEndDate1 - start) / 1000 / 60 / 60 / 24))
          end1.append(parseInt(Math.abs(realEndDate1 - realStartDate1) / 1000 / 60 / 60 / 24))
          end2.append(parseInt(Math.abs(dreamEndDate1 - dreamStartDate1) / 1000 / 60 / 60 / 24))

        }

        TaskX = iDays

        var option = {
          title: {
            text: '',
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
              type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            },
            formatter: function (params) {
              var tar;
              if (params[1].value != '-') {
                tar = params[1];
              } else {
                tar = params[0];
              }
              return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
            }
          },
          legend: {
            data: ['实际', '计划']
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },

          yAxis: {
            type: 'category',
            splitLine: {show: false},
            data: TaskY
          },
          xAxis: {
            type: 'value',
            data: function () {
              var list = [];
              for (var i = 0; i <= TaskX; i += interval) {
                list.push(i);
              }
              return list;
            }()
          },
          series: [
            {
              name: '辅助1',
              type: 'bar',
              stack: '实际',
              itemStyle: {
                normal: {
                  barBorderColor: 'rgba(0,0,0,0)',
                  color: 'rgba(0,0,0,0)'
                },
                emphasis: {
                  barBorderColor: 'rgba(0,0,0,0)',
                  color: 'rgba(0,0,0,0)'
                }
              },
              data: fuzhu1
            },
            {
              name: '辅助2',
              type: 'bar',
              stack: '计划',
              itemStyle: {
                normal: {
                  barBorderColor: 'rgba(0,0,0,0)',
                  color: 'rgba(0,0,0,0)'
                },
                emphasis: {
                  barBorderColor: 'rgba(0,0,0,0)',
                  color: 'rgba(0,0,0,0)'
                }
              },
              data: fuzhu2
            },
            {
              name: '实际',
              stack: '实际',
              type: 'bar',
              label: {
                normal: {
                  show: true,
                  position: 'top'
                }
              },
              data: end1
            },
            {
              name: '计划',
              stack: '计划',
              type: 'bar',
              label: {
                normal: {
                  show: true,
                  position: 'bottom'
                }
              },
              data: end2
            }
          ]
        };
        this.chart.setOption(option);
      },
      handleTaskClose() {
        this.taskModal.type = ''
        this.taskModal.record = null
        this.taskModal.visible = false
        this.getData()
      },
      handleTaskCreate() {
        this.taskModal.type = 'create'
        this.taskModal.record = null
        this.taskModal.visible = true
      },
      handleTaskEdit(record) {
        this.taskModal.type = 'edit'
        this.taskModal.record = record
        this.taskModal.visible = true
      },
      handleTaskDelete(record) {
        api.deleteTask(record.chart_id)
          .then(data => {
            this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
          })
      },
      handleGanttEdit() {
        this.getData()
        this.ganttModal.type = 'edit'
        this.ganttModal.visible = true
      },
      handleGanttClose() {
        this.ganttModal.type = ''
        this.ganttModal.record = null
        this.ganttModal.visible = false
        this.getData()
      },
      parseFloat(f) {
        return parseFloat(f)
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

  .gantt_cal_light_wide.gantt_cal_light_full {
    width: 550px;
  }
</style>