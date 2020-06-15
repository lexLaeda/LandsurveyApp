
import React, {Component} from 'react'


class Modal extends React.Component{
    constructor(props){
        super(props)
    }

    render() {
        return(
            <React.Fragment>
                {this.props.isActiveModal && (
                    <div>
                        <div className="modal-backdrop fade show"></div>
                        <div className="modal show" id="deleteBaseline" tabIndex="-1" role="dialog"
                             style={{display: 'block'}}>
                            <div className="modal-dialog" role="document">
                                <div className="modal-content">{this.props.children}</div>
                            </div>
                        </div>
                    </div>)}
            </React.Fragment>
        )
    }
}
export default Modal;

