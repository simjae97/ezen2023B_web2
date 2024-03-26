import { useState } from "react"


export default function NameForm(props){

    const 제출 = ()=>{
        let nameInput = document.querySelector("#nameInput").value
        console.log(nameInput)
    }

    const [value , setValue] = useState("");
    const handleChange = (e)=> {
        setValue(e.target.value)
    }
    const handlerSubmmit = (e) =>{
            console.log(value)
            console.log(value2)
            console.log(value3)
            alert("dd")
            e.preventDefault(); //브라우저들의 이벤트들을 제거
    }
    //3.
    const [value2 , setValue2] = useState("")
    const handleChange2 = (e) => {
        setValue2(e.target.value)
        e.preventDefault();
    }
    //4.
    const [value3 , setValue3] = useState("grape")

    const handleChange3 = (e)=>
    {
        setValue3(e.target.value)
    }
    return(<>
        <form>
            이름 : <input id="nameInput"/>
            <button type="button" onClick={제출}>제출1</button>
        </form>
        <form onSubmit={handlerSubmmit}>
            이름 : <input value={value} onChange={handleChange}/>
            <button type="submit">제출1</button>
        </form>
        <textarea value={value2} onChange={handleChange2}>

        </textarea>
        <select onChange={handleChange3}>
            <option value="apple">사과</option>
            <option value="banana">바나나</option>
            <option selected value="grape">포도</option>
            <option value="watermelon">수박</option>
        </select>
    </>)

}