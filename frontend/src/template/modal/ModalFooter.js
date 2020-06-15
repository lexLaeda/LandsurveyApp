import React from "react";


export default function ModalFooter(props) {
    return (
        <div className="modal-footer">
            <button onClick={() => props.closeModal(props.element,false)} type="button"
                    className="btn btn-secondary" data-dismiss="modal">Cancel</button>
            <button onClick={() => props.closeModal(props.element,true)} type="button" className="btn btn-primary">Confirm delete</button>
        </div>
    )
}
