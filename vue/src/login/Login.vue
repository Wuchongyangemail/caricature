<template>
    <div>
        <Header/>
        <div style="display: flex;">
            <Aside/>
            <Grid/>
        </div>
    </div>
    <router-view/>
</template>

<script>
    import Header from "../components/Header"
    import Aside from "../components/Aside"
    import Grid from "../components/Grid"
    export default {
        name: "Login",
        components:{
            Header,
            Aside,
            Grid
        }
    }
</script>

<style scoped>

</style>