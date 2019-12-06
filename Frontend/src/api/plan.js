import fetchAPI from '../utils/fetch'

export default {
  //用户信息管理
  getProductList () {
    return fetchAPI('goods', 'get', null)
  },
  createProduct (data) {
    return fetchAPI('goods', 'post', data)
  },
  updateProduct (id, data) {
    return fetchAPI(`goods/${id}/`, 'put', data)
  },
  deleteProduct (id) {
    return fetchAPI(`goods/${id}/`, 'delete')
  },
}