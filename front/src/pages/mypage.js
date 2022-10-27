// import React, { useState } from 'react';
import Profile from '../componenets/mypage_components/profile';
import MyPageNav from '../componenets/mypage_components/mypage_nav';
import Activitise from '../componenets/mypage_components/activities';
// import Settings from '../componenets/mypage_components/settings';

export default function MyPage() {
  return (
    <>
      <div className="border p-6">
        <Profile />
        <MyPageNav />
        <Activitise />
      </div>
    </>
  );
}
