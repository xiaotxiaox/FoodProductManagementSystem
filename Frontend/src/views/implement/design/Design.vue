<template>
  <div>
    <a-card style="margin-bottom: 16px">
      <a-spin :spinning="status.listLoading">
        <a-steps size="small" :current="current">
          <a-step
            v-for="item in designItemList"
            :key="item.design_item_id">
            <template slot="title">
              <router-link :to="{'name': 'implementDesignDetail', params: {id: item.design_item_id}}">{{item.name}}
              </router-link>
            </template>
            <template slot="icon">
              <a-popconfirm title="设置为正在进行" @confirm="handleChangeCurrent(item)">
                <a-icon
                  :type="getIconType(item)"
                  :theme="item.design_item_id - designItemList[0].design_item_id === current ? 'filled' : 'outlined'"
                  style="cursor: pointer"></a-icon>
              </a-popconfirm>
            </template>
          </a-step>
        </a-steps>
      </a-spin>
    </a-card>
    <a-card style="margin-bottom: 16px">
      <a-spin :spinning="status.listLoading">
        <a-row :gutter="16">
          <a-col
            v-for="item in list"
            :key="item.id"
            :xs="{ span: 24 }"
            :sm="{ span: 8 }"
            :xl="{ span: 8 }">
            <a-card
              class="card"
              @click="$router.push({'name': 'implementDesignDetail', params: {id: item.design_item_id}})">
              {{item.name}}
            </a-card>
          </a-col>
        </a-row>
      </a-spin>
    </a-card>
    <div>
      <design-money-modal
        :record="modal.record"
        :visible="modal.visible"
        :type="modal.type"
        :company_id="modal.company_id"
        v-if="modal.visible"
        @close="handleClose()">
      </design-money-modal>
      <a-card>
        <a-row>
          <a-col
            :xs="{ span: 24 }"
            :sm="{ span: 24 }"
            :xl="{ span: 12 ,offset:6}">
            <div style="width: 100%;height:100%;text-align: center">
              <div id="main" style="width: 100%;height: 400px;"></div>
            </div>
          </a-col>
        </a-row>
        <a-row class="item">
          <a-col
            :xs="{ span: 24 }"
            :sm="{ span: 12, offset: 12 }"
            :xl="{ span: 4, offset: 20 }">
            <a-button
              type="primary"
              icon="plus"
              style="width: 100%"
              @click="handleCreate()">
              新建
            </a-button>
          </a-col>
        </a-row>
        <a-table
          bordered
          :columns="columns"
          :dataSource="designCompanyList"
          rowKey="company_id"
          :loading="status.tableLoading"
          :pagination="false">
          <template slot="operation5" slot-scope="text, record, index">
            <a-button @click="$router.push({'name': 'paymentDetail', params: {id: record.company_id}})">查看</a-button>
          </template>
          <template slot="ratio" slot-scope="text, record, index">
            {{getRatio(record)}}
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
      </a-card>
    </div>

  </div>
</template>

<script>
import DesignMoneyModal from './components/DesignMoneyModal'
import echarts from 'echarts'

const columns = [
  {
    title: '设计单位名称',
    dataIndex: 'name',
    width: '12%',
    align: 'center'
  },
  {
    title: '类别',
    dataIndex: 'type',
    width: '8%',
    align: 'center'
  },
  {
    title: '项目名称',
    dataIndex: 'project_name',
    width: '12%',
    align: 'center'
  },
  {
    title: '合同额',
    dataIndex: 'contract_price',
    width: '11%',
    align: 'center'
  },
  {
    title: '结算额',
    dataIndex: 'settled_price',
    width: '11%',
    align: 'center'
  },
  {
    title: '支付累计',
    dataIndex: 'paid_total',
    width: '12%',
    align: 'center'
  },
  {
    title: '本次应付',
    dataIndex: 'should_pay',
    width: '10%',
    align: 'center'
  },
  {
    title: '支付详情',
    dataIndex: 'operation5',
    scopedSlots: { customRender: 'operation5' },
    align: 'center'
  },
  {
    title: '比例',
    width: '10%',
    align: 'center',
    scopedSlots: { customRender: 'ratio' }
  },
  {
    title: '编辑',
    dataIndex: 'operation',
    scopedSlots: { customRender: 'operation' },
    align: 'center'
  }
]
import api from '../../../api/implement'

