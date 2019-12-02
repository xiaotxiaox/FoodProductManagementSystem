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
        label="项目单位名称"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              rules:[
                { required: true, message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.name : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="类别"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'style',
            {
              rules: [
                 { max:128,message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.style : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="项目名称"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'project_name',
            {
              rules: [
                 { max:128,message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.project_name : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="合同额"
        v-bind="layout">
        <a-input
          type="number"
          addonAfter="元"
          v-decorator="[
            'contract_money',
            {
              rules: [
               { required: true, message: '请输入合同额' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.contract_money : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="结算额"
        v-bind="layout">
        <a-input
          type="number"
          addonAfter="元"
          v-decorator="[
          'true_money',
          {
            rules: [
              { max:128, message: '长度在128个汉字以内' }
            ],
            validateTrigger: 'blur',
            initialValue: record ? record.true_money : null
          }
        ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="本次应付"
        v-bind="layout">
        <a-input
          type="number"
          addonAfter="元"
          v-decorator="[
          'should_money',
          {
            rules: [
              { max:128, message: '长度在128个汉字以内' }
            ],
            validateTrigger: 'blur',
            initialValue: record ? record.should_money : null
          }
        ]">
        </a-input>
      </a-form-item>
<!--       <a-form-item-->
<!--            label="支付时间"-->
<!--            :label-col="{span: 6}"-->
<!--            :wrapper-col="{span: 18}">-->
<!--            <a-date-picker-->
<!--              style="width:100%"-->
<!--              v-decorator="[-->
<!--                'paid_date',-->
<!--                {rules: [{type: 'object',required: true, message: '请选择支付时间'}]}-->
<!--              ]"></a-date-picker>-->
<!--          </a-form-item>-->
    </a-form>
  </a-modal>
</template>

<script>
   import api from '../../../../../api/implement'
   export default {
     name: 'Invest_processModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String,
      process_id: String
    },
    data () {
      return {
        layout: {
          'label-col': { span: 8 },
          'wrapper-col': { span: 12 }
        },
        form: this.$form.createForm(this),
       process: {},
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      getData () {
        if (this.isEdit) {
          api.getInvestProcessMatter(this.record.id)
            .then(data => {
              console.log(data)
              this.process = data
            })
        }
      },
      handleOk () {
        this.form.validateFields((error, data) => {
          console.log(data)
          if (!error) {
            if (this.isEdit) {
              api.updateInvestProcess(this.record.id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createInvestProcess(this.$store.getters.projectSelected.id, data)
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