import React, { useState } from 'react';
import "./Flights.css";
import { useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlane } from '@fortawesome/free-solid-svg-icons';
import { Button, Typography, Box, Snackbar } from '@mui/material';

const Flights = () => {
    const navigate = useNavigate();
    const [miles, setMiles] = useState(500);
    const [openSnackbar, setOpenSnackbar] = useState(false);
    const [buttonDisabled, setButtonDisabled] = useState(false);

    const showDetails = (id) => {
        navigate("/FlightDetails/003")
    }

    const proceedWithFlight = () => {
        showDetails();
    };

    const handleRedeemMiles = () => {
        if (!buttonDisabled) {
            setMiles(0);
            setButtonDisabled(true);
            setOpenSnackbar(true);
            reducePrices(); // Reduz os preços dos voos
        }
    };

    const handleCloseSnackbar = () => {
        setOpenSnackbar(false);
    };

    const reducePrices = () => {
        // Seleciona todos os elementos com a classe 'price'
        const prices = document.querySelectorAll('.price');
        
        // Reduz 50 EUR de cada preço
        prices.forEach(price => {
            const currentPrice = parseFloat(price.innerText.replace(' EUR', ''));
            price.innerText = `${(currentPrice - 50).toFixed(2)} EUR`;
        });
    };


    return (
        <main className="Flight_main">
            <div className="FlightsContainer">
                <div className="Flight_title">
                    <span>Select flight from Lisboa to Praha</span>
                </div>
                <div className="searchDetails">
                    Thursday, 9 May
                </div>
                <div className="Flight_options">
                    {/* Conteúdo do voo 1 */}
                    <div className="Flight_selection">
                        <div className='city_info'>
                            <span className='city_name'>LIS</span>
                            <span className='time'>05:00</span>
                        </div>
                        <div className='arrow'>
                            <div className="stick"></div><div className="head"></div>
                        </div>
                        <div className='city_info'>
                            <span className='city_name'>CZE</span>
                            <span className='time'>08:20</span>
                        </div>
                        <div className='details'>
                            <span>Direct</span>
                            <span>4h20min</span>
                        </div>
                        <span className='price'>300.00 EUR</span>
                        <button className='proceed-btn' onClick={proceedWithFlight}>
                            <FontAwesomeIcon icon={faPlane} /> Proceed with trip
                        </button>
                    </div>

                    {/* Conteúdo do voo 2 */}
                    <div className="Flight_selection">
                        <div className='city_info'>
                            <span className='city_name'>LIS</span>
                            <span className='time'>13:50</span>
                        </div>
                        <div className='arrow'>
                            <div className="stick"></div><div className="head"></div>
                        </div>
                        <div className='city_info'>
                            <span className='city_name'>CZE</span>
                            <span className='time'>16:10</span>
                        </div>
                        <div className='details'>
                            <span>Direct</span>
                            <span>4h20min</span>
                        </div>
                        <span className='price'>320.00 EUR</span>
                        <button className='proceed-btn' onClick={proceedWithFlight}>
                            <FontAwesomeIcon icon={faPlane} /> Proceed with trip
                        </button>
                    </div>

                    {/* Conteúdo do voo 3 */}
                    <div className="Flight_selection">
                        <div className='city_info'>
                            <span className='city_name'>LIS</span>
                            <span className='time'>20:30</span>
                        </div>
                        <div className='arrow'>
                            <div className="stick"></div><div className="head"></div>
                        </div>
                        <div className='city_info'>
                            <span className='city_name'>CZE</span>
                            <span className='time'>23:50</span>
                        </div>
                        <div className='details'>
                            <span>Direct</span>
                            <span>4h20min</span>
                        </div>
                        <span className='price'>250.00 EUR</span>
                        <button className='proceed-btn' onClick={proceedWithFlight}>
                            <FontAwesomeIcon icon={faPlane} /> Proceed with trip
                        </button>
                    </div>
                </div>
            </div>
            {/* Banner de desconto de milhas */}
            <Box
                display="flex"
                style={{ backgroundColor: '#8A2BE2' }}
                color="white"
                p={10} 
                marginTop="120px"
                marginBottom="30px"
                marginLeft="20px"
                marginRight="20px"
                alignItems="center"
                justifyContent="space-between"
                position="relative"
                borderRadius="10px"
                boxShadow="0 4px 6px rgba(0, 0, 0, 0.1)"
                height="10px"
                width="calc(100% - 40px)"
                maxWidth="1200px"
            >
                <Box zIndex="1" maxWidth="60%">
                    <Typography variant="h4" fontFamily="Roboto, sans-serif" marginBottom="10px">Special Offers</Typography>
                    <Typography variant="body1" fontFamily="Roboto, sans-serif">Check out our latest offers and discounts! Don't miss out!</Typography>
                    <Typography variant="body1" fontFamily="Roboto, sans-serif">You have {miles} miles available.</Typography>
                </Box>
                <Button variant="contained" color="secondary" onClick={handleRedeemMiles} disabled={buttonDisabled} style={{ marginTop: '10px' }}>
                    Discount miles
                </Button>
            </Box>
            {/* Snackbar para mostrar mensagem */}
            <Snackbar
                anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'center',
                }}
                open={openSnackbar}
                autoHideDuration={6000}
                onClose={handleCloseSnackbar}
                message="Milhas descontadas!"
                action={
                    <Button color="inherit" size="small" onClick={handleCloseSnackbar}>
                        Close
                    </Button>
                }
            />
        </main>
    )
};

export default Flights;