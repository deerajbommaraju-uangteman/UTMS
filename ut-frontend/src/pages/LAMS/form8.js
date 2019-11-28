import React, { Component } from 'react';
import { MDBContainer, MDBCol, MDBBtn } from 'mdbreact';
import DocsLink from '../../components/docsLink';
import SectionContainer from '../../components/sectionContainer';

class eightForm extends Component {
  state = {
    accountHolderName: '',
    bankName:'',
    bankAccountNumber:'',
    
   
  };

  submitHandler = event => {
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
          title='Form'
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
                  Account Holder Name
                </label>
                <input
                  value={this.state.accountHolderName}
                  name='accountHolderName'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterNameEx'
                  className='form-control'
                  placeholder='Account Holder Name'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterName'
                  className='grey-text'
                >
                  Bank Name
                </label>
                <input
                  value={this.state.bankName}
                  name='bankName'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterName'
                  className='form-control'
                  placeholder='Bank Name'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterName'
                  className='grey-text'
                >
                  Bank Account Number
                </label>
                <input
                  value={this.state.bankAccountNumber}
                  name='bankAccountNumber'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterName'
                  className='form-control'
                  placeholder='Bank Account Number'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
           
 
             
           
            <MDBCol md='4' className='mb-3'>
              <div className='custom-control custom-checkbox pl-3'>
                <input
                  className='custom-control-input'
                  type='checkbox'
                  value=''
                  id='invalidCheck'
                  required
                />
                <label className='custom-control-label' htmlFor='invalidCheck'>
                  Agree to terms and conditions
                </label>
                <div className='invalid-feedback'>
                  You must agree before submitting.
                </div>
              </div>
            </MDBCol>
            
            <MDBBtn color='unique' type='submit' href="/form9">
              Submit Form
            </MDBBtn>
          </form>
        </SectionContainer>
      </MDBContainer>
    );
  }
}

export default eightForm;
