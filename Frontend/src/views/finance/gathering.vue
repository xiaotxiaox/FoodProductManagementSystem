<template>
  <div>
    <order-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :customer_id="modal.order_id"
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
              <!--              <a-card title="安全生产" :bordered=true>-->
              <!--                <p style="text-align: center">{{Days}}天</p>-->
              <!--              </a-card>-->
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
    <a-card style="margin-bottom: 16px" title="收款">
      <!--<a-row>-->
        <!--<a-col-->
          <!--class="item"-->
          <!--:xs="{ span: 24 }"-->
          <!--:sm="{ span: 12 }"-->
          <!--:xl="{ span: 4 }">-->
          <!--<a-button-->
            <!--type="primary"-->
            <!--icon="plus"-->
            <!--style="width: 100%;float:right;margin-bottom: 16px"-->
            <!--@click="handleCreate()">-->
            <!--新建-->
          <!--</a-button>-->
        <!--</a-col>-->
      <!--</a-row>-->
      <!--<a-spin :spinning="status.listLoading">-->
      <a-table
        bordered
        :columns="columns"
        :dataSource="orderList"
        :scroll="{ x: 1300 }"
        rowKey="customer_id"
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
  import OrderModal from './components/OrderModal'
  import echarts from 'echarts'
  import api from '../../api/sale'
  import {mapGetters} from 'vuex'
  const columns = [
    {
      title: '订单编号',
      dataIndex: 'order_id',
      width: '20%',
      align: 'center'
    }, {
      title: '客户姓名',
      dataIndex: 'customer_name',
      width: '10%',
      align: 'center'
    },{
      title: '订单总金额',
      dataIndex: 'order_amount',
      width: '10%',
      align: 'center'
    },{
      title: '优惠后金额',
      dataIndex: 'discount_amount',
      width: '10%',
      align: 'center'
    },{
      title: '已付货款',
      dataIndex: 'payment',
      width: '10%',
      align: 'center'
    },
    {
      title: '预付款日期',
      dataIndex: 'order_date',
      width: '10%',
      align: 'center'
    },{
      title: '尾款日期',
      dataIndex: 'get_date',
      width: '10%',
      align: 'center'
    },
    {
      title: '订单状态',
      dataIndex: 'state',
      width: '10%',
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
  const orderList=[
    {
      order_id: '123',
      customer_name:'123'
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
          opinion: ['待付款', '进行中','退货中', '订单完成','退货完成','异常'],
          opinionData: [
              { value: 10, name: '待付款', itemStyle: { color: '#00b0f0' } },
              { value: 20, name: '进行中', itemStyle: { color: '#7030a0' } },
              { value: 10, name: '已完成', itemStyle: { color: '#00CD00' } }
          ],
        modal: {
          record: null,
          visible: false,
          type: '1',
          order_id: this.id
        },
        columns,
        orderList,
        // project_id: this.projectSelected().id
      }
    },
    mounted(){
      //this.getData()
        this.$nextTick(function () {
            this.drawPie('main')
        })
    },
    methods: {
       ...mapGetters(['projectSelected']),
      getData(){
         api.getCustomerInfoList(this.project_id)
          .then(data => {
            this.customerInfoList = data
            this.status.listLoading = false
            console.log(1)
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