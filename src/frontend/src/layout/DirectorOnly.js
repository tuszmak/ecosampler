import { useNavigate, useOutlet } from "react-router-dom";
import useAuth from "../hook/useAuth";
import { useEffect } from "react";

const DirectorOnly = () => {
  const outlet = useOutlet();
  const { auth } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (auth?.role !== "DIRECTOR") return navigate("/");
  }, []);

  return <>{outlet}</>;
};

export default DirectorOnly;
