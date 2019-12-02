<template>
  <div>
    <a-card style="margin-bottom: 16px">
      <a-row>
        <a-col
          :xs="{ span: 24 }"
          :sm="{ span: 24 }"
          :xl="{ span: 24 }">
          <div style="width: 50%;height:100%;float: left">
            <div id="main" style="width: 100%;height: 400px;"></div>
          </div>
          <div style="width: 50%;height:100%;float: right">
            <div id="echartContainer" style="width: 100%; height: 400px;"></div>
          </div>
        </a-col>
      </a-row>
    </a-card>

    <invest_account-modal
      :record="modal2.record"
      :visible="modal2.visible"
      :type="modal2.type"
      :change_id="modal2.change_id"
      v-if="modal2.visible"
      @close="handleClose2()">
    </invest_account-modal>
    <invest_process-modal
      :record="modal1.record"
      :visible="modal1.visible"
      :type="modal1.type"
      :process_id="modal1.process_id"
      v-if="modal1.visible"
      @close="handleClose1()">

    </invest_process-modal>
    <a-card style="margin-bottom: 16px">
      <span>进度款支付台账</span>
      <a-row class="item">
        <a-col
          :xs="{ span: 24 }"
          :sm="{ span: 12, offset: 12 }"
          :xl="{ span: 4, offset: 20 }">
          <a-button
            type="primary"
            icon="plus"
            style="width: 100%"
            @click="handleCreate1()">
            新建
          </a-button>
        </a-col>
      </a-row>
      <a-table
        bordered
        :columns="columns1"
        :dataSource="processList"
        rowKey="id"
        :scroll="{x:1300}"
        :pagination="false">
        <template slot="operation5" slot-scope="text, record, index">
          <a-button @click="$router.push({'name': 'accountProcessDetail', params: {id: record.id}})">查看</a-button>
        </template>
        <template slot="operation6" slot-scope="text, record, index">
          <a-button @click="$router.push({'name': 'accountProcessFile', params: {id: record.id}})">查看</a-button>
        </template>
        <template slot="operation4" slot-scope="text, record, index">
          <a-button @click="handleEdit1(record)">编辑</a-button>
          <a-popconfirm
            title="确认删除吗?"
            @confirm="handleDelete1(record)">
            <a-button type="danger">删除</a-button>
          </a-popconfirm>
        </template>

      </a-table>

    </a-card>
    <a-card style="margin-bottom: 16px">
      <span>变更台账</span>
      <a-row class="item">
        <a-col
          :xs="{ span: 24 }"
          :sm="{ span: 12, offset: 12 }"
          :xl="{ span: 4, offset: 20 }">
          <a-button
            type="primary"
            icon="plus"
            style="width: 100%"
            @click="handleCreate2()">
            新建
          </a-button>
        </a-col>
      </a-row>
      <a-table
        bordered
        :columns="columns2"
        :dataSource="changeList"
        rowKey="id"
        :pagination="false">
        <template slot="InDe" slot-scope="text, record, index">
          {{getInDe(record)}}
        </template>
        <template slot="contract" slot-scope="text, record, index">
          {{getContract(record)}}
        </template>
        <template slot="change" slot-scope="text, record, index">
          {{getChange(record)}}
        </template>
        <template slot="operation1" slot-scope="text, record, index">
          <a-button @click="$router.push({'name': 'accountChangesFile1', params: {id: record.id}})">查看</a-button>
        </template>
        <template slot="operation2" slot-scope="text, record, index">
          <a-button @click="$router.push({'name': 'accountChangesFile2', params: {id: record.id}})">查看</a-button>
        </template>
        <template slot="operation3" slot-scope="text, record, index">
          <a-button @click="handleEdit2(record)">编辑</a-button>
          <a-popconfirm
            title="确认删除吗?"
            @confirm="handleDelete2(record)">
            <a-button type="danger">删除</a-button>
          </a-popconfirm>
        </template>
      </a-table>

    </a-card>
  </div>
</template>

<script>

  import ARow from "ant-design-vue/es/grid/Row";
