import { IconLogo, IconSearch } from '@stackoverflow/stacks-icons';
import ReactHtmlParser from 'react-html-parser';
const Header = () => {
  return (
    <div className="flex-col">
      <div className="h-1 bg-primary-300"></div>
      <div className="flex justify-center px-4 py-4 bg-soGray-headerbg">
        <div className="items-center mx-2 my-1">
          {ReactHtmlParser(IconLogo)}
        </div>
        <div className="flex items-center px-2 py-1 mx-2 mr-10 bg-white rounded-md grow">
          <div className="flex mx-2 my-1 text-soGray-icon">
            {ReactHtmlParser(IconSearch)}
          </div>
          <input type="text" placeholder="Search..."></input>
        </div>
        <button className="px-3 py-1 mx-2 border rounded text-blue hover:bg-buttonSecondaryHover bg-buttonSecondary border-secondary-200">
          Log in
        </button>
        <button className="px-2 py-1 mx-1 text-white border rounded hover:bg-buttonPrimaryHover bg-buttonPrimary border-secondary-300">
          Sign up
        </button>
      </div>
    </div>
  );
};

export default Header;
