export const fetchUploadImage = async (blob) => {
  let formData = new FormData();
  formData.append('file', blob);

  return fetch(`/file`, {
    method: 'POST',
    headers: {
      authorization: sessionStorage.getItem('access_token'),
    },
    body: formData,
  })
    .then((response) => {
      console.log(response.ok);
      if (!response.ok) {
        throw Error('유효하지 않은 요청입니다.');
      } else {
        return response.json();
      }
    })
    .then((data) => {
      console.log(data);
      return data.data;
    })
    .catch((error) => {
      throw Error(error.message);
    });
};
