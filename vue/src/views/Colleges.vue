<template>
    <div class="background">
        <div class="searchPanel">
            <el-input type="name" clearable class="inputStyle" placeholder="学校名称" v-model="search" maxlength="10"/>
            <el-button native-type="button" type="success" class="buttonStyle" @click="searchData"><strong class="font">查询</strong></el-button>
        </div>
        <div class="dataPanel">
            <el-table
                    :data="tableData"
                    style="width: 100%;">
                <el-table-column
                        prop="schoolName"
                        label="学校名称"
                        width="250">
                </el-table-column>
                <el-table-column
                        prop="volunteerCode"
                        label="志愿编码"
                        width="250">
                </el-table-column>
                <el-table-column
                        prop="probability"
                        label="概率">
                </el-table-column>
            </el-table>
        </div>
        <div class="page">
            <el-pagination
                    background
                    @current-change="currentChange"
                    layout="prev, pager, next"
                    :page-size=searchParam.pageSize
                    :total=searchParam.total
                    :current-page=searchParam.currentPage
            style="display: inline-block; float: none; margin: auto;">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import { shopMixin } from '@/store/mixin';
    import request from '../utils/request';
    export default {
        mixins: [shopMixin],
        data() {
            return {
                searchParam: {
                    batch: '',
                    department: '',
                    score: 0,
                    rank: 0,
                    area: '',
                    schoolArea: '',
                    pageSize: 10,
                    total: 0,
                    currentPage: 1,
                    search: ''
                },
                tableData: [],
                req: request,
                search: ''
            }
        },
        mounted() {
            this.haveMessage();
            this.searchData();
        },
        methods: {
            searchData() {
                console.log('查询数据');
                this.refreshSearchData();
                this.searchParam.search = this.search;
                this.req.post("/api/controller/searchData",this.searchParam).then(res => {
                    this.searchParam.total = res.total;
                    this.tableData = res.results;
                    console.log(res);
                })
            },
            currentChange(val) {
                if (val > this.searchParam.total || val === 0) {
                    return;
                }
                this.searchParam.currentPage = val;
                this.searchData();
            },
            haveMessage() {
                this.keepSession();
                this.returnMain();
            },
            refreshSearchData() {
                this.searchParam.area = window.sessionStorage.getItem('AREA');
                this.searchParam.batch = window.sessionStorage.getItem('BATCH');
                this.searchParam.department = window.sessionStorage.getItem('DEPARTMENT');
                this.searchParam.rank = window.sessionStorage.getItem('RANK');
                this.searchParam.score = window.sessionStorage.getItem('SCORE');
            }
        }
    }
</script>

<style scoped>
    /deep/ .el-input__inner {
        height: 47px;
        background-color: rgba(255, 255, 255, 0.347);
    }
    /*最外层透明*/
    ::v-deep .el-table,
    ::v-deep .el-table__expanded-cell {
        background-color: transparent !important;
    }
    /* 表格内背景颜色 */
    ::v-deep .el-table th,
    ::v-deep .el-table tr,
    ::v-deep .el-table td {
        background-color: transparent !important;
    }
    /* 去掉中间数据的分割线 */
    .el-table__row>td{
        border: none;
    }
    /* 去掉上面的线 */
    .el-table th.is-leaf{
        border: none;
    }
    /* 去掉最下面的那一条线 */
    .el-table::before {
        height: 0px;
    }
    .background {
        width: 100%;
        background: url('~@/assets/images/mainBackground.svg');
        padding-top: 30px;
    }
    .dataPanel {
        width: 1000px;
        height: 450px;
        margin: auto;
    }
    .searchPanel {
        width: 300px;
        height: 55px;
        margin-left: 900px;
    }
    .font {
        font-family:'Comic Sans MS', cursive;
        color: white;
        text-align: center;
    }
    .inputStyle {
        width: 200px;
    }
    .buttonStyle {
        margin: 0 0 0 20px;
    }
    .page {
        width: 450px;
        height: 100px;
        margin: auto;
        text-align:center;
    }
</style>