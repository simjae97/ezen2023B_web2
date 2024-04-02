import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./layout/Header";
import Footer from "./layout/Footer";
import Home from './layout/Home'
import SignUp from "./member/SignUp";
import Login from "./member/Login";
import React, { useState } from "react";
import BoardWrite from "./board/BoardWrite";
import BoardAllView from "./board/BoardAllView";





/////////////////////////////////////////컨텍스트만들기////////////////////////////
//1.React.createContext()이용한 컨텍스트 선언
export const LoginInfoContext = React.createContext("");
//2.provide 해당 컨텍스트를 사용할 컴포넌트들을 감싼다

//3.컨텍스트 사용할 컴포넌트에서 컨텍스트를 호출한다


export default function Index(props){
    
    const[logininfo,setLogin] = useState("");

    return(<>
        <LoginInfoContext.Provider value={{logininfo,setLogin}}>
            <BrowserRouter>
                <div id="wrap">
                    <Header/>
                    <Routes>
                        <Route path="/" element={<Home/>}/>
                        <Route path="/member/signup" element={<SignUp/>}/>
                        <Route path="/member/login" element={<Login />}/>
                        <Route path="/board/write" element={<BoardWrite/>}/> 
                        <Route path="/board" element={<BoardAllView/>}/>
                    </Routes>
                    <Footer/>
                </div>
            </BrowserRouter>
        </LoginInfoContext.Provider>
    </>)
}