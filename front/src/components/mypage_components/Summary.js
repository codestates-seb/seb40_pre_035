import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { BASE_URL } from '../../util/api';

const Summary = () => {
  const [profile, setProfile] = useState(null);
  const [answers, setAnswers] = useState(null);
  const [questions, setQuestions] = useState(null);

  useEffect(() => {
    fetch(`${BASE_URL}/accounts/1`)
      .then((res) => {
        if (!res.ok) {
          // error coming back from server
          throw Error('could not fetch the data for that resource');
        }
        return res.json();
      })
      .then((profile) => {
        setProfile(profile);
      })
      .catch((err) => {
        console.error(err.message);
      });
  }, []);

  useEffect(() => {
    fetch(`${BASE_URL}/answers/account/1?page=1&size=3&sort=id%2Cdesc`)
      .then((res) => {
        if (!res.ok) {
          // error coming back from server
          throw Error('could not fetch the data for that resource');
        }
        return res.json();
      })
      .then((answers) => {
        setAnswers(answers);
      })
      .catch((err) => {
        console.error(err.message);
      });
  }, []);

  const answersContent = answers?.content;

  useEffect(() => {
    fetch(`${BASE_URL}/questions/account/1?page=1&size=3&sort=id%2Cdesc`)
      .then((res) => {
        if (!res.ok) {
          // error coming back from server
          throw Error('could not fetch the data for that resource');
        }
        return res.json();
      })
      .then((questions) => {
        setQuestions(questions);
      })
      .catch((err) => {
        console.error(err.message);
      });
  }, []);

  const questionsContent = questions?.content;

  // console.log(data.content.reduce((pre, cur) => pre.totalVote + cur.totalVote));

  return (
    <div className="flex w-full">
      <div className="w-3/12 m-3">
        <p className="text-2xl font-medium">Stats</p>
        <div className="p-3 m-2 border rounded border-soGray-normal bg-soGray-headerbg">
          <div className="m-3 ">
            <p className="text-xl font-medium"> {profile?.totalVotes} </p>
            total votes
          </div>
          <div className="m-3">
            <p className="text-xl font-medium">{profile?.totalAnswers}</p>
            answers
          </div>
          <div className="m-3">
            <p className="text-xl font-medium">{profile?.totalQuestions}</p>
            questions
          </div>
        </div>
      </div>
      <div className="flex flex-col justify-between w-9/12 m-3">
        <div>
          <p className="text-2xl font-medium">Answers</p>

          <div className="m-2 border rounded border-soGray-normal bg-soGray-headerbg">
            {answersContent?.map((el, index) => {
              return (
                <div
                  key={index}
                  className="p-3 overflow-hidden border-b border-soGray-normal whitespace-nowrap text-ellipsis last:border-none "
                >
                  <div className="text-xs font-medium">
                    {el.totalVote} votes
                  </div>
                  <div className="">{el.content}</div>
                  <div className="text-sm text-right">
                    createdAt {el.createdAt}
                  </div>
                </div>
              );
            })}
            {answersContent === null ? (
              <p className="p-6 m-2 text-center border rounded border-soGray-normal bg-soGray-headerbg">
                You have not{' '}
                <Link to="/" className="text-secondary-350">
                  answered
                </Link>{' '}
                any Answers
              </p>
            ) : (
              ''
            )}
          </div>
        </div>
        <div className="">
          <p className="mb-1 text-2xl font-medium">Questions</p>
          <div className="m-2 border rounded border-soGray-normal bg-soGray-headerbg">
            {questionsContent?.map((el, index) => {
              return (
                <div
                  key={index}
                  className="p-3 overflow-hidden border-b border-soGray-normal whitespace-nowrap text-ellipsis last:border-none "
                >
                  <div className="text-xs font-medium">
                    {el.totalVote} votes
                  </div>
                  <div className="">{el.content}</div>
                  <div className="text-sm text-right">
                    createdAt {el.createdAt}
                  </div>
                </div>
              );
            })}
            {questionsContent === null ? (
              <p className="p-6 m-2 text-center border rounded border-soGray-normal bg-soGray-headerbg ">
                You have not{' '}
                <Link to="/" className="text-secondary-500">
                  answered
                </Link>{' '}
                any questions
              </p>
            ) : (
              ''
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Summary;
