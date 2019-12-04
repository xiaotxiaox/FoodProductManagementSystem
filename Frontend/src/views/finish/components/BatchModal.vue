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
        label="原料名称"
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
        label="采购数量"
        v-bind="layout">
        <a-input
          type="number"
          addonAfter="千克"
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
        label="原料单价"
        v-bind="layout">
        <a-input
          type="number"
          addonAfter="元/千克"
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
        label="总金额"
        v-bind="layout">
        <a-input
          type="number"
          addonAfter="元"
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
        label="申请日期"
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
        label="处理日期"
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
        label="状态"
        v-bind="layout">
        <a-select
          placeholder="请选择订单状态"
          v-decorator="[
                'staff',
                {rules:[{required: true, message: '请选择订单状态'}],
                initialValue: record ? record.staff : null}
              ]">
          <a-select-option :key="1" :value="1">待处理</a-select-option>
          <a-select-option :key="2" :value="2">已同意</a-select-option>
          <a-select-option :key="3" :value="3">未同意</a-select-option>

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