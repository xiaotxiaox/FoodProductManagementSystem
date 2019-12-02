<template>
  <a-modal
    v-if="this.project_category==='1'"
    :title="title"
    :visible="visible"
    :keyboard="false"
    :maskClosable="false"
    okText="保存"
    okType="primary"
    @ok="handleOk()"
    @cancel="handleCancel()">
    <a-form
      :form="form">
      <a-form-item
        label="建筑名称"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              rules:[
                { required: true, message: '请输入名称' },
                { max:32, message: '长度在32个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.name : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="奖项"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}">
        <a-select
          placeholder="请选择"
          option.initialValue="city"
          v-decorator="[
                'awards',
                {
                initialValue: record?record.awards:'1'}
              ]">
          <a-select-option :key="1" value="1">市级</a-select-option>
          <a-select-option :key="2" value="2">省级</a-select-option>
          <a-select-option :key="3" value="3">国级</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="基础"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}">
        <a-select
          placeholder="请选择"
          option.initialValue="1"
          v-decorator="[
                'base',
                {
                initialValue: record?record.base:'1'}
              ]">
          <a-select-option :key="1" value="1">合格</a-select-option>
          <a-select-option :key="2" value="2">不合格</a-select-option>
          <a-select-option :key="3" value="3">严重不合格</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="主体"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}">
        <a-select
          placeholder="请选择"
          option.initialValue="1"
          v-decorator="[
                'mainBody',
                {
                initialValue: record?record.mainBody:'1'}
              ]">
          <a-select-option :key="1" value="1">合格</a-select-option>
          <a-select-option :key="2" value="2">不合格</a-select-option>
          <a-select-option :key="3" value="3">严重不合格</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="装饰"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}">
        <a-select
          placeholder="请选择"
          option.initialValue="1"
          v-decorator="[
                'decorate',
                {
                initialValue: record?record.decorate:'1'}
              ]">
        <a-select-option :key="1" value="1">合格</a-select-option>
          <a-select-option :key="2" value="2">不合格</a-select-option>
          <a-select-option :key="3" value="3">严重不合格</a-select-option>
        </a-select>
      </a-form-item>
       <a-form-item
        label="屋面"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}">
        <a-select
          placeholder="请选择"
          option.initialValue="good"
          v-decorator="[
                'roofing',
                {
                initialValue: record?record.roofing:'1'}
              ]">
          <a-select-option :key="1" value="1">合格</a-select-option>
          <a-select-option :key="2" value="2">不合格</a-select-option>
          <a-select-option :key="3" value="3">严重不合格</a-select-option>
        </a-select>
      </a-form-item>
       <a-form-item
        label="防水"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}">
        <a-select
          placeholder="请选择"
          option.initialValue="good"
          v-decorator="[
                'waterproof',
                {
                initialValue: record?record.waterproof:'good'}
              ]">
         <a-select-option :key="1" value="1">合格</a-select-option>
          <a-select-option :key="2" value="2">不合格</a-select-option>
          <a-select-option :key="3" value="3">严重不合格</a-select-option>
        </a-select>
      </a-form-item>
       <a-form-item
        label="保温"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}">
        <a-select
          placeholder="请选择"
          option.initialValue="good"
          v-decorator="[
                'insulation',
                {
                initialValue: record?record.insulation:'good'}
              ]">
         <a-select-option :key="1" value="1">合格</a-select-option>
          <a-select-option :key="2" value="2">不合格</a-select-option>
          <a-select-option :key="3" value="3">严重不合格</a-select-option>
        </a-select>
      </a-form-item>

    </a-form>
  </a-modal>
  <a-modal
    v-else
    :title="title"
    :visible="visible"
    :keyboard="false"
    :maskClosable="false"
    okText="保存"
    okType="primary"
    @ok="handleOk()"
    @cancel="handleCancel()">
    <a-form
      :form="form">
      <a-form-item
        label="建筑名称"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}"
        v-bind="layout">
        <a-input
          type="text"
          v-decorator="[
            'name',
            {
              rules:[
                { required: true, message: '请输入名称' },
                { max:32, message: '长度在32个汉字以内' }
              ],
              validateTrigger: 'blur',
              initialValue: record ? record.name : null
            }
          ]">
        </a-input>
      </a-form-item>
      <a-form-item
        label="奖项"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}">
        <a-select
          placeholder="请选择"
          option.initialValue="1"
          v-decorator="[
                'awards',
                {
                initialValue: record?record.awards:'1'}
              ]">
          <a-select-option :key="1" value="1">市级</a-select-option>
          <a-select-option :key="2" value="2">省级</a-select-option>
          <a-select-option :key="3" value="3">国级</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="基础"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}">
        <a-select
          placeholder="请选择"
          option.initialValue="1"
          v-decorator="[
                'base',
                {
                initialValue: record?record.base:'1'}
              ]">
          <a-select-option :key="1" value="1">合格</a-select-option>
          <a-select-option :key="2" value="2">不合格</a-select-option>
          <a-select-option :key="3" value="3">严重不合格</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="路层"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}">
        <a-select
          placeholder="请选择"
          option.initialValue="1"
          v-decorator="[
                'choice_A',
                {
                initialValue: record?record.choice_A:'1'}
              ]">
          <a-select-option :key="1" value="1">合格</a-select-option>
          <a-select-option :key="2" value="2">不合格</a-select-option>
          <a-select-option :key="3" value="3">严重不合格</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item
        label="路面"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}">
        <a-select
          placeholder="请选择"
          option.initialValue="1"
          v-decorator="[
                'choice_B',
                {
                initialValue: record?record.choice_B:'1'}
              ]">
        <a-select-option :key="1" value="1">合格</a-select-option>
          <a-select-option :key="2" value="2">不合格</a-select-option>
          <a-select-option :key="3" value="3">严重不合格</a-select-option>
        </a-select>
      </a-form-item>
       <a-form-item
        label="桥涵"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}">
        <a-select
          placeholder="请选择"
          option.initialValue="1"
          v-decorator="[
                'choice_C',
                {
                initialValue: record?record.choice_C:'1'}
              ]">
          <a-select-option :key="1" value="1">合格</a-select-option>
          <a-select-option :key="2" value="2">不合格</a-select-option>
          <a-select-option :key="3" value="3">严重不合格</a-select-option>
        </a-select>
      </a-form-item>
       <a-form-item
        label="路灯"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}">
        <a-select
          placeholder="请选择"
          option.initialValue="1"
          v-decorator="[
                'choice_D',
                {
                initialValue: record?record.choice_D:'1'}
              ]">
         <a-select-option :key="1" value="1">合格</a-select-option>
          <a-select-option :key="2" value="2">不合格</a-select-option>
          <a-select-option :key="3" value="3">严重不合格</a-select-option>
        </a-select>
      </a-form-item>
       <a-form-item
        label="绿化"
        :label-col="{span: 8}"
        :wrapper-col="{span: 12}">
        <a-select
          placeholder="请选择"
          option.initialValue="1"
          v-decorator="[
                'choice_E',
                {
                initialValue: record?record.choice_E:'1'}
              ]">
         <a-select-option :key="1" value="1">合格</a-select-option>
          <a-select-option :key="2" value="2">不合格</a-select-option>
          <a-select-option :key="3" value="3">严重不合格</a-select-option>
        </a-select>
      </a-form-item>

    </a-form>
  </a-modal>
</template>

<script>
  import api from '../../../../api/implement'
  import { mapGetters } from 'vuex'
  import apiProject from '../../../../api/project'
  export default {
    name: 'QualityModal',
    props: {
      record: Object,
      visible: Boolean,
      type: String,
    },
    data () {
      return {
        project_id:this.projectSelected().id,
        layout: {
          'label-col': { span: 8 },
          'wrapper-col': { span: 12 }
        },
        project_category:null,
        form: this.$form.createForm(this),
        matter: {}
      }
    },
    mounted () {
      this.getData()
    },
    methods: {
       ...mapGetters(['projectSelected']),
      getData() {
        apiProject.getProject(this.projectSelected().id)
          .then(data => {
            this.project_category = data.category
          })},
      handleOk () {
        this.form.validateFields((error, data) => {
          if (!error) {
              api.updateQuality(this.record.building_id, data)
                .then(data => {
                  this.$notification.success({message: '成功', description: '更新成功', key: 'SUCCESS'})
                  this.$emit('close')
                })
          }
        })
      },
      handleCancel () {
        this.$emit('close')
      }

    },
    computed: {
      title () {
        return  '编辑'
      }
    }
  }
</script>

<style scoped lang="less">

</style>