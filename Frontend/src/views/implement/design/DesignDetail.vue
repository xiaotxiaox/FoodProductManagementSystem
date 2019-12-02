<template>
  <a-card>
    <a-row class="item">
      <span style="font-size: 20px">
        {{name}}
      </span>
      <a-upload
        name="image"
        action="/api/file/"
        style="float: right"
        :multiple="true"
        :withCredentials="true"
        :headers="headers"
        :showUploadList="false"
        :beforeUpload="beforeUpload"
        @change="handleChange">
        <a-button
          type="primary"
          icon="upload">
          上传
        </a-button>
      </a-upload>
      <a-button
        style="float: right; margin-right: 5px;"
        @click="$router.go(-1)">
        返回
      </a-button>
    </a-row>
    <a-table
      bordered
      rowKey="id"
      :columns="columns"
      :loading="status.loading"
      :dataSource="fileList"
      :pagination="false">
      <template slot="operation" slot-scope="text, record, index">
        <a-button-group>
          <a-button type="primary" @click="handleDownload(record)">下载</a-button>
          <a-popconfirm
            title="确认删除吗?"
            @confirm="handleDelete(record)">
            <a-button type="danger">删除</a-button>
          </a-popconfirm>
        </a-button-group>
      </template>
    </a-table>
  </a-card>
</template>

<script>
  const columns = [
    {
      title: '文件名称',
      dataIndex: 'name',
      width: '24%',
      align: 'center'
    },
    {
      title: '上传人',
      dataIndex: 'user.username',
      width: '20%',
      align: 'center'
    },
    {
      title: '上传日期',
      dataIndex: 'time',
      width: '20%',
      align: 'center'
    },
    {
      title: '编辑',
      dataIndex: 'operation',
      scopedSlots: { customRender: 'operation' },
      align: 'center'
    }
  ]
  import api from '../../../api/implement'
  import { getCookie } from '../../../utils/util'

  export default {
    name: 'DesignDetail',
    props: {
      id: Number
    },
    data () {
      return {
        columns,
        status: {
          loading: true
        },
        name: '',
        fileList: [],
        file: {},
        headers: {
          'X-CSRFToken': getCookie('csrftoken')
        }
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      getData () {
        this.status.loading = true
        api.getDesignItem(this.id)
          .then(data => {
            this.name = data.name
            this.fileList = data.files
            this.status.loading = false
          })
      },
      handleChange (info) {
        if (info.file.status === 'done') {
          this.file = info.file.response
          api.createDesignItemFile(this.id, this.file)
            .then(data => {
              this.$notification.success({ message: '成功', description: '上传文件成功', key: 'SUCCESS' })
              this.getData()
            })
        } else if (info.file.status === 'error') {
          this.$notification.error({ message: '失败', description: '上传文件失败', key: 'ERROR' })
        }
      },
      beforeUpload (file) {
        let isLt10G = file.size / 1024 / 1024 / 1024 <= 10
        if (!isLt10G) {
          this.$message.error('文件大小请小于10GB')
        }
        return isLt10G
      },
      handleDownload (record) {
        window.open(record.url)
      },
      handleDelete (record) {
        api.deleteDesignItemFile(record.id)
          .then(data => {
            this.$notification.success({message: '成功', description: '成功删除文件', key: 'SUCCESS'})
            this.getData()
          })
      }
    }
  }
</script>

<style scoped lang="less">
  .item {
    margin-bottom: 16px;
  }

  .ant-upload {
    width: 100%;
  }
</style>