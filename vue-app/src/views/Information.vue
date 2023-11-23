<template>
    <div class="background">
        <div :class="tempName">
            <h2 class="font">What's your name?</h2>
            <el-form :model="nameValidateForm" ref="nameValidateForm" class="demo-ruleForm">
                <el-form-item
                        class = "fromItemStyle"
                        prop="name"
                        :rules="[
                          { required: true, message: '姓名不能为空'},
                          { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                        ]"
                >
                    <el-input type="name" class="inputStyle" placeholder="你的名字" v-model="nameValidateForm.name" maxlength="10"/>
                </el-form-item>
            </el-form>
            <el-button type="success" class="buttonStyle" @click="changeName('nameValidateForm')"><strong class="font">好了</strong></el-button>
        </div>
        <div :class="tempDepartment" style="z-index: 80;">
            <h2 class="font">Are you liberal arts or science?</h2>
            <div class="department" style="float: left;">
                <span class="informationIconStyle iconfont icon-chizi1"/>
                <el-button type="success" class="buttonStyle" @click="changeDepartment('L')" style="margin: 20px 0 0 30px;"><strong class="font">我是理科</strong></el-button>
            </div>
            <div class="department" style="float: right;">
                <span class="informationIconStyle iconfont icon-jilu"/>
                <el-button type="success" class="buttonStyle" @click="changeDepartment('W')" style="margin: 20px 0 0 30px;"><strong class="font">我是文科</strong></el-button>
            </div>
        </div>
        <div :class="tempScore" style="z-index: 75">
            <h2 class="font">What are your grades?</h2>
            <el-form :model="scoreValidateForm" ref="scoreValidateForm" class="demo-ruleForm">
                <el-form-item
                        class = "fromItemStyle"
                        prop="score"
                        :rules="[
                          { required: true, message: '成绩不能为空'}
                        ]"
                >
                    <el-input-number type="score" class="inputStyle" v-model="scoreValidateForm.score" :min="0" :max="750" label="成绩"></el-input-number>
                </el-form-item>
            </el-form>
            <el-button type="success" class="buttonStyle" @click="changeScore('scoreValidateForm')"><strong class="font">好了</strong></el-button>
        </div>
        <div :class="tempArea" style="z-index: 50">
            <h2 class="font">Where are you?</h2>
            <el-form :model="areaValidateForm" ref="areaValidateForm" class="demo-ruleForm">
                <el-form-item
                        class = "fromItemStyle"
                        prop="area"
                        :rules="[
                          { required: true, message: '所在地不能为空'},
                        ]"
                >
                    <el-select type="area" class="inputStyle" v-model="areaValidateForm.area" filterable placeholder="请选择">
                        <el-option
                                v-for="item in areas"
                                :key="item.id"
                                :label="item.areaName"
                                :value="item.areaName">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <el-button type="success" class="buttonStyle" @click="changeArea('areaValidateForm')"><strong class="font">好了</strong></el-button>
        </div>
        <div :class="tempRank" style="z-index: 25">
            <h2 class="font">How do you rank?</h2>
            <el-form :model="rankValidateForm" ref="rankValidateForm" class="demo-ruleForm">
                <el-form-item
                        class = "fromItemStyle"
                        prop="rank"
                        :rules="[
                          { required: true, message: '排名不能为空'}
                        ]"
                >
                    <el-input-number type="rank" class="inputStyle" v-model="rankValidateForm.rank" :min="0" :max="12000000" label="排名"></el-input-number>
                </el-form-item>
            </el-form>
            <el-button type="success" class="buttonStyle" @click="changeRank('rankValidateForm')"><strong class="font">好了</strong></el-button>
        </div>
    </div>
</template>

<script>
    import { shopMixin } from '@/store/mixin';
    import request from '../utils/request';
    export default {
        mixins: [shopMixin],
        name: "Information",
        data() {
            return {
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
            }
        }
    }
</script>

<style scoped>
    /deep/ .el-input__inner {
        height: 47px;
        background-color: rgba(255, 255, 255, 0.247);
    }
    .background {
        height: 400px;
        width: 100%;
        padding-top: 100px;
        background: url('~@/assets/images/mainBackground.svg');
    }
    .font {
        font-family:'Comic Sans MS', cursive;
        color: white;
        text-align: center;
    }
    .name {
        height: 300px;
        width: 300px;
        opacity: 0%;
        margin-left:575px;
        position: absolute;
        transition: 1s;
        z-index: 100;
    }
    .inputStyle {
        width: 200px;
    }
    .buttonStyle {
        margin: 50px 0 0 43%;
    }
    .hidden {
        opacity: 0%;
        transition: 1s;
    }
    .nonHidden {
        opacity: 100%;
        transition: 1s;
    }
    .displayNone {
        display: none;
    }
    .fromItemStyle {
        width: 200px;
        margin: 30px 150px 0 50px;
    }
    .informationIconStyle {
        color: white;
        font-size: 80px;
        margin-left: 30px;
    }
    .department {
        width: 49%;
        height: 160px;
        padding-top: 30px;
    }
</style>


