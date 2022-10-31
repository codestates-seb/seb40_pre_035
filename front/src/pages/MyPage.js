// import React, { useState } from 'react';
import Profile from '../components/mypage_components/Profile';
import MyPageNav from '../components/mypage_components/MypageNav';

function MyPage() {
  return (
    <>
      <div className="p-6 border">
        <Profile />
        <MyPageNav />
      </div>
    </>
  );
}

export default MyPage;
