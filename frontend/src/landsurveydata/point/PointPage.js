import {PointTable} from "./PointContent";
import React, {useContext, useState} from "react";
import {AddButton, Container, TableTitle} from "../../template/Control";
import {ModalBody, ModalComplete, ModalFooter} from "../../template/Modal";
import Context from "../../Context";
import PForm from "./PForm";

export const PointModalContext = React.createContext({});

export default function PointPage() {
    const {addElement, deleteElement, addPair, baselines, deleteElements} = useContext(Context);
    const [point, setPoint] = useState({});
    const [associatedBaselines, setAssociatedBaselines] = useState({});
    const [isActiveAddModal, setIsActiveAddModal] = useState(false);
    const [isActiveDeleteModal, setIsActiveDeleteModal] = useState(false);
    const [isActiveAlertModal, setIsActiveAlertModal] = useState(false);

    const openAddModal = (point) => {
        if (point && point.id) {
            setPoint(point);
        }
        setIsActiveAddModal(true);
    };

    const closeAddModal = (point, isEnable) => {
        if (isEnable) {
            if (point.isLR) {
                addPair({point: point, root: 'point'});
            } else {
                addElement({element: point, type: 'points', root: 'point'});
            }

        }
        setPoint({});
        setIsActiveAddModal(false);
    };

    const openDeleteModal = (point) => {
        setPoint(point);
        setIsActiveDeleteModal(true);
    };

    const closeDeleteModal = (point, isEnable) => {
        if (isEnable) {
            const associatedBaselines = getAssociatedBaselines(point);
            if (associatedBaselines.length === 0) {
                deleteElement({element: point, type: 'points', root: 'point'});
                setPoint({});
            } else {
                setAssociatedBaselines(associatedBaselines);
                setIsActiveAlertModal(true);
            }
        }
        setIsActiveDeleteModal(false);
    };

    const closeAlertModal = (isEnable) => {
        if (isEnable) {
            deleteElements({elements: associatedBaselines, type: 'baselines', root: 'baseline'});
            setTimeout(() => deleteElement({element: point, type: 'points', root: 'point'}), 500);
        }
        setIsActiveAlertModal(false);
        setAssociatedBaselines([]);
        setPoint({});
    };

    const getAssociatedBaselines = (point) => {
        return baselines.filter(bl => bl.pointStart.name === point.name || bl.pointEnd.name === point.name);
    };

    const tableTitle = 'Points Table';
    const addTitle = (point && point.id) ? `Edit point ${point.name}` : 'Add new point';
    const deleteTitle = `Delete point ${point.name}`;
    const deleteBody = <p>Are you really want to delete point {point.name}?</p>;
    const alertBody = <div>Baselines {baselines.map(bl => bl.name).join()} are associated with point {point.name}, they
        wil also be deleted</div>;

    return (
        <PointModalContext.Provider value={{openAddModal, openDeleteModal}}>
            <Container>
                <TableTitle title={tableTitle}/>
                <PointTable/>
                <AddButton onClick={openAddModal}/>
                <ModalComplete isActive={isActiveAddModal} title={addTitle} close={closeAddModal}>
                    <PForm point={point} closeModal={closeAddModal}/>
                </ModalComplete>
                <ModalComplete isActive={isActiveDeleteModal} title={deleteTitle} close={closeDeleteModal}>
                    <ModalBody>
                        {deleteBody}
                    </ModalBody>
                    <ModalFooter element={point} closeModal={closeDeleteModal}/>
                </ModalComplete>
                <ModalComplete isActive={isActiveAlertModal} title={deleteTitle} close={closeAlertModal}>
                    <ModalBody>
                        {alertBody}
                    </ModalBody>
                    <ModalFooter element={point} closeModal={closeAlertModal}/>
                </ModalComplete>

            </Container>
        </PointModalContext.Provider>
    )
}
