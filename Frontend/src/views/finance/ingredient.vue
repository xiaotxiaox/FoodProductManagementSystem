<template>
  <div>
    <in-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :id="modal.id"
      v-if="modal.visible"
      @close="handleClose()">
    </in-modal>
    <a-card style="margin-bottom: 16px" title="原料入库申请表">
      <!--<a-spin :spinning="status.listLoading">-->
      <a-table
        bordered
        :columns="columns"
        :dataSource="inList"
        :scroll="{ x: 1300 }"
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
      <!--</a-spin>-->
    </a-card>
  </div>
</template>

<script>
    import InModal from './components/InModal'
    import api from '../../api/ingredient'
    import api1 from '../../api/finance'
    import moment from 'moment'

    const columns = [
        {
            title: '入库编号',
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
            title: '采购数量',
            dataIndex: 'num',
            width: '10%',
            align: 'center'
        }, {
            title: '总计花费',
            dataIndex: 'totalPrice',
            width: '10%',
            align: 'center'
        }, {
            title: '申请日期',
            dataIndex: 'timeApply',
            width: '10%',
            align: 'center'
        }, {
            title: '进货日期',
            dataIndex: 'timeHandle',
            width: '20%',
            align: 'center'
        },{
            title: '保质期',
            dataIndex: 'timeprotect',
            width: '20%',
            align: 'center'
        },
        {
            title: '申请状态',
            dataIndex: 'state',
            width: '10%',
            align: 'center',
            customRender: (text, record) => {
                if (record.state === 1)
                    return "申请中"
                else if (record.state === 2)
                    return '已同意'
                else if (record.state === 3)
                    return '未同意'
            },
        },
        {
            title: '处理人',
            dataIndex: 'user.name',
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
    export default {
        name: "ingredient",
        components: {
            InModal
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
                inList: [],
            }
        },
        mounted() {
            this.getData()
        },
        methods: {
            getData() {
                api1.getInList()
                    .then(data => {
                        data.timeApply = new moment(data.timeApply)
                        data.timeHandle = new moment(data.timeHandle)
                        data.timeprotect = new moment(data.timeprotect)
                        this.inList = data
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