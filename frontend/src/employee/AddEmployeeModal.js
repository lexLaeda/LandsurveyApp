import React, {useContext} from 'react'
import {ModalBody, ModalHeader, ModalMain} from "../template/Modal";
import EmployeeForm from "./EmployeeForm";
import EmployeePage from "./EmployeePage";
import Context from '../Context'

function AddEmployeeModal(props) {
    const {departments, posts} = useContext(Context);
    const title = (props.employee && props.employee.id) ? "Edit employee" : "Add new employee";
    return (
        <ModalMain size="large" isActiveModal={props.isActiveModal}>
            <ModalHeader closeModal={props.closeModal} title={title}/>
            <ModalBody>
                <EmployeeForm departments={departments} posts={posts} employee={props.employee} closeModal={props.closeModal}/>
            </ModalBody>
        </ModalMain>
    );
}

export default AddEmployeeModal;