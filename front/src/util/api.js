export const BASE_URL = 'http://13.125.238.70:8080';

export const fetchSignup = async (data) => {
  return fetch(`/accounts`, {
    method: 'POST',
    header: { 'Content-Type': 'multipart/form-data;charset=UTF-8' },
    body: data,
  })
    .then((res) => {
      if (!res.ok) {
        throw Error('could not fetch the data for that resource');
      }
      // if (response.data) {
      //   return response.json();
      // }
      alert('회원가입 성공');
    })
    .catch((error) => {
      console.log(error.message);
    });
};

export const fetchLogin = async (data) => {
  fetch(`/auth/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
      Accept: 'application/json',
    },
    body: data,
  })
    .then((res) => {
      if (!res.ok) {
        throw Error('could not fetch the data for that resource');
      }
      console.log(res.headers);
      console.log(res.headers.get('Authorization'));
      console.log(res.headers.get('refresh'));

      const accessToken = res.headers.get('Authorization');
      const refreshToken = res.headers.get('refresh');
      sessionStorage.setItem('access_token', accessToken); // 30분
      sessionStorage.setItem('refresh_token', refreshToken); // 420분

      return res.json();
    })
    .then((data) => {
      console.log(data.data);
      console.log('로그인 되었습니다.');
      // alert('로그인되었습니다.');
    })
    .catch((err) => {
      console.error(err.message);
    });
};

// 로그인한 Account 조회
export const fetchUserInfo = async () => {
  return (
    fetch(`/accounts/user`, {
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
      // .then((data) => {
      //   console.log(data);
      //   let userdata = {
      //     email: data.email,
      //     username: data.nickname,
      //     profile: data.profile,
      //   };
      //   return userdata;
      // })
      .catch((err) => {
        console.error(err.message);
      })
  );
};
