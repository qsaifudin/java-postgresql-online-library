<template>
  <v-app-bar app fixed>
    <v-container class="d-flex" width="100%">
      <v-btn>
        <NuxtLink
          class=""
          to="https://qsaifudin.site/"
          target="_blank"
          style="color: white; text-decoration: none"
        >
          <v-toolbar-title>
            <h3 style="font-size: 30px">👉<span class="animate-text">Saifudin</span></h3>
          </v-toolbar-title>
        </NuxtLink>
      </v-btn>
      <div class="">
        <v-btn class="pt-0 mt-0" href="https://www.linkedin.com/in/qsaifudin/" target="_blank"
          >Linkedin</v-btn
        >
        <v-btn class="pt-0 mt-0" href="https://qsaifudin.site/" target="_blank">Website</v-btn>
        <v-btn class="pt-0 mt-0" href="https://github.com/qsaifudin" target="_blank">Github</v-btn>
      </div>
      <v-spacer></v-spacer>
      <div class="mt-1 mr-5">
        <v-icon start icon="mdi-account-circle"></v-icon>
        {{ user.toUpperCase() }}
      </div>
      <v-btn class="pt-0 mt-0" @click="logout" variant="outlined">Logout</v-btn>
    </v-container>
  </v-app-bar>
</template>

<script>
export default {
  data() {
    return {
      user: "User",
    };
  },
  mounted() {
    // Check if user is already logged in
    this.user = JSON.parse(localStorage.getItem("user")).nama;
  },
  methods: {
    logout() {
      // Remove user data from localStorage
      if (process.client) {
        localStorage.removeItem("user");
        localStorage.removeItem("role");
      }
      navigateTo("/");
    },
  },
};
</script>

<style scoped>
.graph {
  width: 800px;
  height: 600px;
  border: 1px solid #000;
}

.animate-text {
  font-family: "Open Sans";
  font-weight: 800;
  font-size: 30px;
  background: linear-gradient(to right, currentColor 0, #a2ebd58f 10%, currentColor 20%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: borderAnimate 10s infinite alternate;
}

@keyframes borderAnimate {
  0%,
  100% {
    background-position: -100px;
  }

  50% {
    background-position: 510px;
  }
}
</style>
