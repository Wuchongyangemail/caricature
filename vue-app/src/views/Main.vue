<template>
    <div class="all" id="all">
        <div class="upup"></div>
        <div class="updown" style="color:#036;  font-weight:500; letter-spacing:2px;"><strong>北极光漫画</strong></div>
        <div class="centerup"></div>
        <div class="centerdown"
             style="font-family: 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; font-size:20px; text-align:center; color:#034">
        </div>
        <div class="down">
            <el-button  color="#626aef" style=" text-align:center; border:none; transition:1s; width:280px; height:50px; border-radius: 200px 200px 200px 200px; outline:0 !important; font-family:'Comic Sans MS', cursive; font-size:20px; color:#FFF;" @click="collectionInformation">Open!</el-button>
        </div>
    </div>
</template>

<script>
    import { shopMixin } from '@/store/mixin';
    export default {
        mixins: [shopMixin],
        name: "Main.vue",
        components:{
        },
        mounted() {
            this.removeSession();
        },
        methods: {
            collectionInformation() {
                this.$router.push({
                    path: '/information'
                });
            }
        }
    }
</script>

<style scoped>
    .all {
        height: 100%;
        width: 100%;
        margin: 0px auto;
        background: url('~@/assets/images/mainBackground.svg');
    }

    .upup {
        height: 100px;
        width: 400px;
        margin: 0px auto;
        background: url('~@/assets/images/log.svg');
    }

    .updown {
        height: 50px;
        width: 570px;
        margin: 0px auto;
        font-size: 45px;
        font-family: "Lucida Sans Unicode", "Lucida Grande", sans-serif;
        text-align: center;
    }

    .centerup {
        height: 300px;
        width: 500px;
        float: inherit;
        margin: 0px auto;
        background: url('~@/assets/images/log.gif');
    }

    .centerdown {
        height: 100px;
        width: 750px;
        margin: 0px auto;
    }

    .down {
        height: 100px;
        width: 300px;
        margin: 0px auto;
        padding-top: 30px;
    }

    .font {
        font-family:'Comic Sans MS', cursive;
    }

    /*.sodown {*/
    /*    height: 40px;*/
    /*    width: 100%;*/
    /*    margin: 0px auto;*/
    /*    padding-top: 20px;*/
    /*    text-align: center;*/
    /*    font-size: 13px;*/
    /*}*/
</style>