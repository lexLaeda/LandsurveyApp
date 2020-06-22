import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import App from './App';
import BaselinePage from './baseline/BaselinePage'
import LevelReferencePage from './levelreferenses/LevelReferencePage'
import './App.css';
import EmployeePage from "./employee/EmployeePage";
import PointPage from "./point/PointPage";


ReactDOM.render(
    <Router>
        <div>
            <Route exact path='/' component={App}/>
            <Route path='/pvo/point' component={PointPage}/>
            <Route path='/pvo/baseline' component={BaselinePage}/>
            <Route path='/pvo/level-reference' component={LevelReferencePage}/>
            <Route path='/tracking/employee/list' component={EmployeePage}/>
        </div>
    </Router>,
    document.getElementById('root')
);