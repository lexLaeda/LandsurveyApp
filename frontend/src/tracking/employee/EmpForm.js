import {FormModalFooter, FuncSelectInput, FuncTextInput, SelectInputText} from "../../template/Control";
import React, {useContext, useState} from "react";
import {useForm} from "react-hook-form";
import Context from "../../Context";


export default function EmpForm({employee, closeModal}) {

    let defaultValue = {};

    const [genders, setGenders] = useState(['M', 'F']);
    const [image, setImage] = useState({});
    const {posts, departments} = useContext(Context);

    let addressId;
    let contactId;
    let employeeId;

    if (employee && employee.id) {
        const address = employee.address;
        const contact = employee.contact;

        addressId = employee.address.id;
        contactId = employee.contact.id;
        employeeId = employee.id;

        defaultValue = {
            id: employee.id,
            firstName: employee.firstName,
            lastName: employee.lastName,
            birthday: employee.birthday,
            gender: employee.gender,
            city: address.city,
            street: address.street,
            house: address.house,
            apartment: address.apartment,
            num: employee.num,
            post: employee.postId,
            department: employee.departmentId,
            email: contact.email,
            phone: contact.phone
        }
    }

    const {register, handleSubmit, errors} = useForm({
        defaultValues: defaultValue
    });

    const onSubmit = (data) => {
        const address = new Address(
            {
                id: addressId,
                city: data.city,
                street: data.street,
                house: data.house,
                apartment: data.apartment
            });
        console.log(data.phone);
        console.log(data.email);
        console.log(data.phone);
        const contact = new Contact({id: contactId, email: data.email, phone: data.phone});
        console.log(contact);
        const employee = new Employee(
            {
                id: employeeId,
                num: data.num,
                firstName: data.firstName,
                lastName: data.lastName,
                birthday: data.birthday,
                gender: data.gender,
                department: data.department,
                post: data.post,
                address,
                contact
            });
        console.log(employee);
        closeModal(employee, image.imagePreviewUrl, image.file, true);
    };

    const handleImageChange = (event) => {
        event.preventDefault();
        const reader = new FileReader();
        const file = event.target.files[0];
        reader.onloadend = () => {
            setImage({
                file: file,
                imagePreviewUrl: reader.result
            });
        };
        reader.readAsDataURL(file);
    };
    const id = (employee.id) ? employee.id : -1;
    const avatar = (image && image.imagePreviewUrl) ? image.imagePreviewUrl : `/api/image/employee/?id=${id}`;
    return (
        <div className="container">
            <h1 className="text-center mt-5">Employee personal card</h1>
            <form onSubmit={handleSubmit(onSubmit)}>
                <div className="row justify-content-end">
                    <div className="col-md-6 col-lg-4 mt-5 text-center">
                        <div className="avatar">
                            <div className="avatar-img">
                                <img width="200" src={avatar} alt="..."/>
                            </div>
                            <input className="avatar-file" name="file" type="file"
                                   onChange={(e) => handleImageChange(e)}/>
                        </div>
                    </div>
                    <div className="col-md-6 col-lg-4 mt-5">
                        <h4>Personal data</h4>
                        <FuncTextInput register={register} type="text" label="First name" name="firstName"/>
                        <FuncTextInput register={register} type="text" label="Last name" name="lastName"/>
                        <FuncTextInput register={register} type="date" label="Birthday" name="birthday"/>
                        <SelectInputText register={register} label="Gender" elements={genders} name="gender"/>
                    </div>
                    <div className="col-md-6 col-lg-4 mt-5">
                        <h4>Address</h4>
                        <FuncTextInput register={register} type="text" label="City" name="city"/>
                        <FuncTextInput register={register} type="text" label="Street" name="street"/>
                        <FuncTextInput register={register} type="text" label="House" name="house"/>
                        <FuncTextInput register={register} type="text" label="Flat" name="apartment"/>
                    </div>
                    <div className="col-md-6 col-lg-4 mt-5">
                        <h4>Professional data</h4>
                        <FuncTextInput register={register} type="text" label="Tab num" name="num"/>
                        <FuncSelectInput register={register} label="Post" elements={posts} name="post"/>
                        <FuncSelectInput register={register} label="Department" elements={departments}
                                         name="department"/>
                    </div>
                    <div className="col-md-6 col-lg-4 mt-5">
                        <h4>Contact data</h4>
                        <FuncTextInput register={register} type="email" label="Email" name="email"/>
                        <FuncTextInput register={register} type="tel" label="Phone" name="phone"/>
                    </div>
                </div>
                <FormModalFooter closeModal={closeModal}/>
            </form>
        </div>
    );
}

function Employee({id, num, firstName, lastName, birthday, gender, address, contact, post, department}) {
    this.id = id;
    this.num = num;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.gender = gender;
    this.address = address;
    this.contact = contact;
    this.postId = post;
    this.departmentId = department;
}

function Address({id, city, street, house, apartment}) {
    this.id = id;
    this.city = city;
    this.street = street;
    this.house = house;
    this.apartment = apartment;
}

function Contact({id, email, phone}) {
    this.id = id;
    this.email = email;
    this.phone = phone;
}