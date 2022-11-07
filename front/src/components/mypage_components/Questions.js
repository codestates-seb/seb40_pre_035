import { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';

const Questions = ({ idData }) => {
  const { id } = useParams();
  const [data, setData] = useState(null);

  useEffect(() => {
    fetch(`/questions/account/${id}?page=1&size=99&sort=id%2Cdesc`)
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

  const answerType = (count, selected) => {
    if (selected) return 'so-answers-type3';
    if (+count > 0) return 'so-answers-type2';
    else return 'font-medium text-soGray-light';
  };

  // const login = () => {
  //   return sessionStorage.userEmail === idData?.email;
  // };

  // const isLogin = {
  //   true: 'p-3 border-t border-soGray-normal',
  //   false: 'p-3 hidden border-t border-soGray-normal',
  // };
  const options = {
    month: 'short',
    day: 'numeric',
  };
  const hourOptions = {
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
    hour12: false,
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
              <div className="flex items-center text-[13px] mt-1 mr-6 basis-[100px]">
                <div className="pr-2 font-medium">{el.totalVote} votes</div>
                <div
                  className={`${answerType(el.answerCount, el.selectedAnswer)}`}
                >
                  {el.selectedAnswer ? (
                    <svg
                      aria-hidden="true"
                      className="mr-2 svg-icon iconCheckmarkSm"
                      width="14"
                      height="14"
                      viewBox="0 0 14 14"
                      fill="#fff"
                    >
                      <path d="M13 3.41 11.59 2 5 8.59 2.41 6 1 7.41l4 4 8-8Z"></path>
                    </svg>
                  ) : (
                    ''
                  )}
                  <span>{el.answerCount} answers</span>
                </div>
              </div>
              <div className="block max-w-3xl py-0.5 overflow-hidden whitespace-nowrap text-ellipsis text-secondary-500 ">
                <a href={`../question/${el.id}`}>
                  {el.content &&
                    el.content.replace(/"/g, '').replace(/<[^>]*>?/g, '')}
                </a>
              </div>
              <div className="text-sm text-right">
                asked{' '}
                {new Date(el.createdAt).toLocaleDateString('en-US', options)} at{' '}
                {new Date(el.createdAt).toLocaleDateString(
                  'en-US',
                  hourOptions
                )}
              </div>
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
        {/* <div className={isLogin[login() ?? false]}>
          <Link to="/" className="text-secondary-500">
            Deleted answers
          </Link>
        </div> */}
      </div>
    </div>
  );
};

export default Questions;
