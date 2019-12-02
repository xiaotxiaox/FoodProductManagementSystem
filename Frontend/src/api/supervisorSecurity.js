import fetchAPI from '../utils/fetch'

export default {
  //安全审查
  createSafetyReviewFile (id, file) {
    return fetchAPI(`supervisionSecurityManagement/securityReview/file/${id}/post`, 'post', file)
  },
  deleteSafetyReviewFile (id) {
    return fetchAPI(`supervisionSecurityManagement/securityReview/file/${id}/delete`, 'delete')
  },
  getSafetyReviewItem (id) {
    return fetchAPI(`supervisionSecurityManagement/securityReview/${id}/`, 'get')
  },
  getSafetyReviewList (project_id) {
    return fetchAPI('supervisionSecurityManagement/securityReview/', 'get', null, { project_id })
  },
   //安全管理--过程检查--安全巡视
  getSecurityPatrolList (project_id) {
    return fetchAPI('supervisionSecurityManagement/processCheck/securityPatrol/', 'get', null, { project_id })
  },
  createSecurityPatrol (project_id, data) {
    return fetchAPI('supervisionSecurityManagement/processCheck/securityPatrol/', 'post', data, { project_id })
  },
  updateSecurityPatrol (patrol_id, data) {
    return fetchAPI(`supervisionSecurityManagement/processCheck/securityPatrol/${patrol_id}/`, 'put', data)
  },
  deleteSecurityPatrol (patrol_id) {
    return fetchAPI(`supervisionSecurityManagement/processCheck/securityPatrol/${patrol_id}/`, 'delete')
  },
  //安全管理--过程检查--相关文件
  createCheckFileFile (id, file) {
    return fetchAPI(`supervisionSecurityManagement/processCheck/file/${id}/post`, 'post', file)
  },
  deleteCheckFileFile (id) {
    return fetchAPI(`supervisionSecurityManagement/processCheck/file/${id}/delete`, 'delete')
  },
  getCheckFileItem (id) {
    return fetchAPI(`supervisionSecurityManagement/processCheck/${id}/`, 'get')
  },
  getCheckFileList(project_id) {
    return fetchAPI('supervisionSecurityManagement/processCheck/', 'get', null, {project_id})
  },
  //安全管理--过程检查--通知单
  getProcessCheckNoticeList (project_id) {
    return fetchAPI('supervisionSecurityManagement/processCheck/noticeLetter/', 'get', null, { project_id })
  },
  createProcessCheckNotice (project_id, data) {
    return fetchAPI('supervisionSecurityManagement/processCheck/noticeLetter/', 'post', data, { project_id })
  },
  updateProcessCheckNotice (notice_id, data) {
    return fetchAPI(`supervisionSecurityManagement/processCheck/noticeLetter/${notice_id}/`, 'put', data)
  },
  deleteProcessCheckNotice (notice_id) {
    return fetchAPI(`supervisionSecurityManagement/processCheck/noticeLetter/${notice_id}/`, 'delete')
  },
  createProcessCheckNoticeFile (id, file) {
    return fetchAPI(`supervisionSecurityManagement/processCheck/noticeLetter/noticeFile/${id}/`, 'post', file)
  },
  deleteProcessCheckNoticeFile (id) {
    return fetchAPI(`supervisionSecurityManagement/processCheck/noticeLetter/noticeFile/${id}/`, 'delete')
  },
  createProcessCheckReplyFile (id, file) {
    return fetchAPI(`supervisionSecurityManagement/processCheck/noticeLetter/replyFile/${id}/`, 'post', file)
  },
  deleteProcessCheckReplyFile (id) {
    return fetchAPI(`supervisionSecurityManagement/processCheck/noticeLetter/replyFile/${id}/`, 'delete')
  },
  getProcessCheckNoticeItem (id) {
    return fetchAPI(`supervisionSecurityManagement/processCheck/noticeLetter/${id}/`, 'get')
  },
  //安全管理--细则审批
  createApprovalFile (id, file) {
    return fetchAPI(`supervisionSecurityManagement/rulesReview/file/${id}/post`, 'post', file)
  },
  deleteApprovalFile (id) {
    return fetchAPI(`supervisionSecurityManagement/rulesReview/file/${id}/delete`, 'delete')
  },
  getApprovalItem (project_id) {
    return fetchAPI(`supervisionSecurityManagement/rulesReview/`, 'get', null,{project_id})
  },
  //风险清单
  getRiskList (project_id) {
    return fetchAPI('supervisionSecurityManagement/riskList/', 'get', null, { project_id })
  },
  createRiskList (project_id, data) {
    return fetchAPI('supervisionSecurityManagement/riskList/', 'post', data, { project_id })
  },
  updateRiskList (id, data) {
    return fetchAPI(`supervisionSecurityManagement/riskList/${id}/`, 'put', data)
  },
  deleteRiskList(id) {
    return fetchAPI(`supervisionSecurityManagement/riskList/${id}/`, 'delete')
  },
  createRiskListFile (id, file) {
    return fetchAPI(`supervisionSecurityManagement/riskList/file/${id}/`, 'post', file)
  },
  deleteRiskListFile (id) {
    return fetchAPI(`supervisionSecurityManagement/riskList/file/${id}/`, 'delete')
  },
  getRiskListItem (id) {
    return fetchAPI(`supervisionSecurityManagement/riskList/${id}/`, 'get')
  }
}