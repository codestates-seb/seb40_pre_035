import { useNavigate } from 'react-router-dom';
import { SpotAlertXL } from '@stackoverflow/stacks-icons';
import { Icon } from '../../util/Icon';

const NotFound = ({ code = 404 }) => {
  const navigate = useNavigate();

  return (
    <div className="flex flex-col items-center justify-center w-full bg-soGray-bg h-[600px]">
      <div className="flex flex-row items-center">
        <div className="mr-4">{Icon(SpotAlertXL)}</div>
        <h2 className="leading-10 text-left">
          <p className="mb-4 text-5xl font-extrabold text-soGray-darker">
            {code && code}
          </p>
          <p className="mb-2 text-3xl font-medium text-black">
            Page not found We&apos;re sorry,
          </p>
          <p className="text-lg text-black">
            we couldn&apos;t find the page you requested.
          </p>
        </h2>
      </div>
      {/* {message === null ? (
        
      ) : (
        <h2 className="mb-20 text-5xl text-center bg-soGray-normal">
          {message}
        </h2>
      )} */}
      <button className="mb-20 so-button-primary" onClick={() => navigate('/')}>
        go to main
      </button>
    </div>
  );
};

export default NotFound;
