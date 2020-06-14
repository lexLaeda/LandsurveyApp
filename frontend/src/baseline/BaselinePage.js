import React, {Component} from 'react'
import axios from 'axios';
import Menu from "../menu/Menu";
import BaseLineTable from "./BaselineTable";
import '../App.css';
import ModalAddBaseline from "./ModalAddBaseline";
import DeleteModal from "../Modal/DeleteModal";

class BaselinePage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            baselines: [],
            points: [],
            isActiveDeleteModal: false,
            isActiveAddModal: false,
            baseline: {
                id: '',
                name: '',
            }
        };
    }

    componentDidMount() {
        this.setBaselines();
        this.setPoints();
    }

    setBaselines() {
        axios.get('/api/baseline/list')
            .then(res => {
                this.setState({baselines: res.data});
                console.log(this.state.baselines);
            });
    }

    setPoints() {
        axios.get('/api/point/list')
            .then(res => {
                this.setState({points: res.data});
                console.log(this.state.points);
            });
    }
    saveBaseline(baseline) {

        axios.post('/api/baseline/add',baseline).then(res => {
            console.log(res.data);
        })
    }

    deleteBaseline(baseline) {
        axios.delete('/api/baseline/delete/' + baseline.id).then(res => {
            console.log(res.data);
        })
    }

    openDeleteModal = (baseline) => {
        console.log("вызывается");
        this.setState({baseline: baseline});
        this.setState({isActiveDeleteModal: true})
    };

    closeDeleteModal = (baseline, isEnable) => {
        if (isEnable) {
            this.deleteBaseline(baseline);
        }
        this.setState({isActiveDeleteModal: false});
        setTimeout(()=> this.setBaselines(),1000);
    };

    openAddModal = () => {
        this.setState({isActiveAddModal: true});
    };

    closeAddModal = (baseline, isEnable) => {
        if(isEnable){
            console.log(baseline.name);
            console.log(baseline.pointStart);
            console.log(baseline.pointEnd);
            this.saveBaseline(baseline);
        }
        this.setState({isActiveAddModal : false});
        setTimeout(()=> this.setBaselines(),1000);
    };


    render() {
        return (
            <div>
                <Menu/>
                <div className="container">
                    <div className="panel panel-default">
                        <div className="panel-heading">
                            <h3 className="panel-title text-center mt-5 mb-5">Baseline list</h3>
                            <BaseLineTable baselines={this.state.baselines} points={this.state.points}
                                           setBaselines={this.setBaselines} openDeleteModal={this.openDeleteModal}/>
                        </div>
                    </div>
                    <div className="panel-body">
                        <button type="button" className="btn btn-dark" data-toggle="modal"
                                onClick={() => this.openAddModal()}>Add new
                        </button>
                    </div>
                    <ModalAddBaseline isActiveAddModal={this.state.isActiveAddModal}
                                      closeAddModal={this.closeAddModal}
                                      points={this.state.points}/>

                    <DeleteModal element={this.state.baseline}
                                 isActiveDeleteModal={this.state.isActiveDeleteModal}
                                 closeDeleteModal={this.closeDeleteModal}/>;
                </div>
            </div>
        );
    }
}

export default BaselinePage;