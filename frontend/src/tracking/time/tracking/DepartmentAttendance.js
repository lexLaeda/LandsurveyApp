import {Container, ContainerFluid, Tabs} from "../../../template/Control";
import React, {useContext, useState} from "react";
import MonthTable from "./MonthTable";
import Context from "../../../Context";

function DepartmentAttendance({department, closeAttendance}) {
    const months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
    const years = [];
    for (let i = 2020; i < 2040; i++){
        years.push(i);
    }
    const [activeMonth, setActiveMonth] = useState("January");
    const [activeYear, setActiveYear] = useState(2020);
    const {holidays} = useContext(Context);
    const handleChange = (event) =>{
        setActiveYear(event.target.value);
    };


    return (
        <ContainerFluid>
            <h3 className="panel-title text-center mt-5 mb-5">{department.name}</h3>
            <h3 className="panel-title text-center mb-5">Employee attendance</h3>
            <div>
                <button onClick={() => closeAttendance()}>Close</button>
            </div>
            <div className="row align-items-end">
                <div className="col mt-5">
                    <Tabs elements={months} activeId={activeMonth} setActiveId={setActiveMonth}/>
                </div>
                <div className="col-md-2 col-lg-2 mt-5">
                    <select className="form-control form-control-sm" onChange={handleChange}>
                        {years.map(year=> <option key={year} value={year}>{year}</option>)}
                    </select>
                </div>

            </div>
            <div>
                <MonthTable employeeHistories={[]} month={months.indexOf(activeMonth)} year={activeYear} yearHolidays={holidays}/>
            </div>
        </ContainerFluid>
    )
}

export default DepartmentAttendance;