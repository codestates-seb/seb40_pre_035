const Pagination = ({ pageInfo, currentPage, onClickPage }) => {
  return (
    <ul className="w-full flex items-center flex-row justify-start mt-2.5 mb-7 px-4  space-x-1">
      <li
        className={`so-page-item
              ${1 === currentPage && 'hidden'}`}
      >
        <button onClick={() => onClickPage('Prev')}>Prev</button>
      </li>
      {Array.from({ length: +pageInfo.totalPages }).map((el, i) => {
        return (
          <li
            key={i}
            className={`so-page-item ${
              +currentPage === i + 1
                ? 'bg-primary-400 text-white hover:bg-primary-600'
                : ''
            }`}
          >
            <button onClick={() => onClickPage(i + 1)}>{i + 1}</button>
          </li>
        );
      })}
      <li
        className={`so-page-item
              ${pageInfo.totalPages === currentPage && 'hidden'}`}
      >
        <button onClick={() => onClickPage('Next')}>Next</button>
      </li>
    </ul>
  );
};

export default Pagination;
