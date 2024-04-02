import axios from "axios";
import { useEffect, useState } from "react";
import MediaCard from "./MediaCard";

export default function BoardAllView(props){
    const [infos, setInfos] = useState([]);
    useEffect(
        ()=>{
            axios.get("/board/get.do").then( (r) => { 
                console.log(r)
                setInfos(r.data);
            }).catch((error) => {
                console.error("Error:", error);
            });
        },[]
    )
    console.log(infos)

    return (
        <div style={{display:"flex"}}>
            {infos.map((info, index) => (
                    <li key={index}>
                        <MediaCard info={info}/>   
                      </li>
            ))}
        </div>
    );
}