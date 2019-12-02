import fetchAPI from '../utils/fetch'

export default {
  // application
  getApplicationList (project_id) {
    return fetchAPI('implement/application/', 'get', null, { project_id })
  },
  createApplication (project_id, data) {
    return fetchAPI('implement/application/', 'post', data, { project_id })
  },
  updateApplication (application_id, data) {
    return fetchAPI(`implement/application/${application_id}/`, 'put', data)
  },
  deleteApplication (application_id) {
    return fetchAPI(`implement/application/${application_id}/`, 'delete')
  },
  getApplication (application_id) {
    return fetchAPI(`implement/application/${application_id}/`, 'get')
  },
  // application matter
  createApplicationMatter (application_id, data) {
    return fetchAPI('implement/application/matter/', 'post', data, { application_id })
  },
  getApplicationMatterList (application_id) {
    return fetchAPI('implement/application/matter/', 'get', null, { application_id })
  },
  getApplicationMatter (matter_id) {
    return fetchAPI(`implement/application/matter/${matter_id}/`, 'get')
  },
  updateApplicationMatter (matter_id, data) {
    return fetchAPI(`implement/application/matter/${matter_id}/`, 'put', data)
  },
  deleteApplicationMatter (matter_id) {
    return fetchAPI(`implement/application/matter/${matter_id}/`, 'delete')
  },
  // bid
  getBidList (project_id) {
    return fetchAPI('implement/bid/', 'get', null, { project_id })
  },
  createBid (project_id, data) {
    return fetchAPI('implement/bid/', 'post', data, { project_id })
  },
  updateBid (bid_id, data) {
    return fetchAPI(`implement/bid/${bid_id}/`, 'put', data)
  },
  deleteBid (bid_id) {
    return fetchAPI(`implement/bid/${bid_id}/`, 'delete')
  },
  getBidSum (project_id) {
    return fetchAPI('implement/bid/sum/', 'get', null, { project_id })
  },
  //招标文件
  createBidFile (id, file) {
    return fetchAPI(`implement/bid/bidFile/${id}/`, 'post', file)
  },
  deleteBidFile (id) {
    return fetchAPI(`implement/bid/bidFile/${id}/delete/`, 'delete')
  },
  getBidItem (id) {
    return fetchAPI(`implement/bid/${id}/`, 'get')
  },
  //中标文件
  createWinBidFile (id, file) {
    return fetchAPI(`implement/bid/winBidFile/${id}/`, 'post', file)
  },
  deleteWinBidFile (id) {
    return fetchAPI(`implement/bid/winBidFile/${id}/delete/`, 'delete')
  },
  // design item
  getDesignItemList (project_id) {
    return fetchAPI('implement/design/item/', 'get', null, { project_id })
  },
  getDesignItem (id) {
    return fetchAPI(`implement/design/item/${id}/`, 'get')
  },
  // design item doing
  getDesignItemDoing (project_id) {
    return fetchAPI('implement/design/item/doing/', 'get', null, { project_id })
  },
  updateDesignItemDoing (id, design_item) {
    return fetchAPI(`implement/design/item/doing/${id}/`, 'put', { design_item })
  },
  // design item file
  createDesignItemFile (id, file) {
    return fetchAPI(`implement/design/item/${id}/file/`, 'post', file)
  },
  deleteDesignItemFile (id) {
    return fetchAPI(`implement/design/item/file/${id}/`, 'delete')
  },
  // design company
  getDesignCompanyList (project_id) {
    return fetchAPI('implement/design/company/', 'get', null, { project_id })
  },
  createDesignCompany (designCompany, project_id) {
    return fetchAPI('implement/design/company/', 'post', designCompany, { project_id })
  },
  updateDesignCompany (id, designCompany) {
    return fetchAPI(`implement/design/company/${id}/`, 'put', designCompany)
  },
  deleteDesignCompany (id) {
    return fetchAPI(`implement/design/company/${id}/`, 'delete')
  },
  getDesignCompanyMatter (id) {
    return fetchAPI(`implement/design/company/${id}/`, 'get')
  },
  updateDesignCompanyMatter (plan_id, data) {
    return fetchAPI(`implement/design/company/${plan_id}/`, 'put', data)
  },
  createDesignCompanyMatter (project_id, data) {
    return fetchAPI('implement/design/company/', 'post', data, { project_id })
  },
  //designCompanyPay
  getDesignPayDetailMatter (father_id) {
    return fetchAPI(`implement/design/company/${father_id}/`, 'get',null)
  },
  updateDesignPayDetail (detail_id, data) {
    return fetchAPI(`implement/design/company/children/${detail_id}/`, 'put', data)
  },
  createDesignPayDetail (father_id, data) {
    return fetchAPI(`implement/design/company/children/`, 'post', data,{father_id})
  },
  // getDesignPayDetailList (process_id) {
  //   return fetchAPI(`implement/design/company/${process_id}`, 'get', null)
  // },
  deleteDesignPayDetail (detail_id) {
    return fetchAPI(`implement/design/company/children/${detail_id}/`, 'delete')
  },
  // getDesignPayDetailList (company_id) {
  //   return fetchAPI(`implement/design/payList/${company_id}`, 'get', null)
  // },
  // 进度管理页面
  //build
  getBuildList (project_id) {
    return fetchAPI('implement/progress/building/', 'get', null, { project_id })
  },
  createBuild (project_id, data) {
    return fetchAPI('implement/progress/building/', 'post', data, { project_id })
  },
  updateBuild (building_id, data) {
    return fetchAPI(`implement/progress/building/${building_id}/`, 'put', data)
  },
  deleteBuild (building_id) {
    return fetchAPI(`implement/progress/building/${building_id}/`, 'delete')
  },
  //claim
  getClaimSum (project_id) {
    return fetchAPI('implement/progress/claim/sum/', 'get', null, { project_id })
  },
  getClaimList (project_id) {
    return fetchAPI('implement/progress/claim/', 'get', null, { project_id })
  },
  getClaimAddressList (project_id) {
    return fetchAPI('project/addressList/companyName/', 'get', null, { project_id })
  },
  createClaim (project_id, data) {
    return fetchAPI('implement/progress/claim/', 'post', data, { project_id })
  },
  updateClaim (claim_id, data) {
    return fetchAPI(`implement/progress/claim/${claim_id}/`, 'put', data)
  },
  deleteClaim (claim_id) {
    return fetchAPI(`implement/progress/claim/${claim_id}/`, 'delete')
  },
  createClaimFile (id, file) {
    return fetchAPI(`implement/progress/claim/file/${id}/`, 'post', file)
  },
  deleteClaimFile (id) {
    return fetchAPI(`implement/progress/claim/file/${id}/`, 'delete')
  },
  getClaimItem (id) {
    return fetchAPI(`implement/progress/claim/${id}/`, 'get')
  },
   //质量管理
  getQualityList (project_id) {
    return fetchAPI('implement/quality/individualQuality/', 'get', null, { project_id })
  },
   updateQuality (building_id, data) {
    return fetchAPI(`implement/quality/individualQuality/${building_id}/`, 'put', data)
  },
  getDifficultyList (project_id) {
    return fetchAPI('implement/quality/difficulty/', 'get', null, { project_id })
  },
   updateDifficulty (difficulty_id, data) {
    return fetchAPI(`implement/quality/difficulty/${difficulty_id}/`, 'put', data)
  },
  //信息管理
  createInformationFile (id, file) {
    return fetchAPI(`implement/information/file/${id}/post`, 'post', file)
  },
  deleteInformationFile (id) {
    return fetchAPI(`implement/information/file/${id}/delete`, 'delete')
  },
  getInformationItem (id) {
    return fetchAPI(`implement/information/${id}/`, 'get')
  },
  getInformationList (project_id) {
    return fetchAPI('implement/information/', 'get', null, { project_id })
  },
  //安全管理
  getSecurityList (project_id) {
    return fetchAPI('implement/security/riskList/', 'get', null, { project_id })
  },
  createSecurity (project_id, data) {
    return fetchAPI('implement/security/riskList/', 'post', data, { project_id })
  },
  updateSecurity (risk_id, data) {
    return fetchAPI(`implement/security/riskList/${risk_id}/`, 'put', data)
  },
  deleteSecurity (risk_id) {
    return fetchAPI(`implement/security/riskList/${risk_id}/`, 'delete')
  },
  createSecurityFile (id, file) {
    return fetchAPI(`implement/security/riskList/file/${id}/`, 'post', file)
  },
  deleteSecurityFile (id) {
    return fetchAPI(`implement/security/riskList/file/${id}/delete/`, 'delete')
  },
  getSecurityItem (id) {
    return fetchAPI(`implement/security/riskList/${id}/`, 'get')
  },
  getStartDate(project_id){
     return fetchAPI('project/time/', 'get', null, { project_id })
  },
  getSecurityNotice (project_id) {
    return fetchAPI('supervisionSecurityManagement/processCheck/noticeLetter/number/', 'get', null, { project_id })
  },
  //合同管理
  getContractList (project_id) {
    return fetchAPI('implement/contract/', 'get', null, { project_id })
  },
  createContract (project_id, data) {
    return fetchAPI('implement/contract/', 'post', data, { project_id })
  },
  updateContract (contract_id, data) {
    return fetchAPI(`implement/contract/${contract_id}/`, 'put', data)
  },
  deleteContract (contract_id) {
    return fetchAPI(`implement/contract/${contract_id}/`, 'delete')
  },
   getContractNotice (project_id) {
    return fetchAPI('supervisionQualityControl/controlProcess/noticeLetter/number/', 'get', null, { project_id })
  },

  //工程变更
  createContractChangeFile (id, file) {
    return fetchAPI(`implement/contract/additionalProtocolFile/${id}/`, 'post', file)
  },
  deleteContractChangeFile (id) {
    return fetchAPI(`implement/contract/additionalProtocolFile/${id}/`, 'delete')
  },
  //延期审查
  createContractReviewFile (id, file) {
    return fetchAPI(`implement/contract/claimDamageFile/${id}/`, 'post', file)
  },
  deleteContractReviewFile (id) {
    return fetchAPI(`implement/contract/claimDamageFile/${id}/`, 'delete')
  },
  //工程合同
  createContractProjectFile (id, file) {
    return fetchAPI(`implement/contract/contractReviewFile/${id}/`, 'post', file)
  },
  deleteContractProjectFile (id) {
    return fetchAPI(`implement/contract/contractReviewFile/${id}/`, 'delete')
  },
  getContractItem (id) {
    return fetchAPI(`implement/contract/${id}/`, 'get')
  },
  createAccountDetailMatter (company_id, data) {
    return fetchAPI('implement/invest/company/account/', 'post', data, { company_id })
  },
  updateAccountDetailMatter (company_id, data) {
    return fetchAPI(`implement/invest/company/account/${company_id}/`, 'put', data)
  },
   getAccountDetailMatter (company_id) {
    return fetchAPI(`implement/invest/company/account/${company_id}/`, 'get')
  },
  getAccountDetailMatterList (company_id) {
    return fetchAPI('implement/invest/company/account/', 'get', null, { company_id })
  },
  getAccountDetail (company_id) {
    return fetchAPI(`implement/invest/company/account/${company_id}/`, 'get')
  },
  updateAccountDetail (company_id, data) {
    return fetchAPI(`implement/invest/company/account/${company_id}/`, 'put', data)
  },
  deleteAccountDetailMatter (company_id) {
    return fetchAPI(`implement/invest/company/account/${company_id}/`, 'delete')
  },
  //投资管理
  //变更
  getInvestChangeList (project_id) {
    return fetchAPI(`implement/investment/changingLedger/`, 'get', null, { project_id })
  },
  getInvestChange () {
    return fetchAPI(`implement/investment/changingLedger/`, 'get')
  },
  deleteInvestChange (change_id) {
    return fetchAPI(`implement/investment/changingLedger/${change_id}/`, 'delete')
  },
  getInvestChangeMatter (id) {
    return fetchAPI(`implement/investment/changingLedger/${id}/`, 'get')
  },
  updateInvestChange (change_id, data) {
    return fetchAPI(`implement/investment/changingLedger/${change_id}/`, 'put', data)
  },
  createInvestChange (project_id, data) {
    return fetchAPI('implement/investment/changingLedger/', 'post', data, { project_id })
  },
  //进度款
  getInvestProcessList (project_id) {
    return fetchAPI(`implement/investment/progressPayment/`, 'get', null, { project_id })
  },
  getInvestProcess () {
    return fetchAPI(`implement/investment/progressPayment/`, 'get')
  },
  deleteInvestProcess (process_id) {
    return fetchAPI(`implement/investment/progressPayment/${process_id}/`, 'delete')
  },
  getInvestProcessMatter (id) {
    return fetchAPI(`implement/investment/progressPayment/${id}/`, 'get')
  },
  updateInvestProcess (process_id, data) {
    return fetchAPI(`implement/investment/progressPayment/${process_id}/`, 'put', data)
  },
  createInvestProcess (project_id, data) {
    return fetchAPI('implement/investment/progressPayment/', 'post', data, { project_id })
  },
  //变更台账附件
  getInvestChangeFile1 (id) {
    return fetchAPI(`implement/investment/changingLedger/${id}/`, 'get')
  },
  createInvestChangeFile1 (id, file) {
    return fetchAPI(`implement/investment/changingLedger/accessory/${id}/`, 'post', file)
  },
  deleteInvestChangeFile1 (id) {
    return fetchAPI(`implement/investment/changingLedger/accessory/${id}/`, 'delete')
  },
  //变更台账合同
  getInvestChangeFile2 (id) {
    return fetchAPI(`implement/investment/changingLedger/${id}/`, 'get')
  },
  createInvestChangeFile2 (id, file) {
    return fetchAPI(`implement/investment/changingLedger/contract/${id}/`, 'post', file)
  },
  deleteInvestChangeFile2 (id) {
    return fetchAPI(`implement/investment/changingLedger/contract/${id}/`, 'delete')
  },
  //进度款中标清单
  getProcessFile (id) {
    return fetchAPI(`implement/investment/progressPayment/${id}/`, 'get')
  },
  createProcessFile (id, file) {
    return fetchAPI(`implement/investment/progressPayment/file/${id}/`, 'post', file)
  },
  deleteProcessFile(id) {
    return fetchAPI(`implement/investment/progressPayment/file/${id}/`, 'delete')
  },
  //进度款详情
  getInvestProcessDetailList (process_id) {
    return fetchAPI(`implement/investment/progressPayment/${process_id}`, 'get', null)
  },
  getInvestProcessDetail () {
    return fetchAPI(`implement/investment/progressPayment/`, 'get')
  },
  deleteInvestProcessDetail (detail_id) {
    return fetchAPI(`implement/investment/progressPayment/children/${detail_id}/`, 'delete')
  },
  getInvestProcessDetailMatter (id) {
    return fetchAPI(`implement/investment/progressPayment/children/${id}/`, 'get')
  },
  updateInvestProcessDetail (detail_id, data) {
    return fetchAPI(`implement/investment/progressPayment/children/${detail_id}/`, 'put', data)
  },
  createInvestProcessDetail (father_id, data) {
    return fetchAPI(`implement/investment/progressPayment/children/`, 'post', data,{father_id})
  },
  //横道图
  getInitialChart (project_id) {
    return fetchAPI('implement/progress/ganttChart/chooseTime/', 'get', null, { project_id })
  },
  updateInitialChart (time_id, data) {
    return fetchAPI(`implement/progress/ganttChart/chooseTime/${time_id}/`, 'put', data)
  },
  getProgressList (project_id) {
    return fetchAPI('implement/progress/ganttChart/editChart/', 'get', null, { project_id })
  },
  createTask (project_id, data) {
    return fetchAPI('implement/progress/ganttChart/editChart/', 'post', data, { project_id })
  },
  updateTask (id, data) {
    return fetchAPI(`implement/progress/ganttChart/editChart/${id}/`, 'put', data)
  },
  deleteTask (id) {
    return fetchAPI(`implement/progress/ganttChart/editChart/${id}/`, 'delete')
  },
  //投资管理树状图
  getTreeList (project_id) {
    return fetchAPI('implement/investment/treeView/', 'get', null, { project_id })
  },
  getTrees (project_id) {
    return fetchAPI(`implement/investment/treeView/`, 'get',null, { project_id })
  },
  // getTreeMatter () {
  //   return fetchAPI(`implement/investment/treeView/`, 'get')
  // },
  createTrees (project_id, data) {
    return fetchAPI('implement/investment/treeView/', 'post', data, { project_id })
  },
  updateTrees (tree_view_id, data) {
    return fetchAPI(`implement/investment/treeView/${tree_view_id}/`, 'put', data)
  },

  getTreeSum (project_id) {
    return fetchAPI('implement/investment/treeView/realSum/', 'get', null, { project_id })
  },
  //总投资表
  createTotalInvest (project_id, data) {
    return fetchAPI('implement/totalInvestment/', 'post', data, { project_id })
  },
  updateTotalInvest (total_investment_id, data) {
    return fetchAPI(`implement/totalInvestment/${total_investment_id}/`, 'put', data)
  },
  getTotalInvestList (project_id) {
    return fetchAPI('implement/totalInvestment/', 'get', null, { project_id })
  },
  deleteTotalInvest (total_investment_id) {
    return fetchAPI(`implement/totalInvestment/${total_investment_id}/`, 'delete')
  },

}