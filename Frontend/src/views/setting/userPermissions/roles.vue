<template>
     <div>
       <roles-modal
        :record="modal.record"
         :visible="modal.visible"
         :type="modal.type"
         :users_id="modal.roles_id"
         v-if="modal.visible"
         @close="handleClose()">

       </roles-modal>
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
        :dataSource="settingRolesList"
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
   const columns = [
    {
      title:'序号',
      dataIndex:'id',
      width:'7%',
      align:'center'
    },
    {
      title:'角色名',
      dataIndex:'name',
      width:'10%',
      align:'center'
    },
    {
      title:'角色描述',
      dataIndex:'description',
      width:'17%',
      align:'center'
    },
     {
      title:'权限说明',
      dataIndex:'permissions',
      width:'35%',
      align:'center',
    customRender: (text, record) => {
      var s=''
        for (var temp of record.permissions) {
          s = s + temp.description + ' '
        }
        return s
      }
    },
     {
      title: '编辑',
      dataIndex: 'operation',
      scopedSlots: { customRender: 'operation' },
      align: 'center'
    },

  ]
  import rolesModal from './components/rolesModal'
  import api from '../../../api/setting'
  export default {
    name: 'roles',
    components:{
      rolesModal
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
        form: this.$form.createForm(this),
        roles: {},
        modal: {
          record: null,
          visible: false,
          type: '1',
          roles_id: this.id
        },
        columns,
        settingRolesList: []
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      getData () {
        api.getSettingRolesMatterList()
          .then(data => {
          this.settingRolesList = data.sort((x, y) => {
            return x.id > y.id
          })
            // console.log(this.settingRolesList)
            // for (var i in this.settingRolesList){
            //   var s=''
            //   for (var temp of i.permissions) {
            //     s = s + temp.description + '<br>'
            //   }
            //   i.permissions=s
            // }
        })
        api.getSettingRoles(this.id)
          .then(data => {
            this.roles = data
          })
      },
      handleSubmit () {
        this.form.validateFields((error, data) => {
          if (!error) {
            api.updateSettingRoles(this.id, data)
              .then(data => {
                this.$notification.success({ message: '成功', description: '修改成功', key: 'SUCCESS' })
              })
          }
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
        api.deleteSettingRolesMatter(record.id)
          .then(data => {
            this.$notification.success({ message: '成功', description: '删除成功', key: 'SUCCESS' })
            this.getData()
          })
      },
      parseFloat (f) {
        return parseFloat(f)
      }
    },
     computed: {
       isEdit () {
         return this.type === 'edit'
       },
       title () {
         return this.isEdit ? '编辑' : '新建'
       }
     }
  }
</script>

<style scoped>

</style>