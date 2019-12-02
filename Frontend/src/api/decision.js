import fetchAPI from '../utils/fetch'

export default {
  getDecisionList(project_id) {
    return fetchAPI('decision/', 'get', null, {project_id})
  },
  updateDecision(id, decision) {
    return fetchAPI(`decision/${id}/`, 'put', decision)
  },
  deleteDecision(id) {
    return fetchAPI(`decision/${id}/`, 'delete')
  },
  createDecision(project_id, decision) {
    return fetchAPI(`decision/`, 'post', decision,{project_id})
  },
  getDecision(id) {
    return fetchAPI(`decision/${id}/`, 'get')
  },
  getDecisionMatterList(decision_id) {
    return fetchAPI(`decision/matter/`, 'get', null, {decision_id})
  },
  createDecisionMatter(matter, decision_id) {
    return fetchAPI(`decision/matter/`, 'post', matter, {decision_id})
  },
  updateDecisionMatter(id, matter) {
    return fetchAPI(`decision/matter/${id}/`, 'put', matter)
  },
  deleteDecisionMatter(id) {
    return fetchAPI(`decision/matter/${id}/`, 'delete')
  },
  //file
  createDecisionFile(id, file) {
    return fetchAPI(`decision/matter/file/${id}/`, 'post', file)
  },
  deleteDecisionFile(id) {
    return fetchAPI(`decision/matter/file/${id}/`, 'delete')
  },
  getDecisionItem(id) {
    return fetchAPI(`decision/matter/${id}/`, 'get',null)
  },
}
