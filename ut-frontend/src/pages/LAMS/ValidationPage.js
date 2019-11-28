import React, { Component } from 'react';
import { MDBContainer, MDBRow, MDBCol, MDBBtn } from 'mdbreact';
import DocsLink from '../../components/docsLink';
import SectionContainer from '../../components/sectionContainer';

class ValidationPage extends Component {
  state = {
    fname: '',
    lname: '',
    email: '',
    city: '',
    state: '',
    zip: '',
    gender:'',
    opt:'',

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
            <MDBRow>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterNameEx'
                  className='grey-text'
                >
                  First name
                </label>
                <input
                  value={this.state.fname}
                  name='fname'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterNameEx'
                  className='form-control'
                  placeholder='First name'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterEmailEx2'
                  className='grey-text'
                >
                  Last name
                </label>
                <input
                  value={this.state.lname}
                  name='lname'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterEmailEx2'
                  className='form-control'
                  placeholder='Last name'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterConfirmEx3'
                  className='grey-text'
                >
                  Email
                </label>
                <input
                  value={this.state.email}
                  onChange={this.changeHandler}
                  type='email'
                  id='defaultFormRegisterConfirmEx3'
                  className='form-control'
                  name='email'
                  placeholder='Your Email address'
                  required
                />
                <small id='emailHelp' className='form-text text-muted'>
                  We'll never share your email with anyone else.
                </small>
              </MDBCol>
            </MDBRow>
            <MDBRow>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterPasswordEx4'
                  className='grey-text'
                >
                  City
                </label>
                <input
                  value={this.state.city}
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterPasswordEx4'
                  className='form-control'
                  name='city'
                  placeholder='City'
                  required
                />
                <div className='invalid-feedback'>
                  Please provide a valid city.
                </div>
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterPasswordEx4'
                  className='grey-text'
                >
                  State
                </label>
                <input
                  value={this.state.state}
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterPasswordEx4'
                  className='form-control'
                  name='state'
                  placeholder='State'
                  required
                />
                <div className='invalid-feedback'>
                  Please provide a valid state.
                </div>
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterPasswordEx4'
                  className='grey-text'
                >
                  Zip
                </label>
                <input
                  value={this.state.zip}
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterPasswordEx4'
                  className='form-control'
                  name='zip'
                  placeholder='Zip'
                  required
                />
                <div className='invalid-feedback'>
                  Please provide a valid zip.
                </div>
                <div className='valid-feedback'>Looks good!</div>
            </MDBCol>
                
              <MDBCol md='4' className='mb-3'>
              <div className='custom-control custom-radio'>
                <input
                 checked={this.state.gender === "male"}
                 value="male"
                 onChange={this.changeHandler}
                  type='radio'
                  className='custom-control-input'
                  id='customControlValidation2'
                  name='gender'
                  required
                />
                <label
                  className='custom-control-label'
                  htmlFor='customControlValidation2'
                >
                  Toggle this custom radio
                </label>
              </div>
              <div className='custom-control custom-radio mb-3'>
                <input
                checked={this.state.gender === "female"}
                value="female"
                 onChange={this.changeHandler}
                  type='radio'
                  className='custom-control-input'
                  id='customControlValidation3'
                  name='gender'
                  required
                />
                <label
                  className='custom-control-label'
                  htmlFor='customControlValidation3'
                >
                  Or toggle this other custom radio
                </label>
                <div className='invalid-feedback'>
                  Select any radio button
                </div>
              </div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='opt' value={this.state.opt} className='custom-select bMDBRowser-default' required>
                  <option value='select'>Open this select menu</option>
                  <option value='1'>One</option>
                  <option value='2'>Two</option>
                  <option value='3'>Three</option>
                </select>
                <div className='invalid-feedback'>
                  Select one
                </div>
              </div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
              <div className='custom-file'>
                <input
                  type='file'
                  className='custom-file-input'
                  id='validatedCustomFile'
                  required
                />
                <label
                  className='custom-file-label'
                  htmlFor='validatedCustomFile'
                >
                  Choose file...
                </label>
                <div className='invalid-feedback'>
                  upload a file
                </div>
              </div>
              </MDBCol>
            </MDBRow>
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
            <MDBBtn color='unique' type='submit'>
              Submit Form
            </MDBBtn>
          </form>
        </SectionContainer>
      </MDBContainer>
    );
  }
}

export default ValidationPage;
