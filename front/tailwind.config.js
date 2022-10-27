/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./src/**/*.{js, jsx, html}'],
  theme: {
    extend: {},
    colors: {
      transparent: 'transparent',
      current: 'currentColor',
      primary: {
        100: '#fce3cf',
        150: '#fbd3b3',
        200: '#f9be8e',
        300: '#f8ae71',
        350: '#f69B51',
        400: '#f48225',
        500: '#f2740d',
        600: '#da680b',
        700: '#bd5a0a',
        800: '#a54f09',
        900: '#8d4307',
      },
      secondary: {
        100: '#C8E0F4',
        150: '#A5CFED',
        200: '#7AB7E4',
        300: '#58A4DE',
        350: '#348FD5',
        400: '#0074CC',
        500: '#0065B2',
        600: '#005799',
        700: '#00457A',
        800: '#013761',
        900: '#002847',
      },
    },
  },
  plugins: [],
};
