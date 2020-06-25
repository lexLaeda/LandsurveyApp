import React, {useContext} from 'react';
import Context from "../Context";
import {Pencil, Trash} from "../template/Icons";
import {ModalBody, ModalHeader, ModalMain} from "../template/Modal";
import AddBaselineForm from "./AddBaselineForm";
import {BLModalContext} from "./BaselinePage";

export function BaselineTable() {
    const {baselines} = useContext(Context);
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
            {baselines.map((baseline, index) => {
                return <Item baseline={baseline} key={baseline.id} index={index}/>
            })}
            </tbody>
        </table>
    );
}

function Item(props) {
    const {openAddModal, openDeleteModal} = useContext(BLModalContext);
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
                    name: props.baseline.name
                })}
                        type="button" className="btn btn-light">
                    <Trash/>
                </button>
            </td>
        </tr>
    )
}


