<template>
  <div class="all">
    <div class="box">
      <div class="login">
        <div class="head"/>
        <div class="loginTop">
          <div class="userCode">
            <el-input type="name" class="inputStyle" placeholder="账号" v-model="userName" maxlength="10"/>
          </div>
        </div>
        <div class="loginButton">
          <div class="userCode">
            <el-input type="name" class="inputStyle" placeholder="密码" v-model="password" maxlength="20"
                      show-password/>
          </div>
        </div>
        <div class="LogRegister">
          <div class="LogRegisterLeft">
            <el-button type="success" class="buttonStyle" @click="login"><strong class="font">登录</strong></el-button>
          </div>
          <div class="LogRegisterRight">
            <el-button type="success" class="buttonStyle" @click="toRegister"><strong class="font">注册</strong>
            </el-button>
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
  name: "login",
  data() {
    return {
      searchParam: {
        userName: '',
        password: ''
      },
      userName: '',
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
      areas: []
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
    toRegister() {
      this.$router.push({
        path: '/register'
      });
    },
    login() {
      if (this.userName == '' || this.userName == null) {
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
      this.searchParam.userName = this.userName;
      this.searchParam.password = this.password;
      this.req.post("/api/controller/login", this.searchParam).then(res => {
        if (res.code == 200) {
          this.$message({
            showClose: true,
            message: '登录成功',
            type: 'success',
          });
          this.setCar('TOKEN', res.token);
          this.$router.push({
            path: '/worksManagement'
          });
          return;
        }
        this.$message({
          showClose: true,
          message: '账号或密码错误，请重新输入',
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
  height: 550px;
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
  height: 70px;
  width: 100%;
  margin-top: 50px;
  padding-top: 20px;
  float: left;
}

.loginButton {
  height: 70px;
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

.name {
  height: 300px;
  width: 300px;
  opacity: 0%;
  margin-left: 575px;
  position: absolute;
  transition: 1s;
  z-index: 100;
}

.LogRegisterLeft {
  width: 100px;
  margin-left: 140px;
  float: left;
}

.LogRegisterRight {
  width: 100px;
  margin-left: 50px;
  float: left;
}

</style>