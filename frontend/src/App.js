import React, {Component} from 'react';
import axios from 'axios';
import PointTable from './point/PointTable';
import Menu from './template/Menu';
import './App.css';
import {AddButton} from "./template/Control";
import DeleteModal from "./template/DeleteModal";
import Context from "./Context";
import AddPointModal from "./point/AddPointModal";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            points: [],
            isActiveDeleteModal: false,
            isActiveAddModal: false,
            point: {
                id: '',
                name: '',
                x: '',
                y: '',
                h: '',
            }
        };
    }

    componentDidMount() {
        this.setPoints();
    }

    setPoints() {
        axios.get('/api/point/list')
            .then(res => {
                this.setState({points: res.data});
                console.log(this.state.points);
            });
    }

    savePoint(point) {
        if (point.id) {
            axios.post('/api/point/edit/' + point.id, point).then(res => {
                console.log(res.data);
            });
        } else {
            axios.post('/api/point/add', point).then(res => console.log(res));
        }
    }

    delete(point) {
        axios.delete('/api/point/delete/' + point.id).then(res => {
            console.log(res.data);
        });
    }

    openAddModal = (point) => {
        if (point && point.id) {
            this.setState({point: point});
        }
        this.setState({isActiveAddModal: true});
    };

    openDeleteModal = (point) => {
        this.setState({point: point});
        this.setState({isActiveDeleteModal: true});
    };

    closeAddModal = (point, isEnable) => {
        if (isEnable) {
            this.savePoint(point);
        }
        this.setState({isActiveAddModal: false, point : {}});
        setTimeout(()=> this.setPoints(),1000);
    };

    closeDeleteModal = (point, isEnable) => {
        if (isEnable) {
            this.delete(point);
        }
        this.setState({isActiveDeleteModal: false, point: {} });
        setTimeout(()=> this.setPoints(),1000);
    };


    render() {
        return (
            <Context.Provider value={{openAddModal: this.openAddModal, openDeleteModal: this.openDeleteModal}}>
                <div>
                    <Menu/>
                    <div className="container">
                        <div className="panel panel-default">
                            <div className="panel-heading">
                                <h3 className="panel-title text-center mt-5 mb-5">POINTS LIST</h3>
                                <PointTable points={this.state.points}/>
                            </div>
                            <div className="panel-body">
                                <AddButton openAddModal={this.openAddModal}/>
                                <AddPointModal isActiveModal={this.state.isActiveAddModal}
                                               closeModal={this.closeAddModal}
                                               point={this.state.point}/>

                                <DeleteModal title="Delete point"
                                             element={this.state.point}
                                             isActiveModal={this.state.isActiveDeleteModal}
                                             closeModal={this.closeDeleteModal}/>;
                            </div>
                        </div>
                    </div>
                </div>
            </Context.Provider>
        );
    }
}

export default App;