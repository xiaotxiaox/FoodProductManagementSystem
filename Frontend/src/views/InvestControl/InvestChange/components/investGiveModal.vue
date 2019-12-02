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
            'provide_name',
            {
              rules:[
                { required: true, message: '请输入变更名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.provide_name : null
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
            'provide_company',
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
            'provide_amount',
            {
              rules: [
              { required: true, message: '请输入份数' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.provide_amount : null
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
                'provide_time',
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
            'provide_remark',
            {
              rules: [
                { max:128, message: '长度在128个字符以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.provide_remark : null
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
    name: 'investGiveModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String,
      give_id: Number
    },
    data () {
      return {
        layout: {
          'label-col': { span: 8 },
          'wrapper-col': { span: 12 }
        },
        form: this.$form.createForm(this),
        gives: {},
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      getData () {
        if (this.isEdit) {
          this.record.provide_time=new moment(this.record.provide_time)
          api.getInvestGiveMatter(this.record.provide_id)
            .then(data => {
              this.gives = data
            })
        }
      },
      handleOk () {
        this.form.validateFields((error, data) => {
          console.log(data)
          if (!error) {
            data.provide_time=data.provide_time.format('YYYY-MM-DD')
            if (this.isEdit) {
              api.updateInvestGive(this.record.provide_id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createInvestGive(this.$store.getters.projectSelected.id, data)
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