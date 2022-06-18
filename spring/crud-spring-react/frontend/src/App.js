import React, { Component } from 'react';
import './App.css';
import Home from './components/Home.js';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ClientList from './components/ClientList.js';
import ClientEdit from "./components/ClientEdit.js";

class App extends Component {
  render() {
    return (
        // todo: make router work, url is adjusted but not served, use hash-router instead?
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/clients' exact={true} component={ClientList}/>
            <Route path='/clients/:id' component={ClientEdit}/>
          </Switch>
        </Router>
    )
  }
}

export default App;