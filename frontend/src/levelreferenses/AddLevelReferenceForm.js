import React from 'react'
import {FormModalFooter, TextInput} from "../template/Control";


class  AddLevelReferenceForm extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            id : '',
            name : '',
            elevation : ''
        };
        if (props.levelReference && props.levelReference.id){
          this.state = {
              id : props.levelReference.id,
              name : props.levelReference.name,
              elevation : props.levelReference.elevation
          }
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleSubmit(event){
        event.preventDefault();
        const id = this.state.id;
        const name = this.state.name;
        const elevation = this.state.elevation;
        this.props.closeModal({id,name,elevation},true);
    }

    handleChange(event){
        const target = event.target;
        const name = target.name;
        const value = target.value;
        this.setState({[name]: value} )
    }

    render() {
        return(
            <form onSubmit={this.handleSubmit}>
                <TextInput type="text" label="name" name="name" value={this.state.name} handleChange={this.handleChange} />
                <TextInput type="number" label="elevation" name="elevation" value={this.state.elevation} handleChange={this.handleChange}/>
                <FormModalFooter closeModal={this.props.closeModal}/>
            </form>
        );
    }
}
export default AddLevelReferenceForm;