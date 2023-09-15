import { AdminHeader } from "../../compoents/admin/layout/AdminHeader";
import { Layout } from "antd";
import { Outlet } from "react-router";

export const AdminLayout = () => {
  return (
    <Layout>
      <AdminHeader />
      <Layout.Content
        style={{ marginLeft: "5%", marginRight: "5%", marginTop: 20 }}
      >
        <Outlet />
      </Layout.Content>
    </Layout>
  );
};
