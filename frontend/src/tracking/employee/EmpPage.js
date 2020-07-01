import {AddButton, Container, TableTitle} from "../../template/Control";
import EmployeeTable from "./EmployeeContent";
import React, {useContext, useState} from "react";
import Context from "../../Context";
import axios from "axios";
import {ModalBody, ModalComplete, ModalFooter} from "../../template/Modal";
import EmpForm from "./EmpForm";

export const EmpModalContext = React.createContext({});

export default function EmpPage() {
    const [isActiveAddModal, setIsActiveAddModal] = useState(false);
    const [isActiveDeleteModal, setIsActiveDeleteModal] = useState(false);
    const [employee, setEmployee] = useState({});
    const {employees, departments, posts, addElement, addElementWithAvatar, deleteElementWithAvatar} = useContext(Context);
    const [avatars, setAvatars] = useState([]);

    const openAddModal = (employee) => {
        if (employee && employee.id) {
            setEmployee(employee);
        }
        setIsActiveAddModal(true)
    };

    const closeAddModal = (employee, imagePreviewUrl, formData, isEnable) => {
        if (isEnable && formData) {
            addElementWithAvatar({element: employee, type: 'employees', root: 'employee', formData});
            if (employee.id) {
                updateAvatars(employee.id, imagePreviewUrl);
            }
        } else if (isEnable) {
            addElement({element: employee, type: 'employee', root: 'employee'})
        }
        setEmployee({});
        setIsActiveAddModal(false);
    };

    const updateAvatars = (id, imagePreviewUrl) => {
        const filtered = avatars.filter(avatar => {
            return avatar.id !== id;
        });
        filtered .push({id, imagePreviewUrl});
        setAvatars(filtered);
    };

    const openDeleteModal = (employee) => {
        setEmployee(employee);
        setIsActiveDeleteModal(true);
    };

    const closeDeleteModal = (employee, isEnable) => {
        if (isEnable) {
            deleteElementWithAvatar({element: employee, root: 'employee', type: 'employees'});
            deleteAvatar(employee.id);
        }
        setEmployee({});
        setIsActiveDeleteModal(false);
    };

    const deleteAvatar = (id) => {
        const filtered = avatars.filter(avatar => avatar.id !== id);
        setAvatars(filtered);
    };

    const tableTitle = 'Employee Table';
    const addTitle = (employee && employee.id) ? `Edit employee ${employee.num}` : 'Add new employee';
    const deleteTitle = `Delete employee ${employee.name}`;
    const deleteBody = <p>Are you really want to delete employee {employee.num}?</p>;
    const modalSize = 'large';
    return (
        <EmpModalContext.Provider value={{openAddModal, openDeleteModal, avatars}}>
            <Container>
                <TableTitle title={tableTitle}/>
                <EmployeeTable/>
                <AddButton onClick={openAddModal}/>
                <ModalComplete size={modalSize} isActive={isActiveAddModal} title={addTitle} close={closeAddModal}>
                    <EmpForm employee={employee} closeModal={closeAddModal}/>
                </ModalComplete>
                <ModalComplete title={deleteTitle} isActive={isActiveDeleteModal} close={closeDeleteModal}>
                    <ModalBody>
                        {deleteBody}
                    </ModalBody>
                    <ModalFooter element={employee} closeModal={closeDeleteModal}/>
                </ModalComplete>
            </Container>
        </EmpModalContext.Provider>
    )

}