import VoteController from './VoteController';

VoteController;
function AnswerItem() {
  return (
    <div className="answer-item flex flex-row mb-5">
      <VoteController />
      <div
        className="question-content flex-auto so-editor"
        style={{
          opacity: 0.5,
          height: '200px',
          backgroundColor: 'rgba(0, 255, 0, 0.2)',
        }}
      >
        <div className="">
          <p>I have an SVG file: check.svg</p>

          <pre className=""></pre>

          <p>
            I want a React component from it; but I don&apos;t want any wrapper
            tag, just{' '}
          </p>

          <pre className="">
            <code className="">
              &lt;svg&gt;
              <span className="">
                <span className="">
                  &lt;<span className="">path</span> […]/&gt;
                </span>
              </span>
              &lt;/svg&gt;
            </code>
          </pre>

          <p>I know I can do something like</p>

          <pre className="">
            <code className="">
              <span className="">const</span>
              <span className="">renderSvg</span>
            </code>
          </pre>

          <p>or even easier</p>

          <pre className=""></pre>

          <p>
            Does anyone know how to achieve this without using an external
            library?
          </p>
        </div>
      </div>
    </div>
  );
}

export default AnswerItem;
