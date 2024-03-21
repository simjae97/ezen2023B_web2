import React,{useState} from "react";
import Comment from "./Comment";


export default function CommentList(props) {
    const [newComment, setNewComment] = useState('');
    const [name, setName] = useState('');
    const [commentArray, setCommentArray] = useState([
        { id: 1, name: "강호동", comment: "첫 번째 댓글" },
        { id: 2, name: "유재석", comment: "두 번째 댓글" },
        { id: 3, name: "하하", comment: "세 번째 댓글" }
    ]); 
    // 객체를 포함하는 배열
    // 새로운 댓글 입력 시 상태 업데이트
    const handleInputChange = (event) => {
        setNewComment(event.target.value);
    };

    const handleNameChange = (event) => {
        setName(event.target.value);
    };

    // 댓글 추가 이벤트 핸들러
    const handleAddComment = () => {
        const newId = commentArray.length + 1; // 새로운 댓글의 ID 생성
        const newCommentObject = { id: newId, name: name, comment: newComment }; // 새로운 댓글 객체 생성
        setCommentArray([...commentArray, newCommentObject]); // 새로운 댓글 추가
        console.log("New comment:", newComment);
        setNewComment('');
        setName(' ');
    };

    const renderedComments = commentArray.map((data) => ( // 고유한 key prop 추가
        <Comment name={data.name} comment={data.comment} />
    ));

    

    return (
        <div>
            {/* 사용자 이름 입력 창 */}
            <input type="text" value={name} onChange={handleNameChange} placeholder="이름을 입력하세요" />
            {/* 댓글 입력 창 */}
            <input type="text" value={newComment} onChange={handleInputChange} placeholder="댓글을 입력하세요" />
            <button type="button" onClick={handleAddComment}>댓글 추가</button>
            <ul>
                {renderedComments}
            </ul>
        </div>
    );
}

// export default function CommentList(props){
//     let response = [ {name : '유재석' , content : '안녕하세요1'} ,
//     {name : '강호동' , content : '안녕하세요2'} ,
//     {name : '신동엽' , content : '안녕하세요3'} ];
//     const result = response.map((data) => {
//         return <Comment name ={data.name} comment={data.content}></Comment>
//     })
//     return(
//         <div>
//             {result}
//         </div>

//     );

// }

