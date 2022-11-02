import { useState, useEffect } from 'react';
import { BASE_URL } from '../../util/api';

function QuestionFilter(questionId) {
  const [questionList, setQuestionList] = useState([]);
  const [isPending, setIsPending] = useState(true);

  useEffect(() => {
    fetch(`${BASE_URL}/questions?page=1&size=10&sort=id%2Cdesc`)
      .then((response) => {
        if (!response.ok) {
          throw Error('could not fetch the data for that resource');
        }
        return response.json();
      })
      .then((data) => {
        setIsPending(false);
        setQuestionList(data.content);
      })
      //통신이 되면 json을 스테이츠로 변환
      .catch((error) => {
        setIsPending(false);
        throw Error(error.message);
      });
  }, [questionId]);

  if (isPending) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <div
        className="inline-flex rounded-md shadow-sm object-right"
        role="group"
      >
        <div>
          <button
            type="button"
            onClick={() =>
              questionList.sort(function (a, b) {
                return a.createdAt - b.createdAt;
              })
            }
            className="py-2 px-4 text-sm font-regular text-black
            bg-white rounded-l border border-soGray-normal hover:bg-soGray-light  focus:z-10  focus:bg-soGray-normal"
          >
            Newest
          </button>
        </div>
        <div>
          <button
            type="button"
            onClick={() =>
              questionList.sort(function (a, b) {
                return a.totalVote - b.totalVote;
              })
            }
            className="py-2 px-4 text-sm font-regular text-black
            bg-white rounded-lr border border-soGray-normal hover:bg-soGray-light focus:bg-soGray-normal focus:z-10 "
          >
            Vote
          </button>
        </div>
        <div>
          <button
            type="button"
            onClick={() =>
              questionList.sort(function (a, b) {
                return a.answerCount - b.answerCount;
              })
            }
            className="py-2 px-4 text-sm font-regular text-black
            bg-white rounded-r border focus:z-10 border-soGray-normal hover:bg-soGray-light focus:bg-soGray-normal"
          >
            Unanswered
          </button>
        </div>
      </div>
    </div>
  );
}

export default QuestionFilter;
