import './App.css';
import Header from './Header';
import Footer from './components/footer/Footer';
import Home from './pages/Home';
import Login from './pages/Login';
import MyPage from './pages/MyPage';
import Signup from './pages/Signup';
import QuestionCreate from './pages/QuestionCreate';
import QuestionDetail from './pages/QuestionDetail';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

function App() {
  return (
    // <>
    //   <MyPage />
    // </>
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/mypage/*" element={<MyPage />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/questioncreate" element={<QuestionCreate />} />
        <Route path="/questiondetail/:id" element={<QuestionDetail />} />
        {/* 잘못된 주소로 접근한 경우 */}
        <Route path="*" element={<Home />} />
      </Routes>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
