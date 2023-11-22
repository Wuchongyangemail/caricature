<template>
  <div :class="head">
    <div :class="dateTime">
      <div class="date">
        <span class="font" v-text="getDate()" style="font-size: 20px; color: white; margin: 0 0 0 0;"></span>
      </div>
      <div class="time">
        <span class="font" v-text="newTime" style="font-size: 20px; color: white; margin: 80px"></span>
      </div>
    </div>
    <div :class="selectBatchClass">
<!--      <el-dropdown>-->
<!--                <span class="el-dropdown-link">-->
<!--                    <span class="iconfont icon-guijipicichaxun"-->
<!--                          style="color: white; font-size: 20px; margin-left: 50px"></span>-->
<!--                    <span class="font" style="font-size: 20px; margin-left: 10px;" v-if="batch === null">录取批次</span>-->
<!--                    <span class="font" style="font-size: 20px; margin-left: 10px;" v-if="batch !== null"-->
<!--                          v-text="batch"></span>-->
<!--                </span>-->
<!--        <template #dropdown>-->
<!--          <el-dropdown-menu>-->
<!--            <el-dropdown-item @click="batchClick(1)">我的信息</el-dropdown-item>-->
<!--            <el-dropdown-item divided @click="batchClick(2)">本科二批</el-dropdown-item>-->
<!--          </el-dropdown-menu>-->
<!--        </template>-->
<!--      </el-dropdown>-->
    </div>
    <div :class="mark" @click="goMain">
      <div class="markImage"></div>
    </div>
    <div :class="selectSchoolInArea">
<!--      <span class="iconfont icon-ditudingwei" style="color: white; font-size: 20px; margin-left: 40px"></span>-->
<!--      <span class="font" style="font-size: 20px; margin-left: 10px;" @click="goDataManage">数据管理</span>-->
    </div>
    <div :class="myMessage">
      <span class="iconfont icon-wowode" style="color: white; font-size: 20px; margin-left: 50px"></span>
      <span class="font" style="font-size: 20px; margin-left: 10px;" @click="searchMyMessage">菜单</span>
      <el-drawer v-model="drawer" title="菜单" :with-header="false">
        <div class="headPortrait">
          <el-image :src="require('@/assets/images/head.jpeg')" class="headPortrait"/>
        </div>
        <div class="my">
          <span class="font messageFont" @click="goAuthorCenter">作者中心</span>
        </div>
        <div class="my">
          <span class="font messageFont" @click="goWorksManagement">小说管理</span>
        </div>
        <div class="my">
          <span class="messageFont font">订阅管理</span>
        </div>
      </el-drawer>
    </div>
  </div>
</template>

<script>
import {shopMixin} from '@/store/mixin';

