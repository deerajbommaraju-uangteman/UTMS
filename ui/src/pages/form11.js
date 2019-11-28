import React, { Component } from 'react';
import axios from 'axios';
import { MDBContainer, MDBRow, MDBCol, MDBBtn } from 'mdbreact';
import DocsLink from './../components/docsLink';
import SectionContainer from './../components/sectionContainer';

class eleventhForm extends Component {
    constructor(props) {
        super(props);
        this.submitHandler = this.submitHandler.bind(this);
        this.fileInput = React.createRef();
      }

  submitHandler = event => {
    event.preventDefault();
    alert(
        `Selected file - ${
          this.fileInput.current.files[0].name
        }`
      );
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
              <div className='custom-file'>
                <input
                  type='file'
                  ref={this.fileInput}
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
            <MDBBtn color='unique' type='submit'>
              Submit Form
            </MDBBtn>
          </form>
        </SectionContainer>
      </MDBContainer>
    );
  }
}

export default eleventhForm;
