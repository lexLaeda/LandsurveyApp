import React, {Component} from 'react';
import {FormModalFooter, SelectInput, TextInput} from "../template/Control";


class AddBaselineForm extends React.Component {

    constructor(props){
        super(props);

        if(props.baseline && props.baseline.id){
            this.state = {
              id : props.baseline.id,
              name : props.baseline.name,
              pointStart : props.baseline.pointStart.id,
              pointEnd : props.baseline.pointEnd.id
            }
        } else {
            this.state = {
                id: '',
                name: '',
                pointStart: 0,
                pointEnd: 0
            };
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleChange(event){
        const target = event.target;
        const name = target.name;
        const value = target.value;
        this.setState({[name]: value} )
    }
    handleSubmit(event){
        event.preventDefault();
        const name = this.state.name;
        const id = this.state.id;
        const pointStartId = this.state.pointStart;
        const pointEndId = this.state.pointEnd;
        const pointStart = this.props.points.filter((point)=> point.id ==  pointStartId)[0];
        const pointEnd = this.props.points.filter((point)=> point.id ==  pointEndId)[0];
        const baseline = {id,name,pointStart,pointEnd};
        this.props.closeModal(baseline, true);
    }
    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <TextInput type="text" label="name" value={this.state.name} name="name" handleChange={this.handleChange}/>
                <SelectInput label="StartPoint" value={this.state.pointStart} name="pointStart" elements={this.props.points} handleChange={this.handleChange}/>
                <SelectInput label="EndPoint" value={this.state.pointEnd} name="pointEnd" elements={this.props.points} handleChange={this.handleChange}/>
                <FormModalFooter closeModal={this.props.closeModal}/>
            </form>
        )
    }
}
export default AddBaselineForm;