<template>
  <div>
    <el-container>

      <el-main>
            <el-card>
            <el-form :inline="true" label-width="140px" label-position="right">
              <el-col span="8" style="margin-left: 25vh;text-align: left">
                <td><el-form-item label="用户名"></el-form-item></td>
                <td><el-input v-model="username"></el-input></td></el-col>
              <el-col span="8" style="margin-right: 25vh">
                <td><el-form-item label="账号"></el-form-item></td>
                <td><el-input v-model="userid"></el-input></td></el-col>
              <el-col span="8" style="margin-left: 25vh">
                <td><el-form-item label="密码"></el-form-item></td>
                <td><el-input v-model="password"></el-input></td></el-col>
              <el-col span="8" style="margin-right: 25vh">
                <td><el-form-item label="确认密码"></el-form-item></td>
                <td><el-input v-model="confirm"></el-input></td></el-col>
              <el-col span="8" style="margin-left: 25vh">
                <td><el-form-item label="真实姓名"></el-form-item></td>
                <td><el-input v-model="realname"></el-input></td></el-col>
              <el-col span="8" style="margin-right: 25vh">
                <td><el-form-item label="身份证号"></el-form-item></td>
                <td><el-input v-model="identifier"></el-input></td></el-col>
              <el-col span="8" style="margin-left: 25vh">
                <td><el-form-item label="学校"></el-form-item></td>
                <td><el-input v-model="school"></el-input></td></el-col>
              <el-col span="8" style="margin-right: 25vh">
                <td><el-form-item label="学号"></el-form-item></td>
                <td><el-input v-model="studentid"></el-input></td></el-col>
              <el-col span="8" style="margin-left: 25vh">
                <td><el-form-item label="性别"></el-form-item></td>
                <td><el-input v-model="gender"></el-input></td></el-col>
              <el-col span="8" style="margin-right: 25vh">
                <td><el-form-item label="手机号"></el-form-item></td>
                <td><el-input v-model="phonenum"></el-input></td>
                <td><el-button size="small" style="margin-left: 10px">获取验证码</el-button></td>
                </el-col>
              <el-col span="8" style="margin-left: 25vh">
                <td><el-form-item label="验证码"></el-form-item></td>
                <td><el-input v-model="captcha"></el-input></td></el-col>
              <el-col span="8" style="margin-right: 25vh">
                <td><el-button @click="handleregister" style="margin-left: 15vh">注册</el-button></td>
                <td><el-button @click="reset" style="margin-left: 10px">重置</el-button></td>
              </el-col>
              <!--            <el-col span="5" style="margin-left: 10vh">-->
              <!--            <el-form-item>身份证照片</el-form-item>-->
              <!--            <el-upload-->
              <!--                action=""-->
              <!--                :on-success="identifieruploadsuccess"-->
              <!--                ref="upload"-->
              <!--                :show-file-list="false">-->
              <!--              <el-button style="margin-left: 2vh" size="small"><i class="el-icon-upload"></i></el-button>-->
              <!--            </el-upload>-->
              <!--&lt;!&ndash;            </el-col>&ndash;&gt;-->
              <!--&lt;!&ndash;            <el-col span="5" style="margin-left: 10vh">&ndash;&gt;-->
              <!--            <el-form-item>学生证照片</el-form-item>-->
              <!--            <el-upload-->
              <!--                action=""-->
              <!--                :on-success="studentuploadsuccess"-->
              <!--                ref="upload"-->
              <!--                :show-file-list="false">-->
              <!--              <el-button style="margin-left: 2vh" size="small"><i class="el-icon-upload"></i></el-button>-->
              <!--            </el-upload>-->
              <!--            </el-col>-->
            </el-form>
          </el-card>
<!--  </div>-->
<!--  </el-col>-->
<!--          <el-col :span="6"><div class="grid-content ep-bg-purple" /></el-col>-->
<!--        </el-row>-->
      </el-main>

      <el-footer>
        <el-link href="" style="margin-top: 20vh">联系我们</el-link>
        <el-link href="" style="margin-left: 10vh;margin-top: 20vh">网站证书</el-link>
        <el-link href="" style="margin-left: 10vh;margin-top: 20vh">联系我们</el-link>
      </el-footer>
    </el-container>

  </div>

</template>

<script>
import axios from 'axios'
export default {
  name: 'regist',

  data() {
    return {
      username: '',
      userid: '',
      password: '',
      confirm: '',
      realname: '',
      identifier: '',
      school: '',
      studentid: '',
      gender: '',
      phonenum: '',
      captcha: '',
      picofidentifier: '',
      picofstudent: '',
    }
  },
  computed: {

  },
  methods: {
    handregist() {
       axios.post('http://10.181.247.69:7001/user/register',
           {
             username: this.username,
             userid: this.userid,
             password: this.password,
             userrealname: this.realname,
             usericid: this.identifier,
             userschool: this.school,
             usersid: this.studentid,
             usergender: this.gender,
             userphone: this.phonenum
           }).then(res=>{
         console.log("1111111")
       })
    },
    reset(){
      this.username = '',
      this.userid = '',
      this.password = '',
      this.confirm =  '',
      this.realname = '',
      this.identifier = '',
      this.school = '',
      this.studentid = '',
      this.gender = '',
      this.phonenum = '',
      this.captcha = '',
      this.picofidentifier = '',
      this.picofstudent = ''
    }
  }
}
</script>

<style scoped>
.form{
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.el-form--inline .el-form-item{
  flex: 1 0 40%;
  align-items: center;
}

.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.el-footer{

}

.el-container{
  background: linear-gradient(white,#cedee1);
}
</style>
