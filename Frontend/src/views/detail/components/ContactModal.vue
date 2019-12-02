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
        label="单位"
        v-bind="layout">
        <a-input
          type="text"
          autocomplete="true"
          v-decorator="[
            'company',
                {rules:[
                  {required: true, message: '请输入单位名称'},
                  {max:32, message:'长度在32个汉字以内'}
                ],
                validateTrigger: 'blur',
                initialValue: record ? record.company : null}
              ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="单位类型"
        v-bind="layout">
        <a-select
          placeholder="请选择单位类型"
          v-decorator="[
           'company_type',
           {
             rules: [
              {required: true, message: '请选择单位类型'}
             ],
             initialValue: record ? record.company_type.value : null
           }
         ]">
          <a-select-option
            v-for="item in typeList"
            :key="item.value"
            :value="item.value">
            {{ item.label }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="姓名"
        v-bind="layout">
        <a-input
          type="text"
          autocomplete="true"
          placeholder="请输入负责人姓名"
          v-decorator="[
                'name',
                {rules: [
                   {required: true, message: '请输入负责人姓名'},
                   {max:8,message:'长度在8个汉字以内'}
                 ],
                 validateTrigger: 'blur',
                 initialValue: record ? record.name : null}
              ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="岗位"
        v-bind="layout">
        <a-input
          type="text"
          autocomplete="true"
          v-decorator="[
                'job',
                {rules: [
                   {max:8,message:'长度在8个汉字以内'}
                 ],
                 validateTrigger: 'blur',
                 initialValue: record ? record.job : null}
              ]">
        </a-input>
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
        label="联系电话"
        v-bind="layout">
        <a-input
          type="phone"
          autocomplete="true"
          placeholder="13812341234"
          v-decorator="[
                            'telephone',
                            {rules: [
                                    {required: true, message: '请输入联系电话'},
                                    {pattern:/^[1][3,4,5,7,8][0-9]{9}$/, message: '联系电话格式错误'}
                                    ],
                            validateTrigger: 'blur',
                            initialValue: record ? record.telephone : null}
                            ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="备注"
        v-bind="layout">
        <a-textarea
          placeholder="请输入备注（不多于120字）"
          :rows="4"
          v-decorator="[
                'note',
                {rules: [
                   {max:120, message:'长度在120个汉字以内'}
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
        prop="company"
        label="单位"
        v-bind="layout">
        <a-input
          type="text"
          autocomplete="true"
          v-decorator="[
                'company',
                {rules:[
                  {required: true, message: '请输入单位名称'},
                  {max:32, message:'长度在32个汉字以内'}
                ], validateTrigger: 'blur'}
              ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="单位类型"
        v-bind="layout">
        <a-select
          placeholder="请选择单位类型"
          v-decorator="[
                'company_type',
                {rules: [{required: true, message: '请选择单位类型'}]}
              ]">
          <a-select-option
            v-for="item in typeList"
            :key="item.value"
            :value="item.value">
            {{ item.label }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="姓名"
        v-bind="layout">
        <a-input
          type="text"
          autocomplete="true"
          v-decorator="[
                'name',
                {rules: [
                   {required: true, message: '请输入负责人姓名'},
                   {max:8,message:'长度在8个汉字以内'}
                 ],
                 validateTrigger: 'blur'}
              ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="岗位"
        v-bind="layout">
        <a-input
          type="text"
          autocomplete="true"
          v-decorator="[
                'job',
                {rules: [
                   {max:8,message:'长度在8个汉字以内'}
                 ],
                 validateTrigger: 'blur'}
              ]">
        </a-input>
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
                {
              type: 'email', message: '请输入正确邮箱地址',
            }
                 ],
                 validateTrigger: 'blur'}
              ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="联系电话"
        v-bind="layout">
        <a-input
          type="phone"
          autocomplete="true"
          placeholder="13812341234"
          v-decorator="[
            'telephone',
            {rules: [
              {required: true, message: '请输入联系电话'},
              {pattern:/^[1][3,4,5,7,8][0-9]{9}$/, message: '联系电话格式错误'}
            ],
              validateTrigger: 'blur'}
           ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="备注"
        v-bind="layout">
        <a-textarea
          placeholder="请输入备注（不多于120字）"
          :rows="4"
          v-decorator="[
            'note',
            {rules: [
              {max:120, message:'长度在120个汉字以内'}
            ],
            validateTrigger: 'blur'}
          ]"></a-textarea>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../api/detail'
  import { mapGetters } from 'vuex'

  export default {
    name: 'ContactModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String
    },
    data () {
      return {
        layout: {
          'label-col': { span: 8 },
          'wrapper-col': { span: 16 }
        },
        form: this.$form.createForm(this),
        typeList: [],
        params: {
          project_id: this.projectSelected().id
        }
      }
    },
    mounted () {
      api.getTypeList()
        .then(data => {
          //console.log(data)
          this.typeList = data
        })
    },
    methods: {
      ...mapGetters(['projectSelected']),
      handleOk () {
        this.form.validateFields((error, data) => {
          if (!error) {
            data.company_type = {
              value: data.company_type
            }
            if (this.isEdit) {
              data.id = this.record.id
              api.updateAddressList(data)
                .then(data => {
                  console.log(data)
                  this.$emit('close')
                  this.$notification.success({ message: '成功', description: '修改成功', key: 'SUCCESS' })
                })
            } else {
              api.createAddressList(data, this.params)
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