<template>
    <page-layout title="进度控制文件">
    <a-card v-if="scheduleList.length > 0">
      <a-row :gutter="60">
        <a-col
          :xs="{span: 24}"
          :sm="{span: 8}"
          :xl="{span: 6}"
          v-for="item in scheduleList">
          <card
            :key="item.id"
            :id="item.id"
            :title="item.title"
            toName="scheduleDetail">
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
   import api from '../../../api/scheduleControl'
  export default {
    name: 'ScheduleFile',
    components: {
      Card,
      PageLayout
    },
    data () {
      return {
        scheduleList: []
      }
    },
    mounted () {
      api.getScheduleFileList(this.$store.getters.projectSelected.id)
        .then(data => {
          this.scheduleList = data.sort((x, y) => {
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
