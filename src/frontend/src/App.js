import {Routes, Route} from 'react-router-dom'
import Sample from './Sample';
import {Button} from 'antd';
import 'antd/dist/reset.css';
import Login from "./Login";
import Register from "./Register";

function App() {
    return (
        <div className="App">
            <h1>Project Here</h1>
            <Button type="primary" onClick={(e) => console.log(e.target.value)}>Button</Button>
            <Routes>
                <Route path='/' element={<Sample/>}/>
                <Route path={"/login"} element={<Login/>}/>
                <Route path={"register"} element={<Register/>}/>
            </Routes>
        </div>
    );
}

export default App;
