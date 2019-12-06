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
        label="原料编号"
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
                { required: true, message: '请输入原料编号' },
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.id: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="原料名称"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              rules: [
              {required: true, message: '请输入原料名称'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.name: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="供货商"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'purchaser',
            {
              rules: [
              {required: true, message: '请输入原料名称'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.purchaser: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="原料单价"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'price',
            {
              rules: [
              {required: true, message: '请输入商品单价'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.price: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="计量单位"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'unit',
            {
              rules: [
              {required: true, message: '请输入原料计量单位'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.unit: null
            }
          ]">
        </a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../api/ingredient'
  import {mapGetters} from 'vuex'

  export default {
    name: 'SumModal',
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
              api.updateSum(this.record.id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createSum(data)
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