import api from '../../api/user'

const user = {
  state: {
    id: '',
    username: '',
    role: []
  },
  mutations: {
    SET_ID: (state, id) => {
      state.id = id
    },
    SET_USERNAME: (state, username) => {
      state.username = username
    },
    SET_ROLE: (state, role) => {
      state.role = role
    }
  },
  actions: {
    // 登录
    Login ({ commit }, user) {
      return api.login(user.username, user.password)
    },

    // 获取用户信息
    GetInfo ({ commit }) {
      return new Promise((resolve, reject) => {
        api.getInfo().then(response => {
          const result = response
          console.log(result.role)
          if (result.role && result.role.permissions.length > 0) {
            const role = result.role
            role.permissions = result.role.permissions
            role.permissions.map(per => {
              if (per.actions != null && per.actions.length > 0) {
                per.actionList = per.actions.map(action => {
                  return action.name
                })
              }
            })
            role.permissionList = role.permissions.map(permission => {
              return permission.name
            })
            commit('SET_ROLE', result.role)
            commit('SET_ID', result.id)
            commit('SET_USERNAME', result.username)
          } else {
            reject(new Error('getInfo: roles must be a non-null array !'))
          }
          resolve(response)
        })
      })
    },

    // 登出
    Logout ({ commit }) {
      return new Promise((resolve) => {
        commit('SET_ID', '')
        commit('SET_USERNAME', '')
        commit('SET_ROLE', [])
        api.logout().then(() => {
          resolve()
        })
      })
    }

  }
}

export default user
