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
  getSalePolicyList (project_id) {
    return fetchAPI('implement/contract/', 'get', null, { project_id })
  },
  createSalePolicy (project_id, data) {
    return fetchAPI('implement/contract/', 'post', data, { project_id })
  },
  updateSalePolicy (contract_id, data) {
    return fetchAPI(`implement/contract/${contract_id}/`, 'put', data)
  },
  deleteSalePolicy (contract_id) {
    return fetchAPI(`implement/contract/${contract_id}/`, 'delete')
  }
}