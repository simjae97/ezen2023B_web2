import React,{useState} from "react";
import Toolbar from "./Toolbar";


export default function LandingPage(props){
        //1.state 상태변수
        const [isLoggedin , setIsLoggedin] = useState(false);
        
        const onClickLogin = () =>{
            setIsLoggedin(true);
        }
        const onClickLogout = () =>{
            setIsLoggedin(false);
        }
        return(<>
            <Toolbar isLoggedin={isLoggedin} onClickLogin={onClickLogin} onClickLogout={onClickLogout}/>
            <div>안녕안녕</div>
        </>)
}