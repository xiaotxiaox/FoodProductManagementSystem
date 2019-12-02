<template>
  <a-modal
    :title="title"
    :visible="visible"
    :keyboard="false"
    :maskClosable="false"
    okText="保存"
    okType="primary"
    @ok="handleGanttOk()"
    @cancel="handleGanttCancel()">
    <a-form
      :form="form">
      <a-form-item
      label="起始日期"
      :label-col="{span: 8}"
      :wrapper-col="{span: 12}"
      v-bind="layout">
      <a-date-picker
      style="width:100%"
      v-decorator="[
      'start_date',
      {rules: [{type: 'object'}],
      initialValue: record ? record.start_date : null}
      ]"></a-date-picker>
      </a-form-item>
      <a-form-item
      label="终止日期"
      :label-col="{span: 8}"
      :wrapper-col="{span: 12}"
      v-bind="layout">
      <a-date-picker
      style="width:100%"
      v-decorator="[
      'end_date',
      {rules: [{type: 'object'}],
      initialValue: record ? record.end_date : null}
      ]"></a-date-picker>
      </a-form-item>
      <a-form-item
        label="间隔"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}">
         <a-input
          type="number"
          addonAfter="天"
          v-decorator="[
            'interval',
            {
              rules: [
              {required: true, message: '请输入间隔'},
              {validator: validateProgressLimit, message: '进度范围必须为0-366'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.interval : null
            }
          ]">
        </a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
import api from '../../../../api/implement'
import { mapGetters } from 'vuex'

export default {
  name: 'GanttModal',
  props: {
    record: Object,
    visible: Boolean,
    type: String,
  },
  data () {
    return {
      project_id: this.projectSelected().id,
      layout: {
        'label-col': { span: 8 },
        'wrapper-col': { span: 12 }
      },
      form: this.$form.createForm(this),
      matter: {}
    }
  },
  mounted () {
  },
  methods: {
    ...mapGetters(['projectSelected']),
    handleGanttOk () {
      console.log(this.record.time_id)
      this.form.validateFields((error, data) => {
        if (!error) {
          data.start_date = data.start_date.format('YYYY-MM-DD')
          data.end_date = data.end_date.format('YYYY-MM-DD')
          api.updateInitialChart(this.record.time_id, data)
            .then(data => {
              this.$notification.success({ message: '成功', description: '更新成功', key: 'SUCCESS' })
              this.$emit('close')
            })
        }
      })
    },
    handleGanttCancel () {
      this.$emit('close')
    },
    validateProgressLimit (rule, value, callback) {
      if (this.isEdit) {
        callback()
      } else {
        if (value < 0 || value > 366)
          callback('false')
        else
          callback()
      }
    }
  },
  computed: {
    isEdit () {
      return this.type === 'edit'
    },
    title () {
      return this.isEdit ? '编辑' : '列名管理'
    }
  }
}
</script>

<style scoped lang="less">

</style>