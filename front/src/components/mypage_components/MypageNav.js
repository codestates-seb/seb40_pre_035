import { useState } from 'react';
import { Routes, Route, Link } from 'react-router-dom';
import Activitise from './Activities';
import Settings from './Settings';
import MyPage from '../../pages/MyPage';

const MyPageNav = () => {
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
    <div>
      <ul className="flex mt-2 mb-4" role="group">
        <li>
          <Link to="/mypage/activity">
            <button
              onClick={actHandler}
              className={
                act
                  ? 'm-0.5 py-1.5 px-3 bg-primary-400 rounded-2xl hover:bg-primary-700 text-white'
                  : 'm-0.5 py-1.5 px-3 hover:bg-soGray-normal hover:rounded-2xl'
              }
            >
              Activity
            </button>
          </Link>
        </li>
        <li>
          <Link to="/mypage/settings">
            <button
              onClick={setHandler}
              className={
                set
                  ? 'm-0.5 py-1.5 px-3 bg-primary-400 rounded-2xl hover:bg-primary-700  text-white'
                  : 'm-0.5 py-1.5 px-3 hover:bg-soGray-normal hover:rounded-2xl'
              }
            >
              Settings
            </button>
          </Link>
        </li>
      </ul>
      <Routes>
        <Route exact path="/*" element={<Activitise />} />
        <Route path="/activity" element={<Activitise />} />
        <Route path="/settings/*" element={<Settings />} />
      </Routes>
    </div>
  );
};

export default MyPageNav;
