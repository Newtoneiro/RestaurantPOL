import './Inputs.css'

function Input(props) {
    return (
        <input
            type="text"
            placeholder={props.name}
            defaultValue={props.defaultValue}
            {...props.register(props.name)}
        />
    )
}

function RequiredInput(props) {
    return (
        <>
        <input
            type="text"
            maxLength={100}
            placeholder={props.name}
            defaultValue={props.defaultValue}
            {...props.register(props.name, { required: true })}
        />
        {props.errors[props.name] && <p className='form-error'>{props.name} is required</p>}
        </>
    )
}

RequiredInput.defaultProps = {
    defaultValue: ''
};

function RequiredFloatInput(props) {
    return (
        <>
        <input className='float-input'
            type="number"
            step={0.01}
            negative="false"
            placeholder={props.name}
            {...props.register(props.name, {
                required: {value: true, message: `${props.name} is required`},
                min: {value: 0.01, message: `${props.name} must me > 0.01`},
                valueAsNumber: {value: true, message: `${props.name} must be a number`}
            })}
        />
        <p className='form-error'>{props.errors[props.name]?.message}</p>
        </>
    )
}

function RequiredTextarea(props) {
    return (
        <>
        <textarea maxLength={200}
            {...props.register(props.name, { required: true })}
            placeholder={props.name}
        />
        {props.errors[props.name] && <p className='form-error'>{props.name} is required</p>}
        </>
    )
}

export {
    Input,
    RequiredInput,
    RequiredTextarea,
    RequiredFloatInput
};