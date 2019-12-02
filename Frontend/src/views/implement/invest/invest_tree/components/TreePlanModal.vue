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
        label="建设项目总投资"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'total_investment_of_construction_project',
            {
              rules:[
                { message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.total_investment_of_construction_project : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="工程造价"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'project_cost',
            {
              rules:[
                { message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.project_cost : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="增值税"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'value_added_tax',
            {
              rules:[
                { message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.value_added_tax : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="资金筹措费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'fund_preparation_fee',
            {
              rules:[
                { message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.fund_preparation_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="流动资金"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'fluid_capital',
            {
              rules:[
                {message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.fluid_capital : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="工程费用"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'project_fee',
            {
              rules:[
                {  message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.project_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="工程建设其他费用"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'project_construction_other_fee',
            {
              rules:[
                {message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.project_construction_other_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="预备费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'prepare_fee',
            {
              rules:[
                {  message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.prepare_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="建筑工程费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'construction_work_fee',
            {
              rules:[
                { message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.construction_work_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="安装工程费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'installation_work_fee',
            {
              rules:[
                {message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.installation_work_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="设备预置费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'device_preset_fee',
            {
              rules:[
                { message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.device_preset_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="土地使用费和其他补偿费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'land_use_fee',
            {
              rules:[
                { message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.land_use_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="建设管理费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'construction_management_fee',
            {
              rules:[
                { message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.construction_management_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="可行性研究费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'feasibility_study_fee',
            {
              rules:[
                { message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.feasibility_study_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="专项评价费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'special_evaluation_fee',
            {
              rules:[
                {message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.special_evaluation_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="研究试验费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'research_experiment_fee',
            {
              rules:[
                {  message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.research_experiment_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="勘察设计费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'reconnaissance_design_fee',
            {
              rules:[
                {message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.reconnaissance_design_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="场地准备费和临时设施费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'site_preparation_fee',
            {
              rules:[
                {  message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.site_preparation_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="引进技术和进口设备材料其他费"
        :label-col="{span: 11}"
        :wrapper-col="{span: 8}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'imported_technology_fee',
            {
              rules:[
                { message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.imported_technology_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="特殊设备安全监督检验费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'special_equipment_fee',
            {
              rules:[
                {message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.special_equipment_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="市政公用配套设施费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'municipal_administration_fee',
            {
              rules:[
                { message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.municipal_administration_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="联合试运转费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'joint_commissioning_fee',
            {
              rules:[
                { message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.joint_commissioning_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="工程保险费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'engineer_insurance_fee',
            {
              rules:[
                { message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.engineer_insurance_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="专利及专有技术使用费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'patent_fee',
            {
              rules:[
                { message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.patent_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="生产准备费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'production_preparation_fee',
            {
              rules:[
                {message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.production_preparation_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="监理费"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'supervisor_fee',
            {
              rules:[
                {  message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.supervisor_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="造价咨询"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'cost_consultation_fee',
            {
              rules:[
                {message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.cost_consultation_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="招标代理"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'bid_agency_fee',
            {
              rules:[
                {  message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.bid_agency_fee : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="其他"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          addonAfter="元"
          v-decorator="[
            'other_fee',
            {
              rules:[
                { message: '请输入项目单位名称' },
                { max:128, message: '长度在128个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.other_fee : null
            }
          ]">
        </a-input>
      </a-form-item>


    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../../../api/implement'
  import ATextarea from "ant-design-vue/es/input/TextArea"
  import {mapGetters} from 'vuex'
  export default {
    name: 'TreePlanModal',
    components: {ATextarea},
    props: {
      record: Object,
      visible: Boolean,
      type: String,
    },
    data() {
      return {
        project_id: this.projectSelected().id,
        layout: {
          'label-col': {span: 8},
          'wrapper-col': {span: 12}
        },
        form: this.$form.createForm(this),
        matter: {}
      }
    },
     mounted() {
    },
    methods:{
      ...mapGetters(['projectSelected']),
      handleOk() {
        this.form.validateFields((error, data) => {
          if (!error) {
            api.updateTrees(this.record.tree_view_id, data)
              .then(data => {
                this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                this.$emit('close')
              })
          }
        })
      },
      handleCancel() {
        this.$emit('close')
      }
    },
    computed: {
      title() {
        return '编辑'
      }
    }

  }
</script>

<style scoped>

</style>