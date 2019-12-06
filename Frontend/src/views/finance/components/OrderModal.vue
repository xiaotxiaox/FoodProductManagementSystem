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
        v-if="isEdit"
        label="订单编号"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="number"
          disabled
          v-decorator="[
            'id',
            {
              rules:[
                { required: true, message: '请输入订单编号' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.id: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="客户姓名"
        v-bind="layout">
        <a-select
          placeholder="请选择客户姓名"
          v-decorator="[
                'custom',
                {
                rules:[{required: true, message: '请选择客户姓名'}],
                initialValue: record ? record.custom.id : null}
              ]">
          <a-select-option
            v-for="item in customList"
            :key="item.id"
            :value="item.id">
            {{ item.name }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="商品名称"
        v-bind="layout">
        <a-select
          placeholder="请选择商品名称"
          v-decorator="[
                'goods',
                {
                // rules:[{required: true, message: '请选择客户姓名'}],
                initialValue: record ? record.goods : null}
              ]">
          <a-select-option
            v-for="item in typeList"
            :key="item.value"
            :value="item.value">
            {{ item.label }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="商品数量"
        v-bind="layout">
        <a-input
          type="number"
          v-decorator="[
            'count',
            {
              rules: [
             {required: true, message: '请输入商品数量'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.count : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="订单总金额"
        v-bind="layout">
        <a-input
          type="number"
          v-decorator="[
            'totalCost',
            {
              rules: [
             {required: true, message: '请输入商品数量'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.totalCost : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="优惠后总金额"
        v-bind="layout">
        <a-input
          type="number"
          v-decorator="[
            'discountCost',
            {
              rules: [
             {required: true, message: '请输入商品数量'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.discountCost : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="已付金额"
        v-bind="layout">
        <a-input
          type="number"
          v-decorator="[
            'paidMoney',
            {
              rules: [
             {required: true, message: '请输入商品数量'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.paidMoney : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="预付款日期"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'willDate',
                {rules: [{ type: 'object', required: true, message: '请输入订货日期' }],
                 initialValue: isEdit ? record.willDate : null}
              ]">
        </a-date-picker>
      </a-form-item>
      <a-form-item
        label="尾款日期"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'finalDate',
                {
                // rules: [{ type: 'object', required: true, message: '请输入开工日期' }],
                 initialValue: isEdit ? record.finalDate : null}
              ]"></a-date-picker>
      </a-form-item>
      <a-form-item
        label="订单状态"
        v-bind="layout">
        <a-select
          placeholder="请选择订单状态"
          v-decorator="[
                'state',
                {rules:[{required: true, message: '请选择订单状态'}],
                initialValue: record ? record.state : null}
              ]">
          <a-select-option :key="1" :value="1">待付款</a-select-option>
          <a-select-option :key="2" :value="2">进行中</a-select-option>
          <a-select-option :key="3" :value="3">退货中</a-select-option>
          <a-select-option :key="4" :value="4">订单完成</a-select-option>
          <a-select-option :key="5" :value="5">退货完成</a-select-option>
          <a-select-option :key="6" :value="6">异常</a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
    import api from '../../../api/sale'
    import moment from 'moment'
    import {mapGetters} from 'vuex'

    export default {
        name: 'OrderModal',
        props: {
            record: Object,
            visible: Boolean,
            type: String,
        },
        data() {
            return {
                layout: {
                    'label-col': {span: 8},
                    'wrapper-col': {span: 12}
                },
                form: this.$form.createForm(this),
                matter: {},
                typeList: [],
                customList: []
            }
        },
        mounted() {
            this.getData()
        },
        methods: {
            ...mapGetters(['projectSelected']),
            getData() {
                api.getCustomerInfoList()
                    .then(data => {
                        this.customList = data
                    })
                if (this.isEdit) {
                    if (this.record.willDate !== null) {
                        this.record.willDate = new moment(this.record.willDate)
                    }
                    if (this.record.finalDate !== null) {
                        this.record.finalDate = new moment(this.record.finalDate)
                    }
                }
            },
            handleOk() {
                this.form.validateFields((error, data) => {
                    if (!error) {
                        if (data.willDate)
                            data.willDate = data.willDate.format('YYYY-MM-DD')
                        if (data.finalDate)
                            data.finalDate = data.finalDate.format('YYYY-MM-DD')
                        if (this.isEdit) {
                            api.updateOrder(this.record.id, data)
                                .then(data => {
                                    this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                                    this.$emit('close')
                                })
                        } else {
                            api.createOrder(data)
                                .then(data => {
                                    this.$notification.success({message: '成功', description: '新建成功', key: 'SUCCESS'})
                                    this.$emit('close')
                                })
                        }
                    }
                })
            },
            handleCancel() {
                this.$emit('close')
            }

        },
        computed: {
            isEdit() {
                return this.type === 'edit'
            },
            title() {
                return this.isEdit ? '编辑' : '新建'
            }
        }
    }
</script>

<style scoped lang="less">

</style>