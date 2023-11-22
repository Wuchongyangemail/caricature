const actions = {
    // commit执行的方法, state是对象
    setSelectBatch: ({commit, state}, selectBatch) => {
        return commit('SET_SELECT_BATCH', selectBatch);
    },
    setMyRanking: ({commit, state}, myRanking) => {
        return commit('SET_MY_RANKING', myRanking);
    },
    setMyScore: ({commit, state}, myScore) => {
        return commit('SET_MY_SCORE', myScore);
    },
    setAreaSource: ({commit, state}, areaSource) => {
        return commit('SET_AREA_SOURCE', areaSource);
    },
    setMyName: ({commit, state}, myName) => {
        return commit('SET_MY_NAME', myName);
    },
    setMyDepartment: ({commit, state}, myDepartment) => {
        return commit('SET_MY_DEPARTMENT', myDepartment);
    },
    setCarId: ({commit, state}, carId) => {
        return commit('SET_CAR_ID', carId);
    },
    setCarName: ({commit, state}, carName) => {
        return commit('SET_CAR_NAME', carName);
    },
    setCarUrl: ({commit, state}, carUrl) => {
        return commit('SET_CAR_URL', carUrl);
    },
    setCarMo: ({commit, state}, carMo) => {
        return commit('SET_CAR_MO', carMo);
    }
};

export default actions;