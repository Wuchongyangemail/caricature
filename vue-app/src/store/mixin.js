import {mapActions, mapGetters} from 'vuex';

// 保存数据：window.sessionStorage.setItem('key',value);
// 读取数据：window.sessionStorage.getItem('key');
// 删除单个数据：window.sessionStorage.removeItem('key');
// 删除所有数据：window.sessionStorage.clear();
// 得到某个索引的key：window.sessionStorage.key(index);

export const shopMixin = {
    computed: {
        ...mapGetters(['selectBatch', 'myRanking', 'myScore', 'areaSource', 'myName', 'myDepartment', 'carId', 'carName', 'carUrl', 'carMo'])
    },
    methods: {
        ...mapActions(['setSelectBatch', 'setMyRanking', 'setMyScore', 'setAreaSource', 'setMyName', 'setMyDepartment', 'setCarId', 'setCarName', 'setCarMo', 'setCarUrl']),

        returnMain() {
            console.log("判断是否有数据");
            let me = this;
            me.setAreaSource(window.sessionStorage.getItem('AREA'));
            me.setMyDepartment(window.sessionStorage.getItem('DEPARTMENT'));
            me.setSelectBatch(window.sessionStorage.getItem('BATCH'));
            me.setMyRanking(window.sessionStorage.getItem('RANK'));
            me.setMyScore(window.sessionStorage.getItem('SCORE'));
            if (this.getMyScore() == 0 || this.getMyRanking() == 0) {
                this.$router.push({
                    path: '/main'
                });
            }
        },

        keepSession() {
            if (window.sessionStorage.getItem('SCORE') == 0 || window.sessionStorage.getItem('SCORE') == null) {
                window.sessionStorage.setItem('SCORE', this.getMyScore());
                window.sessionStorage.setItem('AREA', this.getAreaSource());
                window.sessionStorage.setItem('RANK', this.getMyRanking());
                window.sessionStorage.setItem('DEPARTMENT', this.getMyDepartment());
                window.sessionStorage.setItem('BATCH', '本科一批');
            }
        },

        removeSession() {
            window.sessionStorage.removeItem('SCORE');
            window.sessionStorage.removeItem('AREA');
            window.sessionStorage.removeItem('RANK');
            window.sessionStorage.removeItem('DEPARTMENT');
            window.sessionStorage.removeItem('BATCH');
        },

        updateSelectBatch(batch) {
            this.setSelectBatch(batch);
            console.log('selectBatch:' + this.selectBatch);
        },

        setCar(name, value) {
            window.sessionStorage.setItem(name, value);
        },

        moveCar(name) {
            window.sessionStorage.removeItem(name);
        },

        keepCarMes(id, name, url, mo) {
            window.sessionStorage.setItem("CARID", id);
            window.sessionStorage.setItem("CARNAME", name);
            window.sessionStorage.setItem("CARURL", url);
            window.sessionStorage.setItem("CARMO", mo);
        },

        keepCarMomentMes(code, name, url) {
            window.sessionStorage.setItem("CARCOMMENTCODE", code);
            window.sessionStorage.setItem("CARCOMMENTNAME", name);
            window.sessionStorage.setItem("CARCOMMENTURL", url);
        },

        getSelectBatch() {
            return this.selectBatch;
        },

        updateMyScore(score) {
            this.setMyScore(score);
        },

        getMyScore() {
            return this.myScore;
        },

        updateMyRanking(ranking) {
            this.setMyRanking(ranking);
        },

        getMyRanking() {
            return this.myRanking;
        },

        updateAreaSource(areaSource) {
            this.setAreaSource(areaSource);
        },

        getAreaSource() {
            return this.areaSource;
        },

        updateMyName(myName) {
            this.setMyName(myName);
        },

        getMyName() {
            return this.myName;
        },

        updateMyDepartment(myDepartment) {
            this.setMyDepartment(myDepartment);
        },

        getMyDepartment() {
            return this.myDepartment;
        },

        getCarId() {
            return this.carId;
        },

        setCarId(carId) {
            this.carId = carId;
        },

        getCarName() {
            return this.carName;
        },

        setCarName(carName) {
            this.carName = carName;
        },

        getCarUrl() {
            return this.carUrl;
        },

        setCarUrl(carUrl) {
            this.carUrl = carUrl;
        },

        getCarMo() {
            return this.carMo;
        },

        setCarMo(carMo) {
            this.carMo = carMo;
        }
    }

};