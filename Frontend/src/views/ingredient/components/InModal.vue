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
        label="入库编号"
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
                { required: true, message: '请输入入库编号' },
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.id: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="原料名称"
        v-bind="layout">
        <a-select
          placeholder="请选择原料名称"
          v-decorator="[
                'materialid',
                {rules:[{required: true, message: '请选择原料名称'}],
                initialValue: record ? record.materialtotal.id : null}
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
        label="采购数量"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="number"
          v-decorator="[
            'num',
            {
              rules:[
                { required: true, message: '请输入采购数量' },
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.num: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        v-if="isEdit"
        label="进货时间"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'timeHandle',
                {rules: [{ type: 'object', required: true, message: '请输入进货时间' }],
                 initialValue: isEdit ? record.timeHandle : null}
              ]">
        </a-date-picker>
      </a-form-item>
      <a-form-item
        label="保质期"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'timeprotect',
                {rules: [{ type: 'object', required: true, message: '请输入进货时间' }],
                 initialValue: isEdit ? record.timeprotect : null}
              ]">
        </a-date-picker>
      </a-form-item>
      <a-form-item
        label="申请状态"
        v-bind="layout">
        <a-select
          placeholder="请选择申请状态"
          v-decorator="[
                'state',
                {rules:[{required: true, message: '请选择员工性别'}],
                initialValue: record ? record.state : null}
              ]">
          <a-select-option :key="1" :value="1">申请中</a-select-option>
          <a-select-option :key="2" :value="2">已同意</a-select-option>
          <a-select-option :key="3" :value="3">未同意</a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../api/ingredient'
  import moment from 'moment'

  export default {
    name: 'InModal',
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
        getData(){
            if (this.isEdit) {
                if (this.record.timeHandle !== null) {
                    this.record.timeHandle = new moment(this.record.timeHandle)
                }
                if (this.record.timeprotect !== null) {
                    this.record.timeprotect = new moment(this.record.timeprotect)
                }
            }
            api.getSumList()
                .then(data => {
                    this.typeList = data
                    console.log(this.typeList)
                })
        },
      handleOk() {
        this.form.validateFields((error, data) => {
          if (!error) {
              data.timeprotect = data.timeprotect.format('YYYY-MM-DD')
            if (this.isEdit) {
                data.timeHandle = data.timeHandle.format('YYYY-MM-DD')
              api.updateInbound(this.record.id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createInbound(data)
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