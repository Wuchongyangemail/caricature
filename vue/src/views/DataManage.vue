<template>
    <div class="all">

    </div>
</template>

<script>
    export default {
        name: "DataManage",
        data() {
            return {
            };
        },
        methods: {
            handleChange(file, fileList) {
                this.fileList = fileList.slice(-3);
            }
        }
    }
</script>

<style scoped>
    .all {
        height: 100%;
        min-height: 1550px;
        width: 100%;
        background: url('~@/assets/images/mainBackground.svg');
      border: 1px solid red;
    }
    .up {
        height: 50%;
        width: 100%;
        display: flex;
    }
    .devide {
        height: 275px;
        width: 33%;
    }
    .dataUpload {
        height: 70%;
        width: 100%;
        margin: 45px 0 0 60px;
    }
    .uploadStyle {
        font-size: 100px;
        opacity: 50%;
    }
</style>