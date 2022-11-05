import { useState } from 'react';
import { Link } from 'react-router-dom';
import { Viewer } from '@toast-ui/react-editor';
import AnswerDeleteModal from '../answers/AnswerDeleteModal';
import relTimeFormat from '../../util/relativeTimeFormat';
import renderToMarkdown from '../../util/renderMarkdown';
import { fetchAnswerVote } from '../../util/fetchVote';
import { showToast } from '../toast/Toast';

function AnswerItem({ item, updated }) {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isClickAUpVote, setIsClickAUpVote] = useState(false);
  const [isClickADownVote, setIsClickADownVote] = useState(false);
  const token = sessionStorage.getItem('access_token');
  const currUser = sessionStorage.getItem('userEmail');

  const showModalDelete = () => {
    setIsModalOpen(true);
  };

  const hideModalDelete = (flag) => {
    setIsModalOpen(!flag);
  };

  const checkIfAuthor = (userInfo) => {
    if (token && currUser) {
      const author = userInfo.email;
      if (author !== currUser) return '';
      return (
        <div>
          <button onClick={showModalDelete} className="ml-2">
            Delete
          </button>
        </div>
      );
    }
  };

  const onClickAUpVote = (id) => {
    if (!token) {
      showToast('Please Login first.', 'danger');
    }
    setIsClickAUpVote(true);
    fetchAnswerVote(id, 'UP').then((message) => {
      if (message) {
        if (message === 'cancel vote') {
          showToast('Up vote is canceled. 🥲');
        } else if (message === 'success vote') {
          showToast('Your vote has been sent.. 😊');
        }
        updated(true);
      }
      setIsClickAUpVote(false);
    });
  };

  const onClickADownVote = (id) => {
    if (!token) {
      showToast('Please Login first.', 'danger');
    }
    setIsClickADownVote(true);
    fetchAnswerVote(id, 'DOWN').then((message) => {
      if (message) {
        if (message === 'cancel vote') {
          showToast('Down Vote is Canceled. 😊');
        } else if (message === 'success vote') {
          showToast('Your vote has been sent.. 🥲');
        }
        updated(true);
      }
      setIsClickADownVote(false);
    });
  };

  return (
    <div className="flex flex-row px-1 py-5 mx-5 mb-3 border-t answer-item border-soGray-light">
      <div className="flex flex-col mr-4 vote-group">
        <button
          className="flex justify-center"
          aria-label="Up vote"
          data-status="UP"
          onClick={() => onClickAUpVote(item.id)}
        >
          <svg
            aria-hidden="true"
            className="svg-icon iconArrowUpLg"
            width="36"
            height="36"
            viewBox="0 0 36 36"
            fill={isClickAUpVote ? '#f48225' : '#BABFC3'}
          >
            <path d="M2 25h32L18 9 2 25Z"></path>
          </svg>
        </button>
        <div className="flex justify-center my-3">
          {item ? item.totalVote : ''}
        </div>
        <button
          className="flex justify-center"
          aria-label="Down vote"
          data-status="DOWN"
          onClick={() => onClickADownVote(item.id)}
        >
          <svg
            aria-hidden="true"
            className="svg-icon iconArrowDownLg"
            width="36"
            height="36"
            viewBox="0 0 36 36"
            fill={isClickADownVote ? '#f48225' : '#BABFC3'}
          >
            <path d="M2 11h32L18 27 2 11Z"></path>
          </svg>
        </button>
      </div>
      <div className="flex-auto question-item">
        <div className="flex flex-row mb-3 text-sm user-info align-center">
          <Link to={`/mypage/${item.account.id}`} className="flex mr-4">
            {item.account.profile && (
              <img
                src={`${item.account.profile}`}
                alt={`${item.account.nickname}'s user avatar`}
                width="16"
                height="16"
                className="mr-2"
              />
            )}
            <span className="text-soGray-darker">{item.account.nickname}</span>
          </Link>
          <time className="mr-4 s-user-card--time">
            <span className="mr-1 text-soGray-normal">Asked</span>
            <span
              className="text-soGray-darker"
              title={`${item.createdAt.split('T')[0]} ${
                item.createdAt.split('T')[1].split('.')[0]
              }`}
            >
              {relTimeFormat(item.createdAt)}
            </span>
          </time>
          {item && checkIfAuthor(item.account)}
        </div>
        <Viewer initialValue={renderToMarkdown(item.content)} />
      </div>
      {isModalOpen ? (
        <AnswerDeleteModal
          answerId={item.id}
          hideModal={hideModalDelete}
          updated={updated}
        />
      ) : null}
    </div>
  );
}

export default AnswerItem;
