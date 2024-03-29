import axios from "axios"
import { useEffect, useState } from "react"

export default function SignUp(props){
    const [info,setinfo] = useState({memail:"" ,mpassword:"",mname:""})

    //전송함수
    const [idcheck,setidcheck] = useState(true)

    const onSignup = (e) => {
        /*

        contentType : application/json
        */
       if(!idcheck){
        alert("유효성검사 실패")
        return;
       }
        axios.post("/member/signup/post.do",info)
                .then(
                     (r) => {
                        console.log(r); 
                        console.log(r.data)
                        if(r.data){
                            alert("회원가입성공")
                            window.location.href="/member/login"
                        }
                        else{
                            alert("회원가입실패")
                        }
                    }    
                )
                .catch( e=>{console.log(e)})
        console.log(info)
    }

    const idcheckHandler = (e)=> {setinfo({...info, memail: e.target.value})}

    useEffect(
        ()=>{
            axios.post("/member/idcheck/post.do",info).then((r) => {setidcheck(r.data)}).catch((e)=>{console.log(e)})
        }
    ,[info.memail])
    return(<>
        <form>
            이메일 : <input value={info.memail} type="text" onChange={idcheckHandler}/>{idcheck?" ":<span>아이디 중복</span>}<br/>
            비밀번호 : <input value={info.mpassword} type="text" onChange={ (e)=>{ setinfo({...info, mpassword: e.target.value})}}/><br/>
            이름 : <input value={info.mname} type="text" onChange={ (e)=>{ setinfo({...info, mname: e.target.value})}}/>
            <button type="button" onClick={onSignup}>회원가입</button>
        </form>   
    </>)

}