import React, { useEffect, useRef } from 'react';
import { createRoot } from 'react-dom/client';

const TOAST_ID = 'toast';
const TOAST_MS = 1000;

export const ShowToast = (message = 'default message') => {
  let container = document.getElementById(TOAST_ID);
  if (!container) {
    container = document.createElement('div');
    container.setAttribute('id', TOAST_ID);
    document.body.appendChild(container);
  }

  const root = createRoot(container);
  root.render(<Toast message={message} target={root} />);
};

export const HideToast = (target) => {
  target.unmount();
};

const Toast = ({ message, target }) => {
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
    <div className="so-toast" ref={toast}>
      {message}
    </div>
  );
};
