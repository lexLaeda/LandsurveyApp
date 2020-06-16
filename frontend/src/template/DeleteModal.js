import React from 'react'
import {ModalBody, ModalFooter, ModalHeader, ModalMain} from "./Modal";


export default function DeleteModal(props) {
    return (
        <ModalMain isActiveModal={props.isActiveModal}>
            <ModalHeader title={props.title} closeModal={props.closeModal}/>
            <ModalBody>
                <div>Are you really want to delete {props.element.name} ?</div>
            </ModalBody>
            <ModalFooter closeModal={props.closeModal} element={props.element}/>
        </ModalMain>
    );

}
