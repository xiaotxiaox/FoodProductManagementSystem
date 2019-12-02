<template>
  <div>
    <contract-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :contract_id="modal.contract_id"
      v-if="modal.visible"
      @close="handleClose()">
    </contract-modal>
    <a-card style="margin-bottom: 16px">
      <a-row>
        <a-col
          class="item"
          :xs="{ span: 24 }"
          :sm="{ span: 12 }"
          :xl="{ span: 4 }">
          <a-button
            type="primary"
            icon="plus"
            style="width: 100%;float:right;margin-bottom: 16px"
            @click="handleCreate()">
            新建
          </a-button>
        </a-col>
      </a-row>
      <!--<a-spin :spinning="status.listLoading">-->
      <a-table
        bordered
        :columns="columns"
        :dataSource="contractList"
        rowKey="contract_id"
        :pagination="false">
        <template slot="project_change" slot-scope="text, record, index">
          <a-button type="primary" @click="$router.push({'name': 'ChangeDetail', params: {id: record.id}})">
            工程变更
          </a-button>
        </template>
         <template slot="review" slot-scope="text, record, index">
          <a-button type="primary" @click="$router.push({'name': 'ReviewDetail', params: {id: record.id}})">
            延期审查
          </a-button>
        </template>
         <template slot="project_contract" slot-scope="text, record, index">
          <a-button type="primary" @click="$router.push({'name': 'ContractDetail', params: {id: record.id}})">
            工程合同
          </a-button>
        </template>
        <template slot="operation" slot-scope="text, record, index">
          <a-button @click="handleEdit(record)">编辑</a-button>
          <a-popconfirm
            title="确认删除吗?"
            @confirm="handleDelete(record)">
            <a-button type="danger">删除</a-button>
          </a-popconfirm>
        </template>
      </a-table>
      <!--</a-spin>-->
    </a-card>
  </div>
</template>

<script>
  import ContractModal from './components/ContractModal'
  import api from '../../../api/implement'
  import {mapGetters} from 'vuex'
  const columns = [
    {
      title: '乙方',
      dataIndex: 'second_party',
      width: '10%'
    }, {
      title: '项目名称',
      dataIndex: 'entry_name',
      width: '20%',
      align: 'center'
    },
    {
      title: '付款方式',
      dataIndex: 'payment_method',
      width: '10%',
      align: 'center'
    }, {
      title: '价款调整',
      dataIndex: 'price_adjustment',
      width: '10%',
      align: 'center'
    }, {
      title: '风险条件',
      dataIndex: 'risk_conditions',
      width: '10%',
      align: 'center'
    }, {
      title: '履约状况',
      dataIndex: 'performance_status',
      width: '10%',
      align: 'center'
    }, {
      title: '工程变更',
      dataIndex: 'project_change',
      scopedSlots: {customRender: 'project_change'},
      width: '10%',
      align: 'center'
    },{
      title: '延期审查',
      dataIndex: 'review',
      scopedSlots: {customRender: 'review'},
      width: '10%',
      align: 'center'
    },{
      title: '工程合同',
      dataIndex: 'project_contract',
      scopedSlots: {customRender: 'project_contract'},
      width: '10%',
      align: 'center'
    },
    {
      title: '编辑',
      dataIndex: 'operation',
      scopedSlots: {customRender: 'operation'}
    }
  ]
  export default {
    name: "Contract",
    components: {
      ContractModal
    },
    props: {
      id: Number
    },
    data() {
      return {
        status: {
          listLoading: true,
          tableLoading: true
        },
        modal: {
          record: null,
          visible: false,
          type: '1',
          contract_id: this.id
        },
        columns,
        contractList:[],
        project_id: this.projectSelected().id
      }
    },
    mounted(){
      this.getData()
    },
    methods: {
       ...mapGetters(['projectSelected']),
      getData(){
         api.getContractList(this.project_id)
          .then(data => {
            this.contractList = data
            this.status.listLoading = false
            console.log(1)
          })
      },
      handleClose() {
        this.modal.type = ''
        this.modal.record = null
        this.modal.visible = false
        this.getData()
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
        api.deleteContract(record.id)
          .then(data => {
            this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
          })
      },
    }
  }
</script>

<style scoped>

</style>