import echarts from 'echarts'
  import {mapGetters} from 'vuex'
  const columns1 = [
    {
      title: '投资单位名称',
      dataIndex: 'name',
      width: '15%',
      align: 'center'
    },
    {
      title: '类别',
      dataIndex: 'style',
      width: '10%',
      align: 'center'
    },
    {
      title: '项目名称',
      dataIndex: 'project_name',
      width: '15%',
      align: 'center'
    },
    {
      title: '合同额',
      dataIndex: 'contract_money',
      width: '6%',
      align: 'center'
    },
    {
      title: '结算额',
      dataIndex: 'true_money',
      width: '6%',
      align: 'center'
    },
    {
      title: '本次应付',
      dataIndex: 'should_money',
      width: '10%',
      align: 'center'
    },
    {
      title: '支付累计',
      dataIndex: 'paidTotal',
      width: '10%',
      align: 'center'
    },
    {
      title: '比例',
      dataIndex: 'ratio',
      customRender: (text, row, index) => {
          return text.toFixed(2)+'%'
        },
      width: '18%',
      align: 'center'
    },
    {
      title: '支付详情',
      dataIndex: 'operation5',
      scopedSlots: {customRender: 'operation5'},
      align: 'center'
    },
    {
      title: '中标清单',
      dataIndex: 'operation6',
      scopedSlots: {customRender: 'operation6'},
      align: 'center'
    },
    {
      title: '编辑',
      dataIndex: 'operation4',
      scopedSlots: {customRender: 'operation4'},
      align: 'center'
    }
  ]


