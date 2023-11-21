<template>
  <div class="all">
    <div class="head">
      <div class="picture">
        <el-upload
            action="#"
            list-type="picture-card"
            :auto-upload="false"
            v-show="isView"
            :file-list="fileList"
            max-count="1"
            style="width: 320px; height:20px;"
            :on-success="uploadSuccess">
          <i slot="default" class="el-icon-plus">主图</i>
        </el-upload>
      </div>
      <div class="line"></div>
      <div class="mainName">
        <span class="font messageFont">
          <el-input v-model="name" placeholder="名称" :value="name"></el-input>
        </span>
      </div>
      <div class="memo">
        <el-input
            type="textarea"
            :rows="5"
            placeholder="简介"
            v-model="mo"
            class="font memoFont"
            resize="none"
            :value="mo">
        </el-input>
      </div>
    </div>
    <div class="center_right">
      <div class="center_right_button">
        <el-button class="buttonStyle" type="danger" round @click="back">返回</el-button>
      </div>
      <div class="center_right_top">
        <el-button class="buttonStyle" type="success" round>保存</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import {shopMixin} from "@/store/mixin";
import request from "@/utils/request";

export default {
  name: "ChapterPage",
  mixins: [shopMixin],
  mounted() {
    var s = window.sessionStorage.getItem('CARURL');
    if (s != null) {
      var tempMain = {
        url:(s == null || !s.includes('http')) ? ("https://www.redbz.com" + s) : s
      };
      this.fileList.push(tempMain);
    }
    this.mo = window.sessionStorage.getItem('CARMO');
    this.name = window.sessionStorage.getItem('CARNAME');
    this.code = window.sessionStorage.getItem('CARID');
  },
  data() {
    return {
      input: '',
      textarea: '',
      dialogImageUrl: '',
      dialogVisible: true,
      disabled: true,
      isView: 'true',
      fileList:[],
      mo:'',
      name:'',
      code:''
    }
  },
  methods: {
    editPage() {
      this.$router.push({
        path: '/chapterPage'
      });
    },
    uploadSuccess() {
      console.log(this.isView);
      this.isView = false;
      console.log(this.isView);
    },
    back() {
      this.$router.push({
        path: '/worksManagement'
      });
    }
  }
}
</script>

<style scoped>
/deep/ .el-input__inner {
  height: 50px;
  background-color: rgba(255, 255, 255, 0.247);
}

.all {
  height: 100%;
  min-height: 550px;
  width: 100%;
  background: url('~@/assets/images/mainBackground.svg');
  padding-top: 30px;
  //border: 1px solid red;
}

.head {
  height: 200px;
  width: 70%;
  margin-bottom: 10px;
  //border: 1px solid black;
  margin-left: 15%;
  border-radius: 50px;
  background-color: rgba(255, 255, 255, 0.6);
}

.picture {
  width: 320px;
  height: 150px;
  margin-top: 20px;
  margin-left: 20px;
  //border: 1px solid rebeccapurple;
  float: left;
  border-radius: 10%;
}

.mainName {
  width: 400px;
  height: 60px;
  //border: 1px solid rosybrown;
  margin-left: 70px;
  margin-top: 10px;
  float: left;
}

.memo {
  width: 550px;
  height: 100px;
  //border: 1px solid green;
  float: left;
  margin-top: 10px;
  margin-left: 20px;
}

.font {
  font-family: 'Comic Sans MS', cursive;
  color: white;
}

.messageFont {
  font-size: 30px;
  color: #FF9933;
  margin: auto;
  text-align: left;
  display: block;
  margin-top: 5px;
}

.memoFont {
  font-size: 10px;
  color: #FF9933;
  margin: auto;
  text-align: left;
  display: block;
  margin-top: 5px;
}

.line {
  border: 1px solid #FF9933;
  height: 80%;
  margin-top: 2%;
  float: left;
  margin-left: 20px;
  border-radius: 90%;
}

.center_right{
  height: 120px;
  width: 220px;
  //border: 1px solid peru;
  margin-top: 200px;
  margin-left: 43%;
}

.center_right_top{
  height: 50px;
  width: 100px;
  //border: 1px solid peru;
  float: left;
}

.center_right_button{
  height: 50px;
  width: 100px;
  //border: 1px solid peru;
  float: left;
}

.buttonStyle {
  padding-top: 8px;
}
</style>