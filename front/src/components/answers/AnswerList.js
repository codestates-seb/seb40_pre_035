import { useState, useEffect } from 'react';
import Editor from '../questions/Editor';
import AnswerItem from './AnswerItem';
import { BASE_URL } from '../../util/api';

function AnswerList({ questionId }) {
  console.log(questionId);
  const [answerList, setAnswerList] = useState([]);
  const [isPending, setIsPending] = useState(true);

  useEffect(() => {
    fetch(`${BASE_URL}/answers?page=1&size=10&sort=id%2Cdesc`)
      .then((response) => {
        if (!response.ok) {
          throw Error('could not fetch the data for that resource');
        }
        return response.json();
      })
      .then((data) => {
        setIsPending(false);
        setAnswerList(data.content);
      })
      .catch((error) => {
        setIsPending(false);
        throw Error(error.message);
      });
  }, [questionId]);

  if (isPending) {
    return <div>Loading...</div>;
  }

  return (
    <>
      {console.log(answerList)}
      {answerList && (
        <div className="answers-group">
          {answerList.map((answerItem) => (
            <AnswerItem key={answerItem.id} item={answerItem} />
          ))}
        </div>
      )}
      <Editor />
    </>
  );
}

export default AnswerList;
