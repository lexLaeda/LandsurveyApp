
import React from 'react'

import AddBaselineForm from "./AddBaselineForm";
import {ModalBody, ModalHeader, ModalMain} from "../template/Modal";

export default function AddBaselineModal(props) {
    let title = (props.baseline && props.baseline.id) ? 'Edit baseline' : 'Add new baseline';
    return (
        <ModalMain isActiveModal={props.isActiveModal}>
            <ModalHeader title={title} closeModal={props.closeModal}/>
            <ModalBody>
                <AddBaselineForm points={props.points} closeModal={props.closeModal} baseline={props.baseline}/>
            </ModalBody>
        </ModalMain>
    )
}