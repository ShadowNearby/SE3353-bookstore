import { Avatar, Dropdown, Layout, message } from "antd";
import React, { useContext, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { logout } from "../../service/auth/logout";
import { checkSession } from "../../service/auth/checkSession";
import { UserContext } from "../../context/UserProvider";
import { ICurrentUserContext } from "../../interface";

interface ICheck {
  auth: boolean;
}

const { Header } = Layout;
export const HeadBar = () => {
  const { user } = useContext(UserContext) as ICurrentUserContext;
  const navigate = useNavigate();
  useEffect(() => {
    checkSession().then((res: ICheck) => {
      if (!res.auth) navigate("/login");
    });
  }, []);
  const items = [
    {
      key: "1",
      label: (
        <Link
          to={"/login"}
          onClick={async () => {
            const msg = await logout();
            message.info(`登录时间${msg.message}`);
          }}
        >
          {`登出`}
        </Link>
      ),
    },
  ];
  return (
    <Header
      style={{
        position: "sticky",
        zIndex: 1,
        width: "100%",
        flexDirection: "row",
        textAlign: "right",
      }}
    >
      <Dropdown placement={"bottomRight"} arrow menu={{ items }}>
        <Avatar src={user.avatar} size={40}></Avatar>
      </Dropdown>
    </Header>
  );
};
