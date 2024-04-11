import { useContext, useEffect, useRef, useState } from "react";
import { LoginInfoContext } from "../Index";
import styles from "./Chatting.css"
import * as React from 'react';
import Button from '@mui/material/Button';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import { type } from "@testing-library/user-event/dist/type";

export default function Chatting(props){
    //--------------------------(클라이언트)웹소켓구현(자바에서 공식적으로 지원)--------------------------------------------//
    //1.웹 소켓 생성 new WebSocket(서버소켓url)
    //1.해당 컴포넌트가 렌더링 될떄 소켓 재렌더링 방지
    //참조값 고정
    let clientSocket = useRef(null);
    //ref에 참조가 없으면
    if(!clientSocket.current){
        clientSocket.current = new WebSocket("ws://192.168.17.10:80/chat");
        //onmclose // onerror // onmessage // onopen : 웹소켓 객체네 포함된 메소드들
            //2.각 메소드 정의
                //1.클라이언트 소켓이 close발생했을때 콜백함수 정의 
        clientSocket.current.onclose = (e) =>{console.log(e); console.log("닫힘")}
                //2.클라이언트 소켓이 error 발생했을때 콜백함수 정의(에러는 이미 발생했고 그 다음 행동 정의)
        clientSocket.current.onerror = (e) =>{console.log(e); console.log("에러")}
                //3.클라이언트 소켓이 메세지를 받았을 경우 콜백함수 정의
        clientSocket.current.onmessage = (e)=>{
            msgList.push(JSON.parse(e.data))
            setMsgList([...msgList]);
        } 
                //4.클라이언트 소켓이 open 발생했을때 콜백함수 정의
        clientSocket.current.onopen = (e)=>{console.log(e); console.log("서버 소켓연결")}
        console.log(clientSocket);
        
        //2. 연결된 소켓에게 메세지 보내기
        
        //3. 서버 소켓으로부터 메세지받기
    
        //4.연결 종료
    }

    //===============================================================================================

    const{logininfo , setLogin} =  useContext(LoginInfoContext)
    
    const onSend = ()=>{
        
        let info = {
            msg : msgInput,                     //작성된 내용
            fromMname : logininfo.mname ,      //작성자
            date : new Date(),
            type : "msg"
        }
        // 3. 연결된 서버 소켓에게 메세지 보내기
            // send() : 데이터 타입 :텍스트
                // Json -> 문자: JSON.stringify(js객체)
                // 문자 -> json : JSON.parse
        clientSocket.current.send(JSON.stringify(info));
        //메세지 초기화
        setMsgInput("");
    }

    //이모티콘 클릭시 이미지 전송
    const onEmoSend = (emo) =>{
        let info ={
            msg : emo,
            fromMname : logininfo.mname,
            date : new Date(),
            type : "emo"
        }
        clientSocket.current.send(JSON.stringify(info));
        handleClose();
    }


    //채팅내용입력창
    const [msgInput, setMsgInput] = useState("");
    //채팅 목록의 내용물들
    const [msgList , setMsgList] = useState([]);

    const activeEnter = (e) =>{

        if(e.keyCode == 13 && e.ctrlKey){
            setMsgInput(msgInput + "\n");
            return;
        }

        if(e.keyCode == 13){
            if(msgInput.length != 0){
                onSend();
            }
        }
    }
    // 스크롤을 자동으로 최하단에 내리기
    useEffect ( ()=>{
        let chatcont =  document.querySelector(".chatcont")
        console.log(chatcont.scroll)
        console.log(chatcont.scrollTop); // 현재 스크롤의 상단 위치
        console.log(chatcont.scrollHeight); //스크롤의 전체 길이
        //2.
        chatcont.scrollTop = chatcont.scrollHeight; //상단 위치를 최 하단위치로 변경
    } , [msgList])

    //날짜 확인:오늘이면 시간분,지났으면 연일월
    const checkdays = (date) =>{
        console.log(date)
        const nowday = new Date();
        const checkday = new Date(date)
        let year1 = checkday.getFullYear();
        let month1 = checkday.getMonth();
        let day1 = checkday.getDate();

        let year2 = nowday.getFullYear();
        let month2 = nowday.getMonth();
        let day2 = nowday.getDate();

        if (year1 === year2 && month1 === month2 && day1 === day2) {
            console.log("안녕")
            return (<>{checkday.getHours()}시 {checkday.getMinutes()}분</>)
        }
        else{
            console.log("자바")
            return (<>{year1}-{month1}-{day1}</>)
        }

    }

    // 드랍다운 mui
    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);
    const handleClick = (event) => {
      setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
      setAnchorEl(null);
    };

    // - msg타입에 따른 html을 반환하는 함수
    const typehtml = (m) => {
        
        if(m.type == "msg"){
            return <div className="content"> 
                    {m.msg.split("\n").map((line, index) => (
                        <React.Fragment key={index}>
                            {line}
                            <br/>
                        </React.Fragment>
                    ))} 
                    </div>
        }
        else if (m.type == "emo"){
            return <img src={"/emo/"+m.msg}/>
        }
    }


    return (<>
        <h3> 채팅방 </h3>
        <div className="chatbox">
            <div className="chatcont">

                {
                    msgList.map( (m)=>{
                        return (<>
                            {
                                logininfo.mname == m.fromMname ? 
                                    (
                                        <div className="rcont">
                                            <div className="subcont">
                                                <div className="date"> { checkdays(m.date) } </div>
                                                {typehtml(m)}
                                            </div>
                                        </div>
                                    ) :
                                    <div className="lcont">
                                        <img className="pimg"  />
                                        <div className="tocont">
                                            <div className="name">{ m.fromMname } </div>
                                            <div className="subcont">
                                                {typehtml(m)}
                                                <div className="date"> { checkdays(m.date)} </div>
                                            </div>
                                        </div>
                                    </div>
                            }       
                        </>);
                    })
                }
            </div>
            <div className="chatbottom">
                <textarea value={msgInput} onChange= { (e)=>{ setMsgInput( e.target.value) }} onKeyDown={activeEnter}> </textarea>
                <button type="button" onClick={ onSend }> 전송 </button>
                
            </div>
            <div>
                <Button
                    id="demo-positioned-button"
                    aria-controls={open ? 'demo-positioned-menu' : undefined}
                    aria-haspopup="true"
                    aria-expanded={open ? 'true' : undefined}
                    onClick={handleClick}
                >
                    이모티콘
                </Button>
                <Menu
                    id="demo-positioned-menu"
                    aria-labelledby="demo-positioned-button"
                    anchorEl={anchorEl}
                    open={open}
                    onClose={handleClose}
                    anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'left',
                    }}
                    transformOrigin={{
                    vertical: 'top',
                    horizontal: 'left',
                    }}
                >
                    <div style={{height:200, overflowY:"scroll"}}>
                    {
                        Array(43).fill().map( (v,i) =>{
                            return (<><img src={`/emo/emo${i+1}.gif`} onClick={()=>onEmoSend(`emo${i+1}.gif`)}/>
                                { (i+1) % 5 == 0 && <br/>}
                            </>)
                        })

                    }
                    </div>
                </Menu> 
                </div>
        </div>
    </>)
}