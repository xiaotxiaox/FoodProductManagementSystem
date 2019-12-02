<template xmlns:a-select-option="http://www.w3.org/1999/html">
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
        label="单位名称"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'company_name',
            {
              rules:[
                { required: true, message: '请输入名称' },
                { max:32, message: '长度在32个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.company_name : null
            }
          ]">
        </a-input>
      </a-form-item>
       <a-form-item
        label="项目名称"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'project_name',
            {
              rules:[
                { required: true, message: '请输入名称' },
                { max:32, message: '长度在32个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.project_name : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="类别"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}">
        <a-select
          placeholder="请选择类别"
          option.initialValue="建筑工程费"
          v-decorator="[
                'category',
                {rules: [
                {required: true, message: '请选择类别'}
                ],
                initialValue: record?record.category:'建筑工程费'}
              ]">
          <a-select-option v-for="item in categoryList":value="item.value":key="item.key">{{item.label}}</a-select-option>

        </a-select>
      </a-form-item>
      <a-form-item
        label="开始时间"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'start_time',
                {rules: [{type: 'object'}],
                initialValue: record ? record.start_time : null}
              ]"></a-date-picker>
      </a-form-item>
      <a-form-item
        label="截止时间"
        v-bind="layout">
        <a-date-picker
          style="width:100%"
          v-decorator="[
                'end_time',
                {rules: [{type: 'object'}],
                initialValue: record ? record.end_time : null}
              ]"></a-date-picker>
      </a-form-item>

      <a-form-item
        label="实际完成额度"
        v-bind="layout">
        <a-input
          type="decimal(13,2)"
          addonAfter="元"
          v-decorator="[
            'completion_quota',
            {
              rules: [
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.completion_quota : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="备注"
        v-bind="layout">
        <a-textarea
          placeholder="请输入备注（不多于120字）"
          :rows="4"
          v-decorator="[
            'explain',
            {rules: [
              {max:120, message:'长度在120个汉字以内'}
            ],
            validateTrigger: 'blur',
            initialValue: record ? record.explain : null}
          ]"></a-textarea>
      </a-form-item>
    </a-form>
    </a-modal>
</template>

<script>
  import api from '../../../../../api/implement'
  import { mapGetters } from 'vuex'
  import moment from 'moment'

  export default {
    name: 'investTableModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String,
    },
    data () {
      return {
        layout: {
          'label-col': { span: 8 },
          'wrapper-col': { span: 12 }
        },
        form: this.$form.createForm(this),
        matter: {},
        categoryList:[
           {key:'1', label:'建筑工程费', value:'建筑工程费'},
           {key:'2', label:'安装工程费',value:'安装工程费'},
           {key:'3', label:'设备预置费',value:'设备预置费'},
           {key:'4', label:'土地使用费和其他补偿费',value:'土地使用费和其他补偿费'},
           {key:'5', label:'建设管理费',value:'建设管理费'},
           {key:'7', label:'专项评价费',value:'专项评价费'},
           {key:'8', label:'研究试验费',value:'研究试验费'},
           {key:'10', label:'场地准备费和临时设施费',value:'场地准备费和临时设施费'},
           {key:'12', label:'特殊设备安全监督检验费',value:'特殊设备安全监督检验费'},
           {key:'14', label:'联合试运转费',value:'联合试运转费'},
           {key:'15', label:'工程保险费',value:'工程保险费'},
           {key:'16', label:'专利及专有技术使用费',value:'专利及专有技术使用费'},
           {key:'17', label:'生产准备费',value:'生产准备费'},
           {key:'18', label:'监理费',value:'监理费'},
           {key:'19', label:'造价咨询',value:'造价咨询'},
           {key:'21', label:'其他',value:'其他'},
           {key:'22', label:'预备费',value:'预备费'},
           {key:'23', label:'增值税',value:'增值税'},
           {key:'24', label:'资金筹措费',value:'资金筹措费'},
           {key:'25', label:'流动资金',value:'流动资金'}
        ]
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
      ...mapGetters(['projectSelected']),
      getData () {
        if (this.isEdit) {
          if (this.record.start_time !== null) {
            this.record.start_time = new moment(this.record.start_time)
          }
          if (this.record.end_time !== null) {
            this.record.end_time = new moment(this.record.end_time)
          }
        }
      },
      handleOk () {
        this.form.validateFields((error, data) => {
            if (!error) {
              if (data.start_time != null) {
                data.start_time = data.start_time.format('YYYY-MM-DD')
              }
              if (data.end_time != null) {
                data.end_time = data.end_time.format('YYYY-MM-DD')
              }
            }
            if (this.isEdit) {
              api.updateTotalInvest(this.record.total_investment_id, data)
                .then(data => {
                  this.$notification.success({ message: '成功', description: '更新成功', key: 'SUCCESS' })
                  this.$emit('close')
                })
            } else {
              api.createTotalInvest(this.$store.getters.projectSelected.id, data)
                .then(data => {
                  this.$notification.success({ message: '成功', description: '新建成功', key: 'SUCCESS' })
                  this.$emit('close')
                })
            }
          }
        )
      },
      handleCancel () {
        this.$emit('close')
      }

    },
    computed: {
      isEdit () {
        return this.type === 'edit'
      }
      ,
      title () {
        return this.isEdit ? '编辑' : '新建'
      }
    }
  }
</script>

<style scoped>

</style>