<template>
  <page-layout :title="'仪表盘'">
    <a-button @click="handle">123</a-button>
  </page-layout>
</template>

<script>
import PageLayout from '../../components/page/PageLayout'

export default {
  name: 'Dashboard',
  components: {
    PageLayout
  },
  data () {
    return { project: {} }
  },
  mounted () {
  },
  methods: {
    handle () {
      window.history.go(0)
    }
  }
}
</script>

<style lang="stylus">

</style>
