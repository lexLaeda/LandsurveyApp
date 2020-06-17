import React, {Component} from 'react'
import Context from "../Context";
import Menu from "../template/Menu";
import EmployeeTable from "./EmployeeTable";
import axios from 'axios';
import {AddButton} from "../template/Control";
import DeleteModal from "../template/DeleteModal";
import AddEmployeeModal from "./AddEmployeeModal";

class EmployeePage extends Component{

    constructor(props){
        super(props);
        this.state = {
            employees : [],
            departments : [],
            posts : [],
            employee : {},
            isActiveAddModal : false,
            isActiveDeleteModal : false
        };
    }

    componentDidMount() {
        this.setEmployees();
        this.setDepartments();
        this.setPosts();
    }

    setEmployees(){
        axios.get('/api/employee/list').then((res) =>{
           this.setState({employees : res.data});
            console.log(this.state.employees);});
    }
    setDepartments(){
        axios.get('/api/department/list').then((res) => {this.setState({departments : res.data});
        console.log(this.state.departments)});
    }
    setPosts(){
        axios.get('/api/post/list').then((res) => {this.setState({posts : res.data});
            console.log(this.state.posts)});
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
            this.setState({isActiveAddModal : true});
        }
    };

    closeAddModal = (employee, isEnable) => {
        if (isEnable) {
            this.saveEmployee(employee);
            this.setEmployees();
        }
        this.setState({isActiveAddModal : false});
    };

    saveEmployee(employee){
        if (employee.id){
            axios.post('/api/employee/edit/' + employee.id, employee).then(res => console.log(res.data));
        } else{
            axios.post('/api/employee/add',employee).then(res => console.log(res.data));
        }
        this.setState({employee: {}});
        setTimeout(()=> this.setEmployees(),1000);
    }

    deleteEmployee(employee){
        axios.delete('/api/employee/delete/' + employee.id).then(res => console.log(res.data));
        this.setState({employee: {}});
        setTimeout(()=> this.setEmployees(),1000);
    }

    render() {
        return(
            <Context.Provider value={{posts : this.state.posts, departments : this.state.departments, openDeleteModal : this.openDeleteModal, openAddModal : this.openAddModal}}>
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