<template>
  <div class="all">
    <div class="head">
      <div class="picture">
        <el-upload
            action="#"
            list-type="picture-card"
            :auto-upload="false"
            multiple="false"
            :file-list="mainFileList"
            limit="1"
            :on-success="uploadSuccess">
          <i slot="default" class="el-icon-plus">主图</i>
        </el-upload>
      </div>
      <div class="line"></div>
      <div class="mainName">
        <span class="font messageFont">
          <el-input v-model="carCommentName" placeholder="名称"></el-input>
        </span>
      </div>
    </div>
    <div class="foot">
      <el-image class="el_image" v-for="url in urls" :key="url" :src="url" lazy/>
    </div>
    <div class="center">
      <div class="lastFoot">
        <el-upload
            v-model:file-list="fileList"
            class="upload-demo"
            action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
            :on-change="handleChange"
        >
          <el-button type="primary">Click to upload</el-button>
          <template #tip>
            <div class="el-upload__tip">
              jpg/png files with a size less than 500kb
            </div>
          </template>
        </el-upload>
      </div>
      <div class="center_right">
        <div class="center_right_top">
          <el-button class="buttonStyle" type="success" round>保存</el-button>
        </div>
        <div class="center_right_button">
          <el-button class="buttonStyle" type="danger" round @click="back">返回</el-button>
        </div>
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
    var temp = window.sessionStorage.getItem('CARCOMMENTURL');
    if (temp != null && temp != '' && temp != 'undefined') {
      var tempMain = {
        url: temp
      };
      this.mainFileList.push(tempMain);
    }
    this.carCommentName = window.sessionStorage.getItem('CARCOMMENTNAME');
    if (this.carCommentName == null || this.carCommentCode == '') {
      this.carCommentName = '';
    }
    this.carCommentCode = window.sessionStorage.getItem('CARCOMMENTCODE');
    if (this.carCommentCode == null || this.carCommentCode == '') {
      this.carCommentCode = '';
    }
    this.searchList();
  },
  data() {
    return {
      textarea: '',
      dialogImageUrl: '',
      dialogVisible: true,
      disabled: true,
      isView: 'true',
      urls: [],
      req: request,
      searchParam: {
        carCommentCode: ''
      },
      carCommentCode: '',
      carCommentName: '',
      carCommentUrl: '',
      mainFileList: [],
      fileList: []
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
    searchList() {
      this.searchParam.carCommentCode = this.carCommentCode;
      this.req.post("/api/caricature/searchPicDetail", this.searchParam).then(res => {
        if (res.code == '200') {
          this.urls = res.results;
          for (var i = 0; i < this.urls.length; i++) {
            var temp = {
              name: i,
              url: this.urls[i]
            }
            this.fileList.push(temp);
          }
        } else {
          this.$message({
            showClose: true,
            message: res.msg,
            type: 'error'
          });
        }
      })
    },
    back() {
      this.$router.push({
        path: '/editPage'
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
  height: 500px;
  width: 260px;
  margin-bottom: 10px;
//border: 1px solid black; margin-left: 20px;
  float: left;
  border-radius: 50px;
  background-color: rgba(255, 255, 255, 0.6);
}

.center {
  height: 500px;
  width: 260px;
  margin-bottom: 10px;
//border: 1px solid black; margin-right: 20px;
  float: right;
  border-radius: 50px;
  background-color: rgba(255, 255, 255);
}

.foot {
  height: 500px;
  width: 50%;
//border: 1px solid black; margin-left: 55px;
  float: left;
  border-radius: 50px;
  overflow-y: auto;
  background-color: rgba(255, 255, 255);
}

.el_image {
  display: block;
  width: 50%;
  margin: auto;
  min-height: 200px;
}

.el_image:last-child {
  margin-bottom: 0;
}

.picture {
  width: 150px;
  height: 350px;
  margin-top: 20px;
  margin-left: 58px;
//border: 1px solid rebeccapurple; border-radius: 10%;
}

.mainName {
  width: 200px;
  height: 60px;
//border: 1px solid rosybrown; margin-left: 28px;
  margin-top: 10px;
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

.line {
  border: 1px solid #FF9933;
  width: 80%;
  margin-top: 10px;
  margin: auto;
//margin-left: 5px; border-radius: 90%;
}

.lastFoot {
  height: 400px;
  margin: auto;
  width: 60%;
//border: 1px solid royalblue; background-color: rgba(255, 255, 255);
  border-radius: 10px;
  margin-left: 55px;
  margin-top: 20px;
  float: left;
}

.center_right {
  height: 80px;
  width: 100px;
//border: 1px solid peru; float: right;
  margin-right: 60px;
}

.center_right_top {
  height: 40px;
  width: 100px;
//border: 1px solid peru; float: right;
}

.center_right_button {
  height: 50px;
  width: 100px;
//border: 1px solid peru; float: right;
}

.buttonStyle {
  padding-top: 8px;
}
</style>