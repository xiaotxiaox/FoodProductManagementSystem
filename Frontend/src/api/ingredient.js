import fetchAPI from '../utils/fetch'

export default {
  //
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
    console.log(111)
    console.log(data)
    return fetchAPI('material', 'post', data)
  },
  updateInbound (id, data) {
    return fetchAPI(`material/${id}/`, 'put', data)
  },
  deleteInbound (id) {
    return fetchAPI(`material/${id}/`, 'delete')
  },
  //出库
  getOutList () {
    return fetchAPI('outbound', 'get', null)
  },
  createOutbound (data) {
    console.log(data)
    return fetchAPI('outbound', 'post', data)
  },
  updateOutbound (id, data) {
    return fetchAPI(`outbound/${id}/`, 'put', data)
  },
  deleteOutbound (id) {
    return fetchAPI(`outbound/${id}/`, 'delete')
  },
}