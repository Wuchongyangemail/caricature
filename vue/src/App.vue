<template>
  <div>
    <Head/>
    <HeadFill/>
    <router-view/>
    <Foot/>
  </div>
</template>

<style>

</style>
<script>
  import Foot from "./component/all/Foot";
  import Head from "./component/all/Head";
  import HeadFill from "./component/all/HeadFill";
  export default {
    name:"app",
    components:{
      Head,
      Foot,
      HeadFill
    }
  }
</script>