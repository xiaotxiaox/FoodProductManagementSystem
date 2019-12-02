import fetchAPI from '../utils/fetch'

export default {
  login (username, password) {
    return fetchAPI('user/login/', 'post', {
      username,
      password
    })
  },
  logout () {
    return fetchAPI('user/logout/', 'get')
  },
  getInfo () {
    return fetchAPI('user/userinfo/', 'get')
  }
}