export default {
  mixins: [shopMixin],
  name: "Head",
  data() {
    return {
      newTime: '',
      drawer: false,
      batch: null,
      handMyName: '',
      score: '',
      ranking: '',
      area: '',
      department: '',
      oldScrollTop: 0,
      head: 'head',
      dateTime: 'dateTime',
      myMessage: 'myMessage',
      selectSchoolInArea: 'selectSchoolInArea',
      selectBatchClass: 'selectBatch',
      mark: 'mark'
    }
  },
  mounted() {
    this.nowTimes();
    this.batchClick(0);
    window.addEventListener("scroll", this.scrolling);
  },
  destroyed() {
    window.removeEventListener("scroll", this.scrolling);
  },
  methods: {
    getDate() {
      var date = new Date();
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      month = month < 10 ? "0" + month : month;
      var day = date.getDate();
      day = day < 10 ? "0" + day : day;
      var week = date.getDay();
      week = "日一二三四五六".charAt(week);
      week = "星期" + week;
      return year + "." + month + "." + day + "  " + week + "  ";
    },
    getTime() {
      var date = new Date();
      var hour = date.getHours();
      hour = hour < 10 ? "0" + hour : hour;
      var minute = date.getMinutes();
      minute = minute < 10 ? "0" + minute : minute;
      var second = date.getSeconds();
      second = second < 10 ? "0" + second : second;
      this.newTime = '';
      this.newTime = hour + ":" + minute + ":" + second;
    },
    nowTimes() {
      setInterval(this.getTime, 1000);
      this.clear();
    },
    clear() {
      clearInterval(this.nowTimes)
      this.nowTimes = null;
    },
    batchClick(batch) {
      this.updateSelectBatch(batch);
      this.batchShowText();
    },
    batchShowText() {
      let tempBatch = this.getSelectBatch();
      if (tempBatch !== null) {
        switch (tempBatch) {
          case 0: {
            this.batch = null;
            break;
          }
          case 1: {
            this.batch = "本科一批";
            break;
          }
          case 2: {
            this.batch = "本科二批";
            break;
          }
        }
      }
    },
    scrolling() {
      // 滚动条距文档顶部的距离
      let scrollTop = window.pageYOffset || document.documentElement.scrollTop ||
          document.body.scrollTop
      // 滚动条滚动的距离
      let scrollStep = scrollTop - this.oldScrollTop;
      // 更新——滚动前，滚动条距文档顶部的距离
      this.oldScrollTop = scrollTop;
      if (scrollStep > 0 && this.oldScrollTop > 90) {
        this.head = 'head change';
        this.dateTime = 'dateTime nonDisplay';
        this.myMessage = 'myMessage nonDisplay';
        this.selectSchoolInArea = 'selectSchoolInArea nonDisplay';
        this.selectBatchClass = 'selectBatch nonDisplay';
        this.mark = 'mark change';
      } else {
        this.head = 'head';
        this.dateTime = 'dateTime';
        this.myMessage = 'myMessage';
        this.selectSchoolInArea = 'selectSchoolInArea';
        this.selectBatchClass = 'selectBatch';
        this.mark = 'mark';
      }
    },
    collectionInformation() {
      this.drawer = false;
      let tempRouter = window.location.href;
      if (tempRouter.search('/information') !== -1) {
        location.reload();
        return;
      }
      this.$router.push({
        path: '/information'
      });
    },
    goMain() {
      this.$router.push({
        path: '/main'
      });
    },
    liberalArtsOrScience() {
      if (this.department == "W") {
        return "文科";
      } else if (this.department == "L") {
        return "理科";
      } else {
        return "文理科:暂无";
      }
    },
    goDataManage() {
      this.$router.push({
        path: '/login'
      });
    },
    goAuthorCenter() {
      this.drawer = false;
      this.$router.push({
        path: '/authorCenter'
      });
    },
    goWorksManagement() {
      this.drawer = false;
      this.$router.push({
        path: '/worksManagement'
      });
    },
    searchMyMessage() {
      this.$alert('请登录！', '警告！', {
        confirmButtonText: '我这就去...',
        callback: action => {
          window.location.href = 'login';
        }
      });
      this.drawer = true;
      this.handMyName = this.getMyName();
      this.score = this.getMyScore();
      this.ranking = this.getMyRanking();
      this.area = this.getAreaSource();
      this.department = this.getMyDepartment();
    }
  }
}
</script>

<style scoped>
.head {
  height: 90px;
  width: 100%;
  background-color: #626aef;
  display: flex;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1;
  box-shadow: 0 2px 4px #898989;
  transition: 0.15s;
}

.dateTime {
  height: 90px;
  width: 300px;
  margin: 0 0 0 0;
}

.date {
  height: 35px;
  width: 250px;
  margin: 15px 0 0 30px;

}

.time {
  height: 50px;
  width: 100%;
  margin: 5px 0 0 0;
}

.mark {
  height: 90px;
  width: 120px;
  margin-left: 70px;
  background: url('~@/assets/images/mark.svg') no-repeat 100% 100%;
  transition: 1.2s;
}

.selectBatch {
  height: 50px;
  width: 200px;
  margin: 30px 40px 0 30px;
}

.selectSchoolInArea {
  height: 40px;
  width: 200px;
  margin: 30px 20px 0 150px;
}

.myMessage {
  height: 40px;
  width: 200px;
  margin: 30px 10px 0 75px;
}

.font {
  font-family: 'Comic Sans MS', cursive;
  color: white;
}

.messageFont {
  font-size: 30px;
  color: #FF9933;
  margin: auto;
  text-align: center;
  display: block;
  margin-top: 10px;
}

.el-dropdown-link {
  cursor: pointer;
  color: #ffffff;
}

.headPortrait {
  border-radius: 50%;
  height: 120px;
  width: 120px;
  margin: auto;
}

.my {
  height: 60px;
  width: 80%;
  margin: auto;
  border-top: 1px solid #FF9933;
  margin-top: 20px;
}

.change {
  height: 0px;
  transition: 0.15s;
  background-image: none;
}

.nonDisplay {
  display: none;
}
</style>