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
        label="批次编号"
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
                { required: true, message: '请输入批次编号' },
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.id: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="商品名称"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'good.name',
            {
              rules:[
                { required: true, message: '请输入工厂名称' },
                { max:16, message: '长度在16个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.good.name: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="商品数量"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'goodcount',
            {
              rules:[
                { required: true, message: '请输入工厂地址' },
                { max:64, message: '长度在64个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.address: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="负责人"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'leader',
            {
              rules: [
              {required: true, message: '请输入负责人姓名'}
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.leader: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="负责人联系电话"
        v-bind="layout">
        <a-input
          type="phone"
          autocomplete="true"
          placeholder="13812341234"
          v-decorator="[
                            'leaderPhone',
                            {rules: [
                                    {required: true, message: '请输入联系电话'},
                                    {pattern:/^[1][3,4,5,7,8][0-9]{9}$/, message: '联系电话格式错误'}
                                    ],
                            validateTrigger: 'blur',
                            initialValue: record ? record.leaderPhone : null}
                            ]">
        </a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../api/produceDepartment'
  import {mapGetters} from 'vuex'

  export default {
    name: 'ProducingModal',
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
    },
    methods: {
      ...mapGetters(['projectSelected']),
      handleOk() {
        this.form.validateFields((error, data) => {
          if (!error) {
            if (this.isEdit) {
              api.updateFactory(this.record.id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createFactory(data)
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