import React from 'react'
import {ModalBody, ModalFooter, ModalHeader, ModalMain} from "./Modal";


export default class DeleteModal extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <ModalMain isActiveModal={this.props.isActiveModal}>
                <ModalHeader title="Delete baseline" closeModal={this.props.closeModal}/>
                <ModalBody>
                    <div>Are you really want to delete {this.props.element.name} ?</div>
                </ModalBody>
                <ModalFooter closeModal={this.props.closeModal} element={this.props.element}/>
            </ModalMain>
        );
    }
}
