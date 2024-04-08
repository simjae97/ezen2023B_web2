import axios from "axios";
import { useRef } from "react";

export default function BoardWrite(){

    //1. 재렌더링 고정 참조 변수 
    const boardWriteFormRef = useRef();
    console.log(boardWriteFormRef)

    const handleSubmit = (e) => {
        
        axios.post("/board/post.do", boardWriteFormRef.current) // axios contentType : mulitpart
            .then((response) => {
                if(response.data){
                    alert("글 작성 성공");
                    // 성공 시 처리
                    window.location.href="/board"
                }
                else{
                    alert("작성실패")
                }
            })
            .catch((error) => {
                console.error("Error:", error);
                // 실패 시 처리
            });
    };
    return(<>
        <form ref={boardWriteFormRef}>
            <input type="text" name="bcontent"/>
            <input type="file" name="uploadList" multiple accept="image/*"/>
            <button type="button" onClick={handleSubmit}>전송</button>
        </form>
    </>);

}