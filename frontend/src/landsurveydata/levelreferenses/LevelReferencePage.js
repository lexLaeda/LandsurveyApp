import {AddButton, Container, TableTitle} from "../../template/Control";
import React, {useContext, useState} from "react";
import {LevelReferenceTable} from "./LevelReferenceTable";
import {ModalBody, ModalComplete, ModalFooter} from "../../template/Modal";
import Context from "../../Context";
import LevelReferenceForm from "./LevelReferenceForm";

export const LRModalContext = React.createContext({});

export default function LevelReferencePage() {

    const {points, addElement, deleteElement} = useContext(Context);
    const [levelReference, setLevelReference] = useState({});
    const [isActiveAddModal, setIsActiveAddModal] = useState(false);
    const [isActiveDeleteModal, setIsActiveDeleteModal] = useState(false);

    const openAddModal = (levelReference) => {
        if (levelReference && levelReference.id) {
            setLevelReference(levelReference);
        }
        setIsActiveAddModal(true);
    };

    const closeAddModal = (levelReference, isEnable) => {
        if (isEnable) {
            addElement({element: levelReference, type: 'levelReferences', root: 'level-reference'});
        }
        setLevelReference({});
        setIsActiveAddModal(false);
    };

    const getAssociatedPoints = (levelReference) => {
        return points.filter(point => point.name === levelReference.name);
    };

    const openDeleteModal = (levelReference) => {
        setLevelReference(levelReference);
        setIsActiveDeleteModal(true);
    };

    const closeDeleteModal = (levelReference, isEnable) => {
        if (isEnable) {
            if (getAssociatedPoints(levelReference).length === 0) {
                deleteElement({element: levelReference, type: 'levelReferences', root: 'level-reference'});
            }
        }
        setLevelReference({});
        setIsActiveDeleteModal(false);
    };

    const tableTitle = 'Level reference Table';
    const addTitle = (levelReference && levelReference.id) ? `Edit level reference ${levelReference.name}` : 'Add new level reference';
    const deleteTitle = `Delete level reference ${levelReference.name}`;
    const deleteBody = <p>Are you really want to delete level reference {levelReference.name}?</p>;

    return (
        <LRModalContext.Provider value={{openAddModal, openDeleteModal, closeAddModal}}>
            <Container>
                <TableTitle title={tableTitle}/>
                <LevelReferenceTable/>
                <AddButton onClick={openAddModal}/>
                <ModalComplete isActive={isActiveAddModal} title={addTitle} close={closeAddModal}>
                    <LevelReferenceForm closeModal={closeAddModal} levelReference={levelReference}/>
                </ModalComplete>
                <ModalComplete isActive={isActiveDeleteModal} title={deleteTitle} close={closeDeleteModal}>
                    <ModalBody>
                        {deleteBody}
                    </ModalBody>
                    <ModalFooter element={levelReference} closeModal={closeDeleteModal}/>
                </ModalComplete>
            </Container>
        </LRModalContext.Provider>
    )
}