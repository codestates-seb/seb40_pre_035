import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from './Header';
import Footer from './components/footer/Footer';
import Home from './pages/Home';
import Login from './pages/Login';
import Signup from './pages/Signup';
import MyPage from './pages/MyPage';
import Sidebar from './components/sidebar/Sidebar';
import QuestionList from './pages/QuestionList';
import QuestionCreate from './pages/QuestionCreate';
import QuestionUpdate from './pages/QuestionUpdate';
import QuestionDetail from './pages/QuestionDetail';
import NotFound from './components/notfound/NotFound';

function App() {
  return (
    <BrowserRouter>
      <Header />
      <div className="flex max-w-[1200px] mx-auto my-0 flex-row flex-nowrap justify-start items-start shadow-lg shadow-black/5 bg-white">
        <nav className="fixed w-[164px]">
          <Sidebar />
        </nav>
        <main className="flex flex-auto pl-[164px]">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/question" element={<QuestionList />} />
            <Route path="/mypage/*" element={<MyPage />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/question/create" element={<QuestionCreate />} />
            <Route path="/question/detail/:id" element={<QuestionDetail />} />
            <Route path="/question/update/:id" element={<QuestionUpdate />} />

            {/* 잘못된 주소로 접근한 경우 */}
            <Route path="*" element={<NotFound />} />
          </Routes>
        </main>
      </div>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
