import Vue from 'vue'
import router from './router'
import store from './store'
import cookie from 'js-cookie'

import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import notification from 'ant-design-vue/es/notification'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['login'] // no redirect whitelist

router.beforeEach((to, from, next) => {
  NProgress.start() // start progress bar
  const isLogin = cookie.get('sessionid')
  if (isLogin) {
    if (to.path === '/user/login') {
      next({ path: '/project' })
      NProgress.done()
    } else {
      if (store.getters.role.length === 0) {
        store
          .dispatch('GetInfo')
          .then(res => {
            const roles = res && res.role
            store.dispatch('GenerateRoutes', { roles }).then(() => {
              router.addRoutes(store.getters.addRouters)
              const redirect = decodeURIComponent(from.query.redirect || to.path)
              if (to.path === redirect) {
                next({ ...to, replace: true })
              } else {
                next({ path: redirect })
              }
            })
          })
          .catch(() => {
            notification.error({
              message: '错误',
              description: '请求用户信息失败，请重试'
            })
            store.dispatch('Logout')
              .then(() => {
                next({ path: '/user/login', query: { redirect: to.fullPath } })
              })
          })
      } else {
        next()
      }
    }
  } else {
    if (whiteList.includes(to.name)) {
      next()
    } else {
      next({ path: '/user/login', query: { redirect: to.fullPath } })
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done()
})

/**
 * Action 权限指令
 * 指令用法：
 *  - 在需要控制 name 级别权限的组件上使用 v-name:[method] , 如下：
 *    <i-button v-action:add >添加用户</a-button>
 *    <a-button v-action:delete>删除用户</a-button>
 *    <a v-action:edit @click="edit(record)">修改</a>
 *
 *  - 当前用户没有权限时，组件上使用了该指令则会被隐藏
 *  - 当后台权限跟 pro 提供的模式不同时，只需要针对这里的权限过滤进行修改即可
 *
 *  @see https://github.com/sendya/ant-design-pro-vue/pull/53
 */
const action = Vue.directive('action', {
  bind: function (el, binding, vnode) {
    const actionName = binding.arg
    const role = store.getters.role
    const permission = vnode.context.$route.meta.permission
    let actions = []
    role.permissions.forEach(p => {
      if (p.name !== permission[0]) {
        return
      }
      actions = p.actionList
    })
    if (actions.indexOf(actionName) < 0) {
      setTimeout(() => {
        if (el.parentNode == null) {
          el.style.display = 'none'
        } else {
          el.parentNode.removeChild(el)
        }
      }, 10)
    }
  }
})

export {
  action
}
