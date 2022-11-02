const Pagination = ({ postsPerPage, totalPosts }) => {
  const pageNumbers = [];
  for (let i = 1; i <= Math.ceil(totalPosts / postsPerPage); i++) {
    pageNumbers.push(i);
  }
  return (
    <nav>
      <div className="box-content flex w-[14rem] space-x-1 bg-white rounded gap-1">
        <button
          onClick={() => postsPerPage(pageNumbers - 1)}
          className={`inline-flex items-center py-1 px-2 text-sm font-regular
           text-gray-500 bg-white rounded border border-soGray-normal hover:bg-soGray-normal
           ${pageNumbers === 1 && 'hidden'}
            `}
        >
          Prev
        </button>
        <ul className="pagination">
          {pageNumbers.map((number) => (
            <li key={number} className="page-item">
              <a
                href="!#"
                className={`flex-auto items-center py-1 px-2 font-regular
           text-gray-500  bg-white rounded box-content border border-soGray-normal hover:bg-soGray-normal ${
             number === postsPerPage && 'text-white bg-primary-400'
           }`}
              >
                {number}
              </a>
            </li>
          ))}
        </ul>
        <button
          onClick={() => postsPerPage(pageNumbers + 1)}
          disabled={pageNumbers === totalPosts}
          className={`inline-flex items-center py-1 px-2 text-sm font-regular
           text-gray-500 bg-white rounded border border-soGray-normal hover:bg-soGray-normal
           ${pageNumbers === totalPosts && 'hidden'}`}
        >
          Next
        </button>
      </div>
    </nav>
  );
};
export default Pagination;
