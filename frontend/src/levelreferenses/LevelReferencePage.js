import React, {Component} from 'react'
import axios from 'axios';
import Menu from "../template/menu/Menu";
import LevelReferenceTable from "./LevelReferenceTable";
import DeleteModal from "../template/modal/DeleteModal";

class LevelReferencePage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            levelReferenceList: [],
            isActiveDeleteModal: false,
            levelReference: {
                id : '',
                name : ''
            }
        };
    }

    componentDidMount() {
        this.setLevelReferences();
    }

    setLevelReferences(){
        axios.get('/api/level-reference/list')
            .then(res => {
                this.setState({levelReferenceList: res.data});
                console.log(this.state.levelReferenceList)
            });
    }

    openDeleteModal = (levelReference) => {
        console.log("вызывается");
        this.setState({levelReference: levelReference});
        this.setState({isActiveDeleteModal: true})
    };

    closeDeleteModal = (levelReference, isEnable) => {
        if (isEnable) {
            this.deleteLevelReference(levelReference);
        }
        this.setState({isActiveDeleteModal: false});
        setTimeout(()=> this.setLevelReferences(),1000);
    };

    deleteLevelReference(levelReference) {
        axios.delete('/api/level-reference/delete/' + levelReference.id).then(res => {
            console.log(res.data);
        })
    }

    render() {

        return (
            <div>
                <Menu/>
                <div className="container">
                    <div className="panel panel-default">
                        <div className="panel-heading">
                            <h3 className="panel-title text-center mt-5 mb-5">
                                Baseline list
                            </h3>
                            <LevelReferenceTable levelReferenceList={this.state.levelReferenceList} openDeleteModal={this.openDeleteModal}/>
                        </div>
                    </div>
                    <div className="panel-body">
                        <button type="button" className="btn btn-dark" data-toggle="modal"
                                data-target="#addNewBaseline">Add new
                        </button>
                    </div>
                </div>
                <DeleteModal element={this.state.levelReference}
                             isActiveDeleteModal={this.state.isActiveDeleteModal}
                             closeDeleteModal={this.closeDeleteModal}/>;
            </div>
        );
    }

}

export default LevelReferencePage;