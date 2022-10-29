import VoteController from '../components/questions/VoteController';
import AnswerItem from '../components/questions/AnswerItem';
import Editor from '../components/questions/Editor';
import '../components/common.css';

function QuestionDetail() {
  return (
    <main className="p-4 content">
      <div className="mb-3 border-b question-header border-soGray-light">
        <div className="flex justify-between mb-4 question-title">
          <h2 className="row-auto pr-2 text-2xl font-medium break-words">
            Question 상세 페이지 제목이 들어갑니다Question 상세 페이지 제목이
            들어갑니다Question 상세 페이지 제목이 들어갑니다
          </h2>
          <div className="flex justify-end basis-52">
            <button className="so-button-primary">Ask Question</button>
          </div>
        </div>
        <div className="flex flex-row mb-4 text-sm user-info align-center">
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
              <span className="mr-1 text-soGray-normal">Asked</span>
              <span title="2022-10-26 16:37:16Z" className="text-soGray-darker">
                38 secs ago
              </span>
            </a>
          </time>
        </div>
      </div>
      <div className="mb-10 question-body">
        <div className="inner-content">
          <div
            role="main"
            aria-label="question and answers"
            className="flex flex-row"
          >
            <div className="mr-4 question-votes">
              <VoteController />
            </div>
            <div
              className="flex-auto question-content so-editor"
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
      <Editor />
    </main>
  );
}

export default QuestionDetail;
