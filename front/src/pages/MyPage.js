// import React, { useState } from 'react';
import Profile from '../components/mypage_components/Profile';
import MyPageNav from '../components/mypage_components/MypageNav';

function MyPage() {
  return (
    <>
      <div className="p-6 pb-20 border-l border-r w-[1036px] border-soGray-bg">
        <Profile />
        <MyPageNav />
      </div>
    </>
  );
}

export default MyPage;
