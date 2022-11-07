import { useEffect, useState, useRef } from 'react';
import { Link, useParams, useNavigate } from 'react-router-dom';
import { fetchUploadImage } from '../../util/fetchFile';
import { showToast } from '../toast/Toast';

const EditProfile = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [idData, setIdData] = useState(null);
  const [userProfile, setUserProfile] = useState(idData?.profile);
  const [profileFile, setProfileFile] = useState(null);
  const [uploadImg, setUploadImg] = useState(false);

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [passwordCheck, setPasswordCheck] = useState('');

  const [usernameError, setUsernameError] = useState(true);
  const [passwordError, setPasswordError] = useState(true);
  const [passwordCheckError, setPasswordCheckError] = useState(true);

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
    const passwordRegexp =
      /^(?=.[A-Za-z])(?=.\d)[a-zA-Z\d`~!@#$%^&*()-_=+]{8,}$/;
    if (password.length === 0 || passwordRegexp.test(password)) {
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
    console.log(usernameError);
    console.log(passwordError);
    console.log(passwordCheckError);
    if (!uploadImg) showToast('plz upload img');
    if (!usernameError && !passwordError && !passwordCheckError && uploadImg) {
      onEdit();
      navigate(`/mypage/${id}`);
      return location.reload();

      // return true;
    }
    // return ;
    else {
      return false;
    }
  }

  useEffect(() => {
    fetch(`/accounts/${id}`)
      .then((res) => {
        if (!res.ok) {
          // error coming back from server
          throw Error('could not fetch the data for that resource');
        }
        return res.json();
      })
      .then((idData) => {
        setUserProfile(idData?.profile);
        setIdData(idData);
      })
      .catch((err) => {
        console.error(err.message);
      });
  }, []);

  const userName = idData?.nickname;

  const onChange = async (e) => {
    setUploadImg(true);
    setProfileFile(e.target.files[0]);
    await fetchUploadImage(e.target.files[0]).then((path) => {
      setUserProfile(path);
    });
  };

  // console.log(profileFile);

  const onEdit = () => {
    const formData = new FormData();
    formData.append('profile', profileFile);
    formData.append('password', password);
    formData.append('nickname', username);

    fetch(`/accounts/${id}`, {
      method: 'POST',
      headers: {
        // 'Content-Type': 'multipart/form-data;charset=UTF-8',
        Authorization: `${sessionStorage.access_token}`,
      },
      body: formData,
    })
      .then((res) => {
        if (!res.ok) {
          if (res.status === 400) showToast('Fill in the blanks');
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
                  'py-1 pr-12 pl-3 m-0.5 bg-primary-400 rounded-2xl hover:bg-primary-700 text-white'
                }
              >
                <Link to={`/mypage/${id}/settings/editprofile`}>
                  <button>Edit Profile</button>
                </Link>
              </li>
              <li
                className={
                  'py-1 pr-12 pl-3 m-0.5 hover:bg-soGray-light hover:rounded-2xl'
                }
              >
                <Link to={`/mypage/${id}/settings/deleteprofile`}>
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
          <div className="mb-2 text-2xl font-medium"> Public information </div>
          <div className="p-6 border rounded border-soGray-normal mb-7">
            <div className="font-semibold">Profile image</div>
            <div className="relative">
              <img src={userProfile} alt="" className="w-40 h-40" />
              <form>
                <input
                  type="file"
                  accept="image/jpeg"
                  id="imgUpload"
                  className="hidden"
                  onChange={onChange}
                ></input>
                <label
                  className="absolute block w-40 py-2 text-sm text-center text-white bg-soGray-normal h-9 bottom-px"
                  htmlFor="imgUpload"
                >
                  Change picture
                </label>
              </form>
            </div>

            <div className="font-semibold text-lg my-0.5">Display name</div>
            <input
              type="text"
              value={username}
              placeholder={userName}
              onChange={handleUserName}
              className="w-3/6 p-1 pl-1.5 border rounded border-soGray-normal"
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
            {passwordCheckError && (
              <p className="text-sm text-danger-500">
                The passwords do not match.
              </p>
            )}
            <p className="mt-2 text-xs text-gray-600">
              passwords must contain at least eight characters,
              <br /> including at least 1letter and 1 number.
            </p>
          </div>
          <div>
            <button
              className="m-1.5 p-2.5 bg-buttonPrimary rounded text-white font-medium"
              onClick={onSubmit}
            >
              Save profile
            </button>

            <Link
              to={`/mypage/${id}`}
              disable={true}
              // disable={!usernameError && !passwordError && !passwordCheckError}
            >
              <button className="m-1.5 p-2.5 rounded text-blue-600 font-medium text-buttonPrimary">
                Cancel
              </button>
            </Link>
          </div>
        </div>
      </div>
    </>
  );
};

export default EditProfile;
