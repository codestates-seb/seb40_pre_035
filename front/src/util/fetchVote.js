export const fetchVote = async (id, status, type) => {
  return fetch(`/${type}s/${type}Vote/${id}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      authorization: sessionStorage.getItem('access_token'),
    },
    body: JSON.stringify({
      state: status,
    }),
  })
    .then((response) => {
      if (response.ok) {
        return response.json();
      }
    })
    .then((data) => {
      console.log(data);
      return data && data.data;
    })
    .catch((error) => {
      console.log(error);
      throw Error(error.message);
    });
};
