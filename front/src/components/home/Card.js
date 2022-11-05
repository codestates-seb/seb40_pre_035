import { Link } from 'react-router-dom';
import relTimeFormat from '../../util/relativeTimeFormat';

function Card({ item }) {
  return (
    <div className="w-auto p-4 pr-0 leading-7 text-left border-b text-md border-soGray-light">
      <div className="flex w-full">
        <div className="text-[13px] text-right mt-1 mr-6 shrink-0 grow-0 basis-[80px]">
          <div className="Vote">{item.totalVote} votes</div>
          <div className="Answers">{item.answerCount} answers</div>
        </div>
        <div className="flex flex-col flex-wrap shrink-1 grow-1 w-[calc(100%-105px)] basis-auto pl-1.5">
          <div className="text-[1.2rem] line-clamp-2 break-all w-full text-buttonPrimary font-semibold mb-2">
            <Link to={`/question/detail/${item.id}`}>{item.title}</Link>
          </div>
          <div className="w-full mb-4 text-sm break-all line-clamp-2">
            {item.content
              .split('')
              .map((el) => el.match(/^[a-zA-Zㄱ-힣0-9]*$/))
              .join('')}
          </div>

          <div className="w-full flex items-center flex-row text-[13px] text-soGray-darker space-x-2">
            <Link to={`/mypage/${item.account.id}`}>
              <div className="w-[24px] h-[24px]">
                {item.account.profile && (
                  <img
                    className="w-full h-auto"
                    src={item.account.profile}
                    alt={`${item.account.nickname}'s Avatar`}
                  />
                )}
              </div>
              <div className="font-semibold text-soGray-darker">
                {item.account.nickname}
              </div>
            </Link>
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
