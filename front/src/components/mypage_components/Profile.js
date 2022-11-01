import { useState, useEffect } from 'react';
import { BASE_URL } from '../../util/api';

const Profile = () => {
  const [data, setData] = useState(null);

  useEffect(() => {
    fetch(`${BASE_URL}/accounts/1`)
      .then((res) => {
        if (!res.ok) {
          // error coming back from server
          throw Error('could not fetch the data for that resource');
        }
        return res.json();
      })
      .then((data) => {
        setData(data);
      })
      .catch((err) => {
        console.error(err.message);
      });
  }, []);

  return (
    <div className="flex">
      <img src={data?.profile} alt="" className="w-32 h-32" />
      <div className="self-center ml-3">
        <div className="mx-2 mb-2 text-4xl font-bold ">{data?.nickname}</div>
        <ul className="flex">
          <li className="px-1.5">
            {/* <Link to="/">Logout</Link> */}
            <a href="/">Logout</a>
          </li>
          /
          <li className="px-1.5">
            {/* <Link to="/settings">Edit Profile</Link> */}
            <a href="/mypage/settings/editprofile">Edit Profile</a>
          </li>
          /
          <li className="px-1.5">
            {/* <Link to="/settings">Delete Profile</Link> */}
            <a href="/mypage/settings/deleteprofile">Delete Profile</a>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default Profile;
