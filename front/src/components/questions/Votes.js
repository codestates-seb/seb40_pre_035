import { useState } from 'react';

function VoteController({ total }) {
  const [count, setCount] = useState(total);

  const onClickUpVote = () => {
    setCount(count + 1);
  };

  const onClickDownVote = () => {
    setCount(count - 1);
  };

  return (
    <div className="flex flex-col mb-5 mr-4 vote-group">
      <button
        className="flex justify-center"
        aria-label="Up vote"
        onClick={onClickUpVote}
      >
        <svg
          aria-hidden="true"
          className="svg-icon iconArrowUpLg"
          width="36"
          height="36"
          viewBox="0 0 36 36"
          // fill={}
        >
          <path d="M2 25h32L18 9 2 25Z"></path>
        </svg>
      </button>
      <div className="flex my-3 vote-count" data-value={total}>
        {count}
      </div>
      <button
        className="flex justify-center"
        aria-label="Down vote"
        onClick={onClickDownVote}
      >
        <svg
          aria-hidden="true"
          className="svg-icon iconArrowDownLg"
          width="36"
          height="36"
          viewBox="0 0 36 36"
        >
          <path d="M2 11h32L18 27 2 11Z"></path>
        </svg>
      </button>
    </div>
  );
}

export default VoteController;
