### 1、flex 布局的概念  

- Flex是Flexible Box的缩写，意为"弹性布局"，用来为盒状模型提供最大的灵活性。

- 任何一个容器都可以指定为 Flex 布局，行内元素也可以通过 inline-flex 使属性值用 Flex 布局。
  
- inline-flex 和 inline-block 一样，对设置了该属性值的元素的子元素来说是个 display:flex 的容器，对外部元素来说是个 inline 的块。  

- 设为 Flex 布局以后，子元素的float、clear和vertical-align属性将失效。

### 2、容器的属性  
- 2.1、flex-direction（设置子元素的排列方向） 

    - row | row-reverse | column | column-reverse; 
    
    - row（默认值）：主轴为水平方向，起点在左端；
    
    - row-reverse：主轴为水平方向，起点在右端。
    
    - column：主轴为垂直方向，起点在上沿。
    
    - column-reverse：主轴为垂直方向，起点在下沿。
  
- 2.2、flex-wrap属性（设置是否换行）  

    - nowrap | wrap | wrap-reverse;
    
    - nowrap（默认）：不换行。
      
    - wrap：换行，第一行在上方。
      
    - wrap-reverse：换行，第一行在下方。
    
- 2.3、flex-flow 属性（flex-direction和flex-wrap的结合）  

    - \<flex-direction> || \<flex-wrap>;

- 2.4、justify-content属性（设置子元素在横轴上的排列）  

    - flex-start | flex-end | center | space-between | space-around;

    - flex-start（默认值）：左对齐；　　　　

    - flex-end：右对齐；　　　　　　　　　　

    - center： 居中；　　　　　　　　　　

    - space-between：两端对齐，项目之间的间隔都相等；
    - space-around：每个子元素之间的间隔相等，且子元素之间的间隔比子元素与父元素的距离大一倍。

- 2.5、align-items属性（设置子元素在纵轴上的排列）  

    - flex-start | flex-end | center | baseline | stretch;
    - flex-start：交叉轴的起点对齐。　　　　 
    - flex-end：交叉轴的终点对齐。　　　　   
    - center：交叉轴的中点对齐。
    - baseline: 项目的第一行文字的基线对齐。
    - stretch（默认值）：如果子元素未设置高度 height 或设为auto，将占满整个容器的高度。如果子元素设置了宽度，则该属性值无效

- 2.6、align-content属性（设置每行子元素之间的排列方式）  

    - flex-start | flex-end | center | space-between | space-around | stretch;
    - stretch（默认值）：每行子元素默认排列，相当于只设置了flex-wrap:wrap，每行子元素间有一定间隔
    - flex-start：子元素从纵轴的起点开始排列，且行间没有间距     
    - flex-end：与纵轴的终点对齐，且行间没有间距。　
    - center：与纵轴的中点对齐。　　　　
    - space-between：与纵轴两端对齐，轴线之间的间隔平均分布。
    - space-around：每行子元素的间隔都相等，且每行子元素之间的间隔比子元素到父元素的距离大一倍。


### 3、子元素的属性  
- 3.1、order 属性（设置某个子元素的排列先后顺序）  

    - \<integer>
    - `order`属性定义项目的排列顺序。数值越小，排列越靠前，默认为0。

- 3.2、flex-grow属性（设置子元素的放大比例，默认为0）  

    - \<number>

    - `flex-grow`属性定义项目的放大比例，该属性值默认为`0`，即即使存在剩余空间，也不放大。

- 3.3、flex-shrink属性（设置子元素的缩小比例，默认为1）  

    - \<number>
    - `flex-shrink`属性定义了子元素的缩小比例，默认为1，即默认是如果父元素装不下子元素，如果不换行的话那么子元素都按照一样的比例缩小。该属性取负值无效。

- 3.4、flex-basis属性（设置子元素理想的宽度）  

    - \<length> | auto
    - `flex-basis`属性的默认值为`auto`，即子元素的本来大小。

- 3.5、flex属性（flex-grow、flex-shrink和flex-basis的组合）  

    - none | [ <'flex-grow'> <'flex-shrink'>? || <'flex-basis'>
    - `flex`属性是`flex-grow`, `flex-shrink` 和 `flex-basis`的简写，默认值为`0 1 auto`。后两个属性可选。

- 3.6、align-self属性（设置单个子元素的纵轴排列方式）  

    -  auto | flex-start | flex-end | center | baseline | stretch
    - `align-self`属性允许单个项目有与其他项目不一样的纵轴排列方式，可覆盖`align-items`属性。

参考博客:<a href="http://www.ruanyifeng.com/blog/2019/03/grid-layout-tutorial.html">CSS Grid 网格布局教程 - 阮一峰的网络日志</a>