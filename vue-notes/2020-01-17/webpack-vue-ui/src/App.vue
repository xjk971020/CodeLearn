<template>
  <div>
    <h1>这是app组件</h1>
    <mt-button type="default">default</mt-button>
    <mt-button type="primary">primary</mt-button>
    <mt-button type="danger">danger</mt-button>
    <mt-button>123</mt-button>
    <router-link to="/account">Account</router-link>
    <router-link to="/usersList">usersList</router-link>
    <router-view></router-view>
  </div>
</template>

<script>
import { Toast } from "mint-ui";

export default {
  data() {
    return {
      toastInstance: null
    };
  },
  created: function() {
    this.getList();
  },
  methods: {
    getList() {
      this.show();
      setTimeout(() => {
        this.toastInstance.close();
      }, 3000);
    },
    show() {
      this.toastInstance = Toast({
        message: "提示消息",
        duration: -1,
        position: "top",
        iconClass: "glyphicon glyphicon-heart",
        className: "myicon"
      });
    }
  }
};
</script>

<style>
.myicon .glyphicon {
  color: red !important;
}
</style>
