import React, {Component} from 'react';
import axios from 'axios';
import PointTable from './point/PointTable';
import Menu from './template/Menu';
import './App.css';

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            points: []
        };
    }

    componentDidMount() {
        axios.get('/api/point/list')
            .then(res => {
                this.setState({points: res.data});
                console.log(this.state.points);
            });
    }

    render() {
        return (
            <div>
                <Menu/>
                <div className="container">
                    <div className="panel panel-default">
                        <div className="panel-heading">
                            <h3 className="panel-title text-center mt-5 mb-5">
                                POINTS LIST
                            </h3>
                            <PointTable points={this.state.points}/>
                        </div>
                        <div className="panel-body">
                            <button type="button" className="btn btn-dark" data-toggle="modal"
                                    data-target="#addNewPoint">Add new
                            </button>
                        </div>
                    </div>
                </div>
                <div className="modal fade" id="addNewPoint" tabIndex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div className="modal-dialog" role="document">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title" id="exampleModalLabel">Create new point</h5>
                                <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form>
                                <div className="modal-body">
                                    <div className="form-group">
                                        <label htmlFor="name">Name</label>
                                        <input type="text" className="form-control" id="name"/>
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="x">X coordinate</label>
                                        <input type="text" className="form-control" id="x"/>
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="y">Y coordinate</label>
                                        <input type="text" className="form-control" id="y"/>
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="h">H coordinate</label>
                                        <input type="text" className="form-control" id="h"/>
                                    </div>
                                </div>
                                <div className="modal-footer">
                                    <button type="button" className="btn btn-secondary" data-dismiss="modal">Close
                                    </button>
                                    <button type="button" className="btn btn-primary">Save changes</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default App;