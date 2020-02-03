//屏幕默认大小,初始化图片的占用位置
var screenHeight = window.screen.height + 500;
var imgId = 0;

window.onload = function () {
    console.log(document.documentElement.scrollTop)
    console.log(window.screen.height)
    console.log(document.documentElement.scrollHeight)
    insertImg();
    window.addEventListener("scroll", function () {
        //document.documentElement.scrollTop:已经滚动的内容高度
        //window.screen.height: 窗口高度
        //document.documentElement.scrollHeight:能滚动的最大高度
        if (document.documentElement.scrollTop + window.screen.height > document.documentElement.scrollHeight) {
            screenHeight = screenHeight + 500;
            insertImg();
        }
    })
}

var insertImg = function () {
    let inter = setInterval(function () { //就是给浏览器渲染图片的时间
        if (document.documentElement.scrollHeight > screenHeight) {//达到预期的高度. 停止
            clearInterval(inter);
        }
        if (imgId >= 10) {
            imgId = 0;
        }
        let minDiv = minItem();
        let img = document.createElement("img");
        img.setAttribute("src", "img/" + ++imgId + ".jpg");
        minDiv.appendChild(img);//放图, 给浏览器一个指令. 插图. 但是浏览器的渲染还没有完成.
    }, 100);//定时器, 每隔100毫秒. 运行一次function
}

/**
 * 返回最小高度的div
 * @returns {HTMLElement}
 */
var minItem = function () {
    let waterfall_item_1 = document.getElementById("waterfall-01");
    let waterfall_item_2 = document.getElementById("waterfall-02");
    let waterfall_item_3 = document.getElementById("waterfall-03");
    let waterfall_item_4 = document.getElementById("waterfall-04");

    let waterfall_imgs_1 = waterfall_item_1.children;
    let waterfall_imgs_2 = waterfall_item_2.children;
    let waterfall_imgs_3 = waterfall_item_3.children;
    let waterfall_imgs_4 = waterfall_item_4.children;

    let waterfall_imgs_1_height = caclHeight(waterfall_imgs_1);
    let waterfall_imgs_2_height = caclHeight(waterfall_imgs_2);
    let waterfall_imgs_3_height = caclHeight(waterfall_imgs_3);
    let waterfall_imgs_4_height = caclHeight(waterfall_imgs_4);

    let minHeight = Math.min(waterfall_imgs_1_height, waterfall_imgs_2_height, waterfall_imgs_3_height, waterfall_imgs_4_height);

    if (minHeight == waterfall_imgs_1_height) {
        return waterfall_item_1;
    }

    if (minHeight == waterfall_imgs_2_height) {
        return waterfall_item_2;
    }

    if (minHeight == waterfall_imgs_3_height) {
        return waterfall_item_3;
    }

    if (minHeight == waterfall_imgs_4_height) {
        return waterfall_item_4;
    }
}

/**
 * 计算每一列图片DIV的高度
 */
var caclHeight = function (waterfallItems) {
    let imgItemHeight = 0;
    if (waterfallItems == null || waterfallItems.length == 0) {
        return imgItemHeight;
    } else {
        for (let i = 0; i < waterfallItems.length; ++i) {
            imgItemHeight += waterfallItems[i].height
        }
        return imgItemHeight;
    }
}