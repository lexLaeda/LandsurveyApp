import React, {useContext} from 'react'
import Context from "../../Context";
import {Pencil, Trash} from "../../template/Icons";
import {ModalBody, ModalHeader, ModalMain} from "../../template/Modal";
import EmployeeForm from "./EmployeeForm";
import {EmpModalContext} from "./EmpPage";


function EmployeeTable() {

    const {employees} = useContext(Context);
    return (
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
                return <Item employee={employee} key={employee.id} index={index}/>
            })
            }
            </tbody>
        </table>
    );
}

function Item({employee, index}) {
    const {departments, posts} = useContext(Context);
    const {openAddModal, openDeleteModal, avatars} = useContext(EmpModalContext);
    let fullAddress = '';
    if (employee && employee.address) {
        fullAddress = concatFullAddress(employee.address.city, employee.address.street, employee.address.house, employee.address.apartment);
    }
    let post = '';
    let department = '';
    if (departments && departments.leadingComments) {
        post = getElementName(employee.postId, posts);
        department = getElementName(employee.departmentId, departments);
    }
    let imageSrc;

    let employeeAvatars = [];
    if (avatars) {
        employeeAvatars = avatars.filter(element => element.id === employee.id);
    }
    console.log(employee);
    if (employeeAvatars && employeeAvatars.length === 1) {
        imageSrc = employeeAvatars[0].imagePreviewUrl;
    } else {
        imageSrc = (employee.id) ? `/api/image/employee/?id=${employee.id}` : `/api/image/employee/?id=-1`;
    }


    return (
        <tr>
            <td>{index + 1}</td>
            <td><img width="200" src={imageSrc}/></td>
            <td>{employee.num}</td>
            <td>{employee.firstName}</td>
            <td>{employee.lastName}</td>
            <td>{employee.birthday}</td>
            <td>{employee.age}</td>
            <td>{post}</td>
            <td>{department}</td>
            <td>{fullAddress}</td>
            <td>
                <button onClick={() => openAddModal(employee)} type="button" className="btn btn-light">
                    <Pencil/>
                </button>
            </td>
            <td>
                <button onClick={() => openDeleteModal({id: employee.id, name: employee.firstName})} type="button"
                        className="btn btn-light">
                    <Trash/>
                </button>
            </td>
        </tr>
    )
}

export function AddEmployeeModal(props) {
    const {departments, posts} = useContext(Context);
    const title = (props.employee && props.employee.id) ? "Edit employee" : "Add new employee";
    return (
        <ModalMain size="large" isActiveModal={props.isActiveModal}>
            <ModalHeader closeModal={props.closeModal} title={title}/>
            <ModalBody>
                <EmployeeForm departments={departments} posts={posts} employee={props.employee}
                              closeModal={props.closeModal}/>
            </ModalBody>
        </ModalMain>
    );
}

function concatFullAddress(city, street, house, flat) {
    return flat + ' , ' + street + ' ' + city;
}

function getElementName(id, elements) {
    const element = elements.find((element) =>
        element.id == id);

    if (element && element.name) {
        return element.name;
    } else {
        return '...';
    }
}

export default EmployeeTable;