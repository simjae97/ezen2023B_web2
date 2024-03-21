

//컴포넌트를 만드는 방법
/*
    1.첫글자를 대문자로 하는 jsx파일 생성
    2.함수형 컴포넌트 생성
        1.컴포넌트함수 선언
            function 컴포넌트명(props){
                return(jsx형식문법)
            }    
        2. 다른곳에서 해당 파일 import시 반환할 컴포넌트 명시
        -해당 파일에 여러개 함수가 존재 할수 있으므로
컴포넌트를 호출하는 방법
    1.다른 컴포넌트에서 해당 컴포넌트를 호출하는 방법
    import 컴포넌트명 from 컴포넌트파일경로;

*/


// function 컴포넌트명(props){

//     return ( <div>안녕 자바</div>);
// }

// export default 해당파일호출시반환할컴포넌트명;

import React, { useState } from 'react';

export default function Book(props) { 
    const [bookPage, setBookPage] = useState(props.age);
    const [name, setName] = useState(props.name);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [newName, setNewName] = useState('');

    const pageUp = () => {
        setBookPage(bookPage + 1);
    };

    const pageDown = () => {
        setBookPage(bookPage - 1);
    };

    const openModal = () => {
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setIsModalOpen(false);
    };

    const handleNameChange = () => {
        setIsModalOpen(true);
    };

    const handleInputChange = (e) => {
        setNewName(e.target.value);
    };

    const handleSubmit = () => {
        setName(newName);
        setIsModalOpen(false);
    };
    
    return ( 
        <div>
            <h1>{name}.</h1>
            <h2>이 사람의 나이는 {bookPage}</h2>
            <button type="button" onClick={pageUp}>나이늘리기</button>
            <button type="button" onClick={pageDown}>나이낮추기</button>
            <button type="button" onClick={openModal}>이름바꾸기</button>
            {isModalOpen && ( // 모든 항이 true일때 우항을 리턴하는걸 이용한거같음 아마
                <div>
                    <input type="text" value={newName} onChange={handleInputChange} />
                    <button onClick={handleSubmit}>변경</button>
                    <button onClick={closeModal}>취소</button>
                </div>
            )}
        </div>
    );
}