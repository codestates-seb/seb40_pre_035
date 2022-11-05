const QuestionFilter = ({ filter, handler }) => {
  const clickNewest = () => {
    let sort = filter.sort((a, b) => {
      return a.createdAt < b.createdAt ? -1 : 1;
    });
    handler([...sort]);
  };

  const clickVotes = () => {
    let sort = filter.sort((a, b) => {
      return a.totalVote < b.totalVote ? 1 : -1;
    });
    handler([...sort]);
  };

  const clickUnAnswered = () => {
    let sort = filter.sort((a, b) => {
      return a.answerCount < b.answerCount ? -1 : 1;
    });
    handler([...sort]);
  };
  return (
    <div className="filter">
      <div
        className="inline-flex object-right rounded-md shadow-sm"
        role="group"
      >
        <div>
          <button
            type="button"
            onClick={clickNewest}
            className="px-4 py-2 text-sm text-black bg-white border rounded-l font-regular border-soGray-normal hover:bg-soGray-light focus:z-10 focus:bg-soGray-normal"
          >
            Newest
          </button>
        </div>
        <div>
          <button
            type="button"
            onClick={clickVotes}
            className="px-4 py-2 text-sm text-black bg-white border font-regular rounded-lr border-soGray-normal hover:bg-soGray-light focus:bg-soGray-normal focus:z-10 "
          >
            Vote
          </button>
        </div>
        <div>
          <button
            type="button"
            onClick={clickUnAnswered}
            className="px-4 py-2 text-sm text-black bg-white border rounded-r font-regular focus:z-10 border-soGray-normal hover:bg-soGray-light focus:bg-soGray-normal"
          >
            Unanswered
          </button>
        </div>
      </div>
    </div>
  );
};

export default QuestionFilter;
