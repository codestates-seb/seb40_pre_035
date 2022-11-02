import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { BASE_URL } from '../../util/api';

const Summary = () => {
  const [data, setData] = useState(null);
  const [isPending, setIsPending] = useState(true);

  useEffect(() => {
    fetch(`${BASE_URL}/questions/account/1?page=1&size=5&sort=id%2Cdesc`)
      .then((res) => {
        if (!res.ok) {
          // error coming back from server
          throw Error('could not fetch the data for that resource');
        }
        return res.json();
      })
      .then((data) => {
        setIsPending(false);
        setData(data);
      })
      .catch((err) => {
        setIsPending(false);
        console.error(err.message);
      });
  }, []);

  if (isPending) {
    return <div>Loading...</div>;
  }

  console.log(data.content.reduce((pre, cur) => pre.totalVote + cur.totalVote));

  return (
    <div className="flex w-full">
      <div className="w-3/12 m-3">
        <p className="text-2xl font-medium">Stats</p>
        <div className="p-3 m-2 border rounded border-soGray-normal bg-soGray-headerbg">
          <div className="m-3 ">
            <p className="text-xl font-medium">0</p>
            total votes
          </div>
          <div className="m-3">
            <p className="text-xl font-medium">0</p>
            answers
          </div>
          <div className="m-3">
            <p className="text-xl font-medium">0</p>
            questions
          </div>
        </div>
      </div>
      <div className="flex flex-col justify-between w-9/12 m-3">
        <div>
          <p className="text-2xl font-medium">Answers</p>
          <p className="p-6 m-2 text-center border rounded border-soGray-normal bg-soGray-headerbg">
            You have not{' '}
            <Link to="/" className="text-secondary-350">
              answered
            </Link>{' '}
            any questions
          </p>
        </div>
        <div className="">
          <p className="text-2xl font-medium">Questions</p>
          <p className="p-6 m-2 text-center border rounded border-soGray-normal bg-soGray-headerbg">
            You have not{' '}
            <Link to="/" className="text-secondary-350">
              asked
            </Link>{' '}
            any questions
          </p>
        </div>
      </div>
    </div>
  );
};

export default Summary;
