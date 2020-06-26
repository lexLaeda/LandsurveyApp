import {ModalBody} from "../../template/Modal";
import {CheckBox, FormModalFooter, FuncTextInput, TextInput} from "../../template/Control";
import ReactTooltip from "react-tooltip";
import * as React from "react";
import {useForm} from "react-hook-form";
import {useContext} from "react";
import Context from "../../Context";
import {useState} from "react";


export default function PForm({point,closeModal}) {

    const defaultValue = (point && point.id) ? point : {};

    const {points} = useContext(Context);
    const [exists,setExists] = useState(false);

    const {register,handleSubmit} = useForm({
        defaultValues: defaultValue
    });

    const onSubmit = (data) =>{
        const filtered = points.filter(point => point.name === data.name);
        if (filtered.length === 1){
            setExists(true);
        } else {
            setExists(false);
            closeModal(data,true);
        }
    };

    const existsAlert = (exists) ? <p className="text-danger">point with this name already exist</p> : '';
    let $isEnableLevelReference;
    if (!defaultValue.id) {
        $isEnableLevelReference =
            <CheckBox data-tip="hello world" label="Point is LevelReference" name="isLR" register={register}/>
    }
    return(
        <div>
            <form onSubmit={handleSubmit(onSubmit)}>
                <ModalBody>
                        <FuncTextInput register={register({
                            required: "Required",
                            pattern: {
                                value: /[A-Za-z0-9А-Яа-я]/,
                                message: <p className="text-danger" >"invalid name"</p>
                            }
                        })} type="text" label="name" name="name"/>
                        {existsAlert}
                        <FuncTextInput register={register} step="0.00001" type="number" label="X Coordinate" name="x"/>
                        <FuncTextInput register={register} step="0.00001" type="number" label="Y Coordinate" name="y"/>
                        <FuncTextInput register={register} step="0.00001" type="number" label="H Coordinate" name="h"/>
                        {$isEnableLevelReference}
                        <ReactTooltip/>
                </ModalBody>
                <FormModalFooter closeModal={closeModal}/>
            </form>
        </div>
    );
}