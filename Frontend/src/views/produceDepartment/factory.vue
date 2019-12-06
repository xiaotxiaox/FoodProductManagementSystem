<template>
  <div>
    <factory-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :id="modal.id"
      v-if="modal.visible"
      @close="handleClose()">
    </factory-modal>
    <a-card style="margin-bottom: 16px" title="工厂管理表">
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
        :dataSource="factoryList"
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
  import FactoryModal from './components/FactoryModal'
  import api from '../../api/produceDepartment'
  import {mapGetters} from 'vuex'
  const columns = [
      {
          title: '工厂编号',
          dataIndex: 'id',
          width: '20%',
          align: 'center'
      },
    {
      title: '工厂名称',
      dataIndex: 'name',
      width: '20%',
      align: 'center'
    }, {
      title: '工厂地址',
      dataIndex: 'address',
      width: '30%',
      align: 'center'
    },
    {
      title: '负责人',
      dataIndex: 'leader',
      width: '10%',
      align: 'center'
    }, {
      title: '负责人联系方式',
      dataIndex: 'leaderPhone',
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
    name: "factory",
    components: {
      FactoryModal
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
        factoryList:[],
      }
    },
    mounted(){
      this.getData()
    },
    methods: {
       ...mapGetters(['projectSelected']),
      getData(){
         api.getFactoryList()
          .then(data => {
            this.factoryList = data
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
        api.deleteFactory(record.id)
          .then(data => {
            this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
          })
      },
    }
  }
</script>

<style scoped>

</style>