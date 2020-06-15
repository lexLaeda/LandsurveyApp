import Modal from "../template/modal/Modal";
import React from 'react'
import ModalHeader from "../template/modal/ModalHeader";
import ModalBody from "../template/modal/ModalBody";
import AddBaselineForm from "./AddBaselineForm";

export default function AddBaselineModal(props) {
    return (
        <Modal isActiveModal={props.isActiveModal}>
            <ModalHeader closeModal={props.closeModal}/>
            <ModalBody>
                <AddBaselineForm points={props.points} closeModal={props.closeModal} baseline={props.baseline}/>
            </ModalBody>
        </Modal>
    )
}