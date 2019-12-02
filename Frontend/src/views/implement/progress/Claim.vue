<template>
  <div>
    <claim-modal
      :record="claimModal.record"
      :visible="claimModal.visible"
      :type="claimModal.type"
      :claim_id="claimModal.claim_id"
      v-if="claimModal.visible"
      @close="handleClaimClose()">
    </claim-modal>
    <a-card title="工期索赔一览表" style="margin-bottom: 16px">
      <a-row>
        <a-col
          class="item"
          :xs="{ span: 24 }"
          :sm="{ span: 12 ,offset:12}"
          :xl="{ span: 4 ,offset:20}">
          <a-button
            type="primary"
            icon="plus"
            style="width: 100%"
            @click="handleClaimCreate()">
            新建
          </a-button>
        </a-col>
      </a-row>
      <a-spin :spinning="status.listLoading">
        <a-table
          bordered
          :columns="columns"
          :dataSource="matterList"
          rowKey="claim_id"
          :pagination="false">
          <template slot="file" slot-scope="text, record, index">
            <a-button @click="$router.push({'name': 'implementProgressClaimDetail', params: {id: record.claim_id}})">文件管理</a-button>
          </template>
          <template slot="operation" slot-scope="text, record, index">
            <a-button @click="handleClaimEdit(record)">编辑</a-button>
            <a-popconfirm
              title="确认删除吗?"
              @confirm="handleClaimDelete(record)">
              <a-button type="danger">删除</a-button>
            </a-popconfirm>
          </template>
        </a-table>
      </a-spin>
    </a-card>
    <a-card title="总计">
      <a-col>
        <a-card-grid style="width: 50%; text-align: center">原工期：{{card.original_time}}天</a-card-grid>
        <a-card-grid style="width: 50%; text-align: center">新工期：{{card.new_time}}天</a-card-grid>
      </a-col>
    </a-card>
    <a-col
      :xs="{span: 24}"
      :sm="{span: 20, offset: 2}"
      :xl="{span: 16, offset: 4}">
    </a-col>

  </div>
</template>

<script>
  import ClaimModal from './components/ClaimModal'
  import api from '../../../api/implement'
  import {mapGetters} from 'vuex'

  const columns = [
    {
      title: '单位名称',
      dataIndex: 'name',
      width: '15%',
      align: 'center'
    },
    {
      title: '类别',
      dataIndex: 'type',
      width: '10%',
      align: 'center'
    }, {
      title: '索赔项目',
      dataIndex: 'item',
      width: '25%',
      align: 'center'
    },
    {
      title: '原工期',
      dataIndex: 'original_time',
      width: '13%',
      align: 'center'
    },
    {
      title: '新工期',
      dataIndex: 'new_time',
      width: '13%',
      align: 'center'
    },
    {
      title: '附件',
      dataIndex: 'file',
      scopedSlots: {customRender: 'file'},
      width: '12%',
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
    name: 'Claim',
    components: {
      ClaimModal
    },
    props: {
      id: Number
    },
    data() {
      return {
        status: {
          listLoading: true
        },
        card: {
          original_time: null,
          new_time: null
        },
        layout: {
          'label-col': {span: 6},
          'wrapper-col': {span: 18}
        },
        form: this.$form.createForm(this),
        claimModal: {
          record: null,
          visible: false,
          type: '1',
          claim_id: this.id
        },
        columns,
        matterList: [],
        project_id: this.projectSelected().id
      }
    },
    mounted() {
      this.getData()
    },
    methods: {
      ...mapGetters(['projectSelected']),
      getData() {
        api.getClaimList(this.project_id)
          .then(data => {
            this.matterList = data
            this.status.listLoading = false
          }),
        api.getClaimSum(this.project_id)
          .then(data => {
            this.card = data
          })

      },
      handleClaimClose() {
        this.claimModal.type = ''
        this.claimModal.record = null
        this.claimModal.visible = false
        this.getData()
      },
      handleClaimCreate() {
        this.claimModal.type = 'create'
        this.claimModal.record = null
        this.claimModal.visible = true
      },
      handleClaimEdit(record) {
        this.claimModal.type = 'edit'
        this.claimModal.record = record
        this.claimModal.visible = true
      },
      handleClaimDelete(record) {
        api.deleteClaim(record.claim_id)
          .then(data => {
            this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
            this.status.listLoading = true
          })
      },
      parseFloat(f) {
        return parseFloat(f)
      }
    }
  }
</script>

<style scoped lang="less">
  .item {
    margin-bottom: 10px;
  }

  .wrapper {
    width: 7rem;
    height: 5rem;
  }
</style>