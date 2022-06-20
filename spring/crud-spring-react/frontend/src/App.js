import {Component} from "react";

class App extends Component {
  state = {
    clients: []
  };

  async componentDidMount() {
    const response = await fetch('/clients');
    const body = await response.json();
    this.setState({clients: body});
  }

  render() {
    const {clients} = this.state;
    return (
        <div className="App">
          <h2>Clients</h2>
          {clients.map(client =>
              <div key={client.id}>
                {client.name} ({client.email})
              </div>
          )}
        </div>
    );
  }
}
export default App;