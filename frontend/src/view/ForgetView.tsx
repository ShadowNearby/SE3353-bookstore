import "../static/css/register.css";
// import '../static/css/style.css'
import { Alert, Button, Form, Input, message } from "antd";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { handleForget } from "../service/auth/forget";
import { IMessage } from "../interface";
import { validateEmail } from "../utility/textCheck";

export const ForgetView = () => {
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
        <h1 className="register-text">修改密码</h1>
        <Form className="register-form">
          <Input
            type="text"
            required
            placeholder="账号"
            className="register-textinput input"
            value={username}
            onChange={(e) => {
              setUsername(e.target.value);
              !e.target.value && password && rePassword && validateEmail(email)
                ? setDisabled(true)
                : setDisabled(false);
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
                ? setDisabled(true)
                : setDisabled(false);
            }}
          />
          <Input
            type="password"
            required
            placeholder="新密码"
            className="register-textinput1 input"
            value={password}
            onChange={(e) => {
              setPassword(e.target.value);
              !e.target.value && rePassword && username && validateEmail(email)
                ? setDisabled(true)
                : setDisabled(false);
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
              !e.target.value && password && username && validateEmail(email)
                ? setDisabled(true)
                : setDisabled(false);
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
              handleForget(username, password, email).then((res: IMessage) => {
                if (res.message !== "OK") {
                  messageApi
                    .error(<Alert message={res.message} type={"error"} />, 2)
                    .then();
                } else {
                  messageApi
                    .success(<Alert message={"注册成功"} type={"success"} />, 1)
                    .then(() => {
                      navigate("/");
                    });
                }
              });
            }}
          >
            修改
          </Button>
        </Form>
      </div>
    </div>
  );
};
