import React from "react";
import EmployeeAttendance from "./EmployeeAttendance";


function MonthTable({employeeHistories, month, year, yearHolidays}) {
    const days = new Date(year,month+1,0);
    const dates = [];
    console.log(days.getDate());
    for (let i = 1; i <= days.getDate(); i++){
        const date = new Date(year,month,i);
        dates.push(date);
    }

    const holidaysFromApi = yearHolidays.map(yearHoliday => new Date(yearHoliday.date));
    const containsDate = (date) => holidaysFromApi.filter(holiday => {
        return holiday.getFullYear() === date.getFullYear() && holiday.getMonth() === date.getMonth() && holiday.getDate() === date.getDate()}).length === 1;
    
    return(
        <table className="table mt-4">
            <thead>
                <tr>
                    <th>№</th>
                    <th>Num</th>
                    <th>FullName</th>
                    {dates.map(date => {
                        const dayWeek = date.getDay();
                        let background;
                        if (dayWeek === 6 || dayWeek === 0 || containsDate(date)){
                            background = "table-background";
                        }
                        return <th className={background} key={date.getDate()}>{date.getDate()}</th>;
                    })}
                </tr>
            </thead>
            <tbody>
                {employeeHistories.map((employeeHistory, index) =>{
                    const attendances = employeeHistory.attendances.map(
                        attendance => attendance.date.getMonth() === month
                    );
                    return <EmployeeAttendance key={employeeHistory.id} employee={employeeHistory.employee} index={index} attendances={attendances}/>;
                })}
            </tbody>
        </table>
    );
}

export default MonthTable;