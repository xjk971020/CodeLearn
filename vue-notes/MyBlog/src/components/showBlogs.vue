<template>
  <div v-theme:column id="show-blogs">
    <h1>博客总览</h1>
    <input type="text" v-model="search" placeholder="请输入搜索内容" />
    <div v-for="blog in filteredBlogs" :key="blog.id" class="single-blog">
      <router-link :to="'/blog/' + blog.id">
        <h3 v-ranbow>{{ blog.title | to-uppercase }}</h3>
      </router-link>
      <article>
        {{ blog.content | snippet }}
      </article>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "show-blogs",
  data() {
    return {
      blogs: [],
      search: ""
    };
  },
  created() {
    //如果要请求本地json,需要放到static文件夹下 ./../static/data.json
    // this.$http
    //   .get("./../../static/json/showAllBLogsResult.json")
    //   .then(function(result) {
    //     var response = result.body.data;
    //     if (response.status == "success") {
    //       this.blogs = response.data.splice(0, 10);
    //     }
    //   });
    axios
      .get("/showAllBLogsResult.json")
      //用箭头函数保证作用域
      .then(result =>  {
        var response = result.data.data;
        if (response.status == "success") {
           this.blogs = response.data.splice(0, 10);
        }
      });
  },
  computed: {
    filteredBlogs: function() {
      return this.blogs.filter(blog => {
        return blog.title.match(this.search);
      });
    }
  },
  filters: {
    //  "to-uppercase": function(value) {
    //      return value.toUpperCase();
    //  },
    toUppercase(value) {
      return value.toUpperCase();
    }
  }
};
</script>

<style scoped>
input {
  padding: 8px;
  width: 100%;
  box-sizing: border-box;
}

#show-blogs {
  max-width: 800px;
  margin: 0 auto;
}

.single-blog {
  padding: 20px;
  margin: 20px 0;
  box-sizing: border-box;
  background: #eee;
  border: 1px dotted #aaa;
}

#show-blogs a {
  color: #444;
  text-decoration: none;
}
</style>
