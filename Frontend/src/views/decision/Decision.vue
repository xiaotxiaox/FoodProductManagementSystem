<template>
  <page-layout title="项目决策">
    <a-card v-if="decisionList.length > 0">
      <a-row>
        <a-button
          type="primary"
          @click="$router.push({name: 'DecisionEdit',id:project_id})" style="float: right;">
          编辑
        </a-button>
        <p>提示：绿色表示完成，黄色表示正在办理，红色表示无法办理，紫色表示不用办理 ，灰色表示尚未办理。</p>
      </a-row>
      <a-row :gutter="60">
        <a-col
          :xs="{span: 24}"
          :sm="{span: 8}"
          :xl="{span: 6}"
          v-for="item in decisionList">
          <card
            :key="item.decision_id"
            :id="item.decision_id"
            :title="item.title"
            :estimate="item.estimate"
            :status="item.status"
            :actual="item.actual"
            toName="decisionDetail">
          </card>
        </a-col>
      </a-row>
      <a-row>
        <total-card
          :estimate="total.estimate"
          :actual="total.actual">
        </total-card>
      </a-row>
    </a-card>
    <a-card v-else>
      <a-skeleton active/>
    </a-card>
  </page-layout>
</template>

<script>
  import PageLayout from '../../components/page/PageLayout'
  import Card from './components/Card'
  import TotalCard from './components/TotalCard'
  import api from '../../api/decision'
  import { mapGetters } from 'vuex'

  export default {
    name: 'Decision',
    components: {
      Card,
      TotalCard,
      PageLayout
    },
    data () {
      return {
        decisionList: [],
        project_id: this.projectSelected().id
      }
    },
    mounted () {
      api.getDecisionList(this.$store.getters.projectSelected.id)
        .then(data => {
          this.decisionList = data.sort((x, y) => {
            return x.decision_id > y.decision_id
          })
        })
    },
    methods:{
      ...mapGetters(['projectSelected'])
    },
    computed: {
      total () {
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
      }
    }
  }
</script>

<style scoped lang="less">

</style>
