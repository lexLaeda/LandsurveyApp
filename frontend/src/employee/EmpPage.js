import {AddButton, Container, TableTitle} from "../template/Control";
import EmployeeTable from "./EmployeeContent";
import React, {useContext, useState} from "react";
import Context from "../Context";
import axios from "axios";
import BLForm from "../landsurveydata/baseline/BaselineForm";
import {ModalComplete} from "../template/Modal";

export const EmpModalContext = React.createContext({});

export default function EmpPage (){
    const [isActiveAddModal, setIsActiveAddModal] = useState(false);
    const [isActiveDeleteModal, setIsActiveDeleteModal] = useState(false);
    const [employee, setEmployee] = useState({});
    const {employees,departments,posts,deleteElement} = useContext(Context);
    const [avatars, setAvatars] = useState([]);

    const openAddModal = (employee) => {
        if (employee && employee.id){
            setEmployee(employee);
        }
        setIsActiveAddModal(true)
    };

    const closeAddModal = (employee,imagePreviewUrl,formData ,isEnable) =>{
        if (isEnable){

        }
    };

    const saveAvatar = (imagePreviewUrl, formData, id)=> {
        axios.post('/api/image/?id=' + id + '&dir=employee', formData).then(res => {
            if (res.status === 200){
                const filtered = avatars.filter(avatar=>avatar.id !== id);
                filtered.push({id: id, imagePreviewUrl: imagePreviewUrl});
                setAvatars(filtered);
            }
        });
    };


    const openDeleteModal = (employee) =>{
        setEmployee(employee);
        setIsActiveDeleteModal(true);
    };

    const closeDeleteModal = (employee, isEnable)=>{
        if (isEnable){
            deleteElement({element: employee, root: 'employee', name: 'employees'});
            deleteAvatar(employee.id);
            setEmployee({});
            setIsActiveDeleteModal(false);
        }
    };

    const deleteAvatar = (id) =>{
        axios.delete('/api/image/delete/?id=' + id + '&dir=employee').then(res=>{
            if (res.status === 200){
                const filtered = avatars.filter(avatar=>avatar.id !== id);
                setAvatars(filtered);
            }
        })
    };

    const tableTitle = 'Employee Table';
    const addTitle = (employee && employee.id) ? `Edit employee ${employee.num}` : 'Add new employee';
    const deleteTitle = `Delete baseline ${employee.name}`;
    const deleteBody = <p>Are you really want to delete baseline {employee.name}?</p>;

    return(
        <EmpModalContext.Provider value={{openAddModal,openDeleteModal, avatars}}>
        <Container>
            <TableTitle title={tableTitle}/>
            <EmployeeTable/>
            <AddButton onClick={openAddModal}/>
            <ModalComplete isActive={isActiveAddModal} title={addTitle} close={closeAddModal}>
                <BLForm employee={employee} closeModal={closeAddModal}/>
            </ModalComplete>
        </Container>
        </EmpModalContext.Provider>
    )

}