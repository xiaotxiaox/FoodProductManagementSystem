<template>
  <page-layout :title="isEdit ? '项目编辑' : '项目新建'">
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
                <a-icon :type="status.img.loading ? 'loading' : 'plus'"/>
                <div class="text">选择图片</div>
              </div>
            </a-upload>
            <span>支持图片文件，不大于2MB</span>
          </a-form-item>
          <a-form-item
            label="项目编号"
            v-bind="layout">
            <a-input
              type="text"
              autocomplete="true"
              placeholder="1234567890"
              v-decorator="[
                'id',
                {rules:[
                  {required: true, message: '请输入项目编号'},
                  {max:11, message:'长度在11个字符以内'},
                  {validator: validateChinese, message: '不能含有中文字符'},
                  {validator: validateIDRepeat, message: '项目编号重复'}
                 ],
                 validateTrigger: 'blur',
                 initialValue: isEdit ? project.id : null}
              ]">
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
                ], validateTrigger: 'blur',
                 initialValue: isEdit ? project.name : null}
              ]">
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
                 initialValue: isEdit ? project.fund_sources : []}
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
              placeholder="请选择工程类型"
              v-decorator="[
                'category',
                {rules: [{required: true, message: '请选择工程类型'}],initialValue: isEdit ? project.category:null}
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
                 initialValue: isEdit ? project.place : null}
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
                {rules: [{ type: 'object', required: true, message: '请输入开工日期' }],
                 initialValue: isEdit ? project.starting_date : null}
              ]"></a-date-picker>
          </a-form-item>
          <a-form-item
            label="预计竣工日期"
            v-bind="layout">
            <a-date-picker
              style="width:100%"
              v-decorator="[
                'finishing_date',
                {rules: [{ type: 'object',required: true, message: '请输入建设地点' }],
                 initialValue: isEdit ? project.finishing_date : null}
              ]"></a-date-picker>
          </a-form-item>
          <a-form-item
            label="工程总造价"
            v-bind="layout">
            <a-input
              type="number"
              autocomplete="true"
              placeholder="1000"
              addonAfter="元"
              v-decorator="[
                'cost',
                {
                  initialValue: isEdit ? project.cost : null
                }
              ]">
            </a-input>
          </a-form-item>
          <a-form-item
            label="投资额"
            v-bind="layout">
            <a-input
              type="decimal(13,2)"
              autocomplete="true"
              placeholder="1000"
              addonAfter="元"
              v-decorator="[
              'investment',
                {
                  initialValue: isEdit ? project.investment : null
                }
              ]">
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
              v-decorator="[
              'area',
                {
                  initialValue: isEdit ? project.area : null
                }
              ]">
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
                {
                  rules: [
                   { max:2000, message:'长度在2000个汉字以内' }
                  ],
                  validateTrigger: 'blur',
                  initialValue: isEdit ? project.condition : null
                }
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
                {
                  rules: [
                    { max:2000, message:'长度在2000个汉字以内' }
                  ],
                  validateTrigger: 'blur',
                  initialValue: isEdit ? project.introduction : null
                }
              ]"></a-textarea>
          </a-form-item>
          <a-form-item>
            <a-button
              v-if="!isEdit"
              class="btn"
              type="primary"
              @click="handleSubmit">
              提交
            </a-button>
            <a-button
              v-if="!isEdit"
              class="btn"
              @click="handelClearForm">
              重置
            </a-button>
            <a-button
              v-if="isEdit"
              type="primary"
              class="btn"
              @click="handleSubmit">
              更新
            </a-button>
            <a-popconfirm title="是否确定删除？" @confirm="handelDelete">
              <a-button
                v-if="isEdit"
                type="danger"
                class="btn">
                删除
              </a-button>
            </a-popconfirm>
            <a-button
              class="btn"
              @click="$router.go(-1)">
              返回
            </a-button>
          </a-form-item>
        </a-form>
      </a-col>
    </a-card>
  </page-layout>

</template>

<script>
import PageLayout from '../../components/page/PageLayout'
import api from '../../api/project'
import moment from 'moment'
import { getCookie } from '../../utils/util'
import { mapGetters } from 'vuex'

export default {
  name: 'ProjectDetail',
  components: {
    PageLayout
  },
  data () {
    return {
      layout: {
        'label-col': { span: 6 },
        'wrapper-col': { span: 18 }
      },
      status: {
        img: {
          loading: false
        }
      },
      form: this.$form.createForm(this),
      fundSourcesList: [],
      project: {},
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
    this.getData()
  },
  methods: {
    ...mapGetters(['projectSelected']),
    getData () {
      api.getFundSourcesList().then(data => {
        this.fundSourcesList = data
      })
      if (this.isEdit) {
        api.getProject(this.projectSelected().id)
          .then(data => {
            data.fund_sources = data.fund_sources.map(value => {
              return value.value
            })
            data.starting_date = new moment(data.starting_date)
            data.finishing_date = new moment(data.finishing_date)
            if (data.images) {
              this.image = data.images
            }
            this.project = data
            if(data.category==='1')
              data.category='房屋'
            if(data.category==='2')
              data.category='道路'
          })
      }
    },
    handleSubmit (e) {
      e.preventDefault()
      this.form.validateFields((error, data) => {
        if (!error) {
          data.starting_date = data.starting_date.format('YYYY-MM-DD')
          data.finishing_date = data.finishing_date.format('YYYY-MM-DD')
          data.uuid = this.image.uuid
          if (this.isEdit) {
            api.updateProject(data)
              .then(data => {
                this.$notification.success({ message: '成功', description: '修改项目成功', key: 'SUCCESS' })
                this.$router.push({ name: 'detail' })
              })
          } else {
            api.createProject(data)
              .then(data => {
                this.$notification.success({ message: '成功', description: '新建项目成功', key: 'SUCCESS' })
                this.$router.push({ name: 'project' })
              })
          }
        }
      })
    },
    handelDelete () {
      api.deleteProject(this.project.id)
        .then(data => {
          this.$notification.success({ message: '成功', description: '删除成功', key: 'SUCCESS' })
          this.$router.push({ name: 'project' })
          // 强制刷新页面 清空 store
          window.history.go(0)
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
      const isLt2M = file.size / 1024 / 1024 <= 100
      if (!isLt2M) {
        this.$message.error('图片大小请小于100MB')
      }
      return isImage && isLt2M
    },
    validateIDRepeat (rule, value, callback) {
      if (this.isEdit) {
        callback()
      } else {
        api.validateID(value)
          .then(data => {
            if (data === 'true') {
              callback('项目编号重复')
            } else {
              callback()
            }
          })
      }
    },
    validateChinese (rule, value, callback) {
      if (/[\u4e00-\u9fa5]/.test(value)) {
        callback('id can not contain chinese')
      } else {
        callback()
      }
    }
  },
  computed: {
    isEdit () {
      return this.$route.path.split('/').pop() === 'edit'
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

  .btn {
    float: right;
    margin: 10px 0 10px 10px;
  }
</style>
