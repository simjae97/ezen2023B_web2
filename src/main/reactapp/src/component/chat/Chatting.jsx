import { useEffect, useRef, useState } from "react";

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
            msgList.push(e.data)
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
    const onSend = ()=>{
        clientSocket.current.send(msgInput);
    }
    //채팅내용입력창
    const [msgInput, setMsgInput] = useState("");
    //채팅 목록의 내용물들
    const [msgList , setMsgList] = useState([]);

    return(<>
        <div>
            <h3>채팅방</h3>
            <div>
                {
                    msgList.map( e=>{
                        return <div> {e} </div>
                    })
                }
            </div>
            <textarea value={msgInput} onChange={(e)=>{setMsgInput(e.target.value)}}></textarea>
            <button type="button" onClick={onSend}> 전송 </button>
        </div>
    
    </>)
}