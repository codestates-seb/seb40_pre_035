import Pagination from './Pagination';
import ListTop from './QuestionListTop';
import ListMiddle from './QuestionListMiddle';
import Card from './Card';

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
