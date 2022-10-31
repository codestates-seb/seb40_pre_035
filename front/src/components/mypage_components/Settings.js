import { useState } from 'react';
import EditProfile from './EditProfile';
import DeleteProfile from './DeleteProfile';

function Settings() {
  const [currentTab, setCurrentTab] = useState(0);

  const menuArr = [
    { name: 'Edit Profile', content: <EditProfile /> },
    { name: 'Delete Profile', content: <DeleteProfile /> },
  ];

  const selectMenuHandler = (index) => {
    setCurrentTab(index);
  };

  return (
    <div className="flex">
      <nav className="my-3 ml-3 mr-8">
        <p className="py-1.5 pr-12 pl-3 text-xs font-bold">
          RERSONAL INFORMATION
        </p>

        <div className="flex flex-col">
          {menuArr.map((ele, index) => {
            return (
              <button
                key={index}
                className={
                  currentTab === index
                    ? 'py-1.5 pr-12 pl-3'
                    : 'py-1.5 pr-12 pl-3'
                }
                onClick={() => selectMenuHandler(index)}
              >
                {ele.name}
              </button>
            );
          })}
          {/* <li className="py-1.5 pr-12 pl-3">Summary</li>
          <li className="py-1.5 pr-12 pl-3">Answers</li>
          <li className="py-1.5 pr-12 pl-3">Questions</li> */}
        </div>

        {/* <ul className="">
          <li className="py-1.5 pr-12 pl-3">Edit Profile</li>
          <li className="py-1.5 pr-12 pl-3">Delete Profile</li>
        </ul> */}
      </nav>
      {menuArr[currentTab].content}
    </div>
  );
}

export default Settings;
