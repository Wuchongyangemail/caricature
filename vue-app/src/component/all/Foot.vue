<template>
    <div class="foot">
        <div class="doll">
            <span class="dollIcon iconfont icon-xueshimao5"></span>
            <span class="dollIcon iconfont icon-xueshimao"></span>
            <span class="dollIcon iconfont icon-xueshimao-shi"></span>
            <span class="dollIcon iconfont icon-maozi"></span>
            <span class="dollIcon iconfont icon-xueshimao1"></span>
            <span class="dollIcon iconfont icon-xueshimao2"></span>
            <span class="dollIcon iconfont icon-xueshimaoxuexibiye"></span>
            <span class="dollIcon iconfont icon-xueshimao3"></span>
            <span class="dollIcon iconfont icon-xueshimao4"></span>
            <span class="dollIcon iconfont icon-xueshi"></span>
            <span class="dollIcon iconfont icon-001_xueshimao"></span>
            <span class="dollIcon iconfont icon-xueshimao6"></span>
            <span class="dollIcon iconfont icon-XX_007"></span>
            <span class="dollIcon iconfont icon-icon-test"></span>
            <span class="dollIcon iconfont icon-xueshimao9"></span>
            <span class="dollIcon iconfont icon-xueshimao10"></span>
            <span class="dollIcon iconfont icon-xueshimao11"></span>
            <span class="dollIcon iconfont icon-xueshimao12"></span>
            <span class="dollIcon iconfont icon-zu"></span>
        </div>
        <div class="incentive"></div>
        <div class="message">
            <div class="dataManage">
<!--                <el-link class="msssageLinkButton" type="primary" :underline="false" @click="goDataManage">数据管理</el-link>-->
            </div>
        </div>
        <div class="aboutMe">
            <div class="cell_dashed"/>
            <div class="sodown">
                <td width="100" height="27" align="center"> &copy; 2020 北极光 版权所有ICP证：<a href="http://www.beian.miit.gov.cn">
                    <strong>豫ICP备20020118号-1</strong></a></td>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Foot",
        methods: {
            goDataManage() {
                this.$router.push({
                    path: '/login'
                });
            }
        }
    }
</script>

<style scoped>
    .foot {
        width: 100%;
        background-color: #ffffff;
    }
    .doll {
        height: 50px;
        width: 100%;
        background-color: #D8E7FE;
        display: flex;
    }
    .dollIcon {
        font-size: 30px;
        color: #626aef;
        margin: 10px 0 0 34px;
        opacity: 45%;
    }
    .incentive {
        height: 50px;
        width: 100%;
        background-color: #626aef;
    }
    .message {
        height: 20px;
        width: 100%;
    }
    .aboutMe {
        height: 25px;
        width: 100%;
    }
    .cell_dashed{
        border: 1px solid #626aef;
        border-radius: 90%;
        width: 95%;
        margin: auto;
    }
    .sodown {
        height: 10px;
        width: 100%;
        margin: 0px auto;
        padding-top: 5px;
        text-align: center;
        font-size: 13px;
    }
    .dataManage {
        height: 20px;
        width: 500px;
    }
    .msssageLinkButton {
        margin: 20px 0 0 20px;
    }
</style>