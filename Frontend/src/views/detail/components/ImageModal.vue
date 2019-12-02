<template>
  <a-modal
    :title="title"
    :visible="visible"
    :keyboard="false"
    :maskClosable="false"
    okText="保存"
    okType="primary"
    @ok="handleOk()"
    @cancel="handleCancel()">
    <a-form
      :form="form">
      <a-form-item
        label="项目图片"
        v-bind="layout">
        <a-upload
          name="image"
          action="/api/file/"
          :withCredentials="true"
          :headers="headers"
          accept="image/*"
          listType="picture-card"
          :showUploadList="false"
          :beforeUpload="beforeUpload"
          @change="handleImageChange">
          <img v-if="image.url" :src="image.url" alt="项目图片" width="200px"/>
          <div v-else style="width: 100px">
            <a-icon :type="status.img.loading ? 'loading' : 'plus'"/>
            <div class="text">选择图片</div>
          </div>
        </a-upload>
        <span>支持图片文件，不大于2MB</span>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
import api from '../../../api/detail'
import { mapGetters } from 'vuex'
import { getCookie } from '../../../utils/util'

export default {
  name: 'ImageModal',
  props: {
    record: Object,
    visible: Boolean,
    type: String
  },
  data () {
    return {
      layout: {
        'label-col': { span: 8 },
        'wrapper-col': { span: 16 }
      },
      status: {
        img: {
          loading: false
        }
      },
      form: this.$form.createForm(this),
      typeList: [],
      project_id: this.projectSelected().id,
      image: {
        uuid: null,
        url: null
      },
      headers: {
        'X-CSRFToken': getCookie('csrftoken')
      }
    }
  },
  mounted () {
    if(this.record.url!=0)
      this.image=this.record
  },
  methods: {
    ...mapGetters(['projectSelected']),
    handleOk () {
      this.form.validateFields((error, data) => {
        if (!error) {
          data.uuid = this.image.uuid
          if(this.record.url==0){
            api.createImage(this.project_id, data)
            .then(data => {
              this.$emit('close')
              this.$notification.success({ message: '成功', description: '新建成功', key: 'SUCCESS' })
            })
          }
          else{
            api.deleteImage(this.project_id)
            .then(data => {
              this.$emit('close')
              this.$notification.success({ message: '成功', description: '删除成功', key: 'SUCCESS' })
            })
            api.createImage(this.project_id, data)
            .then(data => {
              this.$emit('close')
              this.$notification.success({ message: '成功', description: '新建成功', key: 'SUCCESS' })
            })
          }
        }
      })
    },
    handleImageChange (info) {
      if (info.file.status === 'uploading') {
        this.loading = true
      } else if (info.file.status === 'done') {
        this.image.uuid = info.file.response.uuid
        this.image.url = info.file.response.url.toString()
        this.loading = false
        console.log("this.image")
        console.log(this.image)
      } else if (info.file.status === 'error') {
        this.$message.error(`${info.file.name} 上传失败.`)
        this.loading = false
      }
    },
    beforeUpload (file) {
      const isImage = /image\/*/.test(file.type)
      if (!isImage) {
        this.$message.error('图片格式不正确')
      }
      const isLt2M = file.size / 1024 / 1024 <= 100
      if (!isLt2M) {
        this.$message.error('图片大小请小于100MB')
      }
      return isImage && isLt2M
    },
    handleCancel () {
      this.$emit('close')
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

<style scoped lang="less">

</style>