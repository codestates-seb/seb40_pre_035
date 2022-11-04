import {
  IconArrowUpDown,
  IconAchievements,
  IconTags,
  IconSpeechBubbleQuestion,
} from '@stackoverflow/stacks-icons';
import ReactHtmlParser from 'react-html-parser';
const SOInfo = () => {
  return (
    <div className="mb-[100px] mr-[80px]">
      <div className="my-10 text-xxl">Join the Stack Overflow community</div>

      <div className="flex my-5">
        <div className="items-center mx-2 my-1">
          {ReactHtmlParser(IconSpeechBubbleQuestion)}
        </div>
        <div>Get unstuck — ask a question</div>
      </div>
      <div className="flex my-5">
        <div className="items-center mx-2 my-1">
          {ReactHtmlParser(IconArrowUpDown)}
        </div>
        <div>Unlock new privileges like voting and commenting</div>
      </div>
      <div className="flex my-5">
        <div className="items-center mx-2 my-1">
          {ReactHtmlParser(IconTags)}
        </div>
        <div>Save your favorite tags, filters, and jobs</div>
      </div>
      <div className="flex my-5">
        <div className="items-center mx-2 my-1 ">
          {ReactHtmlParser(IconAchievements)}
        </div>
        <div>Earn reputation and badges</div>
      </div>

      <div className="text-sm">
        Collaborate and share knowledge with a private group for FREE.
        <br></br>
        Get Stack Overflow for Teams free for up to 50 users.
      </div>
    </div>
  );
};

export default SOInfo;
