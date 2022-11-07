import { EC2 } from './fetchLogin';

export const fetchUploadImage = async (blob) => {
  let formData = new FormData();
  formData.append('file', blob);

  return fetch(`${EC2}/file`, {
    method: 'POST',
    headers: {
      authorization: sessionStorage.getItem('access_token'),
    },
    body: formData,
  })
    .then((response) => {
      if (!response.ok) {
        throw Error('유효하지 않은 요청입니다.');
      } else {
        return response.json();
      }
    })
    .then((data) => {
      return data.data;
    })
    .catch((error) => {
      throw Error(error.message);
    });
};
