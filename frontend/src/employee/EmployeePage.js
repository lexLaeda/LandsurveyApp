
import React, {Component, useState} from 'react'
import Context from "../Context";
import Menu from "../template/Menu";
import EmployeeTable from "./EmployeeTable";
import axios from 'axios';
import {AddButton} from "../template/Control";
import DeleteModal from "../template/DeleteModal";
import AddEmployeeModal from "./AddEmployeeModal";
import ApiService from "../template/ApiService";

class EmployeePage extends Component{

    constructor(props){
        super(props);
        this.state = {
            avatars : [],
            employees : [],
            departments : [],
            posts : [],
            employee : {},
            isActiveAddModal : false,
            isActiveDeleteModal : false
        };

    };

    componentDidMount() {
        axios.get('/api/employee/list').then(res => this.setState({employees: res.data}));
        axios.get('/api/department/list').then(res => this.setState({departments: res.data}));
        axios.get('/api/post/list').then(res => this.setState({posts: res.data}));
    }

    openDeleteModal = (employee)=> {
        this.setState({employee : employee, isActiveDeleteModal : true});
    };

    closeDeleteModal = (employee, isEnable) => {
        if(isEnable){
            this.deleteEmployee(employee);
        }
        this.setState({employee : {}, isActiveDeleteModal : false});
    };

    openAddModal = (employee) => {
        if (employee && employee.id){
            this.setState({employee : employee, isActiveAddModal : true})
        } else {
            this.setState({isActiveAddModal : true, employee : {}});
        }
    };

    closeAddModal = (employee,file, imagePreviewUrl,isEnable) => {
        if (isEnable) {
            this.saveEmployee(employee,file,imagePreviewUrl);
        }
        this.setState({isActiveAddModal : false, employee : {}});
    };

    saveEmployee(employee, file, imagePreviewUrl){
        if (employee.id){
            axios.post('/api/employee/edit/' + employee.id, employee).then(res => {
                let employees = this.state.employees;
                employees = this.getFilteredArray({array : employees, id : employee.id});
                employees.push(employee);
                this.setState({employees : employees});
            });
            if (file){
                this.saveImage({id : employee.id, file : file, imagePreviewUrl: imagePreviewUrl});
            }
        } else{
            let id;
            axios.post('/api/employee/add',employee).then(res => {
                let employees = this.state.employees;
                employees.push(res.data);
                id = res.data.id;
                this.setState({employees : employees});
            });
            this.saveImage({id : id,file : file, imagePreviewUrl : imagePreviewUrl});
        }
    }

    saveImage({id,file,imagePreviewUrl}){
        const data = new FormData();
        data.append('file',file);
        axios.post('/api/image/?id=' + id + '&dir=employee',data).then(res=>console.log(res.data));
        const avatars = this.getFilteredArray({ array :  this.state.avatars, id : id});
        avatars.push({id : id, imagePreviewUrl : imagePreviewUrl});
        this.setState({avatars : avatars});
    }

    deleteEmployee(employee){
        axios.delete('/api/employee/delete/' + employee.id).then(res => {
            if (res.status === 200){
                let employees = this.state.employees;
                employees = this.getFilteredArray({array : employees, id : employee.id});
                this.setState({employees : employees});
            }
        });
    }

    getFilteredArray({array,id}){
        return array.filter((el)=>el.id !== id);
    }

    render() {
        return(
            <Context.Provider value={{avatars: this.state.avatars, posts : this.state.posts, departments : this.state.departments, openDeleteModal : this.openDeleteModal, openAddModal : this.openAddModal}}>
                <div>
                    <Menu/>
                    <div className="container">
                        <div className="panel panel-default">
                            <div className="panel-heading">
                                <h3 className="panel-title text-center mt-5 mb-5">Employee list</h3>
                                <EmployeeTable employees={this.state.employees}/>
                                <AddButton openAddModal={this.openAddModal}/>
                            </div>
                        </div>
                    </div>
                    <AddEmployeeModal isActiveModal={this.state.isActiveAddModal}
                                      closeModal={this.closeAddModal}
                                      employee={this.state.employee}/>
                    <DeleteModal title="Delete employee"
                                 element={{id : this.state.employee.id, name : this.state.employee.fullName}}
                                 isActiveModal={this.state.isActiveDeleteModal}
                                 closeModal={this.closeDeleteModal}/>
                </div>
            </Context.Provider>
        );
    }
}
export default EmployeePage;