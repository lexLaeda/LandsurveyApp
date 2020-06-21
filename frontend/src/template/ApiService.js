import axios from 'axios'


class ApiService {

    findAll({root}){
        let elements = [];
        axios.get(`/api/${root}/list`).then(res=>{
            elements = res.data;
        });
        return elements;
    }
}

export default ApiService;