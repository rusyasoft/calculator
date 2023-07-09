import React, { Component } from "react";
import ReactDOM from "react-dom";
import MortgageCalc from "./components/MortgageCalc";
import 'bootstrap/dist/css/bootstrap.css';
import { Container, Row, Col } from 'reactstrap';

export class App extends Component {
    render() {
        return (
            <Container>
                <Row>
                    <Col>
                    <h1>Welcome to Rustams Mortgage Calculator</h1>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <MortgageCalc />
                    </Col>
                </Row>
            </Container>
    );
    }
}

export default App;

ReactDOM.render(<App />, document.querySelector("#app"));