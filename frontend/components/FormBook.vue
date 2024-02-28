<template>
  <div class="pa-4 text-center">
    <v-dialog v-model="dialog" max-width="600" persistent>
      <template v-slot:activator="{ props: activatorProps }">
        <v-btn
          size="large"
          class="text-none font-weight-regular px-12"
          prepend-icon="mdi-book"
          text="Add Book"
          color="primary"
          v-bind="activatorProps"
        ></v-btn>
      </template>

      <v-card prepend-icon="mdi-book" title="Add Book">
        <v-card-text>
          <v-row dense>
            <v-col>
              <v-text-field v-model="title" label="Title" required></v-text-field>
              <v-textarea
                v-model="description"
                variant="filled"
                auto-grow
                label="Description"
                rows="2"
                row-height="20"
              ></v-textarea>
              <v-file-input
                @change="onChangeCover"
                :rules="rules"
                accept="image/png, image/jpeg, image/bmp"
                label="Input Cover"
                prepend-icon="mdi-camera"
              ></v-file-input>
            </v-col>
          </v-row>
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn text="Close" variant="plain" @click="dialog = false"></v-btn>

          <v-btn color="primary" text="Save" variant="tonal" @click="saveBook"></v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
export default {
  data: () => ({
    dialog: false,
    title: "",
    description: "",
    cover: null,
    rules: [(value) => !!value || "Cover is required"],
  }),
  methods: {
    convertToBase64(file) {
      return new Promise(function (resolve, reject) {
        let reader = new FileReader();
        let imgResult = "";
        reader.readAsDataURL(file);
        reader.onload = function () {
          imgResult = reader.result;
        };
        reader.onerror = function (error) {
          reject(error);
        };
        reader.onloadend = function () {
          resolve(imgResult);
        };
      });
    },
    onChangeCover(file) {
      console.log("🚀 ~ onChangeCover ~ file:", file)
      this.convertToBase64(file).then((res) => {
        this.cover = res;
      });
    },
    async saveBook() {
      try {
        const config = useRuntimeConfig();
        console.log("🚀 ~ saveBook ~ config:", config);

        const response = await fetch(config.public.BASE_URL + "/book", {
          method: "POST",
          body: {
            title: this.title,
            title: this.description,
            cover: this.cover,
          },
        });
        if (response.ok) {
          // Book saved successfully
          // Optionally, you can handle further actions like closing the dialog or refreshing the book list
          this.dialog = false;
        } else {
          console.error("Failed to save book");
        }
      } catch (error) {
        console.error("Error :", error);
      }
    },
  },
};
</script>
