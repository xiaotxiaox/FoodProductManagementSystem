import Vue from 'vue'
import Vuex from 'vuex'

import app from './modules/app'
import user from './modules/user'
import permission from './modules/permission'
import project from './modules/project'
import getters from './getters'
import { TaskNodeStore } from 'vue-task-node'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    app,
    user,
    permission,
    project,
    TaskNodeStore
  },
  state: {

  },
  mutations: {

  },
  actions: {

  },
  getters
})
