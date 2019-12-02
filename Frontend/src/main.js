import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/'
import { VueAxios } from '@/utils/request' // axios 不建议引入到 Vue 原型链上
import Vuex from 'vuex'
import {TaskNode} from 'vue-task-node'
import 'vue-task-node/dist/css/vnode.css'
Vue.config.productionTip = false

import './core/use'
import bootstrap from './core/bootstrap'
import '@/permission' // permission control
import '@/utils/filter' // global filter

Vue.config.productionTip = false

Vue.use(VueAxios, router)
Vue.use(TaskNode)
Vue.use(Vuex)



new Vue({
  router,
  store,
  created () {
    bootstrap()
  },
  render: h => h(App)
}).$mount('#app')


