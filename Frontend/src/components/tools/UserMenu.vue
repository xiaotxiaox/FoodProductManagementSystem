<template>
  <div class="user-wrapper">
    <span class="action">
      <router-link :to="{ name: 'help' }">
        <a-icon type="question-circle-o"></a-icon>
      </router-link>
    </span>
    <!--<header-notice class="name"/>-->
    <a-dropdown>
      <span class="action ant-dropdown-link user-dropdown-menu">
        <a-icon type="user"></a-icon>
        <span> {{ username() }}</span>
      </span>
      <a-menu slot="overlay" class="user-dropdown-menu-wrapper">
        <a-menu-item key="0">
          <router-link :to="{ name: 'setting' }">
            <a-icon type="setting"/>
            <span>设置</span>
          </router-link>
        </a-menu-item>
        <a-menu-item key="1">
          <a @click="handleLogout">
            <a-icon type="logout"/>
            <span>注销</span>
          </a>
        </a-menu-item>
      </a-menu>
    </a-dropdown>
  </div>
</template>

<script>
// import HeaderNotice from './HeaderNotice'
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'UserMenu',
  // components: {
  //   HeaderNotice
  // },
  methods: {
    ...mapActions(['Logout']),
    ...mapGetters(['username']),
    handleLogout () {
      const that = this

      this.$confirm({
        title: '提示',
        content: '真的要注销登录吗 ?',
        onOk () {
          return that.Logout({}).then(() => {
            window.location.reload()
          }).catch(err => {
            that.$message.error({
              title: '错误',
              description: err.message
            })
          })
        },
        onCancel () {
        }
      })
    }
  }
}
</script>
