export const fetchAnswerList = async (id) => {
  return (
    fetch(`/answers/question/${id}?size=100`)
      // /answers/question/46?size=100
      .then((response) => {
        if (!response.ok) {
          console.log(response.statusText);
          throw Error('could not fetch the answer data.');
        }
        return response.json();
      })
      .catch((error) => {
        throw Error(error.message);
      })
  );
};

export const fetchCreateAnswer = async (fetchData) => {
  console.log(fetchData);
  return fetch(`/answers`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      authorization: sessionStorage.getItem('access_token'),
    },
    body: JSON.stringify(fetchData),
  })
    .then((response) => {
      console.log(response);
      if (!response.ok) {
        console.log(response.statusText);
        throw Error('유효하지 않은 요청입니다.');
      }
      return response.json();
    })
    .catch((error) => {
      throw Error(error.message);
    });
};
