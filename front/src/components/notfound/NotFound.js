import { useNavigate } from 'react-router-dom';
import { SpotAlertXL } from '@stackoverflow/stacks-icons';
import { Icon } from '../../util/Icon';

const NotFound = ({ code = 404 }) => {
  const navigate = useNavigate();

  return (
    <div className="flex flex-col items-center justify-center w-full bg-soGray-bg">
      <div className="flex flex-row items-center">
        <div className="mr-4">{Icon(SpotAlertXL)}</div>
        <h2 className="leading-10 text-left">
          <p className="mb-4 font-extrabold text-xxl text-soGray-darker">
            {code && code}
          </p>
          <p className="mb-2 font-medium text-black text-xxl">
            Page not found We&apos;re sorry,
          </p>
          <p className="text-lg text-black">
            we couldn&apos;t find the page you requested.
          </p>
        </h2>
      </div>
      <button className="mb-20 so-button-primary" onClick={() => navigate('/')}>
        go to main
      </button>
    </div>
  );
};

export default NotFound;
