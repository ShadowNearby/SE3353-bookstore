import "../static/css/register.css";
// import '../static/css/style.css'
import { Alert, Button, Form, Input, message } from "antd";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import { handleRegister } from "../service/auth/register";
import { IMessage } from "../interface";
import { validateEmail } from "../utility/textCheck";

export const RegisterView = () => {
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [rePassword, setRePassword] = useState("");
  const [email, setEmail] = useState("");
  const [disabled, setDisabled] = useState(true);
  const [messageApi, contextHolder] = message.useMessage();
  return (
    <div className="register-container">
      <div className="register-container1">
        {contextHolder}
        <h1 className="register-text">注册账户</h1>
        <Form className="register-form">
          <Input
            type="text"
            required
            placeholder="账号"
            className="register-textinput input"
            value={username}
            onChange={(e) => {
              setUsername(e.target.value);
              e.target.value && password && rePassword && validateEmail(email)
                ? setDisabled(false)
                : setDisabled(true);
            }}
          />
          <Input
            type="password"
            required
            placeholder="密码"
            className="register-textinput1 input"
            value={password}
            onChange={(e) => {
              setPassword(e.target.value);
              e.target.value && rePassword && username && validateEmail(email)
                ? setDisabled(false)
                : setDisabled(true);
            }}
          />
          <Input
            type="password"
            required
            placeholder="重新输入密码"
            className="register-textinput2 input"
            value={rePassword}
            onChange={(e) => {
              setRePassword(e.target.value);
              e.target.value && password && username && validateEmail(email)
                ? setDisabled(false)
                : setDisabled(true);
            }}
          />
          <Input
            type="email"
            required
            placeholder="邮箱"
            className="register-textinput3 input"
            value={email}
            onChange={(e) => {
              setEmail(e.target.value);
              rePassword &&
              password &&
              username &&
              validateEmail(e.target.value)
                ? setDisabled(false)
                : setDisabled(true);
            }}
          />
          <Button
            className="register-button button"
            disabled={disabled}
            onClick={() => {
              if (password !== rePassword) {
                messageApi
                  .error(
                    <Alert message={"两次输入密码不一致"} type={"error"} />,
                    2
                  )
                  .then(() => setRePassword(""));
                return;
              }
              if (!validateEmail(email)) {
                messageApi
                  .error(<Alert message={"邮箱不符合格式"} type={"error"} />, 2)
                  .then(() => setEmail(""));
                return;
              }
              handleRegister(username, password, email).then(
                (res: IMessage) => {
                  if (res.message !== "注册成功") {
                    messageApi
                      .error(<Alert message={res.message} type={"error"} />, 2)
                      .then();
                  } else {
                    messageApi
                      .success(
                        <Alert message={"注册成功"} type={"success"} />,
                        1
                      )
                      .then(() => {
                        // localStorage.setItem("username", username);
                        navigate("/");
                      });
                  }
                }
              );
            }}
          >
            注册
          </Button>
          <div className="register-container2">
            <span className="register-text1">
              <span>
                <Link to={"/login"} style={{ color: "black" }}>
                  {"已有账户？登录"}
                </Link>
              </span>
            </span>
          </div>
        </Form>
      </div>
    </div>
  );
};
