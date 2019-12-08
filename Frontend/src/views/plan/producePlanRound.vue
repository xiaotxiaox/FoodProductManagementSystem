<template>
  <div>
    <produce-round-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :id="modal.id"
      v-if="modal.visible"
      @close="handleClose()">
    </produce-round-modal>
    <a-card style="margin-bottom: 16px" title="生产计划-批次管理表">
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
            新建批次
          </a-button>
        </a-col>
      </a-row>
      <a-table
        bordered
        :columns="columns"
        :dataSource="planRoundList"
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
  </div>
</template>

<script>
    import ProduceRoundModal from './components/ProduceRoundModal'
    import api from '../../api/plan'
    const columns = [
        {
            title: '批次编号',
            dataIndex: 'id',
            width: '5%',
            align: 'center'
        },
        {
            title: '批次任务说明',
            dataIndex: 'name',
            width: '20%',
            align: 'center'
        }, {
            title: '生产商品',
            dataIndex: 'goods.name',
            width: '10%',
            align: 'center'
        }, {
            title: '生产数量',
            dataIndex: 'count',
            width: '10%',
            align: 'center'
        },{
            title: '计量单位',
            dataIndex: 'goods.unit',
            width: '10%',
            align: 'center'
        },{
            title: '生产班组',
            dataIndex: 'team.name',
            width: '10%',
            align: 'center'
        },{
            title: '处理人',
            dataIndex: 'user.name',
            width: '10%',
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
        name: "producePlanRound",
        components: {
            ProduceRoundModal
        },
        props: {
            id: String
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
                planRoundList:[],
            }
        },
        mounted(){
            this.getData()
        },
        methods: {
            getData(){
                api.getPlanRoundList(this.id)
                    .then(data => {
                        console.log(data)
                        this.planRoundList = data
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
                api.deletePlanRound(record.id)
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