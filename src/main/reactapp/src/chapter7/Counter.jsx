import React,{useState} from "react";

let count = 0;
export default function Counter(props){
    const [counter,setCounter] = useState(0);
    const [inputValue1, setInputValue1] = useState("");
    
    const handlerInputValue1 = (event) => {
        console.log(event) //이벤트 발생한 결과물 객체
        console.log(event.target) //이벤트를 발생시킨 주체(마크업) == this
        setInputValue1(event.target.value); //걔의 밸류
    }

    const a = useState(0);
    console.log(a);
    const handlerInputButton = ()=>{
        setCounter(counter+1)
    }

    // 3. 상태 관리 변수
        //useState("초기값");
            //[0] : state의 초기값 또는 값
            //[1]:  state의 set함수
    return(
    <div>
        <p>총 {count}번 클릭했습니다</p>
        <button type="button" onClick={()=> count++ }>
            클릭
        </button>
        <p>총 {counter}번 클릭했습니다</p>
            <button type="button" onClick={handlerInputButton}>
                클릭
            </button>
        <div>
            <input type="text"/> <br/>
            <input type="text" value={inputValue1} / > <br/>
            <input type="text" value={inputValue1} onChange= {handlerInputValue1} / > <br/>

        </div>
    </div>
    );

}