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
        prop="matter"
        label="办理事项"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          autocomplete="true"
          v-decorator="[
                'matter',
                {rules:[
                  {required: true, message: '请输入办理事项'},
                  {max:128, message:'长度在128个汉字以内'}
                ],
                validateTrigger: 'blur',
                initialValue: decision ? decision.matter : null}
              ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="份数"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          autocomplete="true"
          placeholder="XXX路XXX号"
          v-decorator="[
                'number',
                {rules: [
                   {required: true, message: '请输入负责人姓名'},
                   {max:8,message:'长度在8个汉字以内'}
                 ],
                 validateTrigger: 'blur',
                 initialValue: decision ? decision.number : null}
              ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="办理部门"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          autocomplete="true"
          v-decorator="[
                'department',
                {rules: [
                   {max:8,message:'长度在8个汉字以内'}
                 ],
                 validateTrigger: 'blur',
                 initialValue: decision ? decision.department : null}
              ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="状态"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          autocomplete="true"
          placeholder="123456@163.com"
          v-decorator="[
                'status',
                {
                 validateTrigger: 'blur',
                 initialValue: decision ? decision.status : null}
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
                            validateTrigger: 'blur',
                            initialValue: decision ? decision.telephone : null}
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
                            validateTrigger: 'blur',
                            initialValue: decision ? decision.paid_status : null}
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
                            'dealTime',
                            {
                            validateTrigger: 'blur',
                            initialValue: decision ? decision.dealTime : null}
                            ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="备注"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-textarea
          placeholder="请输入备注（不多于120字）"
          :rows="4"
          v-decorator="[
                'note',
                {rules: [
                   {max:120, message:'长度在120个汉字以内'}
                 ],
                 validateTrigger: 'blur',
                 initialValue: decision ? decision.note : null}
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
          autocomplete="true"
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
          autocomplete="true"
          placeholder=""
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
          autocomplete="true"
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
                            {rules: [
                                    {pattern:/^[1][3,4,5,7,8][0-9]{9}$/, message: '联系电话格式错误'}
                                    ],
                            validateTrigger: 'blur'}
                            ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="是否缴费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          v-decorator="[
                            'paid_status',
                            {
                            rules: [
                   {max:128,message:'长度在128个汉字以内'}
                 ],
                            validateTrigger: 'blur'}
                            ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="办理时限"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          type="text"
          v-decorator="[
                            'deal_time',
                            {
                            rules: [
                   {max:128,message:'长度在128个汉字以内'}
                 ],
                            validateTrigger: 'blur'}
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
      type: String
    },
    data() {
      return {
        form: this.$form.createForm(this),

      }
    },
    mounted() {
    },
    methods: {

      handleOk() {
        this.$emit('close')
      },
      handleCancel() {
        this.$emit('close')
      },
      handleSubmit(e) {
        e.preventDefault()
        this.form.validateFields((error, data) => {
          if (!error) {
            console.log(data)
          }
        })
      },
    },
    computed: {
      isEdit() {
        return this.type === 'edit'
      },
      title() {
        return this.isEdit ? '编辑' : '新建'
      }
    }
  }
</script>

<style scoped lang="less">

