import { Outlet } from "react-router-dom";
import HeaderComponent from '../component/HeaderComponent'

import { Breadcrumb, Layout, theme } from 'antd';
const { Content, Footer } = Layout;

const RootLayout = () => {

  const {
    token: { colorBgContainer },
  } = theme.useToken();
  return (
    <div className="root-layout">
      <Layout className="layout">
        <HeaderComponent />
        <Content
          style={{
            padding: '0 50px',
            theme: 'dark'
          }}
        >
          <Breadcrumb
            style={{
              margin: '16px 0',
            }}
            items={[{ title: 'Home' }, { title: 'List' }, { title: 'App' }]}
          >
          </Breadcrumb>
          <div
            className="site-layout-content"
            style={{
              background: colorBgContainer,
            }}
          >
            Content
            <Outlet /> {/* router Outlet serve to render the routes */}
          </div>
        </Content>
        <Footer
          style={{
            textAlign: 'center',
          }}
        >
          Ant Design ©2023 Created by Ant UED
        </Footer>
      </Layout>
    </div>
  );
}

export default RootLayout;
