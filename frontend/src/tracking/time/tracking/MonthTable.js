import React from "react";
import EmployeeAttendance from "./EmployeeAttendance";


function MonthTable({employeeHistories, month, year, yearHolidays}) {
    const days = new Date(year,month,0);
    const dates = [];
    for (let i = 1; i <= days; i++){
        dates.push(i);
    }
    return(
        <table>
            <thead>
                <tr>
                    <th>№</th>
                    <th>Num</th>
                    <th>FullName</th>
                    {dates.map(date => <th>date</th>)}
                </tr>
            </thead>
            <tbody>
                {employeeHistories.map((employeeHistory, index) =>{
                    const attendances = employeeHistory.attendances.map(
                        attendance => attendance.date.getMonth() === month
                    );
                    return <EmployeeAttendance employee={employeeHistory.employee} index={index} attendances={attendances} days={dates}/>;
                })}
            </tbody>
        </table>
    );
}

export default MonthTable;