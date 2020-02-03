window.onload = imgLoad;
window.onscroll = scrollImg(imgLoad);

function imgLoad() {
    let imgs = document.querySelectorAll('img')
    for (let i = 0; i < imgs.length; ++ i) {
        let top = imgs[i].getBoundingClientRect().top;
        if (top < window.innerHeight) {
            imgs[i].src = imgs[i].dataset.src;
        }
    }
}

function scrollImg(fn) {
    let timer = null;
    let context = this;
    let args = arguments;
    return function () {
        clearTimeout(timer);
        timer = setTimeout(() => {
            fn.apply(context, args);
        }, 500);
    }
}