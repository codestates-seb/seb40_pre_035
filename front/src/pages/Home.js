import SidebarHome from '../components/home/SidebarHome';
import SidebarRight from '../components/home/SidebarRight';
import QuestionAskBox from '../components/home/QuestionAskBox';
import { useState, useEffect } from 'react';
import { BASE_URL } from '../util/api';
import Card from '../components/home/Card';

function Home(questionId) {
  const [questionList, setQuestionList] = useState([]);
  const [isPending, setIsPending] = useState(true);

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

  return (
    <>
      <div className="content-around" />
      <div className="relative flex h-auto mx-auto my-0 1-0-auto">
        <SidebarHome />
        <div className="flex p-10 leading-7 columns">
          <div className="pl-8">
            <div className="flex">
              <div className="flex flex-col">
                <div className="flex space-x-0">
                  <div className="text-3xl pr-96">Top Questions</div>
                  <div className="pl-5 mb-2">
                    <QuestionAskBox />
                  </div>
                </div>
                <div className="mt-16">
                  {questionList && (
                    <div className="questionList">
                      {questionList.map((card) => (
                        <Card key={card.id} item={card} />
                      ))}
                    </div>
                  )}
                </div>
              </div>
            </div>
          </div>
          <div className="cardLayout">
            <div className="card"></div>
          </div>
        </div>
      </div>
    </>
  );
}

export default Home;
