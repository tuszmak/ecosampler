import { useNavigate, useOutlet } from "react-router-dom";
import useAuth from "../hook/useAuth";
import { useEffect } from "react";

const PrivateRoutes = () => {
  const outlet = useOutlet();
  const { auth } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!auth) return navigate("login");
  }, []);

  return <>{outlet}</>;
};

export default PrivateRoutes;
