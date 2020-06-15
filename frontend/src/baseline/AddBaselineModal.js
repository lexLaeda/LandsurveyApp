
import React from 'react'

import AddBaselineForm from "./AddBaselineForm";
import {ModalBody, ModalHeader, ModalMain} from "../template/modal/Modal";

export default function AddBaselineModal(props) {
    let title = (props.baseline) ? 'Edit baseline' : 'Add new baseline';
    return (
        <ModalMain isActiveModal={props.isActiveModal}>
            <ModalHeader title={title} closeModal={props.closeModal}/>
            <ModalBody>
                <AddBaselineForm points={props.points} closeModal={props.closeModal} baseline={props.baseline}/>
            </ModalBody>
        </ModalMain>
    )
}