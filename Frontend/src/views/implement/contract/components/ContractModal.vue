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
        label="乙方"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'second_party',
            {
              rules:[
                { required: true, message: '请输入乙方名称' },
                { max:16, message: '长度在16个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.second_party: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="项目名称"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'entry_name',
            {
              rules: [
              { required: true, message: '请输入项目名称' },
                { max:64, message: '长度在64个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.entry_name : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="付款方式"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'payment_method',
            {
              rules: [
              { required: true, message: '请输入付款方式' },
                { max:16, message: '长度在16个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.payment_method : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="价款调整"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'price_adjustment',
            {
              rules: [
                { max:32, message: '长度在32个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.price_adjustment : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="风险条件"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'risk_conditions',
            {
              rules: [
                { max:64, message: '长度在64个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.risk_conditions : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="履约状况"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'performance_status',
            {
              rules: [
              { required: true, message: '请输入履约状况' },
                { max:32, message: '长度在32个字符以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.performance_status : null
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
    name: 'ContractModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String,
    },
    data () {
      return {
        project_id:this.projectSelected().id,
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
      handleOk () {
        this.form.validateFields((error, data) => {
          if (!error) {
            if (this.isEdit) {
              api.updateContract(this.record.id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createContract(this.project_id,data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '新建成功', key: 'SUCCESS'})
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

<style scoped lang="less">

</style>