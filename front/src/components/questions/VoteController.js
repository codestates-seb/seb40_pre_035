function VoteController() {
  return (
    <div className="vote-group flex flex-col mr-4 mb-5">
      <button className="flex justify-center" aria-label="Up vote">
        <svg
          aria-hidden="true"
          className="svg-icon iconArrowUpLg"
          width="36"
          height="36"
          viewBox="0 0 36 36"
        >
          <path d="M2 25h32L18 9 2 25Z"></path>
        </svg>
      </button>
      <div className="vote-count flex my-3" data-value="34351">
        34351
      </div>
      <button className="flex justify-center" aria-label="Down vote">
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
