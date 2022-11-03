import Pagination from './Pagination';
import ListTop from './QuestionListTop';
import ListMiddle from './QuestionListMiddle';
import Card from './Card';
import { useState, useEffect } from 'react';
import { BASE_URL } from '../../util/api';

function Mainbar({ questionId }) {
  const [questionList, setQuestionList] = useState([]);
  const [isPending, setIsPending] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [postsPerPage, setPostsPerPage] = useState(10);

  useEffect(() => {
    fetch(`${BASE_URL}/questions?page=1&size=10&sort=id%2Cdesc`)
      .then((response) => {
        if (!response.ok) {
          throw Error('could not fetch the data for that resource');
        }
        return response.json();
      })
      .then((data) => {
        setIsPending(false);
        setQuestionList(data.content);
      })
      //통신이 되면 json을 스테이츠로 변환
      .catch((error) => {
        setIsPending(false);
        throw Error(error.message);
      });
  }, [questionId]);

  if (isPending) {
    return <div>Loading...</div>;
  }

  const indexOfLastPost = currentPage * postsPerPage;
  const indexOfFirstPost = indexOfLastPost - postsPerPage;
  const currentPosts = questionList.slice(indexOfFirstPost);

  return (
    <>
      <div className="flex flex-col">
        <ListTop />
        <div className="pt-10">
          <ListMiddle />
        </div>
        {questionList && (
          <div className="questionList">
            {questionList.map((card) => (
              <Card key={card.id} item={card} />
            ))}
          </div>
        )}
        <div className="mt-10">
          <Pagination
            postsPerPage={postsPerPage}
            totalPosts={questionList.length}
          />
        </div>
      </div>
    </>
  );
}

export default Mainbar;
