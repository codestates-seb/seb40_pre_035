import { useState } from 'react';

const Pagination = () => {
  let [num, setNum] = useState(1);

  const pages = [
    { page: num },
    { page: num + 1 },
    { page: num + 2 },
    { page: num + 3 },
  ];

  const numPages = Math.ceil(num / pages.length);

  return (
    <div className="box-content flex w-[14rem] space-x-1 bg-white rounded gap-1">
      <button
        onClick={() => setNum(num - 1)}
        disabled={num === 1}
        className="inline-flex items-center py-1 px-2 text-sm font-regular
           text-gray-500 bg-white rounded border border-soGray-normal hover:bg-soGray-normal
            "
      >
        Prev
      </button>
      {pages.map((pg, i) => (
        <button
          key={i + 1}
          onClick={() => setNum(i + 1)}
          aria-current={num === i + 1 ? 'page' : null}
          className={`flex-auto items-center py-1 px-2 font-regular
           text-gray-500  bg-white rounded box-content border border-soGray-normal hover:bg-soGray-normal ${
             num === pg.page && 'text-white bg-primary-400'
           }`}
        >
          {pg.page}
        </button>
      ))}
      <button
        onClick={() => setNum(num + 1)}
        disabled={num === numPages}
        className="inline-flex items-center py-1 px-2 text-sm font-medium
           text-gray-500 bg-white rounded border border-soGray-normal hover:bg-soGray-normal"
      >
        Next
      </button>
    </div>
  );
};

export default Pagination;
