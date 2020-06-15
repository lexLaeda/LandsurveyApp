import React from 'react'
import {Icons, Pencil, Trash} from "../template/Icons";

export default function BaselineTableItem(props) {
    return (
        <tr>
            <td>{props.index + 1}</td>
            <td>{props.baseline.name}</td>
            <td>{props.baseline.pointStart.name}</td>
            <td>{props.baseline.pointEnd.name}</td>
            <td>{props.baseline.created}</td>
            <td>{props.baseline.updated}</td>
            <td>
                <button onClick={() => props.openAddModal(props.baseline)} type="button" className="btn btn-light">
                    <Pencil/>
                </button>
            </td>
            <td>
                <button onClick={() => props.openDeleteModal({
                    id: props.baseline.id,
                    name: props.baseline.name})}
                        type="button" className="btn btn-light">
                    <Trash/>
                </button>
            </td>
        </tr>
    )
}