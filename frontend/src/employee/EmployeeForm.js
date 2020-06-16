import React from 'react'
import {SelectInput, TextInput} from "../template/Control";


class EmployeeForm extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            id : '',
            firstName : '',
            lastName : '',
            birthday : '',
            gender : '',
            city : '',
            street : '',
            house : '',
            apartment : '',
            num : '',
            post : '',
            department : '',
            email : '',
            phone : ''
        }
    }
    handleSubmit(){

    }

    handleChange(){

    }

    render() {
        return(
            <div className="container">
                <h1 className="text-center mt-5">
                    Личная карточка сотрудника
                </h1>
                <!--/*@thymesVar id="employeeDto" type="com.tracking.dto.EmployeeDto"*/-->
                <form onSubmit={this.handleSubmit}>
                    <div className="row justify-content-end">
                        <div className="col-md-6 col-lg-4 mt-5 text-center">
                            <div className="avatar">
                                <div className="avatar-img">
                                </div>
                                <input className="avatar-file" name="file" type="file"/>
                            </div>
                        </div>
                        <div className="col-md-6 col-lg-4 mt-5">
                            <h4>Personal data</h4>
                            <TextInput value={this.state.firstName} type="text" label="First name" name="firstName" handleChange={this.handleChange}/>
                            <TextInput value={this.state.lastName} type="text" label="Last name" name="lastName" handleChange={this.handleChange}/>
                            <TextInput value={this.state.birthday} type="date" label="Birthday" name="birthday" handleChange={this.handleChange}/>
                            <SelectInput value={this.state.gender} elements={['M','F']} label="Gender" handleChange={this.handleChange}/>
                        </div>
                        <div className="col-md-6 col-lg-4 mt-5">
                            <h4>Address</h4>
                            <TextInput value={this.state.city} type="text" label="City" name="city" handleChange={this.handleChange}/>
                            <TextInput value={this.state.street} type="text" label="Street" name="street" handleChange={this.handleChange}/>
                            <TextInput value={this.state.house} type="text" label="House" name="house" handleChange={this.handleChange}/>
                            <TextInput value={this.state.apartment} type="text" label="Flat" name="apartment" handleChange={this.handleChange}/>
                        </div>
                        <div className="col-md-6 col-lg-4 mt-5">
                            <h4>Professional data</h4>
                            <TextInput value={this.state.num} type="text" label="Tab num" name="num" handleChange={this.handleChange}/>
                            <SelectInput value={this.state.post} label="Post" name="post" handleChange={this.handleChange} />
                            <SelectInput value={this.state.department} label="Department" name="department" handleChange={this.handleChange}/>
                        </div>
                        <div className="col-md-6 col-lg-4 mt-5">
                            <h4>Contact data</h4>
                            <TextInput value={this.state.email} type="email" label="Email" name="email" handleChange={this.handleChange}/>
                            <TextInput value={this.state.phone} type="tel" label="Phone" name="phone" handleChange={this.handleChange}/>
                        </div>
                    </div>
                    <div className="text-right mb-4">
                        <button className="btn btn-info" type="submit">Submit</button>
                        <button className="btn btn-light" type="submit">Cancel</button>
                    </div>
                </form>
            </div>
        )
    }
}