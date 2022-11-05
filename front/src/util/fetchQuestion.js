import { useNavigate } from 'react-router-dom';

export const fetchQuestionDetail = async (id) => {
  return fetch(`/questions/${id}`)
    .then((response) => {
      if (!response.ok) {
        console.log(response.statusText);
        throw Error('could not fetch the question data');
      }
      return response.json();
    })
    .catch((error) => {
      throw Error(error.message);
    });
};

export const fetchQuestionList = async (page, filter) => {
  console.log('Question 리스트가 조회됩니다.');
  console.log('page: ' + page);

  let url = null;
  if (filter !== 'vote') {
    url = `/questions?page=${page}&size=10&sort=id%2Cdesc`;
  } else {
    url = '';
  }

  return fetch(url)
    .then((response) => {
      if (!response.ok) {
        throw Error('could not fetch the data for that resource');
      }
      return response.json();
    })
    .catch((error) => {
      throw Error(error.message);
    });
};

export const fetchCreateQuestion = async (fetchData) => {
  const navigate = useNavigate();

  fetch(`/questions`, {
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
    .then((data) => {
      navigate(`/question/detail/${data.id}`);
    })
    .catch((error) => {
      throw Error(error.message);
    });
};
