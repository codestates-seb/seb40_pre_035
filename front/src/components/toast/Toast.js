import React, { useEffect, useRef } from 'react';
import { createRoot } from 'react-dom/client';

const TOAST_ID = 'toast';
const TOAST_MS = 1500;

export const showToast = (message = 'default message', type = 'confirm') => {
  let container = document.getElementById(TOAST_ID);

  if (!container) {
    container = document.createElement('div');
    container.setAttribute('id', TOAST_ID);
    document.body.appendChild(container);

    const root = createRoot(container);
    root.render(
      <React.StrictMode>
        <Toast message={message} target={root} type={type} />
      </React.StrictMode>
    );
  }
};

export const HideToast = (target) => {
  target.unmount();
};

const Toast = ({ message, target, type }) => {
  const toast = useRef();
  let timer = -1;

  const onStart = () => {
    timer = window.setTimeout(() => {
      HideToast(target);
    }, TOAST_MS);
  };

  useEffect(() => {
    onStart();
    toast.current?.focus();
  });

  console.log(message);
  return (
    <div
      className={`so-toast ${
        type === 'danger' ? 'so-toast-danger' : 'so-toast-confirm'
      }`}
      ref={toast}
    >
      {message}
    </div>
  );
};
