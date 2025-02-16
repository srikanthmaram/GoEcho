
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Echochat from './Components/Echochat';
import Home from './Components/Home';

function App() {
  return (
    <div className="App">
     <Router>
      
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/echochat" element={<Echochat />} />
      </Routes>
      
    </Router>
    </div>
  );
}

export default App;
