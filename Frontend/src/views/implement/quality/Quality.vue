<template>
  <div>
    <quality-modal
      :record="qualityModal.record"
      :visible="qualityModal.visible"
      :type="qualityModal.type"
      :quality_id="qualityModal.building_id"
      v-if="qualityModal.visible"
      @close="handleQualityClose()">
    </quality-modal>
    <emphasis-modal
      :record="emphasisModal.record"
      :visible="emphasisModal.visible"
      :type="emphasisModal.type"
      :difficulty_id="emphasisModal.difficulty_id"
      v-if="emphasisModal.visible"
      @close="handleEmphasisClose()">
    </emphasis-modal>
    <a-card style="margin-bottom: 16px">
      <a-row>
        <a-col
          :xs="{ span: 24 }"
          :sm="{ span: 24 }"
          :xl="{ span: 24 }">
          <div style="width: 50%;height:100%;float: left">
            <div id="echartContainer" style="width: 100%; height: 400px;"></div>
          </div>
          <div style="width: 50%;height:100%;float: right">
            <div id="main" style="width: 100%;height: 400px;"></div>
          </div>
        </a-col>
      </a-row>
    </a-card>
    <a-card style="margin-bottom: 16px">
      <a-spin :spinning="status.listLoading">
        <a-table
          v-if="this.project_category==='1'"
          bordered
          :columns="columns1"
          :dataSource="qualityList"
          rowKey="building_id"
          :pagination="false">
          <template slot="operation" slot-scope="text, record, index">
            <a-button @click="handleQualityEdit(record)">编辑</a-button>
          </template>
        </a-table>
         <a-table
          v-else
          bordered
          :columns="columns2"
          :dataSource="qualityList"
          rowKey="building_id"
          :pagination="false">
          <template slot="operation" slot-scope="text, record, index">
            <a-button @click="handleQualityEdit(record)">编辑</a-button>
          </template>
        </a-table>
      </a-spin>
      <a-col
        :xs="{span: 24}"
        :sm="{span: 20, offset: 2}"
        :xl="{span: 16, offset: 4}">
      </a-col>
    </a-card>
    <a-card style="margin-bottom: 16px">
      <a-spin :spinning="status.listLoading">
        <a-table
          bordered
          :columns="emphasisColumns"
          :dataSource="emphasisList"
          rowKey="difficulty_id"
          :pagination="false">
          <template slot="operation" slot-scope="text, record, index">
            <a-button @click="handleEmphasisEdit(record)">编辑</a-button>
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
  import echarts from 'echarts'
  import QualityModal from './components/QualityModal'
  import EmphasisModal from './components/EmphasisModal'
  import apiProject from '../../../api/project'
  import TableModal from './components/TableModal'
  import api from '../../../api/implement'
  import {mapGetters} from 'vuex'

  const columns1 = [
    {
      title: '建筑名称',
      dataIndex: 'name',
      width: '20%',
      align: 'center'
    },
    {
      title: '奖项',
      dataIndex: 'awards',
      customRender: (text, record) => {
        if (record.awards === '1')
          return '市级'
        else if (record.awards === '2')
          return '省级'
        else if (record.awards === '3')
          return '国级'
      },
      width: '10%',
      align: 'center'
    },
    {
      title: '基础',
      dataIndex: 'base',
      customRender: (text, record) => {
        if (record.base === '1')
          return '合格'
        else if (record.base === '2')
          return '不合格'
        else if (record.base === '3')
          return '严重不合格'
      },
      width: '10%',
      align: 'center'
    },
    {
      title: '主体',
      dataIndex: 'mainBody',
      customRender: (text, record) => {
        if (record.mainBody === '1')
          return '合格'
        else if (record.mainBody === '2')
          return '不合格'
        else if (record.mainBody === '3')
          return '严重不合格'
      },
      width: '10%',
      align: 'center'
    },
    {
      title: '装饰',
      dataIndex: 'decorate',
      customRender: (text, record) => {
        if (record.decorate === '1')
          return '合格'
        else if (record.decorate === '2')
          return '不合格'
        else if (record.decorate === '3')
          return '严重不合格'
      },
      width: '10%',
      align: 'center'
    },
    {
      title: '屋面',
      dataIndex: 'roofing',
      customRender: (text, record) => {
        if (record.roofing === '1')
          return '合格'
        else if (record.roofing === '2')
          return '不合格'
         else if (record.roofing === '3')
          return '严重不合格'
      },
      width: '10%',
      align: 'center'
    },
    {
      title: '防水',
      dataIndex: 'waterproof',
      customRender: (text, record) => {
        if (record.waterproof === '1')
          return '合格'
        else if (record.waterproof === '2')
          return '不合格'
        else if (record.waterproof === '3')
          return '严重不合格'
      },
      width: '10%',
      align: 'center'
    },
    {
      title: '保温',
      dataIndex: 'insulation',
      customRender: (text, record) => {
        if (record.insulation === '1')
          return '合格'
        else if (record.insulation === '2')
          return '不合格'
        else if (record.insulation === '3')
          return '严重不合格'
      },
      width: '10%',
      align: 'center'
    },
    {
      title: '编辑',
      dataIndex: 'operation',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ]
  const columns2 = [
    {
      title: '建筑名称',
      dataIndex: 'name',
      width: '20%',
      align: 'center'
    },
    {
      title: '奖项',
      dataIndex: 'awards',
      customRender: (text, record) => {
        if (record.awards === '1')
          return '市级'
        else if (record.awards === '2')
          return '省级'
        else if (record.awards === '3')
          return '国级'
      },
      width: '10%',
      align: 'center'
    },
    {
      title: '基础',
      dataIndex: 'base',
      customRender: (text, record) => {
        if (record.base === '1')
          return '合格'
        else if (record.base === '2')
          return '不合格'
        else if (record.base === '3')
          return '严重不合格'
      },
      width: '10%',
      align: 'center'
    },
    {
      title: '路层',
      dataIndex: 'choice_A',
      customRender: (text, record) => {
        if (record.choice_A === '1')
          return '合格'
        else if (record.choice_A === '2')
          return '不合格'
        else if (record.choice_A === '3')
          return '严重不合格'
      },
      width: '10%',
      align: 'center'
    },
    {
      title: '路面',
      dataIndex: 'choice_B',
      customRender: (text, record) => {
        if (record.choice_B === '1')
          return '合格'
        else if (record.choice_B === '2')
          return '不合格'
        else if (record.choice_B === '3')
          return '严重不合格'
      },
      width: '10%',
      align: 'center'
    },
    {
      title: '桥涵',
      dataIndex: 'choice_C',
      customRender: (text, record) => {
        if (record.choice_C === '1')
          return '合格'
        else if (record.choice_C === '2')
          return '不合格'
         else if (record.choice_C === '3')
          return '严重不合格'
      },
      width: '10%',
      align: 'center'
    },
    {
      title: '路灯',
      dataIndex: 'choice_D',
      customRender: (text, record) => {
        if (record.choice_D === '1')
          return '合格'
        else if (record.choice_D === '2')
          return '不合格'
        else if (record.choice_D === '3')
          return '严重不合格'
      },
      width: '10%',
      align: 'center'
    },
    {
      title: '绿化',
      dataIndex: 'choice_E',
      customRender: (text, record) => {
        if (record.choice_E === '1')
          return '合格'
        else if (record.choice_E === '2')
          return '不合格'
        else if (record.choice_E === '3')
          return '严重不合格'
      },
      width: '10%',
      align: 'center'
    },
    {
      title: '编辑',
      dataIndex: 'operation',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ]
  const emphasisColumns = [
    {
      title: '重点',
      dataIndex: 'keyPoint',
      width: '25%',
      align: 'center'
    },
    {
      title: '难点',
      dataIndex: 'difficulty',
      width: '25%',
      align: 'center'
    },
    {
      title: '通病',
      dataIndex: 'CommonFailing',
      width: '25%',
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
    name: "Quality",
    components: {
      QualityModal,
      EmphasisModal,
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
        project_category:null,
        columns1,
        columns2,
        qualityList: [],
        emphasisColumns,
        emphasisList: [],
        not_num: '',
        reply_num: '',
        option1: {
          tooltip: {},
          xAxis: {
            data: [],
            axisLabel: {
              show: true,
              textStyle: {
                fontSize: 20
              }
            }
          },
          yAxis: {
            axisLabel: {
              show: true,
              textStyle: {
                fontSize: 14
              },
              formatter: function (value) {
                var texts = [];
                if (value == "1") {
                  texts.push('市级');
                } else if (value == "2") {
                  texts.push('省级');
                } else if (value == "3") {
                  texts.push('国级');
                }
                return texts;
              }
            }
          },
          series: [{
            label: {
              textStyle: {
                fontSize: 50
              }
            },
            name: '奖项',
            type: 'bar',
            barWidth: 50,
            data: [1, 2, 3, 3],
            itemStyle: {
              normal: {
                //每根柱子颜色设置
                color: function (params) {
                  let colorList = [
                    '#00B0F0'
                  ]
                  return colorList[params.dataIndex]
                }
              }
            }
          }]
        },
        chart1: null,
        charts: '',
        opinion: ['已回复', '未回复'],
        opinionData: [
          {value: 50, name: '已回复', itemStyle: {color: '#00b0f0'}},
          {value: 50, name: '未回复', itemStyle: {color: '#7030a0'}},
        ],
        qualityModal: {
          record: null,
          visible: false,
          type: '1',
          quality_id: this.id
        },
        emphasisModal: {
          record: null,
          visible: false,
          type: '1',
          emphasis_id: this.id
        },
        tableModal: {
          record: null,
          visible: false,
          table_id: this.id
        },
        form: this.$form.createForm(this),
        project_id: this.projectSelected().id
      }
    },
    mounted() {
      this.chart1 = echarts.init(document.getElementById('echartContainer'))
      this.chart1.setOption(this.option1, true)
      window.onresize = this.chart1.resize
      this.getData()
      this.$nextTick(function () {
        this.drawPie('main')
      })
    },
    methods: {
      ...mapGetters(['projectSelected']),
      getData() {
        apiProject.getProject(this.projectSelected().id)
          .then(data => {
            this.project_category = data.category
          })
        api.getQualityList(this.project_id)
          .then(data => {
            console.log(data)
            this.qualityList = data
            this.change()
            this.status.listLoading = false
          })
        api.getDifficultyList(this.project_id)
          .then(data => {
            this.emphasisList = data
          })
        api.getContractNotice(this.project_id)
          .then(data => {
            this.not_num = data.not_num
            this.reply_num = data.reply_num
            this.opinionData[0].value=this.reply_num
            this.opinionData[1].value=this.not_num
            this.$nextTick(function () {
              this.drawPie('main')
            })
          })
      },
      change() {
        var arrName = []
        var arrProgress = []
        for (var building of this.qualityList) {
          console.log(this.qualityList)
          console.log(building)
          arrName.push(building.name)
          arrProgress.push(building.awards)
        }
        // console.log(building)
        // console.log(arrName)
        // console.log(arrProgress)
        this.chart1.setOption({
          tooltip: {},
          xAxis: {
            data: arrName,
            axisLabel: {
              show: true,
              textStyle: {
                fontSize: 20
              }
            }
          },
          yAxis: {
            axisLabel: {
              show: true,
              textStyle: {
                fontSize: 14
              },
              formatter: function (value) {
                var texts = [];
                if (value == "1") {
                  texts.push('市级');
                } else if (value == "2") {
                  texts.push('省级');
                } else if (value == "3") {
                  texts.push('国级');
                }
                return texts;
              }
            }
          },
          series: [{
            label: {
              textStyle: {
                fontSize: 50
              }
            },
            name: '总计',
            type: 'bar',
            barWidth: 50,
            data: arrProgress,
            itemStyle: {
              normal: {
                //每根柱子颜色设置
                color: '#1890FF'
              }
            }
          }]
        }, true)

      },
      drawPie(id) {
        this.charts = echarts.init(document.getElementById(id))
        this.charts.setOption({
          tooltip: {
            trigger: 'item',
          },
          legend: {
            orient: 'vertical',
            x: 'left',
            data: this.opinion
          },
          series: [
            {
              name: '数量',
              type: 'pie',
              radius: ['0%', '70%'],
              avoidLabelOverlap: false,
              label: {
                normal: {
                  show: true,
                  position: 'inside'
                },
                emphasis: {
                  show: true,
                  textStyle: {
                    fontSize: '30',
                    fontWeight: 'bold'
                  }
                }
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: this.opinionData
            }
          ]
        })
      },
      handleQualityClose() {
        this.qualityModal.type = ''
        this.qualityModal.record = null
        this.qualityModal.visible = false
        this.getData()
      },
      handleQualityEdit(record) {
        this.qualityModal.type = 'edit'
        this.qualityModal.record = record
        this.qualityModal.visible = true
      },
      handleEmphasisClose() {
        this.emphasisModal.type = ''
        this.emphasisModal.record = null
        this.emphasisModal.visible = false
        this.getData()
      },
      handleEmphasisEdit(record) {
        this.emphasisModal.type = 'edit'
        this.emphasisModal.record = record
        this.emphasisModal.visible = true
        console.log(this.emphasisModal.record)
      },
    }
  }
</script>

<style scoped>

</style>