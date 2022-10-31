function Summary() {
  return (
    <div className="flex w-full">
      <div className="w-3/12 m-3">
        <p className="text-2xl font-medium">Stats</p>
        <div className="p-3 m-2 border rounded border-soGray-normal bg-soGray-headerbg">
          <div className="m-3 ">
            <p className="text-xl font-medium">0</p>
            total votes
          </div>
          <div className="m-3">
            <p className="text-xl font-medium">0</p>
            answers
          </div>
          <div className="m-3">
            <p className="text-xl font-medium">0</p>
            questions
          </div>
        </div>
      </div>
      <div className="flex flex-col justify-between w-9/12 m-3">
        <div>
          <p className="text-2xl font-medium">Answers</p>
          <p className="p-6 m-2 text-center border rounded border-soGray-normal bg-soGray-headerbg">
            You have not{' '}
            <a href="http://localhost:3000/" className="text-secondary-350">
              answered
            </a>{' '}
            any questions
          </p>
        </div>
        <div className="">
          <p className="text-2xl font-medium">Questions</p>
          <p className="p-6 m-2 text-center border rounded border-soGray-normal bg-soGray-headerbg">
            You have not{' '}
            <a href="http://localhost:3000/" className="text-secondary-350">
              asked
            </a>{' '}
            any questions
          </p>
        </div>
      </div>
    </div>
  );
}

export default Summary;
