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
        label="设计单位名称"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              rules:[
                { required: true, message: '请输入设计单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.name : null
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
              { required: true, message: '请输入设计单位名称' },
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
          type="text"
          v-decorator="[
            'contract_price',
            {
              rules: [
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.contract_price : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="状态"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
          'status',
          {
            rules: [
              { max:128, message: '长度在128个汉字以内' }
            ],
            validateTrigger: 'blur',
            initialValue: record ? record.status : null
          }
        ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="联系方式"
        v-bind="layout">
        <a-input
          type="text"
          placeholder="13812341234"
          v-decorator="[
            'telephone',
            {
              rules: [
                { max:128, message: '长度在128个字符以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.telephone : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="是否缴费"
        v-bind="layout">
        <a-input
          type="text"
          placeholder="是/否"
          v-decorator="[
            'paid_status',
            {
              rules: [
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.paid_status : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="办理时限"
        v-bind="layout">
        <a-input
          type="text"
          placeholder=""
          v-decorator="[
            'deal_time',
            {
              rules: [
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.deal_time : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="备注"
        v-bind="layout">
        <a-textarea
          placeholder="请输入备注（不多于256字）"
          :rows="4"
          v-decorator="[
            'note',
            {
              rules: [
                { max:256, message: '长度在256个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.note : null
            }
          ]">
        </a-textarea>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../../api/implement'

  export default {
    name: 'DesignAccountDetailModal'
  }
</script>

<style scoped="less">

</style>