
import Vue from 'vue'

import videoShow from './videoShow.vue'

import Video from 'video.js'
import 'video.js/dist/video-js.css'

Vue.prototype.$video = Video



// var login = {
//   template: '<h1>这是login组件，是用网页中的形式创建出来的</h1>'
// }
var vm = new Vue({
  el: '#app',
  render: c => c(videoShow)
})
