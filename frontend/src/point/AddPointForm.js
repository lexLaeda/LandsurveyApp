import * as React from "react";
import {CheckBox, FormModalFooter, TextInput} from "../template/Control";
import ReactTooltip from 'react-tooltip';
import {ModalBody} from "../template/Modal";

class AddPointForm extends React.Component {
    constructor(props) {
        super(props);
        this.rules = {
            isEnableToCreateLevelReference: false,
            setIsEnable() {
                this.isEnableToCreateLevelReference = !this.isEnableToCreateLevelReference;
            }
        };

        this.state = {
            id: '',
            name: '',
            x: 0,
            y: 0,
            h: 0,
            isNewPoint: true
        };
        if (props.point && props.point.id) {
            this.state.id = props.point.id;
            this.state.name = props.point.name;
            this.state.x = props.point.x;
            this.state.y = props.point.y;
            this.state.h = props.point.h;
            this.state.isNewPoint = false;
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const target = event.target;
        const name = target.name;
        const value = target.value;
        this.setState({[name]: value});
    }

    handleSubmit(event) {
        event.preventDefault();
        const id = this.state.id;
        const name = this.state.name;
        const x = this.state.x;
        const y = this.state.y;
        const h = this.state.h;
        const isLevelReference = this.rules.isEnableToCreateLevelReference;
        this.props.close({id, name, x, y, h}, true);
    }


    handleCheckBoxChange = () => {
        this.rules.setIsEnable();
    };


    render() {

        let $isEnableLevelReference;
        if (this.state.isNewPoint) {
            $isEnableLevelReference =
                <CheckBox data-tip="hello world" label="Point is LevelReference" onChange={this.handleCheckBoxChange}/>
        }
        return (
            <div>
                <ModalBody>
                    <form onSubmit={this.handleSubmit}>
                        <TextInput type="text" label="name" value={this.state.name} name="name"
                                   handleChange={this.handleChange}/>
                        <TextInput type="number" label="X Coordinate" value={this.state.x} name="x"
                                   handleChange={this.handleChange}/>
                        <TextInput type="number" label="Y Coordinate" value={this.state.y} name="y"
                                   handleChange={this.handleChange}/>
                        <TextInput type="number" label="H Coordinate" value={this.state.h} name="h"
                                   handleChange={this.handleChange}/>
                        {$isEnableLevelReference}
                        <ReactTooltip/>
                        <FormModalFooter closeModal={this.props.close}/>
                    </form>
                </ModalBody>

        </div>
        )
    }
}

export default AddPointForm;