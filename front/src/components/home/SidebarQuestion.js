function SidebarQuestion() {
  return (
    <div>
      <div className="container sticky items-center t-14 w-48 flex flex-col pt-6 pb-16 bg-white">
        <div className="OL flex items-center  flex-col m-0 p-0 w-full h-full  font-xl ">
          <div className="flex justify-start items-center pl-4 w-full h-10">
            <div
              className="flex items-center h-full w-40 text-base text-black 
            "
            >
              <a href="/">
                <div className="flex mb-5  pt-2 font-normal text-sm hover:font-semibold">
                  Home
                </div>
              </a>
            </div>
          </div>
          <div className="flex justify-start items-center pl-4 w-full h-10">
            <div className="pb-2 pt-7  font-normal text-sm">PUBLIC</div>
          </div>
          <div
            className="styledLink li flex justify-start items-center pl-4 w-full h-10 bg-soGray-bg 
           text-base text-black font-bold
          active:bg-soGray-bg border-r-4  border-solid border-primary-400"
          >
            <a
              href="/questionMain"
              className="flex text-base   hover:font-semibold"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
                fill="currentColor"
                className="w-7 h-auto pr-2"
              >
                <path d="M15.75 8.25a.75.75 0 01.75.75c0 1.12-.492 2.126-1.27 2.812a.75.75 0 11-.992-1.124A2.243 2.243 0 0015 9a.75.75 0 01.75-.75z" />
                <path
                  fillRule="evenodd"
                  d="M12 2.25c-5.385 0-9.75 4.365-9.75 9.75s4.365 9.75 9.75 9.75 9.75-4.365 9.75-9.75S17.385 2.25 12 2.25zM4.575 15.6a8.25 8.25 0 009.348 4.425 1.966 1.966 0 00-1.84-1.275.983.983 0 01-.97-.822l-.073-.437c-.094-.565.25-1.11.8-1.267l.99-.282c.427-.123.783-.418.982-.816l.036-.073a1.453 1.453 0 012.328-.377L16.5 15h.628a2.25 2.25 0 011.983 1.186 8.25 8.25 0 00-6.345-12.4c.044.262.18.503.389.676l1.068.89c.442.369.535 1.01.216 1.49l-.51.766a2.25 2.25 0 01-1.161.886l-.143.048a1.107 1.107 0 00-.57 1.664c.369.555.169 1.307-.427 1.605L9 13.125l.423 1.059a.956.956 0 01-1.652.928l-.679-.906a1.125 1.125 0 00-1.906.172L4.575 15.6z"
                  clipRule="evenodd"
                />
              </svg>
              Questions
            </a>
          </div>
        </div>
      </div>
    </div>
  );
}

export default SidebarQuestion;
