import fetchAPI from '../utils/fetch'

export default {
  //进度控制文件
  getScheduleFileList (project_id) {
    return fetchAPI('supervisionProgress/start/', 'get', null, { project_id })
  },
  getScheduleFile (id) {
    return fetchAPI(`supervisionProgress/start/${id}/`, 'get')
  },
  createScheduleFileItemFile (id, file) {
    return fetchAPI(`supervisionProgress/start/file/${id}/`, 'post', file)
  },
  deleteScheduleFileItemFile (id) {
    return fetchAPI(`supervisionProgress/start/file/${id}/`, 'delete')
  },
  //进度计划
  getSchedulePlanList (project_id) {
    return fetchAPI(`supervisionProgress/progressPlan/`, 'get', null, { project_id })
  },
  getSchedulePlan () {
    return fetchAPI(`supervisionProgress/progressPlan/`, 'get')
  },
  deleteSchedulePlan (plan_id) {
    return fetchAPI(`supervisionProgress/progressPlan/${plan_id}/`, 'delete')
  },
  getSchedulePlanMatter (id) {
    return fetchAPI(`supervisionProgress/progressPlan/${id}/`, 'get')
  },
  updateSchedulePlan (plan_id, data) {
    return fetchAPI(`supervisionProgress/progressPlan/${plan_id}/`, 'put', data)
  },
  createSchedulePlan (project_id, data) {
    return fetchAPI('supervisionProgress/progressPlan/', 'post', data, { project_id })
  },
  //进度计划附件
  getSchedulePlanFile1 (id) {
    return fetchAPI(`supervisionProgress/progressPlan/${id}/`, 'get')
  },
  createSchedulePlanFile1 (id, file) {
    return fetchAPI(`supervisionProgress/progressPlan/accessory/${id}/`, 'post', file)
  },
  deleteSchedulePlanFile1 (id) {
    return fetchAPI(`supervisionProgress/progressPlan/accessory/${id}/`, 'delete')
  },
  //进度计划审批
  getSchedulePlanFile2 (id) {
    return fetchAPI(`supervisionProgress/progressPlan/${id}/`, 'get')
  },
  createSchedulePlanFile2 (id, file) {
    return fetchAPI(`supervisionProgress/progressPlan/audit/${id}/`, 'post', file)
  },
  deleteSchedulePlanFile2 (id) {
    return fetchAPI(`supervisionProgress/progressPlan/audit/${id}/`, 'delete')
  },
  //通知单
  getScheduleNoticeList (project_id) {
    return fetchAPI(`supervisionProgress/noticeLetter/`, 'get', null, { project_id })
  },
  getScheduleNotice () {
    return fetchAPI(`supervisionProgress/noticeLetter/`, 'get')
  },
  deleteScheduleNotice (notice_id) {
    return fetchAPI(`supervisionProgress/noticeLetter/${notice_id}/`, 'delete')
  },
  getScheduleNoticeMatter (id) {
    return fetchAPI(`supervisionProgress/noticeLetter/${id}/`, 'get')
  },
  updateScheduleNotice (notice_id, data) {
    return fetchAPI(`supervisionProgress/noticeLetter/${notice_id}/`, 'put', data)
  },
  createScheduleNotice (project_id, data) {
    return fetchAPI('supervisionProgress/noticeLetter/', 'post', data, { project_id })
  },
  //通知单文件
  getScheduleNoticeFile1 (id) {
    return fetchAPI(`supervisionProgress/noticeLetter/${id}/`, 'get')
  },
  createScheduleNoticeFile1 (id, file) {
    return fetchAPI(`supervisionProgress/noticeLetter/noticeFile/${id}/`, 'post', file)
  },
  deleteScheduleNoticeFile1 (id) {
    return fetchAPI(`supervisionProgress/noticeLetter/noticeFile/${id}/`, 'delete')
  },
  //回复单文件
  getScheduleNoticeFile2 (id) {
    return fetchAPI(`supervisionProgress/noticeLetter/${id}/`, 'get')
  },
  createScheduleNoticeFile2 (id, file) {
    return fetchAPI(`supervisionProgress/noticeLetter/replyFile/${id}/`, 'post', file)
  },
  deleteScheduleNoticeFile2 (id) {
    return fetchAPI(`supervisionProgress/noticeLetter/replyFile/${id}/`, 'delete')
  },
}