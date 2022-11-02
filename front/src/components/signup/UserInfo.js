import { useState } from 'react';
import '../../components/common.css';
const UserInfo = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const [usernameError, setUsernameError] = useState(false);
  const [emailError, setEmailError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);

  function handleUserName(e) {
    setUsername(e.target.value);
  }
  function handleEmail(e) {
    setEmail(e.target.value);
  }
  function handlePassword(e) {
    setPassword(e.target.value);
  }
  function checkUsername() {
    const usernameRegexp = /^[a-zA-Z가-헿0-9]{4,}$/;
    if (!username || !usernameRegexp.test(username)) {
      setUsernameError(true);
    } else setUsernameError(false);
  }
  function checkEmail() {
    const emailRegexp = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    if (!email || !emailRegexp.test(email)) {
      setEmailError(true);
    } else setEmailError(false);
  }
  function checkPassword() {
    const passwordRegexp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    if (!password || !passwordRegexp.test(password)) {
      setPasswordError(true);
    } else setPasswordError(false);
  }

  function onSubmit() {
    checkUsername();
    checkEmail();
    checkPassword();
    if (!usernameError && !passwordError) return true;
    else return false;
  }
  // 동적으로 tailwindcss 추가
  const borderColor = {
    true: 'w-full px-2 py-1 border rounded border-danger-500',
    false: 'w-full px-2 py-1 border rounded border-soGray-light',
  };

  return (
    <div className="flex-col w-1/3 my-5 ml-2 mr-10 align-middle justify-items-center">
      <div className="px-5 pt-3 pb-10 bg-white rounded-md drop-shadow-xl">
        <div className="form">
          <div className="flex-col justify-center mx-2 my-3">
            <div className="font-bold">Display name</div>
            <input
              type="text"
              value={username}
              onChange={handleUserName}
              className={borderColor[usernameError ?? false]}
            ></input>
            {usernameError && (
              <p className="text-xxs text-danger-500">
                4자 이상부터 가능하며 특수 문자가 없어야 합니다.
              </p>
            )}
          </div>
          <div className="flex-col justify-center mx-2 my-3">
            <div className="font-bold">Email</div>
            <input
              type="Email"
              value={email}
              onChange={handleEmail}
              className={borderColor[emailError ?? false]}
            ></input>
            <p className="text-xxs text-danger-500">
              이메일 형식에 맞지 않습니다.
            </p>
          </div>
          <div className="flex-col justify-center mx-2 my-3">
            <div className="font-bold">Password</div>
            <input
              type="password"
              value={password}
              onChange={handlePassword}
              className={borderColor[passwordError ?? false]}
            ></input>
            {passwordError && (
              <p className="text-xxs text-danger-500">
                영어와 숫자를 최소 1개 포함하여 8자 이상이어야합니다.
              </p>
            )}
            <p className="text-sm text-soGray-normal">
              Passwords must contain at least eight characters, including at
              least 1 letter and 1 number.
            </p>
          </div>
          <button onClick={onSubmit} className="w-full mt-10 so-button-primary">
            Sign up
          </button>
        </div>
      </div>
      <div className="flex justify-center my-5">
        <p className="mx-1">Already have an account?</p>
        <a
          href="./login"
          className="text-secondary-600 hover:text-secondary-300"
        >
          Log in
        </a>
      </div>
    </div>
  );
};

export default UserInfo;
