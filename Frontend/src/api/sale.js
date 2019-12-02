import fetchAPI from '../utils/fetch'

export default {
  //用户信息管理
  getCustomerInfoList (project_id) {
    return fetchAPI('implement/contract/', 'get', null, { project_id })
  },
  createCustomerInfo (project_id, data) {
    return fetchAPI('implement/contract/', 'post', data, { project_id })
  },
  updateCustomerInfo (contract_id, data) {
    return fetchAPI(`implement/contract/${contract_id}/`, 'put', data)
  },
  deleteCustomerInfo (contract_id) {
    return fetchAPI(`implement/contract/${contract_id}/`, 'delete')
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