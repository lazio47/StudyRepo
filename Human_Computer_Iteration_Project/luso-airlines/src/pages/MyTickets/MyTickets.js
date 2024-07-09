import React, { useEffect, useState } from 'react';
import FlightTicket from './FlightTicket';
import './FlightTicket.css';
import "./NotFound"
import NotFound from './NotFound';

const MyTickets = () => {
    const valor = JSON.parse(localStorage.getItem('lusoAirlinesCurrentUser'));
    const [cliente, setCliente] = useState({
        nome: 'N/A',
        email: 'N/A',
        numeroVoo: 'N/A',
        voosAnteriores: ["N/A"],
        imagem: 'N/A'
    });

    useEffect(() => {
        if (valor && cliente.nome === 'N/A') {
            console.log("Aqui->" + valor.userFirstName);
            setCliente({
                nome: valor["userFirstName"] + " " + valor["userLastName"],
                email: valor["userEmail"],
                numeroVoo: '123456',
                voosAnteriores: ['Lisboa', 'Madri', 'Paris'],
                imagem: 'joao.png'
            });
        }
    }, [valor, cliente]); 

    const flights = [
        {
        airline: "Luso Airlines",
        flightNumber: "LA123",
        departure: {
            city: "Lisbon",
            code: "LIS",
            time: "08:00"
        },
        arrival: {
            city: "Maputo",
            code: "MPM",
            time: "16:00"
        },
        duration: "08:00",
        boardingTime: "07:30",
        passengerName: "Shelton Agostinho",
        seat: "12A",
        terminal: "S",
        gate: "G2",
        class: "E",
        date: "Mon, 1 Jun 2024"
    },
    {
        airline: "Luso Airlines",
        flightNumber: "LA123",
        departure: {
            city: "Lisboa",
            code: "LIS",
            time: "06:15"
        },
        arrival: {
            city: "Maputo",
            code: "MPM",
            time: "16:00"
        },
        duration: "09:45",
        boardingTime: "05:45",
        passengerName: "Bruno Tavarez",
        seat: "12B",
        terminal: "S",
        gate: "G2",
        class: "E",
        date: "Mon, 1 Jun 2024"
    },
    {
        airline: "Luso Airlines",
        flightNumber: "LA123",
        departure: {
            city: "Lisboa",
            code: "LIS",
            time: "06:15"
        },
        arrival: {
            city: "Maputo",
            code: "MPM",
            time: "16:00"
        },
        duration: "09:45",
        boardingTime: "05:45",
        passengerName: "Giovanni Santos",
        seat: "12C",
        terminal: "S",
        gate: "G2",
        class: "E",
        date: "Mon, 1 Jun 2024"
    },
    {
        airline: "TAP",
        flightNumber: "TAP123",
        departure: {
            city: "Praia",
            code: "RAI",
            time: "08:00"
        },
        arrival: {
            city: "Maputo",
            code: "MPM",
            time: "16:00"
        },
        duration: "08:00",
        boardingTime: "07:30",
        passengerName: "John Doe",
        seat: "12F",
        terminal: "R",
        gate: "H2",
        class: "E",
        date: "Tue, 3 Sep 2024"
    },
    {
        airline: "TAP",
        flightNumber: "TAP123",
        departure: {
            city: "Lisbon",
            code: "LIS",
            time: "01:00"
        },
        arrival: {
            city: "New York",
            code: "JFK",
            time: "09:00"
        },
        duration: "08:00",
        boardingTime: "00:30",
        passengerName: "John Doe",
        seat: "9I",
        terminal: "A",
        gate: "C2",
        class: "E",
        date: "Mon, 6 Jul 2024"
    },
    {
        airline: "British Airways",
        flightNumber: "BA456",
        departure: {
            city: "London",
            code: "LHR",
            time: "08:30"
        },
        arrival: {
            city: "Tokyo",
            code: "HND",
            time: "16:15"
        },
        duration: "06:45",
        boardingTime: "08:00",
        passengerName: "Jane Smith",
        seat: "12B",
        terminal: "5",
        gate: "D7",
        class: "B",
        date: "Wed, 10 Sep 2024"
    },
    {
        airline: "Emirates",
        flightNumber: "EK789",
        departure: {
            city: "Dubai",
            code: "DXB",
            time: "14:45"
        },
        arrival: {
            city: "Sydney",
            code: "SYD",
            time: "06:00"
        },
        duration: "11:15",
        boardingTime: "14:15",
        passengerName: "Alice Johnson",
        seat: "22A",
        terminal: "3",
        gate: "F10",
        class: "F",
        date: "Fri, 15 Nov 2024"
    }
    ];

    if (cliente.email === 'N/A' && cliente.imagem === 'N/A') {
        return <NotFound />;
    }else{
        return (
            <div className='tickets'>
                <h1 className='title'>Flight Tickets</h1>
                {flights.map((flight, index) => (
                    <><div className='ticket'><FlightTicket key={index} flight={flight} /></div></>
                ))}
            </div>
        );
    }
}

export default MyTickets;