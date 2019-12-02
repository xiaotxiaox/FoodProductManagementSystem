import fetchAPI from '../utils/fetch'

export default {
  getSettingUsers () {
    return fetchAPI('user/list/', 'get')
  },
   updateSettingUsers (users_id, data) {
    return fetchAPI(`user/${users_id}/`, 'put', data)
  },
   deleteSettingUsersMatter (id) {
    return fetchAPI(`user/${id}/`, 'delete')
  },
  getSettingUsersMatter (id) {
    return fetchAPI(`user/${id}/`, 'get')
  },
  updateSettingUsersMatter (id, data) {
    return fetchAPI(`user/${id}/`, 'put', data)
  },
  udPswSettingUsersMatter (id, data) {
    return fetchAPI(`user/${id}/resetPassword/`, 'post', data)
  },
  createSettingUsersMatter (data) {
    return fetchAPI('user/create/', 'post', data)
  },
  //roles
   getSettingRolesMatterList () {
    return fetchAPI('user/role/', 'get', null)
  },
  getSettingRoles () {
    return fetchAPI(`user/role/`, 'get')
  },
  updateSettingRoles(roles_id, data) {
    return fetchAPI(`user/role/${roles_id}/`, 'put', data)
  },
  getSettingRolesMatter (id) {
    return fetchAPI(`user/settingRole/${id}/`, 'get')
  },
  updateSettingRolesMatter (id, data) {
    return fetchAPI(`user/settingRole/${id}/`, 'put', data)
  },
  createSettingRolesMatter (data) {
    return fetchAPI('user/settingRole/', 'post', data,)
  },
   deleteSettingRolesMatter (id) {
    return fetchAPI(`user/settingRole/${id}/`, 'delete')
  },
  //permissions
   getSettingPermissionsMatterList (id) {
    return fetchAPI('user/permission/', 'get', null, { id })
  },
  getSettingPermissions () {
    return fetchAPI(`user/permission/`, 'get')
  },
  updateSettingPermissions(permissions_id, data) {
    return fetchAPI(`user/permission/${permissions_id}/`, 'put', data)
  },
  deleteSettingPermissionsMatter (id) {
    return fetchAPI(`user/permission/${id}/`, 'delete')
  },
  getSettingPermissionsMatter (id) {
    return fetchAPI(`user/permission/${id}/`, 'get')
  },
  updateSettingPermissionsMatter (id, data) {
    return fetchAPI(`user/permission/${id}/`, 'put', data)
  },
  createSettingPermissionsMatter (permissions_id, data) {
    return fetchAPI('user/permission/', 'post', data, { permissions_id })
  },
  getSettingActions () {
    return fetchAPI(`user/action`, 'get')
  },
  //projectSettings
  getUsers () {
    return fetchAPI(`user/list/`, 'get')
  },
  getProjectList(){
    return fetchAPI(`project/user/`, 'get')
  },
  updateProjectUser(id,data){
    return fetchAPI(`project/user/${id}/`, 'put', data)
  },
  //项目详情设置
  //通讯录
  getAddressList() {
    return fetchAPI('project/companyType/', 'get', null)
  },
  createAddress(data) {
    return fetchAPI('project/settingCompanyType/', 'post', data)
  },
  updateAddress(address_id, data) {
    return fetchAPI(`project/companyType/${address_id}/`, 'put', data)
  },
  deleteAddress(address_id) {
    return fetchAPI(`project/companyType/${address_id}/`, 'delete')
  },
  //资金设置
 getFundList() {
    return fetchAPI('project/fundSourcesList/', 'get', null)
  },
  createFund(data) {
    return fetchAPI('project/fundSourcesList/', 'post', data)
  },
  updateFund(fund_id, data) {
    return fetchAPI(`project/fundSources/${fund_id}/`, 'put', data)
  },
  deleteFund(fund_id) {
    return fetchAPI(`project/fundSources/${fund_id}/`, 'delete')
  },
}
