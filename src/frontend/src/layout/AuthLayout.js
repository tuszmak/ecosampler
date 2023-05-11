import { useLoaderData, useOutlet } from "react-router-dom";
import { AuthProvider } from "../context/AuthProvider";

const AuthLayout = () => {
  const outlet = useOutlet();

  return <AuthProvider>{outlet}</AuthProvider>;
};

export default AuthLayout;
