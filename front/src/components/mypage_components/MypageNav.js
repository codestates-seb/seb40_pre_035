import { Routes, Route, Link } from 'react-router-dom';
import Activitise from './Activities';
import Settings from './Settings';

function MyPageNav() {
  return (
    <div>
      <ul className="flex mt-2 mb-4">
        <li className="m-0.5 py-1.5 px-3">
          <Link to="/mypage/activity">Activity</Link>
        </li>
        <li className="m-0.5 py-1.5 px-3">
          <Link to="/mypage/settings">Settings</Link>
        </li>
      </ul>
      <Routes>
        <Route exact path="/*" element={<Activitise />} />
        <Route path="/activity" element={<Activitise />} />
        <Route path="/settings/*" element={<Settings />} />
      </Routes>
    </div>
  );
}

export default MyPageNav;
