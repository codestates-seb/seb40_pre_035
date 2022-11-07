import { Link, useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { EC2 } from '../../util/fetchLogin';

const Profile = () => {
  const { id } = useParams();
  const [idData, setIdData] = useState(null);

  useEffect(() => {
    fetch(`${EC2}/accounts/${id}`)
      .then((res) => {
        if (!res.ok) {
          // error coming back from server
          throw Error('could not fetch the data for that resource');
        }
        return res.json();
      })
      .then((idData) => {
        setIdData(idData);
      })
      .catch((err) => {
        console.error(err.message);
      });
  }, []);

  const logout = () => {
    sessionStorage.clear();
  };

  const login = () => {
    return sessionStorage.userEmail === idData?.email;
  };

  const isLogin = { true: 'flex', false: 'flex invisible' };

  return (
    <div className="flex">
      <img src={idData?.profile} alt="" className="w-32 h-32" />
      <div className="self-center ml-3">
        <div className="mx-2 mb-2 text-4xl font-bold ">{idData?.nickname}</div>

        <ul className={isLogin[login() ?? false]}>
          <li className="px-1.5 rounded hover:bg-soGray-bg">
            {/* <Link to="/">Logout</Link> */}
            <a href="/" onClick={logout}>
              Logout
            </a>
          </li>
          /
          <li className="px-1.5 rounded hover:bg-soGray-bg">
            <Link to={`./settings/editprofile`}>Edit Profile</Link>
            {/* <a href="./settings/editprofile">Edit Profile</a> */}
          </li>
          /
          <li className="px-1.5 rounded hover:bg-soGray-bg">
            <Link to={`./settings/deleteprofile`}>Delete Profile</Link>
            {/* <a href=".settings/deleteprofile">Delete Profile</a> */}
          </li>
        </ul>
      </div>
    </div>
  );
};

export default Profile;
