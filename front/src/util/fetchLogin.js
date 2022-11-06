import { showToast } from '../components/toast/Toast';
export const BASE_URL = 'http://13.125.238.70:8080'; // 다른페이지에서 아직 참조중이라 남겨둡니다

export const fetchLogin = async (data) => {
  return fetch(`/auth/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
      Accept: 'application/json',
    },
    body: data,
  })
    .then((res) => {
      if (!res.ok) {
        console.log(res);
        if (res.status === 401) showToast('Wrong Email or Password');
        throw Error('could not fetch the data for that resource');
      }
      if (res.status === 200) {
        showToast('Login Success!');

        // 토큰 저장
        const accessToken = res.headers.get('Authorization');
        const refreshToken = res.headers.get('refresh');
        sessionStorage.setItem('access_token', accessToken); // 30분
        sessionStorage.setItem('refresh_token', refreshToken); // 420분
      }
      return res;
    })
    .catch((err) => {
      console.error(err.message);
    });
};

// 로그인한 Account 조회
export const fetchUserInfo = async () => {
  return fetch(`/accounts/user`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
      Accept: 'application/json',
      authorization: sessionStorage.getItem('access_token'),
    },
  })
    .then((res) => {
      if (!res.ok) {
        // error coming back from server
        throw Error('could not fetch the data for that resource');
      }
      return res.json();
    })
    .catch((err) => {
      console.error(err.message);
    });
};

export const checkIfLogined = async () => {
  return await fetchUserInfo().then((data) => {
    if (!data) {
      setTimeout(() => {
        window.location.href = '/login';
      }, 1500);
      showToast('Please log in.', 'danger');
    }
  });
};
