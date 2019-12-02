<template>
  <a-modal
    :title="title"
    :visible="visible"
    :keyboard="false"
    :maskClosable="false"
    okText="保存"
    okType="primary"
    @ok="handleSecurityOk()"
    @cancel="handleSecurityCancel()">
    <a-form
      :form="form">
      <a-form-item
        label="内容"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'risk_content',
            {
              rules: [
                { max:256, message: '长度在256个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.risk_content : null
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
              rules: [
                { max:16, message: '长度在16个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.part : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="风险等级"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'risk_rank',
            {
              rules: [
                { max:16, message: '长度在16个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.risk_rank : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="检查结果"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'result',
            {
              rules: [
                { max:16, message: '长度在16个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.result : null
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
  name: 'SecurityModal',
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
  methods: {
    ...mapGetters(['projectSelected']),
    handleSecurityOk () {
      this.form.validateFields((error, data) => {
        if (!error) {
          if (this.isEdit) {
            api.updateSecurity(this.record.risk_id, data)
              .then(data => {
                this.$notification.success({ message: '成功', description: '更新成功', key: 'SUCCESS' })
                this.$emit('close')
              })
          } else {
            api.createSecurity(this.project_id, data)
              .then(data => {
                this.$notification.success({ message: '成功', description: '新建成功', key: 'SUCCESS' })
                this.$emit('close')
              })
          }
        }
      })
    },
    handleSecurityCancel () {
      this.$emit('close')
    },
    validateProgressLimit (rule, value, callback) {
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