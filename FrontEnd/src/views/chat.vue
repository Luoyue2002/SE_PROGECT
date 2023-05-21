<template>
  <div class="chat-container">
    <div class="sidebar">
      <div class="sidebar-header">
        <div class="user-avatar">
          <img :src="this.logourl" alt="User Avatar">
          <div class="user-name">{{this.username}}</div>
        </div>
        <div style="margin-top: 5%">
          <input v-model="searchQuery" placeholder="Search" />
        </div>
      </div>
      <ul class="user-list">
        <li v-for="user in filteredUserList" :key="user.id" :class="{ active: user.id === selectedUser }" @click="selectUser(user.id)">
          <div class="user-avatar">
            <img :src="getUserAvatar(user.id)" :alt="user.name">
          </div>
          <div class="user-details">
            <div class="user-name">{{ user.name }}</div>
            <div class="user-status">{{ user.status }}</div>
          </div>
        </li>
      </ul>
    </div>
    <div class="chat-window">
      <div class="chat-header">
        <div v-if="selectedUser">
          <div class="selected-user">
            <div class="user-avatar">
              <img :src="getUserAvatar(selectedUser)" :alt="getUser(selectedUser).name">
            </div>
            <div class="user-details">
              <div class="user-name">{{ getUser(selectedUser).name }}</div>
              <div class="user-status">{{ getUser(selectedUser).status }}</div>
            </div>
          </div>
        </div>
      </div>
      <div class="messages" ref="messagesContainer">
        <div v-for="message in getSelectedUserMessages" :key="message.id" class="message" :class="{ mine: message.isMine }">
          <div class="message-avatar">
            <img :src="getUserAvatar(message.userId)" :alt="getUser(message.userId).name">
          </div>
          <div class="message-content">
            <div class="message-sender">{{ getUser(message.userId).name }}</div>
            <div class="message-text">{{ message.textcontent }}</div> <!-- 使用 message.textcontent -->
            <div class="message-time">{{ message.timestamp }}</div> <!-- 使用 message.timestamp -->
          </div>
        </div>
      </div>
      <div class="input-area">
        <input v-model="newMessage" placeholder="Type a message">
        <button @click="sendMessage">Send</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      logourl: require('../pic/logo.jpg'),
      username: "bdwq",
      userid: '',
      userList: [],
      selectedUser: null,
      messages: [{
        userid: 1,
        textcontent: "jjj",
        timestamp: "2021"
      }],
      newMessage: '',
      searchQuery: "",
    };
  },
  computed: {
    getSelectedUserMessages() {
      return this.messages.filter((message) => message.userId === this.selectedUser);
    },
    filteredUserList() {
      const query = this.searchQuery.toLowerCase();
      console.log("query"+query)
      if(query == null)
        return this.userList;
      else
        return this.userList.filter((user) => user.name.toLowerCase().includes(query));
    },
  },
  created() {
    this.userid = this.$route.query.userid
    this.username = this.$route.query.username
    this.load();
    setInterval(this.everySecondFunction, 5000); // 每秒触发的函数
  },
  methods: {
    load() {
      axios.get('http://10.162.59.81:8080/chat/get_session?id=' + this.userid).then(res => {
        for (let i = 0; i < res.data.result.length; i++) {
          const user = {
            id: res.data.result[i].id,
            name: res.data.result[i].name,
            avatar: require('../pic/logo.jpg')
          };
          this.userList.push(user);
        }
        console.log(this.userList);
        // this.$route.go(0);
      });
    },
    everySecondFunction() {
      const selectedUser = this.selectedUser;
      console.log("selectedUser:", selectedUser);
      console.log("mess:"+this.messages.length)

      axios.get('http://10.162.59.81:8080/chat/update_message?senderId=' + selectedUser + '&receiverId=' + this.userid)
          .then(res => {
            if (res.data.result && res.data.result.length > 0) {
              const msg = {
                userId: selectedUser,
                textcontent: res.data.result[0].content, // 使用 textcontent
                timestamp: res.data.result[0].time // 使用 timestamp
              };
              console.log(res.data.result[0]);
              this.messages.push(msg);
            }
          })
          .catch(error => {
            console.log(error);
          });
    }
    ,
    selectUser(userId) {
      this.selectedUser = userId;
      this.scrollToBottom();
    },
    getUser(userId) {
      return this.userList.find(user => user.id === userId);
    },
    getUserAvatar(userId) {
      const user = this.getUser(userId);
      return user ? user.avatar : '';
    },
    sendMessage() {
      if (this.newMessage && this.selectedUser) {
        const messageId = this.messages.length + 1;
        const currentTime = new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
        const newMessage = {
          id: messageId,
          content: this.newMessage,
          time: currentTime,
          userId: this.selectedUser,
          isMine: true
        };
        this.messages.push(newMessage);
        this.newMessage = '';
        this.scrollToBottom();
      }
    },
    scrollToBottom() {
      this.$nextTick(() => {
        const container = this.$refs.messagesContainer;
        container.scrollTop = container.scrollHeight;
      });
    },
  },
};
</script>

<style>
/* Add your custom styles here */

.chat-container {
  display: flex;
  height: 100vh;
  font-family: Arial, sans-serif;
}

.sidebar {
  width: 320px;
  background-color: #f0f0f0;
  padding: 20px;
  overflow-y: auto;
}

.sidebar-header {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.user-avatar img {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin-right: 10px;
}

.user-name {
  font-size: 16px;
  font-weight: bold;
}

.search-bar {
  margin-bottom: 20px;
}

.search-bar input {
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 5px;
  background-color: #ffffff;
}

.user-list {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.user-list li {
  display: flex;
  align-items: center;
  padding: 10px;
  cursor: pointer;
}

.user-list li.active {
  background-color: #d3d3d3;
}

.user-avatar {
  margin-right: 10px;
}

.user-details {
  display: flex;
  flex-direction: column;
}

.user-name {
  font-size: 14px;
  font-weight: bold;
}

.user-status {
  font-size: 12px;
  color: #999999;
}

.chat-window {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.chat-header {
  padding: 20px;
  background-color: #f0f0f0;
  border-bottom: 1px solid #d3d3d3;
}

.selected-user {
  display: flex;
  align-items: center;
}

.user-avatar img {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin-right: 10px;
}

.user-details {
  display: flex;
  flex-direction: column;
}

.user-name {
  font-size: 16px;
  font-weight: bold;
}

.user-status {
  font-size: 14px;
  color: #999999;
}

.messages {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

.message {
  display: flex;
  margin-bottom: 10px;
}

.message-avatar {
  width: 40px;
  height: 40px;
}

.message-content {
  background-color: #f0f0f0;
  border-radius: 10px;
  padding: 10px;
  margin-left: 10px;
  flex-grow: 1;
}

.message-sender {
  font-weight: bold;
  margin-bottom: 5px;
}

.message-time {
  font-size: 12px;
  color: #999999;
}

.message.mine {
  flex-direction: row-reverse;
}

.input-area {
  padding: 20px;
  border-top: 1px solid #d3d3d3;
  display: flex;
  align-items: center;
}

.input-area input {
  flex-grow: 1;
  padding: 10px;
  border: 1px solid #d3d3d3;
  border-radius: 5px;
}

.input-area button {
  margin-left: 10px;
  padding: 10px 20px;
  background-color: #4CAF50;
  color: #ffffff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>