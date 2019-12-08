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
        label="生产计划编号"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          disabled
          type="number"
          v-decorator="[
            'id',
            {
              rules:[
                { required: true, message: '请输入客户类型' },
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.id: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="商品名称"
        v-bind="layout">
        <a-select
          placeholder="请选择商品名称"
          v-decorator="[
                'goodsid',
                {rules:[{required: true, message: '请选择原料名称'}],
                initialValue: record ? record.goods.id : null}
              ]">
          <a-select-option
            v-for="item in typeList"
            :key="item.id"
            :value="item.id">
            {{ item.name }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="计划生产数量"
        v-bind="layout">
        <a-input
          type="number"
          v-decorator="[
            'neednum',
            {
              rules: [
              {required: true, message: '请输入计划生产数量'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.neednum: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="开始生产日期"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'timeproduce',
                {rules: [{ type: 'object', required: true, message: '请输入订货日期' }],
                 initialValue: isEdit ? record.timeproduce : null}
              ]">
        </a-date-picker>
      </a-form-item>
      <a-form-item
        label="计划结束日期"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'timelastest',
                {rules: [{ type: 'object', required: true, message: '请输入计划结束日期' }],
                 initialValue: isEdit ? record.timelastest : null}
              ]">
        </a-date-picker>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../api/plan'
  import moment from 'moment'

  export default {
    name: 'ProducePlanModal',
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
      }
    },
    mounted() {
        this.getData()
    },
    methods: {
        getData() {
            api.getProductList()
                .then(data => {
                    this.typeList = data
                })
            if (this.isEdit) {
                if (this.record.timeCreate !== null) {
                    this.record.timeCreate = new moment(this.record.timeCreate)
                }
                if (this.record.timelastest !== null) {
                    this.record.timelastest = new moment(this.record.timelastest)
                }
                if (this.record.timeproduce !== null) {
                    this.record.timeproduce = new moment(this.record.timeproduce)
                }
            }
        },
      handleOk() {
        this.form.validateFields((error, data) => {
          if (!error) {
              if (data.timeCreate)
                  data.timeCreate = data.timeCreate.format('YYYY-MM-DD')
              if (data.timelastest)
                  data.timelastest = data.timelastest.format('YYYY-MM-DD')
              if (data.timeproduce)
                  data.timeproduce = data.timeproduce.format('YYYY-MM-DD')
            if (this.isEdit) {
              api.updatePlan(this.record.id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createPlan(data)
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