/* eslint-disable jsx-a11y/alt-text */
import AD1 from '../../images/AD1.jpeg';
import AD2 from '../../images/AD2.png';
import AD3 from '../../images/AD3.png';

export default function SidebarRight() {
  return (
    // <div className="layout w-96 mt-5 ml-9">
    <div className="flex flex-col h-screen p-4 inset-y-0 right-0 gap-1 bg-white shadow w-60 text-right">
      <div>
        <a href="https://stackoverflow.com/">
          <img src={AD1} />
        </a>
        <span className="text-xs text-buttonPrimary">Report this ad</span>
      </div>

      <div>
        <a href="https://github.com/">
          <img src={AD2} />
        </a>
      </div>
      <div>
        <a href="https://www.codestates.com/?gclid=Cj0KCQjwwfiaBhC7ARIsAGvcPe5Zz-55ceMDlTGoFYgzP7V6P6rcdrmHYZgVf3I_wIeEg6Lea4FquxMaAtsKEALw_wcB">
          <img src={AD3} />
        </a>
      </div>
    </div>
    // </div>
  );
}
