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
        label="权限名"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              rules: [
               { required: true, message: '请输入权限名' },
                 { max:128,message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.name : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="权限描述"
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
        label="动作选择"
        v-bind="layout">
        <a-select
          mode="multiple"
          placeholder="请选择动作类型"
          v-decorator="[
           'actions',
           {
             rules: [
              {required: true, message: '请选择动作类型'}
             ],
             initialValue: record ? record.actions : []
           }
         ]">
          <a-select-option
            v-for="item in actionsList"
            :key="item.name"
            :name="item.name">
            {{ item.name }}
          </a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
import api from '../../../../api/setting'

export default {
  name: 'permissionsModal',
  props: {
    record: Object,
    visible: Boolean,
    type: String,
    permissions_id: Number
  },
  data () {
    return {
      layout: {
        'label-col': { span: 8 },
        'wrapper-col': { span: 12 }
      },
      form: this.$form.createForm(this),
      actionsList: [],
      action: {}
    }
  },
  mounted () {
    this.getData()
    this.record.actions = this.record.actions.map(value => {
      return value.name
    })
  },
  methods: {
    getData () {
      api.getSettingActions().then(data => {
        console.log(data)
        this.actionsList = data
      })
    },
    handleOk () {
      this.form.validateFields((error, data) => {
        if (!error) {
          var result=[]
          for(var temp of data.actions){
            for(var com of this.actionsList){
              if(temp==com.name)
                result.push(com)
            }
          }
          data.actions=result
          if (this.isEdit) {
            console.log(data)
            api.updateSettingPermissionsMatter(this.record.id, data)
              .then(data => {
                this.$notification.success({ message: '成功', description: '更新成功', key: 'SUCCESS' })
                this.$emit('close')
              })
          } else {
            api.createSettingPermissionsMatter(this.permissions_id, data)
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