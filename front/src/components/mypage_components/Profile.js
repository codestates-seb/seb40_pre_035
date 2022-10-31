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
            {/* <Link to="/">Logout</Link> */}
            Logout
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
}

export default Profile;
