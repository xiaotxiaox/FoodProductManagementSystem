<template>
  <div class="footer">
    <div class="copyright">
      Copyright
      <a-icon type="copyright"></a-icon>
      {{ year }}
      <span>
        <a href="https://ie.wh.sdu.edu.cn/" target="_blank">山东大学（威海）机电与信息工程学院</a>
      </span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LayoutFooter',
  data () {
    return {
      year: (new Date()).getFullYear()
    }
  }
}
</script>

<style lang="less" scoped>
  .footer {
    padding: 0 16px;
    margin: 48px 0 24px;
    text-align: center;

    .copyright {
      color: rgba(0, 0, 0, .45);
      font-size: 14px;

      a {
        color: rgba(0, 0, 0, .45);
      }
    }
  }
</style>
