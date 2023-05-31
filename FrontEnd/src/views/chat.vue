<template>
  <div class="jwchatstyle">
    <el-container direction="vertical" style="position: absolute;overflow-x: hidden;overflow-y: hidden">
      <el-header style="margin-left: 0px">
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
      <el-main class="jwchat">
        <!--
  v-model	输入框中的文字	String	-	""
  taleList	会话内容	Array	-	[]
  toolConfig	工具栏配置	Object	-	{}
  width	JwChat界面框宽度	string	-	750px
  height	JwChat界面框高度	string	-	570px
  config	组件配置	Object	-	{}
  scrollType	消息自动到低	String	scroll	noroll
  showRightBox	显示右边内容	Boolean	false	true
  winBarConfig	多窗口配置
  quickList   自动匹配快捷回复
  @enter	输入框点击就发送或者回车触发的事件	输入的原始数据
  @clickTalk	点击聊天框列中的用户和昵称触发事件	当前对话数据
 -->
        <JwChat-index
            v-model="inputMsg"
            :taleList="taleList"
            :config="config"
            :showRightBox="true"
            scrollType="scroll"
            :winBarConfig="winBarConfig"
            :quickList="config.quickList"
            @enter="bindEnter"
            @clickTalk="talkEvent"
            height="92vh"
            width="198vh"
        >
          <!-- 窗口右边栏 -->
          <JwChat-rightbox :config="rightConfig" @click="rightClick" />
          <!-- 快捷回复 -->
          <!-- <JwChat-talk :Talelist="talk" :config="quickConfig" @event="bindTalk" /> -->
          <!-- 工具栏自定义插槽 -->
          <template slot="tools">
            <div style="width: 20rem; text-align: right" @click="toolEvent(12)">
              <JwChat-icon type="icon-lishi" title="自定义" />
            </div>
          </template>
        </JwChat-index>
      </el-main>
    </el-container>


  </div>
</template>

<script>
import axios from "axios";

const img = "https://www.baidu.com/img/flexible/logo/pc/result.png";
const listData = [
];

