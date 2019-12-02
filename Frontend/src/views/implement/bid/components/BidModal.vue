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
        label="名称"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              rules:[
                { required: true, message: '请输入名称' },
                { max:32, message: '长度在32个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.name : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="状态"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}">
        <a-select
          placeholder="请选择状态"
          option.initialValue="WILL"
          v-decorator="[
                'status',
                {rules: [
                {required: true, message: '请选择状态'}
                ],
                initialValue: record?record.status:'WILL'}
              ]">
          <a-select-option :key="1" value="DONE">完成</a-select-option>
          <a-select-option :key="2" value="DOING">进行中</a-select-option>
          <a-select-option :key="3" value="WILL">未开始</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="代理合同时间"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'contract_time',
                {rules: [{type: 'object'}],
                initialValue: record ? record.contract_time : null}
              ]"></a-date-picker>
      </a-form-item>
      <a-form-item
        label="预计时间"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'estimated_time',
                {rules: [{type: 'object'}],
                initialValue: record ? record.estimated_time : null}
              ]"></a-date-picker>
      </a-form-item>
      <a-form-item
        label="公告时间"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'announce_time',
                {rules: [{type: 'object'}],
                initialValue: record ? record.announce_time : null}
              ]"></a-date-picker>
      </a-form-item>
      <a-form-item
        label="招标控制价"
        v-bind="layout">
        <a-input
          type="decimal(13,2)"
          addonAfter="元"
          v-decorator="[
            'control_price',
            {
              rules: [
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.control_price : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="招标时间"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'bid_time',
                {rules: [{type: 'object'}],
                initialValue: record ? record.bid_time : null}
              ]"></a-date-picker>
      </a-form-item>
      <a-form-item
        label="中标单位"
        v-bind="layout">
        <a-input
          :rows="4"
          v-decorator="[
            'bid_company',
            {
              rules: [
                { max:64, message: '长度在64个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.bid_company : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="联系人"
        v-bind="layout">
        <a-input
          :rows="4"
          v-decorator="[
            'linkman',
            {
              rules: [
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.linkman : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="电话"
        v-bind="layout">
        <a-input
          :rows="4"
          v-decorator="[
            'phone',
            {
              rules: [
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.phone : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="进场时间"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'approach_time',
                {rules: [{type: 'object'}],
                initialValue: record ? record.approach_time : null}
              ]"></a-date-picker>
      </a-form-item>
      <a-form-item
        label="代理费"
        v-bind="layout">
        <a-input
          type="decimal(13,2)"
          addonAfter="元"
          :rows="4"
          v-decorator="[
            'agency_fee',
            {
              rules: [
                { max:16, message: '长度在16个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.agency_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="中标金额"
        v-bind="layout">
        <a-input
          :rows="4"
          type="decimal(13,2)"
          addonAfter="元"
          v-decorator="[
            'bid_price',
            {
              rules: [
                { max:256, message: '长度在256个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.bid_price : null
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
  import moment from 'moment'

  export default {
    name: 'BidModal',
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
      this.getData()
    },
    methods: {
      ...mapGetters(['projectSelected']),
      getData() {
        if (this.isEdit) {
          if (this.record.contract_time !== null) {
            this.record.contract_time = new moment(this.record.contract_time)
          }
          if (this.record.estimated_time !== null) {
            this.record.estimated_time = new moment(this.record.estimated_time)
          }
          if (this.record.announce_time !== null) {
            this.record.announce_time = new moment(this.record.announce_time)
          }
          if (this.record.bid_time !== null) {
            this.record.bid_time = new moment(this.record.bid_time)
          }
          if (this.record.approach_time !== null) {
            this.record.approach_time = new moment(this.record.approach_time)
          }
        }
      },
      handleOk() {
        this.form.validateFields((error, data) => {
          if (!error) {
            if (data.contract_time != null) {
              data.contract_time = data.contract_time.format('YYYY-MM-DD')
            }
            if (data.estimated_time != null) {
              data.estimated_time = data.estimated_time.format('YYYY-MM-DD')
            }
            if (data.announce_time != null) {
              data.announce_time = data.announce_time.format('YYYY-MM-DD')
            }
            if (data.bid_time != null) {
              data.bid_time = data.bid_time.format('YYYY-MM-DD')
            }
            if (data.approach_time != null) {
              data.approach_time = data.approach_time.format('YYYY-MM-DD')
            }
          }
          if (this.isEdit) {
            api.updateBid(this.record.bid_id, data)
              .then(data => {
                this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                this.$emit('close')
              })
          } else {
            api.createBid(this.project_id, data)
              .then(data => {
                this.$notification.success({message: '成功', description: '新建成功', key: 'SUCCESS'})
                this.$emit('close')
              })
          }
      }
  )
  },
  handleCancel()
  {
    this.$emit('close')
  }

  },
  computed: {
    isEdit()
    {
      return this.type === 'edit'
    }
  ,
    title()
    {
      return this.isEdit ? '编辑' : '新建'
    }
  }
  }
</script>

<style scoped lang="less">

</style>