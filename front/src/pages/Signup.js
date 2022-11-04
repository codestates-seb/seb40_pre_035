import SignupInfo from '../components/signup/SignupInfo';
import SOInfo from '../components/signup/SOInfo';
function Signup() {
  return (
    <div className="flex justify-center w-full h-[calc(100vh-180px)] align-center bg-soGray-bg">
      <div className="flex items-center justify-center w-full h-full mx-32">
        <SOInfo />
        <SignupInfo />
      </div>
    </div>
  );
}

export default Signup;
