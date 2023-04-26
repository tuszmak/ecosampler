import { Routes, Route } from 'react-router-dom'
import Sample from './Sample';
import { Button } from 'antd';
import 'antd/dist/reset.css';

function App() {
  return (
    <div className="App">
      <h1>Project Here</h1>
      <Button type="primary">Button</Button>
      <Routes>
        <Route path='/' element={<Sample />} />
      </Routes>
    </div>
  );
}

export default App;
