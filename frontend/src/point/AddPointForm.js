import * as React from "react";
import {FormModalFooter, TextInput} from "../template/Control";


class AddPointForm extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            id : '',
            name : '',
            x : 0,
            y : 0,
            h : 0
        };
        if (props.point && props.point.id){
            this.state.id = props.point.id;
            this.state.name = props.point.name;
            this.state.x = props.point.x;
            this.state.y = props.point.y;
            this.state.h = props.point.h;
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event){
        const target = event.target;
        const name = target.name;
        const value = target.value;
        this.setState({[name]:value});
    }

    handleSubmit(event){
        event.preventDefault();
        const id = this.state.id;
        const name = this.state.name;
        const x = this.state.x;
        const y = this.state.y;
        const h = this.state.h;
        this.props.closeModal({id,name,x,y,h},true);
    }


    render() {
        return(
            <form onSubmit={this.handleSubmit}>
                <TextInput type="text" label="name" value={this.state.name} name="name" handleChange={this.handleChange}/>
                <TextInput type="number" label="X Coordinate" value={this.state.x} name="x" handleChange={this.handleChange}/>
                <TextInput type="number" label="Y Coordinate" value={this.state.y} name="y" handleChange={this.handleChange}/>
                <TextInput type="number" label="H Coordinate" value={this.state.h} name="h" handleChange={this.handleChange}/>
                <FormModalFooter closeModal={this.props.closeModal}/>
            </form>
        )
    }
}
export default AddPointForm;