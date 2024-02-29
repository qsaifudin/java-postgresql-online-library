<template>
  <div class="">
    <NavComponent />
    <v-snackbar v-model="snackbarSuccess" :timeout="timeout" vertical color="green">
      Success Borrow Book

      <template v-slot:actions>
        <v-btn variant="text" @click="snackbarSuccess = false"> Close </v-btn>
      </template>
    </v-snackbar>
    <v-snackbar v-model="isError" :timeout="timeout" vertical color="pink">
      <v-icon icon="mdi-close-circle"></v-icon> {{ errorContent }}
      <template v-slot:actions>
        <v-btn variant="text" @click="isError = false"> Close </v-btn>
      </template>
    </v-snackbar>
    <!-- <v-alert
      v-if="isError"
      color="#C51162"
      theme="dark"
      icon="mdi-close-circle"
      class="mb-3"
      border
      variant="tonal"
    >
      {{ errorContent }}
    </v-alert> -->
    <v-if v-if="isAdmin">
      <FormBook @runGetBook="getBooks" />
    </v-if>
    <div class="text-center" v-if="loadingGetBook">
      <v-progress-circular :size="50" color="primary" indeterminate></v-progress-circular>
    </div>
    <v-row>
      <v-col v-for="book in books" :key="book.id">
        <v-card class="mx-auto" width="305">
          <v-img class="ma-3" src="/book.svg" height="200px"></v-img>

          <v-divider></v-divider>
          <v-card-title>{{ book.title }}</v-card-title>
          <v-divider></v-divider>
          <v-card-text class="text-justify">
            {{ book.description }}
          </v-card-text>
          <v-card-text class="pt-0 mt-0">
            <!-- Conditionally render "Borrow Book" button based on user role -->
            <template v-if="!isAdmin">
              <v-btn
                class="pt-0 mt-0"
                color="green-lighten-2"
                :loading="loadingBorrow"
                @click="borrowBook(book.id)"
              >
                Borrow Book
              </v-btn>
            </template>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
definePageMeta({
  middleware: ["auth"],
  // or middleware: 'auth'
});
export default {
  data: () => ({
    show: false,
    books: [],
    loadingBorrow: false,
    loadingGetBook: false,
    isAdmin: false,
    isError: false,
    snackbarSuccess: false,
    errorContent: "",
    timeout:3000
  }),
  methods: {
    async getBooks() {
      this.loadingGetBook = true;
      try {
        const config = useRuntimeConfig();
        console.log("🚀 ~ getBooks ~ config.public.BASE_URL:", config.public);
        const response = await $fetch(config.public.BASE_URL + "/book");
        if (response.status === 200) {
          this.books = response.data;
        }
        this.loadingGetBook = false;
      } catch (error) {
        console.error("Error :", error);
        this.loadingGetBook = false;
      }
    },
    async borrowBook(bookId) {
      this.loadingBorrow = true;
      this.isError = false;
      try {
        const config = useRuntimeConfig();
        const userId = JSON.parse(localStorage.getItem("user")).id;
        const response = await $fetch(config.public.BASE_URL + "/transaction", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            bookId,
            userId,
          }),
        });
        if (response.success) {
          console.log("Book borrowed successfully");
          this.snackbarSuccess = true;
        } else {
          this.isError = true;
          this.errorContent = response.message;
          // Handle borrowing failure
          console.error("Failed to borrow book:", response.message);
        }
      } catch (error) {
        this.isError = true;
        console.error("Error borrowing book:", error);
      } finally {
        this.loadingBorrow = false;
      }
    },
  },
  async mounted() {
    // this.$toast.success('Berhasil menyimpan data')
    this.isAdmin = localStorage.getItem("role") === "admin";
    console.log("🚀 ~ mounted ~ this.isAdmin:", this.isAdmin);
    await this.getBooks();
  },
};
</script>
