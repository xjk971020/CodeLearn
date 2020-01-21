<template>
  <div id="single-blog">
    <h1>{{ blog.title }}</h1>
    <p>作者: {{ blog.author }}</p>
    <p>分类</p>
    <ul>
      <li v-for="category in blog.categories" :key="category">
        {{ category }}
      </li>
    </ul>
    <hr>
    <p>内容:</p>
    <article>{{ blog.content }}</article>
  </div>
</template>

<script>
export default {
  name: "single-blog",
  data() {
    return {
      id: this.$route.params.id,
      blog: {}
    };
  },
  created() {
    this.blog = this.$http
      .get("./../../static/json/showAllBLogsResult.json")
      .then(function(result) {
        var response = result.body.data;
        if (response.status == "success") {
          var blogs = [];
          blogs = response.data;
          blogs = blogs.filter(blog => {
            if (blog.id == this.id) {
              return blog;
            }
          });
          this.blog = blogs[0];
        }
      });
  }
};
</script>

<style scoped>
#single-blog {
  max-width: 960px;
  margin: 0 auto;
  padding: 20px;
  background: #eee;
  border: 1px dotted #aaa;
}
</style>
