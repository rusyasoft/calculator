import React, {useState} from 'react'
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
        <div>
            <label>Property Price: </label>
            <input 
                type = "text"
                name = "propertyPrice"
                value = {mcase['propertyPrice']}
                onChange = {handleInputChange}
            />
            <br />
            
            <label>Down payment: </label>
            <input 
                type = "text"
                name = "downPayment"
                value = {mcase['downPayment']}
                onChange = {handleInputChange}
            />
            <br />
            
            <label>Annual Interest Rate (%): </label>
            <input 
                type = "text"
                name = "annualInterest"
                value = {mcase['annualInterest']}
                onChange = {handleInputChange}
            />
            <br />

            <label>Ammortization Period: </label>
            <input 
                type = "text"
                name = "ammortPeriod"
                value = {mcase['ammortPeriod']}
                onChange = {handleInputChange}
            />
            <br />

            <label>Payment Schedule: </label>
            <select
                name="paymentSchedule"
                onChange={handleInputChange}
            >
                <option value={'monthly'}>Monthly</option>
                <option value={'semi-monthly'}>Semi Monthly</option>
                <option value={'bi-weekly'}>Be Weekly</option>
                <option value={'weekly'}>Weekly</option>
            </select>
            <br />

            <button onClick={handleSendByGet}>Send</button>
            <br /><br />

            <label>Payment per Period: {mresult.paymentPerPeriod}</label>
        </div>
    )
}

export default MortgageCalc; 