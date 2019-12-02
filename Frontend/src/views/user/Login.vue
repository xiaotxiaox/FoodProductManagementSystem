<template>
  <div class="main">
    <a-form
      id="formLogin"
      class="user-layout-login"
      ref="formLogin"
      :form="form"
      @submit="handleSubmit">
      <a-form-item style="margin-top: 24px; text-align: center; font-size: 20px;">
        用户登录
      </a-form-item>
      <a-form-item style="margin-top: 40px">
        <a-input
          size="large"
          type="text"
          autocomplete="true"
          placeholder="用户名"
          v-decorator="[
            'username',
            {rules: [{ required: true, message: '请输入用户名' }], validateTrigger: 'blur'}
          ]">
          <a-icon slot="prefix" type="user" :style="{ color: 'rgba(0,0,0,.25)' }"/>
        </a-input>
      </a-form-item>
      <a-form-item>
        <a-input
          size="large"
          type="password"
          autocomplete="true"
          placeholder="密码"
          v-decorator="[
            'password',
            {rules: [{ required: true, message: '请输入密码' }], validateTrigger: 'blur'}
          ]">
          <a-icon slot="prefix" type="lock" :style="{ color: 'rgba(0,0,0,.25)' }"/>
        </a-input>
      </a-form-item>
      <a-form-item style="margin-top:24px">
        <a-button
          size="large"
          type="primary"
          htmlType="submit"
          class="login-button"
          :loading="state.loginBtn"
          :disabled="state.loginBtn">
          登陆
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import { timeFix } from '@/utils/util'

export default {
  name: 'Login',
  data () {
    return {
      form: this.$form.createForm(this),
      state: {
        loginBtn: false
      }
    }
  },
  methods: {
    ...mapActions(['Login', 'Logout']),
    handleSubmit (e) {
      e.preventDefault()
      this.form.validateFields((error, data) => {
        if (!error) {
          this.Login(data)
            .then((data) => {
              this.state.loginBtn = true
              this.loginSuccess(data)
            })
        } else {
          this.state.loginBtn = true
          setTimeout(() => {
            this.state.loginBtn = false
          }, 500)
        }
      })
    },
    loginSuccess (data) {
      this.$router.push({ name: 'project' })
      setTimeout(() => {
        this.$notification.success({
          message: `欢迎`,
          description: `${timeFix()} ${this.$store.getters.username}，欢迎回来`
        })
      }, 1000)
    }
  }
}
</script>

<style lang="less" scoped>
  .user-layout-login {
    label {
      font-size: 14px;
    }

    button.login-button {
      padding: 0 15px;
      font-size: 16px;
      height: 40px;
      width: 100%;
    }
  }
</style>
