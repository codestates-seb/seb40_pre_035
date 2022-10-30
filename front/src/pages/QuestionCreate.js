import '../components/common.css';
import Editor from '../components/questions/Editor';

function QuestionCreate() {
  const handleOnChangeTitle = () => {};
  return (
    <main className="so-askQuestion pb-[80px]">
      <h1 className="my-8 so-header-title">Ask a public question</h1>
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
            onChange={handleOnChangeTitle}
          />
        </div>
      </div>
      <div className="mb-4 so-card">
        <h5 className="font-bold">What are the details of your problem?</h5>
        <p className="mb-4 text-sm">
          Introduce the problem and expand on what you put in the title. Minimum
          20 characters.
        </p>
        <Editor />
      </div>
      <div className="flex">
        <button
          className="mr-3 so-button-primary"
          type="button"
          autoComplete="off"
          disabled=""
        >
          Review your question
        </button>

        <div className="flex--item">
          <button
            className="so-button-dismiss text-danger-700"
            data-action="s-modal#show"
            type="button"
          >
            Discard draft
          </button>
        </div>
      </div>
    </main>
  );
}

export default QuestionCreate;
