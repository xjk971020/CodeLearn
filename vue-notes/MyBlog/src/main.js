// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from "vue";
import App from "./App";
import router from './router'

import VueResource from "vue-resource";
import VueRouter from 'vue-router';

import axios from 'axios'

axios.defaults.baseURL = './../../static/json';

Vue.config.productionTip = false;

Vue.use(VueResource);
Vue.use(VueRouter);
Vue.use(axios);

Vue.directive("ranbow", {
  bind: function(el, binding, vnode) {
    el.style.color = "#" + Math.random().toString().slice(2,8);
  }
});

//自定义指令,重要
Vue.directive("theme", {
  bind:function(el, binding, vnode) {
    if (binding.value === "width") {
      el.style.width = '1260px';
    } else if (binding.value == "narrow") {
      el.style.width = '560px';
    }

    if (binding.arg == "column") {
      el.style.background = "#6677cc";
      el.style.padding  = '20px';
    }
  }
})

// Vue.filter("to-uppercase", function(value) {
//   return value.toUpperCase();
// })

Vue.filter("snippet", function(value) {
  return value.slice(0, 100) + '...';
})

/* eslint-disable no-new */
new Vue({
  el: "#app",
  components: { App },
  template: "<App/>",
  router
});
