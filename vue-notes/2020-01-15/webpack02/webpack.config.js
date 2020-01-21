const path = require("path");
const htmlWebpackPlugin = require("html-webpack-plugin");

module.exports = {
  entry: path.join(__dirname, "./src/main.js"), // 入口，表示，要使用 webpack 打包哪个文件
  output: {
    // 输出文件相关的配置
    path: path.join(__dirname, "./dist"), // 指定 打包好的文件，输出到哪个目录中去
    filename: "bundle.js" // 这是指定 输出的文件的名称
  },
  plugins: [
    new htmlWebpackPlugin({
      template: path.join(__dirname, "./src/index.html"), //制定模板文件路径
      filename: "index.html" //指定生成页面的名称
    })
  ],
  module: {
    rules: [
      { test: /\.css$/, use: ["style-loader", "css-loader"] }, //配置.css文件的第三方loader规则
      { test: /\.js$/, use: 'babel-loader', exclude: /node_modules/ }, // 配置 Babel 来转换高级的ES语法
    ]
  }
};
