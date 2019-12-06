<template>
  <div>
    <producing-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :id="modal.id"
      v-if="modal.visible"
      @close="handleClose()">
    </producing-modal>
    <a-card style="margin-bottom: 16px" title="正在生产批次表">
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
        :dataSource="producingList"
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
        <template slot="state" slot-scope="text, record, index">
          <a-button @click="qualified(record)">检验合格</a-button>
          <a-popconfirm
            title="确认销毁改批次产品吗?"
            @confirm="disqualified(record)">
            <a-button type="danger">检验不合格</a-button>
          </a-popconfirm>
        </template>
      </a-table>
      <!--</a-spin>-->
    </a-card>
  </div>
</template>

<script>
    import ProducingModal from './components/ProducingModal'
    import api from '../../api/produceDepartment'

    const columns = [
        {
            title: '流水编号',
            dataIndex: 'id',
            width: '20%',
            align: 'center'
        },
        {
            title: '对应批次编号',
            dataIndex: 'round.id',
            width: '20%',
            align: 'center'
        },
        {
            title: '对应批次名称',
            dataIndex: 'round.name',
            width: '20%',
            align: 'center'
        },
        {
            title: '商品名称',
            dataIndex: 'good.name',
            width: '20%',
            align: 'center'
        }, {
            title: '商品数量',
            dataIndex: 'goodCount',
            width: '20%',
            align: 'center'
        }, {
            title: '生产日期',
            dataIndex: 'produceDate',
            width: '30%',
            align: 'center'
        },
        {
            title: '处理人',
            dataIndex: 'user.name',
            width: '10%',
            align: 'center'
        },
        {
            title: '检验',
            dataIndex: 'state',
            align: 'center',
            scopedSlots: {customRender: 'state'}
        },
        // {
        //   title: '编辑',
        //   dataIndex: 'operation',
        //   align: 'center',
        //   scopedSlots: {customRender: 'operation'}
        // }
    ]
    export default {
        name: "producing",
        components: {
            ProducingModal
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
                    id: this.id
                },
                columns,
                producingList: [],
            }
        },
        mounted() {
            this.getData()
        },
        methods: {
            getData() {
                api.getProducingList()
                    .then(data => {
                        console.log(data)
                        this.producingList = data
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
            qualified(record) {
                let item={}
                item.id=record.id
                item.state = 1
                item.good=record.good.id
                item.round=record.round.id
                item.goodCount=record.goodCount
                item.produceDate=record.produceDate
                item.handeler=record.handler
                api.updateProducing(record.id, item)
                    .then(data => {
                        this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                        this.$emit('close')
                    })
                this.getData()
            },
            disqualified(record) {
                let item={}
                item.id=record.id
                item.state = 2
                item.good=record.good.id
                item.round=record.round.id
                item.goodCount=record.goodCount
                item.produceDate=record.produceDate
                item.handeler=record.handler
                api.updateProducing(record.id, item)
                    .then(data => {
                        this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                        this.$emit('close')
                    })
                this.getData()
            },
            handleDelete(record) {
                api.deleteProducing(record.id)
                    .then(data => {
                        this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
                    })
                this.getData()
            },
        }
    }
</script>

<style scoped>

</style>