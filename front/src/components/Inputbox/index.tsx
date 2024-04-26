import React, { ChangeEvent } from 'react'
import './style.css';

export interface InputBoxProps{
  label : string;
  type : 'text' | 'password';
  value : string;
  placeholder : string;
  onChangeHandler : (event:ChangeEvent<HTMLInputElement>) => void;
  buttonTitle? : string;
  buttonStatus? : boolean;
  onButtonClickHandler?: () => void;
  message? : string;
  error? : boolean;
}

export default function InputBox({label, type, value, placeholder, onChangeHandler, buttonTitle, buttonStatus, onButtonClickHandler, message, error} : InputBoxProps) {

  const buttonClass = buttonStatus ? 'input-primary-button' : 'input-disable-button';
  const messageClass = 'input-message ' + (error ? 'error' : 'primary'); 

  return (
    <div className = "input-box">
      <div className = "input-label label">{label}</div>
      <div className = "input-content-box">
        <input className = "input" type={type} value= {value} placeholder={placeholder} onChange={onChangeHandler}/>
        {/* && 연산자를 사용해 buttonTitle이 있으면 div 요소를 표시 아니면 표시 안함*/}
        {buttonTitle && <div className = {buttonClass} onClick={onButtonClickHandler}>{buttonTitle}</div>} 
      </div>
      <div className = {messageClass}>
        {message}
      </div>
    </div>
  )
}
