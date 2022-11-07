import { EC2 } from './fetchLogin';

export const fetchQuestionDetail = async (id) => {
  return fetch(`${EC2}/questions/${id}`)
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
const searchTemp = '';

export const fetchQuestionList = async (page, filter, searchText) => {
  console.log('Question 리스트가 조회됩니다.');
  console.log('page: ' + page);
  console.log('filter: ' + filter);
  console.log(searchText);
  let url = null;
  if (filter === 'unanswered') {
    if (searchText !== searchTemp && searchText !== null) {
      url = `/questions/unAnswered?page=${page}&size=10&sort=createdAt%2Cdesc&keyword=${searchText}`;
    } else if (searchText === null) {
      url = `/questions/unAnswered?page=${page}&size=10&sort=createdAt%2Cdesc`;
      console.log('url: ' + url);
    }
  } else if (filter === 'newest') {
    if (searchText !== searchTemp && searchText !== null) {
      url = `/questions?page=${page}&size=10&sort=id%2Cdesc&keyword=${searchText}`;
    } else {
      url = `/questions?page=${page}&size=10&sort=createdAt%2Cdesc`;
      console.log('url: ' + url);
    }
  } else if (filter === 'vote') {
    if (searchText !== searchTemp && searchText !== null) {
      url = `/questions/totalVote?page=${page}&size=10&keyword=${searchText}`;
      console.log('stringvoteurl:' + url);
    } else {
      url = `/questions/totalVote?page=${page}&size=10`;
      console.log('url: ' + url);
    }
  }
  return fetch(`${EC2}${url}`)
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

export const fetchCreateQuestion = async (fetchData) => {
  return fetch(`${EC2}/questions`, {
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
    .then((data) => {
      return data.id;
    })
    .catch((error) => {
      throw Error(error.message);
    });
};

export const fetchUpdateQuestion = async (fetchData, id) => {
  fetch(`${EC2}/questions/${id}`, {
    method: 'PATCH',
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
    .then((data) => {
      return data.data;
    })
    .catch((error) => {
      throw Error(error.message);
    });
};

export const fetchDeleteQuestion = async (id) => {
  fetch(`${EC2}/questions/${id}`, {
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
      return data.data;
    })
    .catch((error) => {
      throw Error(error.message);
    });
};
