import React from 'react';
import 'antd/dist/antd.css';
import { Layout, Menu, Icon } from 'antd';
import { BrowserRouter as Router, NavLink } from 'react-router-dom';
import { relative } from 'path';
import './dashboard1.css';

const { Sider } = Layout;
const { SubMenu } = Menu;

class SiderDemo extends React.Component {
	state = {
		collapsed: true
	};

	onCollapse = (collapsed) => {
		console.log(collapsed);
		this.setState({ collapsed });
	};

	render() {
		return (
			<div>
				{/* <Header className="header">
          <Menu
            theme="dark"
            mode="horizontal"
            defaultSelectedKeys={["2"]}
            style={{ lineHeight: "64px" }}
          >
            <Menu.Item key="1">nav 1</Menu.Item>
            <Menu.Item key="2">nav 2</Menu.Item>
            <Menu.Item key="3">nav 3</Menu.Item>
          </Menu>
        </Header> */}
				<Layout style={{ minHeight: '100vh' }}>
					<Sider collapsible collapsed={this.state.collapsed} onCollapse={this.onCollapse}>
						<Menu theme="dark" mode="inline">
							<Menu.Item key="1">
								<NavLink to="/db-tabl" activeClassName="navbar-list">
									<Icon type="search" />
									<span>Search</span>
								</NavLink>
							</Menu.Item>
							<SubMenu
								key="sub1"
								title={
									<span>
										<Icon type="desktop" />
										<span>User</span>
									</span>
								}
							>
								<Menu.Item key="2">
									<NavLink to="/topics" activeClassName="navbar-list">
										Application Workbench
									</NavLink>
								</Menu.Item>
								<Menu.Item key="3">
									<NavLink to="/db-ta" activeClassName="navbar-list">
										patner applicaton draft
									</NavLink>
								</Menu.Item>
								<Menu.Item key="4">
									<NavLink to="/db-t" activeClassName="navbar-list">
										Dv workload users
									</NavLink>
								</Menu.Item>
								<Menu.Item key="5">
									<NavLink to="/db-" activeClassName="navbar-list">
										workload history
									</NavLink>
								</Menu.Item>
							</SubMenu>
							<SubMenu
								key="sub2"
								title={
									// path = { ["/users/:id", "/profile/:id"]}
									<span>
										<Icon type="pie-chart" />
										<span>Reports</span>
									</span>
								}
							>
								<Menu.Item key="db-table1">
									<NavLink to="db-table1" activeClassName="navbar-list">
										Reports
									</NavLink>
								</Menu.Item>
								<Menu.Item key="db-table">
									<NavLink to="db-table" activeClassName="navbar-list">
										Dv Productivity Report
									</NavLink>
								</Menu.Item>
							</SubMenu>
						</Menu>
					</Sider>
				</Layout>
			</div>
		);
	}
}
export default SiderDemo;
