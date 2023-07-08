import React, { Component } from "react";
import ReactDOM from "react-dom";
import MortgageCalc from "./components/MortgageCalc";

export class App extends Component {
    render() {
        return (
            <div>
                <h1>Welcome to Rustams Mortgage Calculator</h1>
                <MortgageCalc />
            </div>
    );
    }
}

export default App;

ReactDOM.render(<App />, document.querySelector("#app"));