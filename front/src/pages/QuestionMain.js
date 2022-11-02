import SidebarQuestion from '../components/home/SidebarQuestion';
import SidebarRight from '../components/home/SidebarRight';
import Mainbar from '../components/home/Main';

const QuestionMain = () => {
  return (
    <>
      <div className="content-around" />
      <div className="relative flex h-auto mx-auto my-0 1-0-auto">
        <SidebarQuestion />
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

export default QuestionMain;
