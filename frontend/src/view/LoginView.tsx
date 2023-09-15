import "../static/css/login.css";
import "../static/css/style.css";
import { Button, Form, Input, message } from "antd";
import { Link, useNavigate } from "react-router-dom";
import { useContext, useState } from "react";
import { handleLogin } from "../service/auth/login";
import { LoadingOutlined } from "@ant-design/icons";
import { ICurrentUserContext } from "../interface";
import { UserContext } from "../context/UserProvider";

interface ILogin {
  userId: number;
  username: string;
  userType: string;
  userAvatar: string;
  message: string;
  state: number;
}

export const LoginView = () => {
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [disabled, setDisabled] = useState(true);
  const { updateUser } = useContext(UserContext) as ICurrentUserContext;
  return (
    <div className="login-container">
      <div className="login-container1">
        <h1 className="login-text">密码登录</h1>
        <Form className="login-form">
          <Input
            type="text"
            placeholder="账号"
            required
            className="login-textinput input"
            value={username}
            onChange={(event) => {
              setUsername(event.target.value);
              !event.target.value && password
                ? setDisabled(true)
                : setDisabled(false);
            }}
          />
          <Input.Password
            type="password"
            placeholder="密码"
            required
            className="input login-textinput1 "
            value={password}
            onChange={(event) => {
              setPassword(event.target.value);
              !event.target.value && username
                ? setDisabled(true)
                : setDisabled(false);
            }}
          />
          <Button
            className=" login-button "
            disabled={disabled}
            onClick={() => {
              handleLogin(username, password).then((res: ILogin) => {
                if (res.state === 200) {
                  message
                    .success(
                      <span>
                        {res.message}
                        <LoadingOutlined />
                      </span>,
                      1
                    )
                    .then(() => {
                      updateUser({
                        id: res.userId,
                        username: res.username,
                        avatar: res.userAvatar,
                      });
                      res.userType === "USER"
                        ? navigate("/")
                        : navigate("/admin/books");
                    });
                } else {
                  message.error(res.message, 2).then(() => setPassword(""));
                }
              });
            }}
          >
            登录
          </Button>
          <div className="login-container2">
            <span className="login-text1">
              <Link to={"/register"} style={{ color: "black" }}>
                立即注册
              </Link>
            </span>
            <span className="login-text4">
              <Link to={"/forget"} style={{ color: "black" }}>
                忘记密码
              </Link>
            </span>
          </div>
        </Form>
      </div>
    </div>
  );
};
