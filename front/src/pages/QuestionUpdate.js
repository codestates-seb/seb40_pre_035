import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import {
  fetchQuestionDetail,
  fetchUpdateQuestion,
} from '../util/fetchQuestion';
import { checkIfLogined } from '../util/fetchLogin';
import { showToast } from '../components/toast/Toast';
import { Editor } from '../components/editor/Editor';

function QuestionUpdate() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [isEditorEdit, setIsEditorEdit] = useState(false);

  useEffect(() => {
    try {
      checkIfLogined().then(() => {
        fetchQuestionDetail(id).then((res) => {
          console.log(res);
          setTitle(res.title);
          setContent(res.content);
          setIsEditorEdit(true);
        });
      });
    } catch (error) {
      console.log(`🛑 ${error}`);
    }
  }, []);

  const onChangeTitle = (e) => {
    setTitle(e.target.value);
  };

  const onChangeContent = (content) => {
    setContent(content);
  };

  const onClickDiscard = (id) => {
    navigate(`/question/${id}`);
  };

  const onClickSubmit = (id) => {
    if (title.length < 10) {
      showToast('Minimum 10 characters.');
    } else if (content.length < 50) {
      showToast('Minimum 50 characters.');
    } else {
      fetchUpdateQuestion({ title, content }, id).then((message) => {
        navigate(`/question/${id}`);
      });
    }
  };

  return (
    <div className="bg-white px-6 pt-[40px] pb-[80px] border-l-soGray-light border-l">
      <div className="mb-4">
        <h5 className="font-bold">Title</h5>
        <p className="mb-2 text-sm">
          Be specific and imagine you’re asking a question to another person.
        </p>
        <div className="flex">
          <input
            id="title"
            name="title"
            type="text"
            maxLength="300"
            placeholder=""
            value={title}
            className="w-full px-4 py-2 border rounded border-soGray-light"
            data-min-length="15"
            data-max-length="150"
            onChange={onChangeTitle}
          />
        </div>
      </div>
      <div className="mb-4">
        <h5 className="mb-2 font-bold">Body</h5>
        <div className="mb-4">
          <Editor
            onChange={onChangeContent}
            height={'300px'}
            isEditorEdit={isEditorEdit}
            setIsEditorEdit={setIsEditorEdit}
            initValue={content}
          />
        </div>
        <p className="mb-4 text-sm">
          for our company I am looking for a selfhosted software, which we can
          use to check automatically solutions from the tasks in our onboarding
          process.
        </p>
      </div>
      <div className="flex">
        <button
          className="mr-3 so-button-primary"
          type="button"
          autoComplete="off"
          onClick={() => onClickSubmit(id)}
        >
          Save edits
        </button>

        <div className="flex--item">
          <button
            className="so-button-dismiss text-secondary-400"
            type="button"
            onClick={() => onClickDiscard(id)}
          >
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
}

export default QuestionUpdate;
