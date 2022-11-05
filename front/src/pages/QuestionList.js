import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import SidebarRight from '../components/home/SidebarRight';
import Loading from '../components/loading/Loading';
import Sidebar from '../components/sidebar/Sidebar';
import Card from '../components/home/Card';
import Pagination from '../components/home/Pagination';
import QuestionFilter from '../components/home/QuestionFilter';
import { fetchQuestionList } from '../util/fetchQuestion';

function QuestionList() {
  const [questionList, setQuestionList] = useState([]);
  const [isPending, setIsPending] = useState(true);
  const [currPage, setCurrPage] = useState(1);
  const [pageInfo, setPageInfo] = useState({});
  const [isUpdate, setIsUpdate] = useState(true);
  const [currFilter, setCurrFilter] = useState('newest');

  function onFilterClick(e) {
    setCurrFilter(e.target.value);
    console.log(e.target.value);
    setIsUpdate(true);
  }

  useEffect(() => {
    if (isUpdate) {
      fetchQuestionList(currPage, currFilter).then((res) => {
        console.log(res.content);
        setQuestionList(res.content);
        setPageInfo(res);
        setIsPending(false);
        setIsUpdate(false);
      });
    }
  }, [isUpdate]);

  if (isPending) {
    return (
      <div>
        <Loading />
      </div>
    );
  }

  return (
    <div className="so-main-wrapper">
      <nav className="sticky max-h-[calc(100vh-180px)] top-[60px] w-[164px] flex-grow-0 flex-shrink-0 basis-[164px]">
        <Sidebar />
      </nav>
      <div className="so-main-content so-with-both-side">
        <div className="flex flex-row items-center justify-between px-6 py-8">
          <h2 className="text-xxl">Questions</h2>
          <Link to="/question/create" className="so-button-primary">
            Ask Question
          </Link>
        </div>
        <div>
          {/* <QuestionFilter /> */}
          <div className="filter">
            <div
              className="inline-flex object-right rounded-md shadow-sm"
              role="group"
            >
              <div>
                <button
                  type="button"
                  value="newest"
                  onClick={onFilterClick}
                  className="px-4 py-2 text-sm text-black bg-white border rounded-l font-regular border-soGray-normal hover:bg-soGray-light focus:z-10 focus:bg-soGray-normal"
                >
                  Newest
                </button>
              </div>
              <div>
                <button
                  type="button"
                  value="vote"
                  onClick={onFilterClick}
                  className="px-4 py-2 text-sm text-black bg-white border font-regular rounded-lr border-soGray-normal hover:bg-soGray-light focus:bg-soGray-normal focus:z-10 "
                >
                  Vote
                </button>
              </div>
              <div>
                <button
                  type="button"
                  value="unanswered"
                  onClick={onFilterClick}
                  className="px-4 py-2 text-sm text-black bg-white border rounded-r font-regular focus:z-10 border-soGray-normal hover:bg-soGray-light focus:bg-soGray-normal"
                >
                  Unanswered
                </button>
              </div>
            </div>
          </div>
        </div>
        <div className="mr-8 border-t border-soGray-light">
          {questionList && (
            <div className="questionList">
              {questionList.map((card) => (
                <Card key={card.id} item={card} />
              ))}
            </div>
          )}
        </div>
        <div>
          <Pagination
            currentPage={currPage}
            pageInfo={pageInfo}
            setCurrPage={setCurrPage}
            setIsUpdate={setIsUpdate}
          />
        </div>
      </div>
      <aside className="w-[280px] flex-grow-0 flex-shrink-0 basis-[280px]">
        <SidebarRight />
      </aside>
    </div>
  );
}

export default QuestionList;
