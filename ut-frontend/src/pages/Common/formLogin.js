import React, { Component } from 'react';
import { MDBContainer, MDBCol, MDBBtn } from 'mdbreact';
import DocsLink from '../../components/docsLink';
import SectionContainer from '../../components/sectionContainer';

class formLogin extends Component {
  state = {
    username: '',
    password: '',
  };

  submitHandler = event => {
    alert({...this.state})
    console.log({...this.state})
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
          title='Login Form'
          href='https://mdbootstrap.com/docs/react/forms/validation/'
        />
        <SectionContainer header='Login'>
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
                  Username
                </label>
                <input
                  value={this.state.username}
                  name='username'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterNameEx'
                  className='form-control'
                  placeholder='username'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterEmailEx2'
                  className='grey-text'
                >
                  Password
                </label>
                <input
                  value={this.state.password}
                  name='password'
                  onChange={this.changeHandler}
                  type='password'
                  id='defaultFormRegisterEmailEx2'
                  className='form-control'
                  placeholder='password'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>

              
           
            <MDBBtn color='unique' type='submit' >
              Next
            </MDBBtn>
          </form>
        </SectionContainer>
      </MDBContainer>
    );
  }
}

export default formLogin;









