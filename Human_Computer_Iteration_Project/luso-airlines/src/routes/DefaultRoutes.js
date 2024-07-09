import { Suspense } from "react";
import { Route, Routes } from "react-router-dom";
import Home from "../pages/Home/Home";
import LoginPage from "../pages/LoginPage/LoginPage";
import RegisterPage from "../pages/RegisterPage/RegisterPage";
import CheckinPage from "../pages/Checkin/Checkin";
import Flights from "../pages/Flights/Flights";
import FlightDetails from "../pages/FlightDetails/FlightDetails";
import InformationPage from "../pages/Information/Information";
import PaymentPage from "../pages/Payement/Pay";
import ProfilePage from "../pages/ProfilePage/ProfilePage";
import ReserveFlight from "../pages/ReserveFlights/ReserveFlight";
import Help from "../pages/Help/help";
import MyTickets from "../pages/MyTickets/MyTickets";

const DefaultRoutes = () => {
    return (
        <Routes>
            <Route
                path="/"
                element={
                    <Suspense fallback={<div>Olà LOADING...</div>}>
                        <Home />
                    </Suspense>
                }
            />
            <Route
                path="/loginPage"
                element={
                    <Suspense fallback={<div>Olà LOADING...</div>}>
                        <LoginPage />
                    </Suspense>
                }
            />

            <Route
                path="/registerPage"
                element={
                    <Suspense fallback={<div>Olà LOADING...</div>}>
                        <RegisterPage />
                    </Suspense>
                }
            />

            <Route 
                path="/checkinPage"
                element={
                    <Suspense fallback={<div>Olà LOADING...</div>}>
                        <CheckinPage />
                    </Suspense>
                }
            />

            <Route 
                path="/Flights"
                element={
                    <Suspense fallback={<div>Olà LOADING...</div>}>
                        <Flights />
                    </Suspense>
                        }
            />
            <Route
                path="/informationPage"
                element={
                    <Suspense fallback={<div>Olà LOADING...</div>}>
                        <InformationPage />
                    </Suspense>
                }
            />

            <Route
                path="/paymentPage"
                element={
                    <Suspense fallback={<div>Olà LOADING...</div>}>
                        <PaymentPage/>
                    </Suspense>
                }
            />

            <Route 
                path="/FlightDetails/:id"
                element={
                    <Suspense fallback={<div>Olà LOADING...</div>}>
                        <FlightDetails />
                    </Suspense>
                }
            />
            
            <Route
                path="/ProfilePage"
                element={
                    <Suspense fallback={<div>Olà LOADING...</div>}>
                        <ProfilePage />
                    </Suspense>
                }
            />

            <Route
                path="/ReserveFlight"
                element={
                    <Suspense fallback={<div>Olà LOADING...</div>}>
                        <ReserveFlight />
                    </Suspense>
                }
            />

            <Route
                path="/help"
                element={
                    <Suspense fallback={<div>Olà LOADING...</div>}>
                        <Help />
                    </Suspense>
                }
            />

            <Route
                path="/MyTickets"
                element={
                    <Suspense fallback={<div>Olà LOADING...</div>}>
                        <MyTickets />
                    </Suspense>
                }
            />

        </Routes>
    );
}

export default DefaultRoutes;
