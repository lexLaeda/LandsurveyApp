import React from 'react';
import BaselineTableItem from './BaselineTableItem';

export default function BaseLineTable(props) {
    return (
        <table className="table table-hover">
            <thead className="thead-light">
            <tr>
                <th></th>
                <th>Name</th>
                <th>Point one</th>
                <th>Point two</th>
                <th>Created</th>
                <th>Updated</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            {props.baselines.map((baseline, index) => {
                return <BaselineTableItem baseline={baseline} key={baseline.id} index={index}
                                          openDeleteModal={props.openDeleteModal}
                                          openAddModal={props.openAddModal}/>
            })}
            </tbody>
        </table>
    );
}