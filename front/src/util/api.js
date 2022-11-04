export const BASE_URL = 'http://13.125.238.70:8080';

export const fetchUploadImage = async (blob) => {
  let formData = new FormData();
  formData.append('file', blob);

  fetch(`/file`, {
    method: 'POST',
    headers: {},
    body: formData,
  })
    .then((response) => {
      console.log(response.ok);
      if (!response.ok) {
        throw Error('유효하지 않은 요청입니다.');
      } else {
        return response.json();
      }
    })
    .then((data) => {
      console.log(data);
      return data.data;
    })
    .catch((error) => {
      throw Error(error.message);
    });
};

export const fetchCreateQuestion = async (fetchData) => {
  fetch(`/questions`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization:
        'Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsImlkIjoxLCJ1c2VybmFtZSI6InRlc3QxQHRlc3QuY29tIiwic3ViIjoiMSIsImlhdCI6MTY2NzQwMzM3OSwiZXhwIjoxNjY3NDA1MTc5fQ.tBnUxVdRDNw0Pjwkg-hIuMpy1PuFVbjec6vdZglS0dk',
    },
    body: JSON.stringify(fetchData),
  })
    .then((response) => {
      console.log(response);
      if (!response.ok) {
        throw Error('유효하지 않은 요청입니다.');
      } else {
        return response.json();
      }
    })
    .then((data) => {
      window.location.href = `/question/detail/${data.id}`;
    })
    .catch((error) => {
      throw Error(error.message);
    });
};

export const fetchCreateReview = async (fetchData) => {
  fetch(`/answers?page=1&size=10&sort=id%2Cdesc`, {
    method: 'POST',
    data: fetchData,
  })
    .then((response) => {
      console.log(response);
      if (!response.ok) {
        throw Error('유효하지 않은 요청입니다.');
      } else {
        window.location.reload();
        return response.json();
      }
    })
    .catch((error) => {
      throw Error(error.message);
    });
};

export const fetchSignup = async (data) => {
  fetch(`/accounts`, {
    method: 'POST',
    header: { 'Content-Type': 'multipart/form-data;charset=UTF-8' },
    body: data,
  })
    .then((res) => {
      if (!res.ok) {
        // error coming back from server
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
    // body: JSON.stringify({
    //   email: 'test435@gmail.com',
    //   password: 'test1234',
    // }),
  })
    .then((res) => {
      if (!res.ok) {
        // error coming back from server
        throw Error('could not fetch the data for that resource');
      }
      console.log(res.headers);
      console.log(res.headers.get('Authorization'));
      console.log(res.headers.get('refresh'));

      //sessionStorage에 저장 -> 브라우저 종료시 제거됨
      const accessToken = res.headers.get('Authorization');
      const refreshToken = res.headers.get('refresh');
      sessionStorage.setItem('access_token', accessToken); // 30분
      sessionStorage.setItem('refresh_token', refreshToken); // 420분

      return res.json();
    })
    .then((data) => {
      console.log(data.data);
      console.log('로그인 되었습니다.');
      alert('로그인되었습니다.');
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
        Authorization: sessionStorage.getItem('access_token'),
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