export default {
  name: 'Design',
  components: { DesignMoneyModal },
   props: {
      id: Number
    },
  data () {
    return {
      columns,
      designItemList: [],
      list: [],
      current: 0,
      doing_id: null,
      designCompanyList: [],
      status: {
        listLoading: true,
        tableLoading: true
      },
      moneys: {},
      modal: {
        record: null,
        visible: false,
        type: '1',
        company_id: this.company_id
      },
      charts: '',
      opinion: ['合同额', '支付累计'],
      opinionData: [
        { value: 100, name: '合同额', itemStyle: { color: '#00b0f0' } },
        { value: 0, name: '支付累计', itemStyle: { color: '#7030a0' } },
      ],
    }
  },
  mounted () {
    this.getData()
    this.$nextTick(function () {
      this.drawPie('main')
    })
  },
  methods: {
    getData () {
      this.getDesignItemList()
      this.getDesignCompanyList()
    },
    getDesignItemList () {
      this.status.listLoading = true
      api.getDesignItemList(this.project_id)
        .then(data => {
          this.designItemList = data.slice(0, 12)
          this.list[0] = data[12]
          this.list[1] = data[8]
          this.list[2] = data[13]
          this.getDesignItemDoing()
          this.status.listLoading = false
        })
    },
    getDesignItemDoing () {
      api.getDesignItemDoing(this.project_id)
        .then(data => {
          this.current = data[0].design_item - this.designItemList[0].design_item_id
          this.doing_id = data[0].id
        })
    },
    getDesignCompanyList () {
      this.status.tableLoading = true
      api.getDesignCompanyList(this.project_id)
        .then(data => {
          console.log(data)
          this.designCompanyList = data
          var sumC = 0
          var sumAdd = 0
          for (var i of data) {
            sumC = sumC + i.contract_price
            sumAdd = sumAdd + i.paid_total
          }
          console.log(data)
          this.opinionData[0].value = sumC - sumAdd
          this.opinionData[1].value = sumAdd
          this.status.tableLoading = false
          this.$nextTick(function () {
            this.drawPie('main')
          })
        })
    },
    handleChangeCurrent (item) {
      this.current = item.design_item_id - this.designItemList[0].design_item_id
      api.updateDesignItemDoing(this.doing_id, item.design_item_id)
        .then(data => {
          this.$notification.success({ message: '成功', description: '更换正在进行项目成功', key: 'SUCCESS' })
        })
    },
    getIconType (item) {
      let ret = ''
      if (item.design_item_id - this.designItemList[0].design_item_id < this.current) {
        ret = 'check-circle'
      } else if (item.design_item_id - this.designItemList[0].design_item_id === this.current) {
        ret = 'clock-circle'
      } else {
        ret = 'minus-circle'
      }
      return ret
    },
    handleClose () {
      this.modal.type = ''
      this.modal.record = null
      this.modal.visible = false
      this.getDesignCompanyList()
    },
    handleCreate () {
      this.modal.type = 'create'
      this.modal.record = null
      this.modal.visible = true
    },
    handleEdit (record) {
      this.modal.type = 'edit'
      this.modal.record = record
      this.modal.visible = true
      console.log(record)
    },
    handleDelete (record) {
      api.deleteDesignCompany(record.company_id)
        .then(data => {
          this.$notification.success({ message: '成功', description: '删除成功', key: 'SUCCESS' })
          this.getDesignCompanyList()
        })
    },
    getRatio (record) {
      let paid_total = parseFloat(record.paid_total)
      let settled_price = parseFloat(record.settled_price)
      if (paid_total && settled_price) {
        return `${((paid_total / settled_price) * 100).toFixed(2)} %`
      } else {
        return ''
      }
    },
    drawPie (id) {
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
  },
  computed: {
    project_id () {
      return this.$store.getters.projectSelected.id
    }
  }
}
</script>

<style scoped lang="less">
  @import '../../../../public/color';

  .item {
    margin-bottom: 16px;
  }

  .ant-timeline-item-last {
    height: 20px;
    padding: 0;
  }

  .card {
    text-align: center;

    &:hover {
      border-color: @primary-color;
      box-shadow: 0 0 1px @primary-color;
    }
  }
</style>