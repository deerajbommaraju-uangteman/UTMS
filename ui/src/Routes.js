import React from "react";
import { Route, Switch } from "react-router-dom";

// FREE
import ValidationPage from "./pages/ValidationPage";
import firstForm from "./pages/form1"
import secondForm from "./pages/form2"
import thirdForm from "./pages/form3"
import fourthForm from "./pages/form4"
import fifthForm from "./pages/form5"
import sixthForm from "./pages/form6"
import seventhForm from "./pages/form7"
import eightForm from "./pages/form8"
import ninthForm from "./pages/form9"
import tenthForm from "./pages/form10"
import eleventhForm from "./pages/form11"
import dvHome from "./pages/dvHome"
import staffHome from "./pages/staffHome"
import managerHome from "./pages/managerHome"
import payLoan from "./pages/payLoan"
import payment from "./pages/payment"
import documentsPage from "./pages/documentsPage"
import paymentPage from "./pages/paymentPage"
import investorHomePage from "./pages/investorHomePage"


class Routes extends React.Component {
  render() {
    return (
      <Switch>
        {/* FREE */}        
        <Route exact path="/form" component={firstForm} />
        <Route exact path="/form2" component={secondForm} />
        <Route exact path="/form3" component={thirdForm} />
        <Route exact path="/form4" component={fourthForm} />
        <Route exact path="/form5" component={fifthForm} />
        <Route exact path="/form6" component={sixthForm} />
        <Route exact path="/form7" component={seventhForm} />
        <Route exact path="/form8" component={eightForm} />
        <Route exact path="/Form9" component={ninthForm} />
        <Route exact path="/Form10" component={tenthForm} />
        <Route exact path="/Form11" component={eleventhForm} />
        <Route exact path="/dvHome" component={dvHome} />
        <Route exact path="/staffHome" component={staffHome} />
        <Route exact path="/managerHome" component={managerHome} />
		<Route exact path="/payLoan" component={payLoan} />    
		<Route exact path="/payment" component={payment} />
		<Route exact path="/paymentPage" component={paymentPage} />
		<Route exact path="/" component={investorHomePage} />
		<Route exact path="/documentsPage" component={documentsPage} />
        <Route
          render={function () {
            return <h1>Not Found</h1>;
          }}
        />
      </Switch>
    );
  }
}

export default Routes;
