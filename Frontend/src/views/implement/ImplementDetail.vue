<template>
  <page-layout :title="$route.params.name">
    <div>
      <ImplementDetail-modal
        :record="modal.record"
        :visible="modal.visible"
        :type="modal.type"
        @close="handleClose()">
      </ImplementDetail-modal>
      <a-card style="margin-bottom: 16px">
        <a-row :gutter="6">
          <a-col
            class="item"
            :xs="{ span: 24 }"
            :sm="{ span: 12 }"
            :xl="{ span: 8 }">
            <a-button
              type="primary"
              icon="plus"
              style="width: 100%"
              @click="handleCreate()">
              新建
            </a-button>
          </a-col>
        </a-row>
        <a-table
          :columns="columns"
          :dataSource="data"
          rowKey="id"
          :pagination="false">
          <template slot="operation" slot-scope="text, record, index">
            <a-button @click="handleEdit(record)">编辑</a-button>
            <a-popconfirm
              title="确认删除吗?"
              @confirm="handleDelete(record)">
              <a-button type="danger">删除</a-button>
            </a-popconfirm>
          </template>
        </a-table>
      </a-card>
      <a-card>
        <a-form>
          <a-form-item
            label="实际缴费"
            :label-col="{span: 6}"
            :wrapper-col="{span: 8}">
            <a-input
              type="decimal(5,2)"
              autocomplete="true"
              placeholder="1000"
              addonAfter="万元"
              v-decorator="['payment']">
            </a-input>
          </a-form-item>
          <a-form-item
            label="目前状态"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-radio-group name="radioGroup" :defaultValue="1"
            v-decorator="['current']">
              <a-radio :value="1">完成</a-radio>
              <a-radio :value="2">正在办理</a-radio>
              <a-radio :value="3">无法办理</a-radio>
              <a-radio :value="4">未办理</a-radio>
              <a-radio :value="5">不用办理</a-radio>
            </a-radio-group>
          </a-form-item>
          <a-form-item>
            <a-row :gutter="6">
              <a-col
                class="item"
                :xs="{ span: 24 }"
                :sm="{ span: 12 }"
                :xl="{ span: 4 }">
                <a-button
                  type="primary"
                  icon="plus"
                  style="width: 100%"
                  @click="handleCreate()">
                  提交
                </a-button>
              </a-col>
            </a-row>
          </a-form-item>
        </a-form>
      </a-card>
    </div>
  </page-layout>
</template>

<script>
  import PageLayout from '../../components/page/PageLayout'
  import DecisionDetailModal from './components/ImplementDetailModal'
  import AFormItem from "ant-design-vue/es/form/FormItem";
import api from '../../api/implement'
  const columns = [
    {
      title: '办理事项',
      dataIndex: 'matter',
      width: '15%'
    },
    {
      title: '份数',
      dataIndex: 'number',
      width: '9%'
    }, {
      title: '办理部门',
      dataIndex: 'department',
      width: '8%'
    },
    {
      title: '状态',
      dataIndex: 'status',
      width: '10%'
    },
    {
      title: '联系电话',
      dataIndex: 'telephone',
      width: '17%'
    },
    {
      title: '是否缴费',
      dataIndex: 'paid_status',
      width: '15%'
    },
    {
      title: '办理时限',
      dataIndex: 'dealTime',
      width: '15%'
    },
    {
      title: '备注',
      dataIndex: 'note',
      width: '13%'
    },
    {
      title: '下载',
      dataIndex: 'download'
    },
    {
      title: '编辑',
      dataIndex: 'operation',
      scopedSlots: {customRender: 'operation'}
    }
  ]
  const data = []
  for (let i = 0; i < 10; i++) {
    data.push({
      id: i.toString(),
      matter: '项目审批正式文件',
      number: '发改委网站下载2份，需建设单位盖公章',
      department: '发改委',
      status: '完成',
      telephone: '12345678987',
      paid_status: '是de',
      dealTime: '2',
      note: '2'
    })
  }
  export default {
    name: 'DecisionDetail',
    components: {
      AFormItem,
      DecisionDetailModal,
      PageLayout
    },
    data() {
      return {
        modal: {
          record: null,
          visible: false,
          type: '1'
        },
        count: 0,
        columns,
        data:[],
        card:{
          payment:"",
          current:{}
        }
      }
    },
    mounted() {
      this.getData()

    },
    methods: {
     getData(){
        api.getDecisionPayment(9).then(data => {
        this.card = data
      }),
          api.getDecisionDetail(9).then(data => {
        this.data = data
      })
      },
      handleClose() {
        this.modal.type = ''
        this.modal.record = null
        this.modal.visible = false
      },
      handleCreate() {
        this.modal.type = 'create'
        this.modal.record = null
        this.modal.visible = true
      },
      handleEdit(record) {
        this.modal.type = 'edit'
        this.modal.record = record
        this.modal.visible = true
      },
      handleDelete(record) {
        console.log(record)
      }
    },
  }
</script>

<style scoped lang="less">
  .item {
    margin-top: 16px;
    margin-bottom: 16px;
    float: right;
  }

</style>