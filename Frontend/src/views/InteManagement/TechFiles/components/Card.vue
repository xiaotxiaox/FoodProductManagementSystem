<template>
  <div class="card-list">
     <router-link :to="{'name': toName, params: {'id': id}}">
      <a-card>
        <p>{{title}}</p>
      </a-card>
    </router-link>
  </div>
</template>

<script>
  export default {
    name: 'Item',
    props: {
      id: Number,
      title: String,
      toName: String
    },
    data () {
      return {}
    },
    methods: {
    }
  }
</script>

<style scoped lang="less">
  @import '~ant-design-vue/es/style/themes/default';

  .card-list {
    .card {
      height: 167px;
      color: @white;
      text-align: center;
      margin: 30px 0;
      border-radius: @border-radius-base;
      box-shadow: 0 0 5px @shadow-color;
    }

    .card:hover {
      box-shadow: 0 0 18px @normal-color;
      transform: scale(1.05);
    }

    .green {
      background-color: @green-5;
    }

    .yellow {
      background-color: @gold-5;
    }

    .red {
      background-color: @red-5;
    }

    .purple {
      background-color: @purple-5;
    }

    .grey {
      background-color: #a9a9a9;
    }
  }
</style>