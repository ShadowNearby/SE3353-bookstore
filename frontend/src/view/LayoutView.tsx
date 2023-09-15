import { SideBar } from "../compoents/layout/SideBar";
import { Layout } from "antd";
import { HeadBar } from "../compoents/layout/HeadBar";
import { FootBar } from "../compoents/layout/FootBar";
import React from "react";
import { Outlet } from "react-router";

export const LayoutView = () => {
  return (
    <Layout>
      <SideBar />
      <Layout>
        <HeadBar />
        <Layout.Content style={{ marginLeft: 10 }}>
          <Outlet />
        </Layout.Content>
        <FootBar />
      </Layout>
    </Layout>
  );
};
