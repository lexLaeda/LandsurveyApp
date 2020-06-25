import React from 'react';
import {FormModalFooter, SelectInput, TextInput} from "../template/Control";
import {ModalBody} from "../template/Modal";


class AddBaselineForm extends React.Component {

    constructor(props) {
        super(props);

        if (props.baseline && props.baseline.id) {
            this.state = {
                id: props.baseline.id,
                name: props.baseline.name,
                pointStart: props.baseline.pointStart.id,
                pointEnd: props.baseline.pointEnd.id,
                errors: {},
                baselines: []
            }
        } else {
            this.state = {
                id: '',
                name: '',
                pointStart: 0,
                pointEnd: 0,
                errors: {},
                baselines: []
            };
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const target = event.target;
        const name = target.name;
        const value = target.value;
        this.setState({[name]: value})
    }

    handleSubmit(event) {
        event.preventDefault();
        const name = this.state.name;
        const id = this.state.id;
        const pointStartId = this.state.pointStart;
        const pointEndId = this.state.pointEnd;
        console.log(this.props.points);
        const pointStart = this.props.points.filter((point) => point.id == pointStartId)[0];
        const pointEnd = this.props.points.filter((point) => point.id == pointEndId)[0];
        const baseline = {id, name, pointStart, pointEnd};
        this.props.closeModal(baseline, true);
    }

    handleValidation() {
        let isValid = true;
        const name = this.state.name;
        const errors = this.state.errors;
        if (typeof name !== "undefined") {
            if (!name.match(/^[a-zA-Z0-9]+$/)) {
                isValid = false;
                errors["name"] = "Only letters or nums";
            }

            if (name.length >= 30) {
                isValid = false;
                errors["name"] = "Maximum length of name is 30 symbols";
            }

            let sameName = this.state.baselines.filter((bl) => bl.name === name);

            if (sameName.length === 1) {
                isValid = false;
                errors["name"] = "Baseline with this name already exists";
            }
        }

        return isValid;
    }

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <ModalBody>
                        <TextInput type="text" label="name" value={this.state.name} name="name"
                                   handleChange={this.handleChange}/>
                        <span className="text-danger"></span>
                        <SelectInput label="StartPoint" value={this.state.pointStart} name="pointStart"
                                     elements={this.props.points} handleChange={this.handleChange}/>
                        <span className="text-danger"></span>
                        <SelectInput label="EndPoint" value={this.state.pointEnd} name="pointEnd"
                                     elements={this.props.points}
                                     handleChange={this.handleChange}/>
                        <span className="text-danger"></span>
                    </ModalBody>
                    <FormModalFooter closeModal={this.props.closeModal}/>
                </form>
            </div>
        )
    }
}

export default AddBaselineForm;