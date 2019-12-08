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
        v-if="isEdit"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="number"
          disabled
          v-decorator="[
            'id',
            {
              rules:[
                { required: true, message: '请输入客户编号' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.id: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="用户名"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              rules:[
                { required: true, message: '请输入用户名' },
                { max:16, message: '长度在16个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.name: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="角色"
        v-bind="layout">
        <a-select
          placeholder="请选择角色类型"
          v-decorator="[
                'roleId',
                {rules:[{required: true, message: '请选择角色类型'}],
                initialValue: record ? record.role.id : null}
              ]">
          <a-select-option
            v-for="item in typeList"
            :key="item.id"
            :value="item.id">
            {{ item.description }}
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
    </a-form>
  </a-modal>
</template>

<script>
    import api from '../../../api/setting'

    export default {
        name: 'UsersModal',
        props: {
            record: Object,
            visible: Boolean,
            type: String,
        },
        data() {
            return {
                layout: {
                    'label-col': {span: 8},
                    'wrapper-col': {span: 12}
                },
                form: this.$form.createForm(this),
                matter: {},
                typeList: [],
            }
        },
        mounted() {
            this.getData();
        },
        methods: {
            getData(){
                api.getSettingRoles()
                    .then(data => {
                        this.typeList = data
                        console.log(this.typeList)
                    })
            },
            handleOk() {
                this.form.validateFields((error, data) => {
                    if (!error) {
                        if (this.isEdit) {
                            api.updateSettingUsersMatter(this.record.id, data)
                                .then(data => {
                                    console.log(data)
                                    this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                                    this.$emit('close')
                                })
                        } else {
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

<style scoped lang="less">

</style>