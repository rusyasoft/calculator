import React, {useState} from 'react';
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';
import axios from 'axios';


const MortgageCalc = () => {
    // const [mcase, setMcase] = useState({
    //     propertyPrice: 0,
    //     downPayment: 0,
    //     annualInterest: 0,
    //     ammortPeriod: 0,
    //     paymentSchedule: 'monthly'
    // });

    const [mcase, setMcase] = useState({
        propertyPrice: 800000,
        downPayment: 400000,
        annualInterest: 0.06,
        ammortPeriod: 20,
        paymentSchedule: 'monthly'
    });
    const [mresult, setMresult] = useState({
        paymentPerPeriod: 0
    });

    const handleInputChange = (e) => {
        const {name, value} = e.target;
        console.log('handleInput change triggered e.name: ', name );

        setMcase((prevMcase) => ({
            ...prevMcase,
            [name]: value !== '' ? (name != 'paymentSchedule'? parseInt(value) : value) : 0
        }));
    } 

    // not used for now
    const handleSendByPost = () => {
        axios.post('http://localhost:8080/mortgage/calculate', mcase)
          .then((response) => {
            // Handle the response from the backend if needed
            console.log(response.data);
          })
          .catch((error) => {
            // Handle any errors that occurred during the request
            console.error(error);
          });
    }

    const handleSendByGet = () => {
        const queryString = `?propertyPrice=${mcase.propertyPrice}&downPayment=${mcase.downPayment}&annualInterest=${mcase.annualInterest}&ammortPeriod=${mcase.ammortPeriod}&paymentSchedule=${mcase.paymentSchedule}`;
        const url = `http://localhost:8080/mortgage/calculate${queryString}`;
    
        axios.get(url)
          .then((response) => {
            // Handle the response from the backend if needed
            console.log(response.data);
            setMresult({
                ['paymentPerPeriod']:  parseInt(response.data)
            });
          })
          .catch((error) => {
            // Handle any errors that occurred during the request
            console.error(error);
          });
    }

    return (
        <Form>
            <FormGroup>
                <Label for="propertyPrice">Property Price:</Label>
                <Input
                type="text"
                name="propertyPrice"
                id="propertyPrice"
                value={mcase.propertyPrice}
                onChange={handleInputChange}
                />
            </FormGroup>

            <FormGroup>
                <Label for="downPayment">Down Payment:</Label>
                <Input
                type="text"
                name="downPayment"
                id="downPayment"
                value={mcase.downPayment}
                onChange={handleInputChange}
                />
            </FormGroup>

            <FormGroup>
                <Label for="annualInterest">Annual Interest Rate (%):</Label>
                <Input
                type="text"
                name="annualInterest"
                id="annualInterest"
                value={mcase.annualInterest}
                onChange={handleInputChange}
                />
            </FormGroup>

            <FormGroup>
                <Label for="ammortPeriod">Ammortization Period:</Label>
                <Input
                type="text"
                name="ammortPeriod"
                id="ammortPeriod"
                value={mcase.ammortPeriod}
                onChange={handleInputChange}
                />
            </FormGroup>

            <FormGroup>
                <Label for="paymentSchedule">Payment Schedule:</Label>
                <Input
                type="select"
                name="paymentSchedule"
                id="paymentSchedule"
                onChange={handleInputChange}
                >
                <option value="monthly">Monthly</option>
                <option value="semi-monthly">Semi Monthly</option>
                <option value="bi-weekly">Bi-Weekly</option>
                <option value="weekly">Weekly</option>
                </Input>
            </FormGroup>

            <Button color="primary" onClick={handleSendByGet}>Send</Button>
            <br /><br />

            <Label>Payment per Period: {mresult.paymentPerPeriod}</Label>
        </Form>
    )
}

export default MortgageCalc; 