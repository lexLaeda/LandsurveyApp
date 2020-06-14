import React from 'react'


export default class DeleteModal extends React.Component {


    constructor(props) {
        super(props);

    }

    render() {
        return (
            <React.Fragment>
                {this.props.isActiveDeleteModal && (
                    <div>
                        <div className="modal-backdrop fade show"></div>
                        <div className="modal show" id="deleteBaseline" tabIndex="-1" role="dialog"
                             style={{display: 'block'}}>
                            <div className="modal-dialog" role="document">
                                <div className="modal-content">
                                    <div className="modal-header">
                                        <h5 className="modal-title" id="exampleModalLabel">Delete data</h5>
                                        <button onClick={() => this.props.closeDeleteModal(this.props.element,false)} type="button"
                                                className="close" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div className="modal-body">
                                        Are you really want to delete {this.props.element.name}
                                    </div>
                                    <div className="modal-footer">
                                        <button onClick={() => this.props.closeDeleteModal(this.props.element,false)} type="button"
                                                className="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                        <button onClick={() => this.props.closeDeleteModal(this.props.element,true)} type="button" className="btn btn-primary">Confirm delete</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>)}
            </React.Fragment>
        );
    }
}