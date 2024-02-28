<template>
  <div class="">
    <TabsComponent />
    <div class=" text-center mb-2">
      <h3>
        Borrowed Book
      </h3>
    </div>
    <v-card class="mx-auto" max-width="344">
      <v-img src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg" height="200px" cover></v-img>

      <v-card-title> Top western road trips </v-card-title>
      <v-divider></v-divider>
      <v-card-text class="text-justify">
        I'm a thing. But, like most politicians, he promised more than he could deliver. You won't
        have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go
        with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do
        his laundry? I've got to find a way to escape. class="text-none font-weight-regular
      </v-card-text>
      <v-divider></v-divider>
      <v-card-text>
        Borrowing date : 12/03/2024 <br />
        Last return date : <mark class="bg-red" > 15/03/2024</mark>
      </v-card-text>
      <v-divider></v-divider>
      <v-card-text class=" text-end">
        <v-btn class="pt-0 mt-0" color="orange-lighten-2"> Return</v-btn>
      </v-card-text>
    </v-card>
  </div>
</template>
<script>
export default {
  data: () => ({
    show: false,
  }),
};
</script>
