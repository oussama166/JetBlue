/** @type {import('tailwindcss').Config} */
module.exports = {
  important: true,
  content: ["./src/**/*.{html,ts}"],
  theme: {
    extend: {
      fontFamily: {
        Lexend: ["Lexend", "sans-serif"],
        JBKlarheit: ["JBKlarheit", "sans-serif"],
      },
      backgroundImage: {
        reservation: "url('assets/images/Background/Palm-Trees.jpg')",
        signIn: "url('assets/images/Background/bg-login.svg')",
      },
      colors: {
        hero: "rgb(0, 32, 91)",
        jetBlue: {
          200: "#0033a0",
          300: "#9fc7f3",
          400: "#002778",
          500: "#0033a0",
          600: "#001A50",
          "mint": "#6cc24a"
        },
        jetblueText: {
          500: "#303234",
          600: "#FFFFFF",
        },
      },
      listStyleType: {
        none: "none",
        disc: "disc",
        decimal: "decimal",
        square: "square",
        roman: "upper-roman",
      },
    },
  },
  plugins: [],
};
