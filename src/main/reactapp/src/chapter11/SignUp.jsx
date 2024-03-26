import { useState } from "react"

export default function SignUp(props){
    const [name , setName] = useState("")
    const [sex , setSex] = useState("남자")
    const submitid = ()=>{
        console.log(name)
        console.log(sex)
        setName("")
        setSex("남자")
    }


    return(<>
    <div>이름 : <input type="text" value={name} onChange={ (e) => {setName(e.target.value)} }/> </div>
    <select value={sex} onChange={(e)=>{setSex(e.target.value)}}>
        <option value="여자">여자</option>    
        <option value="남자" >남자</option>    
    </select>        
    <button type="button" onClick={submitid}>제출 </button>
    </>)

}