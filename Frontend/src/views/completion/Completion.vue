<template>
  <page-layout title="项目竣工">
    <a-card v-if="completionList.length > 0">
      <a-row :gutter="60">
        <a-col
          :xs="{span: 24}"
          :sm="{span: 8}"
          :xl="{span: 6}"
          v-for="item in completionList">
          <card
            :key="item.id"
            :id="item.id"
            :title="item.title"
            toName="documentDetail">
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
   import PageLayout from '../../components/page/PageLayout'
   import Card from './components/Card'
   import api from '../../api/completion'
   export default {
   name: 'completionDocument',
   components: {
      Card,
      PageLayout
    },
    data () {
      return {
        completionList: []
      }
    },
    mounted () {
      api.getCompletionList(this.$store.getters.projectSelected.id)
        .then(data => {
          this.completionList = data.sort((x, y) => {
            return x.id < y.id
          })
        })
    },
    computed: {

    }
}
</script>

<style lang="stylus">

</style>
