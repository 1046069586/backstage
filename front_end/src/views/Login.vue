<template>
<div class="wrapper">
  <div style="margin: 200px auto; width: 300px; height: 250px; background-color: #fff; padding: 20px; border-radius: 10px">
    <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登录</b></div>
      <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>
      <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
    <div style="margin: 10px 0; text-align: right">
      <el-button type="primary" size="small" aria-autocomplete="off" @click="login">登录</el-button>
      <el-button type="warning" size="small" autocomplete="off" @click="$router.push('/register')">注册</el-button>
    </div>

  </div>

</div>
</template>

<script>
export default {
  name: "Login",
  data(){
    return {
      user: {}
    }
  },
  methods: {
    login() {
      this.request.post("/user/login", this.user).then(res => {
        if(res.code === "200"){
          this.$router.push("/")
          localStorage.setItem("user", JSON.stringify(res.data))
        }else{
          this.$message.error(res.msg)
        }
      })

    }
  }
}
</script>

<style>
  .wrapper{
    height: 100vh;
    background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);
    overflow: hidden;
  }

</style>