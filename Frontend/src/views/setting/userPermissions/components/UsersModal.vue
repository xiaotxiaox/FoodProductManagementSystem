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
        label="用户名"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'username',
            {
              rules:[
                { required: true, message: '请输入用户名' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.username : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="角色选择"
        v-bind="layout">
        <a-select
          placeholder="请选择角色类型"
          v-decorator="[
           'role',
           {
             rules: [
              {required: true, message: '请选择角色类型'}
             ],
             initialValue: record ? record.role.name : null
           }
         ]">
          <a-select-option
            v-for="item in rolesList"
            :key="item.name"
            :name="item.name">
            {{ item.name }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="邮箱"
        v-bind="layout">
        <a-input
          type="email"
          autocomplete="true"
          placeholder="123456@163.com"
          v-decorator="[
                'email',
                {rules: [
                { required: true, message: '请输入邮箱地址' },
                {
              type: 'email', message: '请输入正确邮箱地址',
            }
                 ],
                 validateTrigger: 'blur',
                 initialValue: record ? record.email : null}
              ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="姓名"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
          'first_name',
          {
            rules: [
            { required: true, message: '请输入姓名' },
              { max:128, message: '长度在6个汉字以内' }
            ],
            validateTrigger: 'blur',
            initialValue: record ? record.first_name : null
          }
        ]">
        </a-input>
      </a-form-item>
    </a-form>
    <a-form
      :form="form"
     v-else>
      <a-form-item
        label="用户名"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'username',
            {
              rules:[
                { required: true, message: '请输入用户名' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              // initialValue: record ? record.username : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="角色选择"
        v-bind="layout">
        <a-select
          placeholder="请选择角色类型"
          v-decorator="[
           'role',
           {
             rules: [
              {required: true, message: '请选择角色类型'}
             ],
             initialValue: record ? record.role.name : null
           }
         ]">
          <a-select-option
            v-for="item in rolesList"
            :key="item.name"
            :name="item.name">
            {{ item.name }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="邮箱"
        v-bind="layout">
        <a-input
          type="email"
          autocomplete="true"
          placeholder="123456@163.com"
          v-decorator="[
                'email',
                {rules: [
                { required: true, message: '请输入邮箱地址' },
                {
              type: 'email', message: '请输入正确邮箱地址',
            }
                 ],
                 validateTrigger: 'blur',
                 initialValue: record ? record.email : null}
              ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="姓名"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
          'first_name',
          {
            rules: [
            { required: true, message: '请输入姓名' },
              { max:128, message: '长度在6个汉字以内' }
            ],
            validateTrigger: 'blur',
            initialValue: record ? record.first_name : null
          }
        ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="密码"
        v-bind="layout">
        <a-input
          type="password"
          autocomplete="true"
          placeholder="请输入6-16位长度的密码"
          v-decorator="[
            'password',
            {rules: [
              {required: true, message: '请输入密码'},
              {pattern:/[1-9a-zA-Z]{6,16}/, message: '密码格式错误'}
            ],
              validateTrigger: 'blur',
              initialValue: record ? record.password : null}
           ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="密码确认"
        v-bind="layout">
        <a-input
          type="password"
          placeholder=""
          v-decorator="[
            'retry_password',
            {
              rules: [
                { max:128, message: '请再次输入密码' },
                {required: true, message: '请再次确认密码'},
                {validator:validateSortPsw , message: '密码再次输入不正确'},
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.retry_password : null
            }
          ]">
        </a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../../api/setting'
  import AFormItem from 'ant-design-vue/es/form/FormItem'
  import {mapGetters} from 'vuex'

  export default {
    name: 'userModal',
    components: {AFormItem},
    props: {
      record: Object,
      visible: Boolean,
      type: String,
      users_id: Number
    },
    data() {
      return {
        layout: {
          'label-col': {span: 8},
          'wrapper-col': {span: 12}
        },
        form: this.$form.createForm(this),
        users: {},
        rolesList: []
      }
    },
    mounted() {
      this.getData()
    },
    methods: {
      getData() {
        api.getSettingRoles().then(data => {
          console.log(data)
          this.rolesList = data
        })
        if (this.isEdit) {
          api.getSettingUsersMatter(this.record.id)
            .then(data => {
              this.users = data
            })
        }
      },
      handleOk() {
        this.form.validateFields((error, data) => {
          if (!error) {
             var result = []
            console.log(data.role)
            for (var temp of this.rolesList) {
              if (temp.name == data.role) {
                console.log(temp.name)
                result.push(temp)
              }
            }
            console.log(result[0])
            data.role = result[0]
            if (this.isEdit) {

              api.updateSettingUsersMatter(this.record.id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
               // var te=null
               //    for (var temp of this.rolesList) {
               //      if (temp.name == data.role.name) {
               //        console.log(temp)
               //        console.log("tempid")
               //        console.log(temp.id)
               //        te=temp.id
               //      }
               //    }
               //    data.role=te
              api.createSettingUsersMatter(data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '新建成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            }
          }
        })
      },
      handleCancel() {
        this.$emit('close')
      },
      validatePassword(rule, value, callback) {
        if (/[\u4e00-\u9fa5]/.test(value)) {
          callback('id can not contain chinese')
        } else {
          callback()
        }
      },
      validateSortPsw(rule, value, callback) {
        if (/[\u4e00-\u9fa5]/.test(value)) {
          callback('id can not contain chinese')
        } else {
          callback()
        }
      }


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

<style scoped>

</style>