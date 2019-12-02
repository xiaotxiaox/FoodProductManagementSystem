<template>
       <page-layout title="工作记录">
    <a-card v-if="workRecodingList.length > 0">
      <a-row :gutter="60">
        <a-col
          :xs="{span: 24}"
          :sm="{span: 8}"
          :xl="{span: 6}"
          v-for="item in workRecodingList">
          <card
            :key="item.id"
            :id="item.id"
            :title="item.title"
            toName="workRecodingDetail">
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
    name: 'WorkRecoding',
     components: {
      Card,
      PageLayout
    },
    data () {
      return {
        workRecodingList: []
      }
    },
    mounted () {
      api.getWorkRecodingList(this.$store.getters.projectSelected.id)
        .then(data => {
          this.workRecodingList = data.sort((x, y) => {
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