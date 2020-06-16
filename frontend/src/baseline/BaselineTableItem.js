import React, {useContext} from 'react'
import {Pencil, Trash} from "../template/Icons";
import Context from '../Context'

export default function BaselineTableItem(props) {
    const {openAddModal, openDeleteModal} = useContext(Context);
    return (
        <tr>
            <td>{props.index + 1}</td>
            <td>{props.baseline.name}</td>
            <td>{props.baseline.pointStart.name}</td>
            <td>{props.baseline.pointEnd.name}</td>
            <td>{props.baseline.created}</td>
            <td>{props.baseline.updated}</td>
            <td>
                <button onClick={() => openAddModal(props.baseline)} type="button" className="btn btn-light">
                    <Pencil/>
                </button>
            </td>
            <td>
                <button onClick={() => openDeleteModal({
                    id: props.baseline.id,
                    name: props.baseline.name})}
                        type="button" className="btn btn-light">
                    <Trash/>
                </button>
            </td>
        </tr>
    )
}