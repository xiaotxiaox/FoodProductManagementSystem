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
      <div>编号：{{ project.id }}</div>
      <div>客户姓名：{{ project.custom.name }}</div>
      <div>订货日期：{{ project.orderDate }}</div>
      <div>提货日期：{{ project.pickDate }}</div>
      <div>订单状态：{{ project.status }}</div>
    </a-col>
    <a-col
      :xs="{ span: 4 }"
      :sm="{ span: 4 }"
      :xl="{ span: 4 }"
      style="text-align: center">
      <br>
      <div>订单开始时间</div>
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
        let orderDate = new moment(this.project.orderDate)
        let pickDate = new moment(this.project.pickDate)
        return `${today.diff(orderDate, 'days') + 1} / ${pickDate.diff(orderDate, 'days') + 1}`
      },
      isExpire () {
        let today = new moment()
        let pickDate = new moment(this.project.pickDate)
        return today.diff(pickDate, 'days') > 0
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
