import React, { Component } from 'react';
import axios from 'axios';
import { MDBContainer, MDBRow, MDBCol, MDBBtn } from 'mdbreact';
import DocsLink from './../components/docsLink';
import SectionContainer from './../components/sectionContainer';

var urlParams = new URLSearchParams(window.location.search);
class thirdForm extends Component {
  state = {
    // knowAboutUT:'',
    ID : parseInt(urlParams.get('ID')),
    FormID : 'form3'

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
        window.location.assign("http://10.15.15.65:3000/form4?ID="+response.data);
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
             
              <MDBCol md='4' className='mb-3'>
              <div className='custom-control custom-radio'>
                <input
                 checked={this.state.knowAboutUT === "billiBoards"}
                 value="billiBoards"
                 onChange={this.changeHandler}
                  type='radio'
                  className='custom-control-input'
                  id='customControlValidation2'
                  name='knowAboutUT'
                  required
                />
                <label
                  className='custom-control-label'
                  htmlFor='customControlValidation2'
                >
                  Billi Boards
                </label>
              </div>
              <div className='custom-control custom-radio'>
                <input
                 checked={this.state.knowAboutUT === "cinema"}
                 value="cinema"
                 onChange={this.changeHandler}
                  type='radio'
                  className='custom-control-input'
                  id='customControlValidation3'
                  name='knowAboutUT'
                  required
                />
                <label
                  className='custom-control-label'
                  htmlFor='customControlValidation3'
                >
                  Cinema
                </label>
              </div>
              <div className='custom-control custom-radio'>
                <input
                 checked={this.state.knowAboutUT === "brochure"}
                 value="brochure"
                 onChange={this.changeHandler}
                  type='radio'
                  className='custom-control-input'
                  id='customControlValidation5'
                  name='knowAboutUT'
                  required
                />
                <label
                  className='custom-control-label'
                  htmlFor='customControlValidation5'
                >
                  Brouchure
                </label>
              </div>
              
              <div className='custom-control custom-radio mb-3'>
                <input
                checked={this.state.knowAboutUT === "email"}
                value="email"
                 onChange={this.changeHandler}
                  type='radio'
                  className='custom-control-input'
                  id='customControlValidation4'
                  name='knowAboutUT'
                  required
                />
                <label
                  className='custom-control-label'
                  htmlFor='customControlValidation4'
                >
                  Email
                </label>
                <div className='invalid-feedback'>
                  Select any radio button
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

export default thirdForm;
