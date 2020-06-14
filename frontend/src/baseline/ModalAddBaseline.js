import React from 'react'
import AddBaselineForm from "./AddBaselineForm";


export default class ModalAddBaseline extends React.Component {

    constructor(props) {
        super(props);

    }

    render() {
        return (
            <React.Fragment>
                {this.props.isActiveAddModal && (
                    <div>
                        <div className="modal-backdrop fade show"></div>
                        <div className="modal show" id="addNewBaseline" tabIndex="-1" role="dialog"
                             style={{display: 'block'}}>
                            <div className="modal-dialog" role="document">
                                <div className="modal-content">
                                    <div className="modal-header">
                                        <h5 className="modal-title" id="exampleModalLabel">Create new baseline</h5>
                                        <button onClick={() => this.props.closeAddModal({},false)} type="button"
                                                className="close" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <AddBaselineForm points={this.props.points} closeAddModal={this.props.closeAddModal}/>
                                </div>
                            </div>
                        </div>
                    </div>)}
            </React.Fragment>
        );
    }
}