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
        label="办理事项"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'matter',
            {
              rules:[
                { required: true, message: '请输入办理事项' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.matter : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="份数"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'number',
            {
              rules: [
                 { max:128,message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.number : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="办理部门"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'department',
            {
              rules: [
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.department : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="状态"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
          'status',
          {
            rules: [
              { max:128, message: '长度在128个汉字以内' }
            ],
            validateTrigger: 'blur',
            initialValue: record ? record.status : null
          }
        ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="联系方式"
        v-bind="layout">
        <a-input
          type="text"
          placeholder="13812341234"
          v-decorator="[
            'telephone',
            {
              rules: [
                { max:128, message: '长度在128个字符以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.telephone : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="是否缴费"
        v-bind="layout">
        <a-input
          type="text"
          placeholder="是/否"
          v-decorator="[
            'paid_status',
            {
              rules: [
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.paid_status : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="办理时限"
        v-bind="layout">
        <a-input
          type="text"
          placeholder=""
          v-decorator="[
            'deal_time',
            {
              rules: [
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.deal_time : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="备注"
        v-bind="layout">
        <a-textarea
          placeholder="请输入备注（不多于256字）"
          :rows="4"
          v-decorator="[
            'note',
            {
              rules: [
                { max:256, message: '长度在256个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.note : null
            }
          ]">
        </a-textarea>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../../api/implement'

  export default {
    name: 'ApplicationDetailModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String,
      application_id: Number
    },
    data () {
      return {
        layout: {
          'label-col': { span: 8 },
          'wrapper-col': { span: 12 }
        },
        form: this.$form.createForm(this),
        matter: {},
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      getData () {
        if (this.isEdit) {
          api.getApplicationMatter(this.record.matter_id)
            .then(data => {
              this.matter = data
            })
        }
      },
      handleOk () {
        this.form.validateFields((error, data) => {
          if (!error) {
            if (this.isEdit) {
              api.updateApplicationMatter(this.record.matter_id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createApplicationMatter(this.application_id, data)
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