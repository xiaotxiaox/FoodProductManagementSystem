<template>
  <div>
    <users-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      :users_id="modal.users_id"
      v-if="modal.visible"
      @close="handleClose1()">
    </users-modal>
    <update-psw-modal
      :record="pswModal.record"
      :visible="pswModal.visible"
      :type="pswModal.type"
      :users_id="pswModal.users_id"
      v-if="pswModal.visible"
      @close="handleClose2()">
    </update-psw-modal>
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
        :dataSource="settingUsersList"
        rowKey="id"
        :pagination="false">
        <template slot="operation" slot-scope="text, record, index">
          <a-button @click="handleEdit(record)">编辑</a-button>
          <a-popconfirm
            title="确认删除吗?"
            @confirm="handleDelete(record)">
            <a-button type="danger">删除</a-button>
          </a-popconfirm>
          <a-button @click="updatePassword(record)">修改密码</a-button>
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
            width:'8%',
            align:'center'
        },
        {
            title:'用户名',
            dataIndex:'name',
            width:'10%',
            align:'center'
        },
        {
            title:'角色',
            dataIndex:'role.name',
            width:'10%',
            align:'center'
        },
        {
            title:'电子邮箱地址',
            dataIndex:'email',
            width:'17%',
            align:'center'
        },
        {
            title: '编辑',
            dataIndex: 'operation',
            scopedSlots: { customRender: 'operation' },
            align: 'center'
        },

    ]
    import UsersModal from './components/UsersModal'
    import updatePswModal from './components/updatePswModal'
    import api from '../../api/setting'

    export default {
        name: 'addUser',
        components: {
            UsersModal,
            updatePswModal
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
                users: {},
                modal: {
                    record: null,
                    visible: false,
                    type: '1',
                    users_id: this.id
                },
                pswModal: {
                    record: null,
                    visible: false,
                    type: '1',
                    users_id: this.id
                },
                rolesList:[],
                columns,
                settingUsersList: []
            }
        },
        mounted () {
            api.getSettingRoles().then(data => {
                console.log(data)
                this.rolesList = data
            })
            this.getData()
        },
        methods: {
            getData () {
                api.getSettingUsers(this.id)
                    .then(data => {
                        console.log(1111111)
                        console.log(data)
                        this.settingUsersList = data
                    })
            },
            handleClose1 () {
                this.modal.type = ''
                this.modal.record = null
                this.modal.visible = false
                this.getData()
            },
            handleClose2 () {
                this.pswModal.type = ''
                this.pswModal.record = null
                this.pswModal.visible = false
                this.getData()
            },
            handleCreate () {
                this.modal.type = 'create'
                this.modal.record = null
                this.modal.visible = true
                console.log(this.modal.record)
            },
            handleEdit (record) {
                this.modal.type = 'edit'
                this.modal.record = record
                this.modal.visible = true
            },
            handleDelete (record) {
                api.deleteSettingUsersMatter(record.id)
                    .then(data => {
                        this.$notification.success({ message: '成功', description: '删除成功', key: 'SUCCESS' })
                        this.getData()
                    })
            },
            updatePassword(record){
                this.pswModal.type = 'edit'
                this.pswModal.record = record
                this.pswModal.visible = true
            },
            parseFloat (f) {
                return parseFloat(f)
            }
        }
    }
</script>

<style scoped lang="less">
  .item {
    margin: 10px 0;
  }

</style>