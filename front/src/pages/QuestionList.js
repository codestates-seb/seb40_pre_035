import { Link } from 'react-router-dom';
import SidebarRight from '../components/home/SidebarRight';
import Mainbar from '../components/home/Main';
import Sidebar from '../components/sidebar/Sidebar';

const QuestionList = () => {
  return (
    <div className="so-main-wrapper">
      <nav className="sticky max-h-[calc(100vh-180px)] top-[60px] w-[164px] flex-grow-0 flex-shrink-0 basis-[164px]">
        <Sidebar />
      </nav>
      <div className="so-main-content">
        <div className="flex flex-row items-center justify-between px-8 py-8">
          <h2 className="text-xxl">Top Questions</h2>
          <Link to="/question/create" className="so-button-primary">
            Ask Question
          </Link>
        </div>
        <div className="mr-8 border-t border-soGray-light">
          <Mainbar />
        </div>
      </div>
      <aside className="w-[280px] flex-grow-0 flex-shrink-0 basis-[280px]">
        <SidebarRight />
      </aside>
    </div>
  );
};

export default QuestionList;
