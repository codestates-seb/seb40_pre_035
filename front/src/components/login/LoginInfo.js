import { useState, useEffect } from 'react';
import { fetchLogin } from './../../util/fetchLogin';
import { useNavigate } from 'react-router-dom';

const LoginInfo = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [emailError, setEmailError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);
  const [isValidate, setIsValidate] = useState(false);

  function handleEmail(e) {
    setEmail(e.target.value);
  }
  function handlePassword(e) {
    setPassword(e.target.value);
  }

  function checkEmail() {
    if (!email) {
      setEmailError(true);
      return false;
    } else {
      setEmailError(false);
      return true;
    }
  }
  function checkPassword() {
    if (!password) {
      setPasswordError(true);
      return false;
    } else {
      setPasswordError(false);
      return true;
    }
  }

  const borderColor = {
    true: 'w-full px-2 py-1 border rounded border-danger-500',
    false: 'w-full px-2 py-1 border rounded border-soGray-light',
  };

  function validation() {
    // console.log('로그인 유효성 검사');
    checkEmail();
    // ? console.log('email 유효')
    // : console.log('email 유효하지않음');
    checkPassword();
    // ? console.log('password 유효')
    // : console.log('password 유효하지않음');

    // console.log('passwordError: ' + passwordError);
    // console.log('emailError: ' + emailError);
    if (checkEmail() && checkPassword()) {
      // console.log('login ready');
      return true;
    } else return false;
  }

  function onSubmit(e) {
    // validation() ? setIsValidate(true) : setIsValidate(false);
    // console.log('현재 Login isvalidate:', isValidate);
    e.preventDefault();

    if (validation()) {
      onLoginData();
    }
  }

  // 로그인 데이터 fetch
  const onLoginData = async (callback) => {
    const loginData = JSON.stringify({
      email: email,
      password: password,
    });
    const goHome = () => {
      navigate('/');
    };
    let login = await fetchLogin(loginData).then((data) => {
      if (data.status === 200) {
        goHome();
        window.location.reload();
      }
    });
  };

  return (
    <div className="flex-col justify-center my-5 align-middle min-w-[280px]">
      <div className="px-5 pt-3 pb-10 bg-white rounded-md drop-shadow-xl">
        <form className="form" onSubmit={onSubmit}>
          <div className="flex-col justify-center my-3">
            <div className="font-bold">Email</div>
            <input
              type="Email"
              value={email}
              onChange={handleEmail}
              className={borderColor[emailError ?? false]}
            />
            {emailError && (
              <p className="text-xxs text-danger-500">Email cannot be empty.</p>
            )}
          </div>
          <div className="flex-col items-center justify-center my-3">
            <div className="flex items-center">
              <div className="font-bold">Password</div>
              <a
                href="./login"
                className="ml-auto align-bottom text-xxs text-secondary-500 hover:text-secondary-300"
              >
                Forgot password?
              </a>
            </div>

            <input
              type="password"
              value={password}
              onChange={handlePassword}
              className={borderColor[passwordError ?? false]}
            />
            {passwordError && (
              <p className="text-xxs text-danger-500">
                Password cannot be empty.
              </p>
            )}
          </div>
          <button
            type="submit"
            className="flex justify-center w-full mt-10 text-center so-button-primary"
          >
            Log in
          </button>
        </form>
      </div>
      <div className="flex justify-between mx-3 my-10 text-sm">
        <p>Don&apos;t have an account?</p>
        <a
          href="./login"
          className="text-secondary-600 hover:text-secondary-300"
        >
          Sign up
        </a>
      </div>
    </div>
  );
};

export default LoginInfo;
