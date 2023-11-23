// permission-guard.js
import { ref, onMounted } from 'vue';

export default {
    name: 'PermissionGuard',
    setup() {
        console.log('2384');
        const hasPermission = ref(true);
        onMounted(() => {
            console.log('123321')
            // 在这里执行权限校验逻辑，设置 hasPermission 的值
            // 例如，你可以通过 API 请求来判断用户是否具有访问权限
            // 如果用户具有访问权限，将 hasPermission 设置为 true，否则设置为 false
        });

        return {
            hasPermission,
        };
    },
};