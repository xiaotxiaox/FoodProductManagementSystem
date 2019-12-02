<template>
  <div>
    <project-modal
      :record="projectModal.record"
      :visible="projectModal.visible"
      :type="projectModal.type"
      :id="projectModal.id"
      v-if="projectModal.visible"
      @close="handleProjectClose()">
    </project-modal>
    <a-card style="margin-bottom: 16px">
      <a-table
        bordered
        :columns="columns"
        :dataSource="projectList"
        rowKey="id"
        :pagination="false">
        <template slot="operation" slot-scope="text, record, index">
          <a-button @click="handleProjectEdit(record)">编辑</a-button>
        </template>
      </a-table>
    </a-card>
  </div>
</template>

<script>
  import ProjectModal from './components/projectModal'
  import api from '../../api/setting'
  import {mapGetters} from 'vuex'

  const columns = [
    {
      title: '项目名称',
      dataIndex: 'name',
      width: '60%',
      align: 'center'
    },
    {
      title: '可操作用户',
      dataIndex: 'user',
      customRender: (text, record) => {
      var s=''
        for (var temp of record.user){
          s=s+temp.username+' '
        }
        return s
      },
      width: '20%',
      align: 'center'
    },
    {
      title: '编辑',
      dataIndex: 'operation',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ]
  export default {
    name: "projectSettings",
    components: {
      ProjectModal
    },
    props: {
      id: Number
    },
    data() {
      return {
        projectModal: {
          record: null,
          visible: false,
          type: '1',
          id: this.id
        },
        columns,
        projectList:[],
        form: this.$form.createForm(this),
      }
    },
    mounted() {
      this.getData()
    },
    methods: {
      ...mapGetters(['projectSelected']),
      getData() {
        api.getProjectList()
          .then(data => {
            console.log(data)
            this.projectList = data
          })
      },
      handleProjectClose() {
        this.projectModal.type = ''
        this.projectModal.record = null
        this.projectModal.visible = false
        this.getData()
      },
      handleProjectEdit(record) {
        this.projectModal.type = 'edit'
        this.projectModal.record = record
        this.projectModal.visible = true
        console.log(this.projectModal.record)
      },
    }
  }
</script>

<style scoped>

</style>