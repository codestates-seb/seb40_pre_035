// import React from 'react'

function Profile() {
  return (
    <div className="flex">
      <div>
        <img src="../../public/logo192.png" alt="" className="w-32 h-32" />
      </div>
      <div className="self-center">
        <div className=" text-4xl font-bold mx-1.5 mb-2">김민아</div>
        <ul className="flex">
          <li className="px-1.5">
            <a href="http://localhost:3000/">Logout</a>
          </li>
          /
          <li className="px-1.5">
            <a href="http://localhost:3000/">Edit Profile</a>
          </li>
          /
          <li className="px-1.5">
            <a href="http://localhost:3000/">Delete Profile</a>
          </li>
        </ul>
      </div>
    </div>
  );
}

export default Profile;
