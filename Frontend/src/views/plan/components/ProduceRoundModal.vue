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
        label="编号"
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
                { required: true, message: '请输入原料编号' },
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.id: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="批次任务说明"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              validateTrigger: 'blur',
              initialValue: record ? record.name: null
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
                'goods',
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
        label="生产数量"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="number"
          v-decorator="[
            'count',
            {
              rules:[
                { required: true, message: '请输入原料用量' },
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.count: null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="生产班组"
        v-bind="layout">
        <a-select
          placeholder="请选择生产班组"
          v-decorator="[
                'team',
                {rules:[{required: true, message: '请选择原料名称'}],
                initialValue: record ? record.team.id : null}
              ]">
          <a-select-option
            v-for="item in teamList"
            :key="item.id"
            :value="item.id">
            {{ item.name }}
          </a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../api/plan'
  import apiTeam from '../../../api/produceDepartment'
  import apiGood from '../../../api/sale'

  export default {
    name: 'ProduceRoundModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String,
        id:String
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
          teamList:[]
      }
    },
    mounted() {
        this.getData()
    },
    methods: {
        getData(){
            apiGood.getProductList()
                .then(data => {
                    this.typeList = data
                })
            apiTeam.getTeamList()
                .then(data => {
                    this.teamList = data
                })
        },
      handleOk() {
        this.form.validateFields((error, data) => {
          if (!error) {
            if (this.isEdit) {
              api.updatePlanRound(this.record.id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
            } else {
              api.createPlanRound(this.id,data)
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