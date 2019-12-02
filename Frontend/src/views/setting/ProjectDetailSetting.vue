<template>
<div>
    <fund-modal
      :record="fundModal.record"
      :visible="fundModal.visible"
      :type="fundModal.type"
      :fund_id="fundModal.fund_id"
      v-if="fundModal.visible"
      @close="handleFundClose()">
    </fund-modal>
  <address-modal
      :record="addressModal.record"
      :visible="addressModal.visible"
      :type="addressModal.type"
      :fund_id="addressModal.address_id"
      v-if="addressModal.visible"
      @close="handleAddressClose()">
    </address-modal>
    <a-card
      title="项目资金来源"
      style="margin-bottom: 16px">
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
            @click="handleFundCreate()">
            新建
          </a-button>
        </a-col>
      </a-row>
      <!--<a-spin :spinning="status.listLoading">-->
      <a-table
        bordered
        :columns="columns"
        :dataSource="fundList"
        rowKey="_id"
        :pagination="false">
        <template slot="operation" slot-scope="text, record, index">
          <a-button @click="handleFundEdit(record)">编辑</a-button>
          <a-popconfirm
            title="确认删除吗?"
            @confirm="handleFundDelete(record)">
            <a-button type="danger">删除</a-button>
          </a-popconfirm>
        </template>
      </a-table>
      <!--</a-spin>-->
    </a-card>
  <a-card
      title="通讯录"
      style="margin-bottom: 16px">
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
            @click="handleAddressCreate()">
            新建
          </a-button>
        </a-col>
      </a-row>
      <!--<a-spin :spinning="status.listLoading">-->
      <a-table
        bordered
        :columns="columns"
        :dataSource="addressList"
        rowKey="address_id"
        :pagination="false">
        <template slot="operation" slot-scope="text, record, index">
          <a-button @click="handleAddressEdit(record)">编辑</a-button>
          <a-popconfirm
            title="确认删除吗?"
            @confirm="handleAddressDelete(record)">
            <a-button type="danger">删除</a-button>
          </a-popconfirm>
        </template>
      </a-table>
      <!--</a-spin>-->
    </a-card>
  </div>
</template>

<script>
  import FundModal from './components/FundModal'
  import AddressModal from './components/AddressModal'
  import api from '../../api/setting'
  import {mapGetters} from 'vuex'
  const columns = [
    {
      title: '名称',
      dataIndex: 'label',
      align: 'center',
      width: '30%'
    }, {
      title: '描述',
      dataIndex: 'description',
      width: '50%',
      align: 'center'
    },
    {
      title: '编辑',
      dataIndex: 'operation',
      scopedSlots: {customRender: 'operation'}
    }
  ]
  export default {
    name: "ProjectDetailSetting",
    components: {
      FundModal,
      AddressModal
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
        fundModal: {
          record: null,
          visible: false,
          type: '1',
          fund_id: this.id
        },
        addressModal: {
          record: null,
          visible: false,
          type: '1',
          address_id: this.id
        },
        columns,
        fundList:[],
        addressList:[],
      }
    },
    mounted(){
      this.getData()
    },
    methods: {
      getData(){
         api.getFundList()
          .then(data => {
            console.log(data)
            this.fundList = data
            this.status.listLoading = false
          })
        api.getAddressList()
          .then(data => {
            this.addressList = data
            this.status.listLoading = false
          })
      },
      handleFundClose() {
        this.fundModal.type = ''
        this.fundModal.record = null
        this.fundModal.visible = false
        this.getData()
      },
      handleAddressClose() {
        this.addressModal.type = ''
        this.addressModal.record = null
        this.addressModal.visible = false
        this.getData()
      },
      handleFundCreate() {
        this.fundModal.type = 'create'
        this.fundModal.record = null
        this.fundModal.visible = true
      },
      handleAddressCreate() {
        this.addressModal.type = 'create'
        this.addressModal.record = null
        this.addressModal.visible = true
      },
      handleFundEdit(record) {
        this.fundModal.type = 'edit'
        this.fundModal.record = record
        this.fundModal.visible = true
      },
      handleAddressEdit(record) {
        this.addressModal.type = 'edit'
        this.addressModal.record = record
        this.addressModal.visible = true
      },
      handleFundDelete(record) {
        api.deleteFund(record.value)
          .then(data => {
            this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
          })
      },
      handleAddressDelete(record) {
        api.deleteAddress(record.value)
          .then(data => {
            this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
          })
      }
    }
  }
</script>

<style scoped>

</style>