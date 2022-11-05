import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Editor from '../editor/Editor';
import { fetchCreateAnswer } from '../../util/fetchAnswer';
import { showToast } from '../toast/Toast';
import '../../components/common.css';

function AnswerCreate({ questionId, updated }) {
  const [content, setContent] = useState('');
  const [isEditorClear, setIsEditorClear] = useState(false);
  const navigator = useNavigate();

  const onChangeContent = (content) => {
    setContent(content);
  };

  const onClickSubmit = () => {
    if (content.length < 50) {
      showToast('Minimum 50 characters.');
    } else {
      fetchCreateAnswer({ questionId, content }, updated);
    }
  };

  return (
    <div className="p-4">
      <Editor onChange={onChangeContent} height={'300px'} />
      <div className="flex">
        <button
          className="mr-3 so-button-primary"
          type="button"
          autoComplete="off"
          disabled=""
          onClick={onClickSubmit}
        >
          Post your answer
        </button>
      </div>
    </div>
  );
}

export default AnswerCreate;
