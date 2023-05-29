<template>
  <div class="shopstyle">
    <el-container direction="vertical" style="position: absolute;overflow-x: hidden;overflow-y: hidden">
      <el-header>
        <el-row>
          <el-col :span="1" class="lightgreen-box"><img :src="logourl" alt="qiushi" style="display: block;height: 8vh;margin-left: 0px"></img></el-col>
          <el-col :span="18" class="orange-box"><h5 style="display: block;font-size: x-large;margin-top: 3vh;margin-left: 1vh;color: black">qiushi</h5></el-col>
          <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-s-home" style="height: 5vh;margin-top: 3vh;color: white" @click="gotohome" type="text"></el-button></el-col>
          <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-chat-dot-round" style="height: 5vh;margin-top: 3vh;color: white" @click="gotochat" type="text"></el-button></el-col>
          <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-star-on" style="height: 5vh;margin-top: 3vh;color: white" @click="gotostar" type="text"></el-button></el-col>
          <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-shopping-cart-full" style="height: 5vh;margin-top: 3vh;color: white" @click="gotoshoppingcart" type="text"></el-button></el-col>
          <el-col :span="1" class="lightgreen-box"><el-button icon="el-icon-s-custom" style="height: 5vh;margin-top: 3vh;color: white" @click="gotoinfo" type="text"></el-button></el-col>
        </el-row>
      </el-header>

      <el-main>
        <el-row :gutter="24">
          <el-col :span="7"></el-col>
          <el-col :span="3" style="margin-left: 50vh">
            <div class="demo-fit" style="margin-top: 2vh" >
              <div class="block">
                <span class="title">{{ fit }}</span>
                <el-avatar shape="square" :size="150" :fit="fit" :src="url"></el-avatar>
              </div>
            </div>
          </el-col>

          <el-col :span="9" style="margin-top: 2vh">用户名</el-col>
          <el-col :span="9" style="margin-top: 1vh">评价</el-col>
          <el-col :span="9" style="margin-top: 1vh">商铺</el-col>
          <el-col :span="9" style="margin-top: 1vh"><el-button type="primary" style="background: red">发布商品</el-button></el-col>
        </el-row>

        <el-row :gutter="24">
        <div class="card" v-for="message in items" :key="message.id" v-bind:title="message.title" v-bind:desc="message.desc">
          <el-col :span="6">
          <el-card id="app" style="width: 40vh;height: 20vh;margin-top: 5vh">
            <el-image class="img" :src="message.src" fit="fill"><br>{{message.desc}} </el-image>
          </el-card></el-col>
        </div></el-row>

        <el-footer>
          <el-pagination align='center'
                         @size-change="handleSizeChange"
                         @current-change="handleCurrentChange"
                         :current-page="currentPage"
                         :page-sizes="[1,5,10,20]"
                         :page-size="pageSize"
                         layout="total, sizes, prev, pager, next, jumper"
                         :total="items.length"
          style="margin-left: 5vh;margin-bottom: 0vh">
          </el-pagination>
        </el-footer>
      </el-main>
    </el-container>
    <el-main>

    </el-main>
  </div>
</template>

<script>
export default {
  name: "shop",
  data(){
    return{
      logourl: require('../pic/logo.jpg'),
      fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      items:[
        {
          id: '1',
          title: '1',
          desc: '这就是商品1！',
          src: require('../pic/logo.jpg')
        },
        {
          id: '1',
          title: '1',
          desc: '这就是商品2！',
          src: require('../pic/logo.jpg')
        },
        {
          id: '1',
          title: '1',
          desc: '这就是商品3！',
          src: require('../pic/logo.jpg')
        },
        {
          id: '1',
          title: '1',
          desc: '这就是商品4！',
          src: require('../pic/logo.jpg')
        },
        {
          id: '1',
          title: '1',
          desc: '这就是商品5！',
          src: require('../pic/logo.jpg')
        },
        {
          id: '1',
          title: '1',
          desc: '这就是商品6！',
          src: require('../pic/logo.jpg')
        },
      ],
      currentPage: 1, // 当前页码
      total: 20, // 总条数
      pageSize: 8
    }
  },
  methods:{
    handleSizeChange(val) {
      console.log(`每页 ${val} 件`);
      this.currentPage = 1;
      this.pageSize = val;
    },
    //当前页改变时触发 跳转其他页
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
    }
  }

}
</script>

<style>
#app{
  width: 100%;
  display: flex;
  flex-flow: row wrap;
  align-content: flex-start;
}
.card{
  /*height: 300px;*/
  background-color: #ffffff;
  padding: auto;
  margin: auto;
}
.card .img{
  /*height: 250px;*/
  height: auto;
  vertical-align: middle;
  max-width: 100%;
}
body{
  margin-top:0;
  padding:0;
  overflow-x: hidden;
}
.el-header{
  height: 50vh;
  background: -webkit-linear-gradient(left,white,black);
  overflow-y: hidden;
  overflow-x: hidden;
}
.el-container{
  width: 100%;
  overflow-x: hidden;
}
</style>
<style scoped>
.shopstyle{
  width: 100%;
  height: 100%;
  margin-top: 0px;
  overflow-x: hidden;
};
</style>