function Answers() {
  return (
    <div className="w-full">
      <p className="text-2xl font-medium">0 Answers</p>
      <div className="border rounded">
        <p className="p-12 m-2 text-center">
          You have not{' '}
          <a href="http://localhost:3000/" className="text-blue-700">
            answered
          </a>{' '}
          any questions
        </p>
        <div className="text-blue-700 p-4 border-t">
          <a href="http://localhost:3000/">Deleted answers</a>
        </div>
      </div>
    </div>
  );
}

export default Answers;
