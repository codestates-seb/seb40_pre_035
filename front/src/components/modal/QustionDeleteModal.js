import { useNavigate } from 'react-router-dom';
import { fetchDeleteQuestion } from '../../util/fetchQuestion';
import { IconClearSm } from '@stackoverflow/stacks-icons';
import { Icon } from '../../util/convertor';

function QuestionDelete({ postId, hideModal }) {
  const navigate = useNavigate();
  const onClickDelete = (id) => {
    fetchDeleteQuestion(id).then((res) => {
      navigate('/question');
    });
  };

  return (
    <div className="fixed top-0 bottom-0 left-0 right-0 z-40 flex items-center justify-center bg-fixed bg-black/30">
      <div className="p-6 bg-white rounded-md shadow-lg w-[440px] y-[230px]">
        <div className="flex justify-between mb-4">
          <h5 className="font-bold text-xxl">Are you absolutely sure?</h5>
          <button onClick={() => hideModal(true)}>{Icon(IconClearSm)}</button>
        </div>
        <p className="mb-6 font-medium text-md">
          This action cannot be undone. This will permanently delete the
          Question, Answers, and remove all associations.
        </p>
        <div className="flex">
          <button
            className="mr-3 so-button-danger"
            type="button"
            autoComplete="off"
            onClick={() => onClickDelete(postId)}
          >
            Delete Question
          </button>

          <button
            className="so-button-dismiss text-secondary-400"
            type="button"
            onClick={() => hideModal(true)}
          >
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
}

export default QuestionDelete;
