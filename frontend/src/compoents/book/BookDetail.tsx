import React, { useState } from "react";
import { Button, Image, InputNumber, message, Modal, Space } from "antd";
import { IBook } from "../../interface";
import { addOrderItem } from "../../service/add/addOrderItem";
import { addOrder } from "../../service/add/addOrder";

interface Props {
  book: IBook;
}

export const BookDetail = ({ book }: Props) => {
  const [count, setCount] = useState(1);
  const [messageApi, contextHolder] = message.useMessage();
  const [confirmOrderOpen, setConfirmOrderOpen] = useState(false);
  const [confirmOrderLoading, setConfirmOrderLoading] = useState(false);
  const handleConfirmOrderOk = () => {
    setConfirmOrderLoading(true);
    addOrderItem(book.id, count).then((res: number) => {
      addOrder([res]).then(() => {
        setConfirmOrderOpen(false);
        setConfirmOrderLoading(false);
        messageApi.success("购买成功", 1).then();
      });
    });
  };
  const [cartButtonLoading, setCartButtonLoading] = useState(false);
  return (
    <Space align={"start"}>
      <Image src={book.image} />
      <div style={{ paddingLeft: 40 }}>
        {contextHolder}
        <h1
          style={{
            color: "black",
            fontSize: 35,
            fontFamily: "Verdana, Simsun",
            fontWeight: "bold",
          }}
        >
          {book.name}
        </h1>
        <h2 style={{ fontSize: 18, fontWeight: "normal", width: "60%" }}>
          {book.description}
        </h2>
        <p style={{ fontSize: 15 }}>
          {book.author}
          {" 著"}
        </p>
        <p style={{ fontSize: 10 }}>
          {"ISBN："}
          {book.isbn}
        </p>
        <p style={{ fontSize: 15 }}>
          {"剩余 "}
          {book.inventory}
          {" 本"}
        </p>
        <p style={{ color: "red" }}>
          <span style={{ fontSize: 10 }}>{"￥"}</span>
          <span style={{ fontSize: 20 }}>{book.price}</span>
        </p>
        <Space>
          <InputNumber
            value={count}
            onChange={(value) => {
              value && setCount(value);
            }}
            min={1}
            max={book.inventory}
            defaultValue={1}
          ></InputNumber>
          <Button
            style={{ color: "white" }}
            type={"primary"}
            danger
            onClick={() => {
              setConfirmOrderOpen(true);
            }}
          >
            {"购买"}
          </Button>
          <Button
            loading={cartButtonLoading}
            onClick={() => {
              setCartButtonLoading(true);
              addOrderItem(book.id, count).then(() => {
                messageApi
                  .success(`添加 ${count} 本 ${book.name} 至购物车`, 1)
                  .then(() => setCartButtonLoading(false));
              });
            }}
          >
            {"加入购物车"}
          </Button>
          <Modal
            open={confirmOrderOpen}
            confirmLoading={confirmOrderLoading}
            onOk={handleConfirmOrderOk}
            onCancel={() => setConfirmOrderOpen(false)}
            okText={"确认"}
            cancelText={"取消"}
            title={"确认订单"}
          >
            <p>{`${book.name} ￥${book.price} x${count}`}</p>
            <p>{`共计 ￥${(book.price * count).toFixed(2)}`}</p>
          </Modal>
        </Space>
      </div>
    </Space>
  );
};
