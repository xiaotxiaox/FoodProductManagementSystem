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
        label="订单编号"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'ingredient_id',
            {
              rules:[
                { required: true, message: '请输入订单编号' },
                { max:16, message: '长度在16个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.ingredient_id: null
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
                'customer_name',
                {rules:[{required: true, message: '请选择客户姓名'}],
                initialValue: record ? record.customer_name : null}
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
        label="商品名称"
        v-bind="layout">
        <a-select
          placeholder="请选择商品名称"
          v-decorator="[
                'item_name',
                {rules:[{required: true, message: '请选择客户姓名'}],
                initialValue: record ? record.item_name : null}
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
          type="text"
          v-decorator="[
            'item_num',
            {
              rules: [
                { max:32, message: '长度在32个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.item_num : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="订货日期"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'order_date',
                {rules: [{ type: 'object', required: true, message: '请输入订货日期' }],
                 initialValue: isEdit ? record.starting_date : null}
              ]">
        </a-date-picker>
      </a-form-item>
      <a-form-item
        label="提货日期"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'get_date',
                {rules: [{ type: 'object', required: true, message: '请输入开工日期' }],
                 initialValue: isEdit ? record.get_date : null}
              ]"></a-date-picker>
      </a-form-item>
      <a-form-item
        label="订单状态"
        v-bind="layout">
        <a-select
          placeholder="请选择订单状态"
          v-decorator="[
                'staff',
                {rules:[{required: true, message: '请选择订单状态'}],
                initialValue: record ? record.staff : null}
              ]">
          <a-select-option :key="1" :value="1">待付款</a-select-option>
          <a-select-option :key="2" :value="2">进行中</a-select-option>
          <a-select-option :key="3" :value="3">退货中</a-select-option>
          <a-select-option :key="4" :value="4">订单完成</a-select-option>
          <a-select-option :key="5" :value="5">退货完成</a-select-option>
          <a-select-option :key="6" :value="6">异常</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="处理人"
        v-bind="layout">
        <a-select
          placeholder="请选择处理人"
          v-decorator="[
                'staff',
                {rules:[{required: true, message: '请选择处理人'}],
                initialValue: record ? record.staff : null}
              ]">
          <a-select-option
            v-for="item in typeList"
            :key="item.value"
            :value="item.value">
            {{ item.label }}
          </a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
    import api from '../../../api/sale'
    import {mapGetters} from 'vuex'

    export default {
        name: 'IngredientModal',
        props: {
            record: Object,
            visible: Boolean,
            type: String,
        },
        data() {
            return {
                //project_id: this.projectSelected().id,
                layout: {
                    'label-col': {span: 8},
                    'wrapper-col': {span: 12}
                },
                form: this.$form.createForm(this),
                matter: {},
                typeList: [],
            }
        },
        mounted() {
        },
        methods: {
            ...mapGetters(['projectSelected']),
            handleOk() {
                this.form.validateFields((error, data) => {
                    if (!error) {
                        data.order_date = data.order_date.format('YYYY-MM-DD')
                        data.order_date = data.order_date.format('YYYY-MM-DD')
                        if (this.isEdit) {
                            api.updateOrder(this.record.id, data)
                                .then(data => {
                                    this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                                    this.$emit('close')
                                })
                        } else {
                            api.createOrder(this.project_id, data)
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