<template>
  <div>
    <order-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :id="modal.id"
      v-if="modal.visible"
      @close="handleClose()">
    </order-modal>
    <a-card style="margin-bottom: 16px">
      <a-row>
        <a-col
          :xs="{ span: 24 }"
          :sm="{ span: 12 }"
          :xl="{ span: 12 }">
          <a-col style="text-align: center"
                 :xs="{ span: 24 }"
                 :sm="{ span: 16,offset:4 }"
                 :xl="{ span: 17,offset:4}">
            <a-row>
              <iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=55"
                      style="border:solid 1px #7ec8ea" height="300" frameborder="0" marginwidth="0" marginheight="0"
                      scrolling="no"></iframe>
            </a-row>
            <a-row>
              <a-card title="工作统计" :bordered=true>
                <p style="text-align: center">累计处理订单{{this.state4}}个</p>
              </a-card>
            </a-row>
          </a-col>
        </a-col>
        <a-col
          :xs="{ span: 24 }"
          :sm="{ span: 12 }"
          :xl="{ span: 12 }">
          <div id="app">
            <div id="main" style="width: 100%; height: 450px;"></div>
          </div>
        </a-col>
      </a-row>
    </a-card>
    <a-card style="margin-bottom: 16px" title="收款表">
<!--      <a-row>-->
<!--        <a-col-->
<!--          class="item"-->
<!--          :xs="{ span: 24 }"-->
<!--          :sm="{ span: 12 }"-->
<!--          :xl="{ span: 4 }">-->
<!--          <a-button-->
<!--            type="primary"-->
<!--            icon="plus"-->
<!--            style="width: 100%;float:right;margin-bottom: 16px"-->
<!--            @click="handleCreate()">-->
<!--            新增订单-->
<!--          </a-button>-->
<!--        </a-col>-->
<!--      </a-row>-->
      <!--<a-spin :spinning="status.listLoading">-->
      <a-table
        bordered
        :columns="columns"
        :dataSource="orderList"
        :scroll="{ x: 1300 }"
        rowKey="id"
        :pagination="false">
        <template slot="operation" slot-scope="text, record, index">
          <a-button @click="handleEdit(record)">处理</a-button>
        </template>
      </a-table>
      <!--</a-spin>-->
    </a-card>
  </div>
</template>

<script>
    import OrderModal from './components/OrderModal'
    import echarts from 'echarts'
    import moment from 'moment'
    import api from '../../api/sale'
    import {mapGetters} from 'vuex'

    const columns = [
        {
            title: '订单编号',
            dataIndex: 'id',
            width: '20%',
            align: 'center'
        }, {
            title: '客户姓名',
            dataIndex: 'custom.name',
            width: '10%',
            align: 'center'
        },
        {
            title: '商品名称',
            dataIndex: 'good.name',
            width: '10%',
            align: 'center'
        }, {
            title: '商品数量',
            dataIndex: 'count',
            width: '10%',
            align: 'center'
        }, {
            title: '订单总金额',
            dataIndex: 'totalCost',
            width: '10%',
            align: 'center'
        },{
            title: '优惠后金额',
            dataIndex: 'discountCost',
            width: '10%',
            align: 'center'
        }, {
            title: '已付金额',
            dataIndex: 'paidMoney',
            width: '10%',
            align: 'center'
        }, {
            title: '预付款日期',
            dataIndex: 'willDate',
            width: '20%',
            align: 'center'
        }, {
            title: '结款日期',
            dataIndex: 'finalDate',
            width: '20%',
            align: 'center'
        }, {
            title: '订单状态',
            dataIndex: 'state',
            width: '20%',
            align: 'center',
            customRender: (text, record) => {
                if (record.state === 1)
                    return '待付款'
                else if (record.state === 2)
                    return '进行中'
                else if (record.state === 3)
                    return '退货中'
                else if (record.state === 4)
                    return '订单完成'
                else if (record.state === 5)
                    return '退货完成'
                else if (record.state === 6)
                    return '异常'
            },
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
        name: "gathering",
        components: {
            OrderModal
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
                charts: '',
                opinion: ['待付款', '进行中', '订单完成'],
                opinionData: [
                    { value: 10, name: '待付款', itemStyle: { color: '#00b0f0' } },
                    { value: 20, name: '进行中', itemStyle: { color: '#7030a0' } },
                    { value: 10, name: '订单完成', itemStyle: { color: '#00CD00' } }
                ],
                modal: {
                    record: null,
                    visible: false,
                    type: '1',
                    id: this.id
                },
                columns,
                orderList:[]
            }
        },
        mounted() {
            this.getData(),
                this.$nextTick(function () {
                    this.drawPie('main')
                })
        },
        methods: {
            ...mapGetters(['projectSelected']),
            getData() {
                api.getOrderSellList()
                    .then(data => {
                        data.willDate = new moment(data.willDate)
                        data.finalDate = new moment(data.finalDate)
                        console.log(data)
                        this.orderList = data
                        this.status.listLoading = false
                    })
                api.getStatistics()
                    .then(data => {
                        this.state1 = data.state1Number
                        this.state2 = data.state2Number
                        this.state4 = data.state4Number
                        this.opinionData[0].value=this.state1
                        this.opinionData[1].value=this.state2
                        this.opinionData[2].value=this.state4
                        this.$nextTick(function () {
                            this.drawPie('main')
                        })
                    })
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
                api.deleteOrder(record.id)
                    .then(data => {
                        this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
                    })
            },
        }
    }
</script>

<style scoped>

</style>