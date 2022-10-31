function QuestionFilter() {
  return (
    <div>
      <div
        className="inline-flex rounded-md shadow-sm object-right"
        role="group"
      >
        <div>
          <button
            type="button"
            className="py-2 px-4 text-sm font-regular text-black
            bg-white rounded-l border border-soGray-normal hover:bg-soGray-light  focus:z-10  focus:bg-soGray-normal"
          >
            Newest
          </button>
        </div>
        <div>
          <button
            type="button"
            className="py-2 px-4 text-sm font-regular text-black
            bg-white rounded-lr border border-soGray-normal hover:bg-soGray-light focus:bg-soGray-normal focus:z-10 "
          >
            Vote
          </button>
        </div>
        <div>
          <button
            type="button"
            className="py-2 px-4 text-sm font-regular text-black
            bg-white rounded-r border focus:z-10 border-soGray-normal hover:bg-soGray-light focus:bg-soGray-normal"
          >
            Unanswered
          </button>
        </div>
      </div>
    </div>
  );
}

export default QuestionFilter;
