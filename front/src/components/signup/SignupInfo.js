import { useState, useEffect, useRef } from 'react';
import { fetchSignup } from '../../util/fetchSignup';
import { useNavigate } from 'react-router-dom';
import identicon1 from '../../images/identicon1.jpeg';
import '../../components/common.css';

const SignupInfo = () => {
  const navigate = useNavigate();
  const defaultImage = useRef();
  const mounted = useRef(false);
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const [usernameError, setUsernameError] = useState(false);
  const [emailError, setEmailError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);

  const [isValidate, setIsValidate] = useState(false);

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
      return false;
    } else {
      setUsernameError(false);
      return true;
    }
  }
  function checkEmail() {
    const emailRegexp = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    if (!email || !emailRegexp.test(email)) {
      setEmailError(true);
      return false;
    } else {
      setEmailError(false);
      return true;
    }
  }
  function checkPassword() {
    const passwordRegexp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    if (!password || !passwordRegexp.test(password)) {
      setPasswordError(true);
      return false;
    } else {
      setPasswordError(false);
      return true;
    }
  }

  function validation() {
    checkUsername()
      ? console.log('username 유효')
      : console.log('username 유효하지않음');
    checkEmail()
      ? console.log('email 유효')
      : console.log('email 유효하지않음');
    checkPassword()
      ? console.log('password 유효')
      : console.log('password 유효하지않음');

    console.log('usernameError: ' + usernameError);
    console.log('passwordError: ' + passwordError);
    console.log('emailError: ' + emailError);

    if (checkUsername() && checkEmail() && checkPassword()) {
      console.log('signup ready');
      return true;
    }
    return false;
    // if (!usernameError && !passwordError && !emailError) {
    //   console.log('signup ready');
    //   return true;
    // }
    // return false;
  }

  function onSubmit() {
    // validation()
    //   ? setIsValidate((check) => (check = true))
    //   : setIsValidate((check) => (check = false));

    console.log('현재 Signup isvalidate:', isValidate);
    if (validation()) onUpload();
  }

  const dataURLtoFile = (dataurl, fileName) => {
    var arr = dataurl.split(','),
      mime = arr[0].match(/:(.*?);/)[1],
      bstr = atob(arr[1]),
      n = bstr.length,
      u8arr = new Uint8Array(n);

    while (n--) {
      u8arr[n] = bstr.charCodeAt(n);
    }

    return new File([u8arr], fileName, { type: mime });
  };
  const getRandomImage = async () => {
    const b64data = defaultImage.current.currentSrc;
    let imagefile = dataURLtoFile(b64data, 'defaultImage.jpeg');
    return imagefile;
  };

  const onUpload = async (callback) => {
    const formData = new FormData();
    const finalImage = await getRandomImage();
    formData.append('profile', finalImage);
    formData.append('email', email);
    formData.append('password', password);
    formData.append('nickname', username);

    const goLogin = () => {
      navigate('/login');
    };
    let path = await fetchSignup(formData).then((data) => {
      console.log(data);
      if (data.status === 201) goLogin();
    });
  };

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
            {emailError && (
              <p className="text-xxs text-danger-500">
                이메일 형식에 맞지 않습니다.
              </p>
            )}
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
          <button
            onClick={onSubmit}
            className="flex justify-center w-full mt-10 so-button-primary"
          >
            Sign up
          </button>

          <div className="hidden">
            <label htmlFor="file-input">
              <img src={identicon1} ref={defaultImage} alt="default-profile" />
            </label>
          </div>
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

export default SignupInfo;
