import QuestionAskBox from './QuestionAskBox';

const ListTop = () => {
  return (
    <div className="flex flex-col">
      <div className="flex space-x-0">
        <div className="text-3xl pr-96">All Questions</div>
        <div className="pl-5">
          <QuestionAskBox />
        </div>
      </div>
    </div>
  );
};

export default ListTop;
