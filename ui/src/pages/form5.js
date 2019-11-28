import React, { Component } from 'react';
import axios from 'axios';
import { MDBContainer, MDBRow, MDBCol, MDBBtn } from 'mdbreact';
import DocsLink from './../components/docsLink';
import SectionContainer from './../components/sectionContainer';

var urlParams = new URLSearchParams(window.location.search);
class fifthForm extends Component {
  state = {
    Education:'',
    ID : parseInt(urlParams.get('ID')),
    FormID : 'form5'

  };
  submitHandler = event => {
    console.log({...this.state});
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
        window.location.assign("http://10.15.15.65:3000/form6?ID="+response.data);
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
              <div className='custom-control custom-radio'>
                <input
                 checked={this.state.education === "elementarySchool"}
                 value="elementarySchool"
                 onChange={this.changeHandler}
                  type='radio'
                  className='custom-control-input'
                  id='customControlValidation2'
                  name='education'
                  required
                />
                <label
                  className='custom-control-label'
                  htmlFor='customControlValidation2'
                >
                  Elementary School
                </label>
              </div>
              <div className='custom-control custom-radio'>
                <input
                 checked={this.state.education === "JSS"}
                 value="JSS"
                 onChange={this.changeHandler}
                  type='radio'
                  className='custom-control-input'
                  id='customControlValidation3'
                  name='education'
                  required
                />
                <label
                  className='custom-control-label'
                  htmlFor='customControlValidation3'
                >
                  JSS
                </label>
              </div>
              <div className='custom-control custom-radio'>
                <input
                 checked={this.state.education === "diploma1"}
                 value="diploma1"
                 onChange={this.changeHandler}
                  type='radio'
                  className='custom-control-input'
                  id='customControlValidation5'
                  name='education'
                  required
                />
                <label
                  className='custom-control-label'
                  htmlFor='customControlValidation5'
                >
                  Diploma-1
                </label>
              </div>
              
              <div className='custom-control custom-radio mb-3'>
                <input
                checked={this.state.education === "diploma2"}
                value="diploma2"
                 onChange={this.changeHandler}
                  type='radio'
                  className='custom-control-input'
                  id='customControlValidation4'
                  name='education'
                  required
                />
                <label
                  className='custom-control-label'
                  htmlFor='customControlValidation4'
                >
                  Diploma-2
                </label>
                <div className='invalid-feedback'>
                  Select any radio button
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

export default fifthForm;
