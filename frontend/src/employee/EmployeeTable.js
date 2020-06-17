import React, {useContext} from 'react'
import Context from "../Context";
import {Pencil, Trash} from "../template/Icons";


function EmployeeTable({employees}) {

    return(
        <table className="table table-hover">
            <thead className="thead-light">
            <tr>
                <th></th>
                <th>Photo</th>
                <th>Num</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Birthday</th>
                <th>Age</th>
                <th>Post</th>
                <th>Department</th>
                <th>Address</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            {employees.map((employee, index) => {
                return <Item employee={employee} key={employee.id} index={index}/>})
            }
            </tbody>
        </table>
    );
}
function Item({employee,index}) {
    const {openAddModal, openDeleteModal, departments, posts} = useContext(Context);
    let fullAddress = '';
    if (employee && employee.address){
        fullAddress = concatFullAddress(employee.address.city, employee.address.street, employee.address.house, employee.address.apartment);
    }
    let post = '';
    let department = '';
    if (departments && posts){
        console.log(employee.post)
            post = getElementName(employee.postId, posts);
        department = getElementName(employee.departmentId, departments);
    }

    return (
        <tr>
            <td>{index}</td>
            <td>Here fill be photo</td>
            <td>{employee.num}</td>
            <td>{employee.firstName}</td>
            <td>{employee.lastName}</td>
            <td>{employee.birthday}</td>
            <td>{employee.age}</td>
            <td>{post}</td>
            <td>{department}</td>
            <td>{fullAddress}</td>
            <td>
                <button onClick={()=>openAddModal(employee)} type="button" className="btn btn-light">
                    <Pencil/>
                </button>
            </td>
            <td>
                <button onClick={()=>openDeleteModal({id : employee.id, name : employee.firstName})} type="button" className="btn btn-light">
                    <Trash/>
                </button>
            </td>
        </tr>
    )
}
function concatFullAddress(city, street, house, flat){
    return flat + ' , ' + street + ' ' + city;
}

function getElementName(id, elements){
    return elements.find((element)=>
    element.id == id).name;
}
export default EmployeeTable;