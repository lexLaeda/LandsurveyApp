import React from 'react'

export function FormModalFooter(props) {
    return (
        <div className="modal-footer">
            <button onClick={() => props.closeModal(false)} type="button"
                    className="btn btn-secondary">Close
            </button>
            <button type="submit" className="btn btn-primary">Save changes</button>
        </div>
    );
}

export function SelectInput(props) {
    return (
        <div className="form-group">
            <label htmlFor="firstPointSelect">{props.label}</label>
            <select value={props.value} name={props.name} onChange={props.handleChange} className="form-control"
                    id="firstPointSelect" required>
                <option>...</option>
                {props.elements.map((element) => <option value={element.id} key={element.id}>{element.name}</option>)}
            </select>
        </div>
    );
}

export function SelectInputText(props) {

    return (
        <div className="form-group">
            <label htmlFor="firstPointSelect">{props.label}</label>
            <select value={props.value} name={props.name} onChange={props.handleChange} className="form-control"
                    id="firstPointSelect" required>
                <option>...</option>
                {props.elements.map((element, index) => <option key={index} value={element}>{element}</option>)}
            </select>
        </div>
    );
}

export function TextInput(props) {
    return (
        <div className="form-group">
            <label htmlFor="name">{props.label}</label>
            <input value={props.value} name={props.name} onChange={props.handleChange} type={props.type}
                   className="form-control" id="name" required/>
        </div>
    );
}

export function AddButton({onClick}) {
    return (
        <div className="panel-body">
            <button type="button" className="btn btn-dark" data-toggle="modal"
                    onClick={() => onClick()}>Add new
            </button>
        </div>
    );
}

export function CheckBox({label, onChange}) {
    return (
        <div className="form-group form-check" data-tip="hello world">
            <input type="checkbox" className="form-check-input" id="exampleCheck1" onChange={onChange}/>
            <label className="form-check-label" htmlFor="exampleCheck1">{label}</label>
        </div>
    );
}
export function Container(props){
    return (
        <div className="container">
            <div className="panel panel-default">
                <div className="panel-heading">
                    {props.children}
                </div>
            </div>
        </div>
    );
}
export function TableTitle(props) {
    return(
        <h3 className="panel-title text-center mt-5 mb-5">{props.title}</h3>
    )
}