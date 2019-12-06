import fetchAPI from '../utils/fetch'

export default {
  //用户信息管理
  getBatchList () {
    return fetchAPI('batch', 'get', null)
  },
  createBatch (data) {
    return fetchAPI('sale/custom/', 'post', data)
  },
  updateBatch (id, data) {
    return fetchAPI(`sale/custom/${id}/`, 'put', data)
  },
  deleteBatch (id) {
    return fetchAPI(`sale/custom/${id}/`, 'delete')
  }
}