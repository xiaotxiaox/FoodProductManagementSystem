<template>
  <a-modal
    :title="title"
    :visible="visible"
    :keyboard="false"
    :maskClosable="false"
    okText="保存"
    okType="primary"
    @ok="handleTaskOk()"
    @cancel="handleTaskCancel()">
    <a-form
      :form="form">
      <a-form-item
        label="任务名称"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              rules: [
                { max:64, message: '长度在64个汉字以内' },
                 {required: true, message: '请输入任务名称'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.name : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="计划开始日期"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
      'dream_start_date',
      {rules: [{type: 'object'},{required: true, message: '请选择日期'}],
      initialValue: record ? record.dream_start_date : null}
      ]"></a-date-picker>
      </a-form-item>
      <a-form-item
        label="计划截止日期"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
      'dream_end_date',
      {rules: [{type: 'object'},{required: true, message: '请选择日期'}],
      initialValue: record ? record.dream_end_date : null}
      ]"></a-date-picker>
      </a-form-item>
      <a-form-item
        label="实际开始日期"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
      'real_start_date',
      {rules: [{type: 'object'}],
      initialValue: record ? record.real_start_date : null}
      ]"></a-date-picker>
      </a-form-item>
      <a-form-item
        label="实际截止日期"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
      'real_end_date',
      {rules: [{type: 'object'}],
      initialValue: record ? record.real_end_date : null}
      ]"></a-date-picker>
      </a-form-item>
      <a-form-item
        label="状态"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-select
          placeholder="请选择状态"
          v-decorator="[
                'state',
                {rules: [{required: true, message: '请选择状态'}],
                initialValue: record ? record.state : null}
              ]">
          <a-select-option key="1">关键</a-select-option>
          <a-select-option key="2">一般</a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../../api/implement'
  import {mapGetters} from 'vuex'
  import moment from 'moment'

  export default {
    name: 'TaskModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String,
    },
    data() {
      return {
        project_id: this.projectSelected().id,
        layout: {
          'label-col': {span: 8},
          'wrapper-col': {span: 12}
        },
        form: this.$form.createForm(this),
        matter: {}
      }
    },
    mounted() {
      this.getData()
    },
    methods: {
      ...mapGetters(['projectSelected']),
      getData() {
        if (this.isEdit) {
          if (this.record.real_start_date !== null) {
            this.record.real_start_date = new moment(this.record.real_start_date)
          }
          if (this.record.real_end_date !== null) {
            this.record.real_end_date = new moment(this.record.real_end_date)
          }
          if (this.record.dream_end_date !== null) {
            this.record.dream_end_date = new moment(this.record.dream_end_date)
          }
          if (this.record.dream_start_date !== null) {
            this.record.dream_start_date = new moment(this.record.dream_start_date)
          }
        }
      },
      handleTaskOk() {
        this.form.validateFields((error, data) => {
          if (!error) {
            if(data.real_start_date)
              data.real_start_date = data.real_start_date.format('YYYY-MM-DD')
            if(data.real_end_date)
              data.real_end_date = data.real_end_date.format('YYYY-MM-DD')
            data.dream_start_date = data.dream_start_date.format('YYYY-MM-DD')
            data.dream_end_date = data.dream_end_date.format('YYYY-MM-DD')
            console.log("1")
            console.log(data.state)
            if (data.state == "1")
              data.state = 1
            else
              data.state = 2
            if (this.isEdit) {
              api.updateTask(this.record.chart_id, data)
                .then(data => {
                  console.log("data.state")
                  console.log(data.state)
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createTask(this.project_id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '新建成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            }
          }
        })
      },
      handleTaskCancel() {
        this.$emit('close')
      },
      validateProgressLimit(rule, value, callback) {
        if (this.isEdit) {
          callback()
        } else {
          if (value < 0 || value > 100)
            callback('false')
          else
            callback()
        }
      }

    },
    computed: {
      isEdit() {
        return this.type === 'edit'
      },
      title() {
        return this.isEdit ? '编辑' : '列名管理'
      }
    }
  }
</script>

<style scoped lang="less">

</style>