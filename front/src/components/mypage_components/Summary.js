import { useState, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
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
    fetch(`${BASE_URL}/answers/account/1?page=1&size=5&sort=id%2Cdesc`)
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
    fetch(`${BASE_URL}/questions/account/1?page=1&size=5&sort=id%2Cdesc`)
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
      <div className="w-4/12 m-3">
        <p className="text-2xl font-medium">Stats</p>
        <div className="p-3 m-2 border rounded border-soGray-light">
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
      <div className="flex flex-col justify-between w-8/12 m-3">
        <div>
          <p className="text-2xl font-medium">Answers</p>

          <div className="m-2 border rounded border-soGray-light">
            {answersContent?.map((el, index) => {
              return (
                <div
                  key={index}
                  className="flex p-3 overflow-hidden border-b border-soGray-light whitespace-nowrap text-ellipsis last:border-none"
                >
                  <div
                    className={
                      el.selectedAnswer
                        ? 'text-sm px-1.5 font-medium border  border-soGray-light rounded mx-1.5 bg-secondary-150'
                        : 'text-sm px-1.5 font-medium border  border-soGray-light rounded mx-1.5 '
                    }
                  >
                    <p className="flex items-center justify-center w-6 h-6 text-center">
                      {el.totalVote}{' '}
                    </p>
                  </div>
                  <div className="mb-0.5 pl-1 text-secondary-500 grow overflow-hidden whitespace-nowrap text-ellipsis">
                    <a href={`../question/detail/${el.id}`}>{el.content}</a>
                  </div>
                  <div className="flex items-center justify-center text-sm text-right">
                    {el.createdAt}
                  </div>
                </div>
              );
            })}
            {answersContent === null || answersContent?.length === 0 ? (
              <p className="p-6 m-2 text-center ">
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
        <div>
          <p className="mb-1 text-2xl font-medium">Questions</p>
          <div className="m-2 border rounded border-soGray-light ">
            {questionsContent?.map((el, index) => {
              return (
                <div
                  key={index}
                  className="flex p-3 overflow-hidden border-b border-soGray-light whitespace-nowrap text-ellipsis last:border-none"
                >
                  <div
                    className={
                      el.selectedAnswer
                        ? 'text-sm px-1.5 font-medium border  border-soGray-light rounded mx-1.5 bg-secondary-150'
                        : 'text-sm px-1.5 font-medium border  border-soGray-light rounded mx-1.5 '
                    }
                  >
                    <p className="flex items-center justify-center w-6 h-6 text-center ">
                      {el.totalVote}
                    </p>
                  </div>
                  <div className="mb-0.5 pl-1 text-secondary-500 grow overflow-hidden whitespace-nowrap text-ellipsis">
                    <a href={`../question/detail/${el.id}`}>{el.content}</a>
                  </div>
                  <div className="flex items-center justify-center text-sm text-right">
                    {el.createdAt}
                  </div>
                </div>
              );
            })}
            {questionsContent === null || questionsContent?.length === 0 ? (
              <p className="p-6 m-2 text-center ">
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
