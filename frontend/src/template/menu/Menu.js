import React from 'react'
import '../../App.css';
import 'bootstrap/dist/js/bootstrap.js';
import {Link} from 'react-router-dom';

export default function Menu() {
    return (
        <header>
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                <a className="navbar-brand" href="#">Navbar</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item dropdown">
                            <a className="nav-link dropdown-toggle active" id="navbarDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Horizontal and vertical justification
                            </a>
                            <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                                <Link className="dropdown-item" to="/pvo/baseline">Baselines</Link>
                                <Link className="dropdown-item" to="/">Points</Link>
                                <Link className="dropdown-item" to="/pvo/level-reference">Level References</Link>
                            </div>
                        </li>
                        <li className="nav-item dropdown">
                            <a className="nav-link dropdown-toggle" id="navbarDropdown2" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Employee Tracking
                            </a>
                            <div className="dropdown-menu" aria-labelledby="navbarDropdown2">
                                <a className="dropdown-item" href="/">Employee list</a>
                                <a className="dropdown-item" href="/">Department table</a>
                                <a className="dropdown-item" href="/">Department holidays</a>
                            </div>
                        </li>
                        <li className="nav-item active">
                            <a className="nav-link" href="/">Points list<span className="sr-only">(current)</span></a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">Link</a>
                        </li>
                        <li className="nav-item dropdown">
                            <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Dropdown
                            </a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link disabled" href="#" tabIndex="-1" aria-disabled="true">Disabled</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
    )
}