// import { useState } from 'react';
import { Routes, Route, Link } from 'react-router-dom';
import EditProfile from './EditProfile';
import DeleteProfile from './DeleteProfile';

function Settings() {
  // const [currentTab, setCurrentTab] = useState(0);

  // const menuArr = [
  //   { name: 'Edit Profile', content: <EditProfile /> },
  //   { name: 'Delete Profile', content: <DeleteProfile /> },
  // ];

  // const selectMenuHandler = (index) => {
  //   setCurrentTab(index);
  // };

  return (
    <div className="flex">
      <nav className="my-3 ml-3 mr-8 whitespace-nowrap">
        <p className="py-1.5 pr-12 pl-3 text-xs font-bold">
          RERSONAL INFORMATION
        </p>

        <div className="flex flex-col">
          {/* {menuArr.map((ele, index) => {
            return (
              <Link to=`/${ele.name}`>
                <button
                  key={index}
                  className={
                    currentTab === index
                      ? 'py-1.5 pr-12 pl-3 '
                      : 'py-1.5 pr-12 pl-3'
                  }
                  onClick={() => selectMenuHandler(index)}
                >
                  {ele.name}
                </button>
              </Link>
            );
          })} */}
          <ul>
            <li className="py-1.5 pr-12 pl-3">
              <Link to="/mypage/settings/editprofile">Edit Profile</Link>
            </li>
            <li className="py-1.5 pr-12 pl-3">
              <Link to="/mypage/settings/deleteprofile">Delete Profile</Link>
            </li>
          </ul>
        </div>
      </nav>
      {/* {menuArr[currentTab].content} */}
      <Routes>
        <Route exact path="/*" element={<EditProfile />} />
        <Route path="/editprofile" element={<EditProfile />} />
        <Route path="/deleteprofile" element={<DeleteProfile />} />
      </Routes>
    </div>
  );
}

export default Settings;
