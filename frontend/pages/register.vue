<template>
  <div>
    <v-card class="mx-auto pa-12 pb-8" elevation="8" max-width="448" rounded="lg">
      <div class="text-subtitle-1 text-medium-emphasis">Account</div>

      <v-text-field
        v-model="nama"
        density="compact"
        placeholder="Name"
        prepend-inner-icon="mdi-account-outline"
        variant="outlined"
      ></v-text-field>
      <v-text-field
        v-model="email"
        density="compact"
        placeholder="Email address"
        prepend-inner-icon="mdi-email-outline"
        variant="outlined"
      ></v-text-field>

      <!-- Password Input Field -->
      <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
        Password
      </div>
      <v-text-field
        v-model="password"
        :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'"
        :type="visible ? 'text' : 'password'"
        density="compact"
        placeholder="Enter your password"
        prepend-inner-icon="mdi-lock-outline"
        variant="outlined"
        @click:append-inner="visible = !visible"
      ></v-text-field>

      <v-alert
        v-if="isError"
        color="#C51162"
        theme="dark"
        icon="mdi-close-circle"
        class="mb-3"
        border
        variant="tonal"
      >
        {{ errorContent }}
      </v-alert>

      <!-- Login Button -->
      <v-btn block class="mb-8" color="blue" size="large" @click="loginAction" :loading="loading">
        Register
      </v-btn>

      <!-- Sign up link -->
      <v-card-text class="text-center">
        <v-btn block variant="text"  color="blue" size="large" @click="navigateTo('login')" :loading="loading">
        Login
      </v-btn>
      </v-card-text>
    </v-card>
  </div>
</template>
<script setup>
definePageMeta({
  layout: 'login'
});

const visible = ref(false);
const nama = ref('');
const email = ref('');
const password = ref('');
const loading = ref(false);
const isError = ref(false);
const errorContent = ref('');

const loginAction = async () => {
  // Send login request to the server
  loading.value = true;
  const config = useRuntimeConfig();
  try {
    const response = await $fetch(`${config.public.BASE_URL}/user/register`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        nama: nama.value,
        email: email.value,
        password: password.value,
      }),
    });

    // Handle successful login
    console.log("1111111");
    if (response.success) {
      console.log("🚀 ~ login ~ response:", response);

      navigateTo("login");
    } else {
      console.log("222222");
      isError.value = true;
      errorContent.value = response.message;
      console.log("🚀 ~ login ~ response:", response);
      console.log("Login failed:", response.message);
    }
  } catch (error) {
    console.log("3333333333");
    isError.value = true;
    errorContent.value = error.message;
    console.error("Login failed:", error);
  } finally {
    console.log("4444444444");
    loading.value = false;
  }
};
</script>
