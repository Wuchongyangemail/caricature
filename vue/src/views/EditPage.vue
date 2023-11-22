<template>
  <div class="all">
    <div class="head">
      <div class="picture">
        <el-image
            style="z-index: 0; width: 100px;"
            :src="this.getPicUrl()"
            :fit="fit">
        </el-image>
      </div>
      <div class="line"></div>
      <div class="mainName">
        <span class="font messageFont">
          <strong v-text="this.getName()"></strong>
        </span>
      </div>
      <div class="memo">
        <span class="font memoFont">
          <strong v-text="this.getMo()"></strong>
        </span>
      </div>
      <div class="editButton">
        <el-button class="buttonStyle"  type="warning" @click="goEidt">编辑</el-button>
      </div>
    </div>
    <div class="center">
      <div class="centerBox">
        <div class="center_left">
          <el-button class="buttonStyle"  type="warning" @click="back">返回</el-button>
        </div>
        <div class="center_left">
          <el-button class="buttonStyle"  type="success" @click="editPage">发布新章节</el-button>
        </div>
      </div>
    </div>
    <div class="foot">
      <el-table
          :data="tableData"
          style="width: 100%"
          class="el_table"
          height="520">
        <el-table-column
            fixed
            width="100"
            label="">
          <template v-slot="{ row }">
            <el-image
                :src="getPicUrl(row)"
                :fit="fit"></el-image>
          </template>
        </el-table-column>
        <el-table-column
            fixed
            prop=""
            label=""
            width="100">
        </el-table-column>
        <el-table-column
            fixed
            prop="name"
            label="名称"
            width="300">
        </el-table-column>
        <el-table-column
            fixed
            prop="createTime"
            label="更新时间"
            width="200">
        </el-table-column>
        <el-table-column
            prop="zip"
            label="操作"
            width="200">
          <template v-slot="{ row }">
            <el-button class="buttonStyle"  @click="editPage(row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="page">
        <el-pagination
            background
            layout="prev, pager, next"
            :total="total"
            :default-page-size="limit"
            :page-size="limit.toString()"
            :current-page="page.toString()"
            @current-change="current_click"
        >
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import {shopMixin} from "@/store/mixin";
import request from "@/utils/request";

export default {
  name: "EditPage",
  mixins: [shopMixin],
  mounted() {
    this.carMo = window.sessionStorage.getItem('CARMO');
    this.carName = window.sessionStorage.getItem('CARNAME');
    this.carUrl = window.sessionStorage.getItem('CARURL');
    this.carCode = window.sessionStorage.getItem('CARID');
    this.searchList();
  },
  data() {
    return {
      total: 0,
      searchParam: {
        carCode: null,
        page: 1,
        limit: 10
      },
      page: 1,
      limit: 10,
      req: request,
      tableData: [],
      carName: '',
      carUrl: '',
      carMo: '',
      carCode: ''
    }
  },
  methods: {
    editPage(row) {
      this.keepCarMomentMes(row.code, row.name, row.url);
      this.$router.push({
        path: '/chapterPage'
      });
    },
    back() {
      this.$router.push({
        path: '/worksManagement'
      });
    },
    goEidt(){
      this.$router.push({
        path: '/newlyPublish'
      });
    },
    searchList() {
      this.searchParam.carCode = this.carCode;
      this.searchParam.page = this.page;
      this.searchParam.limit = this.limit;
      this.req.post("/api/caricature/searchDetail", this.searchParam).then(res => {
        if (res.code == '200') {
          this.tableData = res.results;
          this.total = res.total;
        } else {
          this.$message({
            showClose: true,
            message: res.msg,
            type: 'error'
          });
        }
      })
    },
    current_click(page) {
      this.page = page;
      this.searchList();
    },
    getPicUrl(row) {
      if (row == null || !row.url.includes('http')) {
        return "https://www.redbz.com" + this.carUrl;
      }
      return row.url;
    },
    getName() {
      return this.carName;
    },
    getMo() {
      return this.carMo;
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
//border: 1px solid black; margin-left: 15%; border-radius: 50px;
  background-color: rgba(255, 255, 255);
}

.el_table {
  margin-top: 40px;
  background-color: rgba(255, 255, 255);
  border-radius: 10px;
  z-index: 0;
//border: 1px solid black;
}

.foot {
  height: 600px;
  margin-top: 10px;
  width: 70%;
//border: 1px solid black; margin-left: 15%; border-radius: 50px;
  padding-top: 1px;
  background-color: rgba(255, 255, 255);
}

.picture {
  width: 150px;
  height: 180px;
  margin-top: 10px;
  margin-left: 100px;
//border: 1px solid rebeccapurple;
  float: left;
}

.mainName {
  width: 400px;
  height: 60px;
//border: 1px solid rosybrown; margin-left: 70px; margin-top: 10px;
  float: left;
}

.memo {
  width: 550px;
  height: 100px;
//border: 1px solid green;
  float: left; margin-top: 10px;
  margin-left: 20px;
}

.editButton{
  width: 80px;
  height: 50px;
  //border: 1px solid green;
  margin-top: 10px;
  float: right;
}

.font {
  font-family: 'Comic Sans MS', cursive;
  color: white;
}

.center {
  height: 60px;
  width: 70%;
//border: 1px solid black; margin-left: 15%; margin-top: 10px;
  border-radius: 40px;
  background-color: rgba(255, 255, 255);
}

.center_left {
  height: 34px;
  width: 100px;
  margin-top: 13px;
  margin-left: 90px;
  float: left;
//border: 1px solid yellow;
}

.centerBox {
  height: 100%;
  width: 500px;
  margin: auto;
//border: 1px solid hotpink;
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

.page {
  height: 30px;
  width: 300px;
//border: 1px solid hotpink;
  margin-left: 30px;
  margin-top: 5px;
}

.buttonStyle {
  padding-top: 8px;
}
</style>