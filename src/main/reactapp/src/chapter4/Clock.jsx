
import React, { useState } from 'react';

function Clock(props){
    const [locale, setLocale] = useState('ko-KR');

    const handleLocaleChange = (newLocale) => {
        setLocale(newLocale); // 선택된 나라로 상태 업데이트
    };

    const currentTime = new Date().toLocaleTimeString(locale);


    return(
        <div>
            <h1>안녕 리액트! </h1>
            <h2>현재 시간 : {currentTime}</h2>
            <button type="button" onClick={() => handleLocaleChange('en-US')}> 미국식 </button>
            <button type="button" onClick={() => handleLocaleChange('ko-KR')}> 한국식 </button>
            <button type="button" onClick={() => handleLocaleChange('zh-CN')}> 중국식  </button>
        </div>
    );
}


export default Clock;