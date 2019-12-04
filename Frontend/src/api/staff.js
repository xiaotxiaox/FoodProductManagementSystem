import fetchAPI from '../utils/fetch'

export default {
  //用户信息管理
  getServicerList () {
    return fetchAPI('stuff/normal/', 'get', null)
  },
  createServicer (data) {
    return fetchAPI('stuff/normal/', 'post', data)
  },
  updateServicer (id, data) {
    return fetchAPI(`stuff/normal/${id}/`, 'put', data)
  },
  deleteServicer (id) {
    return fetchAPI(`stuff/normal/${id}/`, 'delete')
  }
}