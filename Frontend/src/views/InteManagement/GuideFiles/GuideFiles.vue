<template>
    <page-layout title="指导文件">
    <a-card v-if="guideList.length > 0">
      <a-row :gutter="60">
        <a-col
          :xs="{span: 24}"
          :sm="{span: 8}"
          :xl="{span: 6}"
          v-for="item in guideList">
          <card
            :key="item.id"
            :id="item.id"
            :title="item.title"
            toName="guideDetail">
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
   import api from '../../../api/InteManagement'
  export default {
    name: 'GuideFiles',
    components: {
      Card,
      PageLayout
    },
    data () {
      return {
        guideList: []
      }
    },
    mounted () {
      api.getGuideList(this.$store.getters.projectSelected.id)
        .then(data => {
          this.guideList = data.sort((x, y) => {
            return x.id < y.id
          })
        })
    },
    computed: {}
  }
</script>

<style lang="stylus">

</style>
