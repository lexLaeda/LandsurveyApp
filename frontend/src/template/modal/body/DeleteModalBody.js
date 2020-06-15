import React from "react";


export default function DeleteModalBody(props){
    return(
        <div className="modal-body">
            Are you really want to delete {props.element.name} ?
        </div>
    );
}