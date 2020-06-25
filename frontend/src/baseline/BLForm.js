import {ModalBody} from "../template/Modal";
import {FormModalFooter, FuncSelectInput, FuncTextInput, SelectInput} from "../template/Control";
import React, {useContext} from "react";
import {useForm} from "react-hook-form";
import Context from "../Context";


export default function BLFrom({baseline, closeModal}) {
    let equalPoints = false;
    let defaultValue = {};
    if (baseline  && baseline.id){
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

    const {register, handleSubmit, errors} = useForm({
        defaultValues: defaultValue
    });

    const onSubmit = (data) => {
        const pointStartId = data.pointStart;
        const pointEndId = data.pointEnd;
        if (pointStartId === pointEndId){
            equalPoints = true;
        } else {
            closeModal({
                id: data.id,
                name: data.name,
                pointStart: getPointById(data.pointStart),
                pointEnd: getPointById(data.pointEnd),
                created: data.created,
                updated:data.updated
            },true);
        }
    };

    const getPointById = (id)=>{
      let element = {};
      for (let p of points){
          if (p.id === id){
              element = p;
              return element;
          }
      }

    };

    const pointsEqualsAlert = (equalPoints) ? <span className="text-danger">Choose another one, points should be different</span> : '';

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
                    <FuncSelectInput label="EndPoint" name="pointEnd" register={register({
                        required: "Required"
                    })} elements={points}/>
                    {pointsEqualsAlert}
                </ModalBody>
                <FormModalFooter closeModal={closeModal}/>
            </form>
        </div>
    );
}