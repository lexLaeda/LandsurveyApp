import React from 'react'
import {ModalBody, ModalHeader, ModalMain} from "../template/Modal";
import AddBaselineForm from "../baseline/AddBaselineForm";
import AddLevelReferenceForm from "./AddLevelReferenceForm";


function AddLevelReferenceModal(props) {
    let title = (props.levelReference && props.levelReference.id) ? 'Edit Level Reference' : 'Add new Level Reference';
    return (
        <ModalMain isActiveModal={props.isActiveModal}>
            <ModalHeader title={title} closeModal={props.closeModal}/>
            <ModalBody>
                <AddLevelReferenceForm closeModal={props.closeModal} levelReference={props.levelReference}/>
            </ModalBody>
        </ModalMain>
    );
}

export default AddLevelReferenceModal;