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
        label="用料名称"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              rules:[
              {required: true, message: '请输入用料名称'},
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.name : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="清单外"
        v-bind="layout">
        <a-input
          type="number"
          v-decorator="[
            'out_list',
            {
              validateTrigger: 'blur',
              initialValue: record ? record.out_list : 0
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="变更前单价"
        v-bind="layout">
        <a-input
          type="number"
          addonAfter="元"
          v-decorator="[
          'price_before',
          {
            validateTrigger: 'blur',
            initialValue: record ? record.price_before : 0
          }
        ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="变更后单价"
        v-bind="layout">
        <a-input
          type="number"
          addonAfter="元"
          v-decorator="[
          'price_after',
          {
           validateTrigger: 'blur',
            initialValue: record ? record.price_after : 0
          }
        ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="变更前量"
        v-bind="layout">
        <a-input
          type="number"
          v-decorator="[
          'number_before',
          {
            validateTrigger: 'blur',
            initialValue: record ? record.number_before : 0
          }
        ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="变更后量"
        v-bind="layout">
        <a-input
          type="number"
          v-decorator="[
          'number_after',
          {
            validateTrigger: 'blur',
            initialValue: record ? record.number_after : 0
          }
        ]">
        </a-input>
      </a-form-item>

    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../../../api/implement'

  export default {
    name: 'invest_accountModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String,
      change_id: String
    },
    data() {
      return {
        layout: {
          'label-col': {span: 8},
          'wrapper-col': {span: 12}
        },
        form: this.$form.createForm(this),
        notice: {},
      }
    },
    mounted() {
      this.getData()
    },
    methods: {
      getData() {
        if (this.isEdit) {
          api.getInvestChangeMatter(this.record.id)
            .then(data => {
              this.changes = data
            })
        }
      },
      handleOk() {
        this.form.validateFields((error, data) => {
          console.log(data)
          if (!error) {
            if (this.isEdit) {
              api.updateInvestChange(this.record.id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createInvestChange(this.$store.getters.projectSelected.id, data)
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

</style>v