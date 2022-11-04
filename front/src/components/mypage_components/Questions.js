import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { BASE_URL } from '../../util/api';

const Questions = () => {
  const [data, setData] = useState(null);

  useEffect(() => {
    fetch(`${BASE_URL}/questions/account/1?page=1&size=99&sort=id%2Cdesc`)
      .then((res) => {
        if (!res.ok) {
          // error coming back from server
          throw Error('could not fetch the data for that resource');
        }
        return res.json();
      })
      .then((data) => {
        setData(data);
      })
      .catch((err) => {
        console.error(err.message);
      });
  }, []);

  const isLogin = {
    true: 'p-3 border-t border-soGray-normal',
    false: 'p-3 hidden border-t border-soGray-normal',
  };

  return (
    <div className="w-full m-3">
      <p className="mb-2 text-2xl font-medium">
        {data?.content.length} Answers
      </p>{' '}
      <div className="w-full border rounded border-soGray-normal">
        {data?.content.map((el, index) => {
          return (
            <div
              key={index}
              className="p-4 border-t border-soGray-normal first:border-none"
            >
              <div className="text-xs font-medium">{el.totalVote} votes</div>
              <div className="block max-w-3xl py-0.5 overflow-hidden whitespace-nowrap text-ellipsis text-secondary-500 ">
                <a href={`../question/detail/${el.id}`}>{el.content}</a>
              </div>
              <div className="text-sm text-right">asked {el.createdAt}</div>
            </div>
          );
        })}
        {data?.content === null || data?.content.length === 0 ? (
          <p className="p-12 text-center ">
            You have not{' '}
            <Link to="/" className="text-secondary-500">
              asked
            </Link>{' '}
            any questions
          </p>
        ) : (
          ''
        )}
        <div className={isLogin[true ?? false]}>
          <Link to="/" className="text-secondary-500">
            Deleted answers
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Questions;
