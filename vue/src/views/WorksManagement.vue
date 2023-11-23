<template>
  <div class="all">
    <div class="select">
      <div class="back">
        <el-button type="warning" class="buttonStyle" @click="back">返回</el-button>
      </div>
      <div class="add">
        <el-button class="buttonStyle" type="success" @click="newlyPublishClick">发布新小说</el-button>
      </div>
      <div class="selectButton">
        <el-button type="primary" class="buttonStyle" @click="edit"><strong class="font">查询</strong></el-button>
      </div>
      <div class="inputButton">
        <el-input style="margin-top: 1.5px" type="name" class="inputStyle" placeholder="账号" v-model="inputValue"
                  maxlength="10"/>
      </div>
    </div>
    <div class="list">
      <el-table
          :data="tableData"
          class="table_el"
          style="margin-top: 44px;"
          max-height=390px>
        <el-table-column
            fixed
            label="预览">
          <template v-slot="{ row }">
            <el-image
                :src="getPicUrl(row)"
                :fit="fit"></el-image>
          </template>
        </el-table-column>
        <el-table-column
            fixed
            prop="name"
            label="名称"
            width="150">
        </el-table-column>
        <el-table-column
            prop="createUser"
            label="作者"
            width="150">
        </el-table-column>
        <el-table-column
            label="标签"
            width="120">
          <template v-slot="{ row }">
            <el-tag type="warning" v-for="(item, index) in row.tags" :key="index" :is-group="true" :is-collapse="true">
              {{ item }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
            label="浏览量"
            prop="pageView"
            width="120">
        </el-table-column>
        <el-table-column
            label="是否展示"
            width="120">
          <template v-slot="{ row }">
            <el-tag v-if="row.show == 1" type="success">是</el-tag>
            <el-tag v-if="row.show == 0 || row.show == null || row.show ==''" type="danger">否</el-tag>
          </template>
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="最后更新时间"
            width="120">
        </el-table-column>
        <el-table-column
            label="操作"
            width="200"
            fixed="right">
          <template v-slot="{ row }">
            <el-button class="buttonStyle" @click="editPage(row)">编辑</el-button>
            <el-button class="buttonStyle" v-if="row.show == 1" @click="reShow(row)">下架</el-button>
            <el-button class="buttonStyle" v-if="row.show == 0 || row.show == null || row.show ==''"
                       @click="reShow(row)">上架
            </el-button>
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
import newlyPublish from "@/views/NewlyPublish.vue";

export default {
  name: "worksManagement",
  mixins: [shopMixin],
  mounted() {
    this.searchList();
  },
  data() {
    return {
      total: 0,
      limit: 5,
      page: 1,
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      srcList: [
        'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
        'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg'
      ],
      tableData: [],
      searchParam: {
        inputValue: '',
        limit: 10,
        page: 1,
        id: null,
        show: null
      },
      inputValue: '',
      req: request,
      like: true,
      value1: 4154.564,
      value2: 1314,
      title: "增长人数"
    }
  },
  methods: {
    edit() {
      this.isDisabled = !this.isDisabled;
      this.searchParam.inputValue = this.inputValue;
      this.page = 1;
      this.searchList();
    },
    reShow(row) {
      this.searchParam.show = (row.show == 1 ? 0 : 1);
      this.searchParam.id = row.id;
      this.req.post("/api/caricature/isShow", this.searchParam).then(res => {
        if (res.code == "200") {
          this.searchList();
          this.$message({
            showClose: true,
            message: res.msg,
            type: 'success'
          });
        } else {
          this.$message({
            showClose: true,
            message: res.msg,
            type: 'error'
          });
        }
      })
    },
    editPage(row) {
      this.keepCarMes(row.code, row.name, row.url, row.introduction);
      this.$router.push({
        path: '/editPage'
      });
    },
    newlyPublishClick() {
      window.sessionStorage.removeItem('CARURL');
      window.sessionStorage.removeItem('CARMO');
      window.sessionStorage.removeItem('CARNAME');
      this.$router.push({
        path: '/newlyPublish'
      });
    },
    back() {
      window.history.back();
    },
    searchList() {
      this.searchParam.page = this.page;
      this.searchParam.limit = this.limit;
      this.req.post("/api/caricature/getPageList", this.searchParam).then(res => {
        this.tableData = res.results;
        this.total = res.total;
      })
    },
    getPicUrl(row) {
      if (row == null) {
        return;
      }
      return "https://www.redbz.com/" + row.url;
    },
    // prev_click(page){
    //   console.log(1);
    //   this.page += page;
    //   this.searchList();
    // },
    current_click(page) {
      this.page = page;
      this.searchList();
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

.select {
  height: 50px;
  width: 70%;
  margin: auto;
//border: 1px solid blue; background-color: rgba(255, 255, 255); border-radius: 40px;
}

.inputButton {
  width: 250px;
  height: 100%;
  float: right;
//border: 1px solid black;
}

.selectButton {
  width: 70px;
  float: right;
//border: 1px solid red; padding-top: 8px; padding-left: 20px; margin-right: 50px;
}

.like {
  cursor: pointer;
  font-size: 25px;
  display: inline-block;
}

.list {
  width: 70%;
  height: 480px;
//border: 1px solid blueviolet; background-color: rgba(255, 255, 255); border-radius: 50px; margin: auto; margin-top: 15px;
  padding-top: 1px;
  z-index: 0;
}

.table_el {
  background-color: rgba(255, 255, 255);
  z-index: 0;
//border: 1px solid yellow;
}

.back {
  float: left;
  margin-left: 40px;
  margin-top: 10px;
}

.add {
  float: left;
  margin-left: 40px;
  margin-top: 10px;
}

.page {
  height: 30px;
  width: 300px;
//border: 1px solid hotpink; margin-left: 20px; margin-top: 5px;
}

.buttonStyle {
  padding-top: 8px;
}
</style>