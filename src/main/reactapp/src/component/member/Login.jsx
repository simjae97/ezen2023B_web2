import axios from "axios"
import { useState } from "react"

export default function Login(props){
    const [info,setinfo] = useState({memail:"" ,mpassword:""})

    //전송함수

    const onLogin = (e) => {
        axios.post("/member/login/post.do",info)
                .then( (r) => {
                    if(r.data){
                        alert("로그인 성공")
                        window.location.href="/";
                    }
                    else{
                        alert("로그인실패")
                    }
                })
                .catch( (e) => {console.log(e)})
        console.log(info)
    }
    return(<>
        <form>
            이메일 : <input value={info.memail} type="text" onChange={ (e)=> {setinfo({...info, memail: e.target.value})}}/>
            비밀번호 : <input value={info.mpassword} type="text" onChange={ (e)=>{ setinfo({...info, mpassword: e.target.value})}}/>
            <button type="button" onClick={onLogin}>로그인</button>
        </form>   
    </>)

}