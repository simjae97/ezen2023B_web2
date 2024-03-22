import React,{useState} from "react";



export default function UseState(props){
    const [newComment, setNewComment] = useState({});
    const [point , setNewPoint] = useState([{name:"심재훈",point:87},{name:"심재훈2",point: 91}]);
    
    const handleInputChange = () =>{
        // point.push(newComment);
        // console.log(point)
        // setNewPoint(point); // ***************** 주소값이 같으면 set해도 재 렌더링이 되지않음! (이 방법의 경우 똑같은 배열을 넣으므로 새로운 값이아니라고 판단해 렌더링을 하지않는다)
        let newPoint = { ...newComment };
        setNewPoint([...point,newPoint]) 
        setNewComment({ });
    }


    const handleCommentChange = (e)=>{
        let a = newComment;
        a.point = e.target.value
        setNewComment(a)
    }

    const handleNameChange = (e)=>{
        let a = newComment;
        a.name = e.target.value
        setNewComment(a)
    }
    
    
    // const handleAddPoint = () => {
    //     setNewPoint([...point,newComment])    
    //     setNewComment(" ")   
    // }
    return(<>
        <div>
            <div>
                이름: <input type="text" onChange={handleNameChange}/>
                점수: <input type="text" onChange={handleCommentChange}/>
                <button onClick={handleInputChange}> 등록 </button>
            </div>
            <div>
                {point.map((data) => <><div>
                                        이름: {data.name}
                                    </div>
                                    <div>
                                        점수: {data.point}
                                    </div>
                                    </>
                )}
            </div>
        </div>
    
    </>)
}