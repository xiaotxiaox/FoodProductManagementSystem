<template>
  <div>
    <a-card style="margin-bottom: 16px">
      <a-row>
        <a-col
          :xs="{ span: 24 }"
          :sm="{ span: 12 }"
          :xl="{ span: 12 }">
          <a-col style="text-align: center"
                 :xs="{ span: 24 }"
                 :sm="{ span: 16,offset:4 }"
                 :xl="{ span: 17,offset:4}">
            <a-row>
              <iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=55"
                      style="border:solid 1px #7ec8ea" height="300" frameborder="0" marginwidth="0" marginheight="0"
                      scrolling="no"></iframe>
            </a-row>
            <a-row>
              <a-card title="安全生产" :bordered=true>
                <p style="text-align: center">{{Days}}天</p>
              </a-card>
            </a-row>
          </a-col>
        </a-col>
        <a-col
          :xs="{ span: 24 }"
          :sm="{ span: 12 }"
          :xl="{ span: 12 }">
          <div id="app">
            <div id="main" style="width: 100%; height: 450px;"></div>
          </div>
        </a-col>
      </a-row>
    </a-card>
    <security-modal
      :record="securityModal.record"
      :visible="securityModal.visible"
      :type="securityModal.type"
      :table_id="securityModal.risk_id"
      v-if="securityModal.visible"
      @close="handleSecurityClose()">
    </security-modal>
    <a-card style="margin-bottom: 16px">
      <a-row style="margin-bottom: 5px">
        <a-col
          class="item"
          :xs="{ span: 24 }"
          :sm="{ span: 12 ,offset:12}"
          :xl="{ span: 4 ,offset:20}">
          <!--<a-button-->
            <!--type="primary"-->
            <!--icon="plus"-->
            <!--style="width: 100%"-->
            <!--@click="handleSecurityCreate()">-->
            <!--新建-->
          <!--</a-button>-->
        </a-col>
      </a-row>
      <a-spin :spinning="status.listLoading">
        <a-table
          bordered
          :columns="columns"
          :dataSource="matterList"
          rowKey="risk_id"
          :pagination="false">
          <template slot="risk_id" slot-scope="text, record, index">
            <div>{{index+1}}</div>
          </template>
          <template slot="files" slot-scope="text, record, index">
            <a-button @click="$router.push({'name': 'implementSecurityDetail', params: {id: record.risk_id}})">
              附件管理
            </a-button>
          </template>
          <!--<template slot="operation" slot-scope="text, record, index">-->
            <!--<a-button @click="handleSecurityEdit(record)">编辑</a-button>-->
            <!--<a-popconfirm-->
              <!--title="确认删除吗?"-->
              <!--@confirm="handleSecurityDelete(record)">-->
              <!--<a-button type="danger">删除</a-button>-->
            <!--</a-popconfirm>-->
          <!--</template>-->
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
import SecurityModal from './components/SecurityModal'
import api from '../../../api/implement'
import { mapGetters } from 'vuex'
import ACol from 'ant-design-vue/es/grid/Col'
import ARow from 'ant-design-vue/es/grid/Row'
import moment from 'moment'

const columns = [
  {
    title: '序号',
    dataIndex: 'risk_id',
    scopedSlots: { customRender: 'risk_id' },
    width: '10%',
    align: 'center'
  },
  {
    title: '内容',
    dataIndex: 'risk_content',
    width: '20%',
    align: 'center'
  },
  {
    title: '部位',
    dataIndex: 'part',
    width: '15%',
    align: 'center'
  },
  {
    title: '附件',
    dataIndex: 'files',
    scopedSlots: { customRender: 'files' },
    width: '15%',
    align: 'center'
  },

  {
    title: '风险等级',
    dataIndex: 'risk_rank',
    width: '18%',
    align: 'center'
  },
{
    title: '检查结果',
    dataIndex: 'result',
    width: '18%',
    align: 'center'
  }]
  // {
  //   title: '编辑',
  //   dataIndex: 'operation',
  //   scopedSlots: { customRender: 'operation' },
  //   align: 'center'
  // }]
// const columnHead = [
//   {
//     title: '类别',
//     dataIndex: 'type',
//     width: '15%',
//     align: 'center'
//   },
//   {
//     title: '职位',
//     dataIndex: 'position',
//     width: '18%',
//     align: 'center'
//   },
//   {
//     title: '姓名',
//     dataIndex: 'name',
//     width: '20%',
//     align: 'center'
//   },
//   {
//     title: '联系电话',
//     dataIndex: 'phone',
//     width: '25%',
//     align: 'center'
//   },
//   {
//     title: '编辑',
//     dataIndex: 'operation',
//     scopedSlots: { customRender: 'operation' },
//     align: 'center'
//   }
// ]
export default {
  name: 'Security',
  components: {
    ARow,
    ACol,
    SecurityModal
  },
  props: {
    id: Number
  },
  data () {
    return {
      status: {
        listLoading: true,
        tableLoading: true
      },
      charts: '',
      opinion: ['已回复', '未回复'],
      opinionData: [
        { value: 40, name: '已回复', itemStyle: { color: '#00b0f0' } },
        { value: 60, name: '未回复', itemStyle: { color: '#7030a0' } },
      ],
      layout: {
        'label-col': { span: 6 },
        'wrapper-col': { span: 18 }
      },
      form: this.$form.createForm(this),
      securityModal: {
        record: null,
        visible: false,
        type: '1',
        risk_id: this.id
      },
      columns,
      matterList: [],
      project_id: this.projectSelected().id,
      starting_date: '',
      Days: ''
    }
  },
  mounted () {
    this.$nextTick(function () {
      this.drawPie('main')
    })
    this.getData()
  },
  methods: {
    ...mapGetters(['projectSelected']),
    getData () {
      this.status.listLoading = false
      this.status.tableLoading = false
      api.getSecurityList(this.project_id)
        .then(data => {
          this.matterList = data
          this.status.listLoading = false
        })
      api.getStartDate(this.project_id)
        .then(data => {
          this.status.listLoading = false
          this.starting_date = data.starting_date
          let today = new moment()
          let starting_day = new moment(this.starting_date)
          this.Days =`${today.diff(starting_day, 'days') + 1} `
        })
      api.getSecurityNotice(this.project_id)
          .then(data => {
            this.not_num = data.not_num
            this.reply_num = data.reply_num
            this.opinionData[0].value=this.reply_num
            this.opinionData[1].value=this.not_num
            this.$nextTick(function () {
              this.drawPie('main')
            })
          })
      // api.getSecurityHeadList(this.project_id)
      //   .then(data => {
      //     this.headList = data
      //     this.status.tableLoading = false
      //     console.log(1)
      //   })
    },
    drawPie (id) {
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
    handleSecurityClose () {
      this.securityModal.type = ''
      this.securityModal.record = null
      this.securityModal.visible = false
      this.getData()
    },
    handleSecurityCreate () {
      this.securityModal.type = 'create'
      this.securityModal.record = null
      this.securityModal.visible = true
    },
    handleSecurityEdit (record) {
      this.securityModal.type = 'edit'
      this.securityModal.record = record
      this.securityModal.visible = true
    },
    handleSecurityDelete (record) {
      api.deleteSecurity(record.risk_id)
        .then(data => {
          this.$notification.success({ message: '成功', description: '删除成功', key: 'SUCCESS' })
        })
    }
  }
}
</script>

<style scoped>
  .test {
    color: #c82333;
  }
</style>