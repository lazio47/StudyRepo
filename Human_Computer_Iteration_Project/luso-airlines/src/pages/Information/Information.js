import React, { useState } from 'react';
import { TextField, Grid, Card, CardContent, Typography, Button, Container, FormControlLabel, Radio, RadioGroup, FormControl, FormLabel, MenuItem, Select } from '@mui/material';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './Information.css';
import { useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCreditCard } from '@fortawesome/free-solid-svg-icons';

export const InformationPage = () => {
  const navigate = useNavigate();

  const [passengers, setPassengers] = useState([
    { firstName: '', lastName: '', sex: '', age: '', address: '', country: '', civilID: '', isAdult: true }
  ]);

  const handleInputChange = (e, index) => {
    const { name, value } = e.target;
    const newPassengers = [...passengers];
    newPassengers[index][name] = value;
  
    // If the changed field is passenger type (isAdult), also update the address if the passenger is not an adult
    if (name === 'isAdult' && value === 'child') {
      newPassengers[index].address = ''; // Clear address if passenger is a child
    }
  
    setPassengers(newPassengers);
  };
  

  const handleAddPassenger = () => {
    setPassengers([...passengers, { firstName: '', lastName: '', sex: '', age: '', address: '', country: '', civilID: '', isAdult: true }]);
  };

  const handleRemovePassenger = (index) => {
    const newPassengers = [...passengers];
    newPassengers.splice(index, 1);
    setPassengers(newPassengers);
  };

  const handlePaymentClick = () => {
    // Check if all fields are filled for all passengers
    const errors = [];
    passengers.forEach((passenger, index) => {
      for (const key in passenger) {
        if (passenger[key] === '') {
          errors.push(`Please fill in the ${key} field for passenger ${index + 1}`);
        }
      }
    });
    // Display each error in a separate notification
    errors.forEach(error => {
      toast.error(error);
    });
    // If there are any errors, return without proceeding
    if (errors.length > 0) {
      return;
    }
    // If all fields are filled, proceed to the next page or execute payment logic
    // Add your logic here
    console.log('All fields are filled. Proceeding to the next page...');
    // Navigate to the payment page
    navigate('/paymentPage');
  };

  const handlePassengerTypeChange = (e, index) => {
    const { value } = e.target;
    const newPassengers = [...passengers];
    newPassengers[index].isAdult = value === 'adult';
    // If passenger is not an adult, reset fields to empty
    if (!newPassengers[index].isAdult) {
      newPassengers[index].sex = '';
      newPassengers[index].address = ''; // Remove address if passenger is a child
    }
    setPassengers(newPassengers);
  };

  return (
    <Container maxWidth="md" style={{ marginTop: '50px' }}>
      <Card variant="outlined" style={{ backgroundColor: '#FFFFFF', overflowY: 'auto' }}>
        <CardContent>
          <Typography variant="h5" component="h2" style={{ marginBottom: '20px' }}>
            Passenger Information
          </Typography>
          {passengers.map((passenger, index) => (
            <div key={index} style={{ marginBottom: '30px' }}>
              <Typography variant="h6" component="h3" style={{ marginTop: '10px' }}>
                Passenger {index + 1}
              </Typography>
              <form noValidate autoComplete="off">
                <Grid container spacing={2}>
                  <Grid item xs={12}>
                    <RadioGroup name="isAdult" value={passenger.isAdult ? 'adult' : 'child'} onChange={(e) => handlePassengerTypeChange(e, index)} row>
                      <FormControlLabel value="adult" control={<Radio />} label="Adult" />
                      <FormControlLabel value="child" control={<Radio />} label="Child" />
                    </RadioGroup>
                  </Grid>
                  <Grid item xs={12} sm={6}>
                    <TextField name="firstName" label="First Name" variant="outlined" fullWidth value={passenger.firstName} onChange={(e) => handleInputChange(e, index)} />
                  </Grid>
                  <Grid item xs={12} sm={6}>
                    <TextField name="lastName" label="Last Name" variant="outlined" fullWidth value={passenger.lastName} onChange={(e) => handleInputChange(e, index)} />
                  </Grid>
                  <Grid item xs={12}>
                    <FormControl fullWidth variant="outlined">
                      <FormLabel>Sex</FormLabel>
                      <Select
                        value={passenger.sex}
                        onChange={(e) => handleInputChange(e, index)}
                        name="sex"
                        label="Sex"
                        placeholder="Select"
                      >
                        <MenuItem value="male">Male</MenuItem>
                        <MenuItem value="female">Female</MenuItem>
                        <MenuItem value="other">Other</MenuItem>
                      </Select>
                    </FormControl>
                  </Grid>
                  <Grid item xs={12} sm={6}>
                    <TextField name="age" label="Age" variant="outlined" fullWidth value={passenger.age} onChange={(e) => handleInputChange(e, index)} />
                  </Grid>
                  <Grid item xs={12} sm={6}>
                    <TextField name="country" label="Country" variant="outlined" fullWidth value={passenger.country} onChange={(e) => handleInputChange(e, index)} />
                  </Grid>
                    <Grid item xs={12}>
                      <TextField name="address" label="Address" variant="outlined" fullWidth value={passenger.address} onChange={(e) => handleInputChange(e, index)} />
                    </Grid>
                  <Grid item xs={12}>
                    <TextField name="civilID" label="Civil Identification Number" variant="outlined" fullWidth value={passenger.civilID} onChange={(e) => handleInputChange(e, index)} />
                  </Grid>
    
                </Grid>
              </form>
              <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                {passengers.length > 1 && (
                  <Button variant="contained" style={{ backgroundColor: '#f44336', color: 'white' }} onClick={() => handleRemovePassenger(index)}>
                    Remove Passenger
                  </Button>
                )}
              </div>
            </div>
          ))}
          <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
            <Button variant="contained" style={{ backgroundColor: '#4CAF50', color: 'white' }} onClick={handleAddPassenger}>
              Add Passenger
            </Button>
          </div>
          <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
            <Button variant="contained" color="primary" onClick={handlePaymentClick}>
             Proceed to Payment&nbsp;&nbsp; <FontAwesomeIcon icon={faCreditCard} />
            </Button>
          </div>
        </CardContent>
      </Card>
      <ToastContainer />
    </Container>
  );
};

export default InformationPage;
