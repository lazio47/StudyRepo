// App.js
import React from 'react';
import { BrowserRouter as Router } from "react-router-dom"
import DefaultRoutes from './routes/DefaultRoutes';
import DefaultLayout from './layouts/DefaultLayout';



function App() {
  return (
    <Router>
      <DefaultLayout>
        <DefaultRoutes/>
      </DefaultLayout>
    </Router>
  );
}

export default App;