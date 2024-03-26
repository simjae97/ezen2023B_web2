import axios from "axios"
import { useState } from "react"

export default function SignUp(props){
    const [info,setinfo] = useState({memail:"" ,mpassword:"",mname:""})

    //전송함수

    const onSignup = (e) => {
        /*

        contentType : application/json
        */
        axios.post("http://localhost:80/member/signup/post.do",info).then( r => {console.log(r); console.log(r.data)}) 
        console.log(info)
    }
    return(<>
        <form>
            이메일 : <input value={info.memail} type="text" onChange={ (e)=> {setinfo({...info, memail: e.target.value})}}/>
            비밀번호 : <input value={info.mpassword} type="text" onChange={ (e)=>{ setinfo({...info, mpassword: e.target.value})}}/>
            이름 : <input value={info.mname} type="text" onChange={ (e)=>{ setinfo({...info, mname: e.target.value})}}/>
            <button type="button" onClick={onSignup}>회원가입</button>
        </form>   
    </>)

}