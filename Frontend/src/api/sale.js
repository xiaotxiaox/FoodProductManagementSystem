import fetchAPI from '../utils/fetch'

export default {
  //用户信息管理
  getCustomerInfoList () {
    return fetchAPI('sale/custom/', 'get', null)
  },
  createCustomerInfo (data) {
    return fetchAPI('sale/custom/', 'post', data)
  },
  updateCustomerInfo (id, data) {
    return fetchAPI(`sale/custom/${id}/`, 'put', data)
  },
  deleteCustomerInfo (id) {
    return fetchAPI(`sale/custom/${id}/`, 'delete')
  },
  getSalePolicyList () {
    return fetchAPI('sale/policy/', 'get', null)
  },
  createSalePolicy (data) {
    console.log(2)
    return fetchAPI('sale/policy/', 'post', data)
  },
  updateSalePolicy (id, data) {
    console.log(data)
    return fetchAPI(`sale/policy/${id}/`, 'put', data)
  },
  deleteSalePolicy (id) {
    return fetchAPI(`sale/policy/${id}/`, 'delete')
  },
  getSumList () {
    return fetchAPI('sale/order/', 'get', null)
  },
  getOrderSellList () {
    return fetchAPI('sale/order/sell', 'get', null)
  },
  getStatistics () {
    return fetchAPI('sale/order/statistics', 'get', null)
  },
  getOrderBackList () {
    return fetchAPI('sale/order/back', 'get', null)
  },
  createOrder (data) {
    return fetchAPI('sale/order/', 'post', data)
  },
  updateOrder (id, data) {
    return fetchAPI(`sale/order/${id}/`, 'put', data)
  },
  deleteOrder (id) {
    return fetchAPI(`sale/order/${id}/`, 'delete')
  },
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