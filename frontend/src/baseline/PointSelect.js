import React from 'react'


export default function PointSelect(props) {
    return (
        <div className="form-group">
            <label htmlFor="exampleFormControlSelect1">{props.title}</label>
            <select className="form-control" id="exampleFormControlSelect1">
                {props.points.map(point => {
                    return <option>{point.name}</option>
                })}
            </select>
        </div>
    )
}

