<template>
  <a-card class="card">
    <p>总计</p>
    <p>预算: {{estimate}}万元</p>
    <p>实际: {{actual}}万元</p>
  </a-card>
</template>

<script>
  export default {
    name: 'TotalCard',
    props: {
      estimate: String,
      actual: String
    }
  }
</script>

<style scoped lang="less">
  @import '~ant-design-vue/es/style/themes/default';

  .card {
    text-align: center;
    margin: 30px 0;
    border-radius: @border-radius-base;
    box-shadow: 0 0 5px @shadow-color;
  }
</style>