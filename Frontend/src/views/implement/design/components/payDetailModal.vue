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
            label="支付时间"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-date-picker
              style="width:100%"
              v-decorator="[
                'time',
                {rules: [{type: 'object',required: true, message: '请选择支付时间'}],
                initialValue: record ? record.time : null}
              ]">
            </a-date-picker>
          </a-form-item>
      <a-form-item
        label="本次实付"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'money',
            {
              rules: [
                 { max:128,message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.money : null
            }
          ]">
        </a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../../api/implement'
  import moment from 'moment'
  export default {
    name: 'payDetailModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String,
      company_id: Number,
    },
    data () {
      return {
        layout: {
          'label-col': { span: 8 },
          'wrapper-col': { span: 12 }
        },
        form: this.$form.createForm(this)
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      getData () {
        if (this.isEdit) {
          this.record.time=new moment(this.record.time)
        }
      },
      handleOk () {
        this.form.validateFields((error, data) => {
          console.log(data)
          if (!error) {
            data.time=data.time.format('YYYY-MM-DD')
            if (this.isEdit) {
              api.updateDesignPayDetail(this.record.id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createDesignPayDetail(this.company_id, data)
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

<style scoped>

</style>