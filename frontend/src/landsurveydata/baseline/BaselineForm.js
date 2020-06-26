import {ModalBody} from "../../template/Modal";
import {FormModalFooter, FuncSelectInput, FuncTextInput, SelectInput} from "../../template/Control";
import React, {useContext, useState} from "react";
import {useForm} from "react-hook-form";
import Context from "../../Context";


export default function BLFrom({baseline, closeModal}) {

    let defaultValue = {};
    if (baseline && baseline.id){
        defaultValue = {
            id:  baseline.id,
            name: baseline.name,
            pointStart: baseline.pointStart.id,
            pointEnd: baseline.pointEnd.id,
            created: baseline.created,
            updated: baseline.updated
        }
    }

    const {points} = useContext(Context);
    const [equalPoints,setEqualPoints] = useState(false);
    const [isEmptyStart,setIsEmptyStart] = useState(false);
    const [isEmptyEnd,setIsEmptyEnd] = useState(false);

    const {register, handleSubmit, errors} = useForm({
        defaultValues: defaultValue
    });

    const onSubmit = (data) => {
        const pointStartId = data.pointStart;
        const pointEndId = data.pointEnd;
        const isPointStart = checkSelectInput(pointStartId);
        const isPointEnd = checkSelectInput(pointEndId);

        setEqualPoints(pointStartId === pointEndId);
        setIsEmptyStart(!isPointStart);
        setIsEmptyEnd(!isPointEnd);
        console.log(isPointStart);
        console.log(isPointEnd);
        console.log(pointStartId === pointEndId);

        if(isPointStart && isPointEnd && pointStartId !== pointEndId) {
            console.log('сюда заходит');
            closeModal({
                id: data.id,
                name: data.name,
                pointStart: getPointById(data.pointStart),
                pointEnd: getPointById(data.pointEnd),
                created: data.created,
                updated: data.updated
            },true);
        }
    };

    const checkSelectInput = (id) =>{
        return parseInt(id,10);
    };

    const getPointById = (id)=>{
      let element = {};
      for (let p of points){
          if (p.id == id){
              element = p;
              return element;
          }
      }
      return false;
    };

    const pointsEqualsAlert = (equalPoints) ? <p  className="text-danger">Choose another one, points should be different</p > : '';
    const emptyPointStart = (isEmptyStart) ? <p  className="text-danger">Choose point</p > : '';
    const emptyPointEnd = (isEmptyEnd) ? <p  className="text-danger">Choose point</p > : '';

    return (
        <div>
            <form onSubmit={handleSubmit(onSubmit)}>
                <ModalBody>
                    <FuncTextInput type="text" label="name" name="name" register={register({
                        required: "Required",
                        pattern: {
                            value: /[A-Za-z0-9А-Яа-я]/,
                            message: <p className="text-danger" >"invalid name"</p>
                        }
                    })}/>
                    {errors.name && errors.name.message}
                    <FuncSelectInput label="StartPoint" name="pointStart" register={register({
                        required: "Required"
                    })} elements={points}/>
                    {emptyPointStart}
                    <FuncSelectInput label="EndPoint" name="pointEnd" register={register({
                        required: "Required"
                    })} elements={points}/>
                    {pointsEqualsAlert}
                    {emptyPointEnd}
                </ModalBody>
                <FormModalFooter closeModal={closeModal}/>
            </form>
        </div>
    );
}