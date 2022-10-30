import '../components/common.css';
import Editor from '../components/questions/Editor';

function QuestionUpdate() {
  const handleOnChangeTitle = () => {};
  return (
    <main className="bg-white py-4 px-6 pb-[80px] border-l-soGray-light border-l">
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
            value="e.g. Is there an R function for finding the index of an element in a vector?"
            className="w-full px-4 py-2 border rounded border-soGray-light"
            data-min-length="15"
            data-max-length="150"
            onChange={handleOnChangeTitle}
          />
        </div>
      </div>
      <div className="mb-4">
        <h5 className="mb-2 font-bold">Body</h5>
        <div className="mb-4">
          <Editor />
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
          disabled=""
        >
          Save edits
        </button>

        <div className="flex--item">
          <button
            className="so-button-dismiss text-secondary-400"
            data-action="s-modal#show"
            type="button"
          >
            Cancel
          </button>
        </div>
      </div>
    </main>
  );
}

export default QuestionUpdate;
