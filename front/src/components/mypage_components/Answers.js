function Answers() {
  return (
    <div className="w-full m-3">
      <p className="mb-2 text-2xl font-medium">0 Answers</p>
      <div className="border rounded border-soGray-normal">
        <p className="p-12 m-2 text-center ">
          You have not{' '}
          <a href="http://localhost:3000/" className="text-secondary-500">
            answered
          </a>{' '}
          any questions
        </p>
        <div className="p-3 border-t border-soGray-normal">
          <a href="http://localhost:3000/" className="text-secondary-500">
            Deleted answers
          </a>
        </div>
      </div>
    </div>
  );
}

export default Answers;
