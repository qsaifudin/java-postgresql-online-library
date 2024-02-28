<template>
  <div class="">
    <TabsComponent />
    <div class="">
      <FormBook />
    </div>
    <v-row >
      <v-col v-for="book in books" :key="book.id" >
    <v-card class="mx-auto" max-width="300" >
      <v-img  class="ma-3" src="/book.svg" height="200px" ></v-img>

      <v-divider></v-divider>
      <v-card-title>{{ book.title }}</v-card-title>
      <v-divider></v-divider>
      <v-card-text class="text-justify">
        {{ book.description }}
      </v-card-text>
      <v-card-text class="pt-0 mt-0">
        <v-btn class="pt-0 mt-0" color="green-lighten-2" :loading="false"> Borrow Book </v-btn>
      </v-card-text>
    </v-card>
  </v-col>
  </v-row>
  </div>
</template>
<script>
export default {
  data: () => ({
    show: false,
    books: [
      {
        id: 1,
        title: "Top western road trips2",
        description:
          "I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.",
        cover: "https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
      },
      {
        id: 2,
        title: "Another Book",
        description:
          "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam euismod, nunc id aliquet tincidunt, nisl nunc tincidunt nunc, id lacinia nunc mi in nunc. Sed id nunc euismod, lacinia nunc nec, aliquam nunc. Nulla facilisi. Nullam auctor, nunc id aliquet tincidunt, nisl nunc tincidunt nunc, id lacinia nunc mi in nunc.",
        cover: "https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
      },
    ],
    loading:false
  }),
  methods: {
    async getBooks() {
      this.loading = true;
      try {
        const config = useRuntimeConfig(); // Make sure useRuntimeConfig is available in Nuxt 3
        console.log("🚀 ~ getBooks ~ config.public.BASE_URL:", config.public)
        const response = await $fetch(config.public.BASE_URL + "/book");
        if (response.status === 200) {
          this.books = response.data;
        }
        this.loading = false;
      } catch (error) {
        console.error("Error :", error);
        this.loading = false;
      }
    },
  },
  async mounted() {
    await this.getBooks();
  },
};
</script>
