import { IconLogoGlyphMd } from '@stackoverflow/stacks-icons';
import LoginInfo from '../components/login/LoginInfo';
import { Icon } from '../util/convertor';

const Login = () => {
  return (
    <div className="flex justify-center w-full align-center bg-soGray-bg">
      <div className="flex-col justify-center mb-auto pt-14">
        <div className="flex items-center justify-center mx-2 my-10">
          {Icon(IconLogoGlyphMd)}
        </div>
        <LoginInfo />
      </div>
    </div>
  );
};
export default Login;
