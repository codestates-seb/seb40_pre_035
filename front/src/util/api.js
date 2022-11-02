export const BASE_URL = 'http://13.125.238.70:8080';

export const fetchUploadImage = async (blob) => {
  let formData = new FormData();
  formData.append('file', blob);

  fetch(`/file`, {
    method: 'POST',
    headers: {
      'Content-Type':
        'multipart/form-data;boundary=6o2knFse3p53ty9dmcQvWAIx1zInP11uCfbm',
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
      return data.data;
    })
    .catch((error) => {
      throw Error(error.message);
    });
};

export const fetchCreateQuestion = async (fetchData) => {
  fetch(`/questions`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization:
      'Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsImlkIjoxLCJ1c2VybmFtZSI6InRlc3QxQHRlc3QuY29tIiwic3ViIjoiMSIsImlhdCI6MTY2NzQwMzM3OSwiZXhwIjoxNjY3NDA1MTc5fQ.tBnUxVdRDNw0Pjwkg-hIuMpy1PuFVbjec6vdZglS0dk',
    },
    body: JSON.stringify(fetchData),
  })
    .then((response) => {
      console.log(response);
      if (!response.ok) {
        throw Error('유효하지 않은 요청입니다.');
      } else {
        return response.json();
      }
    })
    .then((data) => {
      window.location.href = `/question/detail/${data.id}`;
    })
    .catch((error) => {
      throw Error(error.message);
    });
};
