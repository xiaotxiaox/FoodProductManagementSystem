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
      :form="form"
      v-if="isEdit">
      <a-form-item
        label="办理事项"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          v-decorator="[
            'matter',
            {
              rules:[
                {required: true, message: '请输入办理事项'},
                {max:128, message:'长度在128个汉字以内'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.matter : null}
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="份数"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          v-decorator="[
            'number',
            {
              rules: [
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.number : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="办理部门"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          v-decorator="[
                'department',
                {rules: [
                   {max:128,message:'长度在128个汉字以内'}
                 ],
                 validateTrigger: 'blur',
                 initialValue: record ? record.department : null}
              ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="状态"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          v-decorator="[
                'status',
                {rules:[
                   {max:128,message:'长度在128个汉字以内'}
                 ],
                 validateTrigger: 'blur',
                 initialValue: record ? record.status : null}
              ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="联系方式"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          placeholder="13812341234"
          v-decorator="[
            'telephone',
                            {rules: [
                   {max:128,message:'长度在128个字符以内'}
                 ],
                            validateTrigger: 'blur',
                            initialValue: record ? record.telephone : null}
                            ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="是否缴费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          placeholder="是/否"
          v-decorator="[
                            'paid_status',
                            {
                            rules: [
                   {max:128,message:'长度在128个汉字以内'}
                 ],
                            validateTrigger: 'blur',
                            initialValue: record ? record.paid_status : null}
                            ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="办理时限"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          placeholder=""
          v-decorator="[
                            'deal_time',
                            {
                            rules: [
                   {max:128,message:'长度在128个汉字以内'}
                 ],
                            validateTrigger: 'blur',
                            initialValue: record ? record.deal_time : null}
                            ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="备注"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-textarea
          placeholder="请输入备注（不多于256字）"
          :rows="4"
          v-decorator="[
                'note',
                {rules: [
                   {max:256, message:'长度在256个汉字以内'}
                 ],
                 validateTrigger: 'blur',
                 initialValue: record ? record.note : null}
              ]"></a-textarea>
      </a-form-item>
    </a-form>
    <a-form
      :form="form"
      v-else>
      <a-form-item
        label="办理事项"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          v-decorator="[
                'matter',
                {rules:[
                  {required: true, message: '请输入办理事项'},
                  {max:128, message:'长度在128个汉字以内'}
                ],
                validateTrigger: 'blur'}
              ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="份数"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          v-decorator="[
                'number',
                {rules: [
                   {max:128,message:'长度在128个汉字以内'}
                 ],
                 validateTrigger: 'blur'}
              ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="办理部门"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          v-decorator="[
                'department',
                {rules: [
                   {max:128,message:'长度在128个汉字以内'}
                 ],
                 validateTrigger: 'blur'}
              ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="状态"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          v-decorator="[
                'status',
                {
                rules: [
                   {max:128,message:'长度在128个汉字以内'}
                 ],
                 validateTrigger: 'blur'}
              ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="联系方式"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          placeholder="13812341234"
          v-decorator="[
                            'telephone',
                            {
                            rules: [
                   {max:128,message:'长度在128个字符以内'}
                 ],
                            validateTrigger: 'blur'}]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="是否缴费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          placeholder="是/否"
          v-decorator="[
                            'paid_status',
                            {
                            rules: [
                   {max:128,message:'长度在128个汉字以内'}
                 ],
                            validateTrigger: 'blur'}]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="办理时限"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          placeholder=""
          v-decorator="[
                            'deal_time',
                            {
                            rules: [
                   {max:128,message:'长度在128个汉字以内'}
                 ],
                            validateTrigger: 'blur'}]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="备注"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-textarea
          placeholder="请输入备注（不多于256字）"
          :rows="4"
          v-decorator="[
                'note',
                {rules: [
                   {max:256, message:'长度在256个汉字以内'}
                 ],
                 validateTrigger: 'blur'}
              ]"></a-textarea>
      </a-form-item>
    </a-form>

  </a-modal>
</template>

<script>
  import api from '../../../api/decision'

  export default {
    name: 'DecisionDetailModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String,
      decision_id: Number
    },
    data () {
      return {
        form: this.$form.createForm(this)
      }
    },
    mounted () {

    },
    methods: {
      handleOk () {
        this.form.validateFields((error, data) => {
          if (!error) {
            data.decision = this.decision_id
            if (this.isEdit) {
              api.updateDecisionMatter(this.record.matter_id, data)
                .then(data => {
                  this.$emit('close')
                  this.$notification.success({ message: '成功', description: '修改成功', key: 'SUCCESS' })
                })
            } else {
              api.createDecisionMatter(data, this.decision_id)
                .then(data => {
                  this.$emit('close')
                  this.$notification.success({ message: '成功', description: '新建成功', key: 'SUCCESS' })
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

<style scoped lang="less">

</style>