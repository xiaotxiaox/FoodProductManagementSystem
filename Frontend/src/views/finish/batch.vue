<template>
  <div>
    <batch-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :batch_id="modal.batch_id"
      v-if="modal.visible"
      @close="handleClose()">
    </batch-modal>
    <a-card style="margin-bottom: 16px">
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
    <a-card style="margin-bottom: 16px" title="成品批次表">
      <a-row>
        <a-col
          class="item"
          :xs="{ span: 24 }"
          :sm="{ span: 12 }"
          :xl="{ span: 4 }">
          <a-button
            type="primary"
            icon="plus"
            style="width: 100%;float:right;margin-bottom: 16px"
            @click="handleCreate()">
            新建
          </a-button>
        </a-col>
      </a-row>
      <!--<a-spin :spinning="status.listLoading">-->
      <a-table
        bordered
        :columns="columns"
        :dataSource="batchList"
        :scroll="{ x: 1300 }"
        rowKey="batch_id"
        :pagination="false">
        <template slot="operation" slot-scope="text, record, index">
          <a-button @click="handleEdit(record)">编辑</a-button>
          <a-popconfirm
            title="确认删除吗?"
            @confirm="handleDelete(record)">
            <a-button type="danger">删除</a-button>
          </a-popconfirm>
        </template>
      </a-table>
      <!--</a-spin>-->
    </a-card>
  </div>
</template>

<script>
    import echarts from 'echarts'
    import BatchModal from './components/BatchModal'
    import api from '../../api/sale'
    import {mapGetters} from 'vuex'

    const columns = [
        {
            title: '批次编号',
            dataIndex: 'order_id',
            width: '20%',
            align: 'center'
        }, {
            title: '商品名称',
            dataIndex: 'customer_name',
            width: '10%',
            align: 'center'
        },
        {
            title: '商品数量',
            dataIndex: 'item_name.label',
            width: '10%',
            align: 'center'
        }, {
            title: '成品时间',
            dataIndex: 'item_num',
            width: '10%',
            align: 'center'
        }, {
            title: '保质期',
            dataIndex: 'order_date',
            width: '20%',
            align: 'center'
        },
        {
            title: '处理人',
            dataIndex: 'staff',
            width: '20%',
            align: 'center'
        },
        {
            title: '编辑',
            dataIndex: 'operation',
            align: 'center',
            scopedSlots: {customRender: 'operation'}
        }
    ]
    const batchList = [
        {
            batch_id: '123',
            customer_name: '123'
        }
    ]
    export default {
        name: "batch",
        components:{
            BatchModal
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
                qualityList: [],
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
                            formatter: function (value) {
                                var texts = [];
                                if (value == "1") {
                                    texts.push('市级');
                                } else if (value == "2") {
                                    texts.push('省级');
                                } else if (value == "3") {
                                    texts.push('国级');
                                }
                                return texts;
                            }
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
                opinion: ['收入', '支出'],
                opinionData: [
                    { value: 10, name: '待付款', itemStyle: { color: '#00b0f0' } },
                    { value: 20, name: '进行中', itemStyle: { color: '#7030a0' } }
                ],
                modal: {
                    record: null,
                    visible: false,
                    type: '1',
                    batch_id: this.id
                },
                columns,
                batchList,
                // project_id: this.projectSelected().id
            }
        },
        mounted() {
            this.chart1 = echarts.init(document.getElementById('echartContainer'))
            this.chart1.setOption(this.option1, true)
            window.onresize = this.chart1.resize
            //this.getData(),
            this.$nextTick(function () {
                this.drawPie('main')
            })
        },
        methods: {
            ...mapGetters(['projectSelected']),
            getData() {
                api.getCustomerInfoList(this.project_id)
                    .then(data => {
                        data.order_date = new moment(data.order_date)
                        data.get_date = new moment(data.get_date)
                        this.orderList = data
                        this.status.listLoading = false
                    })
                api.getQualityList(this.project_id)
                    .then(data => {
                        console.log(data)
                        this.qualityList = data
                        this.change()
                        this.status.listLoading = false
                    })
                api.getSecurityNotice(this.project_id)
                    .then(data => {
                        this.not_num = data.not_num
                        this.reply_num = data.reply_num
                        this.opinionData[0].value=this.reply_num
                        this.opinionData[1].value=this.not_num
                        this.$nextTick(function () {
                            this.drawPie('main')
                        })
                    })
            },
            change() {
                var arrName = []
                var arrProgress = []
                for (var building of this.qualityList) {
                    console.log(this.qualityList)
                    console.log(building)
                    arrName.push(building.name)
                    arrProgress.push(building.awards)
                }
                // console.log(building)
                // console.log(arrName)
                // console.log(arrProgress)
                this.chart1.setOption({
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
                        axisLabel: {
                            show: true,
                            textStyle: {
                                fontSize: 14
                            },
                            formatter: function (value) {
                                var texts = [];
                                if (value == "1") {
                                    texts.push('市级');
                                } else if (value == "2") {
                                    texts.push('省级');
                                } else if (value == "3") {
                                    texts.push('国级');
                                }
                                return texts;
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
                        data: arrProgress,
                        itemStyle: {
                            normal: {
                                //每根柱子颜色设置
                                color: '#1890FF'
                            }
                        }
                    }]
                }, true)

            },
            drawPie (id) {
                this.charts = echarts.init(document.getElementById(id))
                this.charts.setOption({
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
                            name: '百分比',
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