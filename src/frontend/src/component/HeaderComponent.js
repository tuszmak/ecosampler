import { Layout, Menu } from "antd";
import { useNavigate } from "react-router-dom";
import useAuth from "../hook/useAuth";
const { Header } = Layout;

const HeaderComponent = () => {
  const navigate = useNavigate();
  const { auth, setAuth } = useAuth();
  const handleLogOut = () => {
    setAuth(null);
    localStorage.removeItem("user");
    navigate("/");
  };
  const publicItems = [
    {
      key: "home",
      label: "Home",
      onClick: () => {
        navigate("/");
      },
    },
  ];
  const authNeed = [
    {
      key: "projects",
      label: "Projects",
      onClick: () => {
        navigate("/projects");
      },
    },
    {
      key: "logout",
      label: "LogOut",
      onClick: () => {
        handleLogOut();
      },
    },
  ];
  const showOnlyWhenNoAuth = [
    {
      key: "login",
      label: "Login",
      onClick: () => {
        navigate("/login");
      },
    },
  ];
  const onlyDirector = [
    {
      key: "register",
      label: "Register",
      onClick: () => {
        navigate("/register");
      },
    },
  ];
  const menuItems = () => {
    if (auth?.role === "DIRECTOR")
      return [...publicItems, ...onlyDirector, ...authNeed];
    if (auth) return [...publicItems, ...authNeed];
    return [...publicItems, ...showOnlyWhenNoAuth];
  };

  return (
    <Header>
      <div className="logo" />
      <Menu
        theme="dark"
        mode="horizontal"
        defaultSelectedKeys={["home"]}
        items={menuItems()}
      />
    </Header>
  );
};

export default HeaderComponent;
