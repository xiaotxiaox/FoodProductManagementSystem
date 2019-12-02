<template>
  <div>
    <invest-table-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :total_investment_id="modal.total_investment_id"
      v-if="modal.visible"
      @close="handleClose()"
    ></invest-table-modal>
    <a-card style="margin-bottom: 16px">
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
      <s-spin :spinning="status.listLoading">
        <a-table
          bordered
          :columns="columns"
          :dataSource="totalInvestList"
          rowKey="total_investment_id"
          :scroll="{x:1300}"
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
      </s-spin>
    </a-card>
  </div>
</template>

<script>
  import ARow from "ant-design-vue/es/grid/Row";
  import api from '../../../../api/implement'
    import {mapGetters} from 'vuex'
  import investTableModal from './components/investTableModal'
  const columns = [
    {
      title: '单位名称',
      dataIndex: 'company_name',
      width: '8%',
      align: 'center'
    },
    {
      title: '项目名称',
      dataIndex: 'project_name',
      width: '8%',
      align: 'center'
    },
     {
      title: '类别',
      dataIndex: 'category',
       align: 'center',
      customRender: (text, record) => {
         if (record.category === '建筑工程费')
          return '建筑工程费'
        else if (record.category === '安装工程费')
          return '安装工程费'
          else if (record.category === '设备预置费')
          return '设备预置费'
          else if (record.category === '土地使用费和其他补偿费')
          return '土地使用费和其他补偿费'
          else if (record.category === '建设管理费')
          return '建设管理费'
          else if (record.category === '可行性研究费')
          return '专项评价费'
          else if (record.category === '研究试验费')
          return '研究试验费'
          else if (record.category === '勘察设计费')
          return '场地准备费和临时设施费'
          else if (record.category === '引进技术和进口设备材料其他费')
          return '引进技术和进口设备材料其他费'
          else if (record.category === '特殊设备安全监督检验费')
          return '特殊设备安全监督检验费'
          else if (record.category === '市政公用配套设施费')
          return '联合试运转费'
          else if (record.category === '工程保险费')
          return '工程保险费'
          else if (record.category === '专利及专有技术使用费')
          return '专利及专有技术使用费'
          else if (record.category === '生产准备费')
          return '生产准备费'
          else if (record.category === '监理费')
          return '监理费'
          else if (record.category === '造价咨询')
          return '造价咨询'
          else if (record.category === '其他')
          return '其他'
          else if (record.category === '预备费')
          return '预备费'
          else if (record.category === '增值税')
          return '增值税'
          else if (record.category === '资金筹措费')
          return '资金筹措费'
        else
          return '流动资金'
      },
      width: '10%'
    },
     {
      title: '开始时间',
      dataIndex: 'start_time',
      width: '8%',
      align: 'center'
    },
     {
      title: '截止时间',
      dataIndex: 'end_time',
      width: '8%',
      align: 'center'
    },
     {
      title: '实际完成额度',
      dataIndex: 'completion_quota',
      width: '8%',
      align: 'center'
    },
     {
      title: '说明',
      dataIndex: 'explain',
      width: '8%',
      align: 'center'
    },
    {
      title: '编辑',
      dataIndex: 'operation',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ]
  export default {
    name: 'invest_table',
    components:{
      investTableModal,
      ARow
    },
     props: {
      id: Number
    },
     data (){
      return{
        columns,
         status: {
          listLoading: true,
          tableLoading: true
        },
        modal: {
          record: null,
          visible: false,
          type: '1',
          total_investment_id: this.id
        },
        form: this.$form.createForm(this),
        totalInvestList:[],
         layout: {
          'label-col': {span: 6},
          'wrapper-col': {span: 18}
        },

      }
     },
     mounted() {
      this.getData()
    },
    methods:{
      getData() {
        this.status.loading = true
        api.getTotalInvestList(this.$store.getters.projectSelected.id)
          .then(data => {
            console.log(data)
            this.totalInvestList = data
            this.status.listLoading = false
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
        api.deleteTotalInvest(record.total_investment_id)
          .then(data => {
            console.log(data)
            this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
          })
      },
      parseFloat(f) {
        return parseFloat(f)
      },
    }
  }
</script>

<style scoped>

</style>