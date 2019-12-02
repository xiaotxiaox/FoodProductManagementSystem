<template>
  <div>
    <tree-plan-modal
      :record="planModal.record"
      :visible="planModal.visible"
      :type="planModal.type"
      v-if="planModal.visible"
      :tree_view_id="planModal.tree_view_id"
      @close="handleCloseTable()">
    </tree-plan-modal>
    <div>
        <a-card>
          <div :class="className" :id="id" style="width: 100%; height: 400px;" ref="myEchart"></div>
        </a-card>
    </div>
     <a-card style="margin-bottom: 16px">
      <a-table
          bordered
          :columns="columns"
          :dataSource="treeList"
          rowKey="tree_view_id"
          :scroll="{x:1300}"
          :pagination="false">
          <template slot="operation" slot-scope="text, record, index">
            <a-button @click="handlePlanEdit(record)">编辑</a-button>
          </template>
        </a-table>
      <a-col
        :xs="{span: 24}"
        :sm="{span: 20, offset: 2}"
        :xl="{span: 16, offset: 4}">
      </a-col>
    </a-card>

  </div>

</template>
<script>
  import echarts from 'echarts'
  import api from '../../../../api/implement'
  import dapi from '../../../../api/decision'
  import TreePlanModal from './components/TreePlanModal'
  const a='A'
  const columns = [
    {
      title: '建设项目总投资',
      dataIndex: 'total_investment_of_construction_project',
      width: '8%',
      align: 'center'
    },
    {
      title: '工程造价',
      dataIndex: 'project_cost',
      width: '8%',
      align: 'center'
    },
    {
      title: '增值税',
      dataIndex: 'value_added_tax',
      width: '8%',
      align: 'center'
    },
     {
      title: '资金筹措费',
      dataIndex: 'fund_preparation_fee',
      width: '8%',
      align: 'center'
    },
     {
      title: '流动资金',
      dataIndex: 'fluid_capital',
      width: '8%',
      align: 'center'
    },
     {
      title: '工程费用',
      dataIndex: 'project_fee',
      width: '8%',
      align: 'center'
    },
     {
      title: '工程建设其他费用',
      dataIndex: 'project_construction_other_fee',
      width: '8%',
      align: 'center'
    },
     {
      title: '预备费',
      dataIndex: 'prepare_fee',
      width: '15%',
      align: 'center'
    },
     {
      title: '建筑工程费',
      dataIndex: 'construction_work_fee',
      width: '8%',
      align: 'center'
    },
     {
      title: '安装工程费',
      dataIndex: 'installation_work_fee',
      width: '8%',
      align: 'center'
    },
     {
      title: '设备预置费',
      dataIndex: 'device_preset_fee',
      width: '8%',
      align: 'center'
    },
     {
      title: '土地使用费和其他补偿费',
      dataIndex: 'land_use_fee',
      width: '12%',
      align: 'center'
    },
     {
      title: '建设管理费',
      dataIndex: 'construction_management_fee',
      width: '15%',
      align: 'center'
    },
     {
      title: '可行性研究费',
      dataIndex: 'feasibility_study_fee',
      width: '8%',
      align: 'center'
    },
     {
      title: '专项评价费',
      dataIndex: 'special_evaluation_fee',
      width: '8%',
      align: 'center'
    },
      {
      title: '研究试验费',
      dataIndex: 'research_experiment_fee',
      width: '8%',
      align: 'center'
    },
     {
      title: '勘察设计费',
      dataIndex: 'reconnaissance_design_fee',
      width: '8%',
      align: 'center'
    },
     {
      title: '场地准备费和临时设施费',
      dataIndex: 'site_preparation_fee',
      width: '12%',
      align: 'center'
    },
     {
      title: '引进技术和进口设备材料其他费',
      dataIndex: 'imported_technology_fee',
      width: '15%',
      align: 'center'
    },
     {
      title: '特殊设备安全监督检验费',
      dataIndex: 'special_equipment_fee',
      width: '12%',
      align: 'center'
    },
         {
      title: '市政公用配套设施费',
      dataIndex: 'municipal_administration_fee',
      width: '12%',
      align: 'center'
    },
     {
      title: '联合试运转费',
      dataIndex: 'joint_commissioning_fee',
      width: '8%',
      align: 'center'
    },
     {
      title: '工程保险费',
      dataIndex: 'engineer_insurance_fee',
      width: '8%',
      align: 'center'
    },
     {
      title: '专利及专有技术使用费',
      dataIndex: 'patent_fee',
      width: '15%',
      align: 'center'
    },
      {
      title: '生产准备费',
      dataIndex: 'production_preparation_fee',
      width: '8%',
      align: 'center'
    },
     {
      title: '监理费',
      dataIndex: 'supervisor_fee',
      width: '8%',
      align: 'center'
    },
     {
      title: '造价咨询',
      dataIndex: 'cost_consultation_fee',
      width: '8%',
      align: 'center'
    },
     {
      title: '招标代理',
      dataIndex: 'bid_agency_fee',
      width: '8%',
      align: 'center'
    },
     {
      title: '其他',
      dataIndex: 'other_fee',
      width: '8%',
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
    name: 'invest_new_tree',
    components:{TreePlanModal},
    props: {
      className: {
        type: String,
        default: 'yourClassName',
      },
      id: Number,
       estimate: String,
       actual: String
    },
    data () {
      return {
        columns,
        project_id: this.id,
        card: {
          construction_work_fee: null,
          installation_work_fee: null,
          device_preset_fee: null,
          land_use_fee: null,
          construction_management_fee: null,
          feasibility_study_fee: null,
          special_evaluation_fee: null,
          research_experiment_fee: null,
          reconnaissance_design_fee: null,
          site_preparation_fee: null,
          imported_technology_fee: null,
          special_equipment_fee: null,
          municipal_administration_fee: null,
          joint_commissioning_fee: null,
          engineer_insurance_fee: null,
          patent_fee: null,
          production_preparation_fee: null,
          supervisor_fee: null,
          cost_consultation_fee: null,
          bid_agency_fee: null,
          other_fee: null,
          prepare_fee: null,
          value_added_tax: null,
          fund_preparation_fee: null,
          fluid_capital: null,
          project_construction_other_fee: null,
          project_fee: null,
          project_cost: null,
          total_investment_of_construction_project: null
        },
        bid_card: {
          save_rate: null,
          bid_price: null,
          control_price: null,
          agency_fee: null
        },
        planModal:{
          record: null,
          visible: false,
          type: '1',
          tree_view_id: this.id
        },
         treeList: [],
         applicationList: [],
         decisionList:[],
        designCompanyList:[],
         process:{},
         trees:{
            all_paid_total: null,
            consult_service_paid_total: null,
            construction_paid_total: null,
            other_paid_total: null,
            bid_paid_total: null,
            design_paid_total: null,
            consultation_paid_total: null,
            supervisor_paid_total: null,
            general_contract_paid_total:null,
            subcontract_paid_total:null
         },
         form: this.$form.createForm(this),
        chart: null

      }
    },
    mounted () {
      this.getData()
      this.chart = echarts.init(this.$refs.myEchart)
       this.chart.showLoading()
        this.chart.hideLoading()
      this.initChart()

    },
    beforeDestroy () {
      if (!this.chart) {
        return
      }
      this.chart.dispose()
      this.chart = null
    },
    methods: {
      getData() {
        api.getTreeList(this.$store.getters.projectSelected.id)
        .then(data => {
          console.log(data)
          this.treeList = data
          this.initChart()
        })
         api.getTrees(this.id)
        .then(data => {
          //console.log(data)
          this.trees = data
        })
      api.getTreeSum(this.$store.getters.projectSelected.id)
        .then(data => {

          this.card = data
          this.initChart()
        })
         api.getApplicationList(this.$store.getters.projectSelected.id)
        .then(data => {
          //console.log(data)
          this.applicationList = data
          this.initChart()
        })
        dapi.getDecisionList(this.$store.getters.projectSelected.id)
        .then(data => {
          this.decisionList = data.sort((x, y) => {
            return x.decision_id > y.decision_id
          })
          this.initChart()
        })
         api.getBidSum(this.$store.getters.projectSelected.id)
          .then(data => {
            this.bid_card = data
           this.initChart()
          })
        api.getDesignCompanyList(this.project_id)
          .then(data => {
            this.designCompanyList = data
            this.initChart()
          })


      },
      getAllPaidTotal (record, total) {
        let all_paid_total = parseFloat(record.all_paid_total)
        let actual = parseFloat(total.actual)
        return `${(all_paid_total + actual).toFixed(2)}`
      },
      handlePlanEdit(record) {
        this.planModal.type = 'edit'
        this.planModal.record = record
        this.planModal.visible = true
        console.log(this.planModal.record)
      },

      handleCloseTable () {
        this.planModal.type = ''
        this.planModal.record = null
        this.planModal.visible = false
        this.getData()
    },
      initChart () {
// 把配置和数据放这里
        let app = {}
        let option = null  //${(parseFloat(this.card.project_construction_other_fee) + parseFloat(this.decisionTotal.actual)).toFixed(2)}
        let data = {
          'name': `总投资：${(parseFloat(this.card.total_investment_of_construction_project) + parseFloat(this.decisionTotal.actual)+ parseFloat(this.applicationTotal.actual)+ parseFloat(this.bid_card.agency_fee)+ parseFloat(this.designTotal.paid_total)).toFixed(2)}元；总计划：${this.plan.total_investment_of_construction_project}元`,
          'children': [
            { 'name': `工程造价：实际：${(parseFloat(this.card.project_cost) + parseFloat(this.decisionTotal.actual)+ parseFloat(this.applicationTotal.actual)+ parseFloat(this.bid_card.agency_fee)+ parseFloat(this.designTotal.paid_total)).toFixed(2)}元；计划：${this.plan.project_cost}元` ,
              'children': [
                {
                  'name': `工程费：实际：${this.card.project_fee}元；计划：${this.plan.project_fee}元`,
                  'children': [
                    { 'name': `建筑工程费：实际：${this.card.construction_work_fee}元；计划：${this.plan.construction_work_fee}元` },
                    { 'name': `安装工程费：实际：${this.card.installation_work_fee}元；计划：${this.plan.installation_work_fee}元` },
                    { 'name': `设置预备费：实际：${this.card.device_preset_fee}元；计划：${this.plan.device_preset_fee}元` }
                  ]
                },
                { 'name': `工程建设其他费：实际：${(parseFloat(this.card.project_construction_other_fee) + parseFloat(this.decisionTotal.actual)+ parseFloat(this.applicationTotal.actual)+ parseFloat(this.bid_card.agency_fee)+ parseFloat(this.designTotal.paid_total)).toFixed(2)}元；计划：${this.plan.project_construction_other_fee}元`,
                  'children': [
                    { 'name': `土地使用费和其他补偿费：实际：${this.card.land_use_fee}元；计划：${this.plan.land_use_fee}元` },
                    { 'name': `建设管理费：实际：${this.card.construction_management_fee}元；计划：${this.plan.construction_management_fee}元` },
                    { 'name': `可行性研究费：实际：${this.decisionTotal.actual}元；计划：${this.plan.feasibility_study_fee}元` },
                    { 'name': `专项评价费：实际：${this.card.special_evaluation_fee}元；计划：${this.plan.special_evaluation_fee}元` },
                    { 'name': `研究试验费：实际：${this.card.research_experiment_fee}元；计划：${this.plan.research_experiment_fee}元` },
                    { 'name': `勘察设计费：实际：${this.designTotal.paid_total}元；计划：${this.plan.reconnaissance_design_fee}元` },
                    { 'name': `场地准备费和临时设施费：实际：${this.card.site_preparation_fee}元；计划：${this.plan.site_preparation_fee}元` },
                    { 'name': `引进技术和进口设备材料其他费：实际：${this.card.imported_technology_fee}元；计划：${this.plan.imported_technology_fee}元` },
                    { 'name': `特殊设备安全监督检验费：实际：${this.card.special_equipment_fee}元；计划：${this.plan.special_equipment_fee}元` },
                    { 'name': `市政公用配套设施费：实际：${this.applicationTotal.actual}元；计划：${this.plan.municipal_administration_fee}元` },
                    { 'name': `联合试运转费：实际：${this.card.joint_commissioning_fee}元；计划：${this.plan.joint_commissioning_fee}元` },
                    { 'name': `工程保险费：实际：${this.card.engineer_insurance_fee}元；计划：${this.plan.engineer_insurance_fee}元` },
                    { 'name': `专利及专有技术使用费：实际：${this.card.patent_fee}元；计划：${this.plan.patent_fee}元` },
                    { 'name': `生产准备费：实际：${this.card.production_preparation_fee}元；计划：${this.plan.production_preparation_fee}元` },
                    { 'name': `监理费：实际：${this.card.supervisor_fee}元；计划：${this.plan.supervisor_fee}元` },
                    { 'name': `造价咨询：实际：${this.card.cost_consultation_fee}元；计划：${this.plan.cost_consultation_fee}元` },
                    { 'name': `招标代理：实际：${this.bid_card.agency_fee}元；计划：${this.plan.bid_agency_fee}元` },
                    { 'name': `其他：实际：${this.card.other_fee}元；计划：${this.plan.other_fee}元` },
                  ]
                },
                { 'name': `预备费：实际：${this.card.prepare_fee}元；计划：${this.plan.prepare_fee}元` }
              ]
            },
            {
              'name': `增值税：实际：${this.card.value_added_tax}元；计划：${this.plan.value_added_tax}元`,
            },
            {
              'name': `资金筹措费：实际：${this.card.fund_preparation_fee}元；计划：${this.plan.fund_preparation_fee}元`,
            },
            { 'name': `流动资金：实际：${this.card.fluid_capital}元；计划：${this.plan.fluid_capital}元` }
          ]

        }
        echarts.util.each(data.children, function (datum, index) {
          index % 2 === 0 && (datum.collapsed = true)
        })
        this.chart.setOption({
          tooltip: {
            trigger: 'item',
            triggerOn: 'mousemove'
          },
          series: [
            {
              type: 'tree',

              data: [data],

              top: '1%',
              left: '10%',
              bottom: '1%',
              right: '25%',

              symbolSize: 10,

              label: {
                normal: {
                  position: 'bottom',
                  verticalAlign: 'middle',
                  align: 'middle',
                  fontSize: 10
                }
              },

              leaves: {
                label: {
                  normal: {
                    position: 'bottom',
                    verticalAlign: 'middle',
                    align: 'left'
                  }
                }
              },

              expandAndCollapse: true,
              animationDuration: 550,
              animationDurationUpdate: 750
            }
          ]

        })
        if (option && typeof option === 'object') {
          this.chart.setOption(option, true)
        }
      }
    },
    computed: {
      applicationTotal () {
        let ret = {
          estimate: '0',
          actual: '0'
        }
        if (this.applicationList.length > 0) {
          ret = {
            estimate: this.applicationList.map((item) => {
              return parseFloat(item.estimate)
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            actual: this.applicationList.map((item) => {
              let actual = item.actual
              if (actual) {
                return parseFloat(actual)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2)
          }
        }
        return ret
      },
      decisionTotal () {
        let ret = {
          estimate: '0',
          actual: '0'
        }
        if (this.decisionList.length > 0) {
          ret = {
            estimate: this.decisionList.map((item) => {
              return parseFloat(item.estimate)
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            actual: this.decisionList.map((item) => {
              let actual = item.actual
              if (actual) {
                return parseFloat(actual)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2)
          }
        }
        return ret
      },
      designTotal () {
        let ret = {
          paid_total: '0'
        }
        if (this.designCompanyList.length > 0) {
          ret = {
            paid_total: this.designCompanyList.map((item) => {
              return parseFloat(item.paid_total)
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2)
          }
        }
        return ret
      },
      plan () {
        let cmp = {
          construction_work_fee: '0',
          installation_work_fee: '0',
          device_preset_fee: '0',
          land_use_fee: '0',
          construction_management_fee: '0',
          feasibility_study_fee: '0',
          special_evaluation_fee: '0',
          research_experiment_fee: '0',
          reconnaissance_design_fee: '0',
          site_preparation_fee: '0',
          imported_technology_fee: '0',
          special_equipment_fee: '0',
          municipal_administration_fee: '0',
          joint_commissioning_fee: '0',
          engineer_insurance_fee: '0',
          patent_fee: '0',
          production_preparation_fee: '0',
          supervisor_fee: '0',
          cost_consultation_fee: '0',
          bid_agency_fee: '0',
          other_fee: '0',
          prepare_fee: '0',
          value_added_tax: '0',
          fund_preparation_fee: '0',
          fluid_capital: '0',
          project_construction_other_fee: '0',
          project_fee: '0',
          project_cost: '0',
          total_investment_of_construction_project: '0'
        }
        if (this.treeList.length > 0) {
          cmp = {
            construction_work_fee: this.treeList.map((item) => {
              let construction_work_fee = item.construction_work_fee
              if (construction_work_fee) {
                return parseFloat(construction_work_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            installation_work_fee: this.treeList.map((item) => {
              let installation_work_fee = item.installation_work_fee
              if (installation_work_fee) {
                return parseFloat(installation_work_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            device_preset_fee: this.treeList.map((item) => {
              let device_preset_fee = item.device_preset_fee
              if (device_preset_fee) {
                return parseFloat(device_preset_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            land_use_fee: this.treeList.map((item) => {
              let land_use_fee = item.land_use_fee
              if (land_use_fee) {
                return parseFloat(land_use_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            construction_management_fee: this.treeList.map((item) => {
              let construction_management_fee = item.construction_management_fee
              if (construction_management_fee) {
                return parseFloat(construction_management_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            feasibility_study_fee: this.treeList.map((item) => {
              let feasibility_study_fee = item.feasibility_study_fee
              if (feasibility_study_fee) {
                return parseFloat(feasibility_study_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            special_evaluation_fee: this.treeList.map((item) => {
              let special_evaluation_fee = item.special_evaluation_fee
              if (special_evaluation_fee) {
                return parseFloat(special_evaluation_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            research_experiment_fee: this.treeList.map((item) => {
              let research_experiment_fee = item.research_experiment_fee
              if (research_experiment_fee) {
                return parseFloat(research_experiment_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            reconnaissance_design_fee: this.treeList.map((item) => {
              let reconnaissance_design_fee = item.reconnaissance_design_fee
              if (reconnaissance_design_fee) {
                return parseFloat(reconnaissance_design_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            site_preparation_fee: this.treeList.map((item) => {
              let site_preparation_fee = item.site_preparation_fee
              if (site_preparation_fee) {
                return parseFloat(site_preparation_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            imported_technology_fee: this.treeList.map((item) => {
              let imported_technology_fee = item.imported_technology_fee
              if (imported_technology_fee) {
                return parseFloat(imported_technology_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            special_equipment_fee: this.treeList.map((item) => {
              let special_equipment_fee = item.special_equipment_fee
              if (special_equipment_fee) {
                return parseFloat(special_equipment_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            municipal_administration_fee: this.treeList.map((item) => {
              let municipal_administration_fee = item.municipal_administration_fee
              if (municipal_administration_fee) {
                return parseFloat(municipal_administration_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            joint_commissioning_fee: this.treeList.map((item) => {
              let joint_commissioning_fee = item.joint_commissioning_fee
              if (joint_commissioning_fee) {
                return parseFloat(joint_commissioning_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            engineer_insurance_fee: this.treeList.map((item) => {
              let engineer_insurance_fee = item.engineer_insurance_fee
              if (engineer_insurance_fee) {
                return parseFloat(engineer_insurance_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            patent_fee: this.treeList.map((item) => {
              let patent_fee = item.patent_fee
              if (patent_fee) {
                return parseFloat(patent_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            production_preparation_fee: this.treeList.map((item) => {
              let production_preparation_fee = item.production_preparation_fee
              if (production_preparation_fee) {
                return parseFloat(production_preparation_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            supervisor_fee: this.treeList.map((item) => {
              let supervisor_fee = item.supervisor_fee
              if (supervisor_fee) {
                return parseFloat(supervisor_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
           cost_consultation_fee: this.treeList.map((item) => {
              let cost_consultation_fee = item.cost_consultation_fee
              if (cost_consultation_fee) {
                return parseFloat(cost_consultation_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
             bid_agency_fee: this.treeList.map((item) => {
              let bid_agency_fee = item.bid_agency_fee
              if (bid_agency_fee) {
                return parseFloat(bid_agency_fee)
              } else {
                return 0
              }
             }).reduce((x, y) => {
               return x + y
             }).toFixed(2),
            other_fee: this.treeList.map((item) => {
              let other_fee = item.other_fee
              if (other_fee) {
                return parseFloat(other_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            prepare_fee: this.treeList.map((item) => {
              let prepare_fee = item.prepare_fee
              if (prepare_fee) {
                return parseFloat(prepare_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            value_added_tax: this.treeList.map((item) => {
              let value_added_tax = item.value_added_tax
              if (value_added_tax) {
                return parseFloat(value_added_tax)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            fund_preparation_fee: this.treeList.map((item) => {
              let fund_preparation_fee = item.fund_preparation_fee
              if (fund_preparation_fee) {
                return parseFloat(fund_preparation_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            fluid_capital: this.treeList.map((item) => {
              let fluid_capital = item.fluid_capital
              if (fluid_capital) {
                return parseFloat(fluid_capital)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            project_construction_other_fee: this.treeList.map((item) => {
              let project_construction_other_fee = item.project_construction_other_fee
              if (project_construction_other_fee) {
                return parseFloat(project_construction_other_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            project_fee: this.treeList.map((item) => {
              let project_fee = item.project_fee
              if (project_fee) {
                return parseFloat(project_fee)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            project_cost: this.treeList.map((item) => {
              let project_cost = item.project_cost
              if (project_cost) {
                return parseFloat(project_cost)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2),
            total_investment_of_construction_project: this.treeList.map((item) => {
              let total_investment_of_construction_project = item.total_investment_of_construction_project
              if (total_investment_of_construction_project) {
                return parseFloat(total_investment_of_construction_project)
              } else {
                return 0
              }
            }).reduce((x, y) => {
              return x + y
            }).toFixed(2)

          }
        }
        return cmp
      }

    }
  }
</script>
<style scoped>

</style>