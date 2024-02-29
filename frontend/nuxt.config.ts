export default defineNuxtConfig({
  devtools: { enabled: true },
  modules: ["vuetify-nuxt-module",],
  runtimeConfig: { public: { BASE_URL: process.env.BASE_URL } },
  vuetify: {
    // optionsPath: './vuetify.options.js',
    moduleOptions: {},
    vuetifyOptions: {
      theme: {
        defaultTheme: "dark",
        themes: {},
      },
    },
  },
});
