<template>
  <div class="all">
    <div class="up">
      <div class="input">
        <el-input type="name" class="inputStyle" placeholder="账号" v-model="userCode" maxlength="10" :disabled="isDisabled"/>
      </div>
      <div class="input">
        <el-input type="name" class="inputStyle" placeholder="密码" v-model="password" maxlength="10" :disabled="isDisabled" show-password/>
      </div>
      <div class="input">
        <el-radio v-model="sex" label="0"  :disabled="isDisabled">男</el-radio>
        <el-radio v-model="sex" label="1"  :disabled="isDisabled">女</el-radio>
      </div>
      <div class="input">
        <el-input type="name" class="inputStyle" placeholder="电话" v-model="tel" maxlength="11" :disabled="isDisabled"/>
      </div>
      <div class="input">
        <el-input type="name" class="inputStyle" placeholder="邮箱" v-model="email" maxlength="20" :disabled="isDisabled"/>
      </div>
      <div class="input"></div>
      <div class="input"></div>
      <div class="input"></div>
    </div>
    <div class="down">
      <div class="LogRegisterLeft">
        <el-button :type="editButton" class="buttonStyle" @click="edit" :disabled=!isDisabled><strong class="font">编辑</strong></el-button>
      </div>
      <div class="LogRegisterRight">
        <el-button :type="keepButton" class="buttonStyle" @click="editMyMessage" :disabled=isDisabled><strong class="font">保存</strong>
        </el-button>
      </div>
    </div>
  </div>
</template>

<script>
import {shopMixin} from "@/store/mixin";
import request from "@/utils/request";

export default {
  name: "AuthorCenter",
  mixins: [shopMixin],
  mounted() {
    this.searchAuthData();
  },
  data() {
    return {
      searchParam: {
        userCode: '',
        password: '',
        userName: '',
        email: '',
        tel: '',
        sex: '',
        code: '',
        id: '',
        character: ''
      },
      userCode: '',
      password: '',
      nameValidateForm: {
        name: ''
      },
      areaValidateForm: {
        area: '',
      },
      rankValidateForm: {
        rank: null,
      },
      scoreValidateForm: {
        score: null,
      },
      departmentValidateForm: {
        department: '',
      },
      req: request,
      tempName: 'name nonHidden',
      tempScore: 'name',
      tempArea: 'name',
      tempRank: 'name',
      tempDepartment: 'name',
      areas: [],
      sex: '0',
      tel: '',
      userName: '',
      email: '',
      isDisabled: 'false',
      character: '',
      id: '',
      editButton: 'danger',
      keepButton: 'info'
    }
  },
  methods: {
    handleChange(file, fileList) {
      this.fileList = fileList.slice(-3);
    },
    edit() {
      this.isDisabled = !this.isDisabled;
      this.keepButton = 'danger';
      this.editButton = 'info';
    },
    searchAuthData(){
      this.req.post("/api/controller/searchAuthData", this.searchParam).then(res => {
        if (res.code == '200') {
          var temp = res.results;
          this.id = temp.id;
          this.email = temp.email;
          this.password = temp.password;
          this.userCode = temp.userName;
          this.tel = temp.tel;
          this.sex = temp.sex.toString();
        } else {
          this.$message({
            showClose: true,
            message: res.msg,
            type: 'error'
          });
        }
      })
    },
    editMyMessage() {
      this.searchParam.userCode = this.userCode;
      this.searchParam.password = this.password;
      this.searchParam.email = this.email;
      this.searchParam.tel = this.tel;
      this.searchParam.sex = this.sex;
      this.searchParam.character = this.character;
      this.searchParam.id = this.id;
      if (this.userCode == '' || this.userCode == null) {
        this.$message({
          showClose: true,
          message: '账号不能为空',
          type: 'warning'
        });
        return;
      }
      if (this.password == '' || this.password == null) {
        this.$message({
          showClose: true,
          message: '密码不能为空',
          type: 'warning'
        });
        return;
      }
      if (this.searchParam.email == '' || this.searchParam.email == null) {
        this.$message({
          showClose: true,
          message: '邮箱不能为空',
          type: 'warning'
        });
        return;
      }
      if (this.searchParam.email == '' || this.searchParam.email == null) {
        this.$message({
          showClose: true,
          message: '邮箱不能为空',
          type: 'warning'
        });
        return;
      }
      if (this.searchParam.email == '' || this.searchParam.email == null) {
        this.$message({
          showClose: true,
          message: '邮箱不能为空',
          type: 'warning'
        });
        return;
      }
      // if (this.searchParam.code == '' || this.searchParam.code == null) {
      //     this.$message({
      //         showClose: true,
      //         message: '验证码不能为空',
      //         type: 'warning'
      //     });
      //     return;
      // }
      this.req.post("/api/controller/editUser", this.searchParam).then(res => {
        if (res.code == 200) {
          this.$message({
            showClose: true,
            message: res.msg,
            type: 'success'
          });
          window.sessionStorage.removeItem("TOKEN");
          this.$router.push({
            path: '/login'
          });
          return;
        }
        this.$message({
          showClose: true,
          message: res.msg,
          type: 'error'
        });
      })
    }
  }
}
</script>

<style scoped>
/deep/ .el-input__inner {
  height: 47px;
  background-color: rgba(255, 255, 255);
}
.all {
  height: 100%;
  width: 100%;
  background: url('~@/assets/images/mainBackground.svg');
  //border: 1px solid red;
  padding-top:35px;
}
.up {
  height: 400px;
  width: 50%;
  display: block;
  //border: 1px solid black;
  margin: auto;
  border-radius: 50px;
  background-color: rgba(255, 255, 255, 0.4);
}
.down {
  height: 100px;
  width: 20%;
  //border: 1px solid blue;
  margin-left: 40%;
  margin-top: 20px;
}
.input {
  height: 50px;
  width: 200px;
  float: left;
  //border: 1px solid green;
  margin-left: 100px;
  margin-top: 40px;
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
.LogRegisterLeft {
  width: 100px;
  margin-left: 20%;
  margin-top: 20px;
  float: left;
}
.LogRegisterRight {
  width: 100px;
  margin-left: 10px;
  margin-top: 20px;
  float: left;
}
</style>