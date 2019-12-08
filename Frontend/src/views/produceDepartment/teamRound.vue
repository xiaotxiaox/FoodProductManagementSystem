<template>
  <div>
    <a-card style="margin-bottom: 16px" title="批次任务表">
      <a-table
        bordered
        :columns="columns"
        :dataSource="teamRoundList"
        rowKey="id"
        :pagination="false">
        <template slot="select" slot-scope="text, record, index">
          <a-button @click="$router.push({'name': 'peiliao', params: {id: record.id}})">查看配料表</a-button>
        </template>
        <template slot="finish" slot-scope="text, record, index">
          <a-popconfirm
            title="确认结束任务吗?"
            @confirm="handleFinish(record)">
            <a-button  type="primary">完成</a-button>
          </a-popconfirm>
        </template>
      </a-table>
    </a-card>
  </div>
</template>

<script>
    import api from '../../api/produceDepartment'
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
            title: '开始生产时间',
            dataIndex: 'productionPlan.timeproduce',
            width: '10%',
            align: 'center'
        },{
            title: '计划完成时间',
            dataIndex: 'productionPlan.timelastest',
            width: '10%',
            align: 'center'
        },{
            title: '处理人',
            dataIndex: 'user.name',
            width: '10%',
            align: 'center'
        },
        {
            title: '配料表',
            dataIndex: 'select',
            align: 'center',
            scopedSlots: {customRender: 'select'}
        },
        {
            title: '操作',
            dataIndex: 'finish',
            align: 'center',
            scopedSlots: {customRender: 'finish'}
        }
    ]
    export default {
        name: "teamRound",
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
                teamRoundList:[],
            }
        },
        mounted(){
            this.getData()
        },
        methods: {
            getData(){
                api.getTeamRoundList(this.id)
                    .then(data => {
                        this.teamRoundList = data
                    })
            },
            handleFinish(record){
                api.changeState(record.id,1)
                    .then(data => {
                        console.log(record.id)
                    })
                this.getData()
            }

        }
    }
</script>

<style scoped>

</style>