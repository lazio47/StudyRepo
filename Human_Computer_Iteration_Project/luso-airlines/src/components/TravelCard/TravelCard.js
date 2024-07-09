import React, { useState } from "react";
import Autosuggest from 'react-autosuggest';
import { useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import "./TravelCard.css";

const TravelCard = () => {
  const navigate = useNavigate();

  const [departureCity, setDepartureCity] = useState("");
  const [arrivalCity, setArrivalCity] = useState("");
  const [tripType, setTripType] = useState("one-way");
  const [departureDate, setDepartureDate] = useState("");
  const [returnDate, setReturnDate] = useState("");
  const [suggestions, setSuggestions] = useState([]);
  const [error, setError] = useState("");

  const today = new Date().toISOString().split("T")[0]; // Get today's date in YYYY-MM-DD format

  const getSuggestions = async (value) => {
    const accessToken = 'pk.eyJ1IjoiYnJ1bm90YXZhcmVzIiwiYSI6ImNsdzVlM3NrazBxdjIybXJ6ZDdhbW9ieHcifQ.Qgq9JIynComyPAqb1vunsA'; // Replace with your access token
    const response = await fetch(`https://api.mapbox.com/geocoding/v5/mapbox.places/${value}.json?access_token=${accessToken}&types=place&limit=5`);
    const data = await response.json();
    const cities = data.features.map(feature => ({
      name: feature.text,
      country: feature.context.find(c => c.id.startsWith('country')).text
    }));
    setSuggestions(cities);
  };

  const renderSuggestion = (suggestion) => (
    <div>
      {suggestion.name}, {suggestion.country}
    </div>
  );

  const inputPropsDeparture = {
    placeholder: 'Departure City',
    value: departureCity,
    onChange: (event, { newValue }) => setDepartureCity(newValue)
  };

  const inputPropsArrival = {
    placeholder: 'Arrival City',
    value: arrivalCity,
    onChange: (event, { newValue }) => setArrivalCity(newValue)
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (tripType === "round-trip" && returnDate < departureDate) {
      setError("Return date must be after the departure date.");
      return;
    }
    setError("");
    navigate("/Flights");
  };

  const addCity = () => {
    setSuggestions([...suggestions, { city: "", date: "" }]);
  };

  const removeCity = (index) => {
    if (suggestions.length > 1) {
      const updatedSuggestions = [...suggestions];
      updatedSuggestions.splice(index, 1);
      setSuggestions(updatedSuggestions);
    }
  };

  return (
    <div className="travel-card-container">
      <div className="form-container">
        <form onSubmit={handleSubmit}>
          <label>
            Trip Type:
            <select 
              value={tripType} 
              onChange={(event) => setTripType(event.target.value)}
              style={{ width: '655px' }} // Set desired width here
            >
              <option value="one-way">One Way</option>
              <option value="round-trip">Round Trip</option>
              <option value="multi-city">Multi-City</option>
            </select>
          </label>

          <div className="row">
            <div>
              <label>Departure City:</label>
              <Autosuggest
                suggestions={suggestions}
                onSuggestionsFetchRequested={({ value }) => getSuggestions(value)}
                onSuggestionsClearRequested={() => setSuggestions([])}
                getSuggestionValue={(suggestion) => suggestion.name}
                renderSuggestion={renderSuggestion}
                inputProps={inputPropsDeparture}
              />
            </div>
            <div>
              <label>Arrival City:</label>
              <Autosuggest
                suggestions={suggestions}
                onSuggestionsFetchRequested={({ value }) => getSuggestions(value)}
                onSuggestionsClearRequested={() => setSuggestions([])}
                getSuggestionValue={(suggestion) => suggestion.name}
                renderSuggestion={renderSuggestion}
                inputProps={inputPropsArrival}
              />
            </div>
          </div>

          <div className="row">
            <div>
              <label>Departure Date:</label>
              <input
                type="date"
                value={departureDate}
                onChange={(event) => setDepartureDate(event.target.value)}
                min={today} // Set the minimum date to today
              />
            </div>

            {tripType === "round-trip" && (
              <div>
                <label>Return Date:</label>
                <input
                  type="date"
                  value={returnDate}
                  onChange={(event) => setReturnDate(event.target.value)}
                  min={departureDate || today} // Set the minimum date to the departure date or today
                />
              </div>
            )}

            {tripType === "multi-city" && (
              <div>
                <label>Return Date:</label>
                <input
                  type="date"
                  value={returnDate}
                  onChange={(event) => setReturnDate(event.target.value)}
                  min={departureDate || today} // Set the minimum date to the departure date or today
                />
              </div>
            )}
          </div>

          {error && <p className="error-message">{error}</p>}

          {tripType === "multi-city" && (
            <div>
              {suggestions.map((city, index) => (
                <div key={index} className="city-row">
                  <label>
                    City {index + 1}:
                    <input
                      type="text"
                      value={city.city}
                      onChange={(event) =>
                        console.log("Implement handleCityChange logic")
                      }
                    />
                  </label>

                  <label>
                    Travel Date {index + 1}:
                    <input
                      type="date"
                      value={city.date}
                      onChange={(event) =>
                        console.log("Implement handleCityChange logic")
                      }
                      className="small-input"
                      min={today} // Set the minimum date to today for multi-city travel dates
                    />
                  </label>

                  {suggestions.length > 1 && ( // Check if there is more than one city
                    <button
                      type="button"
                      onClick={() => removeCity(index)}
                      className="remove-button"
                      style={{ width: '110px' }} // Set desired width here
                    >
                      Remove&nbsp; <FontAwesomeIcon icon={faTrash} />

                    </button>
                  )}
                </div>
              ))}

              <div className="button-container">
                <button type="button" onClick={addCity}>
                  Add City
                </button>
                <button type="submit" className="search-button">
                  Search
                </button>
              </div>
            </div>
          )}
          {tripType !== "multi-city" && (
            <div className="search-button-container">
              <button type="submit" className="search-button">
                Search
              </button>
            </div>
          )}
        </form>
      </div>
    </div>
  );
};

export default TravelCard;
