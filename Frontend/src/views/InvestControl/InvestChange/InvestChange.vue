<template>
    <page-layout title="工程变更台账">
      <invest-receive-modal
        :record="modal1.record"
        :visible="modal1.visible"
        :type="modal1.type"
        :receive_id="modal1.receive_id"
        v-if="modal1.visible"
        @close="handleClose1()">
      </invest-receive-modal>
      <invest-give-modal
        :record="modal2.record"
        :visible="modal2.visible"
        :type="modal2.type"
        :give_id="modal2.give_id"
        v-if="modal2.visible"
        @close="handleClose2()">
      </invest-give-modal>
      <a-card style="margin-bottom: 16px">
      <a-row class="item">
        <a-col
          :xs="{span: 24}"
          :sm="{span: 12}"
          :xl="{span: 20}">
          <span style="font-size: 20px; margin-left: 4px">变更领取台账</span>
        </a-col>
        <a-col
          :xs="{ span: 24 }"
          :sm="{ span: 12 }"
          :xl="{ span: 4 }">
          <a-button
            type="primary"
            icon="plus"
            style="width: 100%"
            @click="handleCreate1()">
            新建
          </a-button>
        </a-col>
      </a-row>
      <a-table
        bordered
        :columns="columns1"
        :dataSource="receiveList"
        rowKey="draw_id"
        :pagination="false"
        :loading="status.loading">
        <template slot="operation1" slot-scope="text, record, index">
          <a-button @click="handleEdit1(record)">编辑</a-button>
          <a-popconfirm
            title="确认删除吗?"
            @confirm="handleDelete1(record)">
            <a-button type="danger">删除</a-button>
          </a-popconfirm>
        </template>
      </a-table>
    </a-card>

      <a-card style="margin-bottom: 16px">
        <a-row class="item">
          <a-col
            :xs="{span: 24}"
            :sm="{span: 12}"
            :xl="{span: 20}">
            <span style="font-size: 20px; margin-left: 4px">变更发放台账</span>
          </a-col>
          <a-col
            :xs="{ span: 24 }"
            :sm="{ span: 12 }"
            :xl="{ span: 4 }">
            <a-button
              type="primary"
              icon="plus"
              style="width: 100%"
              @click="handleCreate2()">
              新建
            </a-button>
          </a-col>
        </a-row>
         <a-table
        bordered
        :columns="columns2"
        :dataSource="giveList"
        rowKey="provide_id"
        :pagination="false"
        :loading="status.loading">
        <template slot="operation2" slot-scope="text, record, index">
          <a-button @click="handleEdit2(record)">编辑</a-button>
          <a-popconfirm
            title="确认删除吗?"
            @confirm="handleDelete2(record)">
            <a-button type="danger">删除</a-button>
          </a-popconfirm>
        </template>
      </a-table>
      </a-card>
    </page-layout>
</template>

<script>
  const columns1=[
    {
      title: '变更名称',
      dataIndex: 'draw_name',
      width: '12%',
      align: 'center'
    },
    {
      title: '领取单位',
      dataIndex: 'draw_company',
      width: '12%',
      align: 'center'
    },
    {
      title: '份数',
      dataIndex: 'draw_amount',
      width: '12%',
      align: 'center'
    },
    {
      title: '领取时间',
      dataIndex: 'draw_time',
      width: '12%',
      align: 'center'
    },
    {
      title: '备注',
      dataIndex: 'draw_remark',
      width: '12%',
      align: 'center'
    },
     {
      title: '编辑',
      dataIndex: 'operation1',
      scopedSlots: { customRender: 'operation1' },
      align: 'center'
    }

  ]
    const columns2=[
    {
      title: '变更名称',
      dataIndex: 'provide_name',
      width: '12%',
      align: 'center'
    },
    {
      title: '领取单位',
      dataIndex: 'provide_company',
      width: '12%',
      align: 'center'
    },
    {
      title: '份数',
      dataIndex: 'provide_amount',
      width: '12%',
      align: 'center'
    },
    {
      title: '领取时间',
      dataIndex: 'provide_time',
      width: '12%',
      align: 'center'
    },
    {
      title: '备注',
      dataIndex: 'provide_remark',
      width: '12%',
      align: 'center'
    },
     {
      title: '编辑',
      dataIndex: 'operation2',
      scopedSlots: { customRender: 'operation2' },
      align: 'center'
    }

  ]
   import PageLayout from '../../../components/page/PageLayout'
   import investReceiveModal from './components/investReceiveModal'
   import investGiveModal from './components/investGiveModal'
   import api from '../../../api/investControl'

  export default {
    name: 'InvestChange',
    components: {
      investReceiveModal,
      investGiveModal,
      PageLayout
    },
    props: {
      draw_id: Number,
      provide_id:Number
    },
    data () {
      return {
        layout: {
          'label-col': { span: 6 },
          'wrapper-col': { span: 18 }
        },
        status:{
          loading:true
        },
        form: this.$form.createForm(this),
        receives: {},
        gives:{},
        modal1: {
          record: null,
          visible: false,
          type: '1',
          receive_id: this.draw_id,
        },
         modal2: {
          record: null,
          visible: false,
          type: '1',
          give_id: this.provide_id,
        },
        columns1,
        columns2,
        receiveList: [],
        giveList:[]
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      getData () {
        this.status.loading=true
        api.getInvestReceiveList(this.$store.getters.projectSelected.id)
          .then(data => {
            this.receiveList = data
            this.status.loading=false
          })
         api.getInvestGiveList(this.$store.getters.projectSelected.id)
          .then(data => {
            this.giveList = data
            this.status.loading=false
          })
        api.getInvestGive(this.provide_id)
          .then(data => {
            this.gives = data
          })
        api.getInvestReceive(this.draw_id)
          .then(data => {
            this.receives = data
          })
      },
      handleClose1 () {
        this.modal1.type = ''
        this.modal1.record = null
        this.modal1.visible = false
        this.getData()
      },
      handleClose2 () {
        this.modal2.type = ''
        this.modal2.record = null
        this.modal2.visible = false
        this.getData()
      },
      handleCreate1 () {
        this.modal1.type = 'create'
        this.modal1.record = null
        this.modal1.visible = true
      },
      handleCreate2 () {
        this.modal2.type = 'create'
        this.modal2.record = null
        this.modal2.visible = true
      },
      handleEdit1 (record) {
        this.modal1.type = 'edit'
        this.modal1.record = record
        this.modal1.visible = true
      },
      handleEdit2 (record) {
        this.modal2.type = 'edit'
        this.modal2.record = record
        this.modal2.visible = true
      },
      handleDelete1 (record) {
        api.deleteInvestReceive(record.draw_id)
          .then(data => {
            this.$notification.success({ message: '成功', description: '删除成功', key: 'SUCCESS' })
            this.getData()
          })
      },
       handleDelete2 (record) {
        api.deleteInvestGive(record.provide_id)
          .then(data => {
            this.$notification.success({ message: '成功', description: '删除成功', key: 'SUCCESS' })
            this.getData()
          })
      },
      parseFloat (f) {
        return parseFloat(f)
      }
    }

  }
</script>

<style scoped>

</style>