const Card = () => {
    //   const themeState = useSelector((state) => state.themeSlice).theme;
    return (
      <div
        className="h-48 w-auto border-b-2 border-buttonSecondary
    text-sl leading-7 text-left p-6"
      >
        <div className="flex">
          <div className="votecontainer text-xs text-right mt-1 ml-2.5 mr-6 mb-1.5 w-24">
            <div className="Vote">0 votes</div>
            <div className="Answers">0 answers</div>
          </div>
          <div className="QuestionContainer pl-1.5">
            {/* <div className="Qustion"> */}
            <div className="QuestionTitle text-buttonPrimary text-2xl mb-2">
              질문 제목
            </div>
            <div className="QuestionSummary mb-2">질문 요약</div>
  
            <div
              className="UserInfoContainer flex  text-xs
            w-[40rem] mb-2 space-x-2"
            >
              <div className="Info">유저 정보 컨테이너</div>
              <div className="img h-6 w-6">img</div>
              <div className="userName text-buttonPrimary">유저이름</div>
              <div className="createdAt">작성일자</div>
            </div>
          </div>
        </div>
      </div>
  
      // </div>
    );
  };
  
  export default Card;