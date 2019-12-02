<template>
  <a-row>
    <a-col
      :xs="{ span: 0 }"
      :sm="{ span: 2 }"
      :xl="{ span: 2 }"
      style="text-align: center;">
      <a-icon type="bank" theme="filled" style="font-size: 40px;"></a-icon>
    </a-col>
    <a-col
      :xs="{ span: 20 }"
      :sm="{ span: 18 }"
      :xl="{ span: 18 }">
      <div>名称：{{ project.name }}</div>
      <div>编号：{{ project.id }}</div>
      <div>开工日期：{{ project.starting_date }}</div>
      <div>建设地点：{{ project.place }}</div>
    </a-col>
    <a-col
      :xs="{ span: 4 }"
      :sm="{ span: 4 }"
      :xl="{ span: 4 }"
      style="text-align: center">
      <br>
      <div>已经安全生产了</div>
      <div style="font-size: 24px">{{ safeDay }}天</div>
    </a-col>
  </a-row>
</template>

<script>
export default {
  name: 'ProjectCard',
  props: {
    project: Object
  },
  computed: {
    safeDay () {
      let today = new Date()
      let starting_day = new Date(this.project.starting_date)
      return Math.floor((today - starting_day) / (24 * 3600 * 1000) + 1)
    }
  }
}
</script>

<style scoped>

</style>