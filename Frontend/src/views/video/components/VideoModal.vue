<template>
  <a-modal
    :title="title"
    :visible="visible"
    :keyboard="false"
    :maskClosable="false"
    okText="保存"
    okType="primary"
    @ok="handleVideoOk()"
    @cancel="handleVideoCancel()">
    <a-form
      :form="form">
      <a-form-item
        label="名字"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              rules: [
                { max:128, message: '长度在64个汉字以内' },
                 {required: true, message: '请输入监控名称'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.name : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="地点"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'place',
            {
              rules: [
                { max:128, message: '长度在64个汉字以内' },
                 {required: true, message: '请输入监控地址'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.place : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="网址"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'url',
            {
              rules: [
               { max:512, message: '长度在512个字符以内' },
              {required: true, message: '请输入监控网址'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.url : null
            }
          ]">
        </a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../api/video'
  import { mapGetters } from 'vuex'

  export default {
    name: 'VideoModal',
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
      handleVideoOk () {
        this.form.validateFields((error, data) => {
          if (!error) {
            if (this.isEdit) {
              api.updateVideo(this.record.id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createVideo(this.project_id,data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '新建成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            }
            this.change()
          }
        })
      },
      handleVideoCancel () {
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