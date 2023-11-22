import Vuex from 'vuex';
import message from '@/store/modules/message';
import getters from './getters';
import actions from './actions';
import { createApp } from 'vue';
import App from '../App';

createApp(App).use(Vuex);

export default new Vuex.Store({
  modules: {
    message
  },
  getters,
  actions
});
