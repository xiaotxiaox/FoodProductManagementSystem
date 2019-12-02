<template>
  <a-modal
    :title="title"
    :visible="visible"
    :keyboard="false"
    :maskClosable="false"
    okText="保存"
    okType="primary"
    @ok="handleOk()"
    @cancel="handleCancel()">
    <a-form
      :form="form">
      <a-form-item
        label="通知单编号"
        v-bind="layout">
        <a-input
          type="Number"
          v-decorator="[
            'id',
            {
              rules:[
                { required: true, message: '请输入通知单编号' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.id : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="部位"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'part',
            {
              rules:[
                { required: true, message: '请输入部位' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.part : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="发出日期"
        :label-col="{span: 6}"
        :wrapper-col="{span: 18}">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'give_time',
                {rules: [{type: 'object',required: true, message: '请选择发出日期'}]}
              ]"></a-date-picker>
      </a-form-item>
      <a-form-item
        label="回复日期"
        :label-col="{span: 6}"
        :wrapper-col="{span: 18}">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'reply_time',
                {rules: [{type: 'object',required: false, message: '请选择回复日期'}]}
              ]"></a-date-picker>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
import api from '../../../../api/scheduleControl'
import moment from 'moment'

export default {
  name: 'ScheduleNoticeModal',
  props: {
    record: Object,
    visible: Boolean,
    type: String,
    notice_id: Number,
    id: Number
  },
  data () {
    return {
      layout: {
        'label-col': { span: 8 },
        'wrapper-col': { span: 12 }
      },
      form: this.$form.createForm(this),
      notice: {},
    }
  },
  mounted () {
    this.getData()
  },
  methods: {
    getData () {
      if (this.isEdit) {
        this.record.give_time = new moment(this.record.give_time)
        this.record.reply_time = new moment(this.record.reply_time)
        api.getScheduleNoticeMatter(this.record.id)
          .then(data => {
            this.notice = data
          })
      }
    },
    handleOk () {
      this.form.validateFields((error, data) => {
        console.log(data)
        if (!error) {
          data.give_time = data.give_time.format('YYYY-MM-DD')
          if (data.reply_time != null) {
            data.reply_time = data.reply_time.format('YYYY-MM-DD')
          }
          if (this.isEdit) {
            api.updateScheduleNotice(this.record.id, data)
              .then(data => {
                this.$notification.success({ message: '成功', description: '更新成功', key: 'SUCCESS' })
                this.$emit('close')
              })
          } else {
            api.createScheduleNotice(this.$store.getters.projectSelected.id, data)
              .then(data => {
                this.$notification.success({ message: '成功', description: '新建成功', key: 'SUCCESS' })
                this.$emit('close')
              })
          }
        }
      })
    },
    handleCancel () {
      this.$emit('close')
    }

  },
  computed: {
    isEdit () {
      return this.type === 'edit'
    },
    title () {
      return this.isEdit ? '编辑' : '新建'
    }
  }
}
</script>

<style scoped>

</style>