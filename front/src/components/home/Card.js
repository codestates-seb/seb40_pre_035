import { Link } from 'react-router-dom';
import { useEffect } from 'react';
import { relTimeFormat } from '../../util/convertor';
import { fetchAnswerList } from '../../util/fetchAnswer';

function Card({ item }) {
  const answerType = (count, selected) => {
    if (selected) return 'so-answers-type3';
    if (+count > 0) return 'so-answers-type2';
    else return 'so-answers-type1';
  };

  return (
    <div className="w-auto p-4 pr-0 leading-7 text-left border-t text-md border-soGray-light">
      <div className="flex w-full">
        <div className="text-[13px] text-right mt-1 mr-6 shrink-0 grow-1 basis-[100px]">
          <div className="Vote">{item.totalVote} votes</div>
          <div
            className={`${answerType(item.answerCount, item.selectedAnswer)}`}
          >
            {item.selectedAnswer ? (
              <svg
                aria-hidden="true"
                className="svg-icon iconCheckmarkSm mr-2"
                width="14"
                height="14"
                viewBox="0 0 14 14"
                fill="#fff"
              >
                <path d="M13 3.41 11.59 2 5 8.59 2.41 6 1 7.41l4 4 8-8Z"></path>
              </svg>
            ) : (
              ''
            )}
            <span>{item.answerCount} answers</span>
          </div>
        </div>
        <div className="flex flex-col flex-wrap shrink-1 grow-1 w-[calc(100%-105px)] basis-auto pl-1.5">
          <div className="text-[1.2rem] line-clamp-2 break-all w-full text-buttonPrimary font-semibold mb-2">
            <Link to={`/question/${item.id}`}>{item.title}</Link>
          </div>
          <div className="w-full mb-4 text-sm break-all line-clamp-2">
            {/* {item?.content
              .split('')
              .map((el) => el.match(/^[a-zA-Zㄱ-힣0-9]*$/))
              .join('')} */}
            {item.content &&
              item.content.replace(/"/g, '').replace(/<[^>]*>?/g, '')}
          </div>

          <div className="w-full flex flex-row justify-end text-[13px] text-soGray-darker space-x-2">
            <Link
              to={`/mypage/${item.account.id}`}
              className="flex items-center mr-2"
            >
              {item.account.profile && item.account.profile !== 'test/path' ? (
                <img
                  className="border border-buttonSecondary rounded w-[20px] h-[20px] mr-2"
                  src={item.account.profile}
                  alt={`${item.account.nickname}'s Avatar`}
                />
              ) : (
                <span className=" border border-buttonSecondary rounded w-[20px] h-[20px] mr-2"></span>
              )}
              <span className="font-semibold text-soGray-darker">
                {item.account.nickname}
              </span>
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
