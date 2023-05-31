<template>
  <div>
    <el-container direction="vertical" style="position: absolute;overflow-x: hidden;overflow-y: hidden">
      <el-header>
        <el-row>
          <el-col :span="1" class="lightgreen-box"><img :src="logourl" alt="qiushi"
              style="display: block;height: 7vh;margin-left: 0px"></el-col>
          <el-col :span="18" class="orange-box">
            <h5 style="display: block;font-size: x-large;margin-top: 2%;margin-left: 1vh;color: black">qiushi</h5>
          </el-col>
          <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-s-home"
              style="height: 5vh;margin-top:30%;color: white" @click="gotohome" type="text"></el-button></el-col>
          <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-chat-dot-round"
              style="height: 5vh;margin-top: 30%;color: white" @click="gotochat" type="text"></el-button></el-col>
          <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-star-on"
              style="height: 5vh;margin-top: 30%;color: white" @click="gotostar" type="text"></el-button></el-col>
          <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-shopping-cart-full"
              style="height: 5vh;margin-top: 30%;color: white" @click="gotoshoppingcart" type="text"></el-button></el-col>
          <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-s-custom"
              style="height: 5vh;margin-top: 30%;color: white" @click="gotoinfo" type="text"></el-button></el-col>
        </el-row>
      </el-header>


      <el-row>
        <!-- 头像和注销按钮 -->
        <el-col :span="6">
          <el-aside width="400px" style="margin-left: 20%;">
            <el-row>
              <el-avatar :src="user_profile" shape="square"></el-avatar>
            </el-row>
            <el-row>
              <el-button type="danger" style="margin-left: 20%; margin-top: 5%;">个人注销
              </el-button>
            </el-row>
          </el-aside>
        </el-col>

        <!-- 个人信息部分 -->
        <el-col :span="12">
          <el-main>
            <el-form label-position="left">

              <el-form-item label="用户名">
                <el-input v-model="user.name" readonly>
                  <template #append>
                    <el-button icon="el-icon-edit" @click="editUsername"></el-button>
                  </template>
                </el-input>
              </el-form-item>

              <el-form-item label="账号">
                <el-input v-model="user.account" readonly>
                </el-input>
              </el-form-item>

              <el-form-item label="密码">
                <el-input v-model="user.password" readonly>
                  <template #append>
                    <el-button icon="el-icon-edit" @click="editPassword">重置密码</el-button>
                  </template>
                </el-input>
              </el-form-item>

              <el-form-item label="学校">
                <el-input v-model="user.school" readonly>
                  <template #append>
                    <el-button icon="el-icon-edit" @click="editSchool">修改学校</el-button>
                  </template>
                </el-input>
              </el-form-item>

              <el-form-item label="学号">
                <el-input v-model="user.schoolId" readonly>
                  <template #append>
                    <el-button icon="el-icon-edit" @click="editSchoolId">修改学号</el-button>
                  </template>
                </el-input>
              </el-form-item>

              <el-form-item label="地址1">
                <el-input v-model="user.address1" readonly>
                  <template #append>
                    <el-button icon="el-icon-edit" @click="editAddress1">修改</el-button>
                  </template>
                </el-input>
              </el-form-item>

              <el-form-item label="地址2">
                <el-input v-model="user.address2" readonly>
                  <template #append>
                    <el-button icon="el-icon-edit" @click="editAddress2">修改</el-button>
                  </template>
                </el-input>
              </el-form-item>

              <el-form-item label="地址3">
                <el-input v-model="user.address3" readonly>
                  <template #append>
                    <el-button icon="el-icon-edit" @click="editAddress3">修改</el-button>
                  </template>
                </el-input>
              </el-form-item>

              <!-- 显示商铺 -->
              <el-form-item label="我的商铺">
                <el-button @click="editAddress1">进入商铺</el-button>
              </el-form-item>

              <el-form-item label="我的订单">
                <div>
                  <el-row>
                    <el-button>我收到的订单</el-button>
                    <el-button>购买记录</el-button>
                  </el-row>
                </div>

              </el-form-item>


            </el-form>
          </el-main>
        </el-col>
      </el-row>

    </el-container>


    <!-- 修改用户名对话框 -->
    <el-dialog title="修改用户名" :visible.sync="editDialogVisible.username" width="30%">
      <el-input v-model="editDialogData.username" placeholder="请输入新的用户名"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible.username = false">取消</el-button>
        <el-button type="primary" @click="saveEditedUsername">保存</el-button>
      </span>
    </el-dialog>

    <el-dialog title="修改密码" :visible.sync="editDialogVisible.password" width="30%">
      <el-input v-model="editDialogData.password" placeholder="请输入新的密码"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible.password = false">取消</el-button>
        <el-button type="primary" @click="saveEditedPassword">保存</el-button>
      </span>
    </el-dialog>

    <el-dialog title="修改学校" :visible.sync="editDialogVisible.school" width="30%">
      <el-input v-model="editDialogData.school" placeholder="请输入新的学校"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible.school = false">取消</el-button>
        <el-button type="primary" @click="saveEditedSchool">保存</el-button>
      </span>
    </el-dialog>

    <el-dialog title="修改学号" :visible.sync="editDialogVisible.schoolId" width="30%">
      <el-input v-model="editDialogData.schoolId" placeholder="请输入新的学校"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible.schoolId = false">取消</el-button>
        <el-button type="primary" @click="saveEditedSchoolId">保存</el-button>
      </span>
    </el-dialog>


    <el-dialog title="修改地址1" :visible.sync="editDialogVisible.address1" width="30%">
      <el-input v-model="editDialogData.address1" placeholder="请输入新的地址1"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible.address1 = false">取消</el-button>
        <el-button type="primary" @click="saveEditedAddress1">保存</el-button>
      </span>
    </el-dialog>

    <el-dialog title="修改地址2" :visible.sync="editDialogVisible.address2" width="30%">
      <el-input v-model="editDialogData.address2" placeholder="请输入新的地址2"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible.address2 = false">取消</el-button>
        <el-button type="primary" @click="saveEditedAddress2">保存</el-button>
      </span>
    </el-dialog>

    <el-dialog title="修改地址3" :visible.sync="editDialogVisible.address3" width="30%">
      <el-input v-model="editDialogData.address3" placeholder="请输入新的地址3"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible.address3 = false">取消</el-button>
        <el-button type="primary" @click="saveEditedAddress3">保存</el-button>
      </span>
    </el-dialog> 

  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "userinfo",
  data() {
    return {
      logourl: require("../pic/logo.jpg"),
      userid:1,
      user_profile: require("../assets/logo.png"),
      user: {
        id: 1,
        name: "123",
        account: "12345",
        password: "123",
        realName: "",
        phoneNumber: "1234",
        school: "zju",
        idendity: "",
        schoolId: "",
        gender: "",
        phone: "",
        image: "",
        address1: "",
        address2: "",
        address3: "",
        ifShop: 1,
      },
      editDialogVisible: {
        username: false,
        password: false,
        school: false,
        schoolId: false,
        address1: false,
        address2: false,
        address3: false,
      },
      editDialogData: {
        username: "name",
        password: "password",
        school: "school",
        schoolId: "schoolId",
        address1: "address1",
        address2: "address2",
        address3: "address3",
      }


    };
  },
  created(){
    this.userid = this.$route.query.userid;
    this.username=this.$route.query.username;
    this.user=this.$route.query.user;
    this.load();
  },
  methods: {
    load(){
      //todo
    },
    //用户名
    editUsername() {
      // this.editDialogData.username = this.user.username;
      this.editDialogVisible.username = true;
    },
    saveEditedUsername() {
      // http
      axios.get('http://127.0.0.1:8080/user/resetInformation?userId=' + this.userid + '&attribute=' + "name"+'&resetInformation='+this.editDialogData.username).then(res => {
        console.log(res);
        console.log(this.editDialogData.username);
      });
      this.user.name = this.editDialogData.username;
      this.editDialogVisible.username = false;
    },
    //密码
    editPassword() {
      // this.editDialogData.username = this.user.username;
      this.editDialogVisible.password = true;
    },
    saveEditedPassword() {
      // http
      axios.get('http://127.0.0.1:8080/user/resetInformation?userId=' + this.userid + '&attribute=' + "password"+'&resetInformation='+this.editDialogData.password).then(res => {
        console.log(res);
        console.log(this.userid);
      });
      this.user.password = this.editDialogData.password;
      this.editDialogVisible.password = false;
    },
    //学校
    editSchool() {
      // this.editDialogData.username = this.user.username;
      this.editDialogVisible.school = true;
    },
    saveEditedSchool() {
      // http
      axios.get('http://127.0.0.1:8080/user/resetInformation?userId=' + this.userid + '&attribute=' + "school"+'&resetInformation='+this.editDialogData.school).then(res => {
        console.log(res);
      });
      this.user.school = this.editDialogData.school;
      this.editDialogVisible.schoodl = false; 
    },
    //学号
    editSchoolId() {
      // this.editDialogData.username = this.user.username;
      this.editDialogVisible.schoolId = true;
    },
    saveEditedSchoolId() {
      // http
      axios.get('http://127.0.0.1:8080/user/resetInformation?userId=' + this.userid + '&attribute=' + "schoolId"+'&resetInformation='+this.editDialogData.schoolId).then(res => {
        console.log(res);
      });
      this.user.schoolId = this.editDialogData.schoolId;
      this.editDialogVisible.schoolId = false;
    },
    //地址1
    editAddress1() {
      // this.editDialogData.username = this.user.username;
      this.editDialogVisible.address1 = true;
    },
    saveEditedAddress1() {
      // http
      axios.get('http://127.0.0.1:8080/user/resetInformation?userId=' + this.userid + '&attribute=' + "address1"+'&resetInformation='+this.editDialogData.address1).then(res => {
        console.log(res);
      });
      this.user.address1 = this.editDialogData.address1;
      this.editDialogVisible.address1 = false;
    },
    //地址2
    editAddress2() {
      // this.editDialogData.username = this.user.username;
      this.editDialogVisible.address2 = true;
    },
    saveEditedAddress2() {
      // http
      axios.get('http://127.0.0.1:8080/user/resetInformation?userId=' + this.userid + '&attribute=' + "address2"+'&resetInformation='+this.editDialogData.address2).then(res => {
        console.log(res);
      });
      this.user.address2 = this.editDialogData.address2;
      this.editDialogVisible.address2 = false;
    },
    //地址3
    editAddress3() {
      // this.editDialogData.username = this.user.username;
      this.editDialogVisible.address3 = true;
    },
    saveEditedAddress3() {
      // http
      axios.get('http://127.0.0.1:8080/user/resetInformation?userId=' + this.userid + '&attribute=' + "address3"+'&resetInformation='+this.editDialogData.address3).then(res => {
        console.log(res);
      });
      this.user.address3 = this.editDialogData.address3;
      this.editDialogVisible.address3 = false;
    },




  },

}
</script>

<style>
#app {
  width: 100%;
  display: flex;
  flex-flow: row wrap;
  align-content: flex-start;
}

.card {
  /*height: 300px;*/
  background-color: #ffffff;
  padding: auto;
  margin: auto;
}

.card .img {
  /*height: 250px;*/
  height: auto;
  vertical-align: middle;
  max-width: 100%;
}

body {
  margin-top: 0;
  padding: 0;
  overflow-x: hidden;
}

.el-header {
  height: 50vh;
  background: -webkit-linear-gradient(left, white, black);
  overflow-y: hidden;
  overflow-x: hidden;
}

.el-container {
  width: 100%;
  overflow-x: hidden;
}

.el-avatar {
  margin-top: 10%;
  margin-left: 10%;
  width: 50%;
  height: auto
}

.custom-descriptions {
  display: flex;
  margin-right: 20%;
  margin-bottom: 5%;
}

.el-descriptions-item {
  /* display: block; */
  background-color: #f2f2f2;
  margin-right: 20%;
  display: flex;
  align-items: center;
}

.modify-button {
  margin-right: auto;
}
</style>
<style scoped></style>