import { Link } from 'react-router-dom';
import relTimeFormat from '../../util/relativeTimeFormat';

function Card({ item }) {
  //   const themeState = useSelector((state) => state.themeSlice).theme;
  console.log(item);
  return (
    <div className="w-auto p-4 leading-7 text-left border-b text-md border-soGray-light">
      <div className="flex">
        <div className="votecontainer text-[13px] text-right mt-1 ml-2.5 mr-6 mb-1.5">
          <div className="Vote">{item.totalVote} votes</div>
          <div className="Answers">{item.answerCount} answers</div>
        </div>
        <div className="QuestionContainer pl-1.5">
          <div className="text-[1.2rem] QuestionTitle text-buttonPrimary font-semibold">
            <Link to={`/question/detail/${item.id}`}>{item.title}</Link>
          </div>
          <div className="mb-2 truncate QuestionSummary">{item.content}</div>

          <div
            className="UserInfoContainer flex text-[13px] text-soGray-darker
            w-[40rem] mb-2 space-x-2"
          >
            <img
              src={item.account.profile}
              alt={`${item.account.nickname}'s Avatar`}
            />
            <div className="font-semibold userName text-soGray-darker">
              {item.account.nickname}
            </div>
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
          </div>
        </div>
      </div>
    </div>
  );
}

export default Card;
