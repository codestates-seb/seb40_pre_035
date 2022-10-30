import '../../components/common.css';
const LoginInfo = () => {
  return (
    <div className="flex-col justify-center my-5 align-middle">
      <div className="px-5 pt-3 pb-10 bg-white rounded-md drop-shadow-xl">
        <div className="flex-col justify-center mx-2 my-3">
          <div className="font-bold">Email</div>
          <input
            type="text"
            className="w-full px-2 py-1 border rounded border-soGray-light"
          ></input>
        </div>
        <div className="flex-col justify-center mx-2 my-3">
          <div className="flex">
            <div className="font-bold">Password</div>
            <a
              href="./login"
              className="ml-auto align-middle text-secondary-500 hover:text-secondary-300"
            >
              Forgot password?
            </a>
          </div>

          <input
            type="password"
            className="w-full px-2 py-1 border rounded border-soGray-light"
          ></input>
        </div>
        <button className="w-full mt-10 so-button-primary">Log in</button>
      </div>
      <div className="flex justify-center my-10">
        <p className="mx-1">{`Don't have an account?`}</p>
        <a
          href="./login"
          className="text-secondary-600 hover:text-secondary-300"
        >
          Sign up
        </a>
      </div>
    </div>
  );
};
export default LoginInfo;
