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
        label="客户类型"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'customer_type',
            {
              rules:[
                { required: true, message: '请输入客户类型' },
                { max:16, message: '长度在16个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.customer_type: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="折扣比例"
        v-bind="layout">
        <a-input
          type="number"
          addonAfter="%"
          v-decorator="[
            'discount',
            {
              rules: [
              {required: true, message: '请输入预付款比例'},
                { max:32, message: '长度在32个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.discount: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="预付款比例"
        v-bind="layout">
        <a-input
          type="number"
          addonAfter="%"
          v-decorator="[
            'prepay_ratio',
            {
              rules: [
              {required: true, message: '请输入预付款比例'},
                { max:32, message: '长度在32个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.discount: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="处理人"
        v-bind="layout">
        <a-select
          placeholder="请选择处理人"
          v-decorator="[
                'staff',
                {rules:[{required: true, message: '请选择处理人'}],
                initialValue: record ? record.staff : null}
              ]">
          <a-select-option
            v-for="item in typeList"
            :key="item.value"
            :value="item.value">
            {{ item.label }}
          </a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../api/sale'
  import {mapGetters} from 'vuex'

  export default {
    name: 'SalePolicyModal',
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
    },
    methods: {
      ...mapGetters(['projectSelected']),
      handleOk() {
        this.form.validateFields((error, data) => {
          if (!error) {
            if (this.isEdit) {
              api.updateCustomerInfo(this.record.id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createCustomerInfo(this.project_id, data)
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