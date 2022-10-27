import Activitise_SideBar from './activities_sidebar';
import Summary from './summary';
// import Answers from './answers';
// import Questions from './questions';

function Activitise() {
  return (
    <div className="flex">
      <Activitise_SideBar />
      <Summary />
      {/* <Answers /> */}
      {/* <Questions /> */}
    </div>
  );
}

export default Activitise;
