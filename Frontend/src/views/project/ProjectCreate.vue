<template>
  <page-layout title="新建项目">
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
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
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
            <span>支持图片文件，不大于2MB</span>
          </a-form-item>
          <a-form-item
            label="项目编号"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-input
              type="text"
              autocomplete="true"
              placeholder="1234567890"
              v-decorator="[
                'id',
                {rules:[
                  {required: true, message: '请输入项目编号'},
                  {max:16, message:'长度在16个字符以内'},
                  {validator: validateChinese, message: '不能含有中文字符'},
                  {validator: validateIDRepeat, message: '项目编号重复'}
                 ],
                 validateTrigger: 'blur'}
              ]">
            </a-input>
          </a-form-item>
          <a-form-item
            label="项目名称"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-input
              type="text"
              autocomplete="true"
              placeholder="XXX项目"
              v-decorator="[
                'name',
                {rules:[
                  {required: true, message: '请输入项目名称'},
                  {max:256, message:'长度在256个汉字以内'}
                ], validateTrigger: 'blur'}
              ]">
            </a-input>
          </a-form-item>
          <a-form-item
            label="工程规模"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-input
              type="text"
              autocomplete="true"
              placeholder="请输入工程规模"
              v-decorator="[
              'scale',
              {rules:[
                 {max:64, message:'长度在64个汉字以内'}
               ],
               validateTrigger: 'blur'
              }]">
            </a-input>
          </a-form-item>
          <a-form-item
            label="性质"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-input
              type="text"
              autocomplete="true"
              placeholder="请输入工程性质"
              v-decorator="[
              'property',
              {rules:[
                 {max:64, message:'长度在64个汉字以内'}
               ],
               validateTrigger: 'blur'
              }]">
            </a-input>
          </a-form-item>
          <a-form-item
            label="工程用途"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-input
              type="text"
              autocomplete="true"
              placeholder="请输入工程用途"
              v-decorator="[
              'purpose',
              {rules:[
                 {max:64, message:'长度在64个汉字以内'}
               ],
               validateTrigger: 'blur'
              }]">
            </a-input>
          </a-form-item>
          <a-form-item
            label="资金来源"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-select
              mode="multiple"
              placeholder="请选择资金来源"
              v-decorator="[
                'fund_sources',
                {rules: [{required: true, message: '请选择资金来源'}],}
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
            label="工程类型"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-select
              mode="multiple"
              placeholder="请选择工程类型"
              v-decorator="[
                'fund_sources',
                {rules: [{required: true, message: '请选择工程类型'}],}
              ]">
              <a-select-option
                :key="1"
                :value="1">
                房屋
              </a-select-option>
              <a-select-option
                :key="2"
                :value="2">
                道路
              </a-select-option>
            </a-select>
          </a-form-item>
          <a-form-item
            label="结构形式"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-input
              type="text"
              autocomplete="true"
              placeholder="请输入结构形式"
              v-decorator="[
              'style',
              {rules:[
                 {max:64, message:'长度在64个汉字以内'}
               ],
               validateTrigger: 'blur'
              }]">
            </a-input>
          </a-form-item>
          <a-form-item
            label="建设地点"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
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
                 validateTrigger: 'blur'}
              ]">
            </a-input>
          </a-form-item>
          <a-form-item
            label="开工日期"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-date-picker
              style="width:100%"
              v-decorator="[
                'starting_date',
                {rules: [{type: 'object', required: true, message: '请输入开工日期'}]}
              ]"></a-date-picker>
          </a-form-item>
          <a-form-item
            label="预计竣工日期"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-date-picker
              style="width:100%"
              v-decorator="[
                'finishing_date',
                {rules: [{type: 'object',required: true, message: '请输入建设地点'}]}
              ]"></a-date-picker>
          </a-form-item>
          <a-form-item
            label="工程总造价"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-input
              type="number"
              autocomplete="true"
              placeholder="1000"
              addonAfter="万元"
              v-decorator="['cost']">
            </a-input>
          </a-form-item>
          <a-form-item
            label="投资额"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-input
              type="decimal(5,2)"
              autocomplete="true"
              placeholder="1000"
              addonAfter="万元"
              v-decorator="['investment']">
            </a-input>
          </a-form-item>
          <a-form-item
            label="建筑面积"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-input
              type="number"
              autocomplete="true"
              placeholder="1000"
              addonAfter="平方米"
              v-decorator="['area']">
            </a-input>
          </a-form-item>
          <a-form-item
            label="施工条件"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-textarea
              placeholder="请输入施工条件（不多于2000字）"
              :rows="4"
              v-decorator="[
                'condition',
                {rules: [
                   {max:2000, message:'长度在2000个汉字以内'}
                 ],
                 validateTrigger: 'blur'}
              ]"></a-textarea>
          </a-form-item>
          <a-form-item
            label="项目简介"
            :label-col="{span: 6}"
            :wrapper-col="{span: 18}">
            <a-textarea
              placeholder="请输入项目简介（不多于2000字）"
              :rows="4"
              v-decorator="[
                'introduction',
                {rules: [
                   {max:2000, message:'长度在2000个汉字以内'}
                 ],
                 validateTrigger: 'blur'}
              ]"></a-textarea>
          </a-form-item>
          <a-form-item>
            <div style="text-align: right">
              <a-button
                style="margin-right: 20px"
                @click="handelClearForm">
                重置
              </a-button>
              <a-button
                type="primary"
                @click="handleSubmit">
                提交
              </a-button>
            </div>
          </a-form-item>
        </a-form>
      </a-col>
    </a-card>
  </page-layout>

