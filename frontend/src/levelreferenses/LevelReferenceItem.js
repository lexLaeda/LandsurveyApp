import React, {useContext} from 'react'
import {Pencil, Trash} from "../template/Icons";
import Context from '../Context'

export default function BaselineTableItem({levelReference,index}) {

    const {openAddModal, openDeleteModal} = useContext(Context);
    return (
        <tr>
            <td>{index + 1}</td>
            <td>{levelReference.name}</td>
            <td>{levelReference.elevation}</td>
            <td>{levelReference.created}</td>
            <td>{levelReference.updated}</td>
            <td>
                <button type="button" onClick={()=>openAddModal(levelReference)} className="btn btn-light">
                    <Pencil/>
                </button>
            </td>
            <td>
                <button onClick={()=>openDeleteModal({id : levelReference.id, name : levelReference.name})} type="button" className="btn btn-light">
                    <Trash/>
                </button>
            </td>
        </tr>
    )
}