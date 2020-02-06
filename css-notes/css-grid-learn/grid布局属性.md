css grid布局属性总结

1. 概念

   - 容器: 采用网格布局的区域，称为"容器"（container）。

   - 项目: 容器内部采用网格定位的子元素，称为"项目"（item）。 
   - 网格线: 划分网格的线，称为"网格线"（grid line）。水平网格线划分出行，垂直网格线划分出列。

2. 容器属性

   - **display**: grid指定一个容器采用网格布局。

   -  **display**: inline-grid; 

     > 注意，设为网格布局以后，容器子元素（项目）的`float`、`display: inline-block`、`display: table-cell`、vertical-align`和`column-*等设置都将失效。

   - **grid-template-columns**: 定义每一列的列宽

   - **grid-template-rows**: 定义每一行的行高

     (1).  repeat()函数可作为grid-template-columns和grid-template-rows属性的值使用

     - `repeat()`接受两个参数，第一个参数是重复的次数，第二个参数是所要重复的值。

       e.g  grid-template-rows: repeat(3, 33.33%);

     - `repeat()`重复某种模式也是可以的。

       e.g grid-template-columns: repeat(2, 100px 20px 80px);

     (2). auto-fill关键字: 如果希望每一行（或每一列）容纳尽可能多的单元格，这时可以使用`auto-fill`关键字表示自动填充。

     - e.g  grid-template-columns: repeat(auto-fill, 100px);

     (3). fr 关键字: 为了方便表示比例关系，网格布局提供了`fr`关键字（fraction 的缩写，意为"片段"）。

     - 如果两列的宽度分别为`1fr`和`2fr`，就表示后者是前者的两倍。

       e.g  grid-template-columns: 1fr 2fr;

     - `fr`可以与绝对长度的单位结合使用，这时会非常方便。

       e.g grid-template-columns: 150px 1fr 2fr;

     (4). minmax(): 产生一个长度范围，表示长度就在这个范围之中。它接受两个参数，分别为最小值和最大值。

     - e.g grid-template-columns: 1fr 1fr minmax(100px, 1fr);

     (5). auto 关键字: `auto`关键字表示由浏览器自己决定长度。

     - e.g  grid-template-columns: 100px auto 100px;

     (6).网格线的名称: `grid-template-columns`属性和`grid-template-rows`属性里面，还可以使用方括号，指定每一根网格线的名字，方便以后的引用。

     - e.g  grid-template-columns: [c1] 100px [c2] 100px [c3] auto [c4];
     - e.g  grid-template-rows: [r1] 100px [r2] 100px [r3] auto [r4];

   - **grid-row-gap** :  设置行与行的间隔（行间距）

   - **grid-column-gap**: 属性设置列与列的间隔（列间距）。

   - **grid-gap**属性是grid-column-gap和grid-row-gap的合并简写形式

     > 根据最新标准，上面三个属性名的`grid-`前缀已经删除，`grid-column-gap`和`grid-row-gap`写成`column-gap`和`row-gap`，`grid-gap`写成`gap`。

   - **grid-template-areas**  : 网格布局允许指定"区域"（area），一个区域由单个或多个单元格组成。

   - **grid-auto-flow**: 划分网格以后，容器的子元素会按照顺序，自动放置在每一个网格。默认的放置顺序是"先行后列"，即先填满第一行，再开始放入第二行，这个顺序由`grid-auto-flow`属性决定，默认值是`row`，即"先行后列"。也可以将它设成`column`，变成"先列后行"。

   - **justify-items**： 设置单元格内容的水平位置（左中右）

     e.g justify-items: start | end | center | stretch;

     - start：对齐单元格的起始边缘。
     - end：对齐单元格的结束边缘。
     - center：单元格内部居中。
     - stretch：拉伸，占满单元格的整个宽度（默认值）。

   -  **align-items**属性设置单元格内容的垂直位置（上中下）

     e.g  align-items: start | end | center | stretch;

     - 同上

   - **place-items**：`align-items`属性和`justify-items`属性的合并简写形式。

     e.g place-items: \<align-items> \<justify-items>;

   - **justify-content**: 整个内容区域在容器里面的水平位置（左中右）

     e.g justify-content: start | end | center | stretch | space-around | space-between | space-evenly;

   - **align-content:** 整个内容区域的垂直位置（上中下）

     e.g align-content: start | end | center | stretch | space-around | space-between | space-evenly;  

   - **place-content**: `align-content`属性和`justify-content`属性的合并简写形式

     e.g place-content: \<align-content> \<justify-content>

   - **grid-auto-columns**和**grid-auto-rows**: 浏览器自动创建的多余网格的列宽和行高。它们的写法与`grid-template-columns`和`grid-template-rows`完全相同。如果不指定这两个属性，浏览器完全根据单元格内容的大小，决定新增网格的列宽和行高。

   - **grid-template**: `grid-template-columns`、`grid-template-rows`和`grid-template-areas`这三个属性的合并简写形式。

   - **grid**属性是`grid-template-rows`、`grid-template-columns`、`grid-template-areas`、 `grid-auto-rows`、`grid-auto-columns`、`grid-auto-flow`这六个属性的合并简写形式。

3. 项目属性

   - **grid-column-start**：左边框所在的垂直网格线

   - **grid-column-end**：右边框所在的垂直网格线

   - **grid-row-start**：上边框所在的水平网格线

   - **grid-row-end**：下边框所在的水平网格线

   - **grid-column**属性是`grid-column-start`和`grid-column-end`的合并简写形式

   - **grid-row**属性是`grid-row-start`属性和`grid-row-end`的合并简写形式。

     e.g 

     ```css
     .item-1 {
       grid-column: 1 / 3;
       grid-row: 1 / 2;
     }
     /* 等同于 */
     .item-1 {
       grid-column-start: 1;
       grid-column-end: 3;
       grid-row-start: 1;
       grid-row-end: 2;
     }
     
     /* 分界线 */
     .item-1 {
       background: #b03532;
       grid-column: 1 / 3;
       grid-row: 1 / 3;
     }
     /* 等同于 */
     .item-1 {
       background: #b03532;
       grid-column: 1 / span 2;
       grid-row: 1 / span 2;
     }
     ```

   - **grid-area**属性指定项目放在哪一个区域，还可用作`grid-row-start`、`grid-column-start`、`grid-row-end`、`grid-column-end`的合并简写形式，直接指定项目的位置。

     e.g grid-area: \<row-start> / \<column-start> / \<row-end> / \<column-end>;

   - **justify-self**属性设置单元格内容的水平位置（左中右），跟`justify-items`属性的用法完全一致，但只作用于单个项目。

     e.g 

     - start：对齐单元格的起始边缘。
     - end：对齐单元格的结束边缘。
     - center：单元格内部居中。
     - stretch：拉伸，占满单元格的整个宽度（默认值）。

   - **align-self**属性设置单元格内容的垂直位置（上中下），跟`align-items`属性的用法完全一致，也是只作用于单个项目。

     e.g 同上

   - **place-self**属性是`align-self`属性和`justify-self`属性的合并简写形式。

详见: [CSS Grid 网格布局教程 - 阮一峰的网络日志](http://www.ruanyifeng.com/blog/2019/03/grid-layout-tutorial.html )