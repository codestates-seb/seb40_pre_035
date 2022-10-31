import Sidebar from '../components/home/SidebarLeft';
import SidebarRight from '../components/home/SidebarRight';
import Mainbar from '../components/home/Main';

// const QuestionMain = () => {
//   const themeState = useSelector((state) => state.themeSlice).theme;

//   const [page, setPage] = useState(1);
//   const [size, setSize] = useState(15);

// useEffect(() => {
//     axios
//       .get(`/questions?size=${size}&page=${page}`)
//       .then((res) => {
//         //pagination
//         setTotal(Number(res.data.pageInfo.totalElements));
//         setPage(page);
//         setSize(size);
//         //card에 뿌릴 data
//         setData(res.data.data.sort((a, b) => b.questionId - a.questionId));

//         localStorage.setItem(
//           'data',
//           JSON.stringify(
//             res.data.data.filter((el) => delete el.questionWriter.userPassword)
//           )
//         );
//       })
//       .catch((err) => err);
//   }, [page, size]);

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