const columns2 = [
  {
    title: '用料名称',
    dataIndex: 'name',
    width: '12%',
    align: 'center'
  },
  {
    title: '清单外',
    dataIndex: 'out_list',
    width: '12%',
    align: 'center'
  },
   {
    title: '合同价',
    width: '10%',
    align: 'center',
    scopedSlots: { customRender: 'contract' }
  },
  {
    title: '变更后价',
    width: '8%',
    align: 'center',
    scopedSlots: { customRender: 'change' }
  },
  {
    title: '变更前单价',
    dataIndex: 'price_before',
    width: '12%',
    align: 'center'
  },
  {
    title: '变更后单价',
    dataIndex: 'price_after',
    width: '12%',
    align: 'center'
  },
  {
    title: '变更前量',
    dataIndex: 'number_before',
    width: '12%',
    align: 'center'
  },
  {
    title: '变更后量',
    dataIndex: 'number_after',
    width: '12%',
    align: 'center'
  },
  {
    title: '增减',
    width: '10%',
    align: 'center',
    scopedSlots: { customRender: 'InDe' }
  },
  {
    title: '附件',
    width: '12%',
    dataIndex: ' operation1',
    align: 'center',
    scopedSlots: { customRender: 'operation1' }
  },
  {
    title: '合同',
    dataIndex: 'operation2',
    scopedSlots: { customRender: 'operation2' },
    align: 'center'
  },
  {
    title: '编辑',
    dataIndex: 'operation3',
    scopedSlots: { customRender: 'operation3' },
    align: 'center'
  }
]
import api from '../../../../api/implement'
import invest_accountModal from './components/invest_accountModal'
import invest_processModal from './components/invest_processModal'


  export default {
    name: 'invest_account',
    components: {
      ARow,
      invest_accountModal,
      invest_processModal
    },
    props: {
      id: Number
    },
    data() {
      return {
        project_id: this.projectSelected().id,
        layout: {
          'label-col': {span: 6},
          'wrapper-col': {span: 18}
        },
        status: {
          loading: true
        },
        form: this.$form.createForm(this),
        changes: {},
        process: {},
        modal1: {
          record: null,
          visible: false,
          type: '1',
          process_id: this.id
        },
        modal2: {
          record: null,
          visible: false,
          type: '1',
          change_id: this.id
        },
        columns1,
        columns2,
        processList: [],
        changeList: [],
        sumList: [],
        payAll: 0,
        settlement: 0,
        add: 0,
        bghj: 0,
        zj: 0,
        option1: {
          tooltip: {},
          xAxis: {
            data: ['清单外+合同价', '变更后价', '增减'],
            axisLabel: {
              show: true,
              textStyle: {
                fontSize: 12
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
            name: '金额',
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
        chart1: null,
        charts: '',
        opinion: ['合同额', '支付累计'],
        opinionData: [
          {value: 100, name: '合同额', itemStyle: {color: '#00b0f0'}},
          {value: 0, name: '支付累计', itemStyle: {color: '#7030a0'}},
        ],
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
        this.status.loading = true
        api.getInvestProcessList(this.$store.getters.projectSelected.id)
          .then(data => {
            for (var list of data) {
              this.payAll += list.paidTotal
              list.ratio = (this.payAll / list.contract_money) * 100
              this.settlement += list.contract_money
            }
            this.processList = data
            this.status.loading = false
            this.opinionData[0].value = this.settlement - this.payAll
            this.opinionData[1].value = this.payAll
            this.$nextTick(function () {
              this.drawPie('main')
            })
          })


        api.getInvestProcess(this.id)
          .then(data => {
            this.process = data
          })

        api.getInvestChangeList(this.$store.getters.projectSelected.id)
          .then(data => {
            for (var i of data) {
              if (i.price_before && i.number_before)
                this.add = parseFloat(this.add) + parseFloat(i.out_list) + parseFloat(i.price_before) * parseFloat(i.number_before)
              if (i.price_after && i.number_after)
                this.bghj += i.price_after * i.number_after
              if (i.price_after && i.number_after && i.price_before && i.number_before)
                this.zj += (i.price_after * i.number_after - i.price_before * i.number_before)
            }
            this.changeList = data
            this.changes = data
            this.status.loading = false
            this.change()
          })
        api.getInvestChange()
          .then(data => {
            this.changes = data
          })

      },
      change() {
        // console.log(building)
        // console.log(arrName)
        // console.log(arrProgress)
        var dataList = []
        dataList.push(this.add)
        console.log(this.add)
        dataList.push(this.bghj)
        console.log(this.bghj)
        dataList.push(this.zj)
        console.log(this.zj)
        this.chart1.setOption({
          tooltip: {},
          xAxis: {
            data: ['清单外+合同价', '变更后价', '增减'],
            axisLabel: {
              show: true,
              textStyle: {
                fontSize: 12
              }
            }
          },
          yAxis: {
            name:"万元",
            axisLabel: {
              formatter: function (value, index){
                return value/10000
              },
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
            name: '金额',
            type: 'bar',
            barWidth: 50,
            data: dataList,
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

      },
      drawPie(id) {
        this.charts = echarts.init(document.getElementById(id))
        this.charts.setOption({
          tooltip: {
            formatter: function (params, ticket, callback) {
              if (params.name == '支付累计')
                return params.name + ':' + params.percent + '%'
            },
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
                    fontWeight: 'blod'
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
      handleClose1() {
        this.modal1.type = ''
        this.modal1.record = null
        this.modal1.visible = false
        this.getData()
      },
      handleClose2() {
        this.modal2.type = ''
        this.modal2.record = null
        this.modal2.visible = false
        this.getData()
      },
      handleCreate1() {
        this.modal1.type = 'create'
        this.modal1.record = null
        this.modal1.visible = true
      },
      handleCreate2() {
        this.modal2.type = 'create'
        this.modal2.record = null
        this.modal2.visible = true
      },
      handleEdit1(record) {
        this.modal1.type = 'edit'
        this.modal1.record = record
        this.modal1.visible = true
      },
      handleEdit2(record) {
        this.modal2.type = 'edit'
        this.modal2.record = record
        this.modal2.visible = true
      },
      handleDelete1(record) {
        api.deleteInvestProcess(record.id)
          .then(data => {
            this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
            this.getData()
          })
      },
      handleDelete2(record) {
        api.deleteInvestChange(record.id)
          .then(data => {
            this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
            this.getData()
          })
      },
      getInDe (record) {
      let price_before = parseFloat(record.price_before)
      let number_before = parseFloat(record.number_before)
      let price_after = parseFloat(record.price_after)
      let number_after = parseFloat(record.number_after)
      if (price_before && number_before && price_after && number_after) {
        return `${(price_after * number_after - price_before * number_before).toFixed(2)}`
      } else {
        return 0
      }
    },
      parseFloat(f) {
        return parseFloat(f)
      },
      getContract(record) {
        let price_before = parseFloat(record.price_before)
        let number_before = parseFloat(record.number_before)
        if (price_before && number_before) {
          return `${(price_before * number_before).toFixed(2)}`
        } else {
          return 0
        }
      },
      getChange(record) {
        let price_after = parseFloat(record.price_after)
        let number_after = parseFloat(record.number_after)
        if (price_after && number_after) {
          return `${(price_after * number_after).toFixed(2)}`
        } else {
          return 0
        }
      }
    }
  }


</script>

<style scoped="less">

</style>