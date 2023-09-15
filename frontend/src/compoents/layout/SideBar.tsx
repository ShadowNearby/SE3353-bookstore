import { Badge, Layout, Menu } from "antd";
import { Link } from "react-router-dom";
import React, { useState } from "react";
import {
  BookOutlined,
  HomeOutlined,
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  ShoppingCartOutlined,
  ShoppingOutlined,
  UserOutlined,
} from "@ant-design/icons";

const { Sider } = Layout;

export const SideBar = () => {
  const [collapsed, setCollapsed] = useState(false);
  return (
    <Sider
      trigger={null}
      collapsed={collapsed}
      collapsible
      style={{ background: "white" }}
    >
      <div style={{ margin: 20 }}>
        {collapsed ? (
          <MenuUnfoldOutlined
            size={30}
            onClick={() => setCollapsed(!collapsed)}
          />
        ) : (
          <MenuFoldOutlined
            size={30}
            onClick={() => setCollapsed(!collapsed)}
          />
        )}
      </div>
      <Menu mode="inline">
        <Menu.Item key={"1"} icon={<HomeOutlined />} onClick={() => {}}>
          <Link to={"/"}>{"主页"}</Link>
        </Menu.Item>
        <Menu.Item key={"2"} icon={<BookOutlined />} onClick={() => {}}>
          <Link to={"/books"}>{"书籍"}</Link>
        </Menu.Item>
        <Menu.Item key={"3"} icon={<ShoppingCartOutlined />} onClick={() => {}}>
          <Link to={"/cart"}>
            <span style={{ paddingRight: 10 }}>{"购物车"}</span>
            <Badge count={0} size={"small"} overflowCount={99}></Badge>
          </Link>
        </Menu.Item>
        <Menu.Item key={"4"} icon={<ShoppingOutlined />} onClick={() => {}}>
          <Link to={"/order"}>
            <span style={{ paddingRight: 10 }}>{"订单"}</span>
            <Badge count={0} size={"small"} overflowCount={99}></Badge>
          </Link>
        </Menu.Item>
        <Menu.Item key={"5"} icon={<UserOutlined />} onClick={() => {}}>
          <Link to={"/user"}>{"我的"}</Link>
        </Menu.Item>
      </Menu>
    </Sider>
  );
};
