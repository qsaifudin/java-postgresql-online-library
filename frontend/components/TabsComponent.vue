<template>
  <v-tabs bg-color="blue-grey-darken-4" class="mb-3"  align-tabs="center">
    <v-tab to="/user"> Dashboard </v-tab>
    <v-tab to="/admin"> Borrowing List </v-tab>

    <v-tab to="/book"> Book List</v-tab>
  </v-tabs>
  <!-- <div>
    <v-breadcrumbs
      :items="items"
      divider="-"
    ></v-breadcrumbs>

  </div> -->
</template>

<script>
  export default {
    data: () => ({
      items: [
        {
          title: 'Dashboard',
          
          href: '/user',
        },
        {
          title: 'Link 1',
          
          href: '/admin',
        },
        {
          title: 'Link 2',
          
          href: '/book',
        },
      ],
    }),
  }
</script>