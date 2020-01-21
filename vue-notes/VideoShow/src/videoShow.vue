    <template>
    <div class="test_two_box">
        <video
        id="myVideo"
        class="video-js"
        >
        <source
            src="https://vdept.bdstatic.com/3763503471585a7365564948516c7972/536875485a4b3769/b255949e72dc1e435a17163fb5feee5d0efe09fe201e11db1a5538c5bdf7bd22e607bf3680ad951bbf8583c82fa04f1f.mp4?auth_key=1579370067-0-0-7dc0f5f911cf1b4d710b1813378c714b"
            type="video/mp4"
        >
        </video>
    </div>
    </template>

    <script>
    /* eslint-disable */
    export default {
    name: "TestTwo",
    data() {
        return {};
    },
    mounted() { 
        this.initVideo();
    },
    methods: {
        initVideo() {
        //初始化视频方法
        let myPlayer = this.$video(myVideo, {
            //确定播放器是否具有用户可以与之交互的控件。没有控件，启动视频播放的唯一方法是使用autoplay属性或通过Player API。
            controls: true,
            //自动播放属性,muted:静音播放
            autoplay: "autoplay",
            //建议浏览器是否应在<video>加载元素后立即开始下载视频数据。
            preload: "auto",
            //设置视频播放器的显示宽度（以像素为单位）
            width: "800px",
            //设置视频播放器的显示高度（以像素为单位）
            height: "400px"
        });
        }
    }
    };
    </script>

    <style scoped>
    </style>