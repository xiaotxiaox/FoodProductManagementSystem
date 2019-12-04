<template>
  <div>
    <ingredient-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :ingredient_id="modal.ingredient_id"
      v-if="modal.visible"
      @close="handleClose()">
    </ingredient-modal>
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
    <a-card style="margin-bottom: 16px" title="订单表">
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
        :dataSource="ingredientList"
        :scroll="{ x: 1300 }"
        rowKey="ingredient_id"
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
    import IngredientModal from './components/IngredientModal'
    import echarts from 'echarts'
    import api from '../../api/sale'
    import {mapGetters} from 'vuex'

    const columns = [
        {
            title: '采购编号',
            dataIndex: 'ingredient_id',
            width: '20%',
            align: 'center'
        }, {
            title: '原料名称',
            dataIndex: 'customer_name',
            width: '10%',
            align: 'center'
        },
        {
            title: '购买数量',
            dataIndex: 'item_name.label',
            width: '10%',
            align: 'center'
        }, {
            title: '总金额',
            dataIndex: 'money',
            width: '10%',
            align: 'center'
        }, {
            title: '申请日期',
            dataIndex: 'item_num',
            width: '10%',
            align: 'center'
        }, {
            title: '申请人',
            dataIndex: 'order_date',
            width: '20%',
            align: 'center'
        }, {
            title: '批准人',
            dataIndex: 'get_date',
            width: '20%',
            align: 'center'
        }, {
            title: '编辑',
            dataIndex: 'operation',
            align: 'center',
            scopedSlots: {customRender: 'operation'}
        }
    ]
    const ingredientList = [
        {
            ingredient_id: '123',
            customer_name: '123'
        }
    ]
    export default {
        name: "ingredient",
        components: {
            IngredientModal
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
                opinion: ['待处理', '已同意','未同意'],
                opinionData: [
                    { value: 10, name: '待处理', itemStyle: { color: '#00b0f0' } },
                    { value: 20, name: '已同意', itemStyle: { color: '#7030a0' } },
                    { value: 10, name: '未同意', itemStyle: { color: '#00CD00' } }
                ],
                modal: {
                    record: null,
                    visible: false,
                    type: '1',
                    ingredient_id: this.id
                },
                columns,
                ingredientList,
                // project_id: this.projectSelected().id
            }
        },
        mounted() {
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
                        this.ingredientList = data
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