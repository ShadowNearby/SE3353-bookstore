import { Button, Input, message, Radio, Space } from "antd";
import React, { useContext, useEffect, useState } from "react";
import { ICurrentUserContext, IUser } from "../interface";
import { getUser } from "../service/get/personal/getUser";
import "../static/css/profile.css";
import { LoadingOutlined } from "@ant-design/icons";
import moment from "moment";
import { putUser } from "../service/admin/putUser";
import { UploadImage } from "../compoents/upload/UploadImage";
import assert from "assert";
import { validateEmail } from "../utility/textCheck";
import { UserContext } from "../context/UserProvider";

export const UserView = () => {
  const { user, updateUser } = useContext(UserContext) as ICurrentUserContext;
  const getUserAndSet = async () => {
    await getUser().then((res: IUser) => {
      setCurrUser(res);
      setUsername(res.username);
      setEmail(res.email);
      setRegisterTime(new Date(res.registerTime));
      setAvatar(res.avatar);
    });
  };
  const [currUser, setCurrUser] = useState<IUser>();
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [registerTime, setRegisterTime] = useState(new Date());
  const [avatar, setAvatar] = useState("");
  useEffect(() => {
    getUserAndSet().then();
  }, []);
  const handleOnClick = () => {
    assert(currUser !== undefined);
    if (!validateEmail(email)) {
      message.error("邮箱格式不正确", 1).then();
      return;
    }
    putUser({
      id: currUser.id,
      username: username,
      avatar: avatar,
      registerTime: currUser.registerTime,
      role: currUser.role,
      email: email,
      banned: currUser.banned,
    }).then((res) => {
      if (res.message === "修改成功") {
        updateUser({ ...user, username: username, avatar: avatar });
        getUserAndSet().then(() => message.success(res.message, 1));
      } else message.error(res.message, 1).then();
    });
  };
  return (
    <>
      {currUser ? (
        <Space direction={"vertical"} style={{ marginLeft: 20 }}>
          <UploadImage state={avatar} setState={setAvatar} />
          <Input
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            addonBefore={"账户"}
            className={"profile-textinput"}
          ></Input>
          <Input
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            addonBefore={"邮箱"}
            className={"profile-textinput"}
          ></Input>
          {/*<Input*/}
          {/*  value={avatar}*/}
          {/*  onChange={(e) => setAvatar(e.target.value)}*/}
          {/*  addonBefore={"头像"}*/}
          {/*  className={"profile-textinput"}*/}
          {/*></Input>*/}
          <Input
            value={moment(registerTime).format("YYYY-MM-DD HH:mm")}
            disabled
            addonBefore={"注册时间"}
            className={"profile-textinput"}
          ></Input>
          <Radio.Group
            value={currUser.role === "ADMIN" ? 0 : 1}
            style={{ marginTop: 12 }}
          >
            <Radio value={0} disabled={currUser.role === "USER"}>
              {"管理员"}
            </Radio>
            <Radio value={1} disabled={currUser.role === "ADMIN"}>
              {"用户"}
            </Radio>
          </Radio.Group>
          <Space style={{ marginTop: 16 }}>
            <Button type={"primary"} onClick={handleOnClick}>
              {"修改"}
            </Button>
            <Button
              danger
              onClick={() => {
                setUsername(currUser.username);
                setEmail(currUser.email);
              }}
            >
              {"取消"}
            </Button>
          </Space>
        </Space>
      ) : (
        <LoadingOutlined />
      )}
    </>
  );
};
