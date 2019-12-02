<template>
    <div>
    <qualification-modal
    :record="modal.record"
    :visible="modal.visible"
    :type="modal.type"
    :qualification_id="modal.qualification_id"
    v-if="modal.visible"
    @close="handleClose()">
    </qualification-modal>
    <page-layout title="资质审查">
      <a-card style="margin-bottom: 16px">
        <a-row class="item">
          <a-col
            :xs="{ span: 24 }"
            :sm="{ span: 12 }"
            :xl="{ span: 4 }">
            <a-button
              type="primary"
              icon="plus"
              style="width: 100%"
              @click="handleCreate()">
              新建
            </a-button>
          </a-col>
        </a-row>
        <a-table
          bordered
          :columns="columns"
          :dataSource="qualiList"
          rowKey="id"
          :pagination="false"
          :loading="status.loading">
          <template slot="operation1" slot-scope="text, record, index">
            <a-button @click="$router.push({'name': 'qualificationFiles1', params: {id: record.id}})">查看</a-button>
          </template>
          <template slot="operation2" slot-scope="text, record, index">
            <a-button @click="$router.push({'name': 'qualificationFiles2', params: {id: record.id}})">查看</a-button>
          </template>
          <template slot="operation3" slot-scope="text, record, index">
            <a-button @click="$router.push({'name': 'qualificationFiles3', params: {id: record.id}})">查看</a-button>
          </template>
          <template slot="operation4" slot-scope="text, record, index">
            <a-button @click="$router.push({'name': 'qualificationFiles4', params: {id: record.id}})">查看</a-button>
          </template>
          <template slot="operation5" slot-scope="text, record, index">
            <a-button @click="$router.push({'name': 'qualificationFiles5', params: {id: record.id}})">查看</a-button>
          </template>
          <template slot="operation6" slot-scope="text, record, index">
            <a-button @click="$router.push({'name': 'qualificationFiles6', params: {id: record.id}})">查看</a-button>
          </template>
          <template slot="operation7" slot-scope="text, record, index">
            <a-button @click="handleEdit(record)">编辑</a-button>
            <a-popconfirm
              title="确认删除吗?"
              @confirm="handleDelete(record)">
              <a-button type="danger">删除</a-button>
            </a-popconfirm>
          </template>
        </a-table>
      </a-card>
    </page-layout>
  </div>
</template>

<script>
  const columns = [
    {
      title: '名称',
      dataIndex: 'name',
      width: '10%',
      align: 'center'
    },
    {
      title: '类型',
      dataIndex: 'style',
      width: '12%',
      align: 'center'
    },

     {
      title: '营业执照',
      dataIndex: 'operation1',
      scopedSlots: { customRender: 'operation1' },
      align: 'center'
    },
     {
      title: '资质证书',
      dataIndex: 'operation2',
      scopedSlots: { customRender: 'operation2' },
      align: 'center'
    },

     {
      title: '管理人员',
      dataIndex: 'operation3',
      scopedSlots: { customRender: 'operation3' },
      align: 'center'
    },
     {
      title: '特殊工种',
      dataIndex: 'operation4',
      scopedSlots: { customRender: 'operation4' },
      align: 'center'
    },

     {
      title: '类似经验',
      dataIndex: 'operation5',
      scopedSlots: { customRender: 'operation5' },
      align: 'center'
    },
     {
      title: '安全生产许可证',
      dataIndex: 'operation6',
      scopedSlots: { customRender: 'operation6' },
      align: 'center'
    },
    {
      title: '编辑',
      dataIndex: 'operation7',
      scopedSlots: { customRender: 'operation7' },
      align: 'center'
    }
  ]
  import PageLayout from '../../../components/page/PageLayout'
  import api from '../../../api/InteManagement'
  import QualificationModal from './components/QualificationModal'
  export default {
    name: 'Qualification',
    components: {
      QualificationModal,
       PageLayout
    },
    props: {
      id: Number
    },
    data () {
      return {
        layout: {
          'label-col': { span: 6 },
          'wrapper-col': { span: 18 }
        },
        status:{
          loading:true
        },
        form: this.$form.createForm(this),
        qualifications: {},
        modal: {
          record: null,
          visible: false,
          type: '1',
          qualification_id: this.id
        },
        columns,
        qualiList: []
      }
    },
    mounted () {
      this.getData()
    },
    methods: {

      getData () {
        this.status.loading=true
        api.getQualificationList(this.$store.getters.projectSelected.id)
          .then(data => {
            this.qualiList = data
            this.status.loading=false
          })
        api.getQualification(this.id)
          .then(data => {
            this.qualifications = data
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
        api.deleteQualification(record.id)
          .then(data => {
            this.$notification.success({ message: '成功', description: '删除成功', key: 'SUCCESS' })
            this.getData()
          })
      },
      parseFloat (f) {
        return parseFloat(f)
      }
    }
  }
</script>

<style scoped>

</style>