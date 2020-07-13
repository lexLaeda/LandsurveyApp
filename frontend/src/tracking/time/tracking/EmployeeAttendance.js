import React from "react";


function EmployeeAttendance({index,employee,attendances, days}) {
    return(
        <tr>
            <td>
                {index + 1}
            </td>
            <td>
                {employee.num}
            </td>
            <td>
                {employee.firstName + " " + employee.lastName}
            </td>
                {days.map(day => {
                    const filter = attendances.filter(attendance =>{
                        return attendance.date == day;
                    });
                    if (filter.length === 1){
                        return <td>filter[0].value</td>
                    }
                })}
        </tr>
    )
}
export default EmployeeAttendance;