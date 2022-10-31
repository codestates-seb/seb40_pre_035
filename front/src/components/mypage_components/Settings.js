import { useState } from 'react';
import { Routes, Route, Link } from 'react-router-dom';
import EditProfile from './EditProfile';
import DeleteProfile from './DeleteProfile';

function Settings() {
  let [act, setAct] = useState(true);
  let [set, setSet] = useState(false);

  const actHandler = () => {
    act = true;
    set = false;
    setAct(act);
    setSet(set);
  };
  const setHandler = () => {
    act = false;
    set = true;
    setAct(act);
    setSet(set);
  };

  return (
    <div className="flex">
      <nav className="my-3 ml-3 mr-8 whitespace-nowrap">
        <p className="py-1.5 pr-12 pl-3 text-xs font-bold">
          RERSONAL INFORMATION
        </p>
        <div>
          <ul className="w-10/12">
            <li
              className={
                act
                  ? 'py-1 pr-12 pl-3 m-0.5 bg-primary-400 rounded-2xl hover:bg-primary-700 text-white'
                  : 'py-1 pr-12 pl-3 m-0.5 hover:bg-soGray-normal hover:rounded-2xl'
              }
            >
              <Link to="/mypage/settings/editprofile">
                <button onClick={actHandler}>Edit Profile</button>
              </Link>
            </li>
            <li
              className={
                set
                  ? 'py-1 pr-12 pl-3 m-0.5 bg-primary-400 rounded-2xl hover:bg-primary-700 text-white'
                  : 'py-1 pr-12 pl-3 m-0.5 hover:bg-soGray-normal hover:rounded-2xl'
              }
            >
              <Link to="/mypage/settings/deleteprofile">
                <button onClick={setHandler}>Delete Profile</button>
              </Link>
            </li>
          </ul>
        </div>
      </nav>
      <Routes>
        <Route exact path="/*" element={<EditProfile />} />
        <Route path="/editprofile" element={<EditProfile />} />
        <Route path="/deleteprofile" element={<DeleteProfile />} />
      </Routes>
    </div>
  );
}

export default Settings;
