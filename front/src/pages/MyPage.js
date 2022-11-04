// import React, { useState } from 'react';
import Profile from '../components/mypage_components/Profile';
import MyPageNav from '../components/mypage_components/MypageNav';
import Sidebar from '../components/sidebar/Sidebar';

function MyPage() {
  return (
    <div className="so-main-wrapper">
      <nav className="sticky max-h-[calc(100vh-180px)] top-[60px] w-[164px]">
        <Sidebar />
      </nav>
      <div className="p-6 so-main-content">
        <Profile />
        <MyPageNav />
      </div>
    </div>
  );
}

export default MyPage;
