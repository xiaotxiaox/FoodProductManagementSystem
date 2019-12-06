import fetchAPI from '../utils/fetch'

export default {
  getProjectList (params) {
    return fetchAPI('project/', 'get', null, params)
  },
  getProjectStatistics (params) {
    return fetchAPI('project/statistics/', 'get', null)
  },
  createProject (project) {
    return fetchAPI('project/', 'post', project)
  },
  // getProject (params,id) {
  //   return fetchAPI(`sale/order/one/`, 'get', null, params:id)
  // },
  getProject (id) {
    return fetchAPI('sale/order/one/', 'get', null, { id })
  },
  updateProject (project) {
    return fetchAPI(`project/${project.id}/`, 'put', project)
  },
  deleteProject (id) {
    return fetchAPI(`project/${id}/`, 'delete')
  },
  getScaleList () {
    return fetchAPI('project/scale/', 'get')
  },
  getPropertyList () {
    return fetchAPI('project/property/', 'get')
  },
  getPurposeList () {
    return fetchAPI('project/purpose/', 'get')
  },
  getFundSourcesList () {
    return fetchAPI('project/fundSources/', 'get')
  },
  getStyleList () {
    return fetchAPI('project/style/', 'get')
  },
  validateID (id) {
    return fetchAPI('project/checkID/', 'post', { id })
  },
  //System settings
  deleteSettingMatter (matter_id) {
    return fetchAPI(`project/settings/matter/${matter_id}/`, 'delete')
  },
  getMapList (project_id) {
    return fetchAPI('project/location/', 'get', null, { project_id })
  },
  updateMapList (project_id,data) {
    return fetchAPI(`project/location/${project_id}/`, 'put', data)
  },
  createMapList (project_id,data) {
    return fetchAPI(`project/location/`, 'post', data,{project_id})
  },

}
