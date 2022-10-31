function Summary() {
  return (
    <div className="flex w-full">
      <div className="m-3 w-3/12">
        <p className="text-2xl">Stats</p>
        <div className="border p-3 m-2">
          <div className="m-3">
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
      <div className="m-3 w-9/12 flex flex-col justify-between">
        <div>
          <p className="text-2xl">Answers</p>
          <p className="border p-6 m-2 text-center">
            You have not{' '}
            <a href="http://localhost:3000/" className="text-blue-700">
              answered
            </a>{' '}
            any questions
          </p>
        </div>
        <div className="">
          <p className="text-2xl">Questions</p>
          <p className="border p-6 m-2 text-center">
            You have not{' '}
            <a href="http://localhost:3000/" className="text-blue-700">
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
