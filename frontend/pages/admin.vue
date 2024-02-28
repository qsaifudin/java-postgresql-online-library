<template>
  <div class="">
    <TabsComponent />
    <v-data-table-server
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
          <v-toolbar-title class="text-center"> Online Library Application Administrator</v-toolbar-title>
        </v-toolbar>
      </template>
      <template v-slot:item="{ item, index }">
        <tr>
          <td>{{ index + 1 }}</td>
          <td>{{ item.bookTitle }}</td>
          <td>{{ item.borrowerName }}</td>
          <td>{{ item.borrowedDate }}</td>
          <td>{{ item.returnDate }}</td>
          <td>
            <v-chip v-if="item.statusReturn == 1" class="ma-2" color="green" label>
              <v-icon start icon="mdi-checkbox-marked-circle"></v-icon>
              Returned
            </v-chip>
            <v-chip v-if="item.statusReturn == 2" class="ma-2" color="orange" label>
              <v-icon start icon="mdi-bookmark-check"></v-icon>
              Borrowed
            </v-chip>
            <v-chip v-if="item.statusReturn == 3" class="ma-2" color="pink" label>
              <v-icon start icon="mdi-close-circle"></v-icon>
              Expired
            </v-chip>
          </td>
        </tr>
      </template>
    </v-data-table-server>
  </div>
</template>
<script>
const borrowings = [
  {
    id: 1,
    bookTitle: "Book 1",
    borrowerName: "User 1",
    borrowedDate: "2024-02-27",
    returnDate: "2024-03-15",
    statusReturn: 1,
  },
  {
    id: 2,
    bookTitle: "Book 2",
    borrowerName: "User 2",
    borrowedDate: "2024-02-25",
    returnDate: "2024-03-10",
    statusReturn: 2,
  },
  {
    id: 2,
    bookTitle: "Book 2",
    borrowerName: "User 2",
    borrowedDate: "2024-02-25",
    returnDate: "2024-03-10",
    statusReturn: 3,
  },
  // Add more borrowing records here
];
const FakeAPI = {
  async fetch({ page, itemsPerPage, sortBy }) {
    return new Promise((resolve) => {
      setTimeout(() => {
        const start = (page - 1) * itemsPerPage;
        const end = start + itemsPerPage;
        const items = borrowings.slice();

        if (sortBy.length) {
          const sortKey = sortBy[0].key;
          const sortOrder = sortBy[0].order;
          items.sort((a, b) => {
            const aValue = a[sortKey];
            const bValue = b[sortKey];
            return sortOrder === "desc" ? bValue - aValue : aValue - bValue;
          });
        }

        const paginated = items.slice(start, end);

        resolve({ items: paginated, total: items.length });
      }, 500);
    });
  },
};

export default {
  data: () => ({
    itemsPerPage: 5,
    headers: [
      { title: "No", key: "index" },
      { title: "Book Title", key: "bookTitle" },
      { title: "Borrower", key: "borrowerName" },
      { title: "Borrowed Date", key: "borrowedDate" },
      { title: "Return Date", key: "returnDate" },
      { title: "Status", key: "statusReturn" },
    ],
    serverItems: [],
    loading: true,
    totalItems: 0,
  }),
  methods: {
    itemNumber(item) {
      //   return this.borrowings.indexOf(item) + 1;
    },
    loadItems({ page, itemsPerPage, sortBy }) {
      this.loading = true;
      FakeAPI.fetch({ page, itemsPerPage, sortBy }).then(({ items, total }) => {
        this.serverItems = items;
        this.totalItems = total;
        this.loading = false;
      });
    },
  },
};
</script>
