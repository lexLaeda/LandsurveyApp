import React from 'react'

export function FormModalFooter(props){
    return(
        <div className="modal-footer">
            <button onClick={() => props.closeModal(props.element, false)} type="button"
                    className="btn btn-secondary">Close</button>
            <button type="submit" className="btn btn-primary">Save changes</button>
        </div>
    );
}

export  function SelectInput(props){

    return(
        <div className="form-group">
            <label htmlFor="firstPointSelect">Select start point</label>
            <select value={props.value} name={props.name} onChange={props.handleChange} className="form-control" id="firstPointSelect">
                <option>...</option>
                {props.elements.map((element) => <option value={element.id}>{element.name}</option>)}
            </select>
        </div>
    );
}

export  function TextInput(props){
    return(
        <div className="form-group">
            <label htmlFor="name">Name</label>
            <input value={props.value} name={props.name} onChange={props.handleChange} type="text" className="form-control" id="name"/>
        </div>
    );
}
export function AddButton(props){

    return(
        <div className="panel-body">
            <button type="button" className="btn btn-dark" data-toggle="modal"
                    onClick={() => props.openAddModal()}>Add new
            </button>
        </div>
    );
}