export const BASE_URL = 'http://13.125.238.70:8080';

export const fetchUploadImage = async (data) => {
  fetch(`${BASE_URL}/file`, {
    method: 'POST',
    headers: { 'Content-type': 'multipart/form-data' },
    data: data,
  })
    .then((response) => {
      console.log(response);
      // if (response.data) {
      //   return response.json();
      // }
    })
    .catch((error) => {
      console.log(error);
    });
};
