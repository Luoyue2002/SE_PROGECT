<template>
  <div>
    <el-container direction="vertical" style="position: absolute;overflow-x: hidden;overflow-y: hidden" class="Back">
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

      <el-main>
        <el-tabs v-model="activeName" stretch @tab-click="handlechange">
          <el-tab-pane label="未支付" name="unpaid"></el-tab-pane>
          <el-tab-pane label="已支付" name="payed"></el-tab-pane>
          <el-tab-pane label="已发货" name="send"></el-tab-pane>
          <el-tab-pane label="已收货" name="received"></el-tab-pane>
        </el-tabs>
        <div class="commodity-show-section">
          <div class="el-row" v-for="(row, rowIndex) in rows" :key="rowIndex">
            <div class="el-col el-col-12" v-for="(orderList, index) in row" :key="index">
              <el-card>
                <el-row>
                  <el-col :span="12">
                    <div class="product-image">
                      <img :src="orderList.image" alt="商品图片" />
                    </div>
                  </el-col>
                  <el-col :span="12">
                    <div class="product-info">
                      <el-row>
                        <div class="product-name">{{ orderList.name }}</div>
                      </el-row>
                      <el-row>
                        <el-col :span="12">
                          <div style="font-size: 30px;">￥{{ orderList.price }}</div>
                          <el-input-number v-model="orderList.num" :min="0" :max="99" label="描述文字" @change="handlechange"></el-input-number>
                        </el-col>
                      </el-row>
                    </div>
                  </el-col>
                </el-row>
              </el-card>
            </div>
          </div>
          <el-pagination align='center' @size-change="handleSizeChange" @current-change="handleCurrentChange"
                         :current-page="pagination.currentPage" :page-sizes="[6]" :page-size="pagination.pageSize"
                         layout="total, sizes, prev, pager, next, jumper" :total="productList.length"
                         style="margin-left: 5vh;margin-bottom: 0vh;margin-top: 30px">
          </el-pagination>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "commodityLike",
  data() {
    return {
      logourl: require('../pic/logo.png'),
      itemnum: 0,
      totalprice: 0,
      activeName: "unpaid",
      pagination: {
        currentPage: 1,
        pageSize: 6,
      },
      currentPage: 1,
      orderList: [
        {
          name: '这就是商品1！',
          image: require('../pic/logo.jpg'),
          price: 123,
          num: 1,
          isvalid: true
        }
      ],
    };
  },
  computed: {
    rows() {
      const currentPage = this.pagination.currentPage;
      const pageSize = this.pagination.pageSize;
      const startIndex = (currentPage - 1) * pageSize;
      const endIndex = startIndex + pageSize;
      //console.log("startIndex=",startIndex,"endIndex=",endIndex);
      const slicedProducts = this.productList.slice(startIndex, endIndex);
      //console.log(slicedProducts)
      return Array.from({ length: 3 }, (_, rowIndex) =>
          slicedProducts.slice(rowIndex * 2, rowIndex * 2 + 2)
      );
    },
  },
  created(){
    this.userid = this.$route.query.userid
    this.load()
  },
  methods: {
    load(){
       axios.post('http://localhost:8080/order/getOrderList/userId='+this.userid+'&state='+this.activeName).then(res=>{
          this.orderList.length = 0;
          for(let i = 0;i < res.data.data.length;i++){
            const order = {
              id: res.data.data[i].id,
              buyer: res.data.data[i].buyer,
              price: res.data.data[i].price,
              address: res.data.data[i].address,
              state: res.data.data[i].state,
              time: res.data.data[i].time.replaceAll('T',' ')
            }
          }
       })
    },
    handlechange(tab,event){
      this.load()
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 件`);
      this.pagination.currentPage = 1;
      this.pagination.pageSize = val;
    },
    //当前页改变时触发 跳转其他页
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pagination.currentPage = val;
    },
    handledelete(){
      for(let i = 0;i < this.productList.length;i++){
        if(this.productList[i].isvalid == false){
          this.productList.splice(i,1)
        }
      }
    },
    gotohome() {
      this.$router.push({ name: 'homepage', query: { userid: this.userid, username: this.username } });
    },
    gotostar() {
      this.$router.push({ name: 'commodityLike', query: { userid: this.userid, username: this.username } });
    },
    gotoinfo() {
      this.$router.push({ name: 'userinfo', query: { userid: this.userid, username: this.username } });
    },
    gotochat() {
      this.$router.push({ name: 'chat', query: { userid: this.userid, username: this.username } });
    },
    gotoshoppingcart() {
      this.$router.push({ name: 'cart', query: { userid: this.userid, username: this.username } });
    }
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

.Back {
  background-color: gray;
}
.commodity-show-section{
  margin-left: 10%;
  margin-right: 10%;
  margin-bottom: 5%;
}
.el-card {
  margin-top: 5%;
  margin-right:5%;
}

.product-info {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
  padding: 20px;
}

.product-name {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 50%;
}

.product-price {
  font-size: 20px;
}
.foot-section{
  background-color: white;
}
</style>




<style scoped></style>