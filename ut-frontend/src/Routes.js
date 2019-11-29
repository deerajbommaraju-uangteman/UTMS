import React from 'react';
import { Route, Switch } from 'react-router-dom';

// LAMS
import HomePage from './pages/LAMS/HomePage';
import firstForm from './pages/LAMS/form1';
import secondForm from './pages/LAMS/form2';
import thirdForm from './pages/LAMS/form3';
import fourthForm from './pages/LAMS/form4';
import fifthForm from './pages/LAMS/form5';
import sixthForm from './pages/LAMS/form6';
import seventhForm from './pages/LAMS/form7';
import eightForm from './pages/LAMS/form8';
import ninthForm from './pages/LAMS/form9';
import tenthForm from './pages/LAMS/form10';
import eleventhForm from './pages/LAMS/form11';
import Thankyou from './pages/LAMS/Thankyou';
//Repayment
import payLoan from './pages/Repayment/payLoan';
import payment from './pages/Repayment/payment';
import payment2 from './pages/Repayment/payment2';

//Common
import tab from './pages/Common/tab';
import table from './pages/Common/table';
import ModalForm from './pages/Common/ModalForm';
import formLogin from './pages/Common/formLogin';
import SiderDemo from './pages/Common/dashboard1';
import DashTable from './pages/Common/DashTable';
import DbTable from './pages/Common/DbTable';
import Reconcile from './pages/Reconcile/Reconcile';
import Reconcile1 from './pages/Reconcile/Reconcile1';
import Topics from './pages/Common/topics';

class Routes extends React.Component {
	render() {
		return (
			<Switch>
				<Route exact path="/topics" component={Topics} />
				<Route exact path="/reconcile" component={Reconcile} />
				<Route exact path="/reconcile1" component={Reconcile1} />
				{/* LAMS */}
				<Route exact path="/" component={HomePage} />
				<Route exact path="/form1" component={firstForm} />
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
				<Route exact path="/thankyou" component={Thankyou} />

				{/* Repayment */}
				<Route exact path="/payLoan" component={payLoan} />
				<Route exact path="/payment" component={payment} />
				<Route exact path="/payment2" component={payment2} />

				{/* Common */}
				<Route exact path="/table" component={table} />
				<Route exact path="/tab" component={tab} />
				<Route exact path="/formlogin" component={formLogin} />
				<Route exact path="/modal" component={ModalForm} />
				<Route exact path="/admin" component={DashTable} />
				<Route exact path="/dashboard" component={SiderDemo} />
				<Route exact path="/db-table" component={DashTable} />
				<Route exact path="/db-table1" component={DbTable} />
				{/* temporary routes */}
				<Route exact path="/db-tabl" component={DbTable} />
				<Route exact path="/db-tab" component={DbTable} />
				<Route exact path="/db-ta" component={DbTable} />
				<Route exact path="/db-t" component={DbTable} />
				<Route exact path="/db-" component={DbTable} />

				<Route
					render={function() {
						return <h1>Not Found</h1>;
					}}
				/>
			</Switch>
		);
	}
}

export default Routes;
