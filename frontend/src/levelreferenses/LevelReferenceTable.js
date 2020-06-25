import React, {useContext} from 'react';
import Context from "../Context";
import {Pencil, Trash} from "../template/Icons";
import {LRModalContext} from "./LevelReferencePage";

export function LevelReferenceTable() {
    const {levelReferences} = useContext(Context);
    return (
        <table className="table table-hover">
            <thead className="thead-light">
            <tr>
                <th></th>
                <th>Name</th>
                <th>Elevation</th>
                <th>Created</th>
                <th>Updated</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            {levelReferences.map((levelReference, index) => {
                return <Item levelReference={levelReference} key={levelReference.id} index={index}/>
            })}
            </tbody>
        </table>
    )
}

function Item({levelReference, index}) {

    const {openAddModal, openDeleteModal} = useContext(LRModalContext);
    return (
        <tr>
            <td>{index + 1}</td>
            <td>{levelReference.name}</td>
            <td>{levelReference.elevation}</td>
            <td>{levelReference.created}</td>
            <td>{levelReference.updated}</td>
            <td>
                <button type="button" onClick={() => openAddModal(levelReference)} className="btn btn-light">
                    <Pencil/>
                </button>
            </td>
            <td>
                <button onClick={() => openDeleteModal({id: levelReference.id, name: levelReference.name})}
                        type="button" className="btn btn-light">
                    <Trash/>
                </button>
            </td>
        </tr>
    )
}

