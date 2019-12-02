<template>
  <div>
    <schedule-plan-modal
    :record="modal.record"
    :visible="modal.visible"
    :type="modal.type"
    :plan_id="modal.plan_id"
    v-if="modal.visible"
    @close="handleClose()">
  </schedule-plan-modal>
    <page-layout title="进度计划">
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
          :dataSource="planList"
          rowKey="id"
          :pagination="false"
          :loading="status.loading">
          <template slot="operation1" slot-scope="text, record, index">
            <a-button @click="$router.push({'name': 'schedulePlanDetail1', params: {id: record.id}})">查看</a-button>
          </template>
          <template slot="operation2" slot-scope="text, record, index">
            <a-button @click="$router.push({'name': 'schedulePlanDetail2', params: {id: record.id}})">查看</a-button>
          </template>
          <template slot="operation3" slot-scope="text, record, index">
            <a-button @click="handleEdit(record)">编辑</a-button>
            <a-popconfirm
              title="确认删除吗?"
              @confirm="handleDelete(record)">
              <a-button type="danger">删除</a-button>
            </a-popconfirm>
          </template>
        </a-table>
      </a-card>
    </page-layout>
  </div>


</template>

<script>

  const columns = [
    {
      title: '名称',
      dataIndex: 'name',
      width: '12%',
      align: 'center'
    },
    {
      title: '审核日期',
      dataIndex: 'time',
      width: '12%',
      align: 'center'
    },
    {
      title: '附件',
      dataIndex: 'operation1',
      scopedSlots: { customRender: 'operation1' },
      align: 'center'
    },
    {
      title: '审批',
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
   import PageLayout from '../../../components/page/PageLayout'
  import SchedulePlanModal from './components/SchedulePlanModal'
  import api from '../../../api/scheduleControl'

  export default {
    name: 'SchedulePlan',
     components: {
      SchedulePlanModal,
       PageLayout
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
        plans: {},
        modal: {
          record: null,
          visible: false,
          type: '1',
          plan_id: this.id
        },
        columns,
        planList: []
      }
    },
    mounted () {
      this.getData()
    },
    methods: {

      getData () {
        this.status.loading=true
        api.getSchedulePlanList(this.$store.getters.projectSelected.id)
          .then(data => {
            this.planList = data
            this.status.loading=false
          })
        api.getSchedulePlan(this.id)
          .then(data => {
            this.plans = data
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
        api.deleteSchedulePlan(record.id)
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