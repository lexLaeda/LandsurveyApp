import {Container, Tabs} from "../../../template/Control";
import React from "react";
import MonthTable from "./MonthTable";

function DepartmentAttendance({department,closeAttendance}) {
    const months = ['January','February','March','April','May','June','July','August','September','October','November','December',];

    return(
        <Container>
            <h>{department.name}</h>
            <button onClick={()=>closeAttendance()}>Close</button>
            <div>
                <Tabs elements={months}/>
            </div>
        </Container>
    )
}

export default DepartmentAttendance;