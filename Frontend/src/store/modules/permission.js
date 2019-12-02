import { asyncRouterMap, constantRouterMap } from '@/config/router.config'
import _ from 'lodash'

/**
 * 过滤账户是否拥有某一个权限，并将菜单从加载列表移除
 *
 * @param permissionList
 * @param route
 * @returns {boolean}
 */
function hasPermission (permissionList, route) {
  let ret = true
  if (route.meta && route.meta.permission) {
    route.meta.permission.forEach(permission => {
      if (permissionList.indexOf(permission) === -1) {
        ret = false
      }
    })
  }
  return ret
}

function filterAsyncRouter (routerMap, roles) {
  return routerMap.filter(route => {
    if (hasPermission(roles.permissionList, route)) {
      if (route.children && route.children.length) {
        route.children = filterAsyncRouter(route.children, roles)
      }
      return true
    }
    return false
  })
}

const permission = {
  state: {
    routers: constantRouterMap,
    addRouters: []
  },
  mutations: {
    SET_ROUTERS: (state, addRouters) => {
      state.routers.push(...addRouters)
      state.addRouters = addRouters
    }
  },
  actions: {
    GenerateRoutes ({ commit, state }, data) {
      return new Promise(resolve => {
        const { roles } = data
        const accessedRouters = filterAsyncRouter(_.cloneDeep(asyncRouterMap), roles)
        commit('SET_ROUTERS', accessedRouters)
        resolve()
      })
    }
  }
}

export default permission
