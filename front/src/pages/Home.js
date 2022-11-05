import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import SidebarRight from '../components/home/SidebarRight';
import Loading from '../components/loading/Loading';
import Sidebar from '../components/sidebar/Sidebar';
import Card from '../components/home/Card';

function Home() {
  const [questionList, setQuestionList] = useState([]);
  const [isPending, setIsPending] = useState(true);

  useEffect(() => {
    fetch(`/questions?page=1&size=10&sort=id%2Cdesc`)
      .then((response) => {
        if (!response.ok) {
          throw Error('could not fetch the data for that resource');
        }
        return response.json();
      })
      .then((data) => {
        setIsPending(false);
        setQuestionList(data.content);
      })
      .catch((error) => {
        setIsPending(false);
        throw Error(error.message);
      });
  }, []);

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
          <h2 className="text-xxl">Top Questions</h2>
          <Link to="/question/create" className="so-button-primary">
            Ask Question
          </Link>
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
      </div>
      <aside className="w-[280px] flex-grow-0 flex-shrink-0 basis-[280px]">
        <SidebarRight />
      </aside>
    </div>
  );
}

export default Home;
