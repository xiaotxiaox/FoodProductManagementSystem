import fetchAPI from '../utils/fetch'

export default {
  getProjectDetails (id) {
    return fetchAPI('project/'+id, 'get')
  }
}
