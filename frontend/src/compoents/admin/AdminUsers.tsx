import {
  Button,
  DatePicker,
  Drawer,
  Image,
  Input,
  message,
  Radio,
  Space,
  Table,
} from "antd";
import { useEffect, useState } from "react";
import { IMessage, IUser } from "../../interface";
import moment from "moment";
import { CheckCircleTwoTone, StopTwoTone } from "@ant-design/icons";
import dayjs from "dayjs";
import { putUser } from "../../service/admin/putUser";
import { getAllUsers } from "../../service/admin/getUsers";
import { UploadImage } from "../upload/UploadImage";
import { validateEmail } from "../../utility/textCheck";

const { Column } = Table;
const sortUsersByTime = (users: IUser[]) => {
  let newUsers: IUser[] = users;
  newUsers.sort((a, b) =>
    a.registerTime > b.registerTime
      ? 1
      : a.registerTime === b.registerTime
      ? 0
      : -1
  );
  return newUsers;
};
export const AdminUsers = () => {
  const [showedUsers, setShowedUsers] = useState<IUser[]>([]);
  useEffect(() => {
    getAllUsers()
      .then((res) => sortUsersByTime(res))
      .then((res) => setShowedUsers(res));
  }, []);
  const [drawerOpen, setDrawerOpen] = useState(false);
  const [selectUsername, setSelectUsername] = useState("");
  const [selectUserId, setSelectUserId] = useState(0);
  const [selectUserAvatar, setSelectUserAvatar] = useState("");
  const [selectUserEmail, setSelectUserEmail] = useState("");
  const [selectUserRole, setSelectUserRole] = useState("");
  const [selectUserBanned, setSelectUserBanned] = useState(false);
  const [selectUserRegisterTime, setSelectUserRegisterTime] = useState(
    new Date()
  );
  const selectUser = (value: IUser) => {
    setSelectUserId(value.id);
    setSelectUsername(value.username);
    setSelectUserAvatar(value.avatar);
    setSelectUserBanned(value.banned);
    setSelectUserEmail(value.email);
    setSelectUserRole(value.role);
    setSelectUserRegisterTime(value.registerTime);
  };
  const [buttonLoading, setButtonLoading] = useState(false);
  const handleConfirm = () => {
    if (!validateEmail(selectUserEmail)) {
      message.error("邮箱格式错误", 1).then();
      return;
    }
    setButtonLoading(true);
    putUser({
      id: selectUserId,
      username: selectUsername,
      avatar: selectUserAvatar,
      registerTime: selectUserRegisterTime,
      role: selectUserRole,
      email: selectUserEmail,
      banned: selectUserBanned,
    }).then((res: IMessage) => {
      if (res.message === "修改成功") {
        getAllUsers()
          .then((res) => sortUsersByTime(res))
          .then((res) => setShowedUsers(res))
          .then(() =>
            message.success(res.message, 1).then(() => setButtonLoading(false))
          );
      } else message.error(res.message, 1).then(() => setButtonLoading(false));
    });
  };
  return (
    <>
      <Drawer open={drawerOpen} onClose={() => setDrawerOpen(false)}>
        <Space direction={"vertical"}>
          <span>{"用户名"}</span>
          <Input
            value={selectUsername}
            onChange={(e) => setSelectUsername(e.target.value)}
          ></Input>
          <span>{"邮箱"}</span>
          <Input
            value={selectUserEmail}
            onChange={(e) => setSelectUserEmail(e.target.value)}
          ></Input>
          <span>{"头像"}</span>
          <UploadImage
            state={selectUserAvatar}
            setState={setSelectUserAvatar}
          />
          <Input
            value={selectUserAvatar}
            onChange={(e) => setSelectUserAvatar(e.target.value)}
          ></Input>
          <span>{"注册时间"}</span>
          <DatePicker
            showTime={true}
            onChange={(value) => {
              if (value) setSelectUserRegisterTime(value.toDate());
            }}
            value={dayjs(selectUserRegisterTime)}
          ></DatePicker>
          <Radio.Group
            value={selectUserBanned ? 1 : 0}
            onChange={(e) => setSelectUserBanned(e.target.value)}
          >
            <Radio value={0}>{"无限制"}</Radio>
            <Radio value={1}>{"禁止"}</Radio>
          </Radio.Group>
          <Radio.Group
            value={selectUserRole}
            onChange={(e) => setSelectUserRole(e.target.value)}
          >
            <Radio value={"ADMIN"}>{"管理员"}</Radio>
            <Radio value={"USER"}>{"用户"}</Radio>
          </Radio.Group>
          <Button loading={buttonLoading} onClick={handleConfirm}>
            {"修改"}
          </Button>
        </Space>
      </Drawer>
      <Table
        dataSource={showedUsers}
        onRow={(value: IUser) => {
          return {
            onClick: () => {
              setDrawerOpen(true);
              selectUser(value);
            },
          };
        }}
      >
        <Column
          title={"用户名"}
          render={(value: IUser) => {
            return <>{value.username}</>;
          }}
        ></Column>
        <Column
          title={"头像"}
          render={(value: IUser) => {
            return (
              <Image
                src={value.avatar}
                style={{ height: 80, width: 80 }}
                preview={false}
              ></Image>
            );
          }}
        ></Column>
        <Column
          title={"邮箱"}
          render={(value: IUser) => {
            return <>{value.email}</>;
          }}
        ></Column>
        <Column
          title={"类型"}
          render={(value: IUser) => {
            return <>{value.role === "ADMIN" ? "管理员" : "用户"}</>;
          }}
        ></Column>
        <Column
          title={"注册时间"}
          render={(value: IUser) => {
            return <>{moment(value.registerTime).format("YYYY-MM-DD HH:mm")}</>;
          }}
        ></Column>
        <Column
          title={"状态"}
          render={(value: IUser) => {
            return (
              <>
                {value.banned ? (
                  <StopTwoTone twoToneColor={"red"} />
                ) : (
                  <CheckCircleTwoTone twoToneColor={"green"} size={30} />
                )}
              </>
            );
          }}
        ></Column>
      </Table>
    </>
  );
};
