import 'whatwg-fetch'
import notification from 'ant-design-vue/es/notification'
import { getCookie } from './util'

const BASE_URL = '/api/'

/**
 * 封装fetch
 * @param url api url
 * @param method get/post/put/delete
 * @param data data to be json
 * @param params urlencoded
 * @returns {Promise<Response>}
 */
export default function fetchAPI (url, method, data = null, params = null) {
  let body = null
  // csrf
  let headers = {
    'Content-Type': 'application/json'
  }
  // if (!/^(GET|HEAD|OPTIONS|TRACE)$/.test(method.toUpperCase())) {
  //   headers['X-CSRFToken'] = getCookie('csrftoken')
  // }
  if (data) {
    body = JSON.stringify(data)
  }
  if (params) {
    url += '?' + (new URLSearchParams(params)).toString()
  }
  return new Promise((resolve, reject) => {
    fetch(BASE_URL + url, {
      credentials: 'include',
      headers: headers,
      method: method,
      body: body
    })
      .then(res => {
        console.log(res.ok)
        if (res.status === 204) {
          return res.text()
        } else if (res.status === 400) {
          return res.json()
        } else if (res.status === 404) {
          return res.json()
        } else if (res.ok) {
          // bug
          return res.json()
        } else {
          throw `${res.status}, ${res.statusText}`
        }
      })
      .then(data => {
        if (!data.detail) {
          resolve(data)
        } else {
          notification.error({ message: '错误', description: JSON.stringify(data.detail), key: 'ERROR' })
        }
      })
      .catch(error => {
        notification.error({ message: '错误', description: error, key: 'ERROR' })
      })
  })
}