import { createContext, useEffect, useState } from "react";

const AuthContext = createContext({});

export const AuthProvider = ({ children }) => {
  const [auth, setAuth] = useState(getAuthInitialValue());

  function getAuthInitialValue() {
    const user = JSON.parse(localStorage.getItem("user"));
    if (user) return user;
    return null;
  }

  return (
    <AuthContext.Provider value={{ auth, setAuth }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthContext;
