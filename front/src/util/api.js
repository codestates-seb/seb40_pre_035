export const BASE_URL = 'http://13.125.238.70:8080';

export const fetchCreateQuestion = async (fetchData) => {
  fetch(`/questions`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization:
        'Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsImlkIjoxLCJ1c2VybmFtZSI6InRlc3QxQHRlc3QuY29tIiwic3ViIjoiMSIsImlhdCI6MTY2NzM5NTY1NCwiZXhwIjoxNjY3Mzk3NDU0fQ.FWl1QzVSYm_jzzhJ-3WSdZ0AMjMShvo9CUxjt-vspO8',
    },
    body: JSON.stringify(fetchData),
  })
    .then((response) => {
      if (!response.ok) {
        throw Error('유효하지 않은 요청입니다.');
      } else {
        window.location.href = '/question/detail/101';
      }
    })
    .catch((error) => {
      throw Error(error.message);
    });
};
