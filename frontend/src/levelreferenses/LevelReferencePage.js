import React, {Component} from 'react'
import axios from 'axios';
import Menu from "../template/Menu";
import LevelReferenceTable, {Table} from "./LevelReferenceTable";
import DeleteModal from "../template/DeleteModal";
import {AddButton} from "../template/Control";
import Context from "../Context";
import AddLevelReferenceModal from "./AddLevelReverenceModal";


class LevelReferencePage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            levelReferenceList: [],
            isActiveDeleteModal: false,
            isActiveAddModal: false,
            levelReference: {
                id: '',
                name: ''
            }
        };
    }

    componentDidMount() {
        this.setLevelReferences();
    }

    setLevelReferences() {
        axios.get('/api/level-reference/list')
            .then(res => {
                this.setState({levelReferenceList: res.data});
            });
    }

    openDeleteModal = (levelReference) => {
        this.setState({levelReference: levelReference});
        this.setState({isActiveDeleteModal: true})
    };

    closeDeleteModal = (levelReference, isEnable) => {
        if (isEnable) {
            this.deleteLevelReference(levelReference);
        }
        this.setState({isActiveDeleteModal: false, levelReference: {}});
    };

    openAddModal = (levelReference) => {
        if (levelReference && levelReference.id) {
            this.setState({levelReference: levelReference});
        }
        this.setState({isActiveAddModal: true});
    };

    closeAddModal = (levelReference, isEnable) => {
        if (isEnable) {
            this.saveLevelReference(levelReference);
        }
        this.setState({isActiveAddModal: false, levelReference: {}});
    };

    saveLevelReference(levelReference) {
        if (levelReference.id) {
            axios.post('/api/level-reference/edit/' + levelReference.id, levelReference).then(res => {
                const levelReferenceList = this.state.levelReferenceList.filter(lr => lr.id !== levelReference.id);
                levelReferenceList.push(res.data);
                this.setState({levelReferenceList : levelReferenceList});
            });
        } else {
            axios.post('/api/level-reference/add', levelReference).then(res => {
                const levelReferenceList = this.state.levelReferenceList;
                levelReferenceList.push(res.data);
                this.setState({levelReferenceList : levelReferenceList});
            })
        }
    }

    deleteLevelReference(levelReference) {
        axios.delete('/api/level-reference/delete/' + levelReference.id).then(res => {
            if (res.status === 200){
                const levelReferenceList = this.state.levelReferenceList.filter(lr => lr.id !== levelReference.id);
                this.setState({levelReferenceList : levelReferenceList});
            }
        })
    }

    render() {

        return (
            <Context.Provider value={{openAddModal: this.openAddModal, openDeleteModal: this.openDeleteModal}}>
                <div>
                    <Menu/>
                    <div className="container">
                        <div className="panel panel-default">
                            <div className="panel-heading">
                                <h3 className="panel-title text-center mt-5 mb-5">
                                    LevelReference List
                                </h3>
                                <Table levelReferenceList={this.state.levelReferenceList}/>
                            </div>
                        </div>
                        <div className="panel-body">
                            <AddButton  openAddModal={this.openAddModal}/>
                        </div>
                    </div>
                    <AddLevelReferenceModal isActiveModal={this.state.isActiveAddModal}
                                            closeModal={this.closeAddModal}
                                            levelReference={this.state.levelReference}/>
                    <DeleteModal title="Delete Level Reference"
                                 element={this.state.levelReference}
                                 isActiveModal={this.state.isActiveDeleteModal}
                                 closeModal={this.closeDeleteModal}/>;
                </div>
            </Context.Provider>
        );
    }

}

export default LevelReferencePage;