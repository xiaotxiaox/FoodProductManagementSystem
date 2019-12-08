<template>
  <div>
    <a-card style="margin-bottom: 16px" title="收支统计图表">
      <a-row>
        <a-col
          :xs="{ span: 24 }"
          :sm="{ span: 24 }"
          :xl="{ span: 24 }">
          <div style="width: 50%;height:100%;float: left">
            <div id="echartContainer" style="width: 100%; height: 400px;"></div>
          </div>
          <div style="width: 50%;height:100%;float: right">
            <div id="main" style="width: 100%;height: 400px;"></div>
          </div>
        </a-col>
      </a-row>
    </a-card>
    <a-card style="margin-bottom: 16px" title="收支统计表">

      <a-table
        bordered
        :columns="columns"
        :dataSource="statisticsList"
        :scroll="{ x: 1300 }"
        rowKey="customer_id"
        :pagination="false">
      </a-table>
    </a-card>
  </div>
</template>

<script>
    import echarts from 'echarts'
    import api from '../../api/finance'
    import {mapGetters} from 'vuex'

    const columns = [
        {
            title: '月份',
            dataIndex: 'month',
            width: '20%',
            align: 'center'
        }, {
            title: '订单收入',
            dataIndex: 'order_income',
            width: '10%',
            align: 'center'
        },
        {
            title: '工资支出',
            dataIndex: 'salary_outcome',
            width: '10%',
            align: 'center'
        }, {
            title: '退款支出',
            dataIndex: 'back_outcome',
            width: '10%',
            align: 'center'
        }, {
            title: '原料采买支出',
            dataIndex: 'purchase_outcome',
            width: '20%',
            align: 'center'
        }, {
            title: '总收入',
            dataIndex: 'total_income',
            width: '20%',
            align: 'center'
        }, {
            title: '总支出',
            dataIndex: 'total_outcome',
            width: '20%',
            align: 'center',
        },
        {
            title: '利润',
            dataIndex: 'profit',
            width: '20%',
            align: 'center'
        }
    ]
    export default {
        name: "statistics",
        data() {
            return {
                status: {
                    listLoading: true,
                    tableLoading: true
                },
                qualityList: {},
                not_num: '',
                reply_num: '',
                option1: {
                    tooltip: {},
                    xAxis: {
                        data: [],
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
                            },
                        }
                    },
                    series: [{
                        label: {
                            textStyle: {
                                fontSize: 50
                            }
                        },
                        name: '奖项',
                        type: 'bar',
                        barWidth: 50,
                        data: [1, 2, 3, 3],
                        itemStyle: {
                            normal: {
                                //每根柱子颜色设置
                                color: function (params) {
                                    let colorList = [
                                        '#00B0F0'
                                    ]
                                    return colorList[params.dataIndex]
                                }
                            }
                        }
                    }]
                },
                charts: '',
                opinion: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
                opinionData: [
                    {value: 10, name: '1月', itemStyle: {color: '#CAE1FF'}},
                    {value: 10, name: '2月', itemStyle: {color: '#C0FF3E'}},
                    {value: 10, name: '3月', itemStyle: {color: '#BFEFFF'}},
                    {value: 10, name: '4月', itemStyle: {color: '#FFD700'}},
                    {value: 10, name: '5月', itemStyle: {color: '#EEAEEE'}},
                    {value: 10, name: '6月', itemStyle: {color: '#9ACD32'}},
                    {value: 10, name: '7月', itemStyle: {color: '#EE7600'}},
                    {value: 10, name: '8月', itemStyle: {color: '#836FFF'}},
                    {value: 10, name: '9月', itemStyle: {color: '#FFC0CB'}},
                    {value: 10, name: '10月', itemStyle: {color: '#00EE00'}},
                    {value: 10, name: '11月', itemStyle: {color: '#CD3700'}},
                    {value: 10, name: '12月', itemStyle: {color: '#00B2EE'}},
                ],
                columns,
                statisticsList: [],
            }
        },
        mounted() {
            this.chart1 = echarts.init(document.getElementById('echartContainer'))
            this.chart1.setOption(this.option1, true)
            window.onresize = this.chart1.resize
            this.getData()
            this.$nextTick(function () {
                this.drawPie('main')
            })
        },
        methods: {
            ...mapGetters(['projectSelected']),
            getData() {
                api.getStatisticsList()
                    .then(data => {
                        this.statisticsList = data
                        var i = 0
                        for (var item of this.statisticsList) {
                            console.log(item)
                            this.opinionData[i].value = item.profit
                            i = i + 1
                        }
                        this.$nextTick(function () {
                            this.drawPie('main')
                        })
                    })
                api.getsumUp()
                    .then(data => {
                        this.qualityList = data
                        console.log("qualityList")
                        console.log(this.qualityList)
                        this.change()
                    })
            },
            change() {
                this.chart1.setOption({
                    title:{
                        text:'总收支统计',
                        left: 'center',
                    },
                    tooltip: {},
                    xAxis: {
                        data: ['总收入', '总支出'],
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
                            },
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
                        data: [this.qualityList.total_income,this.qualityList.total_outcome],
                        itemStyle: {
                            normal: {
                                //每根柱子颜色设置
                                color: '#1890FF'
                            }
                        }
                    }]
                }, true)

            },
            drawPie(id) {
                this.charts = echarts.init(document.getElementById(id))
                this.charts.setOption({
                    title: {
                        text: '每月利润统计',
                        left: 'center',
                    },
                    tooltip: {
                        trigger: 'item',
                    },
                    legend: {
                        orient: 'vertical',
                        x: 'left',
                        data: this.opinion
                    },
                    series: [
                        {
                            name: '利润',
                            type: 'pie',
                            radius: ['0%', '70%'],
                            avoidLabelOverlap: false,
                            label: {
                                normal: {
                                    show: true,
                                    position: 'inside'
                                },
                                emphasis: {
                                    show: true,
                                    textStyle: {
                                        fontSize: '30',
                                        fontWeight: 'bold'
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            data: this.opinionData
                        }
                    ]
                })
            },
            handleClose() {
                this.modal.type = ''
                this.modal.record = null
                this.modal.visible = false
                this.getData()
            },
            handleCreate() {
                this.modal.type = 'create'
                this.modal.record = null
                this.modal.visible = true
            },
            handleEdit(record) {
                this.modal.type = 'edit'
                this.modal.record = record
                this.modal.visible = true
            },
            handleDelete(record) {
                api.deleteCustomerInfo(record.id)
                    .then(data => {
                        this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
                    })
            },
        }
    }
</script>

<style scoped>

</style>