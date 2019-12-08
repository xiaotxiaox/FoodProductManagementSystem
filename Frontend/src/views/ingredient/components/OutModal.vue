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
        label="出库编号"
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
        label="出库数量"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="number"
          v-decorator="[
            'num',
            {
              rules:[
                { required: true, message: '请输入出库数量' },
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.num: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="出库日期"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'timeout',
                {rules: [{ type: 'object', required: true, message: '请输入进货时间' }],
                 initialValue: isEdit ? record.timeout : null}
              ]">
        </a-date-picker>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
    import api from '../../../api/ingredient'
    import moment from 'moment'

    export default {
        name: 'OutModal',
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
                    if (this.record.timeout !== null) {
                        this.record.timeout = new moment(this.record.timeout)
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
                        data.timeout = data.timeout.format('YYYY-MM-DD')
                        if (this.isEdit) {
                            api.updateOutbound(this.record.id, data)
                                .then(data => {
                                    this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                                    this.$emit('close')
                                })
                        } else {
                            api.createOutbound(data)
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