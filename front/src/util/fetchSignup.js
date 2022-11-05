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
