<template>
  <div>
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
    <a-card style="margin-bottom: 16px" title="原料库存表(未过期）">
      <a-table
        bordered
        :columns="columns"
        :dataSource="inList"
        :scroll="{ x: 1300 }"
        rowKey="id"
        :pagination="false">
<!--        <template slot="operation" slot-scope="text, record, index">-->
<!--          <a-button @click="handleEdit(record)">处理</a-button>-->
<!--        </template>-->
      </a-table>
      <!--</a-spin>-->
    </a-card>
    <a-card style="margin-bottom: 16px" title="原料库存表（已过期）">
      <a-table
        bordered
        :columns="columns"
        :dataSource="outList"
        :scroll="{ x: 1300 }"
        rowKey="id"
        :pagination="false">
        <!--        <template slot="operation" slot-scope="text, record, index">-->
        <!--          <a-button @click="handleEdit(record)">处理</a-button>-->
        <!--        </template>-->
      </a-table>
      <!--</a-spin>-->
    </a-card>
  </div>
</template>

<script>

    import echarts from 'echarts'
    import moment from 'moment'
    import api from '../../api/ingredient'
    import {mapGetters} from 'vuex'

    const columns = [
        {
            title: '库存编号',
            dataIndex: 'id',
            width: '20%',
            align: 'center'
        }, {
            title: '商品名称',
            dataIndex: 'name',
            width: '10%',
            align: 'center'
        }, {
            title: '库存数量',
            dataIndex: 'num',
            width: '10%',
            align: 'center'
        }, {
            title: '保质期',
            dataIndex: 'timeprotect',
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
        name: "ingredientInventory",
        data() {
            return {
                status: {
                    listLoading: true,
                    tableLoading: true
                },
                charts: '',
                opinion: ['未过期', '已过期'],
                opinionData: [
                    { value: 10, name: '未过期', itemStyle: { color: '#00b0f0' } },
                    { value: 20, name: '已过期', itemStyle: { color: '#7030a0' } }
                ],
                columns,
                inList:[],
                outList:[]
            }
        },
        mounted() {
            this.getData(),
                this.$nextTick(function () {
                    this.drawPie('main')
                })
        },
        methods: {
            getData() {
                api.getIntimeList()
                    .then(data => {
                        console.log(data)
                        this.inList = data
                    })
                api.getOuttimeList()
                    .then(data => {
                        console.log(data)
                        this.outList = data
                    })
                api.getIngredientStatistics()
                    .then(data => {
                        this.timein = data.timein
                        this.timeout = data.timeout
                        this.opinionData[0].value=this.timein
                        this.opinionData[1].value=this.timeout
                        this.$nextTick(function () {
                            this.drawPie('main')
                        })
                    })
            },
            drawPie (id) {
                this.charts = echarts.init(document.getElementById(id))
                this.charts.setOption({
                    title:{
                        text:'原料保质期统计',
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