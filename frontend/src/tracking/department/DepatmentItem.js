import React from "react";


function DepartmentItem({department, openAttendance}) {
    return(
        <div className=" col-sm-6 col-md-4 col-lg-3 mt-4 mr-4">
        <div className="card department-card " >
            <img src={`/api/image/department/?id=${department.id}`} className="card-img-top" alt="..."/>
                <div className="card-body department-card-body">
                    <h5 className="card-title">{department.name}</h5>
                    <p className="card-text">{department.description}</p>
                </div>
                <ul className="list-group list-group-flush">
                    <li className="list-group-item">Cras justo odio</li>
                    <li className="list-group-item">Dapibus ac facilisis in</li>
                    <li className="list-group-item">Vestibulum at eros</li>
                </ul>
                <div className="card-body">
                    <button onClick={() => openAttendance(department)} className="card-link">Card link</button>
                    <a href="#" className="card-link">Another link</a>
                </div>
        </div>
       </div>
    );
}

export default DepartmentItem;