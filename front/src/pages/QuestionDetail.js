import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import Sidebar from '../components/sidebar/Sidebar';
import { Viewer } from '@toast-ui/react-editor';
import { fetchQuestionDetail } from '../util/fetchQuestion';
import { fetchAnswerList } from '../util/fetchAnswer';
import QuestionDeleteModal from '../components/questions/QustionDeleteModal';
import AnswerList from '../components/answers/AnswerList';
import AnswerCreate from '../components/answers/AnswerCreate';
import Votes from '../components/questions/Votes';
import Loading from '../components/loading/Loading';
import relTimeFormat from '../util/relativeTimeFormat';

import '../components/common.css';

function QuestionDetail() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [info, setInfo] = useState({});
  const [answerList, setAnswerList] = useState([]);
  const [questionUpdate, setQuestionUpdate] = useState(true);
  const [answerUpdate, setAnswerUpdate] = useState(true);
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
    if (questionUpdate || answerUpdate) {
      fetchQuestionDetail(id).then((res) => {
        console.log('question 리스트 조회');
        setIsPending(false);
        setInfo(res);
        setQuestionUpdate(false);
      });

      fetchAnswerList(id).then((res) => {
        setIsPending(false);
        setAnswerList(res.content);
        console.log('answers 리스트 조회');
        console.log(answerList);
        setAnswerUpdate(false);
      });
    }
  }, [questionUpdate, answerUpdate]);

  if (isPending) {
    return (
      <div>
        <Loading />
      </div>
    );
  }

  return (
    <div className="so-main-wrapper">
      <nav className="sticky max-h-[calc(100vh-180px)] top-[60px] w-[164px] flex-grow-0 flex-shrink-0 basis-[164px]">
        <Sidebar />
      </nav>
      {info && (
        <div className="so-main-content max-w-none so-with-one-side">
          <div className="px-8 pt-8 pb-4 mb-3 border-b question-header border-soGray-light">
            <div className="flex justify-between mb-4 question-title">
              <h2 className="row-auto pr-2 font-medium leading-tight break-words text-xxl">
                {info.title}
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

            <div className="flex flex-row text-sm user-info align-center">
              <span className="flex mr-4">
                <img
                  src={`${info.account.profile}`}
                  alt={`${info.account.nickname}'s user avatar`}
                  width="16"
                  height="16"
                  className="mr-2"
                />
                <span className="text-soGray-darker">
                  {info.account.nickname}
                </span>
              </span>
              <time className="mr-4 s-user-card--time">
                <span className="mr-1 text-soGray-normal">Asked</span>
                <span
                  className="text-soGray-darker"
                  title={`${info.createdAt.split('T')[0]} ${
                    info.createdAt.split('T')[1].split('.')[0]
                  }`}
                >
                  {relTimeFormat(info.createdAt)}
                </span>
              </time>
              <div>
                <button
                  onClick={onClickEdit}
                  data-target={info.id}
                  className="mr-2"
                >
                  Edit
                </button>
                |
                <button onClick={showModalDelete} className="ml-2">
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
                <Votes
                  total={info.totalVote}
                  postId={info.id}
                  updated={setQuestionUpdate}
                  type="question"
                />
              </div>
              <div className="flex-auto question-content">
                <Viewer initialValue={JSON.parse(info.content)} />
              </div>
            </div>
          </div>
          {answerList && (
            <AnswerList list={answerList} updated={setAnswerUpdate} />
          )}
          <AnswerCreate updated={setAnswerUpdate} questionId={info.id} />
        </div>
      )}
      {isModalOpen ? <QuestionDeleteModal /> : null}
    </div>
  );
}

export default QuestionDetail;
