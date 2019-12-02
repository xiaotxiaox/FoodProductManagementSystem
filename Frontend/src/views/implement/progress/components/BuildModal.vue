<template>
  <a-modal
    :title="title"
    :visible="visible"
    :keyboard="false"
    :maskClosable="false"
    okText="保存"
    okType="primary"
    @ok="handleBuildOk()"
    @cancel="handleBuildCancel()">
    <a-form
      :form="form">
      <a-form-item
        label="建筑名称"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              rules: [
                { max:16, message: '长度在16个汉字以内' },
                 {required: true, message: '请输入建筑名称'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.name : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="投资进度比"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="number"
          addonAfter="%"
          v-decorator="[
            'progress',
            {
              rules: [
              {required: true, message: '请输入投资进度比'},
              {validator: validateProgressLimit, message: '进度范围必须为0-100'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.progress : null
            }
          ]">
        </a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../../api/implement'
  import { mapGetters } from 'vuex'

  export default {
    name: 'BuildModal',
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
      handleBuildOk () {
        this.form.validateFields((error, data) => {
          if (!error) {
            if (this.isEdit) {
              api.updateBuild(this.record.building_id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createBuild(this.project_id,data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '新建成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            }
            this.change()
          }
        })
      },
      handleBuildCancel () {
        this.$emit('close')
      },
       validateProgressLimit (rule, value, callback) {
        if (this.isEdit) {
          callback()
        } else {
          if(value<0||value>100)
            callback('false')
          else
            callback()
        }
      }

    },
    computed: {
      isEdit () {
        return this.type === 'edit'
      },
      title () {
        return this.isEdit ? '编辑' : '列名管理'
      }
    }
  }
</script>

<style scoped lang="less">

</style>