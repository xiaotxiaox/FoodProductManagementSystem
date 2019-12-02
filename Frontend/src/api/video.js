import fetchAPI from '../utils/fetch'

export default {
  getVideoList (project_id) {
    return fetchAPI('video/', 'get', null, { project_id })
  },
  createVideo (project_id, data) {
    return fetchAPI('video/', 'post', data, { project_id })
  },
  updateVideo (video_id, data) {
    return fetchAPI(`video/${video_id}/`, 'put', data)
  },
  deleteVideo (video_id) {
    return fetchAPI(`video/${video_id}/`, 'delete')
  }
}