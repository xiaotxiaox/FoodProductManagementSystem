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
        label="新增列名"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              rules: [
                { max:16, message: '长度在16个汉字以内' },
                 {required: true, message: '请输入列名'}
              ],
              validateTrigger: 'blur',
              initialValue:  null
            }
          ]">
        </a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../../api/implement'
  import {mapGetters} from 'vuex'
  import ACol from 'ant-design-vue/es/grid/Col'
  import AFormItem from 'ant-design-vue/es/form/FormItem'

  export default {
    name: 'TableModal',
    components: {AFormItem, ACol},
    props: {
      record: Object,
      visible: Boolean
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
      handleBuildOk() {
        this.form.validateFields((error, data) => {
          if (!error) {
            const key = `open${Date.now()}`;
            this.$notification.open({
              message: '确认',
              description: '确认保存后不可修改列名，请确认后修改',
              btn: (h) => {
                return h('a-button', {
                  props: {
                    type: 'primary'
                  },
                  on: {
                    click: () => {
                      // api.createBuild(this.project_id, data)
                      //   .then(data => {
                      //     this.$notification.success({ message: '成功', description: '新建成功', key: 'SUCCESS' })
                      //     this.$emit('close')
                      //   })

                      this.$notification.close(key)
                      this.$emit('close')
                    }
                  }
                }, '确认')
              },
              key
            });
          }
        })
      },

      handleBuildCancel() {
        this.$emit('close')
      },
      validateProgressLimit(rule, value, callback) {
        if (value < 0 || value > 100)
          callback('false')
        else
          callback()
      }
    },
    computed: {
      title() {
        return '新建'
      }
    }
  }
</script>

<style scoped>

</style>
