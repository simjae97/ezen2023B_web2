import axios from "axios";
import { useEffect, useState } from "react";
import MediaCard from "./MediaCard";
import { Pagination } from "@mui/material";

export default function BoardAllView(props){

    const [pageDto, setPageDto] = useState({
        page:1, count:0 , data : []
    });

    const [dodel , setDelete] = useState(true); //props로 스테이트 보내려다가 2페이지 다 지워지면 못생기게 나오길래 그냥 href

    const deleteChange = (e) =>{
        console.log(e)
        // axios.delete("/board/delete.do",e.target.value)
        axios.delete("/board/delete.do",{params:{"bno":e}}).then((re)=>{if(re.data){
            alert("삭제성공")
            // window.location.href="/board"
            setDelete(!dodel)
            }
            else{
                alert("삭제실패,본인이 작성한 글이 아니거나 서버의 오류가 발생했습니다.")
            }
        }).catch((e)=>{console.log(e)})
    }

    useEffect(
        ()=>{
            const info = {page:pageDto.page , view : 4 }

            axios.get("/board/get.do",{params:info}).then( (r) => { 
                console.log(r)
                setPageDto(r.data);
            }).catch((error) => {
                console.error("Error:", error);
            });
        },[pageDto.page,dodel]
    )

 
    console.log(pageDto)

    const handleChange = (event, value) => {
    
        setPageDto({...pageDto, page:value})
    };
    
    

    return (<>
            <div style={{display:"flex"}}>
            {pageDto.data.map((info, index) => (
                    <li key={index}>
                        <MediaCard info={info} deleteChange={deleteChange}/>   
                      </li>
            ))}
        </div>
        <Pagination count={pageDto.count} page={pageDto.page} onChange={handleChange} />
        </>
    );
}

//page: the current page . 현재 페이지
//count : the total number of pages :전체 페이지
// onChange : Callback fired when the page is changed.:콜백함수