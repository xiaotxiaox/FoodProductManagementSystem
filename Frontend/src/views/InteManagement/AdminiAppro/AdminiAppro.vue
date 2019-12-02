<template>
    <page-layout title="行政审批">
    <a-card v-if="approvalList.length > 0">
      <a-row :gutter="60">
        <a-col
          :xs="{span: 24}"
          :sm="{span: 8}"
          :xl="{span: 6}"
          v-for="item in approvalList">
          <card
            :key="item.id"
            :id="item.id"
            :title="item.title"
            toName="approvalDetail">
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
    name: 'adminiAppro',
    components: {
      Card,
      PageLayout
    },
    data () {
      return {
        approvalList: []
      }
    },
    mounted () {
      api.getAdminiApproList(this.$store.getters.projectSelected.id)
        .then(data => {
          console.log(data)
          this.approvalList = data.sort((x, y) => {
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
