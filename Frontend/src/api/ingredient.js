import fetchAPI from '../utils/fetch'

export default {
  //用户信息管理
  getSumList () {
    return fetchAPI('materialtotal', 'get', null)
  },
  createSum (data) {
    return fetchAPI('materialtotal', 'post', data)
  },
  updateSum (id, data) {
    return fetchAPI(`materialtotal/${id}/`, 'put', data)
  },
  deleteSum (id) {
    return fetchAPI(`materialtotal/${id}/`, 'delete')
  },
  //原料库存统计
  getIntimeList () {
    return fetchAPI('inventory/intime', 'get', null)
  },
  getOuttimeList () {
    return fetchAPI('inventory/outtime', 'get', null)
  },
  createIn (data) {//入库
    return fetchAPI('materialtotal', 'post', data)
  },
  createOut (data) {//出库
    return fetchAPI('materialtotal', 'post', data)
  },
  getIngredientStatistics(){
    return fetchAPI('inventory/num', 'get', null)
  },
  // updateSum (id, data) {
  //   return fetchAPI(`materialtotal/${id}/`, 'put', data)
  // },
  // deleteSum (id) {
  //   return fetchAPI(`materialtotal/${id}/`, 'delete')
  // },
  //入库
  getInList () {
    return fetchAPI('material', 'get', null)
  },
  createInbound (data) {
    return fetchAPI('material', 'post', data)
  },
  updateInbound (id, data) {
    return fetchAPI(`material/${id}/`, 'put', data)
  },
  deleteInbound (id) {
    return fetchAPI(`material/${id}/`, 'delete')
  },
}