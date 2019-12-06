import fetchAPI from '../utils/fetch'

export default {
  //工厂管理
  getFactoryList () {
    return fetchAPI('workshop/factory', 'get', null)
  },
  createFactory (data) {
    return fetchAPI('workshop/factory', 'post', data)
  },
  updateFactory (id, data) {
    return fetchAPI(`workshop/factory/${id}`, 'put', data)
  },
  deleteFactory (id) {
    return fetchAPI(`workshop/factory/${id}`, 'delete')
  },
  //车间管理
  getDepartmentList () {
    return fetchAPI('workshop/department', 'get', null)
  },
  createDepartment (data) {
    return fetchAPI('workshop/department', 'post', data)
  },
  updateDepartment (id, data) {
    return fetchAPI(`workshop/department/${id}`, 'put', data)
  },
  deleteDepartment (id) {
    return fetchAPI(`workshop/department/${id}`, 'delete')
  },
  //班组管理
  getTeamList () {
    return fetchAPI('workshop/team', 'get', null)
  },
  createTeam (data) {
    return fetchAPI('workshop/team', 'post', data)
  },
  updateTeam (id, data) {
    return fetchAPI(`workshop/team/${id}`, 'put', data)
  },
  deleteTeam (id) {
    return fetchAPI(`workshop/team/${id}`, 'delete')
  },
  getDepartmentInfoList (id) {
    return fetchAPI('workshop/department/part', 'get', null, { id })
  },
  getProducingList () {
    return fetchAPI('workshop/producing', 'get', null)
  },
  createProducing (data) {
    return fetchAPI('workshop/producing', 'post', data)
  },
  updateProducing (id, data) {
    console.log(111)
    console.log(data)
    return fetchAPI(`workshop/producing/${id}`, 'put', data)
  },
  deleteProducing (id) {
    return fetchAPI(`workshop/producing/${id}`, 'delete')
  },
}