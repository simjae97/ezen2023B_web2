import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function Header(props){
    //로그인 상태 변수
    const[logininfo,setLogin] = useState("");
    //컴포넌트 생성시 axios 실행해서 회원정보 호출
    useEffect(()=>{
        axios.get("/member/login/info/get.do")
            .then( (r)=>{console.log(r)
                    setLogin(r.data)
            } )
            .catch( (e) => {console.log(e)})


    } ,[])
    
    const logoutHandler = ()=>{
        axios.get("/member/logout/get.do").then( (r)=>{ if(r.data){
            alert("로그아웃 성공")
            setLogin("")
        } } )

    }

    const nowlogin = <ul><li>{logininfo.memail}님</li><li><Link to="/">홈</Link></li><li onClick={logoutHandler}>로그아웃</li><li><Link to="/member/login"> 마이페이지(아직없음) </Link></li></ul>
    const nownotlogin = <ul>
        <li><Link to="/"> 홈 </Link></li>
        <li><Link to="/member/signup"> 회원가입 </Link></li>
        <li><Link to="/member/login"> 로그인 </Link></li>
    </ul>
    return(<>
    <div>
        { logininfo ? nowlogin : nownotlogin}
    </div>
    </>)
}