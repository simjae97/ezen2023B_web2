import React,{useEffect,useState} from "react";


export default function Counter2(props){
    //1.state 상태변수
    const [count , setCount] = useState(0);
    const [check ,setCheck] = useState(0);
    //2. useEffect 이펙트함수
    //useEffect (이펙트함수,의존성 배열)
        //1.의존성 배열 생략시 mount,amount,컴포넌트가 업데이트/호출될때마다(렌더링될때마다) 호출
    // useEffect( () => {document.title = `총 ${count}번 클릭했습니다.`
    // console.log("useEffect()")})
        //2.의존성배열 []: 컴포넌트가 mount,amount일때만 호출
    // useEffect( () => {        console.log("useEffect()")
    // document.title = `총 ${count}번 클릭했습니다.`},[])
        //3.의존성 배열[state변수들] : 컴포넌트가 mount,amount,의존성 배열에 포함된 state변수가 수정될때만 실행 호출
    useEffect( () => {
        console.log("useEffect()")
        document.title = `총 ${count}번 클릭했습니다.`
    },[count])


    return (<>
            <div>
                <p> 총 {count}번 클릭했습니다.</p>
                <p> 가짜 렌더링용 {check}</p>
                <button onClick={()=> setCount(count+1)}>
                    클릭
                </button>
                <button onClick={()=> setCheck(check+1)}>
                    체크용 가짜클릭
                </button>
            </div>    
    </>);

}