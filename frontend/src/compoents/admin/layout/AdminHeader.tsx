import { Layout, Menu } from "antd";
import {
  BookOutlined,
  CloseCircleOutlined,
  LineChartOutlined,
  ShoppingCartOutlined,
  StarOutlined,
  UserOutlined,
} from "@ant-design/icons";
import { Link, useNavigate } from "react-router-dom";
import React, { useContext, useEffect } from "react";
import { checkAdmin } from "../../../service/admin/checkAdmin";
import { logout } from "../../../service/auth/logout";
import { UserContext } from "../../../context/UserProvider";
import { ICurrentUserContext } from "../../../interface";

interface ICheck {
  auth: boolean;
}

export const AdminHeader = () => {
  const navigate = useNavigate();
  const { user } = useContext(UserContext) as ICurrentUserContext;
  useEffect(() => {
    if (user.id === 0) navigate("/login");
    checkAdmin().then((res: ICheck) => {
      if (!res.auth) navigate("/login");
    });
  }, []);
  return (
    <Layout.Header>
      <Menu mode={"horizontal"}>
        <Menu.Item key={"1"} icon={<BookOutlined />} onClick={() => {}}>
          <Link to={"/admin/books"}>{"书籍管理"}</Link>
        </Menu.Item>
        <Menu.Item key={"2"} icon={<UserOutlined />} onClick={() => {}}>
          <Link to={"/admin/users"}>{"用户管理"}</Link>
        </Menu.Item>
        <Menu.Item key={"3"} icon={<ShoppingCartOutlined />} onClick={() => {}}>
          <Link to={"/admin/orders"}>{"订单管理"}</Link>
        </Menu.Item>
        <Menu.Item key={"4"} icon={<LineChartOutlined />} onClick={() => {}}>
          <Link to={"/admin/chart"}>{"统计图表"}</Link>
        </Menu.Item>
        <Menu.Item key={"5"} icon={<StarOutlined />} onClick={() => {}}>
          <Link to={"/"}>{"网站主页"}</Link>
        </Menu.Item>
        <Menu.Item icon={<CloseCircleOutlined />} onClick={() => logout()}>
          <Link to={"/login"}>{"登出"}</Link>
        </Menu.Item>
      </Menu>
    </Layout.Header>
  );
};
