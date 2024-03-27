import axios from "axios";


export default function Axios컴포넌트(props){
    
    //1.기본함수
    function 함수명1(e,매개변수1){
        console.log("함수1");console.log(e)
    }
    //2.람다
    const 함수명2 = (e)=>{console.log("함수2");console.log(e)}

    const 함수명3 = (e,매개변수1)=>{console.log("함수3");console.log(매개변수1)}


    //1.Get
    const doGet = async ()=>{
        console.log(1)
        await axios.get("https://jsonplaceholder.typicode.com/posts").then((r)=>{console.log(r)}).catch((error)=>{console.log(error)})
        console.log(2)
        await axios.get("https://jsonplaceholder.typicode.com/posts/1").then((r)=>{console.log(r)}).catch((error)=>{console.log(error)}) //path
        console.log(3)
        await axios.get("https://jsonplaceholder.typicode.com/comments?postId=1").then((r)=>{console.log(r)}).catch((error)=>{console.log(error)}) //queryString
        console.log(4)
        await axios.get("https://jsonplaceholder.typicode.com/comments",{params : {postId : 1 }}).then((r)=>{console.log(r)}).catch((error)=>{console.log(error)}) //queryString

    }
    //2.Post
    const doPost = ()=>{
        let info = {title: "foo" , body :"bar",userId:1}
        axios.post("https://jsonplaceholder.typicode.com/posts",info).then((r)=>{console.log(r)}).catch((error)=>{console.log(error)}) 

        //2.
        const axiosForm = document.querySelector("#axiosForm")
        const axiosFormData = new FormData(axiosForm);

        axios.post("http://localhost:8080",axiosFormData).then((r)=>{console.log(r)}).catch((error)=>{console.log(error)}) 
    }
    //3.Put
    const doPut = ()=>{
        axios.put("https://jsonplaceholder.typicode.com/posts/1",{id:1,title:"foo",body:"bar",userId:1}).then((r)=>{console.log(r)}).catch((error)=>{console.log(error)}) 
    }

    //4.Delete
    const doDel = () =>{
        axios.delete("https://jsonplaceholder.typicode.com/posts/1").then((r)=>{console.log(r)}).catch((error)=>{console.log(error)}) 
    }

    return(<>
        <h3>AXIOS 테스트</h3>
        <button type="button" onClick={함수명1}> 함수1호출 </button>
        <button type="button" onClick={함수명2}> 함수2호출 </button>
        <button type="button" onClick={(e)=>함수명3(e,3)}> 함수3호출</button>
        <button type="button" onClick={doGet}>두겟</button>
        <form id="axiosForm">
            <input type="text" />
        </form>
        <button type="button" onClick={doPost}>두포</button>
        <button type="button" onClick={doPut}>두풋</button>
        <button type="button"onClick={doDel}>두딜</button>
    </>);
}