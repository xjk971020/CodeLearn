// 1. 导入 vue-router 包
import VueRouter from 'vue-router'

import account from './main/account.vue'
import usersList from './main/usersList.vue'
import login from './subcom/login.vue'
import register from './subcom/register.vue'

// 3. 创建路由对象
var router = new VueRouter({
  routes:[
    {
      path : "/account",
      component: account,
      children: [
        {path : "login", component : login},
        {path : "register", component : register}
      ]
    },
    {path : "/usersList", component: usersList}
  ]
})

export default router