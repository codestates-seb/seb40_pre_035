import { IconLogo, IconSearch, IconPerson } from '@stackoverflow/stacks-icons';
import ReactHtmlParser from 'react-html-parser';
import { useState } from 'react';
const Header = () => {
  const [isLogin, setIsLogin] = useState(false);
  const onClick = () => {
    setIsLogin(!isLogin);
  };
  return (
    <div className="sticky top-0 z-50 flex-col w-full drop-shadow">
      <div className="h-1 bg-primary-300"></div>
      <div className="flex justify-center px-4 py-4 bg-soGray-headerbg">
        <div className="items-center mx-2 my-1">
          {ReactHtmlParser(IconLogo)}
        </div>
        <button className="hover:bg-soGray-light" onClick={onClick}>
          임시 isLogin toggle
        </button>
        <div className="flex items-center px-2 py-1 mx-2 mr-10 bg-white rounded-md grow">
          <div className="flex mx-2 my-1 text-soGray-icon">
            {ReactHtmlParser(IconSearch)}
          </div>
          <input type="text" placeholder="Search..."></input>
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
        Log in
      </button>
      <button className="px-2 py-1 mx-1 text-white border rounded hover:bg-buttonPrimaryHover bg-buttonPrimary border-secondary-300">
        Sign up
      </button>
    </>
  );
};

export default Header;
