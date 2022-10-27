import Settings_SideBar from './settings_sidebar';
import EditProfile from './editprofile';
// import DeleteProfile from './deleteprofile';

function Settings() {
  return (
    <div className="flex">
      <Settings_SideBar />
      <EditProfile />
      {/* <DeleteProfile /> */}
    </div>
  );
}

export default Settings;
