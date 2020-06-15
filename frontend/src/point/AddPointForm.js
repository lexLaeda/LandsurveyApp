import * as React from "react";
import {TextInput} from "../template/Control";


class AddPointForm extends React.Component{
    constructor(props){
        super(props)
    }

    render() {
        return(
            <form>
                <TextInput name="name"/>
                <TextInput name="x"/>
                <TextInput name="y"/>
                <TextInput name="h"/>
                <div className="modal-footer">
                    <button type="button" className="btn btn-secondary" data-dismiss="modal">Close
                    </button>
                    <button type="button" className="btn btn-primary">Save changes</button>
                </div>
            </form>
        )
    }
}