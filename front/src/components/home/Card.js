function Card({ item }) {
  //   const themeState = useSelector((state) => state.themeSlice).theme;

  return (
    <div className="w-auto p-4 leading-7 text-left border-b text-md border-soGray-light">
      <div className="flex">
        <div className="votecontainer text-[13px] text-right mt-1 ml-2.5 mr-6 mb-1.5">
          <div className="Vote">{item.totalVote} votes</div>
          <div className="Answers">{item.answerCount} answers</div>
        </div>
        <div className="QuestionContainer pl-1.5">
          <div className="mb-2 text-[1.2rem] QuestionTitle text-buttonPrimary">
            {item.title}
          </div>
          <div className="mb-2 QuestionSummary">{item.content}</div>

          <div
            className="UserInfoContainer flex  text-xs
            w-[40rem] mb-2 space-x-2"
          >
            <div className="img">{item.account.profile}</div>
            <div className="userName text-buttonPrimary">
              {item.account.nickname}
            </div>
            <div className="createdAt">{item.createdAt}</div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Card;
