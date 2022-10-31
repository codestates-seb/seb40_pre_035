import { IconLogoGlyphMd } from '@stackoverflow/stacks-icons';
import ReactHtmlParser from 'react-html-parser';
const Footer = () => {
  return (
    <div className="flex-col">
      <div className="flex px-4 pt-8 align-top text-soGray-light bg-soGray-footerbg">
        <div className="flex mx-5">{ReactHtmlParser(IconLogoGlyphMd)}</div>
        <div className="mt-2 mb-10 grow">
          <div className="font-bold">STACK OVERFLOW</div>
          <div className="mt-4 text-xxs">
            Site design / logo © 2022 Stack Exchange Inc; user contributions
            licensed under CC BY-SA. rev 2022.10.28.42999
          </div>
        </div>
        <div className="flex mx-2 mt-2">
          <a href="/" className="mx-1">
            Questions
          </a>
          <a href="https://stackoverflow.com/help" className="mx-1">
            Help
          </a>
        </div>
      </div>
    </div>
  );
};

export default Footer;
