import { BrowserRouter, Link, Route, Routes } from "react-router-dom";
import JSX선언 from "../chapter3/1_JSX선언";
import Library from "../chapter3/Library";
import Clock from "../chapter4/Clock";

export default function Route컴포넌트(props) {
    return (
        <>
            <BrowserRouter >
            <div id="wrap" style={{display:"flex"}}>
                <고정형컴포넌트 />
            {/* <Link to="/chapter3/jsx선언">jsx</Link> */}
                <Routes>
                    <Route path="/chapter3/jsx선언" element={<JSX선언 />} />
                    <Route path="/chapter3/library" element= {<Library/>} />
                    <Route path="/chapter4/Clock" element={<Clock/>}/>
                </Routes>
            </div>
            </BrowserRouter>
        </>
    );
}


//사이드바
function 고정형컴포넌트(props) {
    return (<>
                <div>
                    <ul>
                        <li><a href="/chapter3/jsx선언">jsx선언</a></li>
                    </ul>
                    <ul>
                        <li><Link to="/chapter3/jsx선언">jsx 선언</Link></li>
                        <li><Link to="/chapter3/library">라이브러리</Link></li>
                        <li><Link to="/chapter4/Clock">시계</Link></li>
                    </ul>
                </div>
            </>)
}
