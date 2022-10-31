// import React, { useState } from 'react';
import Profile from '../components/mypage_components/Profile';
import MyPageNav from '../components/mypage_components/MypageNav';
import Activitise from '../components/mypage_components/Activities';
import Settings from '../components/mypage_components/Settings';

export default function MyPage() {
  return (
    <>
      <div className="p-6 border">
        <Profile />
        <MyPageNav />
        <Activitise />
        <Settings />
      </div>
    </>
  );
}
