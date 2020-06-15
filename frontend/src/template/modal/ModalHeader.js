import React from "react";


export default function ModalHeader(props) {
    return (
        <div className="modal-header">
            <h5 className="modal-title" id="exampleModalLabel">Delete data</h5>
            <button onClick={() => props.closeModal(props.element,false)} type="button"
                    className="close" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    );
}