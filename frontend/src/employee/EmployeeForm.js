import React, {useContext} from 'react'
import {FormModalFooter, SelectInput, SelectInputText, TextInput} from "../template/Control";
import Context from '../Context'

class EmployeeForm extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            file: '',
            imagePreviewUrl: '',
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
        };

        if (props.employee && props.employee.id){
            const employee = props.employee;
            const address = employee.address;
            const contact = employee.contact;
            this.state = {
                file: '',
                imagePreviewUrl: '',
                id : employee.id,
                firstName : employee.firstName,
                lastName : employee.lastName,
                birthday : employee.birthday,
                gender : employee.gender,
                city : address.city,
                street : address.street,
                house : address.house,
                apartment : address.apartment,
                num : employee.num,
                post : employee.postId,
                department : employee.departmentId,
                email : contact.email,
                phone : contact.phone
            };
        }

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);

    }

    handleSubmit(event){
        event.preventDefault();
        const employee = {
            id : this.state.id,
            num : this.state.num,
            firstName : this.state.firstName,
            lastName : this.state.lastName,
            birthday : this.state.birthday,
            gender : this.state.gender,
            address :{
                city : this.state.city,
                street : this.state.street,
                house : this.state.house,
                apartment : this.state.apartment
            },
            contact : {
                email : this.state.email,
                phone : this.state.phone
            },
            postId : this.state.post,
            departmentId : this.state.department,
        };
        console.log(employee.departmentId);
        this.props.closeModal(employee, true);
    }

    handleChange(event){
        const target = event.target;
        const name = target.name;
        const value = target.value;
        this.setState({[name]: value} )
    }
    handleImageChange(event){
        event.preventDefault();
        console.log('image event!');
        let reader = new FileReader();
        let file = event.target.files[0];
        reader.onloadend = () => {
            this.setState({
                file: file,
                imagePreviewUrl: reader.result
            });
        };

        reader.readAsDataURL(file);
        console.log(this.state.imagePreviewUrl)
    };

    render() {
        const genders = ['M','F'];
        let {imagePreviewUrl} = this.state;
        let imagePreview;
        if (imagePreviewUrl) {
            imagePreview = imagePreviewUrl;
            console.log(this.state.imagePreviewUrl)
        } else {
            imagePreview = '/api/image/?id=' + this.state.id + '&dir=employee';
        }
        console.log(this.state.imagePreviewUrl)
        return(
            <div className="container">
                <h1 className="text-center mt-5">Employee personal card</h1>
                <form onSubmit={this.handleSubmit}>
                    <div className="row justify-content-end">
                        <div className="col-md-6 col-lg-4 mt-5 text-center">
                            <div className="avatar">
                                <div className="avatar-img">
                                    <img width="120" src={imagePreview} alt="..." onChange={(e)=> this.handleImageChange(e)}/>
                                </div>
                                <input className="avatar-file" name="file" type="file"/>
                            </div>
                        </div>
                        <div className="col-md-6 col-lg-4 mt-5">
                            <h4>Personal data</h4>
                            <TextInput value={this.state.firstName} type="text" label="First name" name="firstName" handleChange={this.handleChange}/>
                            <TextInput value={this.state.lastName} type="text" label="Last name" name="lastName" handleChange={this.handleChange}/>
                            <TextInput value={this.state.birthday} type="date" label="Birthday" name="birthday" handleChange={this.handleChange}/>
                            <SelectInputText label="Gender" value={this.state.gender} elements={genders} name="gender" handleChange={this.handleChange}/>
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
                            <SelectInput label="Post" value={this.state.post} elements={this.props.posts} name="post" handleChange={this.handleChange} />
                            <SelectInput label="Department" value={this.state.department} elements={this.props.departments} name="department" handleChange={this.handleChange}/>
                        </div>
                        <div className="col-md-6 col-lg-4 mt-5">
                            <h4>Contact data</h4>
                            <TextInput value={this.state.email} type="email" label="Email" name="email" handleChange={this.handleChange}/>
                            <TextInput value={this.state.phone} type="tel" label="Phone" name="phone" handleChange={this.handleChange}/>
                        </div>
                    </div>
                    <FormModalFooter closeModal={this.props.closeModal}/>
                </form>
            </div>
        )
    }
}
export default EmployeeForm;