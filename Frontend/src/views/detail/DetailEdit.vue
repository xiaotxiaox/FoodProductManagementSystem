<template>
  <a-card>
    <a-col
      class="item"
      :xs="{span: 24}"
      :sm="{span: 20}"
      :xl="{span: 16}">
      <a-form
        :form="form"
        @submit="handleSubmit">
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
              <a-icon :type="loading ? 'loading' : 'plus'"/>
              <div class="text">选择图片</div>
            </div>
          </a-upload>
        </a-form-item>
        <a-form-item
          label="项目编号"
          v-bind="layout">
          <a-input
            disabled
            type="text"
            v-decorator="['id', {initialValue: project ? project.id : null}]">
          </a-input>
        </a-form-item>
        <a-form-item
          label="项目名称"
          v-bind="layout">
          <a-input
            type="text"
            autocomplete="true"
            placeholder="XXX项目"
            v-decorator="[
              'name',
              {rules:[
                 {required: true, message: '请输入项目名称'},
                 {max:256, message:'长度在256个汉字以内'}
               ],
               validateTrigger: 'blur',
               initialValue: project ? project.name : null
              }]">
          </a-input>
        </a-form-item>
        <a-form-item
          label="工程规模"
          v-bind="layout">
          <a-input
            type="text"
            autocomplete="true"
            placeholder="请输入工程规模"
            v-decorator="[
              'scale',
              {rules:[
                 {max:64, message:'长度在64个汉字以内'}
               ],
               validateTrigger: 'blur',
               initialValue: project ? project.scale : null
              }]">
          </a-input>
        </a-form-item>
        <a-form-item
          label="工程性质"
          v-bind="layout">
          <a-input
            type="text"
            autocomplete="true"
            placeholder="请输入工程性质"
            v-decorator="[
              'property',
              {rules:[
                 {max:64, message:'长度在64个汉字以内'}
               ],
               validateTrigger: 'blur',
               initialValue: project ? project.property : null
              }]">
          </a-input>
        </a-form-item>
        <a-form-item
          label="工程用途"
          v-bind="layout">
          <a-input
            type="text"
            autocomplete="true"
            placeholder="请输入工程用途"
            v-decorator="[
              'purpose',
              {rules:[
                 {max:64, message:'长度在64个汉字以内'}
               ],
               validateTrigger: 'blur',
               initialValue: project ? project.purpose : null
              }]">
          </a-input>
        </a-form-item>
        <a-form-item
          label="资金来源"
          v-bind="layout">
          <a-select
            mode="multiple"
            placeholder="请选择资金来源"
            v-decorator="[
              'fund_sources',
              {rules: [{required: true, message: '请选择资金来源'}],
               initialValue: project ? project.fund_sources : null}
            ]">
            <a-select-option
              v-for="item in fundSourcesList"
              :key="item.value"
              :value="item.value">
              {{ item.label }}
            </a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item
          label="结构形式"
          v-bind="layout">
          <a-input
            type="text"
            autocomplete="true"
            placeholder="请输入结构形式"
            v-decorator="[
              'style',
              {rules:[
                 {max:64, message:'长度在64个汉字以内'}
               ],
               validateTrigger: 'blur',
               initialValue: project ? project.style: null
              }]">
          </a-input>
        </a-form-item>
        <a-form-item
          label="建设地点"
          v-bind="layout">
          <a-input
            type="text"
            autocomplete="true"
            placeholder="XXX路XXX号"
            v-decorator="[
              'place',
              {rules: [
                 {required: true, message: '请输入建设地点'},
                 {max:64,message:'长度在64个汉字以内'}
               ],
               validateTrigger: 'blur',
               initialValue: project ? project.place : null}
            ]">
          </a-input>
        </a-form-item>
        <a-form-item
          label="开工日期"
          v-bind="layout">
          <a-date-picker
            style="width:100%"
            v-decorator="[
              'starting_date',
              {rules: [{type: 'object', required: true, message: '请输入开工日期'}],
               initialValue: project ? project.starting_date : null}
            ]"></a-date-picker>
        </a-form-item>
        <a-form-item
          label="预计竣工日期"
          v-bind="layout">
          <a-date-picker
            style="width:100%"
            v-decorator="[
              'finishing_date',
              {rules: [{type: 'object',required: true, message: '请输入预计竣工日期'}],
               initialValue: project ? project.finishing_date : null}
            ]"></a-date-picker>
        </a-form-item>
        <a-form-item
          label="工程总造价"
          v-bind="layout">
          <a-input
            type="number"
            autocomplete="true"
            placeholder="1000"
            addonAfter="万元"
            v-decorator="['cost',{initialValue: project ? project.cost : null}]">
          </a-input>
        </a-form-item>
        <a-form-item
          label="投资额"
          v-bind="layout">
          <a-input
            type="decimal(5,2)"
            autocomplete="true"
            placeholder="1000"
            addonAfter="万元"
            v-decorator="['investment', {initialValue: project ? project.investment : null}]">
          </a-input>
        </a-form-item>
        <a-form-item
          label="建筑面积"
          v-bind="layout">
          <a-input
            type="number"
            autocomplete="true"
            placeholder="1000"
            addonAfter="平方米"
            v-decorator="['area',{initialValue: project ? project.area : null}]">
          </a-input>
        </a-form-item>
        <a-form-item
          label="施工条件"
          v-bind="layout">
          <a-textarea
            placeholder="请输入施工条件（不多于2000字）"
            :rows="4"
            v-decorator="[
              'condition',
              {rules: [
                 {max:2000, message:'长度在2000个汉字以内'}
               ],
               initialValue: project ? project.condition : null,
               validateTrigger: 'blur'}
            ]"></a-textarea>
        </a-form-item>
        <a-form-item
          label="项目简介"
          v-bind="layout">
          <a-textarea
            placeholder="请输入项目简介（不多于2000字）"
            :rows="4"
            v-decorator="[
              'introduction',
              {rules: [
                 {max:2000, message:'长度在2000个汉字以内'}
               ],
               validateTrigger: 'blur',
               initialValue: project ? project.introduction : null}
            ]"></a-textarea>
        </a-form-item>
        <a-form-item>
          <div style="text-align: right">
            <a-button
              type="danger"
              style="margin-right: 20px"
              @click="handelDelete">
              删除
            </a-button>
            <a-button
              type="primary"
              @click="handleSubmit">
              更新
            </a-button>
          </div>
        </a-form-item>
      </a-form>
    </a-col>
  </a-card>
