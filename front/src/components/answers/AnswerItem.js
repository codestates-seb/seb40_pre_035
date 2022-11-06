import { useState } from 'react';
import { Link } from 'react-router-dom';
import { Viewer } from '@toast-ui/react-editor';
import AnswerDeleteModal from '../modal/AnswerDeleteModal';
import { relTimeFormat } from '../../util/convertor';
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
      return;
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
      return;
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
      <div className="flex flex-col mr-8 vote-group">
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
        <div className="flex flex-row mb-6 text-sm user-info align-center">
          <Link
            to={`/mypage/${item.account.id}`}
            className="flex items-center mr-4"
          >
            {item.account.profile && item.account.profile !== 'test/path' ? (
              <img
                className="w-full h-full border border-buttonSecondary rounded w-[20px] h-[20px] mr-2"
                src={item.account.profile}
                alt={`${item.account.nickname}'s Avatar`}
              />
            ) : (
              <span className="w-full h-full border border-buttonSecondary rounded w-[20px] h-[20px] mr-2"></span>
            )}
            <span className="font-semibold text-soGray-darker">
              {item.account.nickname}
            </span>
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
        <Viewer initialValue={item.content} />
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
