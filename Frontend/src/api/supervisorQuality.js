import fetchAPI from '../utils/fetch'

export default {
  //控制管理
  createControlManagementFile(id, file) {
    return fetchAPI(`supervisionQualityControl/controlManagement/file/${id}/post`, 'post', file)
  },
  deleteControlManagementFile(id) {
    return fetchAPI(`supervisionQualityControl/controlManagement/file/${id}/delete`, 'delete')
  },
  getControlManagementItem(id) {
    return fetchAPI(`supervisionQualityControl/controlManagement/${id}/`, 'get')
  },
  getControlManagementList(project_id) {
    return fetchAPI('supervisionQualityControl/controlManagement/', 'get', null, {project_id})
  },
  //控制审查
  createControlReviewFile(id, file) {
    return fetchAPI(`supervisionQualityControl/controlReview/file/${id}/post`, 'post', file)
  },
  deleteControlReviewFile(id) {
    return fetchAPI(`supervisionQualityControl/controlReview/file/${id}/delete`, 'delete')
  },
  getControlReviewItem(id) {
    return fetchAPI(`supervisionQualityControl/controlReview/${id}/`, 'get')
  },
  getControlReviewList(project_id) {
    return fetchAPI('supervisionQualityControl/controlReview/', 'get', null, {project_id})
  },
  //控制过程
  //控制过程--过程验收
  createProcessAcceptanceFile(id, file) {
    return fetchAPI(`supervisionQualityControl/controlProcess/processAcceptance/file/${id}/post`, 'post', file)
  },
  deleteProcessAcceptanceFile(id) {
    return fetchAPI(`supervisionQualityControl/controlProcess/processAcceptance/file/${id}/delete`, 'delete')
  },
  getProcessAcceptanceItem(id) {
    return fetchAPI(`supervisionQualityControl/controlProcess/processAcceptance/${id}/`, 'get')
  },
  getProcessAcceptanceList(project_id) {
    return fetchAPI('supervisionQualityControl/controlProcess/processAcceptance/', 'get', null, {project_id})
  },
  //控制过程--过程记录
  createProcessRecordFile(id, file) {
    return fetchAPI(`supervisionQualityControl/controlProcess/processRecording/file/${id}/post`, 'post', file)
  },
  deleteProcessRecordFile(id) {
    return fetchAPI(`supervisionQualityControl/controlProcess/processRecording/file/${id}/delete`, 'delete')
  },
  getProcessRecordItem(id) {
    return fetchAPI(`supervisionQualityControl/controlProcess/processRecording/${id}/`, 'get')
  },
  getProcessRecordList(project_id) {
    return fetchAPI('supervisionQualityControl/controlProcess/processRecording/', 'get', null, {project_id})
  },
  //缺陷处理
  createDefectTreatmentFile(id, file) {
    return fetchAPI(`supervisionQualityControl/DefectHandling/file/${id}/post`, 'post', file)
  },
  deleteDefectTreatmentFile(id) {
    return fetchAPI(`supervisionQualityControl/DefectHandling/file/${id}/delete`, 'delete')
  },
  getDefectTreatmentItem(id) {
    return fetchAPI(`supervisionQualityControl/DefectHandling/${id}/`, 'get')
  },
  getDefectTreatmentList(project_id) {
    return fetchAPI('supervisionQualityControl/DefectHandling/', 'get', null, {project_id})
  },
  //质量控制-控制过程-过程审查
  getProcessAuditList (project_id) {
    return fetchAPI('supervisionQualityControl/controlProcess/processReview/', 'get', null, { project_id })
  },
  getProcessAuditTableList (process_review_id) {
    return fetchAPI(`supervisionQualityControl/controlProcess/processReview/matter/`, 'get',null,{process_review_id})
  },
  createProcessAuditTable (process_review_id, data) {
    return fetchAPI(`supervisionQualityControl/controlProcess/processReview/matter/`, 'post', data,{process_review_id})
  },
  updateProcessAuditTable (matter_id, data) {
    return fetchAPI(`supervisionQualityControl/controlProcess/processReview/matter/${matter_id}/`, 'put', data)
  },
  deleteProcessAuditTable (matter_id) {
    return fetchAPI(`supervisionQualityControl/controlProcess/processReview/matter/${matter_id}/`, 'delete')
  },
  createProcessAuditFile (matter_id, file) {
    return fetchAPI(`supervisionQualityControl/controlProcess/processReview/file/${matter_id}/`, 'post', file)
  },
  deleteProcessAuditFile (id) {
    return fetchAPI(`supervisionQualityControl/controlProcess/processReview/file/${id}/delete/`, 'delete')
  },
  getProcessAuditItem (id) {
    return fetchAPI(`supervisionQualityControl/controlProcess/processReview/matter/${id}/`, 'get')
  },
 //安全管理--过程检查--通知单
  getControlProcessNoticeList (project_id) {
    return fetchAPI('supervisionQualityControl/controlProcess/noticeLetter/', 'get', null, { project_id })
  },
  createControlProcessNotice (project_id, data) {
    return fetchAPI('supervisionQualityControl/controlProcess/noticeLetter/', 'post', data, { project_id })
  },
  updateControlProcessNotice (notice_id, data) {
    return fetchAPI(`supervisionQualityControl/controlProcess/noticeLetter/${notice_id}/`, 'put', data)
  },
  deleteControlProcessNotice (notice_id) {
    return fetchAPI(`supervisionQualityControl/controlProcess/noticeLetter/${notice_id}/`, 'delete')
  },
  createControlProcessNoticeFile (id, file) {
    return fetchAPI(`supervisionQualityControl/controlProcess/noticeLetter/noticeFile/${id}/`, 'post', file)
  },
  deleteControlProcessNoticeFile (id) {
    return fetchAPI(`supervisionQualityControl/controlProcess/noticeLetter/noticeFile/${id}/`, 'delete')
  },
  createControlProcessReplyFile (id, file) {
    return fetchAPI(`supervisionQualityControl/controlProcess/noticeLetter/replyFile/${id}/`, 'post', file)
  },
  deleteControlProcessReplyFile (id) {
    return fetchAPI(`supervisionQualityControl/controlProcess/noticeLetter/replyFile/${id}/`, 'delete')
  },
  getControlProcessNoticeItem (id) {
    return fetchAPI(`supervisionQualityControl/controlProcess/noticeLetter/${id}/`, 'get')
  },
}