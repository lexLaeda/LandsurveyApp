import {FormModalFooter, FuncTextInput} from "../../template/Control";
import {ModalBody} from "../../template/Modal";
import React from "react";
import {useForm} from "react-hook-form";

export default function LevelReferenceForm({levelReference, closeModal}) {

    const defaultValue = (levelReference && levelReference.id) ? levelReference : {};

    const {register, handleSubmit, errors} = useForm({
        defaultValues: defaultValue
    });

    const onSubmit = (data) => {
        if (levelReference && levelReference.id) {
            data.id = levelReference.id;
        }
        closeModal(data, true);
    };

    return (
        <ModalBody>
            <form onSubmit={handleSubmit(onSubmit)}>
                <FuncTextInput type="text" label="name" name="name" register={register({
                    required: "Required",
                    pattern: {
                        value: /[A-Za-z0-9А-Яа-я]/,
                        message: <p className="text-danger">"invalid name"</p>
                    }
                })}/>
                {errors.name && errors.name.message}
                <FuncTextInput type="number" label="elevation" name="elevation" register={register} step="0.00001"/>
                <FormModalFooter closeModal={closeModal}/>
            </form>
        </ModalBody>
    );

}