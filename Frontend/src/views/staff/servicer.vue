<template>
  <div>
    <servicer-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :id="modal.id"
      v-if="modal.visible"
      @close="handleClose()">
    </servicer-modal>
    <a-card style="margin-bottom: 16px" title="员工信息表">
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
        :dataSource="staffList"
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
    import ServicerModal from './components/ServicerModal'
    import api from '../../api/staff'
    import moment from 'moment'
    import {mapGetters} from 'vuex'

    const columns = [
        {
            title: '员工编号',
            dataIndex: 'id',
            width: '20%',
            align: 'center'
        }, {
            title: '姓名',
            dataIndex: 'name',
            width: '20%',
            align: 'center'
        }, {
            title: '性别',
            dataIndex: 'gender',
            width: '20%',
            align: 'center',
            customRender: (text, record) => {
                if (record.gender === 1)
                    return '男'
                else if (record.gender === 2)
                    return '女'
            },
        }, {
            title: '工作部门',
            dataIndex: 'department',
            width: '10%',
            align: 'center',
            customRender: (text, record) => {
                if (record.department === 1)
                    return "销售部"
                else if (record.department === 2)
                    return '财务部'
                else if (record.department === 3)
                    return '成品库部门'
                else if (record.department === 4)
                    return '生产计划科'
                else if (record.department === 5)
                    return '生产车间部门'
                else if (record.department === 6)
                    return '原材料库'
                else if (record.department === 7)
                    return '人事部'
            },
        },
        {
            title: '入职时间',
            dataIndex: 'timein',
            width: '10%',
            align: 'center'
        }, {
            title: '工资',
            dataIndex: 'pay',
            width: '10%',
            align: 'center'
        }, {
            title: '电话号码',
            dataIndex: 'phone',
            width: '20%',
            align: 'center'
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
        name: "servicer",
        components: {
            ServicerModal
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
                staffList: [],
            }
        },
        mounted() {
            this.getData()
        },
        methods: {
            ...mapGetters(['projectSelected']),
            getData() {
                api.getServicerList()
                    .then(data => {
                        console.log(data)
                        data.timein = new moment(data.timein)
                        console.log(data)
                        this.staffList = data
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
                api.deleteServicer(record.id)
                    .then(data => {
                        this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
                    })
            },
        }
    }
</script>

<style scoped>

</style>