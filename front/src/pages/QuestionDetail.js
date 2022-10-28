import '../main.css';
import VoteController from '../components/VoteController';
import AnswerItem from '../components/AnswerItem';

function QuestionDetail() {
  return (
    <main className="content p-4">
      <div className="question-header mb-3 border-b border-soGray-light">
        <div className="question-title flex justify-between mb-4">
          <h2 className="row-auto break-words text-xl pr-2">
            Question 상세 페이지 제목이 들어갑니다Question 상세 페이지 제목이
            들어갑니다Question 상세 페이지 제목이 들어갑니다
          </h2>
          <div className="basis-52 flex justify-end">
            {/* <button className="so-button-primary">Ask Question</button> */}
            <button className="px-4 py-2 mx-1 text-white border rounded hover:bg-buttonPrimaryHover bg-buttonPrimary border-secondary-300">
              Ask Question
            </button>
          </div>
        </div>
        <div className="user-info flex flex-row align-center mb-4 text-sm">
          <a href="/users/2001654/musicamante" className="flex mr-4">
            <img
              src="https://i.stack.imgur.com/yERLj.jpg?s=32&amp;g=1"
              alt="musicamante's user avatar"
              width="16"
              height="16"
              className="mr-2"
            />
            <span className="text-soGray-darker">musicamante</span>
          </a>
          <time className="s-user-card--time">
            <a
              href="/questions/74211291/im-trying-to-embed-a-candle-chart-into-my-pyqt-app-but-it-doesnt-work-plea"
              className="flex"
            >
              <span className="text-soGray-normal mr-1">Asked</span>
              <span title="2022-10-26 16:37:16Z" className="text-soGray-darker">
                38 secs ago
              </span>
            </a>
          </time>
        </div>
      </div>
      <div className="question-body mb-10">
        <div className="inner-content">
          <div
            role="main"
            aria-label="question and answers"
            className="flex flex-row"
          >
            <div className="question-votes mr-4">
              <VoteController />
            </div>
            <div
              className="question-content flex-auto so-editor"
              style={{
                opacity: 0.5,
                height: '1000px',
                backgroundColor: 'rgba(0, 0, 0, 0.3)',
              }}
            >
              <div className="">
                <p>I have an SVG file: check.svg</p>

                <pre className=""></pre>

                <p>
                  I want a React component from it; but I don&apos;t want any
                  wrapper tag, just{' '}
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
        </div>
      </div>
      <div className="answers-group">
        <AnswerItem />
        <AnswerItem />
        <AnswerItem />
      </div>
      <div className="so-editor-create"></div>
    </main>
  );
}

export default QuestionDetail;
