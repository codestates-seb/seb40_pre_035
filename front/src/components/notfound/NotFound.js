import { useNavigate } from 'react-router-dom';

const NotFound = ({ message }) => {
  const navigate = useNavigate();

  return (
    <div className="flex flex-col items-center justify-center w-full mt-[100px]">
      {message === null ? (
        <h2 className="mb-10 text-3xl leading-10 text-center text-soGray-normal">
          잘못된 경로로 접속하였습니다. <br />
          다시 메인으로 이동해 주세요.
        </h2>
      ) : (
        <h2 className="mb-20 text-5xl text-center bg-soGray-normal">
          {message}
        </h2>
      )}
      <button className="mb-20 so-button-primary" onClick={() => navigate('/')}>
        go to main
      </button>
    </div>
  );
};

export default NotFound;
