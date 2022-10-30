import '../../components/common.css';
const UserInfo = () => {
  return (
    <div className="flex-col w-1/3 my-5 ml-2 mr-10 align-middle justify-items-center">
      <div className="px-5 pt-3 pb-10 bg-white rounded-md drop-shadow-xl">
        <div className="flex-col justify-center mx-2 my-3">
          <div className="font-bold">Display name</div>
          <input
            type="text"
            className="w-full px-2 py-1 border rounded border-soGray-light"
          ></input>
        </div>
        <div className="flex-col justify-center mx-2 my-3">
          <div className="font-bold">Email</div>
          <input
            type="text"
            className="w-full px-2 py-1 border rounded border-soGray-light"
          ></input>
        </div>
        <div className="flex-col justify-center mx-2 my-3">
          <div className="font-bold">Password</div>
          <input
            type="text"
            className="w-full px-2 py-1 border rounded border-soGray-light"
          ></input>
          <p className="text-sm text-soGray-normal">
            Passwords must contain at least eight characters, including at least
            1 letter and 1 number.
          </p>
        </div>
        <button className="w-full mt-10 so-button-primary">Sign up</button>
      </div>
      <div className="flex justify-center my-5">
        <p className="mx-1">Already have an account?</p>
        <a
          href="./login"
          className="text-secondary-600 hover:text-secondary-300"
        >
          Log in
        </a>
      </div>
    </div>
  );
};

export default UserInfo;
