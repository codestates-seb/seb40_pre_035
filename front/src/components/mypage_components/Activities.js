import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import Summary from './Summary';
import Answers from './Answers';
import Questions from './Questions';
import { EC2 } from '../../util/fetchLogin';

const Activitise = () => {
  const { id } = useParams();
  const [idData, setIdData] = useState();
  const [currentTab, setCurrentTab] = useState(0);

  const menuArr = [
    { name: 'Summary', content: <Summary /> },
    { name: 'Answers', content: <Answers /> },
    { name: 'Questions', content: <Questions /> },
  ];

  const selectMenuHandler = (index) => {
    setCurrentTab(index);
  };

  useEffect(() => {
    fetch(`${EC2}/accounts/${id}`)
      .then((res) => {
        if (!res.ok) {
          // error coming back from server
          throw Error('could not fetch the data for that resource');
        }
        return res.json();
      })
      .then((idData) => {
        setIdData(idData);
      })
      .catch((err) => {
        console.error(err.message);
      });
  }, []);

  const login = () => {
    return sessionStorage.userEmail === idData?.email;
  };

  const isLogin = { true: '', false: 'invisible' };

  return (
    <>
      <div className="w-full mb-28 ">
        <ul className="flex mt-2 mb-4">
          <li>
            <Link to={`/mypage/${id}/activity`}>
              <button className="m-0.5 py-1.5 px-3 bg-primary-400 rounded-2xl hover:bg-primary-700 text-white">
                Activity
              </button>
            </Link>
          </li>
          <li className={isLogin[login() ?? false]}>
            <Link to={`/mypage/${id}/settings`}>
              <button className="m-0.5 py-1.5 px-3 hover:bg-soGray-normal hover:rounded-2xl">
                Settings
              </button>
            </Link>
          </li>
        </ul>

        <div className="flex">
          <nav className="my-3 ml-3 mr-8">
            <div className="flex flex-col">
              {menuArr.map((ele, index) => {
                return (
                  <button
                    key={index}
                    className={
                      currentTab === index
                        ? 'py-1.5 pr-12 pl-3 m-0.5 bg-soGray-bg rounded-2xl'
                        : 'py-1.5 pr-12 pl-3 m-0.5 hover:bg-soGray-light hover:rounded-2xl'
                    }
                    onClick={() => selectMenuHandler(index)}
                  >
                    {ele.name}
                  </button>
                );
              })}
            </div>
          </nav>
          {menuArr[currentTab].content}
        </div>
      </div>
    </>
  );
};

export default Activitise;
