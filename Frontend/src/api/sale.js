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
  }
}