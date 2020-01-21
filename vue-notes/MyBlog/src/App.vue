<template>
  <div id="app">
      <blog-header></blog-header>
    <router-view></router-view>
  </div>
</template>

<script>
import addBlog from './components/addBlog'
import showBlogs from './components/showBlogs'
import blogHeader from './components/blogHeader'

export default {
  name: 'App',
  components: {
    addBlog,
    showBlogs,
    blogHeader
  }
}
</script>

<style>

</style>
