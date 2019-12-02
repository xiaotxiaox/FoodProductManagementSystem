import fetchAPI from '../utils/fetch'

export default {
  //投资控制文件
  getInvestFileList (project_id) {
    return fetchAPI('supervisionInvestment/investment/', 'get', null, { project_id })
  },
  getInvestFile (id) {
    return fetchAPI(`supervisionInvestment/investment/${id}/`, 'get')
  },
  createInvestFileItemFile (id, file) {
    return fetchAPI(`supervisionInvestment/investment/file/${id}/`, 'post', file)
  },
  deleteInvestFileItemFile (id) {
    return fetchAPI(`supervisionInvestment/investment/file/${id}/`, 'delete')
  },
  //变更台账
  //变更领取台账
  getInvestReceiveList (project_id) {
    return fetchAPI('supervisionInvestment/draw/', 'get', null, { project_id })
  },
  getInvestReceive () {
    return fetchAPI(`supervisionInvestment/draw/`, 'get')
  },
  deleteInvestReceive (draw_id) {
    return fetchAPI(`supervisionInvestment/draw/${draw_id}/`, 'delete')
  },
  updateInvestReceive (draw_id, data) {
    return fetchAPI(`supervisionInvestment/draw/${draw_id}/`, 'put', data)
  },
  createInvestReceive (project_id, data) {
    return fetchAPI('supervisionInvestment/draw/', 'post', data, { project_id })
  },
  getInvestReceiveMatter (draw_id) {
    return fetchAPI(`supervisionInvestment/draw/${draw_id}/`, 'get')
  },
  //变更发放台账
  getInvestGiveList (project_id) {
    return fetchAPI('supervisionInvestment/provide/', 'get', null, { project_id })
  },
  getInvestGive () {
    return fetchAPI(`supervisionInvestment/provide/`, 'get')
  },
  deleteInvestGive (provide_id) {
    return fetchAPI(`supervisionInvestment/provide/${provide_id}/`, 'delete')
  },
  getInvestGiveMatter (provide_id) {
    return fetchAPI(`supervisionInvestment/provide/${provide_id}/`, 'get')
  },
  updateInvestGive (provide_id, data) {
    return fetchAPI(`supervisionInvestment/provide/${provide_id}/`, 'put', data)
  },
  createInvestGive (project_id, data) {
    return fetchAPI('supervisionInvestment/provide/', 'post', data, { project_id })
  },
}