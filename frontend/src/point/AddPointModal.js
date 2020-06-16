import React from 'react'
import {ModalBody, ModalHeader, ModalMain} from "../template/Modal";
import AddPointForm from "./AddPointForm";


function AddPointModal(props) {

    let title = (props.point && props.point.id) ? 'Edit point' : 'Add Point';

    return (
        <ModalMain isActiveModal={props.isActiveModal}>
            <ModalHeader title={title} closeModal={props.closeModal}/>
            <ModalBody>
                <AddPointForm closeModal={props.closeModal} point={props.point}/>
            </ModalBody>
        </ModalMain>
    );
}

export default AddPointModal;