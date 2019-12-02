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
        label="角色名"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              rules: [
               { required: true, message: '请输入角色名' },
                 { max:128,message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.name : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="角色描述"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'description',
            {
              rules: [
              { required: true, message: '请输入角色描述' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.description : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="权限选择"
        v-bind="layout">
        <a-select
          mode="multiple"
          placeholder="请选择权限类型"
          v-decorator="[
           'permissions',
           {
             rules: [
              {required: true, message: '请选择权限类型'}
             ],
             initialValue: record ? record.permissions : null
           }
         ]">
          <a-select-option
            v-for="item in permissionsList"
            :key="item.name"
            :name="item.name">
            {{ item.description }}
          </a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
import api from '../../../../api/setting'

export default {
  name: 'rolesModal',
  props: {
    record: Object,
    visible: Boolean,
    type: String,
    roles_id: Number
  },
  data () {
    return {
      layout: {
        'label-col': { span: 8 },
        'wrapper-col': { span: 12 }
      },
      form: this.$form.createForm(this),
      permissionsList: []
    }
  },
  mounted () {
    this.getData()
    this.record.permissions = this.record.permissions.map(value => {
      return value.name
    })
  },
  methods: {
    getData () {
      api.getSettingPermissions().then(data => {
        console.log(data)
        this.permissionsList = data
      })
    },
    handleOk () {
      this.form.validateFields((error, data) => {
        if (!error) {
         var result = []
          for (var temp of data.permissions) {
            for (var com of this.permissionsList) {
              if (temp == com.name)
                result.push(com)
            }
          }
          data.permissions = result
          if (this.isEdit) {
            api.updateSettingRolesMatter(this.record.id, data)
              .then(data => {
                this.$notification.success({ message: '成功', description: '更新成功', key: 'SUCCESS' })
                this.$emit('close')
              })
          } else {
            api.createSettingRolesMatter(data)
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