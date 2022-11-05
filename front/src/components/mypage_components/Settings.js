import { useState } from 'react';
import { Routes, Route, Link, useParams } from 'react-router-dom';
import EditProfile from './EditProfile';
import DeleteProfile from './DeleteProfile';

const Settings = () => {
  const { id } = useParams();
  return (
    <>
      <ul className="flex mt-2 mb-4">
        <li>
          <Link to={`/mypage/${id}/activity`}>
            <button className="m-0.5 py-1.5 px-3 hover:bg-soGray-normal hover:rounded-2xl">
              Activity
            </button>
          </Link>
        </li>
        <li>
          <Link to={`/mypage/${id}/settings`}>
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
