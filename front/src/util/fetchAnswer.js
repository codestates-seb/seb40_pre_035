export const fetchAnswerList = async (id) => {
  return fetch(`/answers/question/${id}?size=100`)
    .then((response) => {
      if (!response.ok) {
        throw Error('유효하지 않은 요청입니다.');
      }
      return response.json();
    })
    .catch((error) => {
      throw Error(error.message);
    });
};

export const fetchCreateAnswer = async (fetchData) => {
  return fetch(`/answers`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      authorization: sessionStorage.getItem('access_token'),
    },
    body: JSON.stringify(fetchData),
  })
    .then((response) => {
      if (!response.ok) {
        throw Error('유효하지 않은 요청입니다.');
      }
      return response.json();
    })
    .catch((error) => {
      throw Error(error.message);
    });
};

export const fetchDeleteAnswer = async (id) => {
  return fetch(`/answers/${id}`, {
    method: 'DELETE',
    headers: {
      authorization: sessionStorage.getItem('access_token'),
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw Error('유효하지 않은 요청입니다.');
      }
      return response.json();
    })
    .then((data) => {
      return data;
    })
    .catch((error) => {
      throw Error(error.message);
    });
};

export const fetchSelectAnswer = async (id) => {
  return fetch(`/answers/select/${id}`, {
    method: 'POST',
    headers: {
      authorization: sessionStorage.getItem('access_token'),
    },
  }).then((response) => {
    return response.json();
  });
};
