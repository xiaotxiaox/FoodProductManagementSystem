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
        label="编号"
        v-bind="layout">
        <a-input
          type="number"
          disabled
          v-decorator="[
            'id',
            {
              rules:[
                { required: true, message: '请输入用户名' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.id : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="用户名"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              rules:[
                { required: true, message: '请输入用户名' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.name : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="新密码"
        v-bind="layout">
        <a-input
          type="password"
          v-decorator="[
            'password',
            {
              rules: [
                { required: true, message: '请输入新密码' },
                { max:128, message: '长度在128个汉字以内' },
                {validator:validatePassword , message: '密码格式不正确'},
              ],
              //validateTrigger: 'blur',
              //initialValue: record ? record.password : null
            }
          ]">
        </a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
   import api from '../../../api/setting'
   import AFormItem from 'ant-design-vue/es/form/FormItem'
  export default {
    name: 'updatePswModal',
    components: { AFormItem },
    props: {
      record: Object,
      visible: Boolean,
      type: String,
      id: Number
    },
    data () {
      return {
        layout: {
          'label-col': { span: 8 },
          'wrapper-col': { span: 12 }
        },
        form: this.$form.createForm(this),
        users: {},
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      getData () {
        if (this.isUpdatePsw) {
          api.getSettingUsersMatter(this.record.id)
            .then(data => {
              this.users = data
            })
        }
      },
      handleOk () {
        this.form.validateFields((error, data) => {
          if (!error) {
            api.udPswSettingUsersMatter(this.record.id, data)
              .then(data => {
                  console.log(111111111111111111111111111111111)
                  console.log(data)
                this.$notification.success({ message: '成功', description: '更新成功', key: 'SUCCESS' })
                this.$emit('close')
              })
          }
        })
      },
      handleCancel () {
        this.$emit('close')
      },
      validatePassword (rule, value, callback) {
        if (/[\u4e00-\u9fa5]/.test(value)) {
          callback('id can not contain chinese')
        } else {
          callback()
        }
      },
      validateSortPsw (rule, value, callback) {
        if (/[\u4e00-\u9fa5]/.test(value)) {
          callback('id can not contain chinese')
        } else {
          callback()
        }
      },



    },
    computed: {
      isUpdatePsw () {
        return this.type === 'update'
      },
      title(){
        return '编辑'
      }
    }
  }
</script>

<style scoped>

</style>