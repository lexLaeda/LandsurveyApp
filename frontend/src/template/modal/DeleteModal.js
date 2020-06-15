import React from 'react'

import DeleteModalBody from "./body/DeleteModalBody";
import Modal from "./Modal";
import ModalBody from "./ModalBody";
import ModalFooter from "./ModalFooter";
import ModalHeader from "./ModalHeader";



export default class DeleteModal extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <Modal isActiveModal={this.props.isActiveModal}>
                <ModalHeader closeModal={this.props.closeDeleteModal}/>
                <ModalBody>
                    <div>Are you really want to delete {this.props.element.name} ?</div>
                </ModalBody>
                <ModalFooter closeModal={this.props.closeDeleteModal} element={this.props.element}/>
            </Modal>
        );
    }
}