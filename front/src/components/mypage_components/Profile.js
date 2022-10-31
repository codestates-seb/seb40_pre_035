function Profile() {
  return (
    <div className="flex">
      <div>
        <img src="../../public/logo192.png" alt="" className="w-32 h-32" />
      </div>
      <div className="self-center ml-3">
        <div className="mx-2 mb-2 text-4xl font-bold ">김민아</div>
        <ul className="flex">
          <li className="px-1.5">
            {/* <Link to="/">Logout</Link> */}
            <a href="/">Logout</a>
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
