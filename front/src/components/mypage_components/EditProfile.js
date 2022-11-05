import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { BASE_URL } from '../../util/fetchLogin';

const EditProfile = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [passwordCheck, setPasswordCheck] = useState('');

  const [usernameError, setUsernameError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);
  const [passwordCheckError, setPasswordCheckError] = useState(false);

  function handleUserName(e) {
    setUsername(e.target.value);
  }
  function handlePassword(e) {
    setPassword(e.target.value);
  }
  function handlePasswordCheck(e) {
    setPasswordCheck(e.target.value);
  }
  function checkUsername() {
    const usernameRegexp = /^[a-zA-Z가-헿0-9]{4,}$/;
    if (!username || !usernameRegexp.test(username)) {
      setUsernameError(true);
    } else setUsernameError(false);
  }
  function checkPassword() {
    const passwordRegexp = /(?=.\d)(?=.[a-zA-ZS]).{8,}$/;
    if (!password || !passwordRegexp.test(password)) {
      setPasswordError(true);
    } else setPasswordError(false);
  }
  function checkPasswordCheck() {
    if (password !== passwordCheck) {
      setPasswordCheckError(true);
    } else setPasswordCheckError(false);
  }

  function onSubmit() {
    checkUsername();
    checkPassword();
    checkPasswordCheck();
    if (!usernameError && !passwordError && !passwordCheckError) return true;
    else return false;
  }

  // const [data, setData] = useState();
  // useEffect(() => {
  //   fetch(`${BASE_URL}/accounts/1`, {
  //     method: 'PATCH',
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

  return (
    <>
      <div className="flex">
        <nav className="my-3 ml-3 mr-8 whitespace-nowrap">
          <p className="py-1.5 pr-12 pl-3 text-xs font-bold">
            RERSONAL INFORMATION
          </p>
          <div>
            <ul className="w-10/12">
              <li
                className={
                  'py-1 pr-12 pl-3 m-0.5 bg-primary-400 rounded-2xl hover:bg-primary-700 text-white'
                }
              >
                <Link to="/mypage/settings/editprofile">
                  <button>Edit Profile</button>
                </Link>
              </li>
              <li
                className={
                  'py-1 pr-12 pl-3 m-0.5 hover:bg-soGray-light hover:rounded-2xl'
                }
              >
                <Link to="/mypage/settings/deleteprofile">
                  <button>Delete Profile</button>
                </Link>
              </li>
            </ul>
          </div>
        </nav>
        <div className="w-full">
          <div className="pb-4 mb-6 border-b border-soGray-normal">
            <h1 className="text-3xl font-medium">Edit your Profile</h1>
          </div>
          <form>
            <div className="mb-2 text-2xl font-medium">
              {' '}
              Public information{' '}
            </div>
            <div className="p-6 border rounded border-soGray-normal mb-7">
              <div className="font-semibold">Profile image</div>
              <div className="relative">
                <img
                  src="../../public/logo192.png"
                  alt=""
                  className="w-40 h-40"
                />
                <a
                  href="http://localhost:3000/"
                  className="absolute block w-40 py-2 text-sm text-center text-white bg-soGray-normal h-9 bottom-px"
                >
                  Change picture
                </a>
              </div>
              <div className="font-semibold text-lg my-0.5">Display name</div>
              <input
                type="text"
                value={username}
                onChange={handleUserName}
                className="w-3/6 p-1 border rounded border-soGray-normal"
              />
              {usernameError && (
                <p className="text-sm text-danger-500">
                  Display Name can only contain letters, digits, spaces,
                  apostrophes or hyphens and must start with a letter or digit
                </p>
              )}
              <div className="font-semibold text-lg my-0.5">New password</div>
              <input
                type="password"
                value={password}
                onChange={handlePassword}
                className="w-3/6 p-1 border rounded border-soGray-normal"
              />
              {passwordError && (
                <p className="text-sm text-danger-500">
                  passwords must contain at least eight characters, including at
                  least 1letter and 1 number.
                </p>
              )}
              <div className="font-semibold text-lg my-0.5">
                New password (again)
              </div>
              <input
                type="password"
                value={passwordCheck}
                onChange={handlePasswordCheck}
                className="w-3/6 p-1 border rounded border-soGray-normal"
              />
              {passwordError && (
                <p className="text-sm text-danger-500">
                  The passwords do not match.
                </p>
              )}
              <p className="mt-2 text-xs text-gray-600">
                passwords must contain at least eight characters,
                <br /> including at least 1letter and 1 number.
              </p>
            </div>
          </form>
          <div>
            <button
              className="m-1.5 p-2.5 bg-buttonPrimary rounded text-white font-medium"
              onClick={onSubmit}
            >
              Save profile
            </button>
            <button className="m-1.5 p-2.5 rounded text-blue-600 font-medium text-buttonPrimary">
              Cancel
            </button>
          </div>
        </div>
      </div>
    </>
  );
};

export default EditProfile;
