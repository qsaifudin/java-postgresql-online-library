<template>
  <div class="">
    <NavComponent />
    <v-snackbar v-model="snackbarSuccess" :timeout="timeout" vertical color="green">
      Book Returned

      <template v-slot:actions>
        <v-btn variant="text" @click="snackbarSuccess = false"> Close </v-btn>
      </template>
    </v-snackbar>
    <div class="text-center mb-2">
      <h3>Borrowed Books</h3>
    </div>
    <div class="text-center" v-if="loadingGetBook">
      <v-progress-circular :size="50" color="primary" indeterminate></v-progress-circular>
    </div>
    <div v-if="borrowedBooks.length > 0">
      <v-row>
        <v-col v-for="(book, index) in borrowedBooks" :key="index">
          <v-card class="mx-auto" max-width="344">
            <v-img class="ma-3" src="/book.svg" height="200px"></v-img>
            <v-card-title>{{ book.book.title }}</v-card-title>
            <v-divider></v-divider>
            <v-card-text class="text-justify">{{ book.book.description }}</v-card-text>
            <v-divider></v-divider>
            <v-card-text>
              Borrowing date: {{ book.transaction.borrowedDate }}<br />
              Last Return date: <mark class="bg-red">{{ book.transaction.returnDate }}</mark>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-text class="text-end">
              <v-btn
                class="pt-0 mt-0"
                color="orange-lighten-2"
                :loading="loadingReturn"
                @click="returnBook(book.transaction.id)"
                >Return</v-btn
              >
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </div>
    <div v-else class="text-center">
      <p>No books borrowed</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      borrowedBooks: [],
      loadingReturn: false,
      loadingGetBook: false,
      snackbarSuccess: false,
      timeout:3000
    };
  },
  async mounted() {
    await this.getBorrowedBooks();
  },
  methods: {
    async getBorrowedBooks() {
      this.loadingGetBook = true;
      try {
        let userId = null;
        if (process.client) {
          userId = JSON.parse(localStorage.getItem("user")).id;
        }
        const config = useRuntimeConfig();
        const response = await $fetch(config.public.BASE_URL + `/transaction/borrowed/${userId}`);
        if (response.status === 200) {
          this.borrowedBooks = response.data;
        } else {
          console.error("Failed to fetch borrowed books:", response.message);
        }
        this.loadingGetBook = false;
      } catch (error) {
        this.loadingGetBook = false;
        console.error("Error fetching borrowed books:", error);
      }
    },
    async returnBook(transactionId) {
      this.loadingReturn = true;
      try {
        const config = useRuntimeConfig();
        const response = await $fetch(
          config.public.BASE_URL + `/transaction/return_book/${transactionId}`,
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
          }
        );
        if (response.status === 200) {
          this.snackbarSuccess = true;
          await this.getBorrowedBooks();
        } else {
          console.error("Failed to return book:", response.message);
        }
        this.loadingReturn = false;
      } catch (error) {
        this.loadingReturn = false;
        console.error("Error returning book:", error);
      }
    },
  },
};
</script>
