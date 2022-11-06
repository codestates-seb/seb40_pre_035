import { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';

const DeleteProfile = () => {
  const { id } = useParams();
  // const [data, setData] = useState(null);

  // useEffect(() => {
  //   fetch(`/accounts/user`, {
  //     method: 'GET',
  //     'Content-Type': 'multipart/form-data;charset=UTF-8',
  //     Accept: 'application/json',
  //     Authorization: `${sessionStorage.access_token}`,
  //   })
  //     .then((res) => {
  //       if (!res.ok) {
  //         // error coming back from server
  //         throw Error('could not fetch the data for that resource');
  //       }
  //       return res.json();
  //     })
  //     .then((data) => {
  //       setData(data);
  //     })
  //     .catch((err) => {
  //       console.error(err.message);
  //     });
  // }, []);

  // const id = data?.id;

  console.log(sessionStorage.access_token);
  const onRemove = () => {
    fetch(`/accounts/${id}`, {
      method: 'DELETE',
      header: {
        Authorization: sessionStorage.getItem('access_token'),
      },
    }).then();
  };

  const [checkBox, setCheckBox] = useState([]);
  const changeHandler = (checked, id) => {
    if (checked) {
      setCheckBox([...checkBox, id]);
    } else {
      setCheckBox(checkBox.filter((button) => button !== id));
    }
  };

  const disabled = !(checkBox.length === 1);

  const logout = () => {
    sessionStorage.clear();
  };

  return (
    <>
      <div className="flex mb-28">
        <nav className="my-3 ml-3 mr-8 whitespace-nowrap">
          <p className="py-1.5 pr-12 pl-3 text-xs font-bold">
            RERSONAL INFORMATION
          </p>
          <div>
            <ul className="w-10/12">
              <li
                className={
                  'py-1 pr-12 pl-3 m-0.5 hover:bg-soGray-light hover:rounded-2xl'
                }
              >
                <Link to={`/mypage/${id}/settings/editprofile`}>
                  <button>Edit Profile</button>
                </Link>
              </li>
              <li
                className={
                  'py-1 pr-12 pl-3 m-0.5 bg-primary-400 rounded-2xl hover:bg-primary-700 text-white'
                }
              >
                <Link to={`/mypage/${id}/settings/deleteprofile`}>
                  <button>Delete Profile</button>
                </Link>
              </li>
            </ul>
          </div>
        </nav>
        <div>
          <div className="pb-4 mb-6 border-b border-soGray-normal">
            <h1 className="text-3xl font-medium ">Delete Profile</h1>
          </div>
          <p className="mb-4">
            Before confirming that you would like your profile deleted, we&#39;d
            like to take a moment to explain the implications of deletion:
          </p>
          <ul className="mb-4 ml-8">
            <li className="mb-2 list-disc">
              Deletion is irreversible, and you will have no way to regain any
              of your original content, should this deletion be carried out and
              you change your mind later on.
            </li>
            <li className="mb-2 list-disc">
              Your questions and answers will remain on the site, but will be
              disassociated and anonymized (the author will be listed as
              &#34;user20298961&#34;) and will not indicate your authorship even
              if you later return to the site.
            </li>
          </ul>
          <p>
            Confirming deletion will only delete your profile on Stack Overflow
            - it will not affect any of your other profiles on the Stack
            Exchange network. If you want to delete multiple profiles,
            you&#39;ll need to visit each site separately and request deletion
            of those individual profiles.
          </p>
          <form>
            <div className="flex items-start mb-6">
              <input
                type="checkbox"
                id="check"
                className="my-3 mx-1.5 flex"
                onChange={(e) => {
                  changeHandler(e.currentTarget.checked, 'check');
                }}
                checked={checkBox.includes('check') ? true : false}
              />
              <p className="p-0.5 m-1">
                I have read the information stated above and understand the
                implications of having my profile deleted. I wish to proceed
                with the deletion of my profile.
              </p>
            </div>
            {/* <Link to="/"> */}
            <button
              className={
                !disabled
                  ? ' bg-danger-400 text-white p-2.5 rounded'
                  : ' bg-danger-700 text-white p-2.5 rounded '
              }
              disabled={disabled}
              onClick={onRemove}
            >
              Delete profile
            </button>
            {/* </Link> */}
          </form>
        </div>
      </div>
    </>
  );
};

export default DeleteProfile;
