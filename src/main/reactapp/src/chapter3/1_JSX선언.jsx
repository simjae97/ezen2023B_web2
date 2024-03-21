import React from "react";

const user = {name:"강호동" , age: 10} //js 객체

function formatName(user){
    return user.name+" "+user.age;
}

function JSX선언(props){

    const name = "유재석";

    return (
        <div>hello {name}! <br/>
        <div> {formatName(user)}</div>
        <div>{(() => "안녕")()}</div>
    </div>)
}

export default JSX선언;


