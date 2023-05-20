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
                  <el-input v-model="user.username" readonly>
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
                      <el-button icon="el-icon-edit" @click="editUsername">重置密码</el-button>
                    </template>
                  </el-input>
                </el-form-item>


              </el-form>
            </el-main>
          </el-col>
        </el-row>

    </el-container>


    <!-- 修改信息对话框 -->
    <el-dialog title="修改用户名" :visible.sync="editDialogVisible.username" width="30%">
      <el-input v-model="editDialogData.username" placeholder="请输入新的用户名"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible.username = false">取消</el-button>
        <el-button type="primary" @click="saveEditedUsername">保存</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "userinfo",
  data() {
    return {
      logourl: require("../pic/logo.jpg"),
      user_profile: require("../assets/logo.png"),
      user:{
        username:"123",
        account:"12345",
        password:"***",
        phoneNumber:"1234",
      },
      editDialogVisible:{
        username:false,

      },
      editDialogData:{
        username:"123788",

      }
        
    
    };
  },
  methods:{
    editUsername() {
      this.editDialogData.username = this.user.username;
      this.editDialogVisible.username = true;
    },
    saveEditedUsername() {
      // http
      this.user.username = this.editDialogData.username;
      this.editDialogVisible.username = false;
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
<style scoped>


</style>