import React from 'react'
import {ModalBody, ModalHeader, ModalMain} from "../template/Modal";
import EmployeeForm from "./EmployeeForm";
import AddBaselineForm from "../baseline/AddBaselineForm";



function AddEmployeeModal(props) {
    return(
      <ModalMain isActiveModal={props.isActiveModal}>
          <ModalHeader closeModal={props.closeModal} title={props.title}/>
          <ModalBody>
              <EmployeeForm  closeModal={props.closeModal} employee={props.employee}/>
          </ModalBody>
      </ModalMain>
    );
}
export default AddEmployeeModal;