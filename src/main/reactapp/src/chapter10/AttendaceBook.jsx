

export default function AttendanceBook(props){
    const student = [
        {name:"안녕1",eno:1},
        {name:"안녕2",eno:2},
        {name:"안녕3",eno:3},
        {name:"안녕4",eno:4},
        {name:"안녕5",eno:5}


    ]

    return(<>
        <ul>
            {
                student.map((value,index) => {
                    
                    return(<><li key={index} id={value.eno} className={value.eno}>{value.name}</li></>)
                })

            }
        </ul>
    </>);
}