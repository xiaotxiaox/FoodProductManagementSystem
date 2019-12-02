import fetchAPI from '../utils/fetch'

export default {
  getImage (project_id) {
    return fetchAPI(`project/addressList/image/`, 'get', null, {project_id})
  },
  getAddressList (params) {
    return fetchAPI(`project/addressList/`, 'get', null, params)
  },
  createAddressList (data, params) {
    return fetchAPI('project/addressList/', 'post', data, params)
  },
  updateAddressList (data) {
    return fetchAPI(`project/addressList/${data.id}/`, 'put', data)
  },
  deleteAddressListDetail (id) {
    return fetchAPI(`project/addressList/${id}`, 'delete')
  },
  getTypeList () {
    return fetchAPI('project/companyType/', 'get')
  },
  createImage (project_id,data) {
    return fetchAPI('project/addressList/image/', 'post', data,{ project_id })
  },
  deleteImage (project_id) {
    return fetchAPI('project/addressList/image/', 'delete', {project_id})
  }
}
