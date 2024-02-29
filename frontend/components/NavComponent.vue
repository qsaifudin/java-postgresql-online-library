<template>
  <v-tabs bg-color="blue-grey-darken-4" class="mb-3" align-tabs="center">
    <template v-if="!isAdmin">
      <v-tab to="/user"> Dashboard </v-tab>
    </template>
    
    <!-- Show "Borrowing List" tab only if the user is an admin -->
    <template v-if="isAdmin">
      <v-tab to="/admin"> Borrowing List </v-tab>
    </template>

    <v-tab to="/book"> Book List</v-tab>
  </v-tabs>
</template>

<script setup>
// Check if the user is an admin based on role stored in localStorage
const isAdmin = ref(process.client ? localStorage.getItem('role') === 'admin' : false);

console.log("🚀 ~ isAdmin:", isAdmin.value);
</script>