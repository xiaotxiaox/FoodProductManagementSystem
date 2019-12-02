<template>
    <div>
      <account-detail-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :company_id="modal.company_id"
      v-if="modal.visible"
      @close="handleClose()">
    </account-detail-modal>
      <a-card style="margin-bottom: 16px">
        <a-row class="item">
          <a-col
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
        <a-table
          bordered
          :columns="columns"
          :dataSource="accountList"
          rowKey="company_id"
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
   const columns = [
    {
      title: '项目单位名称',
      dataIndex: 'name',
      width: '12%',
      align: 'center'
    },
    {
      title: '项目名称',
      dataIndex: 'project_name',
      width: '10%',
      align: 'center'
    }, {
      title: '合同额',
      dataIndex: 'contract_price',
      width: '10%',
      align: 'center'
    },
    {
      title: '结算额',
      dataIndex: 'settled_price',
      width: '10%',
      align: 'center'
    },
    {
      title: '支付时间',
      dataIndex: 'paid_date',
      width: '12%',
      align: 'center'
    },
    {
      title: '本次支付',
      dataIndex: 'actual_pay',
      width: '12%',
      align: 'center'
    },
    {
      title: '编辑',
      dataIndex: 'operation',
      scopedSlots: { customRender: 'operation' },
      align: 'center'
    }
  ]
   import AccountDetailModal from './components/invest_processModal'
   import api from '../../../../api/implement'
  export default {
    name: 'AccountDetail',
    components: {
      AccountDetailModal
    },
    props: {
      id: Number
    },
    data () {
      return {
        layout: {
          'label-col': { span: 6 },
          'wrapper-col': { span: 18 }
        },
        form: this.$form.createForm(this),
        Project: {},
        modal: {
          record: null,
          visible: false,
          type: '1',
          company_id: this.id
        },
        columns,
        accountList: []
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      getData () {
        api.getAccountDetailMatterList(this.id)
          .then(data => {
            this.accountList = data
          })
        api.getAccountDetail(this.id)
          .then(data => {
            this.Project = data
          })
      },
      handleSubmit () {
        this.form.validateFields((error, data) => {
          if (!error) {
            data.name = this.Project.name
            data.project_name=this.Project.project_name
            api.updateAccountDetail(this.id, data)
              .then(data => {
                this.$notification.success({ message: '成功', description: '修改成功', key: 'SUCCESS' })
              })
          }
        })
      },
      handleClose () {
        this.modal.type = ''
        this.modal.record = null
        this.modal.visible = false
        this.getData()
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
      },
      handleDelete (record) {
        api.deleteAccountDetailMatter(record.company_id)
          .then(data => {
            this.$notification.success({ message: '成功', description: '删除成功', key: 'SUCCESS' })
            this.getData()
          })
      },
      parseFloat (f) {
        return parseFloat(f)
      }
    }
  }
</script>

<style scoped>

</style>