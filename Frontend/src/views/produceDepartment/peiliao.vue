<template>
  <div>
    <a-card style="margin-bottom: 16px" title="批次任务表">
      <a-table
        bordered
        :columns="columns"
        :dataSource="roundIngredientList"
        rowKey="id"
        :pagination="false">
      </a-table>
    </a-card>
  </div>
</template>

<script>
    import api from '../../api/produceDepartment'
    const columns = [
        {
            title: '原料编号',
            dataIndex: 'material.id',
            width: '5%',
            align: 'center'
        },
        {
            title: '原料名称',
            dataIndex: 'material.name',
            width: '20%',
            align: 'center'
        }, {
            title: '所需数量',
            dataIndex: 'count',
            width: '10%',
            align: 'center'
        }, {
            title: '计量单位',
            dataIndex: 'material.unit',
            width: '10%',
            align: 'center'
        }
    ]
    export default {
        name: "peiliao",
        props: {
            id: String
        },
        data() {
            return {
                status: {
                    listLoading: true,
                    tableLoading: true
                },
                columns,
                roundIngredientList:[],
            }
        },
        mounted(){
            this.getData()
        },
        methods: {
            getData(){
                api.getRoundIngredientList(this.id)
                    .then(data => {
                        console.log(data)
                        this.roundIngredientList = data
                    })
            },

        }
    }
</script>

<style scoped>

</style>