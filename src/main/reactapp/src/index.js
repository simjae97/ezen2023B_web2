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
import Counter from './chapter7/Counter';
import UseState from './chapter7/UseStateList';
import ConfirmButton from './chapter8/ConfirmButton';
import LandingPage from './chapter9/LandingPage';
import Counter2 from './chapter7/Counter2';
import AttendanceBook from './chapter10/AttendaceBook';
import NameForm from './chapter11/NameForm';
// import SignUp from './chapter11/SignUp';
import SignUp from './component/member/SignUp';
import Axios컴포넌트 from './chapter0/Axios컴포넌트';
import Route컴포넌트 from './chapter0/Route컴포넌트';
import { Link } from 'react-router-dom';
import Index from './component/Index';
import Calculator from './chapter12/Calculator';
import ProfileCard from './chapter13/ProfileCard';
import DarkOrLight from './chapter14/DarkOrLight';
import TextInputWithFocusButton from './chapter7/TextInputWithFocusButton';

const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render( //이곳에 렌더링 됩니다.
// //   <React.StrictMode>
// //     <Clock />

// //   </React.StrictMode>

// // );
// // setInterval(()=> {
// //   root.render(
// //     <Clock />
//   )

// } , 1000);
root.render(
  // <CommentList/>
  // <Counter />
  // <UseState />
  // <ConfirmButton />
  // <LandingPage />
  // <Counter2/>
  // <AttendanceBook/>
  // <NameForm/>
  // <SignUp />
  // <Axios컴포넌트/>
  // <Route컴포넌트/>
  <Index /> //스프링 연동용
  // <Calculator />
    // <ProfileCard/>

    // <DarkOrLight/>
    // <TextInputWithFocusButton/>
)
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
