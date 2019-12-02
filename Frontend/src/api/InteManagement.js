import fetchAPI from '../utils/fetch'

export default {
  //行政审批
  getAdminiApproList (project_id) {
    return fetchAPI('supervisionIntegrated/administrative/', 'get', null, { project_id })
  },
  getAdminiAppro (id) {
    return fetchAPI(`supervisionIntegrated/administrative/${id}/`, 'get')
  },
   createAdminiApproItemFile (id, file) {
    return fetchAPI(`supervisionIntegrated/administrative/file/${id}/`, 'post', file)
  },
  deleteAdminiApproItemFile (id) {
    return fetchAPI(`supervisionIntegrated/administrative/file/${id}/`, 'delete')
  },
  //技术文件
   getTechnologyList (project_id) {
    return fetchAPI('supervisionIntegrated/technology/', 'get', null, { project_id })
  },
  getTechnology (id) {
    return fetchAPI(`supervisionIntegrated/technology/${id}/`, 'get')
  },
   createTechnologyItemFile (id, file) {
    return fetchAPI(`supervisionIntegrated/technology/file/${id}/`, 'post', file)
  },
  deleteTechnologyItemFile (id) {
    return fetchAPI(`supervisionIntegrated/technology/file/${id}/`, 'delete')
  },
  //指导文件
   getGuideList (project_id) {
    return fetchAPI('supervisionIntegrated/guidance/', 'get', null, { project_id })
  },
  getGuide (id) {
    return fetchAPI(`supervisionIntegrated/guidance/${id}/`, 'get')
  },
   createGuideItemFile (id, file) {
    return fetchAPI(`supervisionIntegrated/guidance/file/${id}/`, 'post', file)
  },
  deleteGuideItemFile (id) {
    return fetchAPI(`supervisionIntegrated/guidance/file/${id}/`, 'delete')
  },
  //工作记录
   getWorkRecodingList (project_id) {
    return fetchAPI('supervisionIntegrated/jobLogging/', 'get', null, { project_id })
  },
  getWorkRecoding (id) {
    return fetchAPI(`supervisionIntegrated/jobLogging/${id}/`, 'get')
  },
   createWorkRecodingItemFile (id, file) {
    return fetchAPI(`supervisionIntegrated/jobLogging/file/${id}/`, 'post', file)
  },
  deleteWorkRecodingItemFile (id) {
    return fetchAPI(`supervisionIntegrated/jobLogging/file/${id}/`, 'delete')
  },
  //工作成果
   getWorkResultList (project_id) {
    return fetchAPI('supervisionIntegrated/achievement/', 'get', null, { project_id })
  },
  getWorkResult (id) {
    return fetchAPI(`supervisionIntegrated/achievement/${id}/`, 'get')
  },
   createWorkResultItemFile (id, file) {
    return fetchAPI(`supervisionIntegrated/achievement/file/${id}/`, 'post', file)
  },
  deleteWorkResultItemFile (id) {
    return fetchAPI(`supervisionIntegrated/achievement/file/${id}/`, 'delete')
  },
  //资质审查
  getQualificationList (project_id) {
    return fetchAPI(`supervisionIntegrated/aptitude/`, 'get', null, { project_id })
  },
  getQualification () {
    return fetchAPI(`supervisionIntegrated/aptitude/`, 'get')
  },
  deleteQualification (qualification_id) {
    return fetchAPI(`supervisionIntegrated/aptitude/${qualification_id}/`, 'delete')
  },
  getQualificationMatter (id) {
    return fetchAPI(`supervisionIntegrated/aptitude/${id}/`, 'get')
  },
  updateQualification(qualification_id, data) {
    return fetchAPI(`supervisionIntegrated/aptitude/${qualification_id}/`, 'put', data)
  },
  createQualification (project_id, data) {
    return fetchAPI('supervisionIntegrated/aptitude/', 'post', data, { project_id })
  },
  //营业执照
  getQualiLicenseFile (id) {
    return fetchAPI(`supervisionIntegrated/aptitude/${id}/`, 'get')
  },
  createQualiLicenseFile (id, file) {
    return fetchAPI(`supervisionIntegrated/aptitude/license/${id}/`, 'post', file)
  },
  deleteQualiLicenseFile (id) {
    return fetchAPI(`supervisionIntegrated/aptitude/license/${id}/`, 'delete')
  },
  //资质证书
   getQualiCertificateFile (id) {
    return fetchAPI(`supervisionIntegrated/aptitude/${id}/`, 'get')
  },
  createQualiCertificateFile (id, file) {
    return fetchAPI(`supervisionIntegrated/aptitude/certificate/${id}/`, 'post', file)
  },
  deleteQualiCertificateFile (id) {
    return fetchAPI(`supervisionIntegrated/aptitude/certificate/${id}/`, 'delete')
  },
  //管理人员
  getQualiManagerFile (id) {
    return fetchAPI(`supervisionIntegrated/aptitude/${id}/`, 'get')
  },
  createQualiManagerFile (id, file) {
    return fetchAPI(`supervisionIntegrated/aptitude/manager/${id}/`, 'post', file)
  },
  deleteQualiManagerFile (id) {
    return fetchAPI(`supervisionIntegrated/aptitude/manager/${id}/`, 'delete')
  },
  //特殊工种
  getQualiSpecialFile (id) {
    return fetchAPI(`supervisionIntegrated/aptitude/${id}/`, 'get')
  },
  createQualiSpecialFile (id, file) {
    return fetchAPI(`supervisionIntegrated/aptitude/special_worker/${id}/`, 'post', file)
  },
  deleteQualiSpecialFile (id) {
    return fetchAPI(`supervisionIntegrated/aptitude/special_worker/${id}/`, 'delete')
  },
  //类似经验
  getQualiExperienceFile (id) {
    return fetchAPI(`supervisionIntegrated/aptitude/${id}/`, 'get')
  },
  createQualiExperienceFile (id, file) {
    return fetchAPI(`supervisionIntegrated/aptitude/experience/${id}/`, 'post', file)
  },
  deleteQualiExperienceFile (id) {
    return fetchAPI(`supervisionIntegrated/aptitude/experience/${id}/`, 'delete')
  },
  //安全生产许可证
  getQualiProductFile (id) {
    return fetchAPI(`supervisionIntegrated/aptitude/${id}/`, 'get')
  },
  createQualiProductFile (id, file) {
    return fetchAPI(`supervisionIntegrated/aptitude/production_license/${id}/`, 'post', file)
  },
  deleteQualiProductFile (id) {
    return fetchAPI(`supervisionIntegrated/aptitude/production_license/${id}/`, 'delete')
  },



}
