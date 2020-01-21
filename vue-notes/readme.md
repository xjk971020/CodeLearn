# 笔记

## [第一天](https://github.com/xjk971020/vue-notes/blob/master/2020-01-12/day1.md)

-  什么是Vue.js
-  Node（后端）中的 MVC 与 前端中的 MVVM 之间的区别
- Vue之 - `基本的代码结构`和`插值表达式`、`v-cloak`
-  Vue指令之`v-text`和`v-html`**
- Vue指令之`v-bind`的三种用法
-  **Vue指令之`v-on`和`跑马灯效果`-**
- Vue指令之`v-on的缩写`和`事件修饰符`
-  事件修饰符
-  Vue指令之`v-model`和`双向数据绑定`
- 简易计算器案例
- 在Vue中使用样式
  - 使用class样式 
  - 使用内联样式
-  Vue指令之`v-for`和`key`属性
- Vue指令之`v-if`和`v-show`
- 品牌管理案例
  - 添加新品牌
  - 删除品牌
  - 根据条件筛选品牌
- 过滤器
  - 私有过滤器
  - 全局过滤器
- 键盘修饰符以及自定义键盘修饰符
  - 1.x中自定义键盘修饰符【了解即可】
  -  [2.x中自定义键盘修饰符](https://cn.vuejs.org/v2/guide/events.html#键值修饰符)
- [自定义指令](https://cn.vuejs.org/v2/guide/custom-directive.html)
  - 1.自定义全局和局部的 自定义指令
  - 2.自定义指令的使用方式

## [第二天](https://github.com/xjk971020/vue-notes/blob/master/2020-01-13/day2.md)

- 在2.x版本中[手动实现筛选的方式](https://cn.vuejs.org/v2/guide/list.html#显示过滤-排序结果)

- [vue实例的生命周期](https://cn.vuejs.org/v2/guide/instance.html#实例生命周期)

- [vue-resource 实现 get, post, jsonp请求](https://github.com/pagekit/vue-resource)

- [Vue中的动画](https://cn.vuejs.org/v2/guide/transitions.html)

  - [使用第三方 CSS 动画库](https://cn.vuejs.org/v2/guide/transitions.html#自定义过渡类名)

  - 使用动画钩子函数

- [v-for 的列表过渡](https://cn.vuejs.org/v2/guide/transitions.html#列表的进入和离开过渡)

- 列表的排序过渡

## [**第三天**](https://github.com/xjk971020/vue-notes/blob/master/2020-01-14/day3.md)

- 定义组件的三种方式
- 组件中展示数据和响应事件
- 使用`components`属性定义局部子组件
- 使用`flag`标识符结合`v-if`和`v-else`切换组件
- 使用`:is`属性来切换不同的子组件,并添加切换动画
- 父组件向子组件传值
- 子组件向父组件传值
- 使用 `this.$refs` 来获取元素和组件
- 在 vue 中使用 vue-router
- 设置路由高亮、设置路由切换动效
- 在路由规则中定义参数
- 使用 `children` 属性实现路由嵌套
- [命名视图实现经典布局](https://github.com/xjk971020/vue-notes/blob/master/2020-01-14/%E8%B7%AF%E7%94%B1/%E8%B7%AF%E7%94%B1-%E4%BD%BF%E7%94%A8%E5%91%BD%E5%90%8D%E8%A7%86%E5%9B%BE%E5%AE%9E%E7%8E%B0%E7%BB%8F%E5%85%B8%E5%B8%83%E5%B1%80.html)
- `watch`属性的使用
- `computed`计算属性的使用
- `watch`、`computed`和`methods`之间的对比
- `nrm`的安装使用

## [第四天]([https://github.com/xjk971020/vue-notes/blob/master/2020-01-15/webpack%E9%A1%B9%E7%9B%AE%E6%AD%A5%E9%AA%A4.md](https://github.com/xjk971020/vue-notes/blob/master/2020-01-15/day4.md))

- 在网页中会引用哪些常见的静态资源？
- 网页中引入的静态资源多了以后有什么问题？？？
- 如何解决上述两个问题
- 什么是webpack?
- [webpack项目构建步骤]([https://github.com/xjk971020/vue-notes/blob/master/2020-01-15/webpack%E9%A1%B9%E7%9B%AE%E6%AD%A5%E9%AA%A4.md](https://github.com/xjk971020/vue-notes/blob/master/2020-01-15/webpack项目步骤.md))
- 如何完美实现上述的2种解决方案
- webpack安装的两种方式、初步使用webpack打包构建列表隔行变色案例
- 使用webpack的配置文件简化打包时候的命令
- 实现webpack的实时打包构建
- 使用`html-webpack-plugin`插件配置启动页面
- 实现自动打开浏览器、热更新和配置浏览器的默认端口号
  - 方式一
  - 方式二
- 使用webpack打包css文件
- 使用webpack打包less文件
- 使用webpack打包sass文件
- 使用webpack处理css中的路径
- 使用babel处理高级JS语法

## [第五天](https://github.com/xjk971020/vue-notes/blob/master/2020-01-16/day5.md)

- 在普通页面中使用render函数渲染组件
- 在webpack中配置.vue组件页面的解析
- 在使用webpack构建的Vue项目中使用模板对象
- ES6中语法使用总结
- 在vue组件页面中，集成[vue-router](https://router.vuejs.org/)路由模块
- 组件中的css作用域问题
- 抽离路由为单独的模块