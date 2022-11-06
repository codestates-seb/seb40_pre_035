import { ReactDOM } from 'react';
import { Link } from 'react-router-dom';

const Pagination = ({
  pageInfo,
  currentPage,
  onPageChange,
  setCurrPage,
  setIsUpdate,
}) => {
  const PageItem = (page) => {
    return Array.from({ length: page }).map((el, i) => {
      return (
        <li key={i + 1} className="page-item">
          <button
            data-page={i + 1}
            onClick={onChangePagination}
            className={`py-1 px-2 font-regular
             bg-white rounded border border-soGray-normal hover:bg-soGray-light ${
               i + 1 === currentPage && 'text-white bg-primary-400'
             }`}
          >
            {i + 1}
          </button>
        </li>
      );
    });
  };

  const onNext = () => {
    onPageChange(currentPage - 1);
  };
  const onPrev = () => {
    onPageChange(currentPage + 1);
  };

  const onChangePagination = (e) => {
    console.log(e.target.dataset.page);
    setCurrPage(e.target.dataset.page);
    setIsUpdate(true);
  };
  return (
    <div className="w-full flex items-center flex-row  justify-start ml-5 mt-2.5 mr-auto mb-7 space-x-2">
      <button
        onClick={onNext}
        className={`items-center px-2 text-sm font-regular h-[30px]
           text-gray-500 bg-white rounded border border-soGray-normal hover:bg-soGray-light
           ${currentPage === 1 && 'hidden'}
            `}
      >
        Prev
      </button>
      <ul className="flex flex-row items-center space-x-2 ">
        {PageItem(pageInfo.totalPages)}
      </ul>
      <button
        onClick={onPrev}
        className={`items-center px-2 text-sm font-regular h-[30px]
        text-gray-500 bg-white rounded border border-soGray-normal hover:bg-soGray-light
        
        `}
        // ${currentPage === pageNumbers.length && 'hidden'}
      >
        Next
      </button>
    </div>
  );
};

export default Pagination;
