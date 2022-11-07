import { useState } from 'react';
import { Link } from 'react-router-dom';
import { Viewer } from '@toast-ui/react-editor';
import AnswerDeleteModal from '../modal/AnswerDeleteModal';
import { relTimeFormat } from '../../util/convertor';
import { fetchAnswerVote } from '../../util/fetchVote';
import { fetchSelectAnswer } from '../../util/fetchAnswer';
import { showToast } from '../toast/Toast';

function AnswerItem({ item, author, updated, selected }) {
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

  const checkIfAuthorForDelete = (userInfo) => {
    if (token && currUser) {
      const author = userInfo.email;
      if (author !== currUser) return '';
      return (
        <button onClick={showModalDelete} className="ml-2 text-soGray-darker">
          Delete
        </button>
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

  const checkIfAuthorForSelect = (item, author) => {
    return (
      <button
        className="seleted-answer"
        onClick={() => onClickSelectAnswer(item)}
      >
        <svg
          aria-hidden="true"
          className="svg-icon iconCheckmarkLg"
          width="36"
          height="36"
          viewBox="0 0 36 36"
          fill={selected?.id === item.id ? '#2E7044' : '#fff'}
          stroke="#ccc"
        >
          <path d="m6 14 8 8L30 6v8L14 30l-8-8v-8Z"></path>
        </svg>
      </button>
    );
  };

  const onClickSelectAnswer = (item) => {
    if (token && currUser) {
      if (author !== currUser) {
        showToast('The author can be select only 🤔', 'danger');
        return false;
      }
      fetchSelectAnswer(item.id).then((data) => {
        if (data?.status === 400) {
          showToast('Please cancel the selected answer first.', 'danger');
          return;
        }

        if (data?.data === 'success select answer') {
          updated(true);

          if (!selected) {
            showToast('Success selected answer. ✅');
          } else if (item.id === selected.id) {
            showToast('Canceled selected answer. 🤔');
          }
        }
      });
    }
  };

  return (
    <div
      className={`flex flex-row px-4 py-5 mx-4 border-soGray-light rounded-md ${
        selected?.id === item.id ? 'bg-soGray-headerbg' : ''
      }`}
    >
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
        {item && checkIfAuthorForSelect(item, author)}
      </div>
      <div className="flex-auto question-item">
        <Viewer initialValue={JSON.parse(item.content)} />
        <div className="flex flex-row justify-end mt-4 text-sm user-info align-center">
          <Link
            to={`/mypage/${item.account.id}`}
            className="flex items-center mr-2"
          >
            {item.account.profile && item.account.profile !== 'test/path' ? (
              <img
                className="border border-buttonSecondary rounded w-[20px] h-[20px] mr-2"
                src={item.account.profile}
                alt={`${item.account.nickname}'s Avatar`}
              />
            ) : (
              <span className="border border-buttonSecondary rounded w-[20px] h-[20px] mr-2"></span>
            )}
            <span className="font-semibold text-soGray-darker">
              {item.account.nickname}
            </span>
          </Link>
          <time className="mr-3 s-user-card--time">
            <span className="mr-1 text-soGray-normal">Answered</span>
            <span
              className="text-soGray-darker"
              title={`${item.createdAt.split('T')[0]} ${
                item.createdAt.split('T')[1].split('.')[0]
              }`}
            >
              {relTimeFormat(item.createdAt)}
            </span>
          </time>
          {item && checkIfAuthorForDelete(item.account)}
        </div>
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
