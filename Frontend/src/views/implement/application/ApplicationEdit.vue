<template>
  <div>
    <application-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      v-if="modal.visible"
      @close="handleClose()">
    </application-modal>
    <a-card>
      <a-input-group class="tool">
        <a-col
          class="item"
          :xs="{ span: 12, offset: 12 }"
          :sm="{ span: 8, offset: 16 }"
          :xl="{ span: 4, offset: 20 }">
          <a-button
            type="primary"
            icon="plus"
            style="width: 100%"
            @click="handleCreate()">
            新建
          </a-button>
        </a-col>
      </a-input-group>
      <a-table
        :columns="columns"
        :dataSource="applicationList"
        rowKey="application_id"
        :pagination="false">
        <template slot="operation" slot-scope="text, record, index">
          <a-button-group>
          <a-button @click="handleEdit(record)">编辑</a-button>
          <a-popconfirm
            title="确认删除吗?"
            @confirm="handleDelete(record)">
            <a-button type="danger">删除</a-button>
          </a-popconfirm>
          </a-button-group>
        </template>
      </a-table>
      <a-input-group class="tool">
        <a-button
          style="margin-top: 16px;float:right"
          @click="$router.go(-1)">
          返回
        </a-button>
      </a-input-group>
    </a-card>
  </div>
</template>

<script>
  const columns = [
    {
      title: '编号',
      dataIndex: 'application_id',
      width: '40%'
    },
    {
      title: '名称',
      dataIndex: 'title',
      width: '40%'
    },
    {
      title: '编辑',
      dataIndex: 'operation',
      width: '20%',
      scopedSlots: { customRender: 'operation' }
    }
  ]
  import ApplicationModal from './components/ApplicationModal'
  import api from '../../../api/implement'

  export default {
    name: 'ApplicationEdit',
    components: {
      ApplicationModal
    },
    data () {
      return {
        columns,
        applicationList: [],
        modal: {
          record: null,
          visible: false,
          type: '1'
        }
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      getData () {
        api.getApplicationList(this.$store.getters.projectSelected.id)
          .then(data => {
            this.applicationList = data
          })
      },
      handleClose () {
        this.modal.type = ''
        this.modal.record = null
        this.modal.visible = false
        this.getData()
      },
      handleCreate () {
        this.modal.type = 'create'
        this.modal.record = null
        this.modal.visible = true
      },
      handleEdit (record) {
        this.modal.type = 'edit'
        this.modal.record = record
        this.modal.visible = true
      },
      handleDelete (record) {
        api.deleteApplication(record.application_id)
          .then(data=>{
            this.$notification.success({message: '成功', description: '成功删除', key: 'SUCCESS'})
          })
        this.getData()
      }
    }
  }
</script>

<style scoped lang="less">
  .tool {

    .item {
      margin-bottom: 16px;
    }
  }

  .button {
    margin-bottom: 16px;
  }
</style>