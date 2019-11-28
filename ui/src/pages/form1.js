import React, { Component } from 'react';
import axios from 'axios';
import { MDBContainer, MDBRow, MDBCol, MDBBtn } from 'mdbreact';
import DocsLink from './../components/docsLink';
import SectionContainer from './../components/sectionContainer';

class firstForm extends Component {
  state = {
    EmailAddress: '',
    MobileNumber: '',
    PersonalIDNumber: '',
    TaxIDNumber: '',
    formID:'1'
  };

  submitHandler = event => {
    //alert({...this.state})
    console.log({...this.state})
    axios({
    method: 'POST',
    url: 'http://10.15.15.65:9090/application-form/received',
    contentType: "application/json",
    data: {...this.state},
    //config: { headers: {'Content-Type': 'application/json' }}}
    })
    .then(function (response) {
        //handle success
        //window.alert(response);
        console.log(response);
        window.location.assign("http://10.15.15.65:3000/form2?ID="+response.data);
    })
    .catch(function (response) {
        //handle error
        console.log(response);
    });
    event.preventDefault();
    event.target.className += ' was-validated';
  };

  changeHandler = event => {
    this.setState({ ...this.state, [event.target.name]: event.target.value});
  };

  render() {
    return (
      <MDBContainer>
        <DocsLink
          title='Validation Form'
          href='https://mdbootstrap.com/docs/react/forms/validation/'
        />
        <SectionContainer header='Validations'>
          <form
            className='needs-validation'
            onSubmit={this.submitHandler}
            noValidate
          >
         
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterNameEx'
                  className='grey-text'
                >
                  Email Address
                </label>
                <input
                  value={this.state.EmailAddress}
                  name='EmailAddress'
                  onChange={this.changeHandler}
                  type='email'
                  id='defaultFormRegisterNameEx'
                  className='form-control'
                  placeholder='Email Adress'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterEmailEx2'
                  className='grey-text'
                >
                  Phone Number
                </label>
                <input
                  value={this.state.MobileNumber}
                  name='MobileNumber'
                  onChange={this.changeHandler}
                  type='number'
                  id='defaultFormRegisterEmailEx2'
                  className='form-control'
                  placeholder='Phone Number'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterConfirmEx3'
                  className='grey-text'
                >
                  Id Card Number
                </label>
                <input
                  value={this.state.PersonalIDNumber}
                  onChange={this.changeHandler}
                  type='number'
                  id='defaultFormRegisterConfirmEx3'
                  className='form-control'
                  name='PersonalIDNumber'
                  placeholder='Your Id Card Number'
                  required
                />
                <small id='emailHelp' className='form-text text-muted'>
                  We'll never share your email with anyone else.
                </small>
              </MDBCol>
           
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterPasswordEx4'
                  className='grey-text'
                >
                  TIN Number
                </label>
                <input
                  value={this.state.TaxIDNumber}
                  onChange={this.changeHandler}
                  type='number'
                  id='defaultFormRegisterPasswordEx4'
                  className='form-control'
                  name='TaxIDNumber'
                  placeholder='Tin number'
                  required
                />
                <div className='invalid-feedback'>
                  Please provide a valid city.
                </div>
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              
           
            <MDBBtn id='btnn' color='unique' type='submit'>
              Next
            </MDBBtn>
          </form>
        </SectionContainer>
      </MDBContainer>
    );
  }
}

export default firstForm;
