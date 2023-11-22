const message = {
    state: {
        selectBatch: 0,
        myScore: 0,
        myRanking: 0,
        areaSource: null,
        myName: null,
        myDepartment: null,
        carId: null,
        carUrl: null,
        carName: null,
        carMo:null
    },
    mutations: {
        SET_SELECT_BATCH: (state, selectBatch) => {
            state.selectBatch = selectBatch;
        },
        SET_MY_SCORE: (state, myScore) => {
            state.myScore = myScore;
        },
        SET_MY_RANKING: (state, myRanking) => {
            state.myRanking = myRanking;
        },
        SET_AREA_SOURCE: (state, areaSource) => {
            state.areaSource = areaSource;
        },
        SET_MY_NAME: (state, myName) => {
            state.myName = myName;
        },
        SET_MY_DEPARTMENT: (state, myDepartment) => {
            state.myDepartment = myDepartment;
        },
        SET_CAR_ID: (state, carId) => {
            state.carId = carId;
        },
        SET_CAR_NAME: (state, carName) => {
            state.carName = carName;
        },
        SET_CAR_URL: (state, carUrl) => {
            state.carUrl = carUrl;
        },
        SET_CAR_MO: (state, carMo) => {
            state.carMo = carMo;
        }
    },
    actions: {}
};
export default message;