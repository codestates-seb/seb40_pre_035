import { IconLogo, IconSearch } from '@stackoverflow/stacks-icons';
import { useState, useRef, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { fetchUserInfo } from './util/fetchLogin';
import { Icon } from './util/convertor';
import { data } from 'autoprefixer';

const Header = () => {
  const [isLogin, setIsLogin] = useState(false);
  const [isFocus, setIsFocus] = useState(false);
  const [userProfileImage, setUserProfileImage] = useState('');
  const [userProfileImageLink, setUserProfileImageLink] = useState('');
  const [searchText, setSearchText] = useState('');
  const search = useRef();
  const navigator = useNavigate();

  useEffect(() => {
    checkLoginState();
  });

  function handleSearch(e) {
    setSearchText(e.target.value);
    // 세션스토리지에 검색어 저장
    sessionStorage.setItem('searchText', searchText);

    if (e.key === 'Enter' && searchText) {
      if (window.location.pathname === '/question') window.location.reload();
      else {
        navigator('/question');
      }
      setSearchText('');
    }
  }

  const checkLoginState = () => {
    // 세션스토리지에 저장되어있고, 현재 주소가 /login이나 signup이 아니면(home등에 진입한상태)
    // isLogin 상태 true. header에 프로필이미지 구현.
    const currentPath = window.location.pathname;
    const isLoginPath =
      currentPath === 'login' || currentPath === 'signup' ? true : false;
    if (sessionStorage.getItem('access_token') && !isLoginPath) {
      setIsLogin(true);
      getUserProfile();
      setUserProfileImageLink(`/mypage/${sessionStorage.getItem('accountId')}`);
    } else {
      setIsLogin(false);
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

      // 유저이메일, account ID 저장
      sessionStorage.setItem('userEmail', data.email);
      sessionStorage.setItem('accountId', data.accountId);
    });
  };
  const LoginGNB = () => {
    return (
      <div className="flex items-center">
        <button
          onClick={onLogoutClick}
          className="px-3 py-1 mx-1 text-gray hover:bg-[#eee] rounded"
        >
          {/* 로그아웃 클릭시 home으로 이동 */}
          <Link to="/">Logout</Link>
        </button>
        <div className="items-center p-2 hover:bg-soGray-light">
          <Link to={userProfileImageLink}>
            <img
              src={userProfileImage}
              alt="userProfile"
              width={25}
              height={25}
            ></img>
          </Link>
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

  const onClickremove = () => {
    sessionStorage.removeItem('searchText');
  };

  return (
    <div className="sticky top-0 z-20 flex-col w-full drop-shadow h-[60px] flex-nowrap">
      <div className="h-1 bg-primary-300"></div>
      <div className="flex justify-center px-2 py-3 bg-soGray-headerbg">
        <div className="items-center mx-2 my-1">
          <Link to="/" onClick={onClickremove}>
            {Icon(IconLogo)}
          </Link>
        </div>
        <div className="flex items-center px-2 py-1 mx-2 mr-10 bg-white border rounded-md grow border-soGray-light focus:ring-secondary-300">
          <div className="flex mx-2 my-1 text-soGray-icon">
            {Icon(IconSearch)}
          </div>
          <input
            type="text"
            value={searchText}
            className="w-[calc(100%-40px)] focus:outline-none focus-visible:outline-none"
            placeholder="Search..."
            onChange={handleSearch}
            onKeyPress={handleSearch}
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
