<template>
    <div>
      <a-card>
        <a-col
        :xs="{span: 24}"
        :sm="{span: 20}"
        :xl="{span: 16}">
          <div style="width: 50%;height:100%;float: left">
             <div id="main" style="width: 100%;height: 400px;"></div>
          </div>
          <div style="width: 50%;height:100%;float: right">造价指标：
            <a-form
              :form="form">
              <a-form-item
                label="平方单价"
                v-bind="layout">
                <a-input
                  type="number"
                  addonAfter="元/平方米"
                  v-decorator="[
              'estimate',
              {initialValue: application.estimate ? parseFloat(application.estimate) : null}]
              ">
                </a-input>
              </a-form-item>
              <a-form-item
                label="配套："
                v-bind="layout">
                <a-input
                  type="number"
                  addonAfter="元/平方米"
                  v-decorator="[
              'actual',
              {initialValue: application.actual ? parseFloat(application.actual) : null}]
              ">
                </a-input>
              </a-form-item>
              <a-form-item
                label="基础："
                v-bind="layout">
                <a-input
                  type="number"
                  addonAfter="元/平方米"
                  v-decorator="[
              'actual',
              {initialValue: application.actual ? parseFloat(application.actual) : null}]
              ">
                </a-input>
              </a-form-item>
              <a-form-item
                label="施工："
                v-bind="layout">
                <a-input
                  type="number"
                  addonAfter="元/平方米"
                  v-decorator="[
              'actual',
              {initialValue: application.actual ? parseFloat(application.actual) : null}]
              ">
                </a-input>
              </a-form-item>
              <a-form-item
                label="装饰："
                v-bind="layout">
                <a-input
                  type="number"
                  addonAfter="元/平方米"
                  v-decorator="[
              'actual',
              {initialValue: application.actual ? parseFloat(application.actual) : null}]
              ">
                </a-input>
              </a-form-item>
              <a-form-item
                label="室外："
                v-bind="layout">
                <a-input
                  type="number"
                  addonAfter="元/平方米"
                  v-decorator="[
              'actual',
              {initialValue: application.actual ? parseFloat(application.actual) : null}]
              ">
                </a-input>
              </a-form-item>
              <a-form-item>
                <a-button
                  style="float: right"
                  type="primary"
                  @click="handleSubmit()">
                  保存
                </a-button>
                <a-button
                  style="float: right; margin-right: 10px"
                  @click="handleEdit(record)">
                  编辑
                </a-button>
              </a-form-item>
            </a-form>
          </div>

      </a-col>
      </a-card>
    </div>
</template>

<script>
    import echarts from 'echarts'
    import api from '../../../../api/implement'
    import {mapGetters} from 'vuex'
  export default {
    name: 'invest_module',
     props: {
      id: Number
    },
    data () {
      return {
        layout: {
          'label-col': { span: 6 },
          'wrapper-col': { span: 18 }
        },
        form: this.$form.createForm(this),
        application: {},
        matterList: [],
        chart1: null,
        charts: null,
        opinion: ['已回复', '未回复'],
        opinionData: [
        {value: 40, name: '已回复', itemStyle: {color: '#00b0f0'}},
        {value: 60, name: '未回复', itemStyle: {color: '#7030a0'}},
      ]
      }

    },
    mounted () {
      this.getData()
      this.$nextTick(function () {
        this.drawPie('main')
      })
    },

    methods: {
       ...mapGetters(['projectSelected']),
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
                    fontWeight: 'blod'
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
      getData () {
        api.getApplicationMatterList(this.id)
          .then(data => {
            this.matterList = data
            this.status.loading=false
          })
        api.getApplication(this.id)
          .then(data => {
            this.application = data
          })
      },
      handleSubmit () {
        this.form.validateFields((error, data) => {
          if (!error) {
            data.title = this.application.title
            api.updateApplication(this.id, data)
              .then(data => {
                this.$notification.success({ message: '成功', description: '修改成功', key: 'SUCCESS' })
              })
          }
        })
      },
      handleClose () {
        this.modal.type = ''
        this.modal.record = null
        this.modal.visible = false
        this.getData()
      },
      handleCreate () {
        this.modal.type = 'create'
        this.modal.record = null
        this.modal.visible = true
      },
      handleEdit (record) {
        this.modal.type = 'edit'
        this.modal.record = record
        this.modal.visible = true
      },
      handleDelete (record) {
        api.deleteApplicationMatter(record.matter_id)
          .then(data => {
            this.$notification.success({ message: '成功', description: '删除成功', key: 'SUCCESS' })
            this.getData()
          })
      },
      parseFloat (f) {
        return parseFloat(f)
      }
    }
  }
</script>

<style scoped lang="less">
  .item {
    margin: 10px 0;
  }

</style>