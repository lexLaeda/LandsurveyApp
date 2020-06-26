import React, {useContext} from 'react';

import Context from "../../Context";
import {Pencil, Trash} from "../../template/Icons";
import {PointModalContext} from "./PointPage";

export function PointTable() {
    const {points} = useContext(Context);
    return (
        <table className="table table-hover">
            <thead className="thead-light">
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
            {points.map((point, index) => {
                return <Item point={point} key={point.id} index={index}/>
            })}
            </tbody>
        </table>
    )
}

function Item({point, index}) {

    const {openAddModal, openDeleteModal} = useContext(PointModalContext);
    return (<tr>
            <td>{index + 1}</td>
            <td>{point.name}</td>
            <td>{point.x}</td>
            <td>{point.y}</td>
            <td>{point.h}</td>
            <td>{point.created}</td>
            <td>{point.updated}</td>
            <td>
                <button type="button" onClick={() => openAddModal(point)} className="btn btn-light">
                    <Pencil/>
                </button>
            </td>
            <td>
                <button type="button" onClick={() => {
                    console.log(point);
                    openDeleteModal({
                        id: point.id,
                        name: point.name
                    })
                }} className="btn btn-light">
                    <Trash/>
                </button>
            </td>
        </tr>
    )
}

