<template>
  <div>
    <a-card style="margin-bottom: 16px">
      <a-row class="item">
        <a-col
          :xs="{ span: 24 ,offset:0}"
          :sm="{ span: 12,offset: 12 }"
          :xl="{ span: 4 ,offset:20}">
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
        :dataSource="matterList"
        rowKey="matter_id"
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
      title: '设计单位名称',
      dataIndex: 'name',
      width: '12%',
      align: 'center'
    },
    {
      title: '项目名称',
      dataIndex: 'project_name',
      width: '12%',
      align: 'center'
    }, {
      title: '合同额',
      dataIndex: 'contract_price',
      width: '12%',
      align: 'center'
    },
    {
      title: '结算额',
      dataIndex: 'settled_price',
      width: '8%',
      align: 'center'
    },
    {
      title: '支付时间',
      dataIndex: 'paid_date',
      width: '12%',
      align: 'center'
    },
    {
      title: '本次实付',
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
  import DesignAccountDetailModal from './components/DesignAccountDetailModal'
  import api from '../../../api/implement'

  export default {
    name: 'DesignAccountDetail',
    components: {
      DesignAccountDetailModal
    },
    data () {
      return {
        columns,

        modal: {
          type: '',
          record: {},
          visible: false
        }
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      getData () {
        this.getDesignItemList()
        this.getDesignCompanyList()
      },
      getDesignItemList () {
        api.getDesignItemList(this.project_id)
          .then(data => {
            this.designItemList = data.slice(0, 12)
            this.list[0] = data[12]
            this.list[1] = data[8]
            this.list[2] = data[13]
            this.getDesignItemDoing()
          })
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
      },
      handleDelete (record) {
        api.deleteDesignCompany(record.company_id)
          .then(data => {
            this.$notification.success({ message: '成功', description: '删除成功', key: 'SUCCESS' })
            this.getDesignCompanyList()
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

<style scoped="less">

</style>