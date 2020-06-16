import React, {useContext} from 'react'
import {Pencil, Trash} from "../template/Icons";
import Context from '../Context'

export default function PointItem({point, index}) {

    const{openAddModal, openDeleteModal} = useContext(Context);
    return (<tr>
            <td>{index + 1}</td>
            <td>{point.name}</td>
            <td>{point.x}</td>
            <td>{point.y}</td>
            <td>{point.h}</td>
            <td>{point.created}</td>
            <td>{point.updated}</td>
            <td>
                <button type="button" onClick={()=>openAddModal(point)} className="btn btn-light">
                    <Pencil/>
                </button>
            </td>
            <td>
                <button type="button" onClick={()=>{
                    console.log(point);
                    openDeleteModal({
                    id: point.id,
                    name: point.name})}} className="btn btn-light">
                    <Trash/>
                </button>
            </td>
        </tr>
    )
}