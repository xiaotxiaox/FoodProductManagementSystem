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
    <a-form :form="form">
      <a-form-item
        v-if="isEdit"
        label="id"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        {{record.else_detail_id}}
      </a-form-item>
      <a-form-item
        label="名称"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12,offset:2}">
        <a-input
          @keypress.enter="handleOk"
          type="text"
          v-decorator="[
            'title',
            {
              rules:
                [
                  {required: true, message: '请输入名称'},
                  {max:128, message:'长度在128个汉字以内'}
                ],
              validateTrigger: 'blur',
              initialValue: isEdit ? record.title : null
            }
          ]">
        </a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../../api/completion'

  export default {
    name: 'ElseModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String
    },
    data () {
      return {
        form: this.$form.createForm(this)
      }
    },
    methods: {
      handleOk () {
        this.form.validateFields((error, data) => {
          if (!error) {
            if (this.isEdit) {
              api.updateElseDetail(this.record.else_detail_id, data)
                .then(data => {
                  this.$emit('close')
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                })
            } else {
              api.createElseDetail(this.$store.getters.projectSelected.id, data)
                .then(data => {
                  this.$emit('close')
                  this.$notification.success({message: '成功', description: '新建成功', key: 'SUCCESS'})
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