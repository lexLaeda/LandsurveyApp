import React, {useContext, useState} from "react";
import Context from "../../Context";
import {Container} from "../../template/Control";
import DepartmentItem from "./DepatmentItem";
import MonthTable from "../time/tracking/MonthTable";
import DepartmentAttendance from "../time/tracking/DepartmentAttendance";


export default function DepartmentPage() {
    const{departments} = useContext(Context);
    const[department, setDepartment] = useState({});
    const[isActiveAttendance, setIsActiveAttendance] = useState(false);
    console.log(departments);

    const  openAttendance = (department) =>{
        setDepartment(department);
        setIsActiveAttendance(true);
    };
    const closeAttendance = () => {
        setDepartment({});
        setIsActiveAttendance(false);
    };
    return (
        <Container>
            <div className="row">
                {isActiveAttendance ? <DepartmentAttendance department={department} closeAttendance={closeAttendance}/> : departments.map(department => <DepartmentItem department={department} key={department.id} openAttendance={openAttendance}/>)}
            </div>
        </Container>
    )
}