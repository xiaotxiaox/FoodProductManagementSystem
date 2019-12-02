<template>
  <div>
    <bid-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :bid_id="modal.bid_id"
      v-if="modal.visible"
      @close="handleClose()">
    </bid-modal>
    <a-card style="margin-bottom: 16px">
      <a-row>
        <a-col
          :xs="{span: 24}"
          :sm="{span: 12}"
          :xl="{span: 20}">
          <span style="font-size: 20px; margin-left: 4px">{{application.title}}</span>
        </a-col>
        <a-col
          class="item"
          :xs="{ span: 24 }"
          :sm="{ span: 12 }"
          :xl="{ span: 4 }">
          <a-button
            type="primary"
            icon="plus"
            style="width: 100%"
            @click="handleCreate()">
            新建
          </a-button>
        </a-col>
      </a-row>
      <a-spin :spinning="status.listLoading">
        <a-table
          bordered
          :columns="columns"
          :dataSource="matterList"
          :scroll="{x:1100}"
          rowKey="bid_id"
          :pagination="false">
          <template slot="bid_file" slot-scope="text, record, index">
          <a-button type="primary" @click="$router.push({'name': 'BidFileDetail', params: {id: record.bid_id}})">
            招标文件
          </a-button>
        </template>
         <template slot="win_bid_file" slot-scope="text, record, index">
          <a-button type="primary" @click="$router.push({'name': 'WinBidFileDetail', params: {id: record.bid_id}})">
            中标文件
          </a-button>
        </template>
          <template slot="operation" slot-scope="text, record, index">
            <a-button @click="handleEdit(record)">编辑</a-button>
            <a-popconfirm
              title="确认删除吗?"
              @confirm="handleDelete(record)">
              <a-button type="danger">删除</a-button>
            </a-popconfirm>
          </template>
        </a-table>
      </a-spin>
    </a-card>
    <a-card title="总计" :bordered="false" style="text-align: center;border: 0">
      <a-col>
        <a-card-grid style="width: 35%; text-align: center">招标控制价：{{card.control_price}}元</a-card-grid>
        <a-card-grid style="width: 35%; text-align: center">中标金额：{{card.bid_price}}元</a-card-grid>
        <a-card-grid style="width: 30%; text-align: center">节约比：{{card.save_rate}}%</a-card-grid>
      </a-col>
      <a-col
        :xs="{span: 24}"
        :sm="{span: 20, offset: 2}"
        :xl="{span: 16, offset: 4}">
        <div id="app">
          <!--创建一个echarts的容器-->
          <div id="echartContainer" style="width: 100%; height: 400px;"></div>
        </div>
      </a-col>
    </a-card>
  </div>
</template>

<script>
  import echarts from 'echarts'
  import BidModal from './components/BidModal'
  import api from '../../../api/implement'
  import {mapGetters} from 'vuex'

  const columns = [
    {
      title: '名称',
      dataIndex: 'name',
      width: '8%'
    },
    {
      title: '状态',
      dataIndex: 'status',
      customRender: (text, record) => {
        if (record.status === 'DOING')
          return '进行中'
        else if (record.status === 'DONE')
          return '已完成'
        else
          return '未开始'
      },
      width: '10%'
    }, {
      title: '代理合同时间',
      dataIndex: 'contract_time',
      width: '7%',
      align: 'center'
    },
    {
      title: '预计时间',
      dataIndex: 'estimated_time',
      width: '7%',
      align: 'center'
    },
    {
      title: '公告时间',
      dataIndex: 'announce_time',
      width: '7%',
      align: 'center'
    },
    {
      title: '招标控制价',
      dataIndex: 'control_price',
      width: '7%',
      align: 'center'
    },
    {
      title: '招标时间',
      dataIndex: 'bid_time',
      width: '7%',
      align: 'center'
    },
    {
      title: '招标文件',
      dataIndex: 'bid_file',
      scopedSlots: {customRender: 'bid_file'},
      width: '10%',
      align: 'center'
    },
    {
      title: '中标文件',
      dataIndex: 'win_bid_file',
      scopedSlots: {customRender: 'win_bid_file'},
      width: '10%',
      align: 'center'
    },
    {
      title: '中标单位',
      dataIndex: 'bid_company',
      width: '7%',
      align: 'center'
    },
    {
      title: '联系人',
      dataIndex: 'linkman',
      width: '7%',
      align: 'center'
    },
    {
      title: '电话',
      dataIndex: 'phone',
      width: '7%',
      align: 'center'
    },
    {
      title: '进场时间',
      dataIndex: 'approach_time',
      width: '7%',
      align: 'center'
    },
    {
      title: '代理费',
      dataIndex: 'agency_fee',
      width: '7%',
      align: 'center'
    },
    {
      title: '中标金额',
      dataIndex: 'bid_price',
      width: '7%',
      align: 'center'
    },
    {
      title: '节约比',
      dataIndex: 'save_rate',
      customRender: (text, record) => {
        return record.save_rate + '%'
      },
      width: '7%',
      align: 'center'
    },
    {
      title: '编辑',
      dataIndex: 'operation',
      scopedSlots: {customRender: 'operation'}
    }
  ]

  export default {
    name: 'Bid',
    components: {
      BidModal
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
        option1: {
          tooltip: {},
          xAxis: {
            data: ['实际', '计划', '节约'],
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
        chart: null,
        layout: {
          'label-col': {span: 6},
          'wrapper-col': {span: 18}
        },
        form: this.$form.createForm(this),
        application: {},
        modal: {
          record: null,
          visible: false,
          type: '1',
          bid_id: this.id
        },
        columns,
        matterList: [],
        project_id: this.projectSelected().id
      }
    },
    mounted() {
      this.chart = echarts.init(document.getElementById('echartContainer'))
      this.chart.setOption(this.option1, true)
      window.onresize = this.chart.resize
      this.getData()
      this.chart.setOption(this.option1, true)

    },

    methods: {
      ...mapGetters(['projectSelected']),
      getData() {
        api.getBidList(this.project_id)
          .then(data => {
            console.log(data)
            this.matterList = data
            this.status.listLoading = false
          })
        api.getBidSum(this.project_id)
          .then(data => {
            console.log(data)
            this.card = data
            this.change()
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
        api.deleteBid(record.bid_id)
          .then(data => {
            this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
          })
      },
      parseFloat(f) {
        return parseFloat(f)
      },
      change() {
        var arr = []
        arr.push(this.card.control_price)
        arr.push(this.card.bid_price)
        arr.push(this.card.control_price - this.card.bid_price)
        this.chart.setOption({
          tooltip: {},
          xAxis: {
            data: ['实际', '计划', '节约'],
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