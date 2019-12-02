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
        label="变更名称"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'draw_name',
            {
              rules:[
                { required: true, message: '请输入变更名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.draw_name : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="领取单位"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'draw_company',
            {
              rules: [
              { required: true, message: '请输入领取单位' },
                 { max:128,message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.draw_company : null
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
            'draw_amount',
            {
              rules: [
              { required: true, message: '请输入份数' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.draw_amount : null
            }
          ]">
        </a-input>
      </a-form-item>
     <a-form-item
            label="领取时间"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-date-picker
              style="width:100%"
              v-decorator="[
                'draw_time',
                {rules: [{type: 'object',required: true, message: '请选择领取时间'}]}
              ]"></a-date-picker>
          </a-form-item>
      <a-form-item
        label="备注"
        v-bind="layout">
        <a-input
          type="text"
          placeholder=""
          v-decorator="[
            'draw_remark',
            {
              rules: [
                { max:128, message: '长度在128个字符以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.draw_remark : null
            }
          ]">
        </a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../../api/investControl'
  import moment from 'moment'
  export default {
    name: 'investReceiveModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String,
      receive_id: Number
    },
    data () {
      return {
        layout: {
          'label-col': { span: 8 },
          'wrapper-col': { span: 12 }
        },
        form: this.$form.createForm(this),
        receives: {},
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      getData () {
        if (this.isEdit) {
          this.record.draw_time=new moment(this.record.draw_time)
          api.getInvestReceiveMatter(this.record.draw_id)
            .then(data => {
              this.receives = data
            })
        }
      },
      handleOk () {
        this.form.validateFields((error, data) => {
          console.log(data)
          if (!error) {
            data.draw_time=data.draw_time.format('YYYY-MM-DD')
            if (this.isEdit) {
              api.updateInvestReceive(this.record.draw_id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createInvestReceive(this.$store.getters.projectSelected.id, data)
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