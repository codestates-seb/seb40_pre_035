import { showToast } from '../components/toast/Toast';
import { EC2 } from './fetchLogin';

export const fetchQuestionVote = async (id, status) => {
  return fetch(`${EC2}/questions/questionVote/${id}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      authorization: sessionStorage.getItem('access_token'),
    },
    body: JSON.stringify({
      state: status,
    }),
  })
    .then(async (response) => {
      if (!response.ok) {
        return response.json().then((res) => {
          throw Error(res.message);
        });
      }
      return response.json();
    })
    .then((data) => data.data)
    .catch((error) => {
      // 'Vote had been sent, Please cancel vote first.'
      showToast('Vote had been sent, Please cancel vote first.', 'danger');
    });
};

export const fetchAnswerVote = async (id, status) => {
  return fetch(`${EC2}/answers/answerVote/${id}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      authorization: sessionStorage.getItem('access_token'),
    },
    body: JSON.stringify({
      state: status,
    }),
  })
    .then(async (response) => {
      if (!response.ok) {
        return response.json().then((res) => {
          throw Error(res.message);
        });
      }
      return response.json();
    })
    .then((data) => data.data)
    .catch((error) => {
      // 'Vote had been sent, Please cancel vote first.'
      showToast('Vote had been sent, Please cancel vote first.', 'danger');
    });
};
