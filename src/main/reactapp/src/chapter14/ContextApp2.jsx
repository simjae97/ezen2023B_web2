import React from "react"

//0.컨텍스트를 이용한 컴포넌트 트리로 곧바로 전달하기 위한 컨텍스트 저장소 탄생
const ThemeContext = React.createContext("light");

//1.컴포넌트1
// Provider를 사용하여 하위 컴포넌트들에게 현재 데이터를 전달
// 모든 하위 컴포넌트들은 컴포넌트 트리 하단에 얼마나 깊이 있는지와 관계 없이 데이터를 호출할 수 있다.
export default function ContextApp(props){
    
    return(<><ThemeContext.Provider value="dark">
        <Toolbar/>
    </ThemeContext.Provider></>)
}

//2.컴포넌트2
//중간에 위치한 컴포넌트는 테마 데이터를 하위 컴포넌트로 전달할 필요가 없다.
function Toolbar(props){
    console.log(props)
    return(<><ThemeButton /></>)
}

//3.컴포넌트3
// consumer를 이용하여 리액트는 가장 가까운 상위테마 provider를 찾아서 해당되는 값을 호출할수있다
function ThemeButton(props){
    console.log(props)
    return(<>
        <ThemeContext.Consumer>
            <Button theme={props.theme}/>
        </ThemeContext.Consumer>
    </>)
}