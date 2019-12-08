<template>
  <div>
    <out-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :id="modal.id"
      v-if="modal.visible"
      @close="handleClose()">
    </out-modal>
    <a-card style="margin-bottom: 16px" title="原料出库表">
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
        :dataSource="outList"
        :scroll="{ x: 1300 }"
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
    import OutModal from './components/OutModal'
    import api from '../../api/ingredient'
    import moment from 'moment'

    const columns = [
        {
            title: '出库编号',
            dataIndex: 'id',
            width: '20%',
            align: 'center'
        }, {
            title: '原料名称',
            dataIndex: 'materialtotal.name',
            width: '20%',
            align: 'center'
        },
        {
            title: '出库数量',
            dataIndex: 'num',
            width: '10%',
            align: 'center'
        },  {
            title: '出库日期',
            dataIndex: 'timeout',
            width: '10%',
            align: 'center'
        },
        {
            title: '处理人',
            dataIndex: 'user.name',
            width: '20%',
            align: 'center'
        },
        // {
        //     title: '编辑',
        //     dataIndex: 'operation',
        //     align: 'center',
        //     scopedSlots: {customRender: 'operation'}
        // }
    ]
    export default {
        name: "out",
        components: {
            OutModal
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
                outList: [],
            }
        },
        mounted() {
            this.getData()
        },
        methods: {
            getData() {
                api.getOutList()
                    .then(data => {
                        data.timeout = new moment(data.timeout)
                        this.outList = data
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
                api.deleteInbound(record.id)
                    .then(data => {
                        this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
                    })
            },
        }
    }
</script>

<style scoped>

</style>