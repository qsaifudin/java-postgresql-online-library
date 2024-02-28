<template>
  <v-app-bar app fixed>
    <v-container class="d-flex">
      <v-btn >
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
      <v-spacer></v-spacer>
      <div class="">
        <v-btn class="pt-0 mt-0" :loading="false">Linkedin</v-btn>
        <v-btn class="pt-0 mt-0" :loading="false">Website</v-btn>
        <v-btn class="pt-0 mt-0" :loading="false">Github</v-btn>
      </div>
    </v-container>
  </v-app-bar>
</template>
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