import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { BASE_URL } from '../../util/api';

const EditProfile = () => {
  const [data, setData] = useState();

  const onEdit = useEffect(() => {
    fetch(`${BASE_URL}/accounts/1`, {
      method: 'PATCH',
      header: {
        'Content-Type': 'application/json;charset=UTF-8',
      },
      body: JSON.stringify({
        // title,
        // body,
        // userId,
      }),
    })
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
    <div className="w-full">
      <div className="pb-4 mb-6 border-b border-soGray-normal">
        <h1 className="text-xxl font-medium">Edit your Profile</h1>
      </div>
      <form>
        <div className="mb-2 text-2xl font-medium"> Public information </div>
        <div className="p-6 border rounded border-soGray-normal mb-7">
          <div className="font-semibold">Profile image</div>
          <div className="relative">
            <img src="../../public/logo192.png" alt="" className="w-40 h-40" />
            <a
              href="http://localhost:3000/"
              className="absolute block w-40 py-2 text-sm text-center text-white bg-soGray-normal h-9 bottom-px"
            >
              Change picture
            </a>
          </div>
          <div className="font-semibold text-lg my-0.5">Display name</div>
          <input
            type="text"
            className="w-3/6 p-1 border rounded border-soGray-normal"
          />
          <div className="font-semibold text-lg my-0.5">New password</div>
          <input
            type="text"
            className="w-3/6 p-1 border rounded border-soGray-normal"
          />
          <div className="font-semibold text-lg my-0.5">
            New password (again)
          </div>
          <input
            type="text"
            className="w-3/6 p-1 border rounded border-soGray-normal"
          />
          <p className="mt-2 text-xs text-gray-600">
            passwords must contain at least eight characters,
            <br /> including at least 1letter and 1 number.
          </p>
        </div>
      </form>
      <div>
        <button className="m-1.5 p-2.5 bg-buttonPrimary rounded text-white font-medium">
          <Link to="/mypage">Save profile</Link>
        </button>
        <button className="m-1.5 p-2.5 rounded text-blue-600 font-medium text-buttonPrimary">
          <Link to="/mypage">Cancel</Link>
        </button>
      </div>
    </div>
  );
};

export default EditProfile;
