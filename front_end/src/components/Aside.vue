<template>
  <el-menu :default-openeds="['1']" router style="background-color: rgb(238, 241, 246)">

        <el-menu-item index="/">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span>主页</span>
          </template>
        </el-menu-item>
      <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span>系统管理</span>
      </template>
        <el-menu-item index="/user">
            <i class="el-icon-menu"></i>
            <span slot="title">用户管理</span>
        </el-menu-item>
        <el-menu-item index="/file">
          <i class="el-icon-document"></i>
          <span slot="title">文件管理</span>
        </el-menu-item>
      </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>

</style>