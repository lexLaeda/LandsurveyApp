import React from 'react'


export default function SelectInput(props){

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