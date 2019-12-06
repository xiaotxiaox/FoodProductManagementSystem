<template>
  <div>
    <product-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :id="modal.id"
      v-if="modal.visible"
      @close="handleClose()">
    </product-modal>
    <a-card style="margin-bottom: 16px" title="商品原料管理表">
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
        :dataSource="productList"
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
          <a-button @click="$router.push({name: 'productIngredientEdit',id:record.id})">原料管理</a-button>
        </template>
      </a-table>
      <!--</a-spin>-->
    </a-card>
  </div>
</template>

<script>
    import ProductModal from './components/ProductModal'
    import api from '../../api/plan'
    const columns = [
        {
            title: '商品编号',
            dataIndex: 'id',
            width: '20%',
            align: 'center'
        },
        {
            title: '商品名称',
            dataIndex: 'name',
            width: '20%',
            align: 'center'
        }, {
            title: '商品单价',
            dataIndex: 'price',
            width: '30%',
            align: 'center'
        },
        {
            title: '计量单位',
            dataIndex: 'unit',
            width: '10%',
            align: 'center'
        },
        {
            title: '原料管理',
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
        name: "productIngredient",
        components: {
            ProductModal
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
                productList:[],
            }
        },
        mounted(){
            this.getData()
        },
        methods: {
            getData(){
                api.getProductList()
                    .then(data => {
                        console.log(data)
                        this.productList = data
                        this.status.listLoading = false
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
                api.deleteProduct(record.id)
                    .then(data => {
                        this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
                    })
            },
        }
    }
</script>

<style scoped>

</style>