</template>

<script>
import projectApi from '../../api/project'
import detailApi from '../../api/detail'
import moment from 'moment'
import { mapGetters } from 'vuex'
import { getCookie } from '../../utils/util'

export default {
  name: 'DetailEdit',
  data () {
    return {
      layout: {
        'label-col': { span: 6 },
        'wrapper-col': { span: 18 }
      },
      form: this.$form.createForm(this),
      scaleList: [],
      propertyList: [],
      purposeList: [],
      fundSourcesList: [],
      styleList: [],
      project: {},
      loading: false,
      image: {
        url: '',
        uuid: ''
      },
      headers: {
        'X-CSRFToken': getCookie('csrftoken')
      }
    }
  },
  mounted () {
    this.getProjectDetail()
    projectApi.getScaleList()
      .then(data => {
        this.scaleList = data
      })
    projectApi.getPropertyList()
      .then(data => {
        this.propertyList = data
      })
    projectApi.getPurposeList()
      .then(data => {
        this.purposeList = data
      })
    projectApi.getFundSourcesList()
      .then(data => {
        this.fundSourcesList = data
      })
    projectApi.getStyleList()
      .then(data => {
        this.styleList = data
      })
  },
  methods: {
    ...mapGetters(['projectSelected']),
    getProjectDetail () {
      // detailApi.getProjectDetail(this.projectSelected().id)
      //   .then(data => {
      //     // 处理获得的数据
      //     data.scale = data.scale.value
      //     data.style = data.style.map(value => {
      //       return value.value
      //     })
      //     data.property = data.property.map(value => {
      //       return value.value
      //     })
      //     data.purpose = data.purpose.map(value => {
      //       return value.value
      //     })
      //     data.fund_sources = data.fund_sources.map(value => {
      //       return value.value
      //     })
      //     data.starting_date = new moment(data.starting_date)
      //     data.finishing_date = new moment(data.finishing_date)
      //     this.project = data
      //   })
    },
    handleSubmit (e) {
      e.preventDefault()
      this.form.validateFields((error, data) => {
        if (!error) {
          data.starting_date = data.starting_date.format('YYYY-MM-DD')
          data.finishing_date = data.finishing_date.format('YYYY-MM-DD')
          console.log(data)
          debugger
          // detailApi.updateProject(data)
          //   .then(data => {
          //     this.$notification.success({ message: '成功', description: '修改成功', key: 'SUCCESS' })
          //     this.$router.push({ name: 'detail' })
          //   })
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
      const isLt2M = file.size / 1024 / 1024 <= 2
      if (!isLt2M) {
        this.$message.error('图片大小请小于2MB')
      }
      return isImage && isLt2M
    },
    handelDelete () {
      this.$confirm({
        title: '此操作不可逆，请再次确定是否删除项目。',
        okText: '删除',
        okType: 'danger',
        onOk () {
          console.log('deleted')
        }
      })
    },
    validateID (rule, value, callback) {
      projectApi.validateID(value)
        .then(data => {
          if (data.result === '此项目编号已被使用！') {
            callback(data.result)
          } else {
            callback()
          }
        })
    }
  }
}
</script>

<style scoped lang="stylus">

</style>
