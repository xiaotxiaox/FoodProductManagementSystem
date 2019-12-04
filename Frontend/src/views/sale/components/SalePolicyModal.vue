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
        v-if="isEdit"
        label="客户编号"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          disabled
          type="number"
          v-decorator="[
            'id',
            {
              rules:[
                { required: true, message: '请输入客户类型' },
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.id: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="客户类型"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'type',
            {
              rules:[
                { required: true, message: '请输入客户类型' },
                { max:16, message: '长度在16个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.type: null
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
              {required: true, message: '请输入预付款比例'}
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
            'rate',
            {
              rules: [
              {required: true, message: '请输入预付款比例'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.rate: null
            }
          ]">
        </a-input>
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
              api.updateSalePolicy(this.record.id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createSalePolicy(data)
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