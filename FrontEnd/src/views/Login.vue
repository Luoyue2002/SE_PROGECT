<template>
  <div class="loginstyle">
    <el-container>
      <el-header direction="vertical" style="position: absolute;overflow-x: hidden;overflow-y: hidden">
        <el-row>
          <el-col :span="1" class="lightgreen-box"><img :src="logourl" alt="qiushi" style="display: block;height: 8vh;margin-left: 0px"></img></el-col>
<!--          <el-col :span="18" class="orange-box"><h5 style="display: block;font-size: x-large;margin-top: 3vh;margin-left: 10vh;color: black">qiushi</h5></el-col>-->
        </el-row>
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
              <el-input v-model="form.userphone" autocomplete="off" ></el-input>
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
  </div>
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
      userphone: "",
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
      logourl: require("../pic/logo.png")
    }
  },
  methods: {
    handlogin() {
      this.login()
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
      if(this.loginoption == "username")
      axios.post('http://10.162.59.81:8080/user/loginByName?userName='+this.form.username+'&password='+this.form.pw).then(res => {
        console.log(res == null)
        if (res.data.success == true) {
          this.$message.success("success!")
          // console.log("user:"+res.userid)
          // this.$router.push({name:'userinfo',query:{userid : res.userid,username: res.username}});
          this.$router.push({name:'userinfo',query:{userid : res.data.data.id,username: this.form.username}});
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
        axios.get('http://10.162.59.81:8080/user/loginById?userId='+this.form.userid+'&password='+this.form.pw).then(res => {
          // console.log(res.data.success)
          if (res.data.success == true) {
            this.$message.success("success!")
            // console.log("user:"+res.userid)
            // this.$router.push({name:'userinfo',query:{userid : res.userid,username: res.username}});
            this.$router.push({name:'userinfo',query:{userid : this.form.userid,username: res.data.data.name}});
          }
          else{
            this.$message({
              message: 'No matching!Please check!',
              type: 'warning'
            });
          }
        }).catch((error) => {
          this.$message({
            message: error,
            type: 'warning'
          });
        })
      }
      else if(this.loginoption == "phone"){
        axios.post('http://10.162.59.81:8080/user/loginByPhone?userPhone='+this.form.userphone+'&password='+this.form.pw).then(res => {
          // console.log("111")
          // console.log(res == null)
          if (res.data.success == true) {
            this.$message.success("success!")
            // console.log("user:"+res.userid)
            // this.$router.push({name:'userinfo',query:{userid : res.userid,username: res.username}});
            this.$router.push({name:'userinfo',query:{userid : res.data.data.id,username: res.data.data.name}});
          }
          else{
            this.$message({
              message: 'No matching!Please check!',
              type: 'warning'
            });
          }
        }).catch((error) => {
          this.$message({
            message: error,
            type: 'warning'
          });
        })
      }
    },
    gotohome(){
      this.$router.push({name:'homepage',query:{userid : this.form.userid,username: this.form.username}});
    },
    gotostar() {
      this.$router.push({name:'cart',query:{userid : this.form.userid,username: this.form.username}});
    },
    gotoinfo() {
      this.$router.push({name:'userinfo',query:{userid : this.form.userid,username: this.form.username}});
    },
    gotochat(){
      this.$router.push({name:'chat',query:{userid : this.form.userid,username: this.form.username}});
    },
    gotoshoppingcart() {
      this.$router.push({name:'cart',query:{userid : this.form.userid,username: this.form.username}});
    }
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
  margin-top: 20vh;
}
.el-card{
  text-align: center;
  margin-left: 30%;
}
</style>

</style>
<style scoped>
.loginstyle{
  width: 100%;
  height: 100%;
  margin-top: 0px;
  overflow-x: hidden;
};
</style>