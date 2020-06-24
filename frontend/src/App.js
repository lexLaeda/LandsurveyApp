import React, {Component} from 'react';
import Menu from './template/Menu';
import './App.css';
import PointPage from "./point/PointPage";
import BaselinePage from "./baseline/BaselinePage";
import LevelReferencePage from "./levelreferenses/LevelReferencePage";
import axios from "axios";
import Context from "./Context";
import PPage from "./point/PPage";

class App extends Component {
    constructor(props) {
        super(props);
        this.apiRoot = '/api';
        this.state = {
            points: [],
            baselines: [],
            levelReferences: [],
            types: [{root :'baseline',name : 'baselines'}, {root : 'level-reference'}, {root : 'point', name : 'points'}],
            element: {},
            isActiveDeleteModal: false,
            isActiveAddModal: false,
        };
    }

    componentDidMount() {
        for (let type of this.state.types) {
            this.loadContent({root : type.root, name : type.name})
        }
    }

    loadContent({root,name}) {
        axios.get(`${this.apiRoot}/${root}/list`).then(res => {
            if (res.status === 200) {
                this.setState({[name]: res.data})
            }
        })
    }

    addElement = ({element, type, root}) => {
        let event;
        if (element.id){
            event = `edit/${element.id}`;
        }else {
            event = 'add';
        }
        
        axios.post(`${this.apiRoot}/${root}/${event}`,element).then(res=>{
            if (res.status === 200){
                const elementSaved = res.data;
                const elements = this.state[type];
                const filteredElements = elements.filter(el => el.id !== elementSaved.id);
                filteredElements.push(elementSaved);
                this.setState({[type]: filteredElements});
            }

        })
    };

    deleteElement = ({element, type,root}) => {
        axios.delete(`${this.apiRoot}/${root}/delete/${element.id}`).then(res=>{
           if (res.status === 200){
               const elements = this.state[type].filter(el => el.id !== element.id);
               this.setState({[type]: elements})
           }
        });
    };

    deleteElements = ({elements, type,root}) => {

        axios.post(`${this.apiRoot}/${root}/delete-list`,elements).then(res=>{
            if (res.status === 200){
                const filtered = this.state[type].filter(el => !elements.includes(el));
                console.log(filtered);
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
                addElement: this.addElement,
                deleteElement: this.deleteElement,
                deleteElements: this.deleteElements
            }}>
                <div>
                    <Menu/>
                    <div id="carouselExampleInterval" className="carousel slide" data-ride="carousel">
                        <ol className="carousel-indicators">
                            <li data-target="#carouselExampleInterval" data-slide-to="0" className="active"></li>
                            <li data-target="#carouselExampleInterval" data-slide-to="1"></li>
                            <li data-target="#carouselExampleInterval" data-slide-to="2"></li>
                        </ol>
                        <div className="carousel-inner">
                            <div className="carousel-item active" data-interval="10000">
                                <PPage/>
                            </div>
                            <div className="carousel-item" data-interval="2000">
                                <BaselinePage/>
                            </div>
                            <div className="carousel-item">
                                <LevelReferencePage/>
                            </div>
                        </div>
                        <a className="carousel-control-prev" href="#carouselExampleInterval" role="button"
                           data-slide="prev">
                            <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span className="sr-only">Previous</span>
                        </a>
                        <a className="carousel-control-next" href="#carouselExampleInterval" role="button"
                           data-slide="next">
                            <span className="carousel-control-next-icon" aria-hidden="true"></span>
                            <span className="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </Context.Provider>

        );
    }
}

export default App;