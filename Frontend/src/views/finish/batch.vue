<template>
  <div>
    <batch-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :id="modal.id"
      v-if="modal.visible"
      @close="handleClose()">
    </batch-modal>
    <a-card style="margin-bottom: 16px">
      <a-row>
        <a-col
          :xs="{ span: 24 }"
          :sm="{ span: 24 }"
          :xl="{ span: 24 }">
          <div style="width: 100%;height:100%">
            <div id="main" style="width: 100%;height: 400px;"></div>
          </div>
        </a-col>
      </a-row>
    </a-card>
    <a-card style="margin-bottom: 16px" title="合格成品批次表">
      <a-table
        bordered
        :columns="columns1"
        :dataSource="batchList"
        :scroll="{ x: 1300 }"
        rowKey="batch_id"
        :pagination="false">
      </a-table>
    </a-card>
    <a-card style="margin-bottom: 16px" title="不合格成品批次表">
      <a-table
        bordered
        :columns="columns2"
        :dataSource="disqualifiedList"
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
    </a-card>
  </div>
</template>

<script>
    import echarts from 'echarts'
    import BatchModal from './components/BatchModal'
    import api from '../../api/finish'
    import moment from 'moment'

    const columns1 = [
        {
            title: '编号',
            dataIndex: 'id',
            width: '10%',
            align: 'center'
        }, {
            title: '批次编号',
            dataIndex: 'producing.id',
            width: '10%',
            align: 'center'
        },
        {
            title: '完成时间',
            dataIndex: 'producing.produceDate',
            width: '20%',
            align: 'center'
        },
        {
            title: '处理人',
            dataIndex: 'user.name',
            width: '10%',
            align: 'center'
        }
    ]
    const columns2 = [
        {
            title: '编号',
            dataIndex: 'id',
            width: '20%',
            align: 'center'
        }, {
            title: '批次编号',
            dataIndex: 'producing.id',
            width: '10%',
            align: 'center'
        },
        {
            title: '完成时间',
            dataIndex: 'producing.produceDate',
            width: '10%',
            align: 'center'
        }, {
            title: '处理方式',
            dataIndex: 'way',
            width: '10%',
            align: 'center'
        }, {
            title: '是否处理',
            dataIndex: 'isHandle',
            customRender: (text, record) => {
                if (record.isHandle === 0)
                    return "未处理"
                else if (record.isHandle === 1)
                    return '已处理'
            },
            width: '20%',
            align: 'center'
        },
        {
            title: '备注',
            dataIndex: 'note',
            width: '10%',
            align: 'center'
        },
        {
            title: '处理人',
            dataIndex: 'user.name',
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
    export default {
        name: "batch",
        components: {
            BatchModal
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
                charts: '',
                opinion: ['合格产品', '不合格产品'],
                opinionData: [
                    { value: 10, name: '合格产品', itemStyle: { color: '#00b0f0' } },
                    { value: 20, name: '不合格产品', itemStyle: { color: '#7030a0' } }
                ],
                modal: {
                    record: null,
                    visible: false,
                    type: '1',
                    id: this.id
                },
                columns1,
                columns2,
                batchList:[],
                disqualifiedList:[]
            }
        },
        mounted() {
            this.getData()
            this.$nextTick(function () {
                this.drawPie('main')
            })
        },
        methods: {
            getData() {
                api.getBatchList()
                    .then(data => {
                        console.log(data)
                        this.batchList = data
                    })
                api.getDisqualifiedList()
                    .then(data => {
                        console.log(data)
                        this.disqualifiedList = data
                    })
                api.getSecurityNotice()
                    .then(data => {
                        this.not_num = data.qualified
                        this.reply_num = data.unqualified
                        this.opinionData[0].value=this.reply_num
                        this.opinionData[1].value=this.not_num
                        console.log(data)
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
                            name: '数量',
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