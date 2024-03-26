import React, {useState} from "react";



export default function ConfirmButton(props){
    const[isConfirmed,setisConfirmed] = useState(false)
    const handleConfirm = () =>{

        setisConfirmed((prevIsConfirmed) => !prevIsConfirmed)
    }

    return(
        <>
        <button onClick={handleConfirm} disabled={isConfirmed}>
            {isConfirmed ? "확인됨":"확인하기"}
        </button>
        </>
    );
}