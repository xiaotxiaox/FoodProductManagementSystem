<template>
  <page-layout title="项目决策详情">
    <div>
      <DecisionDetail-modal
        :record="modal.record"
        :visible="modal.visible"
        :type="modal.type"
        :decision_id="modal.decision_id"
        v-if="modal.visible"
        @close="handleClose()">
      </DecisionDetail-modal>
      <a-card style="margin-bottom: 16px">
        <a-row class="item">
          <a-col
            :xs="{ span: 24 }"
            :sm="{ span: 12 }"
            :xl="{ span: 20 }">
            <span style="font-size: 20px">{{decision.title}}</span>
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
          style="text-align: center"
          bordered
          :loading="status.loading"
          :columns="columns"
          :dataSource="data"
          rowKey="matter_id"
          :pagination="false">
          <template slot="download" slot-scope="text, record, index">
            <a-button @click="$router.push({'name': 'DecisionFile', params: {id: record.matter_id}})">
              文件上传
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
              :label-col="{span: 6}"
              :wrapper-col="{span: 18}">
              <a-input
                type="number"
                autocomplete="true"
                addonAfter="元"
                v-decorator="[
              'estimate',
              {initialValue: decision.estimate || null}]
              ">
              </a-input>
            </a-form-item>
            <a-form-item
              label="实际缴费"
              :label-col="{span: 6}"
              :wrapper-col="{span: 18}">
              <a-input
                type="number"
                autocomplete="true"
                addonAfter="元"
                v-decorator="[
              'actual',
              {initialValue: decision.actual || null}]
              ">
              </a-input>
            </a-form-item>
            <a-form-item
              label="目前状态"
              :label-col="{span: 6}"
              :wrapper-col="{span: 18}">
              <a-radio-group
                name="radioGroup"
                v-decorator="[
                  'status',
                  {initialValue: decision.status || 'WILLDO'}
                ]">
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
                提交
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
  </page-layout>
</template>

<script>
  import DecisionDetailModal from './components/DecisionDetailModal'
  import PageLayout from '../../components/page/PageLayout'
  import api from '../../api/decision'

  const columns = [
    {
      title: '办理事项',
      dataIndex: 'matter',
      width: '15%',
      align: 'center'
    },
    {
      title: '份数',
      dataIndex: 'number',
      width: '9%',
      align: 'center'
    }, {
      title: '办理部门',
      dataIndex: 'department',
      width: '8%',
      align: 'center'
    },
    {
      title: '状态',
      dataIndex: 'status',
      width: '10%',
      align: 'center'
    },
    {
      title: '联系电话',
      dataIndex: 'telephone',
      width: '17%',
      align: 'center'
    },
    {
      title: '是否缴费',
      dataIndex: 'paid_status',
      width: '15%',
      align: 'center'
    },
    {
      title: '办理时限',
      dataIndex: 'deal_time',
      width: '15%',
      align: 'center'
    },
    {
      title: '备注',
      dataIndex: 'note',
      width: '13%',
      align: 'center'
    },
    {
      title: '下载',
      dataIndex: 'download',
      align: 'center',
      scopedSlots: { customRender: 'download' }
    },
    {
      title: '编辑',
      dataIndex: 'operation',
      align: 'center',
      scopedSlots: { customRender: 'operation' }
    }
  ]
  export default {
    name: 'DecisionDetail',
    components: {
      DecisionDetailModal,
      PageLayout
    },
    props: {
      id: Number
    },
    data () {
      return {
        decision: {},
        form: this.$form.createForm(this),
        modal: {
          record: null,
          visible: false,
          type: '1'
        },
        status: {
          loading: true
        },
        columns,
        data: []
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      getData () {
        this.status.loading = true
        api.getDecisionMatterList(this.id)
          .then(data => {
            this.data = data
            this.status.loading = false
          })
        api.getDecision(this.id)
          .then(data => {
            this.decision = data
          })
      },
      handleSubmit () {
        this.form.validateFields((error, data) => {
          if (!error) {
            data.title=this.decision.title
            api.updateDecision(this.id, data)
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
        this.modal.decision_id = this.id
      },
      handleEdit (record) {
        this.modal.type = 'edit'
        this.modal.record = record
        this.modal.visible = true
        this.modal.decision_id = this.id
      },
      handleDelete (record) {
        api.deleteDecisionMatter(record.matter_id)
          .then(data => {
            this.$notification.success({ message: '成功', description: '删除成功', key: 'SUCCESS' })
            this.getData()
          })
      }
    }
  }
</script>

<style scoped lang="less">
  .item {
    margin: 10px 0;
  }

</style>
