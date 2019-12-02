<template>
  <div>
    <else-detail-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :decision_id="modal.decision_id"
      :else_detail_id="modal.else_detail_id"
      v-if="modal.visible"
      @close="handleClose()">
    </else-detail-modal>
    <a-card style="margin-bottom: 16px">
      <a-row class="item">
        <a-col
          :xs="{span: 24}"
          :sm="{span: 12}"
          :xl="{span: 20}">
          <span style="font-size: 20px; margin-left: 4px">{{application.title}}</span>
        </a-col>
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
        :dataSource="matterList"
        rowKey="matter_id"
        :pagination="false"
        :loading="status.loading">
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
    <a-card>
      <a-col
        :xs="{span: 24}"
        :sm="{span: 20}"
        :xl="{span: 16}">
        <a-form
          :form="form">
          <a-form-item
            label="预计缴费"
            v-bind="layout">
            <a-input
              type="number"
              addonAfter="元"
              v-decorator="[
              'estimate',
              {initialValue: application.estimate ? parseFloat(application.estimate) : null}]
              ">
            </a-input>
          </a-form-item>
          <a-form-item
            label="实际缴费"
            v-bind="layout">
            <a-input
              type="number"
              addonAfter="元"
              v-decorator="[
              'actual',
              {initialValue: application.actual ? parseFloat(application.actual) : null}]
              ">
            </a-input>
          </a-form-item>
          <a-form-item
            label="目前状态"
            v-bind="layout">
            <a-radio-group
              v-decorator="[
              'status',
              {initialValue: application.status}]">
              <a-radio value="DONE">完成</a-radio>
              <a-radio value="DOING">正在办理</a-radio>
              <a-radio value="CANNOT">无法办理</a-radio>
              <a-radio value="WILLDO">尚未办理</a-radio>
              <a-radio value="NEEDNOT">不用办理</a-radio>
            </a-radio-group>
          </a-form-item>
          <a-form-item>
            <a-button
              style="float: right"
              type="primary"
              @click="handleSubmit()">
              保存
            </a-button>
            <a-button
              style="float: right; margin-right: 10px"
              @click="$router.go(-1)">
              返回
            </a-button>
          </a-form-item>
        </a-form>
      </a-col>
    </a-card>
  </div>
</template>

<script>

  const columns = [
    {
      title: '办理事项',
      dataIndex: 'matter',
      width: '12%',
      align: 'center'
    },
    {
      title: '份数',
      dataIndex: 'number',
      width: '8%',
      align: 'center'
    }, {
      title: '办理部门',
      dataIndex: 'department',
      width: '12%',
      align: 'center'
    },
    {
      title: '状态',
      dataIndex: 'status',
      width: '8%',
      align: 'center'
    },
    {
      title: '联系电话',
      dataIndex: 'telephone',
      width: '12%',
      align: 'center'
    },
    {
      title: '是否缴费',
      dataIndex: 'paid_status',
      width: '12%',
      align: 'center'
    },
    {
      title: '办理时限',
      dataIndex: 'deal_time',
      width: '12%',
      align: 'center'
    },
    {
      title: '备注',
      dataIndex: 'note',
      width: '12%',
      align: 'center'
    },
    {
      title: '下载',
      dataIndex: 'download',
      width: '13%',
      align: 'center'
    },
    {
      title: '编辑',
      dataIndex: 'operation',
      scopedSlots: { customRender: 'operation' },
      align: 'center'
    }
  ]
  import ElseDetailModal from './components/ElseDetailModal'
  import api from '../../../api/completion'

  export default {
    name: 'ElseDetail',
    components: {
      ElseDetailModal
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
        application: {},
        modal: {
          record: null,
          visible: false,
          type: '1',
          else_detail_id: this.id
        },
        columns,
        matterList: []
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      getData () {
        this.status.loading=true
        api.getElseDetailMatterList(this.id)
          .then(data => {
            this.matterList = data
            this.status.loading=false
          })
        api.getElseDetail(this.id)
          .then(data => {
            this.application = data
          })
      },
      handleSubmit () {
        this.form.validateFields((error, data) => {
          if (!error) {
            data.title = this.application.title
            api.updateElseDetail(this.id, data)
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
        console.log(this.modal.else_detail_id)
      },
      handleEdit (record) {
        this.modal.type = 'edit'
        this.modal.record = record
        this.modal.visible = true
      },
      handleDelete (record) {
        api.deleteElseDetailMatter(record.matter_id)
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

<style scoped lang="less">
  .item {
    margin: 10px 0;
  }

</style>