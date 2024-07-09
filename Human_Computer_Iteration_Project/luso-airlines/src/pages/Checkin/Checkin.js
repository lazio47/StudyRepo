// CheckinPage.js
import React, { useState } from 'react';
import { TextField, Button, Typography, Box } from '@mui/material';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './Checkin.css'; // Importar o arquivo CSS
import { Link } from 'react-router-dom';

const CheckinPage = () => {
  const [ticketNumber, setTicketNumber] = useState('');

  const handleInputChange = (event) => {
    setTicketNumber(event.target.value);
  };

  const handleCheckIn = () => {
    // Simulação de chamada assíncrona para verificar o número do bilhete de avião
    setTimeout(() => {
      if (ticketNumber === '123456') {
        toast.success('Check-in realizado com sucesso!');
      } else {
        toast.error('Não foi possível realizar o check-in. Verifique o número do bilhete de avião.');
      }
    }, 2000);
  };

  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        marginTop: '50px',
      }}
    >
      <ToastContainer />
      <h1>Check-in Online</h1>
      <TextField
        variant="outlined"
        label="Número do bilhete de avião"
        value={ticketNumber}
        onChange={handleInputChange}
        fullWidth
        className="checkin-textfield" // Aplicar a classe CSS para a caixa de texto
        margin="normal"
      />
      <Button variant="contained" onClick={handleCheckIn} sx={{ marginTop: '15px' }}>
        Realizar Check-in
      </Button>

    </Box>
  );
}

export default CheckinPage;
