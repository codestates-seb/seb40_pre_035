import { IconLogoGlyphMd } from '@stackoverflow/stacks-icons';
import ReactHtmlParser from 'react-html-parser';
import LoginInfo from '../components/login/LoginInfo';
const Login = () => {
  return (
    <div className="flex justify-center w-full h-screen align-center bg-soGray-bg">
      <div className="flex-col justify-center w-1/4 mb-auto pt-14">
        <div className="flex items-center justify-center mx-2 my-10">
          {ReactHtmlParser(IconLogoGlyphMd)}
        </div>
        <LoginInfo />
      </div>
    </div>
  );
};
export default Login;
