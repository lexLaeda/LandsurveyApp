import React from 'react';
import LevelReferenceItem from "./LevelReferenceItem"

export default function LevelReferenceTable(props) {

    return (
        <table className="table table-hover">
            <thead className="thead-light">
            <tr>
                <th></th>
                <th>Name</th>
                <th>Elevation</th>
                <th>Created</th>
                <th>Updated</th>
            </tr>
            </thead>
            <tbody>
            {props.levelReferenceList.map((levelReference, index) => {
                return <LevelReferenceItem levelReference={levelReference} key={levelReference.id} index={index} openDeleteModal={props.openDeleteModal}/>
            })}
            </tbody>
        </table>
    )
}