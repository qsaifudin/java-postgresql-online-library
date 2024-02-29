<template>
  <div class="">
    <NavComponent />
    <v-data-table
      v-model:items-per-page="itemsPerPage"
      :sortable="false"
      :headers="headers"
      :items-length="totalItems"
      :items="serverItems"
      :loading="loading"
      item-value="name"
      @update:options="loadItems"
    >
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title class="text-center">
            Online Library Application Administrator</v-toolbar-title
          >
        </v-toolbar>
      </template>
      <template v-slot:item="{ item, index }">
        <tr>
          <!-- <td>{{ index + 1 }}</td> -->
          <td>{{ item.book.title }}</td>
          <td>{{ item.user.nama }}</td>
          <td>{{ item.borrowedDate }}</td>
          <td>{{ item.returnDate }}</td>
          
          <td>
            <v-chip v-if="item.status == 'returned'" class="ma-2" color="green" label>
              <v-icon start icon="mdi-checkbox-marked-circle"></v-icon>
              Returned
            </v-chip>
            <v-chip v-if="item.status == 'borrowed'" class="ma-2" color="orange" label>
              <v-icon start icon="mdi-bookmark-check"></v-icon>
              Borrowed
            </v-chip>
            <v-chip v-if="item.status == 'expired'" class="ma-2" color="pink" label>
              <v-icon start icon="mdi-close-circle"></v-icon>
              Expired
            </v-chip>
          </td>
        </tr>
      </template>
    </v-data-table>
  </div>
</template>

<script setup>
import { ref } from "vue";

const itemsPerPage = ref(5);
const headers = [
  // { title: "No", key: "index" },
  { title: "Borrower", key: "borrowerName" },
  { title: "Book Title", key: "bookTitle" },
  { title: "Borrowed Date", key: "borrowedDate" },
  { title: "Return Date", key: "returnDate" },
  { title: "Status", key: "statusReturn" },
];
const serverItems = ref([]);
const loading = ref(true);
const totalItems = ref(0);



const loadItems = async ({ page, itemsPerPage, sortBy }) => {
  loading.value = true;
  try {
    const config = useRuntimeConfig();
    const response = await $fetch(config.public.BASE_URL + `/transaction`);
    if (response.status === 200) {
      serverItems.value = response.data;
      totalItems.value = response.data.length;
    } else {
      console.error("Failed to fetch data:", response.message);
    }
  } catch (error) {
    console.error("Error fetching data:", error);
  } finally {
    loading.value = false;
  }
};
</script>
