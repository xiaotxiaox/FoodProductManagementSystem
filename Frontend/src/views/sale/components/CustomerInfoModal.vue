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
        label="客户编号"
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
        label="客户姓名"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              rules:[
                { required: true, message: '请输入客户姓名' },
                { max:16, message: '长度在16个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.name: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="客户电话"
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
        label="客户类型"
        v-bind="layout">
        <a-select
          placeholder="请选择客户类型"
          v-decorator="[
                'type',
                {rules:[{required: true, message: '请选择客户类型'}],
                initialValue: record ? record.type.id : null}
              ]">
          <a-select-option
            v-for="item in typeList"
            :key="item.id"
            :value="item.id">
            {{ item.type }}
          </a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../api/sale'

  export default {
    name: 'CustomerInfoModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String,
    },
    data() {
      return {
        //project_id: this.projectSelected().id,
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
            api.getSalePolicyList()
                .then(data => {
                    this.typeList = data
                    console.log(this.typeList)
                })
        },
      handleOk() {
        this.form.validateFields((error, data) => {
            console.log(111)
            console.log(data)
          if (!error) {
            if (this.isEdit) {
              api.updateCustomerInfo(this.record.id, data)
                .then(data => {
                    console.log(data)
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createCustomerInfo(data)
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