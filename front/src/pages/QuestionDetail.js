import { useEffect, useState } from 'react';
import { useNavigate, useParams, Link } from 'react-router-dom';
import Sidebar from '../components/sidebar/Sidebar';
import { Viewer } from '@toast-ui/react-editor';
import { fetchQuestionDetail } from '../util/fetchQuestion';
import { fetchAnswerList } from '../util/fetchAnswer';
import QuestionDeleteModal from '../components/questions/QustionDeleteModal';
import AnswerList from '../components/answers/AnswerList';
import AnswerCreate from '../components/answers/AnswerCreate';
import Loading from '../components/loading/Loading';
import relTimeFormat from '../util/relativeTimeFormat';
import renderToMarkdown from '../util/renderMarkdown';
import { fetchQuestionVote } from '../util/fetchVote';
import { showToast } from '../components/toast/Toast';
import '../components/common.css';

function QuestionDetail() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [info, setInfo] = useState({});
  const [answerList, setAnswerList] = useState([]);
  const [update, setUpdate] = useState(true);
  const [isPending, setIsPending] = useState(true);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isClickQUpVote, setIsClickQUpVote] = useState(false);
  const [isClickQDownVote, setIsClickQDownVote] = useState(false);
  const token = sessionStorage.getItem('access_token');
  const currUser = sessionStorage.getItem('userEmail');

  const showModalDelete = () => {
    setIsModalOpen(true);
  };

  const hideModalDelete = (flag) => {
    setIsModalOpen(!flag);
  };

  const onClickEdit = (id) => {
    navigate(`/question/update/${id}`);
  };

  const onClickAskQuestion = () => {
    navigate('/question/create');
  };

  const getQuestion = (id) => {
    fetchQuestionDetail(id).then((res) => {
      setInfo(res);
    });
  };

  const getAnswer = (id) => {
    fetchAnswerList(id).then((res) => {
      setAnswerList(res.content);
    });
  };

  const checkIfAuthor = (userInfo) => {
    if (token && currUser) {
      const author = userInfo.email;
      if (author !== currUser) return '';
      return (
        <div>
          <button onClick={onClickEdit} data-target={info.id} className="mr-2">
            Edit
          </button>
          |
          <button onClick={showModalDelete} className="ml-2">
            Delete
          </button>
        </div>
      );
    }
  };

  const onClickQUpVote = (id) => {
    if (!token) {
      showToast('Please Login first.', 'danger');
    }
    setIsClickQUpVote(true);
    fetchQuestionVote(id, 'UP').then((message) => {
      if (message) {
        if (message === 'cancel vote') {
          showToast('Up vote is canceled. 🥲');
          setUpdate(true);
        } else if (message === 'success vote') {
          showToast('Your vote has been sent.. 😊');
          setUpdate(true);
        }
      }
      setIsClickQUpVote(false);
    });
  };

  const onClickQDownVote = (id) => {
    if (!token) {
      showToast('Please Login first.', 'danger');
    }
    setIsClickQDownVote(true);
    fetchQuestionVote(id, 'DOWN').then((message) => {
      if (message) {
        if (message === 'cancel vote') {
          showToast('Down Vote is Canceled. 😊');
          setUpdate(true);
        } else if (message === 'success vote') {
          showToast('Your vote has been sent.. 🥲');
          setUpdate(true);
        }
      }
      setIsClickQDownVote(false);
    });
  };

  useEffect(() => {
    if (update) {
      getQuestion(id);
      getAnswer(id);
      setUpdate(false);
      setIsPending(false);
    }
  }, [update]);

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
      {!isPending && Object.keys(info).length ? (
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
              <Link className="flex mr-4" to={`/mypage/${info.account.id}`}>
                <img
                  src={info.account.profile}
                  alt={`${info.account?.nickname}'s user avatar`}
                  width="16"
                  height="16"
                  className="mr-2"
                />
                <span className="text-soGray-darker">
                  {info.account?.nickname}
                </span>
              </Link>
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
              {info && checkIfAuthor(info.account)}
            </div>
          </div>
          <div className="mb-10 question-body">
            <div
              aria-label="question and answers"
              className="flex flex-row p-6"
            >
              <div className="flex justify-center mr-4 question-votes">
                <div className="flex flex-col mr-4 vote-group">
                  <button
                    className="flex justify-center"
                    aria-label="Up vote"
                    data-status="UP"
                    onClick={() => onClickQUpVote(info.id)}
                  >
                    <svg
                      aria-hidden="true"
                      className="svg-icon iconArrowUpLg"
                      width="36"
                      height="36"
                      viewBox="0 0 36 36"
                      fill={isClickQUpVote ? '#f48225' : '#BABFC3'}
                    >
                      <path d="M2 25h32L18 9 2 25Z"></path>
                    </svg>
                  </button>
                  <div className="flex justify-center my-3">
                    {info ? info.totalVote : ''}
                  </div>
                  <button
                    className="flex justify-center"
                    aria-label="Down vote"
                    data-status="DOWN"
                    onClick={() => onClickQDownVote(info.id)}
                  >
                    <svg
                      aria-hidden="true"
                      className="svg-icon iconArrowDownLg"
                      width="36"
                      height="36"
                      viewBox="0 0 36 36"
                      fill={isClickQDownVote ? '#f48225' : '#BABFC3'}
                    >
                      <path d="M2 11h32L18 27 2 11Z"></path>
                    </svg>
                  </button>
                </div>
              </div>
              <div className="flex-auto question-content">
                <Viewer
                  viewer="true"
                  initialValue={renderToMarkdown(info.content)}
                />
              </div>
            </div>
          </div>
          {answerList && <AnswerList list={answerList} />}
          <AnswerCreate questionId={info.id} />
        </div>
      ) : (
        ''
      )}
      {isModalOpen ? (
        <QuestionDeleteModal postId={info.id} hideModal={hideModalDelete} />
      ) : null}
    </div>
  );
}

export default QuestionDetail;
