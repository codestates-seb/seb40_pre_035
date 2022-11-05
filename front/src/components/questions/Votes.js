import { useState, useEffect } from 'react';
import { fetchQuestionVote, fetchAnswerVote } from '../../util/fetchVote';
import { showToast } from '../toast/Toast';

function Votes({ total, postId, updated, type = 'question' }) {
  const [isClickUpVote, setIsClickUpVote] = useState(false);
  const [isClickDownVote, setIsClickDownVote] = useState(false);

  const onClickUpVote = () => {
    setIsClickUpVote(true);
    onChangeVote('UP');
  };

  const onClickDownVote = () => {
    setIsClickDownVote(true);
    onChangeVote('DOWN');
  };

  const onChangeVote = async (status) => {
    await fetchQuestionVote(postId, status, type).then((res) => {
      console.log(res);
      if (res) {
        if (res === 'cancel vote') {
          showToast('Down Vote is Canceled.');
        } else if (res === 'success vote') {
          showToast('Down Vote is Canceled.');
        }
      }
      setIsClickDownVote(false);
    });
  };

  return (
    <div className="flex flex-col mr-4 vote-group">
      <button
        className="flex justify-center"
        aria-label="Up vote"
        data-status="UP"
        onClick={onClickUpVote}
      >
        <svg
          aria-hidden="true"
          className="svg-icon iconArrowUpLg"
          width="36"
          height="36"
          viewBox="0 0 36 36"
          fill={isClickUpVote ? '#f48225' : '#BABFC3'}
        >
          <path d="M2 25h32L18 9 2 25Z"></path>
        </svg>
      </button>
      <div className="flex justify-center my-3">{total}</div>
      <button
        className="flex justify-center"
        aria-label="Down vote"
        data-status="DOWN"
        onClick={onClickDownVote}
      >
        <svg
          aria-hidden="true"
          className="svg-icon iconArrowDownLg"
          width="36"
          height="36"
          viewBox="0 0 36 36"
          fill={isClickDownVote ? '#f48225' : '#BABFC3'}
        >
          <path d="M2 11h32L18 27 2 11Z"></path>
        </svg>
      </button>
    </div>
  );
}

export default Votes;
