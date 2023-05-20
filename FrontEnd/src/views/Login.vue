<template>
    <el-container>
      <el-header>
        <span>
          <img :src="logourl"></img>
        </span>

        <el-button style="margin-left: 1000px">
          1
        </el-button>

        <el-button>

        </el-button>

        <el-button>

        </el-button>
      </el-header>

      <el-main>
        <el-card style="width: 40%;position: center" v-show="idvisible">
          <div style="font-family: 华文行楷;font-size: xxx-large">登录</div>
          <el-form label-width="80px" label-position="left">
            <el-form-item :label="loginoption">
              <el-input v-model="form.userid" autocomplete="off" ></el-input>
            </el-form-item>
            <el-form-item label="Password" style="margin-left: 0px">
              <el-input v-model="form.pw" show-password autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <el-button @click="loginwithphone" type="text">使用手机号登录</el-button>
          <el-button @click="loginwithname" type="text">使用用户名登录</el-button>
          <el-button :datasrc="button" @click="handregist" style="position: relative;width: 80px;height: 40px;background-image: linear-gradient(to bottom right,darkgray,#3F5EFB);border: #cccccc;font-size: large;font-family: 华文行楷;z-index: 1" >
            注册
          </el-button>
          <el-button @click="handlogin" style="position: relative;width: 80px;height: 40px;background-image: linear-gradient(to bottom right,#cccccc,#3F5EFB);margin-top: 100px;border: #cccccc;font-size: large;font-family: 华文行楷;z-index: 1">
            登录
          </el-button>
        </el-card>

        <el-card style="width: 40%;position: center" v-show="namevisible">
          <div style="font-family: 华文行楷;font-size: xxx-large">登录</div>
          <el-form label-width="80px" label-position="left">
            <el-form-item :label="loginoption">
              <el-input v-model="form.username" autocomplete="off" ></el-input>
            </el-form-item>
            <el-form-item label="Password" style="margin-left: 0px">
              <el-input v-model="form.pw" show-password autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <el-button @click="loginwithphone" type="text">使用手机号登录</el-button>
          <el-button @click="loginwithid" type="text">使用用户账号登录</el-button>
          <el-button :datasrc="button" @click="handregist" style="position: relative;width: 80px;height: 40px;background-image: linear-gradient(to bottom right,darkgray,#3F5EFB);border: #cccccc;font-size: large;font-family: 华文行楷;z-index: 1" >
            注册
          </el-button>
          <el-button @click="handlogin" style="position: relative;width: 80px;height: 40px;background-image: linear-gradient(to bottom right,#cccccc,#3F5EFB);margin-top: 100px;border: #cccccc;font-size: large;font-family: 华文行楷;z-index: 1">
            登录
          </el-button>
        </el-card>

        <el-card style="width: 40%;position: center" v-show="phonevisible">
          <div style="font-family: 华文行楷;font-size: xxx-large">登录</div>
          <el-form label-width="80px" label-position="left">
            <el-form-item :label="loginoption">
              <el-input v-model="form.phone" autocomplete="off" ></el-input>
            </el-form-item>
            <el-form-item label="Password" style="margin-left: 0px">
              <el-input v-model="form.pw" show-password autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <el-button @click="loginwithid" type="text">使用用户账号登录</el-button>
          <el-button @click="loginwithname" type="text">使用用户名登录</el-button>
          <el-button :datasrc="button" @click="handregist" style="position: relative;width: 80px;height: 40px;background-image: linear-gradient(to bottom right,darkgray,#3F5EFB);border: #cccccc;font-size: large;font-family: 华文行楷;z-index: 1" >
            注册
          </el-button>
          <el-button @click="handlogin" style="position: relative;width: 80px;height: 40px;background-image: linear-gradient(to bottom right,#cccccc,#3F5EFB);margin-top: 100px;border: #cccccc;font-size: large;font-family: 华文行楷;z-index: 1">
            登录
          </el-button>
        </el-card>

      </el-main>
    </el-container>

</template>

<script>
// import request from "@/utils/request"
import router from "@/router/index"
import axios from 'axios'

export default {
  name: "log",
  data() {
    return {
      userid: "",
      username: "",
      pw: "",
      pn: "",
      loginoption: "userid",
      phonevisible: false,
      idvisible: true,
      namevisible: false,
      // loginvisible: false,
      // registvisible: false,
      nowc1: "",
      nowc2: "",
      form: {},
      button: "",
      uid: "",
      tableData: [],
      logourl: require("../pic/logo.jpg")
    }
  },
  methods: {
    handlogin() {
      console.log("show router")
      console.log(this.$router)
      this.login()
      this.loginvisible = true
      this.form = {}
    },
    handregist(){
      this.$router.push({name:'regist'});
    },
    loginwithphone(){
      this.loginoption = "phone"
      this.phonevisible = true
      this.idvisible = false
      this.namevisible = false
    },
    loginwithid(){
      this.loginoption = "userid"
      this.idvisible = true
      this.phonevisible = false
      this.namevisible = false
    },
    loginwithname(){
      this.loginoption = "username"
      this.namevisible = true
      this.phonevisible = false
      this.idvisible = false
    },
    login() {
      console.log("122")
      if(this.loginoption == "username")
      axios.post('http://10.192.22.86:7001/user/loginbyname?username='+this.username+'&password='+this.password).then(res => {
        console.log("111")
        console.log(res == null)
        if (res.userid != null) {
          this.$message.success("success!")
          console.log("user:"+res.userid)
          this.$router.push({name:'userinfo',query:{userid : res.userid,username: res.username}});
        }
        else{
          this.$message({
            message: 'No matching!Please check!',
            type: 'warning'
          });
        }
      }).catch((error) => {
        this.showMessage(error.response);
      })
      else if(this.loginoption == "userid"){
        axios.get('http://10.192.22.86:7001/user/loginbyid?userid='+this.userid+'&password='+this.password).then(res => {
          console.log("111")
          console.log(res == null)
          if (res.userid != null) {
            this.$message.success("success!")
            console.log("user:"+res.userid)
            this.$router.push({name:'userinfo',query:{userid : res.userid,username: res.username}});
          }
          else{
            this.$message({
              message: 'No matching!Please check!',
              type: 'warning'
            });
          }
        }).catch((error) => {
          this.showMessage(error.response);
        })
      }
      else if(this.loginoption == "phone"){
        axios.post('http://10.192.22.86:7001/user/loginbyphone?phone='+this.username+'&password='+this.password).then(res => {
          console.log("111")
          console.log(res == null)
          if (res.userid != null) {
            this.$message.success("success!")
            console.log("user:"+res.userid)
            this.$router.push({name:'userinfo',query:{userid : res.userid,username: res.username}});
          }
          else{
            this.$message({
              message: 'No matching!Please check!',
              type: 'warning'
            });
          }
        }).catch((error) => {
          this.showMessage(error.response);
        })
      }
    },
  },
}
</script>

<style scoped>
.pict{
  /*background: url("../images/loggin.jpg");*/
  width: 100%;
  height: 100%;
  position: fixed;
  background-size: 100% 100% ;
  overflow: hidden;
}
#particles-js{
  width: 100%;
  height: calc(100% - 100px);
  position: fixed;
}
/*.el-container{*/
/*  text-align: center;*/
/*}*/
.el-main{
  text-align: center;
}
.el-card{
  text-align: center;
  margin-left: 30%;
}
</style>
