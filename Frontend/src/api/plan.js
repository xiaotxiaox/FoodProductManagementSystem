import fetchAPI from '../utils/fetch'

export default {
  //商品管理
  getProductList () {
    return fetchAPI('goods', 'get', null)
  },
  createProduct (data) {
    return fetchAPI('goods', 'post', data)
  },
  updateProduct (id, data) {
    return fetchAPI(`goods/${id}/`, 'put', data)
  },
  deleteProduct (id) {
    return fetchAPI(`goods/${id}/`, 'delete')
  },
  //商品--配料对应表
  getGoodMaterialList (id) {
    return fetchAPI('productionPlan/goodMaterial', 'get', null, { id })
  },
  updateIngredient (id, data) {
    return fetchAPI(`productionPlan/goodMaterial/${id}/`, 'put', data)
  },
  deleteIngredient (id) {
    return fetchAPI(`productionPlan/goodMaterial/${id}/`, 'delete')
  },
  createIngredient (id, data) {
    return fetchAPI('productionPlan/goodMaterial', 'post', data, { id })
  },
  //生产计划管理
  getPlanList () {
    return fetchAPI('productionplan', 'get', null)
  },
  createPlan (data) {
    return fetchAPI('productionplan', 'post', data)
  },
  updatePlan (id, data) {
    return fetchAPI(`productionplan/${id}/`, 'put', data)
  },
  deletePlan (id) {
    return fetchAPI(`productionplan/${id}/`, 'delete')
  },
  //生产计划批次
  getPlanRoundList (id) {
    return fetchAPI('workshop/round/productPlan', 'get', null, { id })
  },
  updatePlanRound (id, data) {
    return fetchAPI(`workshop/round/productPlan/${id}/`, 'put', data)
  },
  deletePlanRound (id) {
    return fetchAPI(`workshop/round/productPlan/${id}/`, 'delete')
  },
  createPlanRound (id, data) {
    return fetchAPI('workshop/round/productPlan', 'post', data, { id })
  },
}