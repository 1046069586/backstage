<template>
  <div>
    <el-container style="height: 700px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <Aside></Aside>
      </el-aside>

      <el-container>
        <el-header >   <!--style="text-align: right; font-size: 12px"-->
          <Header></Header>
        </el-header>

        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import Aside from "@/components/Aside";
import Header from "@/components/Header";


export default {
  name: 'Home',

  data() {

    return {

    }
  },


  components: {
    Aside,
    Header
  },

}
</script>
