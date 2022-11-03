import Sidebar from '../components/sidebar/Sidebar';
import SidebarRight from '../components/home/SidebarRight';
import Mainbar from '../components/home/Main';

const QuestionList = () => {
  return (
    <>
      <div className="relative flex h-auto mx-auto my-0 border-l border-r border-l-soGray-light border-r-soGray-light">
        <div className="flex p-10 leading-7 columns">
          <div className="pl-8">
            <div className="flex">
              <Mainbar />
            </div>
          </div>
          <div className="cardLayout">
            <div className="card"></div>
          </div>
        </div>
        <SidebarRight />
      </div>
    </>
  );
};

export default QuestionList;
