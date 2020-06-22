import React, {Component} from 'react'
import axios from 'axios';
import {AddBaselineModal, Table} from "./BaslineContent";
import '../App.css';

import {AddButton} from "../template/Control";
import Context from "../Context"
import {DeleteModal} from "../template/Modal";

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
                pointStart: {},
                pointEnd: {}
            }
        };
    }

    componentDidMount() {
        axios.get('/api/baseline/list').then(res => this.setState({baselines: res.data}));
        axios.get('/api/point/list').then(res => this.setState({points: res.data}));

    }

    saveBaseline(baseline) {
        if (baseline.id) {
            axios.post('/api/baseline/edit/' + baseline.id, baseline).then(res => {
                if (res.status === 200) {
                    const baselines = this.state.baselines.filter(bl => bl.id !== baseline.id);
                    baselines.push(res.data);
                    this.setState({baselines: baselines});
                }
            });
        } else {
            axios.post('/api/baseline/add', baseline).then(res => {
                if (res.status === 200) {
                    const baselines = this.state.baselines;
                    baselines.push(res.data);
                    this.setState({baselines: baselines});
                }
            })
        }
    }

    deleteBaseline(baseline) {
        axios.delete('/api/baseline/delete/' + baseline.id).then(res => {
            if (res.status === 200) {
                const baselines = this.state.baselines.filter(bl => bl.id !== baseline.id);
                this.setState({baselines: baselines});
            }
        })
    }

    openDeleteModal = (baseline) => {
        this.setState({isActiveDeleteModal: true, baseline: baseline})
    };

    closeDeleteModal = (baseline, isEnable) => {
        if (isEnable) {
            this.deleteBaseline(baseline);
        }
        this.setState({isActiveDeleteModal: false, baseline: {}});
    };

    openAddModal = (baseline) => {
        if (baseline && baseline.id) {
            this.setState({baseline: baseline});
        }
        this.setState({isActiveAddModal: true});
    };

    closeAddModal = (baseline, isEnable) => {
        if (isEnable) {
            this.saveBaseline(baseline);
        }
        this.setState({isActiveAddModal: false, baseline: {}});
    };


    render() {
        return (
            <Context.Provider value={{openAddModal: this.openAddModal, openDeleteModal: this.openDeleteModal}}>
                <div>
                    <div className="container">
                        <div className="panel panel-default">
                            <div className="panel-heading">
                                <h3 className="panel-title text-center mt-5 mb-5">Baseline list</h3>
                                <Table baselines={this.state.baselines}/>
                            </div>
                        </div>
                        <AddButton openAddModal={this.openAddModal}/>
                        <AddBaselineModal isActiveModal={this.state.isActiveAddModal}
                                          closeModal={this.closeAddModal}
                                          points={this.state.points}
                                          baseline={this.state.baseline}/>

                        <DeleteModal title="Delete baseline"
                                     element={this.state.baseline}
                                     isActiveModal={this.state.isActiveDeleteModal}
                                     closeModal={this.closeDeleteModal}/>
                    </div>
                </div>
            </Context.Provider>
        );
    }
}

export default BaselinePage;