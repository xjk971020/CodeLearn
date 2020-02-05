<template>
    <div class="slide">
        <!--    广告图片区域-->
        <div class="slideshow">
            <ul>
                <li v-for="(item, index) in imgArray" v-show="index === mark" :key="index">
                    <img :src="item" alt="">
                </li>
            </ul>
        </div>
        <!--指示器-->
        <div class="bar">
            <span @mouseover="changeImg(index)" :class="index===mark?'active':'unactive'" v-for="(item,index) in imgArray" :key="item">

            </span>
        </div>
    </div>
</template>

<script>

    export default {
        name: 'app',
        created() {
          this.autoPlay()
        },
        data() {
            return {
                // 当前指示器的位置
                mark: 0,
                imgArray: [
                    require('@/assets/2.jpg'),
                    require('@/assets/3.jpg'),
                    require('@/assets/4.jpg'),
                    require('@/assets/5.jpg'),
                    require('@/assets/6.jpg')
                ]
            }
        },
        methods: {
            //播放图片
            autoPlay() {
                setInterval(() => {
                    if (this.mark < this.imgArray.length - 1) {
                        this.mark++
                    } else {
                        this.mark = 0
                    }
                },5000)
            },
            changeImg(index) {
                this.mark = index
            }
        }
    }
</script>

<style>
    * {
        margin: 0;
        padding: 0;
        list-style: none;
    }

    .slide {
        width: 100%;
    }

    .slideshow {
        width: 60%;
        margin: 0 auto;
    }

    img {
        width: 100%;
    }

    .bar {
        position: absolute;
        top: 700px;
        width: 100%;
        text-align: center;
    }

    .unactive {
        border-radius: 50%;
        width: 10px;
        height: 10px;
        border: 2px solid rgba(255, 255, 255, 0.6);
        margin-right: 10px;
        display: inline-block;
        cursor: pointer;
    }

    .active {
        border-radius: 50%;
        width: 10px;
        height: 10px;
        margin-right: 10px;
        display: inline-block;
        border: 2px solid rgba(255, 255, 255, 0.6);
        box-shadow: 0px 0px 3px 2px #eeeeee;
        background-color: rgba(255, 255, 255, 0.8);
        cursor: pointer;
    }
</style>
