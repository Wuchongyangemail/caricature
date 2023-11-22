import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// import 'element-ui/lib/theme-chalk/index.css';
import 'dayjs/locale/zh-cn'
import zhCn from 'element-plus/lib/locale/lang/zh-cn'

createApp(App).use(store).use(ElementPlus,{ zhCn }).use(router).mount('#app')
