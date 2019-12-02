<template>
  <div ref="gantt"></div>
</template>

<script>
import 'dhtmlx-gantt'
import '../../../../../node_modules/dhtmlx-gantt/codebase/locale/locale_cn.js'
import '../../../../../node_modules/dhtmlx-gantt/codebase/dhtmlxgantt.css'
import api from '../../../../api/implement'
import { mapGetters } from 'vuex'
import moment from 'moment'

var colHeader = '<div class ="gantt_grid_head_cell gantt_grid_head_add" onclick ="gantt.createTask()"> </div>'
var colContent = function (task) {
  return ('<button type="button" class="ant-btn ant-btn-default"   onclick="gantt.showLightbox(' + task.id + ')">编辑</button>')
}

var opts = [
  { key: '1', label: '关键' },
  { key: '2', label: '一般' },
]
export default {
  name: 'gantt',
  props: {
    project_id: String,
    tasks: Object
  },
  data () {
    return {
      infor: Object,
      initialGantt: Object,


    }

  },
  mounted () {
    this.getData()
  },
  methods: {
    ...mapGetters(['projectSelected']),
    getData () {
      console.log(this.project_id)
      var temp_id = this.project_id
      console.log(temp_id)
      api.getInitialChart(this.project_id)
        .then(data => {
          this.initialGantt = data[0]
          var num = parseInt(data[0].interval)
          var sDate = data[0].start_date.split('-')
          var start = new Date(sDate[0], sDate[1], sDate[2])
          var oDate = data[0].end_date.split('-')
          var oDate1 = new Date(oDate[0], oDate[1], oDate[2])
          var iDays = parseInt(Math.abs(oDate1 - start) / 1000 / 60 / 60 / 24)
          gantt.config.start_date = new Date(sDate[0], sDate[1] - 1, sDate[2])
          gantt.templates.task_text = function (s, e, task) {
            return task.name
          }
          gantt.config.scales = [
            {
              unit: 'day', step: iDays, format: function (date) {
                var sDate = data[0].start_date.split('-')
                var sDate1 = new Date(sDate[0], sDate[1], sDate[2])
                var eDate = data[0].end_date.split('-')
                var eDate1 = new Date(eDate[0], eDate[1], eDate[2])
                var max = parseInt(Math.abs(eDate1 - sDate1) / 1000 / 60 / 60 / 24)
                var dateToStr = gantt.date.date_to_str('%Y-%m-%d')
                var rDate = dateToStr(date).split('-')
                var rDate1 = new Date(rDate[0], rDate[1], rDate[2])
                var result = parseInt((rDate1 - sDate1) / 1000 / 60 / 60 / 24)
                if (result <= max) {
                  return data[0].start_date + '到' + data[0].end_date
                } else {
                  return '超过预期'
                }
              }
            },
            {
              unit: 'day', step: num, format: function (date) {
                var dateToStr = gantt.date.date_to_str('%Y-%m-%d')
                var aDate = dateToStr(date).split('-')
                var oDate1 = new Date(aDate[0], aDate[1], aDate[2])
                var sDate = data[0].start_date.split('-')
                var start = new Date(sDate[0], sDate[1], sDate[2])
                var iDays = parseInt(Math.abs(oDate1 - start) / 1000 / 60 / 60 / 24)
                return iDays
              }
            }
          ]
          //设置图标区域的日期坐标最大值
          gantt.config.drag_resize = false
          gantt.config.drag_move = false
          gantt.config.drag_lightbox = true
          gantt.config.drag_links = false
          gantt.config.show_progress = false
          gantt.config.autofit = true
          //设置图标区域间隔
          gantt.config.columns = [
            { id: 1, name: 'chart_id', label: '编号', width: 50, align: 'center' },
            { id: 2, name: 'name', label: '名称', width: 75, align: 'center' },
            { id: 3, name: 'start_date', label: '开始时间', width: 86, align: 'center', format: '%YY-%mm-%dd' },
            { id: 4, name: 'end_date', label: '截止时间', width: 86, align: 'center' },
            { id: 5, name: 'status', label: '状态', width: 32, align: 'center' },
            {
              name: 'buttons',
              label: colHeader,
              width: 95,
              template: colContent
            }
          ]

          gantt.init(this.$refs.gantt)
          gantt.parse(this.$props.tasks)
          //为弹窗按钮添加函数
          //删除delete
          gantt.attachEvent('onLightboxDelete', function (id) {
            var task = gantt.getTask(id)
            console.log(gantt.getTask(id))
            api.deleteProgress(task.chart_id)
              .then(data => {
                gantt.hideLightbox()
              })
            return false
          })
          //取消cancle
          gantt.attachEvent('onLightboxCancle', function (id) {
            gantt.hideLightbox()
            return false
          })
          //保存save

          gantt.attachEvent('onLightboxSave', function (id, task, is_new) {
            console.log("2222222222222222")
            var temp = {}
            var dateToStr2 = gantt.date.date_to_str('%Y-%m-%d')
            temp.name = task.name
            temp.start_date = dateToStr2(task.start_date)
            temp.end_date = dateToStr2(task.end_date)
            temp.state = task.state
            temp.status = task.status
            this.infor = temp
            if (is_new) {
              api.createProgress(temp_id, this.infor)
                .then(data => {
                  gantt.hideLightbox()

                })
              console.log("23123213123123123")
            }
            else {
              api.updateProgress(task.chart_id, this.infor)
                .then(data => {
                  gantt.updateTask(task.id)
                  gantt.hideLightbox()
                })
            }
            return false
          })
          //自定义弹窗
          // gantt.locale.labels.section_chart_id = '编号'
          gantt.locale.labels.section_name = '名称'
          gantt.locale.labels.section_sta = '状态'
          gantt.config.lightbox.sections = [
            // { name: 'chart_id', height: 28, map_to: 'chart_id', type: 'textarea', focus: true },
            { name: 'name', height: 28, map_to: 'name', type: 'textarea', focus: true, default_value: '新任务' },
            {
              name: 'time',
              height: 35,
              map_to: { start_date: 'start_date', end_date: 'end_date' },
              type: 'time',
              year_range: [1950, 2100]
            },
            { name: 'sta', height: 28, map_to: 'state', type: 'select', options: opts }
          ]
          gantt.templates.lightbox_header = function (start_date, end_date, task) {
            return (gantt.templates.task_text(task.start_date, task.end_date, task) || '').substr(0, 70)
          }

        })
    },
    change () {
      api.getProgressList(this.project_id)
        .then(data => {
          this.tasks.data = data
          for (var temp of this.tasks.data) {
            var sDate = temp.start_date.split('-')
            temp.start_date = new Date(sDate[0], sDate[1] - 1, sDate[2])
            var eDate = temp.end_date.split('-')
            temp.end_date = new Date(eDate[0], eDate[1] - 1, eDate[2])
            temp.chart_id = '' + temp.chart_id + ''
            if (temp.state == 1) {
              temp.color = 'red'
              temp.status = '关键'
            } else {
              temp.color = 'blue'
              temp.status = '一般'
            }
          }
        })
    }
  },
}
</script>

<style scoped lang="less">
  .gantt_cal_light_wide.gantt_cal_light_full {
    width: 550px;
  }
</style>