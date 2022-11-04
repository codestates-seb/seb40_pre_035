import { useState } from 'react';
import { Routes, Route, Link } from 'react-router-dom';
import EditProfile from './EditProfile';
import DeleteProfile from './DeleteProfile';

const Settings = () => {
  // let [edit, setEdit] = useState(true);
  // let [del, setDel] = useState(false);

  // const editHandler = () => {
  //   edit = true;
  //   del = false;
  //   setEdit(edit);
  //   setDel(del);
  // };
  // const delHandler = () => {
  //   edit = false;
  //   del = true;
  //   setEdit(edit);
  //   setDel(del);
  // };

  return (
    <>
      <ul className="flex mt-2 mb-4">
        <li>
          <Link to="/mypage/activity">
            <button className="m-0.5 py-1.5 px-3 hover:bg-soGray-normal hover:rounded-2xl">
              Activity
            </button>
          </Link>
        </li>
        <li>
          <Link to="/mypage/settings">
            <button className="m-0.5 py-1.5 px-3 bg-primary-400 rounded-2xl hover:bg-primary-700 text-white">
              Settings
            </button>
          </Link>
        </li>
      </ul>
      <Routes>
        <Route exact path="/*" element={<EditProfile />} />
        <Route path="/editprofile" element={<EditProfile />} />
        <Route path="/deleteprofile" element={<DeleteProfile />} />
      </Routes>
    </>
  );
};

export default Settings;
