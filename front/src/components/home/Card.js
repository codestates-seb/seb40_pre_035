function Card({ item }) {
  //   const themeState = useSelector((state) => state.themeSlice).theme;
  console.log('item');

  return (
    <div
      className="h-48 w-auto border-b-2 border-buttonSecondary
    text-sl leading-7 text-left p-6"
    >
      <div className="flex">
        <div className="votecontainer text-xs text-right mt-1 ml-2.5 mr-6 mb-1.5 w-24">
          <div className="Vote">{item.totalVote} votes</div>
          <div className="Answers">{item.answerCount} answers</div>
        </div>
        <div className="QuestionContainer pl-1.5">
          <div className="QuestionTitle text-buttonPrimary text-2xl mb-2">
            {item.title}
          </div>
          <div className="QuestionSummary mb-2">{item.content}</div>

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
