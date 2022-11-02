import { useState } from 'react';
import Summary from './Summary';
import Answers from './Answers';
import Questions from './Questions';

const Activitise = () => {
  const [currentTab, setCurrentTab] = useState(0);

  const menuArr = [
    { name: 'Summary', content: <Summary /> },
    { name: 'Answers', content: <Answers /> },
    { name: 'Questions', content: <Questions /> },
  ];

  const selectMenuHandler = (index) => {
    setCurrentTab(index);
  };

  return (
    <div className="flex ">
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
  );
};

export default Activitise;