export default {
  components: {},
  data() {
    return {
      logourl: require('../pic/logo.png'),
      username: "留恋人间不羡仙",
      userid: '',
      // 输入框中的文字
      inputMsg: "",
      // 会话内容
      taleList: [],
      // 工具栏配置
      tool: {
        // show: ['file', 'history', 'img', ['文件1', '', '美图']],
        // showEmoji: false,
        callback: this.toolEvent,
      },
      // 组件配置
      config: {
        img: require('../pic/logo.jpg'),
        name: "menci777",
        dept: "spider man",
        callback: this.bindCover,
        historyConfig: {
          show: true,
          tip: "你已经来到消息的荒原",
          callback: this.bindLoadHistory,
        },
        // 自动匹配快捷回复
        quickList: [
          { text: "这里是jwchat，您想了解什么问题。", id: 3 },
          { text: "jwchat是最好的聊天组件", id: 4 },
          { text: "谁将烟焚散，散了纵横的牵绊；听弦断，断那三千痴缠。", id: 5 },
          { text: "长夏逝去。山野间的初秋悄然涉足。", id: 6 },
          { text: "江南风骨，天水成碧，天教心愿与身违。", id: 7 },
          { text: "总在不经意的年生。回首彼岸。纵然发现光景绵长。", id: 8 },
          { text: "外面的烟花奋力的燃着，屋里的人激情的说着情话", id: 10 },
          { text: "假如你是云，我就是雨，一生相伴，风风雨雨；", id: 11 },
          {
            text: "即使泪水在眼中打转，我依旧可以笑的很美，这是你学不来的坚强。",
            id: 12,
          },
          {
            text: " 因为不知来生来世会不会遇到你，所以今生今世我会加倍爱你。",
            id: 13,
          },
        ],
      },
      // 多窗口配置
      winBarConfig: {
        active: "win00", // 当前选中
        width: "160px", // 宽度大小
        listHeight: "60px", // 单个高度
        // 用户列表
        list: [
          {
            id: this.userid,
            img: require('../pic/logo.jpg'),
            name: "menci777",
            dept: "spider man",
            readNum: 0,
          },
        ],
        callback: this.bindWinBar,
      },
      // 窗口右边栏配置
      rightConfig: {
        tip: "个人信息",
        notice:
            "此处计划放置个啥",
        listTip: "当前在线",
        list: [
          {
            name: "menci777",
            img: require('../pic/logo.jpg'),
            id: 1,
          },
          {
            name: "menci666",
            img: require('../pic/logo.jpg'),
            id: 1,
          },
        ],
      },
      // 快捷回复配置
      talk: [
        "快捷回复1",
        "快捷回复2",
        "快捷回复3",
        "快捷回复4",
        "快捷回复5",
        "快捷回复6",
      ],
      quickConfig: {
        nav: ["快捷回复", "超级回复"],
        showAdd: true,
        maxlength: 200,
        showHeader: true,
        showDeleteBtn: true,
      },
      rightConfig2: {
        listTip: "当前在线",
        // notice: '【公告】这是一款高度自由的聊天组件，基于AVue、Vue、Element-ui开发。点个赞再走吧 ',
        list: [
          {
            name: "JwChat",
            img: "https://img1.baidu.com/it/u=2109725846,3376113789&fm=26&fmt=auto&gp=0.jpg",
          },
          {
            name: "留恋人间不羡仙",
            img: "https://img1.baidu.com/it/u=31094377,222380373&fm=26&fmt=auto&gp=0.jpg",
          },
          {
            name: "只盼流星不盼雨",
            img: "https://img1.baidu.com/it/u=2109725846,3376113789&fm=26&fmt=auto&gp=0.jpg",
          },
        ],
      },
    };
  },
  created() {
    this.userid = this.$route.query.userid
    // this.username = this.$route.query.username
    this.winBarConfig.active = "win00"
    this.load();
    // setInterval(this.everySecondFunction, 5000); // 每秒触发的函数
  },
  methods: {
    load() {
      // console.log(this.userid)
      // axios.get('http://localhost:8080/chat/getSession?id=1' + this.userid).then(res => {
      //   for (let i = 0; i < res.data.data.length; i++) {
      //     const user = {
      //       id: res.data.data[i].id,
      //       name: res.data.data[i].name,
      //       img: require('../pic/logo.jpg'),
      //       dept: "个人求按摩"
      //     };
      //     console.log("123")
      //     this.winBarConfig.list.push(user);
      //   }
      // });
      axios.get('http://10.162.59.81:8080/chat/getSession?id=' + this.userid).then(res => {
        for (let i = 0; i < res.data.data.length; i++) {
          let readnum = 0;
          const user = {
            id: res.data.data[i].id,
            name: res.data.data[i].name,
            img: require('../pic/logo.jpg'),
            dept: "个人求按摩",
          };
          this.winBarConfig.list.push(user);
          axios.get('http://10.162.59.81:8080/chat/unreadMessageCount?senderId='+user.id+'&receiverId='+this.userid).then(res => {
            console.log(i)
            console.log("id:"+user.id+"   count:"+res.data.data)
            console.log("size"+this.winBarConfig.list.length)
            this.winBarConfig.list[1].readNum = res.data.data
          })
        }
      });
      // console.log("length:"+this.winBarConfig.list[0].id)
      // for(let i = 0;i < this.winBarConfig.list.length;i++){
      //
      // }
    },
    everySecondFunction() {
      const selectedUser = this.selectedUser;
      axios.get('http://10.162.59.81:8080/chat/updateMessage?senderId=' + selectedUser + '&receiverId=' + this.userid)
          .then(res => {
            if (res.data.result && res.data.result.length > 0) {
              const msg = {
                name: selectedUser,
                text: res.data.result[0].content, // 使用 textcontent
                date: res.data.result[0].time, // 使用 timestamp
                mine: false,
                // id: this.num++,
                img: require('../pic/logo.jpg')
              };
              listData.push(msg);
            }
          })
          .catch(error => {
            console.log(error);
          });
    },
    getListArr(size) {
      const listSize = listData.length;
      console.log("size"+listSize)
      if (!size) {
        size = listSize;
      }
      let result = [];
      for(let i = 0;i < listSize;i++){
        if(listData[i].name == this.username){
          result.push(listData[i]);
        }
      }
      return result;
    },

    // 切换用户窗口，加载对应的历史记录
    bindWinBar(play = {}) {
      console.log("play"+play)
      this.taleList.length = 0
      const { type, data = {} } = play;
      console.log(play);
      if (type === "winBar") {
        // console.log("id:"+id)
        const { id, dept, name, img } = data;
        this.config = { ...this.config, id, dept, name, img };
        this.winBarConfig.active = id;
        listData.length = 0;
        if (id === this.userid) {
          this.taleList = this.getListArr();
        }
        else {
          axios.get('http://10.162.59.81:8080/chat/retrieveAllMessage?senderId=' + id + '&receiverId=' + this.userid)
              .then(res => {
                for (let i = res.data.data.length - 1; i >= 0; i--) {
                  let tempname = '';
                  for(let j = 0;j < this.winBarConfig.list.length;j++){
                     if(this.winBarConfig.list[j].id == res.data.data[i].senderId){
                       tempname = this.winBarConfig.list[j].name;
                       break;
                     }
                  }
                  const msg = {
                    name: tempname,
                    text: {text:res.data.data[i].content}, // 使用 textcontent
                    date: res.data.data[i].time.replace('T',' '), // 使用 timestamp
                    mine: res.data.data[i].receiverId == this.userid ? false : true,
                    img: require('../pic/logo.jpg')
                  };
                  // console.log(msg.text)
                  // listData.push(msg);
                  this.taleList.push(msg)
                  console.log("list"+msg.text)
                }
              })
              .catch(error => {
                console.log(error);
              });
          // this.taleList = this.getListArr();
        }
      }
      if (type === "winBtn") {
        const { target: { id } = {} } = data;
        const { list } = this.winBarConfig;
        this.winBarConfig.list = list.reduce((p, i) => {
          if (id != i.id) p.push(i);
          return p;
        }, []);
      }
    },
    // 点击聊天框列中的用户和昵称触发事件
    talkEvent(play) {
      console.log(play);
    },
    // 输入框点击就发送或者回车触发的事件
    bindEnter(e) {
      const id = this.winBarConfig.active
      const msg = this.inputMsg;
      if (!msg) return;
      const currentTime = new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
      const msgObj = {
        date: currentTime,
        text: { text: msg },
        mine: true,
        name: this.username,
        img: require('../pic/logo.jpg'),
      };
      axios.post('http://10.162.59.81:8080/chat/sendMessage?senderId='+this.userid+'&receiverId='+id+'&content='+msg).then(res=>{
        this.taleList.push(msgObj)
      })
      this.newMessage = '';
      this.scrollToBottom();
    },
    // 快捷回复，组件点击选中事件
    bindTalk(play) {
      console.log("talk", play);
      const { key, value } = play;
      if (key === "navIndex" && value == "1") {
        this.talk = ["回复1", "回复2", "回复3"];
      }
      if (key === "navIndex" && value == "2") {
        this.talk = ["超级回复1", "超级回复2", "超级回复3"];
      }
      if (key === "select") {
        this.inputMsg = value;
        // this.bindEnter();
      }
      if (key === "delIndex") {
        this.talk.splice(value, 1);
      }
    },
    /**
     * @description: 点击加载更多的回调函数
     * @param {*}
     * @return {*}
     */
    bindLoadHistory() {
      const history = new Array(3).fill().map((i, j) => {
        return {
          date: "2020/05/20 23:19:07",
          text: { text: j + new Date() },
          mine: false,
          name: "JwChat",
          img: "https://img1.baidu.com/it/u=31094377,222380373&fm=26&fmt=auto&gp=0.jpg",
        };
      });
      let list = history.concat(this.list);
      this.taleList = list;
      console.log("加载历史", list, history);
    },
    /**
     * @description:
     * @param {*} type 当前点击的按钮
     * @param {*} plyload 附加文件或者需要处理的数据
     * @return {*}
     */
    toolEvent(type, plyload) {
      console.log("tools", type, plyload);
    },
    bindCover(event) {
      console.log("header", event);
    },
    rightClick(type) {
      console.log("rigth", type);
    },
    gotohome(){
      this.$router.push({name:'homepage',query:{userid : this.userid,username: this.username}});
    },
    gotostar() {
      this.$router.push({name:'commoityLike',query:{userid : this.userid,username: this.username}});
    },
    gotoinfo() {
      this.$router.push({name:'userinfo',query:{userid : this.userid,username: this.username}});
    },
    gotochat(){
      this.$router.push({name:'chat',query:{userid : this.userid,username: this.username}});
    },
    gotoshoppingcart() {
      this.$router.push({name:'cart',query:{userid : this.userid,username: this.username}});
    }
  },
  // mounted() {
  //   this.taleList = getListArr();
  // },
};
</script>

<style>
.jwchat {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 0px;
  padding: 0px;
}
.active{
  color: red
}
#app{
  width: 100%;
  display: flex;
  flex-flow: row wrap;
  align-content: flex-start;
}
body{
  margin:0;
  padding:0;
  overflow-x: hidden;
}
.el-header{
  height: 50vh;
  margin-left: 0px;
  /*background: -webkit-linear-gradient(left,#d3dce6,white);*/
  background-color: black;
  overflow-y: hidden;
  overflow-x: hidden;
}
.el-container{
  width: 100%;
  overflow-x: hidden;
}
.el-main{
  width: 100%;
  overflow-x: hidden;
  overflow-y: hidden;
}
.el-button{
    font-size: 20px;
}
</style>


<style scoped>
.jwchatstyle{
  width: 100%;
  height: 100%;
  margin-top: 0px;
  overflow-x: hidden;
  margin-left: 0px;
};
</style>