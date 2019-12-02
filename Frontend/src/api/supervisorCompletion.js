import fetchAPI from '../utils/fetch'

export default {
  createSupervisorCompletionFile (id, file) {
    return fetchAPI(`supervisionCompletion/file/${id}/post`, 'post', file)
  },
  deleteSupervisorCompletionFile (id) {
    return fetchAPI(`supervisionCompletion//file/${id}/delete`, 'delete')
  },
  getSupervisorCompletionItem (id) {
    return fetchAPI(`supervisionCompletion/${id}/`, 'get')
  },
  getSupervisorCompletionList (project_id) {
    return fetchAPI('supervisionCompletion/', 'get', null, { project_id })
  },
}