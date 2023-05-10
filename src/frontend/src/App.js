import {Route, createBrowserRouter, createRoutesFromElements, RouterProvider} from 'react-router-dom'
import Sample from './Sample';
import 'antd/dist/reset.css';
import Register from "./pages/register";

import RootLayout from './layout/RootLayout';
import Login from './pages/login';
import Projects from './pages/projects';
import Project from './pages/project';
import { CreateForm } from './component/formComponent/CreateForm';

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path='/' element={<RootLayout />}>
      <Route index element={<Sample />} />
      <Route path='sample' element={<Sample />} />
      <Route path="register" element={<Register/>}/>
      <Route path='login' element={<Login />} />
      <Route path='projects' element={<Projects />} />
      <Route path='project/:id' element={<Project />} />
      <Route path='create-form' element={<CreateForm />} />
    </Route>
  )
)

function App() {
    return (
        <RouterProvider router={router}/>
    );
}

export default App;
