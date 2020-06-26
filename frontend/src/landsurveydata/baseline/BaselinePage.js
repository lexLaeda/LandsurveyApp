import {AddButton, Container, TableTitle} from "../../template/Control";
import React, {useContext, useState} from "react";
import {BaselineTable} from "./BaselineContent";
import Context from "../../Context";
import {ModalBody, ModalComplete, ModalFooter} from "../../template/Modal";
import BLForm from "./BaselineForm";

export const BLModalContext = React.createContext({});

export default function BaselinePage() {

    const {addElement, deleteElement, points} = useContext(Context);
    const [baseline, setBaseline] = useState({});
    const [isActiveAddModal, setIsActiveAddModal] = useState(false);
    const [isActiveDeleteModal, setIsActiveDeleteModal] = useState(false);

    const openAddModal = (baseline) => {
        if (baseline && baseline.id) {
            setBaseline(baseline);
        }
        setIsActiveAddModal(true);
    };

    const closeAddModal = (baseline, isEnable) => {
        console.log(baseline);
        if (isEnable && baseline && baseline.name) {
            addElement({element: baseline, type: 'baselines', root: 'baseline'});
        }
        setBaseline({});
        setIsActiveAddModal(false);
    };

    const openDeleteModal = (baseline) => {
        setBaseline(baseline);
        setIsActiveDeleteModal(true);
    };

    const closeDeleteModal = (baseline, isEnable) => {
        if (isEnable) {
            deleteElement({element: baseline, type: 'baselines', root: 'baseline'});
        }
        setBaseline({});
        setIsActiveDeleteModal(false);
    };

    const tableTitle = 'Baseline Table';
    const addTitle = (baseline && baseline.id) ? `Edit baseline ${baseline.name}` : 'Add new level reference';
    console.log(baseline);
    console.log(baseline.name);
    const deleteTitle = `Delete baseline ${baseline.name}`;
    const deleteBody = <p>Are you really want to delete baseline {baseline.name}?</p>;

    return (

        <BLModalContext.Provider value={{openAddModal, openDeleteModal}}>
            <Container>
                <TableTitle title={tableTitle}/>
                <BaselineTable/>
                <AddButton onClick={openAddModal}/>
                <ModalComplete isActive={isActiveAddModal} title={addTitle} close={closeAddModal}>
                    <BLForm baseline={baseline} closeModal={closeAddModal}/>
                </ModalComplete>
                <ModalComplete title={deleteTitle} isActive={isActiveDeleteModal} close={closeDeleteModal}>
                    <ModalBody>
                        {deleteBody}
                    </ModalBody>
                    <ModalFooter element={baseline} closeModal={closeDeleteModal}/>
                </ModalComplete>
            </Container>
        </BLModalContext.Provider>
    )
}