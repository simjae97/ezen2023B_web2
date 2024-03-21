import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

//1. 내가만든 컴포넌트 import(가져오기) 호출
// import 컴포넌트 from 컴포넌트 파일경로;
import JSX선언 from './chapter3/1_JSX선언';

import Book from './chapter3/Book';

import Library from './chapter3/Library';

import Clock from './chapter4/Clock';
import CommentList from './chapter5/CommentList';


const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render( //이곳에 렌더링 됩니다.
//   <React.StrictMode>
//     <Clock />

//   </React.StrictMode>

// );
// setInterval(()=> {
//   root.render(
//     <Clock />
//   )

// } , 1000);
root.render(
  <CommentList/>
)
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
