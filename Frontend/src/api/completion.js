import fetchAPI from '../utils/fetch'

export default {
  getCompletionList (project_id) {
    return fetchAPI('completion/', 'get', null, { project_id })
  },
  getCompletion (id) {
    return fetchAPI(`completion/${id}/`, 'get')
  },
   createCompletionItemFile (id, file) {
    return fetchAPI(`completion/file/${id}/`, 'post', file)
  },
  deleteCompletionItemFile (id) {
    return fetchAPI(`completion/file/${id}/`, 'delete')
  },
  //elseDetail
  getElseDetailList (project_id) {
    return fetchAPI('completion/elseDetail/', 'get', null, { project_id })
  },
  createElseDetail (project_id, data) {
    return fetchAPI('completion/elseDetail/', 'post', data, { project_id })
  },
  updateElseDetail(else_detail_id, data) {
    return fetchAPI(`completion/elseDetail/${else_detail_id}/`, 'put', data)
  },
  deleteElseDetail (else_detail_id) {
    return fetchAPI(`completion/elseDetail/${else_detail_id}/`, 'delete')
  },
  getElseDetail (else_detail_id) {
    return fetchAPI(`completion/elseDetail/${else_detail_id}/`, 'get')
  },
  // application matter
  createElseDetailMatter (else_detail_id, data) {
    return fetchAPI('completion/elseDetail/matter/', 'post', data, { else_detail_id })
  },
  getElseDetailMatterList (else_detail_id) {
    return fetchAPI('completion/elseDetail/matter/', 'get', null, { else_detail_id })
  },
  getElseDetailMatter (matter_id) {
    return fetchAPI(`completion/elseDetail/matter/${matter_id}/`, 'get')
  },
  updateElseDetailMatter (matter_id, data) {
    return fetchAPI(`completion/elseDetail/matter/${matter_id}/`, 'put', data)
  },
  deleteElseDetailMatter (matter_id) {
    return fetchAPI(`completion/elseDetail/matter/${matter_id}/`, 'delete')
  },
}
