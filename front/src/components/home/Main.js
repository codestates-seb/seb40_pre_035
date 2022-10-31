import Pagination from '../componenets/mypage_components/pagination';
import ListTop from '../componenets/mypage_components/QuestionListTop';
import ListMiddle from '../componenets/mypage_components/QuestionListMiddle';
import Card from '../componenets/mypage_components/card';

const Mainbar = () => {
  return (
    <div className="flex flex-col">
      <ListTop />
      <div className="pt-10">
        <ListMiddle />
      </div>
      <Card />
      <Pagination />
    </div>
  );
};

export default Mainbar;
