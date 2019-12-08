<template>
  <div>
    <produce-plan-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :id="modal.id"
      v-if="modal.visible"
      @close="handleClose()">
    </produce-plan-modal>
    <a-card style="margin-bottom: 16px" title="生产计划管理表">
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
            新建生产计划
          </a-button>
        </a-col>
      </a-row>
      <a-table
        bordered
        :columns="columns"
        :dataSource="planList"
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
        <template slot="ingredient" slot-scope="text, record, index">
          <a-button @click="$router.push({'name': 'producePlanRound', params: {id: record.id}})">分配批次</a-button>
        </template>
      </a-table>
    </a-card>
  </div>
</template>

<script>
    import ProducePlanModal from './components/ProducePlanModal'
    import api from '../../api/plan'
    import moment from 'moment'
    const columns = [
        {
            title: '生产计划编号',
            dataIndex: 'id',
            width: '10%',
            align: 'center'
        },
        {
            title: '商品名称',
            dataIndex: 'goods.name',
            width: '10%',
            align: 'center'
        }, {
            title: '计划生产数量',
            dataIndex: 'neednum',
            width: '10%',
            align: 'center'
        },{
            title: '计量单位',
            dataIndex: 'goods.unit',
            width: '10%',
            align: 'center'
        },{
            title: '创建计划时间',
            dataIndex: 'timecreate',
            width: '15%',
            align: 'center'
        },
        {
            title: '开始生产时间',
            dataIndex: 'timeproduce',
            width: '15%',
            align: 'center'
        },
        {
            title: '计划结束时间',
            dataIndex: 'timelastest',
            width: '15%',
            align: 'center'
        },
        {
            title: '处理人',
            dataIndex: 'user.name',
            width: '10%',
            align: 'center'
        },
        {
            title: '编辑',
            dataIndex: 'ingredient',
            align: 'center',
            scopedSlots: {customRender: 'ingredient'}
        },
        {
            title: '编辑',
            dataIndex: 'operation',
            align: 'center',
            scopedSlots: {customRender: 'operation'}
        }
    ]
    export default {
        name: "producePlan",
        components: {
            ProducePlanModal
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
                    id: this.id
                },
                columns,
                planList:[],
            }
        },
        mounted(){
            this.getData()
        },
        methods: {
            getData(){
                api.getPlanList()
                    .then(data => {
                        data.timeCreate = new moment(data.timeCreate)
                        data.timelastest = new moment(data.timelastest)
                        data.timeproduce = new moment(data.timeproduce)
                        this.planList = data
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
                api.deletePlan(record.id)
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