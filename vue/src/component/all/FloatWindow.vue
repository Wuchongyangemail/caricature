
<template>
    <div class="outBox">
        <div
                :class="['suspendBox', { move: !canDrag }]"
                :style="{top:top+'px',left:left+'px'}"
                @mousedown="start"
                @mousemove="move"
                @mouseup="stop"
                @mouseenter="enter"
                @mouseleave="leave"
        >
            <slot></slot>
        </div>
    </div>
</template>

<script>
    export default {
        props:{
            ableMove: {type: Boolean, default: true},  //是否可移动
            ableHide: {type: Boolean, default: true},  //是否靠边隐藏
            hoverShow: {type: Boolean, default: true},  //图标隐藏时 鼠标悬浮即展示，否则点击展示
            distance: {type: Number, default: 5},  //距离边界多少px就隐藏
            defaultX: {type: Number, default: 1},  //初始横坐标百分比
            defaultY: {type: Number, default: 0.9}  //初始纵坐标百分比
        },
        data() {
            return {
                canDrag: false,
                isClick: true,
                x0: 0,
                y0: 0,
                top: null,
                left: null
            };
        },
        mounted() {
            let img = document.querySelector(".suspendBox");
            let box = document.querySelector(".outBox");
            this.left = (box.offsetWidth - img.offsetWidth) * this.defaultX;
            this.top = (box.offsetHeight - img.offsetHeight) * this.defaultY;
            setTimeout(()=>{
                this.hide()
            }, 1000)
        },
        methods: {
            start(e) {
                console.log("start");
                e.preventDefault();   //阻止默认的拖拽
                if (e.button == 0) {
                    this.canDrag = this.ableMove;
                    this.isClick = true;
                    this.x0 = e.clientX;
                    this.y0 = e.clientY;
                }
            },
            move(e) {
                console.log("move");
                if (this.canDrag == true) {
                    this.isClick = false;
                    let x = e.clientX - this.x0;
                    let y = e.clientY - this.y0;
                    let img = document.querySelector(".suspendBox");
                    let box = document.querySelector(".outBox");
                    if (
                        img.offsetLeft + x < 0 ||
                        img.offsetTop + y < 0 ||
                        img.offsetLeft + x > box.offsetWidth - img.offsetWidth ||
                        img.offsetTop + y > box.offsetHeight - img.offsetHeight
                    ) {
                        return false;
                    }
                    this.left = img.offsetLeft + x;
                    this.top = img.offsetTop + y;
                    this.x0 = e.clientX;
                    this.y0 = e.clientY;
                }
            },
            stop() {
                this.canDrag = false;
                console.log("stop");
                this.show();
            },
            enter() {
                console.log("enter");
                if (this.hoverShow) {
                    this.show();
                }
            },
            leave() {
                console.log("leave");
                this.canDrag = false;
                this.hide();
            },
            show() {
                let img = document.querySelector(".suspendBox");
                let box = document.querySelector(".outBox");
                if (img.offsetLeft < 0) {
                    this.left = 0;
                } else if (img.offsetLeft > box.offsetWidth - img.offsetWidth) {
                    this.left = box.offsetWidth - img.offsetWidth
                } else if(this.isClick){
                    this.clickEvent()
                }
            },
            hide() {
                if (this.ableHide) {
                    console.log("hide");
                    let img = document.querySelector(".suspendBox");
                    let box = document.querySelector(".outBox");
                    if (!this.canDrag && img.offsetLeft < this.distance) {
                        this.left = -img.offsetWidth * 2 / 3;
                    } else if (!this.canDrag && img.offsetLeft > box.offsetWidth - img.offsetWidth - this.distance) {
                        this.left = box.offsetWidth - img.offsetWidth / 3
                    }
                }
            },
            clickEvent() {      //悬浮窗的点击事件
                console.log("悬浮窗的点击事件");
            },
        },
    };
</script>
<style scoped>
    .outBox {
        width: 100%;
        height: 100%;
        position: absolute;
        overflow: hidden;
        z-index:100;
        pointer-events: none;
    }
    .suspendBox {
        position: absolute;
        pointer-events: auto;
    }
    .move {
        transition: 0.3s linear;
        -moz-transition: 0.3s linear; /* Firefox 4 */
        -webkit-transition: 0.3s linear; /* Safari 和 Chrome */
        -o-transition: 0.3s linear; /* Opera */
    }
</style>
