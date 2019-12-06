import fetchAPI from '../utils/fetch'

export default {
  //用户信息管理
  getServicerList () {
    return fetchAPI('stuff/normal', 'get', null)
  },
  createServicer (data) {
    return fetchAPI('stuff/normal', 'post', data)
  },
  updateServicer (id, data) {
    return fetchAPI(`stuff/normal/${id}`, 'put', data)
  },
  deleteServicer (id) {
    return fetchAPI(`stuff/normal/${id}`, 'delete')
  },
  getManagerList () {
    return fetchAPI('stuff/manager', 'get', null)
  },
  createManager (data) {
    return fetchAPI('stuff/manager', 'post', data)
  },
  updateManager (id, data) {
    return fetchAPI(`stuff/manager/${id}`, 'put', data)
  },
  deleteManager (id) {
    return fetchAPI(`stuff/manager/${id}`, 'delete')
  }
}