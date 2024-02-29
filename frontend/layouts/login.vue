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
        <v-btn class="pt-0 mt-0" href="https://www.linkedin.com/in/qsaifudin/" target="_blank">Linkedin</v-btn>
        <v-btn class="pt-0 mt-0" href="https://qsaifudin.site/" target="_blank">Website</v-btn>
        <v-btn class="pt-0 mt-0" href="https://github.com/qsaifudin" target="_blank">Github</v-btn>
      </div>
      <v-spacer></v-spacer>
    </v-container>
  </v-app-bar>
</template>

<script>
export default {
  data() {
    return {
      isAuthenticated: false,
    };
  },
  mounted() {
    // Check if user is already logged in
    this.isAuthenticated = localStorage.getItem('user') !== null;

    // Add an event listener for changes to the localStorage
    window.addEventListener('storage', this.handleStorageChange);
  },
  beforeUnmount() {
    // Remove the event listener when the component is unmounted
    window.removeEventListener('storage', this.handleStorageChange);
  },
  methods: {
    logout() {
      // Remove user data from localStorage
      if (process.client) {
      localStorage.removeItem('user');
      localStorage.removeItem('role');
      }
      // Set authentication status to false
      this.isAuthenticated = false;

      // Navigate to home page
      navigateTo('/');
    },
    handleStorageChange(event) {
      // Check if the 'user' key in localStorage is changed
      if (event.key === 'user') {
        // Update isAuthenticated based on the new value of 'user'
        this.isAuthenticated = event.newValue !== null;
      }
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
  font-family: 'Open Sans';
  font-weight: 800;
  font-size: 30px;
  background: linear-gradient(to right,
      currentColor 0,
      #a2ebd58f 10%,
      currentColor 20%);
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
