<template>
  <a-row>
    <a-col
      :xs="{ span: 0 }"
      :sm="{ span: 2 }"
      :xl="{ span: 2 }"
      style="text-align: center;">
      <br>
      <a-icon type="bank" theme="filled" style="font-size: 40px;"></a-icon>
    </a-col>
    <a-col
      :xs="{ span: 20 }"
      :sm="{ span: 18 }"
      :xl="{ span: 18 }">
      <div>名称：{{ project.name }}</div>
      <div>编号：{{ project.id }}</div>
      <div>开工日期：{{ project.starting_date }}</div>
      <div>预计竣工日期：{{ project.finishing_date }}</div>
      <div>建设地点：{{ project.place }}</div>
    </a-col>
    <a-col
      :xs="{ span: 4 }"
      :sm="{ span: 4 }"
      :xl="{ span: 4 }"
      style="text-align: center">
      <br>
      <div>已经安全生产了</div>
      <div :class="[{expire: isExpire}]"><span style="font-size: 26px">{{ safeDay }}</span> &nbsp;<span>天</span></div>
    </a-col>
  </a-row>
</template>

<script>
  import moment from 'moment'

  export default {
    name: 'ProjectCard',
    props: {
      project: Object
    },
    computed: {
      safeDay () {
        let today = new moment()
        let starting_day = new moment(this.project.starting_date)
        let finishing_day = new moment(this.project.finishing_date)
        return `${today.diff(starting_day, 'days') + 1} / ${finishing_day.diff(starting_day, 'days') + 1}`
      },
      isExpire () {
        let today = new moment()
        let finishing_day = new moment(this.project.finishing_date)
        return today.diff(finishing_day, 'days') > 0
      }
    }
  }
</script>

<style scoped lang="less">
  @import '../../../../public/color';

  .expire {
    color: @red-6;
    font-weight: bold;
  }
</style>
