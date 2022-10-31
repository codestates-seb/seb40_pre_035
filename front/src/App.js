import './App.css';
import Header from './Header';
import Home from './pages/Home';
import Login from './pages/Login';
import MyPage from './pages/mypage';
import Signup from './pages/Signup';
import QuestionCreate from './pages/QuestionCreate';
import QuestionDetail from './pages/QuestionDetail';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/mypage" element={<MyPage />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/questioncreate" element={<QuestionCreate />} />
        <Route path="/questiondetail" element={<QuestionDetail />} />
        {/* 잘못된 주소로 접근한 경우 */}
        <Route path="*" element={<Home />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
