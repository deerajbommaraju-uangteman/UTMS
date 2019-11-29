import React, { Component } from 'react';
import { MDBContainer, MDBCol, MDBBtn } from 'mdbreact';
import DocsLink from '../../components/docsLink';
import SectionContainer from '../../components/sectionContainer';

class firstForm extends Component {
  state = {
    email: '',
    phoneNumber: '',
    idCardNumber: '',
    tinNumber: '',
  };

  submitHandler = event => {
    alert({...this.state})
    // console.log({...this.state})
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
                  value={this.state.email}
                  name='email'
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
                  value={this.state.phoneNumber}
                  name='phoneNumber'
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
                  value={this.state.idCardNumber}
                  onChange={this.changeHandler}
                  type='number'
                  id='defaultFormRegisterConfirmEx3'
                  className='form-control'
                  name='idCardNumber'
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
                  value={this.state.tinNumber}
                  onChange={this.changeHandler}
                  type='number'
                  id='defaultFormRegisterPasswordEx4'
                  className='form-control'
                  name='tinNumber'
                  placeholder='Tin number'
                  required
                />
                <div className='invalid-feedback'>
                  Please provide a valid city.
                </div>
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              
           
            <MDBBtn color='unique' type='submit' href="/form2" >
              Next
            </MDBBtn>
          </form>
        </SectionContainer>
      </MDBContainer>
    );
  }
}

export default firstForm;









