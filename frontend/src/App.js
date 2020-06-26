import React, {Component} from 'react';
import Menu from './template/Menu';
import './App.css';
import axios from "axios";
import Context from "./Context";
import DataMainPage from "./landsurveydata/DataMainPage";
import {BrowserRouter, Route, Switch} from 'react-router-dom'
import EmployeePage from "./employee/EmployeePage";
import EmpPage from "./employee/EmpPage";

class App extends Component {
    constructor(props) {
        super(props);
        this.apiRoot = '/api';
        this.state = {
            points: [],
            baselines: [],
            levelReferences: [],
            employees: [],
            departments: [],
            posts: [],
            types: [{root: 'baseline', name: 'baselines'},
                {root: 'level-reference', name: 'levelReferences'},
                {root: 'point', name: 'points'},
                {root: 'post', name: 'posts'},
                {root: 'department', name: 'departments'},
                {root: 'employee', name: 'employees'}],

            element: {},
            isActiveDeleteModal: false,
            isActiveAddModal: false,
        };
    }

    componentDidMount() {
        for (let type of this.state.types) {
            this.loadContent({root: type.root, name: type.name})
        }
    }

    loadContent({root, name}) {
        axios.get(`${this.apiRoot}/${root}/list`).then(res => {
            if (res.status === 200) {
                console.log(res.data);
                this.setState({[name]: res.data})
            }
        })
    }

    addPair = ({point, root}) => {
        axios.post(`${this.apiRoot}/${root}/add-pair`, {
            pointDto: point,
            levelReferenceDto: {
                name: point.name,
                elevation: point.x
            }
        }).then(res => {
            if (res.status === 200) {
                const elementSaved = res.data;
                this.saveToState({elementSaved: elementSaved.pointDto, type: 'points'});
                this.saveToState({elementSaved: elementSaved.levelReferenceDto, type: 'levelReferences'});
            }
        })
    };

    addElement = ({element, type, root}) => {
        let event;
        if (element.id) {
            event = `edit/${element.id}`;
        } else {
            event = 'add';
        }

        axios.post(`${this.apiRoot}/${root}/${event}`, element).then(res => {
            if (res.status === 200) {
                const elementSaved = res.data;
                this.saveToState({elementSaved, type});
            }

        })
    };
    saveToState = ({elementSaved, type}) => {
        const elements = this.state[type];
        const filteredElements = elements.filter(el => el.id !== elementSaved.id);
        filteredElements.push(elementSaved);
        this.setState({[type]: filteredElements});
    };

    deleteElement = ({element, type, root}) => {
        axios.delete(`${this.apiRoot}/${root}/delete/${element.id}`).then(res => {
            if (res.status === 200) {
                const elements = this.state[type].filter(el => el.id !== element.id);
                this.setState({[type]: elements})
            }
        });
    };

    deleteElements = ({elements, type, root}) => {
        axios.post(`${this.apiRoot}/${root}/delete-list`, elements).then(res => {
            if (res.status === 200) {
                const filtered = this.state[type].filter(el => !elements.includes(el));
                this.setState({[type]: filtered})
            }
        });
    };

    render() {
        return (
            <Context.Provider value={{
                baselines: this.state.baselines,
                points: this.state.points,
                levelReferences: this.state.levelReferences,
                employees: this.state.employees,
                posts: this.state.posts,
                departments: this.state.departments,
                addElement: this.addElement,
                deleteElement: this.deleteElement,
                deleteElements: this.deleteElements,
                addPair: this.addPair
            }}>
                <div>

                    <BrowserRouter>
                        <Menu/>
                        <Switch>
                            <Route path={'/'} exact component={DataMainPage}/>
                            <Route path={'/tracking/employees'} component={EmpPage}/>
                        </Switch>
                    </BrowserRouter>
                </div>
            </Context.Provider>

        );
    }
}

export default App;