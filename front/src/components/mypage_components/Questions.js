import { Link } from 'react-router-dom';

const Questions = () => {
  return (
    <div className="w-full m-3">
      <p className="mb-2 text-2xl font-medium">0 Questions</p>
      <div className="border rounded border-soGray-normal">
        <p className="p-12 m-2 text-center">
          You have not{' '}
          <Link to="/" className="text-secondary-500">
            asked
          </Link>{' '}
          any questions
        </p>
        <div className="p-3 border-t border-soGray-normal ">
          <Link to="/" className="text-secondary-500">
            Deleted questions
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Questions;
