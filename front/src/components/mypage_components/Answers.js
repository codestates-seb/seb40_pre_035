import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { BASE_URL } from '../../util/api';

const Answers = () => {
  const [data, setData] = useState(null);
  const [isPending, setIsPending] = useState(true);

  useEffect(() => {
    fetch(`${BASE_URL}/answers/account/1?page=1&size=5&sort=id%2Cdesc`)
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

  let content = data.content;

  return (
    <div className="w-full m-3">
      <p className="mb-2 text-2xl font-medium">0 Answers</p>
      <div className="border rounded border-soGray-normal">
        {content.map((el, index) => {
          return (
            <div key={index} className="p-3 border-b border-soGray-normal">
              <div className="text-xs font-medium">{el.totalVote} votes</div>
              <div className="">{el.content}</div>
              <div className="text-sm text-right">createdAt {el.createdAt}</div>
            </div>
          );
        })}
        {content === null ? (
          <p className="p-12 m-2 text-center ">
            You have not{' '}
            <Link to="/" className="text-secondary-500">
              answered
            </Link>{' '}
            any questions
          </p>
        ) : (
          ''
        )}
        <div className="p-3 ">
          <Link to="/" className="text-secondary-500">
            Deleted answers
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Answers;
