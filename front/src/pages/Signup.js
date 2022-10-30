import UserInfo from '../components/signup/UserInfo';
import SOInfo from '../components/signup/SOInfo';
function Signup() {
  return (
    <div className="flex justify-center w-full h-full align-center bg-soGray-bg">
      <div className="flex w-full h-full mx-32 my-20">
        <SOInfo />
        <UserInfo />
      </div>
    </div>
  );
}

export default Signup;
