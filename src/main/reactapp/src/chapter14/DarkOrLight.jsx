import { useCallback, useState } from "react";
import MainContent from "./MainContent";
import ThemeContext from "./ThemeContext";


export default function DarkOrLight(props){
    const [theme,setTheme] = useState("light");

    // const toggleTheme = useCallback(()=>{
    //     if(theme == "light"){
    //         setTheme("dark")
    //     }
    //     else if (theme == "dark"){
    //         setTheme("light")
    //     }
    // },[theme])


    const toggleTheme = ()=>{
        if(theme == "light"){
            setTheme("dark")
        }
        else if (theme == "dark"){
            setTheme("light")
        }
    }
    
    return (
        <ThemeContext.Provider value={{theme,toggleTheme}}>
                <MainContent />
        </ThemeContext.Provider>
    )
}