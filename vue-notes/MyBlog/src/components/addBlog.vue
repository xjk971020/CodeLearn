<template>
  <div id="add-blog">
    <h2>添加博客</h2>
    <form v-if="!submited">
      <label>博客标题</label>
      <input type="text" v-model="blog.title" required />

      <label>博客内容</label>
      <textarea v-model="blog.content"></textarea>

      <label>博客分类:</label>
      <div id="checkboxes">
        <label>vue.js</label>
        <input
          type="checkbox"
          name="vue.js"
          value="vue.js"
          v-model="blog.categories"
        />
        <label>javascript</label>
        <input
          type="checkbox"
          name="javascript"
          value="javascript"
          v-model="blog.categories"
        />
      </div>

      <label>博客作者:</label>
      <select v-model="blog.author">
        <option v-for="author in authors" :key="author.id">
          {{ author }}
        </option>
      </select>

      <button @click.prevent="post">添加博客</button>
    </form>

    <div v-if="submited">
      <h3>博客 {{ blog.title }} 发布成功!</h3>
    </div>

    <div id="preview">
      <h3>博客总览</h3>
      <p>博客标题: {{ blog.title }}</p>
      <p>博客作者: {{ blog.author }}</p>
      <p>博客分类</p>
      <ul>
        <li v-for="category in blog.categories" :key="category.id">
          {{ category }}
        </li>
      </ul>
      <p>博客内容</p>
      <p>{{ blog.content }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "add-blog",
  data() {
    return {
      submited: false,
      blog: {
        title: "",
        content: "",
        categories: [],
        author: ""
      },
      authors: ["Cathetine", "Curry", "JackChen"]
    };
  },
  methods: {
    post: function() {
      this.$http
        .post("https://www.easy-mock.com/mock/5e225ca28022f467b42c8087/blog/add", {
          author: this.blog.author,
          content: this.blog.content,
          title: this.blog.title,
          body: this.blog.content
        })
        .then(function(result) {
          var result = result.body;
          if (result.data.status == "success") {
            alert(result.data.message);
             this.submited = true;
          } else {
            alert("添加失败")
          }
        });
    }
  }
};
</script>

<style scoped>
#add-blog * {
  box-sizing: border-box;
}

#add-blog {
  margin: 20px auto;
  max-width: 600px;
  padding: 20px;
}

label {
  display: block;
  margin: 20px 0 10px;
}

textarea{
    height: 200px;
}

input [type="text"],
textarea,
select {
  display: block;
  width: 100%;
  padding: 8px;
}

#checkboxes {
  margin-top: 10px;
}

#checkboxes label {
  display: inline-block;
  margin-top: 0;
}

#checkboxes input {
  display: inline-block;
  margin-right: 10px;
}

button {
  display: block;
  margin: 20px 0;
  background: crimson;
  color: #fff;
  border: 0;
  padding: 14px;
  border-radius: 4px;
  font-size: 18px;
  cursor: pointer;
}

#preview {
    padding: 10px 20px;
    border: 1px dotted #ccc;
    margin : 30px 0;
}

h3 {
    margin-top: 10px;
}
</style>
