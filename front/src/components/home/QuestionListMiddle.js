import QuestionFilter from './QuestionFilter';

const ListMiddle = () => {
  return (
    <div className="flex flex-cols-2">
      <div className="flex pr-80">Question 총 개수</div>
      <div>
        <QuestionFilter />
      </div>
    </div>
  );
};

export default ListMiddle;
