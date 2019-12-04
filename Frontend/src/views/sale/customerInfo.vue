<template>
  <div>
    <customer-Info-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :customer_id="modal.customer_id"
      v-if="modal.visible"
      @close="handleClose()">
    </customer-Info-modal>
    <a-card style="margin-bottom: 16px" title="客户信息表">
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
        :dataSource="customerInfoList"
        rowKey="customer_id"
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
      <!--</a-spin>-->
    </a-card>
  </div>
</template>

<script>
  import CustomerInfoModal from './components/CustomerInfoModal'
  import api from '../../api/sale'
  import {mapGetters} from 'vuex'
  const columns = [
    {
      title: '客户姓名',
      dataIndex: 'name',
      width: '20%',
      align: 'center'
    }, {
      title: '客户电话',
      dataIndex: 'telephone',
      width: '30%',
      align: 'center'
    },
    {
      title: '客户类型',
      dataIndex: 'type.label',
      width: '10%',
      align: 'center'
    }, {
      title: '客户累计购买量',
      dataIndex: 'customer_amount',
      width: '20%',
      align: 'center'
    },
    {
      title: '处理人',
      dataIndex: 'staff',
      width: '20%',
      align: 'center'
    },
    {
      title: '编辑',
      dataIndex: 'operation',
      align: 'center',
      scopedSlots: {customRender: 'operation'}
    }
  ]
  const customerInfoList=[
    {
      customer_name:'123',
      customer_id: '123'
    }
  ]
  export default {
    name: "customerInfo",
    components: {
      CustomerInfoModal
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
          customer_id: this.id
        },
        columns,
        customerInfoList,
        // project_id: this.projectSelected().id
      }
    },
    mounted(){
      //this.getData()
    },
    methods: {
       ...mapGetters(['projectSelected']),
      getData(){
         // api.getCustomerInfoList(this.project_id)
         //  .then(data => {
         //    this.customerInfoList = data
         //    this.status.listLoading = false
         //    console.log(1)
         //  })
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
        api.deleteCustomerInfo(record.id)
          .then(data => {
            this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
          })
      },
    }
  }
</script>

<style scoped>

</style>