import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { fetchCreateQuestion } from '../util/fetchQuestion';
import { checkIfLogined } from '../util/fetchLogin';
import { showToast } from '../components/toast/Toast';
import { Editor } from '../components/editor/Editor';

function QuestionCreate() {
  const navigate = useNavigate();
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const navigator = useNavigate();

  useEffect(() => {
    try {
      checkIfLogined().then(() => {
        console.log(`✅ 로그인 성공`);
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

  const onClickDiscard = () => {
    navigator('/question');
  };

  const onClickSubmit = async () => {
    if (title.length < 10) {
      showToast('Minimum 10 characters.');
    } else if (content.length < 50) {
      showToast('Minimum 50 characters.');
    } else {
      await fetchCreateQuestion({ title, content }).then((id) => {
        console.log(id);
        navigate(`/question/${id}`);
      });
    }
  };

  return (
    <div className="so-askQuestion">
      <h1 className="p-2 my-8 font-bold text-xxl">Ask a public question</h1>
      <div className="mb-8 so-card-info selection:bg-buttonPrimary/50">
        <h2 className="mb-3 text-2xl">Writing a good question</h2>
        <p className="text-md">
          You’re ready to{' '}
          <a
            href="https://stackoverflow.com/help/how-to-ask"
            className="text-secondary-400"
          >
            ask
          </a>{' '}
          a{' '}
          <a
            href="https://stackoverflow.com/help/on-topic"
            className="text-secondary-400"
          >
            programming-related question
          </a>{' '}
          and this form will help guide you through the process.
        </p>
        <p className="mb-2 text-md">
          Looking to ask a non-programming question? See{' '}
          <a
            href="https://stackexchange.com/sites#technology-traffic"
            className="text-secondary-400"
          >
            the topics here
          </a>{' '}
          to find a relevant site.
        </p>
        <h5 className="mb-2 font-bold">Steps</h5>
        <ul className="mb-0 leading-tight list-disc list-inside">
          <li>Summarize your problem in a one-line title.</li>
          <li>Describe your problem in more detail.</li>
          <li>Describe what you tried and what you expected to happen.</li>
          <li>
            Add “tags” which help surface your question to members of the
            community.
          </li>
          <li>Review your question and post it to the site.</li>
        </ul>
      </div>
      <div className="mb-6 so-card">
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
            placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
            className="w-full px-4 py-2 border rounded border-soGray-light"
            data-min-length="15"
            data-max-length="150"
            onChange={onChangeTitle}
          />
        </div>
      </div>
      <div className="mb-4 so-card">
        <h5 className="font-bold">What are the details of your problem?</h5>
        <p className="mb-4 text-sm">
          Introduce the problem and expand on what you put in the title. Minimum
          50 characters.
        </p>
        <Editor onChange={onChangeContent} height={'300px'} />
      </div>
      <div className="flex">
        <button
          className="mr-3 so-button-primary"
          type="button"
          autoComplete="off"
          onClick={onClickSubmit}
        >
          Review your question
        </button>

        <div className="flex--item">
          <button
            className="so-button-dismiss text-danger-700"
            type="button"
            onClick={onClickDiscard}
          >
            Discard draft
          </button>
        </div>
      </div>
    </div>
  );
}

export default QuestionCreate;
