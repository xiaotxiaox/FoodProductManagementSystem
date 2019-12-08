import fetchAPI from '../utils/fetch'

export default {
  //用户信息管理
  getBatchList () {
    return fetchAPI('workshop/unqualified/qualified', 'get', null)
  },
  getDisqualifiedList () {
    return fetchAPI('workshop/unqualified/unqualified', 'get', null)
  },
  getSecurityNotice () {
    return fetchAPI('workshop/unqualified/statistics', 'get', null)
  },
  createBatch (data) {
    return fetchAPI('sale/custom/', 'post', data)
  },
  updateBatch (id, data) {
    console.log(data)
    return fetchAPI(`workshop/unqualified/${id}/`, 'put', data)
  },
  deleteBatch (id) {
    return fetchAPI(`sale/custom/${id}/`, 'delete')
  }
}