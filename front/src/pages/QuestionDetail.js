import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import relTimeFormat from '../util/relativeTimeFormat';
import Votes from '../components/questions/Votes';
import AnswerList from '../components/answers/AnswerList';
import QuestionDeleteModal from '../components/questions/QustionDeleteModal';
import Loading from '../components/loading/Loading';
import { Viewer } from '@toast-ui/react-editor';
import '../components/common.css';

function QuestionDetail() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [data, setData] = useState(null);
  const [isPending, setIsPending] = useState(true);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const showModalDelete = () => {
    setIsModalOpen(true);
  };

  const onClickEdit = (id) => {
    navigate(`/question/update/${id}`);
  };

  const onClickAskQuestion = () => {
    navigate('/question/create');
  };

  useEffect(() => {
    fetch(`/questions/${id}`)
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

  if (isPending) {
    return (
      <div>
        <Loading />
      </div>
    );
  }

  return (
    <div className="flex flex-row flex-auto">
      {data && (
        <div className="so-main-content max-w-none">
          <div className="px-8 py-8 mb-3 border-b question-header border-soGray-light">
            <div className="flex justify-between mb-4 question-title">
              <h2 className="row-auto pr-2 font-medium leading-tight break-words text-xxl">
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
              <time className="mr-4 s-user-card--time">
                <span className="mr-1 text-soGray-normal">Asked</span>
                <span
                  className="text-soGray-darker"
                  title={`${data.createdAt.split('T')[0]} ${
                    data.createdAt.split('T')[1].split('.')[0]
                  }`}
                >
                  {relTimeFormat(data.createdAt)}
                </span>
              </time>
              <div>
                <button onClick={onClickEdit} data-target={data.id}>
                  Edit
                </button>
                <button onClick={showModalDelete} className="">
                  Delete
                </button>
              </div>
            </div>
          </div>
          <div className="mb-10 question-body">
            <div
              aria-label="question and answers"
              className="flex flex-row p-6"
            >
              <div className="flex justify-center mr-4 question-votes">
                <Votes total={data.totalVote} />
              </div>
              <div className="flex-auto question-content so-editor">
                <Viewer initialValue={JSON.parse(data.content)} />
              </div>
            </div>
          </div>
          <AnswerList questionId={data.id} />
        </div>
      )}
      {isModalOpen ? <QuestionDeleteModal /> : null}
    </div>
  );
}

export default QuestionDetail;
