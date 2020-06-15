import React from 'react'

export default function TextInput(props){
    return(
        <div className="form-group">
            <label htmlFor="name">Name</label>
            <input value={props.name} name={props.name} onChange={props.handleChange} type="text" className="form-control" id="name"/>
        </div>
    );
}


