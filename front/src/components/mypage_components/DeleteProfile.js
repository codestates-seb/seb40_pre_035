import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { BASE_URL } from '../../util/api';

const DeleteProfile = () => {
  const onRemove = () => {
    fetch(`${BASE_URL}/accounts/1`, {
      method: 'DELETE',
      // headers: {
      //   Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsImlkIjozLCJ1c2VybmFtZSI6ImRlbGV0ZUBnbWFpbC5jb20iLCJzdWIiOiIzIiwiaWF0IjoxNjY3Mzk4NTM5LCJleHAiOjE2Njc0MDAzMzl9.DYgsA5fGaNixvFNeKu31ix3JEfdobrRK0MiXBYSeMS0'
      // }
    }).then();
  };

  const [checkBox, setCheckBox] = useState([]);
  const changeHandler = (checked, id) => {
    if (checked) {
      setCheckBox([...checkBox, id]);
    } else {
      setCheckBox(checkBox.filter((button) => button !== id));
    }
  };

  const disabled = !(checkBox.length === 1);

  return (
    <div>
      <div className="pb-4 mb-6 border-b border-soGray-normal">
        <h1 className="text-2xl ">Delete Profile</h1>
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
          <input
            type="checkbox"
            id="check"
            className="my-3 mx-1.5 flex"
            onChange={(e) => {
              changeHandler(e.currentTarget.checked, 'check');
            }}
            checked={checkBox.includes('check') ? true : false}
          />
          <p className="p-0.5 m-1">
            I have read the information stated above and understand the
            implications of having my profile deleted. I wish to proceed with
            the deletion of my profile.
          </p>
        </div>
        {/* <Link to="/"> */}
        <button
          className={
            !disabled
              ? ' bg-danger-400 text-white p-2.5 rounded'
              : ' bg-danger-700 text-white p-2.5 rounded '
          }
          disabled={disabled}
          onClick={onRemove}
        >
          Delete profile
        </button>
        {/* </Link> */}
      </form>
    </div>
  );
};

export default DeleteProfile;
