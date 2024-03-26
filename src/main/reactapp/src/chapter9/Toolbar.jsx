import React,{useState} from "react";


export default function Toolbar(props){
        //1.props 매개변수
        const{isLoggedin , onClickLogin,onClickLogout} = props;

        return(<>
            <div>
                {
                    isLoggedin && <span> 환영합니다. </span>
                }
                {
                    isLoggedin ?
                    (<button onClick={onClickLogout}>로그아웃</button>):
                    (<button onClick={onClickLogin}>로그인</button>)
                }
            </div>
        
        
        
        </>)
}