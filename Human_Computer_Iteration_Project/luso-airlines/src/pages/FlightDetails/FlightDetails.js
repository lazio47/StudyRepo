import "./FlightDetails.css"

import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlane } from '@fortawesome/free-solid-svg-icons';
import {faShoppingCart} from '@fortawesome/free-solid-svg-icons'
import data from './Flights.json';


const FlightDetails = () => {
    const navigate = useNavigate();


    const { id } = useParams();
    const flights = data;
    var flight = null;
    for(var i = 0; i<flights.length; i++) {
        if (data[i].id === id) {
            flight = flights[i];
            break;
        }
    }

    const goBack = () => {
        navigate("/Flights");
    }

    const goPayment = () => {
        navigate("/informationPage");
    }

  return (
    <main className='details_main'>
            <div className="detailsContainer">
                <div className="title">
                    <span>From Lisboa to Praha</span>
                </div>
                <div className="ticket">
                    <div className="card top">
                      <div className='origin'>
                        <div className='hour'>20:30</div>
                        <div className='city'>Lisboa</div>
                      </div>
                      <div className='flight_median'>
                        <FontAwesomeIcon icon={faPlane} className="icon"/>
                      </div>
                      <div className='destination'>
                        <div className='hour'>23:50</div>
                        <div className='city'>Praha</div>
                      </div>
                      
                    </div>
                    <div className='median'></div>
                    <div className="card bottom">
                      <div className='row'>
                        <div className='item'>
                          <span className='label'>Flight:</span>
                          <span className='content'>LA0019</span>
                        </div>
                        <div className='item'>
                          <span className='label'>Ticket:</span>
                          <span className='content'>123456</span>
                        </div>
                        <div className='item'>
                          <span className='label'>Company:</span>
                          <span className='content'>Luso Airlines</span>
                        </div>
                        <div className='item'>
                          <span className='label'>Class:</span>
                          <span className='content'>Economic</span>
                        </div>
                      </div>
                      <div className='row'>
                        <div className='item'>
                          <span className='label'>Date:</span>
                          <span className='content'>09/05/2024</span>
                        </div>
                        <div className='item'>
                          <span className='label'>Traveling Time:</span>
                          <span className='content'>4h20min</span>
                        </div>
                        <div className='item'>
                          <span className='label'>Layover:</span>
                          <span className='content'>1</span>
                        </div>

                      </div>
                      
                    </div>
                </div>
                <div className='button_area'>
                  <button className='payment' onClick={goPayment}>Payment <FontAwesomeIcon icon={faShoppingCart}></FontAwesomeIcon> 200 EUR</button>
                </div>
                <button className='goBack' onClick={goBack}>go back</button>
            </div>
        </main>
  );
}

export default FlightDetails;

