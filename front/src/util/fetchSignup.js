import { showToast } from '../components/toast/Toast';

export const fetchSignup = async (data) => {
  return fetch(`/accounts`, {
    method: 'POST',
    header: { 'Content-Type': 'multipart/form-data;charset=UTF-8' },
    body: data,
  })
    .then((res) => {
      if (!res.ok) {
        if (res.status === 400) showToast('Email already exists');
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
