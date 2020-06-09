import React from 'react'
import PointItem from './PointItem'

export default function PointTable(props) {
    return (
        <table className="table table-hover">
            <thead className="thead-dark">
                <tr>
                    <th>№</th>
                    <th>Name</th>
                    <th>X</th>
                    <th>Y</th>
                    <th>H</th>
                    <th>Created</th>
                    <th>Updated</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            {props.points.map((point,index) => {
                return <PointItem point={point} key={point.id} index={index}/>
            })}
            </tbody>
        </table>
    )
}