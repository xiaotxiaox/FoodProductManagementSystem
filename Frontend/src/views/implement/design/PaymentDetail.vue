<template>
    <div>
      <pay-detail-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :detail_id="modal.detail_id"
      v-if="modal.visible"
      @close="handleClose()">
      </pay-detail-modal>
        <a-card style="margin-bottom: 16px">
      <a-row class="item">
        <a-col
          :xs="{span: 24}"
          :sm="{span: 12}"
          :xl="{span: 20}">
          <span style="font-size: 20px; margin-left: 4px">{{details.title}}</span>
        </a-col>
        <a-col
          :xs="{ span: 24 }"
          :sm="{ span: 12, offset: 12 }"
          :xl="{ span: 8, offset: 16 }">
          <a-button
            type="primary"
            icon="plus"
            style="float: right; "
            @click="handleCreate()">
            新建
          </a-button>
           <a-button
            style="float: right; margin-right: 5px;"
            @click="$router.go(-1)">
            返回
          </a-button>
        </a-col>
      </a-row>
      <a-table
        bordered
        :columns="columns"
        :dataSource="detailList"
        rowKey="id"
        :pagination="false">
        <template slot="getName" slot-scope="text, record, index">
          {{getName(record)}}
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
</template>

<script>
  const columns = [
    {
      title: '支付时间',
      dataIndex: 'time',
      width: '35%',
      align: 'center'
    },
    {
      title: '本次实付',
      dataIndex: 'money',
      width: '35%',
      align: 'center'
    },
    {
      title: '编辑',
      dataIndex: 'operation',
      scopedSlots: { customRender: 'operation' },
      align: 'center'
    }
  ]
   import PayDetailModal from './components/payDetailModal'
  import api from '../../../api/implement'
  export default {
    name: 'PaymentDetail',
    components: {
      PayDetailModal
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
        status:{
          loading:true
        },
        form: this.$form.createForm(this),
        details:{},
        modal: {
          record: null,
          visible: false,
          type: '1',
          detail_id: this.id
        },
        columns,
        detailList: []
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      getData () {
        this.status.loading=true
        this.modal.detail_id= this.id
        api.getDesignPayDetailMatter(this.id)
          .then(data => {
            console.log(data)
            this.detailList = data.child
            this.status.loading=false
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
        api.deleteDesignPayDetail(record.id)
          .then(data => {
            this.$notification.success({ message: '成功', description: '删除成功', key: 'SUCCESS' })
            this.getData()
          })
      },
      getName(record) {
       let name=record.name
        if (name) {
          return `name`
        } else {
          return ''
        }
      },
      parseFloat (f) {
        return parseFloat(f)
      }
    }
  }
</script>

<style scoped>

</style>