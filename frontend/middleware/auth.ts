// // middleware/auth.js
export default defineNuxtRouteMiddleware((to, from) => {
  // If user data is not available in localStorage, redirect to login page
  // skip middleware on server
  if (process.server) return;

  if (!localStorage.getItem("user")) {
    console.log("---------");
    return navigateTo("/login");
  }
});
