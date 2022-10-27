function EditProfile() {
  return (
    <div className="w-full">
      <div className="pb-4 mb-6 border-b">
        <h1 className="text-3xl font-medium">Edit your Profile</h1>
      </div>
      <form>
        <div className="mb-2 text-2xl font-medium"> Public information </div>
        <div className="border rounded p-6 mb-7">
          <div className="font-semibold">Profile image</div>
          <div className="relative">
            <img src="../../public/logo192.png" alt="" className="w-40 h-40" />
            <a
              href="http://localhost:3000/"
              className="block bg-neutral-600/50 text-white py-2 w-40 h-9 text-center text-sm absolute bottom-px"
            >
              Change picture
            </a>
          </div>
          <div className="font-semibold text-lg my-0.5">Display name</div>
          <input type="text" className="border rounded p-1 w-3/6" />
          <div className="font-semibold text-lg my-0.5">New password</div>
          <input type="text" className="border rounded p-1 w-3/6" />
          <div className="font-semibold text-lg my-0.5">
            New password (again)
          </div>
          <input type="text" className="border rounded p-1 w-3/6" />
          <p className="mt-2 text-xs text-gray-600">
            passwords must contain at least eight characters,
            <br /> including at least 1letter and 1 number.
          </p>
        </div>
      </form>
      <div>
        <button className="m-1.5 p-2.5 bg-blue-600 rounded text-white font-medium">
          Save profile
        </button>
        <button className="m-1.5 p-2.5 rounded text-blue-600 font-medium">
          Cancel
        </button>
      </div>
    </div>
  );
}

export default EditProfile;
