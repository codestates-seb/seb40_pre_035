import Sidebar from '../components/home/SidebarLeft';
import SidebarRight from '../components/home/SidebarRight';
import Mainbar from '../components/home/Main';


const Home = () => {
  return (
    <>
      <div className="content-around" />
      {/* 위 div 상단바 위치 */}
      <div className="h-auto flex 1-0-auto relative mx-auto my-0 mt-20">
        <Sidebar />
        <div className="flex columns p-10 leading-7">
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
      {/* footer위치 */}
    </>
  );
};

export default Home;
