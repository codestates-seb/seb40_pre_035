import Votes from '../questions/Votes';
import { Viewer } from '@toast-ui/react-editor';

function AnswerItem({ item }) {
  console.log(item);
  return (
    <div className="flex flex-row mb-5 answer-item">
      <Votes total={item.totalVote} />
      <div className="flex-auto question-item so-editor">
        <Viewer initialValue={item.content} />
      </div>
    </div>
  );
}

export default AnswerItem;
