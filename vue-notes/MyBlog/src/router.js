import ShowBlogs from "./components/showBlogs";
import AddBlog from "./components/addBlog";
import SingleBlog from "./components/singleBlog";

import Vue from 'vue'
import VueRouter from "vue-router";

Vue.use(VueRouter);

const router = new VueRouter({
  routes: [
    // { path: "/", redirect: "/show" },
    { path: "/", component: ShowBlogs },
    { path: "/add", component: AddBlog },
    {path:"/blog/:id", component:SingleBlog}
  ],
  //添加后在路由中可以不用添加#
  mode: "history"
});

export default router;
