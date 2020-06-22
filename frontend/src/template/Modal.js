import React from 'react'

export function ModalBody(props) {
    return (
        <div className="modal-body">
            {props.children}
        </div>
    );
}
export function ModalFooter(props) {
    return (
        <div className="modal-footer">
            <button onClick={() => props.closeModal(props.element,false)} type="button"
                    className="btn btn-secondary" data-dismiss="modal">Cancel</button>
            <button onClick={() => props.closeModal(props.element,true)} type="button" className="btn btn-primary">Confirm delete</button>
        </div>
    )
}
export function ModalHeader(props) {
    return (
        <div className="modal-header">
            <h5 className="modal-title" id="exampleModalLabel">{props.title}</h5>
            <button onClick={() => props.closeModal(props.element,false)} type="button"
                    className="close" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    );
}
export function ModalMain(props){
    const largeModal = 'modal-dialog modal-xl';
    const standartModal = 'modal-dialog';
    let modalSize = '';
    if (props.size === 'large'){
        modalSize = largeModal;
    } else {
        modalSize =standartModal
    }
    return(
        <React.Fragment>
            {props.isActiveModal && (
                <div>
                    <div className="modal-backdrop fade show"></div>
                    <div className="modal show" id="deleteBaseline" tabIndex="-1" role="dialog"
                         style={{display: 'block'}}>
                        <div className={modalSize} role="document">
                            <div className="modal-content">{props.children}</div>
                        </div>
                    </div>
                </div>)}
        </React.Fragment>
    );
}
export function DeleteModal(props) {
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
