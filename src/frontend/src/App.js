import { Route, createBrowserRouter, createRoutesFromElements, RouterProvider } from 'react-router-dom'
import Sample from './Sample';
import 'antd/dist/reset.css';
import RootLayout from './layout/RootLayout';
import Projects from './pages/projects';
import Project from './pages/project';

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path='/' element={<RootLayout />}>
      <Route index element={<Sample />} />
      <Route path='sample' element={<Sample />} />
      <Route path='projects' element={<Projects />} />
      <Route path='project/:id' element={<Project />} />
    </Route>
  )
)

function App() {
  return (
    <RouterProvider router={router} />
  );
}

export default App;
