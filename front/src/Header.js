import { IconLogo, IconSearch, IconPerson } from '@stackoverflow/stacks-icons';
import ReactHtmlParser from 'react-html-parser';
import { useState, useRef, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { fetchUserInfo } from './util/api';
import { data } from 'autoprefixer';

const Header = () => {
  const [isLogin, setIsLogin] = useState(false);
  const [isFocus, setIsFocus] = useState(false);
  const [userProfileImage, setUserProfileImage] = useState('');
  const search = useRef();

  useEffect(() => {
    checkLoginState();
  });

  const checkLoginState = () => {
    // 세션스토리지에 저장되어있고, 현재 주소가 /login이나 signup이 아니면(home등에 진입한상태)
    // isLogin 상태 true. header에 프로필이미지 구현.
    const currentPath = window.location.pathname;
    const isLoginPath =
      currentPath === 'login' || currentPath === 'signup' ? true : false;
    if (sessionStorage.getItem('access_token') && !isLoginPath) {
      console.log('chekLoginState if 문 통과함.');
      setIsLogin(true);
      getUserProfile();
    } else {
      setIsLogin(false);
      console.log('chekLoginState if 문 통과 안함.');
    }
  };

  const onLogoutClick = () => {
    sessionStorage.clear();
    setIsLogin(false);
  };

  const onChangeSearch = (e) => {
    console.log(e);
    setIsFocus(true);
  };

  const getUserProfile = async () => {
    return await fetchUserInfo().then((data) => {
      setUserProfileImage(data.profile);
    });
  };
  const LoginGNB = () => {
    return (
      <div className="flex">
        <button
          onClick={onLogoutClick}
          className="px-3 py-1 mx-2 text-gray hover:bg-soGray-light"
        >
          {/* //TODO: 여기는 main으로 보내기 */}
          <Link to="/login">Logout</Link>
        </button>
        <div className="items-center p-2 hover:bg-soGray-light">
          <img src={userProfileImage} alt="userProfile"></img>
        </div>
      </div>
    );
  };

  const LogoutGNB = () => {
    return (
      <>
        <button className="px-3 py-1 mx-2 border rounded text-blue hover:bg-buttonSecondaryHover bg-buttonSecondary border-secondary-200">
          <Link to="/login">Log in</Link>
        </button>
        <button className="px-2 py-1 mx-1 text-white border rounded hover:bg-buttonPrimaryHover bg-buttonPrimary border-secondary-300">
          <Link to="/signup">Sign up</Link>
        </button>
      </>
    );
  };

  return (
    <div className="sticky top-0 z-20 flex-col w-full drop-shadow h-[60px] flex-nowrap">
      <div className="h-1 bg-primary-300"></div>
      <div className="flex justify-center px-2 py-3 bg-soGray-headerbg">
        <div className="items-center mx-2 my-1">
          <Link to="/">{ReactHtmlParser(IconLogo)}</Link>
        </div>
        <div className="flex items-center px-2 py-1 mx-2 mr-10 bg-white border rounded-md grow border-soGray-light focus:ring-secondary-300">
          <div className="flex mx-2 my-1 text-soGray-icon">
            {ReactHtmlParser(IconSearch)}
          </div>
          <input
            type="text"
            className="w-[calc(100%-40px)] focus:outline-none focus-visible:outline-none"
            placeholder="Search..."
            onFocus={(e) => onChangeSearch(e)}
            onBlur={(e) => onChangeSearch(e)}
            ref={search}
          />
        </div>
        <div>{isLogin ? <LoginGNB /> : <LogoutGNB />}</div>
      </div>
    </div>
  );
};

export default Header;
