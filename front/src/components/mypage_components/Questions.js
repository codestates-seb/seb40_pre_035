function Questions() {
  return (
    <div className="w-full m-3">
      <p className="mb-2 text-2xl font-medium">0 Questions</p>
      <div className="border rounded">
        <p className="p-12 m-2 text-center">
          You have not{' '}
          <a href="http://localhost:3000/" className="text-blue-700">
            asked
          </a>{' '}
          any questions
        </p>
        <div className="p-4 text-blue-700 border-t">
          <a href="http://localhost:3000/">Deleted questions</a>
        </div>
      </div>
    </div>
  );
}

export default Questions;
