import {Layout, Menu} from 'antd';
import {useNavigate} from 'react-router-dom';

const { Header } = Layout;

const HeaderComponent = () => {
  const navigate = useNavigate();
  return (  
    <Header>
      <div className="logo" />
      <Menu
        theme="dark"
        mode="horizontal"
        defaultSelectedKeys={['home']}
        items={
          [
            {key:"home", label:"Home",onClick:()=>{navigate('/')} },
            {key:"projects", label:"Projects",onClick:()=>{navigate('/projects')} },
            {key:"register", label:"Register",onClick:()=>{navigate('/register')} },
            {key:"login", label:"Login",onClick:()=>{navigate('/login')} }
          ]
        }
      />
    </Header>
  );
}

export default HeaderComponent;
