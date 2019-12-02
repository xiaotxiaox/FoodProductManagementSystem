<template>
    <page-layout title="投资控制文件">
    <a-card v-if="investFileList.length > 0">
      <a-row :gutter="60">
        <a-col
          :xs="{span: 24}"
          :sm="{span: 8}"
          :xl="{span: 6}"
          v-for="item in investFileList">
          <card
            :key="item.id"
            :id="item.id"
            :title="item.title"
            toName="investDetail">
          </card>
        </a-col>
      </a-row>
    </a-card>
    <a-card v-else>
      <a-skeleton active/>
    </a-card>
  </page-layout>
</template>

<script>
   import PageLayout from '../../../components/page/PageLayout'
   import Card from './components/Card'
   import api from '../../../api/investControl'
  export default {
    name: 'InvestFile',
     components: {
      Card,
      PageLayout
    },
    data () {
      return {
        investFileList: []
      }
    },
    mounted () {
      api.getInvestFileList(this.$store.getters.projectSelected.id)
        .then(data => {
          this.investFileList = data.sort((x, y) => {
            return x.id < y.id
          })
        })
    },
    computed: {

    }
  }
</script>

<style scoped>

</style>