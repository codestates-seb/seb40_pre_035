// import React, { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import Profile from '../components/mypage_components/Profile';
import Sidebar from '../components/sidebar/Sidebar';
import Activitise from '../components/mypage_components/Activities';
import Settings from '../components/mypage_components/Settings';

function MyPage() {
  return (
    <div className="so-main-wrapper">
      <nav className="sticky max-h-[calc(100vh-180px)] top-[60px] w-[164px]">
        <Sidebar />
      </nav>
      <div className="so-main-content so-with-one-side">
        <div className="p-6">
          <Profile />
          <Routes>
            <Route exact path="/" element={<Activitise />} />
            <Route path="/activity" element={<Activitise />} />
            <Route path="/settings/*" element={<Settings />} />
          </Routes>
        </div>
      </div>
    </div>
  );
}

export default MyPage;
