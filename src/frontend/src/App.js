import {Route, createBrowserRouter, createRoutesFromElements, RouterProvider} from 'react-router-dom'
import Sample from './Sample';
import 'antd/dist/reset.css';
import Register from "./pages/register";

import RootLayout from './layout/RootLayout';
import Login from './pages/login';

const router = createBrowserRouter(
    createRoutesFromElements(
        <Route path='/' element={<RootLayout/>}>
            <Route index element={<Sample/>}/>
            <Route path='sample' element={<Sample/>}/>
            <Route path='login' element={<Login/>}/>
            <Route path="register" element={<Register/>}/>
        </Route>
    )
)

function App() {
    return (
        <RouterProvider router={router}/>
    );
}

export default App;
