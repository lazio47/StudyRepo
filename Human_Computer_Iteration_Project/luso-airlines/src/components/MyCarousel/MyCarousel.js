import React from 'react';
import { Carousel } from 'react-responsive-carousel';
import 'react-responsive-carousel/lib/styles/carousel.min.css';
import './MyCarousel.css';
import TravelCard from '../TravelCard/TravelCard';

function MyCarousel() {
  return (
    <div className="carousel-container">
      <Carousel showArrows={false} showStatus={false} showThumbs={false} showIndicators={false} autoPlay interval={2000} infiniteLoop>
        <div>
          <img src="https://bordalo.observador.pt/v2/q:84/rs:fill:4093:2302/c:4093:2302:nowe:1:0/plain/https://s3.observador.pt/wp-content/uploads/2021/06/29094643/GettyImages-1325730433-scaled.jpg" alt="slide1" />
        </div>
        <div>
          <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Terminal_de_Carga_Aeroporto_Internacional_de_Manaus.jpg/1200px-Terminal_de_Carga_Aeroporto_Internacional_de_Manaus.jpg" alt="slide2" />
        </div>
        <div>
          <img src="https://static.euronews.com/articles/stories/08/23/92/60/1200x675_cmsv2_3aa2fb04-59b2-5cca-aa70-61b0e14c443a-8239260.jpg" alt="slide3" />
        </div>
      </Carousel>
    </div>
  );
}

export default MyCarousel;
