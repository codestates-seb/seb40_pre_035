import { useState } from 'react';
import Votes from '../questions/Votes';
import { Viewer } from '@toast-ui/react-editor';
import relTimeFormat from '../../util/relativeTimeFormat';
import AnswerDeleteModal from '../answers/AnswerDeleteModal';

function AnswerItem({ item, setUpdate }) {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const showModalDelete = () => {
    setIsModalOpen(true);
  };

  return (
    <div className="flex flex-row px-1 py-5 mx-5 mb-3 border-t answer-item border-soGray-light">
      <Votes
        total={item.totalVote}
        postId={item.id}
        updated={setUpdate}
        type="answer"
      />
      <div className="flex-auto question-item">
        <div className="flex flex-row mb-3 text-sm user-info align-center">
          <span className="flex mr-4">
            <img
              src={`${item.account.profile}`}
              alt={`${item.account.nickname}'s user avatar`}
              width="16"
              height="16"
              className="mr-2"
            />
            <span className="text-soGray-darker">{item.account.nickname}</span>
          </span>
          <time className="mr-4 s-user-card--time">
            <span className="mr-1 text-soGray-normal">Asked</span>
            <span
              className="text-soGray-darker"
              title={`${item.createdAt.split('T')[0]} ${
                item.createdAt.split('T')[1].split('.')[0]
              }`}
            >
              {relTimeFormat(item.createdAt)}
            </span>
          </time>
          <div>
            <button onClick={showModalDelete} className="ml-2">
              Delete
            </button>
          </div>
        </div>
        <Viewer initialValue={item.content} />
      </div>
      {isModalOpen ? <AnswerDeleteModal /> : null}
    </div>
  );
}

export default AnswerItem;
