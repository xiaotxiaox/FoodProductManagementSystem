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
        label="重点"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-textarea
          type="text"
          :rows="8"
          v-decorator="[
            'keyPoint',
            {
              rules:[
                { required: true, message: '请输入重点' },
                { max:256, message: '长度在256个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.keyPoint : null
            }
          ]">
        </a-textarea>
      </a-form-item>
      <a-form-item
        label="难点"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-textarea
          type="text"
          :rows="8"
          v-decorator="[
            'difficulty',
            {
              rules:[
                { required: true, message: '请输入难点' },
                { max:256, message: '长度在256个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.difficulty : null
            }
          ]">
        </a-textarea>
      </a-form-item>
      <a-form-item
        label="通病"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-textarea
          type="text"
          :rows="8"
          v-decorator="[
            'CommonFailing',
            {
              rules:[
                { required: true, message: '请输入通病' },
                { max:256, message: '长度在256个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.CommonFailing : null
            }
          ]">
        </a-textarea>
      </a-form-item>

    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../../api/implement'
  import {mapGetters} from 'vuex'
  import ATextarea from "ant-design-vue/es/input/TextArea";

  export default {
    name: 'EmphasisModal',
    components: {ATextarea},
    props: {
      record: Object,
      visible: Boolean,
      type: String,
    },
    data() {
      return {
        project_id: this.projectSelected().id,
        layout: {
          'label-col': {span: 8},
          'wrapper-col': {span: 12}
        },
        form: this.$form.createForm(this),
        matter: {}
      }
    },
    mounted() {
    },
    methods: {
      ...mapGetters(['projectSelected']),
      handleOk() {
        this.form.validateFields((error, data) => {
          if (!error) {
            api.updateDifficulty(this.record.difficulty_id, data)
              .then(data => {
                this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                this.$emit('close')
              })
          }
        })
      },
      handleCancel() {
        this.$emit('close')
      }

    },
    computed: {
      title() {
        return '编辑'
      }
    }
  }
</script>

<style scoped lang="less">

</style>