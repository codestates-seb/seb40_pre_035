import { useState } from 'react';
import Summary from './Summary';
import Answers from './Answers';
import Questions from './Questions';

function Activitise() {
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
                    ? 'py-1.5 pr-12 pl-3'
                    : 'py-1.5 pr-12 pl-3'
                }
                onClick={() => selectMenuHandler(index)}
              >
                {ele.name}
              </button>
            );
          })}
          {/* <li className="py-1.5 pr-12 pl-3">Summary</li>
          <li className="py-1.5 pr-12 pl-3">Answers</li>
          <li className="py-1.5 pr-12 pl-3">Questions</li> */}
        </div>
      </nav>
      {menuArr[currentTab].content}
      {/* <Summary /> */}
      {/* <Answers /> */}
      {/* <Questions /> */}
    </div>
  );
}

export default Activitise;
