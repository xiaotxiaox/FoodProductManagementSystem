<template>
  <div>
    <video-modal
      :record="videoModal.record"
      :visible="videoModal.visible"
      :type="videoModal.type"
      :video_id="videoModal.video_id"
      v-if="videoModal.visible"
      @close="handleVideoClose()">
    </video-modal>
    <a-card style="margin-bottom: 16px">
      <a-row style="margin-bottom: 16px">
          <a-button
            type="primary"
            icon="plus"
            style="float: right"
            @click="handleVideoCreate()">
            新建
          </a-button>
         <a-button
            type="default"
            icon="plus"
            style="float: right;margin-right:5px"
            @click="$router.push({'name': 'video', params: {id: project_id}})">
            编辑
          </a-button>
      </a-row>
      <a-spin :spinning="status.listLoading">
        <a-table
          bordered
          :columns="columns"
          :dataSource="matterList"
          rowKey="video_id"
          :pagination="false">
          <template slot="number" slot-scope="text, record, index">
            <div>{{index+1}}</div>
          </template>
          <template slot="url" slot-scope="text, record, index">
            <a :href="record.url" target="_blank">{{record.url}}</a>
          </template>
          <template slot="operation" slot-scope="text, record, index">
            <a-button @click="handleVideoEdit(record)">编辑</a-button>
            <a-popconfirm
              title="确认删除吗?"
              @confirm="handleVideoDelete(record)">
              <a-button type="danger">删除</a-button>
            </a-popconfirm>
          </template>
        </a-table>
      </a-spin>
      <a-col
        :xs="{span: 24}"
        :sm="{span: 20, offset: 2}"
        :xl="{span: 16, offset: 4}">
      </a-col>
    </a-card>
  </div>
</template>

<script>
  import VideoModal from './components/VideoModal'
  import api from '../../api/video'
  import {mapGetters} from 'vuex'

  const columns = [
    {
      title: '序号',
      dataIndex: 'number',
      scopedSlots: {customRender: 'number'},
      width: '15%',
      align: 'center'
    },
    {
      title: '名字',
      dataIndex: 'name',
      width: '15%',
      align: 'center'
    },
    {
      title: '地点',
      dataIndex: 'place',
      width: '30%',
      align: 'center'
    },
    {
      title: '网址',
      dataIndex: 'url',
      width: '30%',
      align: 'center',
      scopedSlots: {customRender: 'url'},
    },
    {
      title: '编辑',
      dataIndex: 'operation',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ]

  export default {
    name: 'VideoTable',
    components: {
      VideoModal
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
        layout: {
          'label-col': {span: 6},
          'wrapper-col': {span: 18}
        },
        form: this.$form.createForm(this),
        videoModal: {
          record: null,
          visible: false,
          type: '1',
          video_id: this.id
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
        this.status.listLoading = true
        api.getVideoList(this.project_id)
          .then(data => {
            this.matterList = data
            console.log(this.matterList)
            this.status.listLoading = false
            console.log(this.matterList)
          })

      },
      handleVideoClose() {
        this.videoModal.type = ''
        this.videoModal.record = null
        this.videoModal.visible = false
        this.getData()
      },
      handleVideoCreate() {
        this.videoModal.type = 'create'
        this.videoModal.record = null
        this.videoModal.visible = true
      },
      handleVideoEdit(record) {
        this.videoModal.type = 'edit'
        this.videoModal.record = record
        this.videoModal.visible = true
      },
      handleVideoDelete(record) {
        api.deleteVideo(record.id)
          .then(data => {
            this.$notification.success({message: '成功', description: '删除成功', key: 'SUCCESS'})
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