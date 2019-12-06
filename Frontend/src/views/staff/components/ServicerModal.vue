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
        label="员工编号"
        v-if="isEdit"
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
                { required: true, message: '请输入员工编号' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.id: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="员工姓名"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              rules:[
                { required: true, message: '请输入员工编号' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.name: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="性别"
        v-bind="layout">
        <a-select
          placeholder="请选择性别"
          v-decorator="[
                'gender',
                {rules:[{required: true, message: '请选择员工性别'}],
                initialValue: record ? record.gender : null}
              ]">
          <a-select-option :key="1" :value="1">男</a-select-option>
          <a-select-option :key="2" :value="2">女</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="工作部门"
        v-bind="layout">
        <a-select
          placeholder="请选择工作部门"
          v-decorator="[
                'department',
                {rules:[{required: true, message: '请选择员工性别'}],
                initialValue: record ? record.department : null}
              ]">
          <a-select-option :key="1" :value="1">销售部</a-select-option>
          <a-select-option :key="2" :value="2">财务部</a-select-option>
          <a-select-option :key="3" :value="3">生产计划科</a-select-option>
          <a-select-option :key="4" :value="4">成品库部门</a-select-option>
          <a-select-option :key="5" :value="5">生产车间部门</a-select-option>
          <a-select-option :key="6" :value="6">原材料库</a-select-option>
          <a-select-option :key="7" :value="7">人事部</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="入职时间"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'timein',
                {rules: [{ type: 'object', required: true, message: '请输入入职时间' }],
                 initialValue: isEdit ? record.timein : null}
              ]">
        </a-date-picker>
      </a-form-item>
      <a-form-item
        label="工资"
        v-bind="layout">
        <a-input
          type="number"
          addonAfter="元/月"
          v-decorator="[
            'pay',
            {
              validateTrigger: 'blur',
              initialValue: record ? record.pay : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="联系电话"
        v-bind="layout">
        <a-input
          type="phone"
          autocomplete="true"
          placeholder="13812341234"
          v-decorator="[
                            'phone',
                            {rules: [
                                    {required: true, message: '请输入联系电话'},
                                    {pattern:/^[1][3,4,5,7,8][0-9]{9}$/, message: '联系电话格式错误'}
                                    ],
                            validateTrigger: 'blur',
                            initialValue: record ? record.phone : null}
                            ]">
        </a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
    import api from '../../../api/staff'
    import moment from 'moment'
    import {mapGetters} from 'vuex'

    export default {
        name: 'ServicerModal',
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
            this.getData();
        },
        methods: {
            getData() {
                if (this.isEdit) {
                    if (this.record.timein !== null) {
                        this.record.timein = new moment(this.record.timein)
                    }
                }
            },
            handleOk() {
                this.form.validateFields((error, data) => {
                    if (!error) {
                        console.log(data)
                        data.timein = data.timein.format('YYYY-MM-DD')
                        if (this.isEdit) {
                            api.updateServicer(this.record.id, data)
                                .then(data => {
                                    this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                                    this.$emit('close')
                                })
                        } else {
                            api.createServicer(data)
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