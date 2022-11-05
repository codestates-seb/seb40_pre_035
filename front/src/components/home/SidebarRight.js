import { Link } from 'react-router-dom';
import AD1 from '../../images/AD1.jpeg';
import AD2 from '../../images/AD2.png';
import AD3 from '../../images/AD3.png';

export default function SidebarRight() {
  return (
    <div className="flex-col px-4 py-6 text-right text-xxs">
      <Link to="https://stackoverflow.com/">
        <img src={AD1} alt="AD1" />
        <span className="text-xs text-buttonPrimary">Report this ad</span>
      </Link>
      <Link to="https://github.com/codestates-seb/seb40_pre_035">
        <img src={AD2} alt="AD2" />
      </Link>
      <Link to="https://www.codestates.com/?gclid=Cj0KCQjwwfiaBhC7ARIsAGvcPe5Zz-55ceMDlTGoFYgzP7V6P6rcdrmHYZgVf3I_wIeEg6Lea4FquxMaAtsKEALw_wcB">
        <img src={AD3} alt="AD3" />
      </Link>
    </div>
  );
}
