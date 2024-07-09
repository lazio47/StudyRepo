import React, { useEffect, useState } from 'react';
import './Navbar.css';
import { useNavigate, useLocation } from 'react-router-dom';
import { useLocalStorage } from '@rehooks/local-storage';
import { Link } from 'react-router-dom';

function Navbar() {
  const navigate = useNavigate();
  const location = useLocation();
  const [lusoAirlinesCurrentUser, setLusoAirlinesCurrentUser] = useLocalStorage("lusoAirlinesCurrentUser");
  const [userIsLogged, setUserIsLogged] = useState(false);

  useEffect(() => {
    setUserIsLogged(!!lusoAirlinesCurrentUser);
  }, [lusoAirlinesCurrentUser]);

  const handleLogoButton = () => {
    navigate("/");
  };

  const handleLoginButton = () => {
    navigate("/loginPage");
  };

  const handleLogoutButton = () => {
    setLusoAirlinesCurrentUser(null);
  };

  return (
    <div className='Navbar'>
      <nav>
        <h2 onClick={handleLogoButton}>Luso Airlines</h2>
        <div className="navbar">
          <ul>
            <li><Link to="/ReserveFlight" className={location.pathname === '/ReserveFlight' ? 'active' : ''}>Reserve Flight</Link></li>
            <li><Link to="/checkinPage" className={location.pathname === '/checkinPage' ? 'active' : ''}>Check-in Online</Link></li>
            <li><Link to="/MyTickets" className={location.pathname === '/MyTickets' ? 'active' : ''}>My Tickets</Link></li>
            <li><Link to="/ProfilePage" className={location.pathname === '/ProfilePage' ? 'active' : ''}>My Account</Link></li>
          </ul>
        </div>
        {userIsLogged ? (
          <button onClick={handleLogoutButton} type="button" className="connection">Logout</button>
        ) : (
          <button onClick={handleLoginButton} type="button" className="connection">Login</button>
        )}
      </nav>
    </div>
  );
}

export default Navbar;
