import React from 'react'
import TextInput from "../template/input/TextInput";
import SelectInput from "../template/input/SelectInput";

class AddBaselineForm extends React.Component {

    constructor(props){
        super(props);
        this.state = {
            id: '',
            name: '',
            pointStart: 0,
            pointEnd: 0
        };
        if(props.baseline){
            this.state.id = props.baseline.id;
            this.state.name = props.baseline.name;
            this.state.pointStart = props.baseline.pointStart.id;
            this.state.pointEnd = props.baseline.pointEnd.id;
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
        const pointStart = this.props.points.filter((point)=> point.id == pointStartId)[0];
        const pointEnd = this.props.points.filter((point)=> point.id == pointEndId)[0];
        const baseline = {id,name,pointStart,pointEnd};
        this.props.closeModal(baseline, true);
    }
    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <TextInput value={this.state.name} name="name" handleChange={this.handleChange}/>
                <SelectInput value={this.state.pointStart} name="pointStart" elements={this.props.points} handleChange={this.handleChange}/>
                <SelectInput value={this.state.pointEnd} name="pointEnd" elements={this.props.points} handleChange={this.handleChange}/>
                <div className="modal-footer">
                    <button onClick={() => this.props.closeModal(this.state, false)} type="button"
                            className="btn btn-secondary">Close</button>
                    <button type="submit" className="btn btn-primary">Save changes</button>
                </div>
            </form>
        )
    }
}
export default AddBaselineForm;