<template>
  <div class="wrapper">
    <div style="margin: 200px auto; width: 300px; height: 250px; background-color: #fff; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>注册</b></div>
      <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>
      <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
      <div style="margin: 10px 0; text-align: right">
        <el-button type="primary" size="small" aria-autocomplete="off" @click="login">注册</el-button>
        <el-button type="warning" size="small" autocomplete="off" @click="$router.push('/login')">返回登入</el-button>
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
      this.request.post("/user/register", this.user).then(res => {
        if(res.code === "200"){
          this.$message.success("注册成功")
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