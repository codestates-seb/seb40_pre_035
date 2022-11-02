import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { BASE_URL } from '../util/api';
import relTimeFormat from '../util/relativeTimeFormat';
import Votes from '../components/questions/Votes';
import AnswerList from '../components/answers/AnswerList';
import Loading from '../components/loading/Loading';
import { Viewer } from '@toast-ui/react-editor';
import '../components/common.css';

function QuestionDetail() {
  const { id } = useParams();
  // const { data, isPending, error } = useFetch(`${BASE_URL}/questions/${id}`);
  const navigate = useNavigate();

  const [data, setData] = useState(null);
  const [isPending, setIsPending] = useState(true);

  useEffect(() => {
    fetch(`${BASE_URL}/questions/${id}`)
      .then((response) => {
        if (!response.ok) {
          throw Error('could not fetch the data for that resource');
        }
        return response.json();
      })
      .then((data) => {
        setIsPending(false);
        setData(data);
      })
      .catch((error) => {
        setIsPending(false);
        throw Error(error.message);
      });
  }, []);

  const onClickAskQuestion = () => {
    navigate('/questioncreate');
  };

  if (isPending) {
    return (
      <div>
        <Loading />
      </div>
    );
  }

  return (
    <main className="p-4 content">
      {console.log(data)}
      {data && (
        <>
          <div className="mb-3 border-b question-header border-soGray-light">
            <div className="flex justify-between mb-4 question-title">
              <h2 className="row-auto pr-2 text-2xl font-medium break-words">
                {data.title}
              </h2>
              <div className="flex justify-end basis-52">
                <button
                  className="so-button-primary"
                  onClick={onClickAskQuestion}
                >
                  Ask Question
                </button>
              </div>
            </div>

            <div className="flex flex-row mb-4 text-sm user-info align-center">
              <span className="flex mr-4">
                <img
                  src={`${data.account.profile}`}
                  alt={`${data.account.nickname}'s user avatar`}
                  width="16"
                  height="16"
                  className="mr-2"
                />
                <span className="text-soGray-darker">
                  {data.account.nickname}
                </span>
              </span>
              <time className="s-user-card--time">
                <span className="mr-1 text-soGray-normal">Asked</span>
                <span className="text-soGray-darker">
                  {relTimeFormat(data.createdAt)}
                </span>
              </time>
            </div>
          </div>
          <div className="mb-10 question-body">
            <div
              role="main"
              aria-label="question and answers"
              className="flex flex-row"
            >
              <div className="mr-4 question-votes">
                <Votes total={data.totalVote} />
              </div>
              <div className="flex-auto question-content so-editor">
                <Viewer initialValue={data.content} />
              </div>
            </div>
          </div>
          <AnswerList questionId={data.id} />
        </>
      )}
    </main>
  );
}

export default QuestionDetail;
