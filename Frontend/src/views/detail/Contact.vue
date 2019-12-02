<template>
  <div>
    <contact-modal
      :record="modal.record"
      :visible="modal.visible"
      :type="modal.type"
      v-if="modal.visible"
      @close="handleClose()">
    </contact-modal>
    <image-modal
      :record="imageModal.record"
      :visible="imageModal.visible"
      :type="imageModal.type"
      v-if="imageModal.visible"
      @close="handleImageClose()">
    </image-modal>
    <a-card>
      <a-row>
        <a-col
          :xs="{span: 20, offset: 2}"
          :sm="{span: 16, offset: 4}"
          :xl="{span: 12, offset: 6}">
          <img v-if="images" class="img" :src="images.url" alt="项目图片">
        </a-col>
      </a-row>
      <a-row :gutter="6">
        <a-col
          class="item"
          :xs="{span: 12}"
          :sm="{span: 6}"
          :xl="{span: 4, offset: 2}">
          <a-select
            v-model="params.ordering"
            style="width: 100%"
            @change="getData()">
            <a-select-option value="">排序</a-select-option>
            <a-select-option value="company">单位正排序</a-select-option>
            <a-select-option value="-company">单位倒排序</a-select-option>
            <a-select-option value="name">姓名正排序</a-select-option>
            <a-select-option value="-name">姓名倒排序</a-select-option>
          </a-select>
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <a-select
            v-model="params.company_type"
            style="width: 100%"
            @change="getData()">
            <a-select-option value="">类型筛选</a-select-option>
            <a-select-option :value="item.value" v-for="item in typeList">{{item.label}}</a-select-option>
          </a-select>
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <a-input
            v-model="params.search"
            style="width: 100%"
            placeholder="单位，姓名，岗位">
          </a-input>
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <a-button
            style="width: 100%"
            icon="search"
            @click="getData()">
            搜索
          </a-button>
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <a-button
            style="width: 100%"
            icon="delete"
            @click="handleClearParams()">
            重置
          </a-button>
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <a-button
            style="width: 100%"
            icon="download"
            @click="handleUploadImage()">
            上传图片
          </a-button>
        </a-col>
        <a-col
          class="item"
          v-bind="layout">
          <a-button
            type="primary"
            icon="plus"
            style="width: 100%"
            @click="handleCreate()">
            新建
          </a-button>
        </a-col>
      </a-row>
      <a-table
        class="item"
        bordered
        :columns="columns"
        :dataSource="data"
        rowKey="id"
        :pagination="false"
        :loading="status.loading">
        <template slot="operation" slot-scope="text, record, index">
          <a-button class="button" @click="handleEdit(record)">编辑</a-button>
          <a-popconfirm
            title="确认删除吗?"
            @confirm="handleDelete(record)">
            <a-button class="button" type="danger">删除</a-button>
          </a-popconfirm>
        </template>
      </a-table>
      <a-pagination
        class="item"
        v-model="params.page"
        @change="handelePage"
        style="float: right"
        :total="count">
      </a-pagination>
    </a-card>
  </div>
</template>

<script>

const columns = [
  {
    title: '单位',
    dataIndex: 'company',
    width: '10%',
    align: 'center'
  },
  {
    title: '单位类型',
    dataIndex: 'company_type.label',
    width: '14%',
    align: 'center'
  },
  {
    title: '姓名',
    dataIndex: 'name',
    width: '8%',
    align: 'center'
  },
  {
    title: '岗位',
    dataIndex: 'job',
    width: '10%',
    align: 'center'
  },
  {
    title: '邮箱',
    dataIndex: 'email',
    width: '17%',
    align: 'center'
  },
  {
    title: '联系电话',
    dataIndex: 'telephone',
    width: '15%',
    align: 'center'
  },
  {
    title: '备注',
    dataIndex: 'note',
    width: '13%',
    align: 'center'
  },
  {
    title: '编辑',
    dataIndex: 'operation',
    align: 'center',
    scopedSlots: { customRender: 'operation' }
  }
]
import api from '../../api/detail'
import ContactModal from './components/ContactModal'
import ImageModal from './components/ImageModal'
import { mapGetters } from 'vuex'
import { getCookie } from '../../utils/util'

export default {
  name: 'RelatedCompany',
  components: {
    ContactModal,
    ImageModal
  },
  data () {
    return {
      layout: {
        xs: { span: 12 },
        sm: { span: 6 },
        xl: { span: 3 }
      },
      status: {
        loading: true
      },
      project_id: this.projectSelected().id,
      params: {
        search: '',
        page: 1,
        ordering: '-company',
        company_type: '',
        project_id: this.projectSelected().id
      },
      modal: {
        record: null,
        visible: false,
        type: '1'
      },
      imageModal: {
        record: null,
        visible: false,
        type: '1'
      },
      typeList: [],
      count: 0,
      data: [],
      columns,
      image: {
        uuid: null,
        url: null
      },
      images:this.image,
      headers: {
        'X-CSRFToken': getCookie('csrftoken')
      }
    }
  },
  mounted () {
    api.getTypeList(this.project_id)
      .then(data => {
        this.ty = data
      })
    this.getData()
  },
  methods: {
    ...mapGetters(['projectSelected']),
    getData () {
      this.status.loading = true
      api.getAddressList(this.params)
        .then(data => {
          console.log(data)
          this.data = data.results
          this.count = data.count
          this.status.loading = false
        })
      api.getImage(this.project_id)
        .then(data => {
          this.image = data
          console.log(this.image)
          if(data.url==0){
            this.images=null
          }
          else
            this.images=data
          console.log(this.images)
        })
    },
    handleUploadImage () {
      if (this.image.url==0) {
        this.imageModal.type = 'create'
        this.imageModal.record = this.image
        this.imageModal.visible = true
      } else {
        this.imageModal.type = 'edit'
        this.imageModal.visible = true
        this.imageModal.record = this.image
      }
    },
    handleImageClose () {
      this.imageModal.type = ''
      this.imageModal.record = null
      this.imageModal.visible = false
      this.getData()
    },
    handleClearParams () {
      this.params.ordering = 'company'
      this.params.search = ''
      this.params.page = 1
      this.params.company_type = ''
      this.getData()
    },
    handelePage (page) {
      this.params.page = page
      this.getData()
    },
    handleClose () {
      this.modal.type = ''
      this.modal.record = null
      this.modal.visible = false
      this.getData()
    },
    handleCreate () {
      this.modal.type = 'create'
      this.modal.record = null
      this.modal.visible = true
    },
    handleEdit (record) {
      this.modal.type = 'edit'
      this.modal.record = record
      this.modal.visible = true
    },
    handleDelete (record) {
      api.deleteAddressListDetail(record.id)
        .then(data => {
          this.$notification.success({ message: '成功', description: '删除成功', key: 'SUCCESS' })
          this.getData()
        })
      this.getData()
    }
  }
}
</script>

<style lang="less" scoped>
  @import '~ant-design-vue/es/style/themes/default';

  .item {
    margin: 10px 0;
  }

  .img {
    display: block;
    margin: 0 auto 20px auto;
    width: 100%;
    border-radius: @border-radius-base;
    box-shadow: 0 0 5px @shadow-color;
  }
</style>