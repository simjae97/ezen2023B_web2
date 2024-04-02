//1.컴포넌트1
export default function ContextApp(props){
    return(<><Toolbar theme = "dark"/></>)
}

//2.컴포넌트2
function Toolbar(props){
    console.log(props)
    return(<><ThemeButton theme={props.theme}/></>)
}

//3.컴포넌트3
function ThemeButton(props){
    console.log(props)
    return(<><Button theme={props.theme}/></>)
}