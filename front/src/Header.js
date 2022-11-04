import { IconLogo, IconSearch, IconPerson } from '@stackoverflow/stacks-icons';
import ReactHtmlParser from 'react-html-parser';
import { useState, useRef } from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
  const [isLogin, setIsLogin] = useState(false);
  const [isFocus, setIsFocus] = useState(false);
  const search = useRef();

  const onClick = () => {
    setIsLogin(!isLogin);
  };

  const onChangeSearch = (e) => {
    console.log(e);
    setIsFocus(true);
  };

  return (
    <div className="sticky top-0 z-20 flex-col w-full drop-shadow h-[60px] flex-nowrap">
      <div className="h-1 bg-primary-300"></div>
      <div className="flex justify-center px-2 py-3 bg-soGray-headerbg">
        <div className="items-center mx-2 my-1">
          <Link to="/">{ReactHtmlParser(IconLogo)}</Link>
        </div>
        {/* <button className="hover:bg-soGray-light" onClick={onClick}>
          임시 isLogin toggle
        </button> */}
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

const LoginGNB = () => {
  return (
    <div className="flex">
      <button className="px-3 py-1 mx-2 text-gray hover:bg-soGray-light">
        Logout
      </button>
      <div className="items-center p-2 hover:bg-soGray-light">
        {ReactHtmlParser(IconPerson)}
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

export default Header;
