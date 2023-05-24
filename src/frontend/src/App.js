import {createBrowserRouter, createRoutesFromElements, Route, RouterProvider,} from "react-router-dom";
import "antd/dist/reset.css";
import Register from "./pages/register";

import RootLayout from "./layout/RootLayout";
import Login from "./pages/login";
import Projects from "./pages/projects";
import Project from "./pages/project";
import AuthLayout from "./layout/AuthLayout";
import {CreateForm} from "./pages/project/CreateForm";
import SampleData from "./pages/sampledata";
import Form from "./pages/form";
import Homepage from "./component/Homepage";
import NotFound from "./pages/error/NotFound";


const router = createBrowserRouter(
  createRoutesFromElements(
    <Route element={<AuthLayout />}>
      <Route path="/" element={<RootLayout />}>
        <Route index element={<Homepage />} />
        <Route path="*" element={<NotFound />} />
        <Route path="register" element={<Register />} />
        <Route path="login" element={<Login />} />
        <Route path="projects" element={<Projects />} />
        <Route path="project/:id" element={<Project />} />
        <Route path='create-form/:id' element={<CreateForm />} />
        <Route path="form/:id" element={<Form />} />
        <Route path="survey/:formID" element={<SampleData />} />
      </Route>
    </Route>
  )
);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