</template>

<script>
import PageLayout from '../../components/page/PageLayout'
import AFormItem from 'ant-design-vue/es/form/FormItem'
import api from '../../api/project'
import { getCookie } from '../../utils/util'

export default {
  name: 'ProjectCreate',
  components: {
    AFormItem,
    PageLayout
  },
  data () {
    return {
      form: this.$form.createForm(this),
      scaleList: [],
      propertyList: [],
      purposeList: [],
      fundSourcesList: [],
      styleList: [],
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
    api.getScaleList().then(data => {
      this.scaleList = data
    })
    api.getPropertyList().then(data => {
      this.propertyList = data
    })
    api.getPurposeList().then(data => {
      this.purposeList = data
    })
    api.getFundSourcesList().then(data => {
      this.fundSourcesList = data
    })
    api.getStyleList().then(data => {
      this.styleList = data
    })
  },
  methods: {
    handleSubmit (e) {
      e.preventDefault()
      this.form.validateFields((error, data) => {
        if (!error) {
          data.starting_date = data.starting_date.format('YYYY-MM-DD')
          if (data.finishing_date) {
            data.finishing_date = data.finishing_date.format('YYYY-MM-DD')
          }
          data.uuid = this.image.uuid
          api.createProject(data).then(data => {
            debugger
            console.log(data)
            this.$router.push({ name: 'project' })
          }).catch(e => {
            this.$notification.error({ message: e })
          })
        }
      })
    },
    handelClearForm () {
      this.form.resetFields()
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
    validateIDRepeat (rule, value, callback) {
      api.validateID(value).then(data => {
        if (data.result === '此项目编号已被使用！') {
          callback(data.result)
        } else {
          callback()
        }
      })
    },
    validateChinese (rule, value, callback) {
      if (/[\u4e00-\u9fa5]/.test(value)) {
        callback('id can not contain chinese')
      } else {
        callback()
      }
    }
  }
}
</script>

<style scoped lang="less">
  .ant-upload-select-picture-card {
    width: 200px;

    i {
      font-size: 32px;
      color: #999;
    }

    .text {
      margin-top: 8px;
      color: #666;
    }
  }
</style>
