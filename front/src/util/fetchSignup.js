import { showToast } from '../components/toast/Toast';
import { EC2 } from './fetchLogin';

export const fetchSignup = async (data) => {
  return fetch(`${EC2}/accounts`, {
    method: 'POST',
    header: { 'Content-Type': 'multipart/form-data;charset=UTF-8' },
    body: data,
  })
    .then((res) => {
      if (!res.ok) {
        if (res.status === 400) showToast('Email already exists', 'danger');
        throw Error('could not fetch the data for that resource');
      }

      if (res.status === 201)
        showToast(
          'Congratulations! Your account has been successfully created!'
        );
      return res;
    })
    .catch((error) => {
      console.log(error.message);
    });
};
