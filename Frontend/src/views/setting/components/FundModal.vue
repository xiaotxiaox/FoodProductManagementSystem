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
        label="名称"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'label',
            {
              rules:[
                { required: true, message: '请输入名称' },
                { max:16, message: '长度在16个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.label: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="描述"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'description',
            {
              rules: [
                { max:64, message: '长度在64个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.description : null
            }
          ]">
        </a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../api/setting'
  import { mapGetters } from 'vuex'

  export default {
    name: 'FundModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String,
    },
    data () {
      return {
        project_id:this.projectSelected().id,
        layout: {
          'label-col': { span: 8 },
          'wrapper-col': { span: 12 }
        },
        form: this.$form.createForm(this),
        matter: {}
      }
    },
    mounted () {
    },
    methods: {
       ...mapGetters(['projectSelected']),
      handleOk () {
        this.form.validateFields((error, data) => {
          if (!error) {
            if (this.isEdit) {
              api.updateFund(this.record.value, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createFund(data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '新建成功', key: 'SUCCESS'})
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

<style scoped lang="less">

</style>