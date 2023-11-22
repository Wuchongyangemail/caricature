<template>
  <div class="all">
    <div class="box">
      <div class="login">
        <div class="head"/>
        <div class="loginTop">
          <div class="userCode">
            <el-input type="name" class="inputStyle" placeholder="账号" v-model="userCode" maxlength="10"/>
          </div>
        </div>
        <div class="loginButton">
          <div class="userCode">
            <el-input type="name" class="inputStyle" placeholder="密码" v-model="password" maxlength="20"
                      show-password/>
          </div>
        </div>
        <div class="sexButton">
          <div class="userCode">
            <el-radio v-model="sex" label="0">男</el-radio>
            <el-radio v-model="sex" label="1">女</el-radio>
          </div>
        </div>
        <div class="telButton">
          <div class="userCode">
            <el-input type="name" class="inputStyle" placeholder="电话" v-model="tel" maxlength="11"/>
          </div>
        </div>
        <div class="loginEmail">
          <div class="userCode">
            <el-input type="name" class="inputStyle" placeholder="邮箱" v-model="searchParam.email" maxlength="20"/>
          </div>
        </div>
        <!--                <div class="verification">-->
        <!--                    <div class="verificationLeft">-->
        <!--                        <el-input type="name" class="inputStyle" placeholder="验证码" v-model="searchParam.code" maxlength="10"/>-->
        <!--                    </div>-->
        <!--                    <div class="verificationRight">-->
        <!--                        <el-button type="success" class="buttonStyle" @click="gainVerification"><strong class="font">获取验证码</strong></el-button>-->
        <!--                    </div>-->
        <!--                </div>-->
        <div class="LogRegister">
          <div class="LogRegisterCenter">
            <el-button type="success" class="buttonStyle" @click="login"><strong class="font">注册</strong></el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {shopMixin} from '@/store/mixin';
import request from '../utils/request';

export default {
  mixins: [shopMixin],
  name: "register",
  data() {
    return {
      searchParam: {
        userCode: '',
        password: '',
        userName: '',
        email: '',
        tel: '',
        sex: '',
        code: ''
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
      userName: ''
    }
  },
  mounted() {
    this.loadAllArea();
    this.removeSession();
  },
  methods: {
    changeName(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.updateMyName(this.nameValidateForm.name);
          this.tempName = 'name';
          setTimeout(() => {
            this.nameNoneDisplay();
          }, 1500);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    nameNoneDisplay() {
      this.tempName = 'name displayNone';
      this.tempDepartment = 'name nonHidden';
    },
    changeDepartment(data) {
      this.updateMyDepartment(data);
      this.tempDepartment = 'name';
      setTimeout(() => {
        this.departmentNoneDisplay();
      }, 1500);
    },
    departmentNoneDisplay() {
      this.tempDepartment = 'name displayNone';
      this.tempScore = 'name nonHidden';
    },
    changeScore(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.updateMyScore(this.scoreValidateForm.score);
          this.tempScore = 'name';
          setTimeout(() => {
            this.scoreNoneDisplay();
          }, 1500);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    scoreNoneDisplay() {
      this.tempScore = 'name displayNone';
      this.tempArea = 'name nonHidden';
    },
    changeArea(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.updateAreaSource(this.areaValidateForm.area);
          this.tempArea = 'name';
          setTimeout(() => {
            this.areaNoneDisplay();
          }, 1500);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    areaNoneDisplay() {
      this.tempArea = 'name displayNone';
      this.tempRank = 'name nonHidden';
    },
    changeRank(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.updateMyRanking(this.rankValidateForm.rank);
          this.tempRank = 'name',
              setTimeout(() => {
                this.rankNoneDisplay();
              }, 1500);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    rankNoneDisplay() {
      this.tempRank = 'name displayNone';
      console.log(this.getMyScore());
      console.log(this.getMyRanking());
      console.log(this.getMyName());
      console.log(this.getMyDepartment());
      console.log(this.getAreaSource());
      this.$router.push({
        path: '/colleges'
      });
    },
    loadAllArea() {
      this.req.get("/api/controller/all_area", {}).then(res => {
        this.areas = res;
        console.log(this.areas);
      })
    },
    gainVerification() {
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
      // if (this.searchParam.email == '' || this.searchParam.email == null) {
      //     this.$message({
      //         showClose: true,
      //         message: '邮箱不能为空',
      //         type: 'warning'
      //     });
      //     return;
      // }
      this.searchParam.userCode = this.userCode;
      this.searchParam.password = this.password;
      // this.req.post("/api/controller/gainVerification", this.searchParam).then(res => {
      //     if (res.resultStatus == 1) {
      //         this.$message({
      //             showClose: true,
      //             message: res.resultMessage,
      //             type: 'success'
      //         });
      //         return;
      //     }
      //     this.$message({
      //         showClose: true,
      //         message: res.resultMessage,
      //         type: 'warning'
      //     });
      // })
    },
    login() {
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
      this.searchParam.userCode = this.userCode;
      this.searchParam.password = this.password;
      this.req.post("/api/controller/register", this.searchParam).then(res => {
        if (res.code == 200) {
          this.$message({
            showClose: true,
            message: res.msg,
            type: 'success'
          });
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
  background-color: rgba(255, 255, 255, 0.247);
}

.all {
  height: 700px;
  width: 100%;
  background: url('~@/assets/images/mainBackground.svg');
}

.login {
  height: 450px;
  width: 500px;
  margin: auto;
}

.box {
  height: 100%;
  width: 100%;
  padding-top: 50px;
}

.head {
  height: 120px;
  width: 120px;
  margin: auto;
  border-radius: 50%;
  background: url('~@/assets/images/mark.svg') no-repeat 100% 100%;
  background-color: #626aef;
  background-position: center;
}

.loginTop {
  height: 50px;
  width: 100%;
  margin-top: 0px;
  padding-top: 20px;
  float: left;
}

.loginButton {
  height: 50px;
  width: 100%;
  padding-top: 20px;
  float: left;
  //border: 1px solid red;
}

.sexButton {
  height: 50px;
  width: 100%;
  padding-top: 20px;
  float: left;
  //border: 1px solid red;
}

.telButton {
  height: 50px;
  width: 100%;
  padding-top: 20px;
  float: left;
  //border: 1px solid red;
}

.loginEmail {
  height: 50px;
  width: 100%;
  padding-top: 20px;
  float: left;
}

.LogRegister {
  height: 50px;
  width: 100%;
  padding-top: 20px;
  float: left;
}

.userCode {
  height: 60px;
  width: 300px;
  margin: auto;
}

.LogRegisterCenter {
  width: 100px;
  margin: auto;
}
</style>