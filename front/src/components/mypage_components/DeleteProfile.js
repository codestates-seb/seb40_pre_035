function DeleteProfile() {
  return (
    <div>
      <div className="pb-4 mb-6 border-b">
        <h1 className="text-xxl ">Delete Profile</h1>
      </div>
      <p className="mb-4">
        Before confirming that you would like your profile deleted, we&#39;d
        like to take a moment to explain the implications of deletion:
      </p>
      <ul className="mb-4 ml-8">
        <li className="mb-2 list-disc">
          Deletion is irreversible, and you will have no way to regain any of
          your original content, should this deletion be carried out and you
          change your mind later on.
        </li>
        <li className="mb-2 list-disc">
          Your questions and answers will remain on the site, but will be
          disassociated and anonymized (the author will be listed as
          &#34;user20298961&#34;) and will not indicate your authorship even if
          you later return to the site.
        </li>
      </ul>
      <p>
        Confirming deletion will only delete your profile on Stack Overflow - it
        will not affect any of your other profiles on the Stack Exchange
        network. If you want to delete multiple profiles, you&#39;ll need to
        visit each site separately and request deletion of those individual
        profiles.
      </p>
      <form>
        <div className="flex items-start mb-6">
          <input type="checkbox" className="my-3 mx-1.5 flex" />
          <p className="p-0.5 m-1">
            I have read the information stated above and understand the
            implications of having my profile deleted. I wish to proceed with
            the deletion of my profile.
          </p>
        </div>
        <button className=" bg-danger-400 text-white p-2.5 rounded">
          Delete profile
        </button>
      </form>
    </div>
  );
}

export default DeleteProfile;
