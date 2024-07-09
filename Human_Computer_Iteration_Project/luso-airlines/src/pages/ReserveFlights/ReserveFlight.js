import "./ReserveFlight.css"

import MyCarousel from "../../components/MyCarousel/MyCarousel"
import TravelCard from "../../components/TravelCard/TravelCard"
import Footer from "../../components/Footer/Footer"
import React from "react";
import "./ReserveFlight.css"


const Home = () => {

    return(
        <div>
            <div className="Carousel">
                <MyCarousel />
                <div className="TravelCardContainer">
                    <TravelCard />
                </div>
                <Footer />
            </div>
        </div>
    )
};

export default Home;