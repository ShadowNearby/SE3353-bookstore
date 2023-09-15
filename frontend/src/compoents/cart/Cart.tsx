import { Avatar, Button, Checkbox, message, Modal, Spin, Table } from "antd";
import { IBook, ICurrentUserContext, IOrderItem } from "../../interface";
import { deleteOrderItem } from "../../service/delete/deleteOrderItem";
import React, { useEffect, useRef, useState } from "react";
import { getOrderItemInCart } from "../../service/get/personal/getOrderItem";
import { addOrder } from "../../service/add/addOrder";
import { UserContext } from "../../context/UserProvider";

const { Column } = Table;
export const Cart = () => {
  const { user } = React.useContext(UserContext) as ICurrentUserContext;
  const ws = useRef<WebSocket | null>(null);
  useEffect(() => {
    ws.current = new WebSocket(`ws://localhost:8080/order/${user.id}`);
    ws.current.onmessage = (e) => {
      message.info(e.data).then(() => getOrderItem());
    };
    ws.current.onerror = (e) => console.error(e);
    return () => {
      ws.current?.close();
    };
  }, [ws]);
  const getOrderItem = async () => {
    await getOrderItemInCart().then((res: IOrderItem[]) => {
      setOrderItemList(res);
    });
  };
  const [orderItemList, setOrderItemList] = useState<IOrderItem[]>([]);
  useEffect(() => {
    getOrderItem().then();
  }, []);
  const [messageApi, contextHolder] = message.useMessage();
  const [selectOrderItemList, setSelectOrderItemList] = useState<
    Set<IOrderItem>
  >(new Set<IOrderItem>());
  const [confirmOrderOpen, setConfirmOrderOpen] = useState(false);
  const [confirmOrderLoading, setConfirmOrderLoading] = useState(false);
  const handleConfirmOrderOk = async () => {
    setConfirmOrderLoading(true);
    let selectOrderItemIds: number[] = [];
    selectOrderItemList.forEach((value: IOrderItem) =>
      selectOrderItemIds.push(value.id)
    );
    addOrder(selectOrderItemIds).then(() => {
      getOrderItem().then(() => {
        setConfirmOrderOpen(false);
        setConfirmOrderLoading(false);
        message.success("订单处理中，请稍后");
      });
    });
  };
  const handleConfirmOrderCancel = async () => {
    setConfirmOrderOpen(false);
  };
  const [orderSum, setOrderSum] = useState(0);
  const confirmOrderSum = async () => {
    let sum = 0;
    Array.from(selectOrderItemList).map(
      (value: IOrderItem) => (sum += value.count * value.book.price)
    );
    setOrderSum(sum);
  };

  return (
    <>
      {contextHolder}
      {orderItemList ? (
        <>
          <Table dataSource={orderItemList}>
            <Column
              title={"选择"}
              render={(orderItem: IOrderItem) => (
                <Checkbox
                  onChange={() => {
                    let newList = selectOrderItemList;
                    newList.has(orderItem)
                      ? newList.delete(orderItem)
                      : newList.add(orderItem);
                    setSelectOrderItemList(newList);
                  }}
                ></Checkbox>
              )}
            ></Column>
            <Column
              title={"图书信息"}
              key={"book"}
              dataIndex={"book"}
              render={(book: IBook) => (
                <div>
                  <Avatar src={book.image} shape={"square"} size={50}></Avatar>
                  {book.name}
                </div>
              )}
            ></Column>
            <Column
              title={"单价"}
              key={"book"}
              dataIndex={"book"}
              render={(book: IBook) => (
                <span style={{ fontSize: 15 }}>
                  {"￥"}
                  {book.price}
                </span>
              )}
            ></Column>
            <Column title={"数量"} key={"count"} dataIndex={"count"}></Column>
            <Column
              title={"总价"}
              render={(orderItem: IOrderItem) => (
                <span style={{ color: "red", fontSize: 18 }}>
                  {"￥"}
                  {orderItem.count * orderItem.book.price}
                </span>
              )}
            ></Column>
            <Column
              render={(orderItem: IOrderItem) => (
                <Button
                  danger
                  onClick={() => {
                    deleteOrderItem(orderItem.id).then(() => {
                      messageApi
                        .success("成功删除", 1)
                        .then(() => getOrderItem());
                    });
                  }}
                >
                  {"移除"}
                </Button>
              )}
            ></Column>
          </Table>
          <Button
            type={"primary"}
            danger
            onClick={() => {
              confirmOrderSum();
              setConfirmOrderOpen(true);
            }}
          >
            {"提交订单"}
          </Button>
          <Modal
            open={confirmOrderOpen}
            confirmLoading={confirmOrderLoading}
            onOk={handleConfirmOrderOk}
            onCancel={handleConfirmOrderCancel}
            okText={"确认"}
            cancelText={"取消"}
            title={"确认订单"}
          >
            {Array.from(selectOrderItemList).map((value: IOrderItem) => {
              return (
                <p>{`${value.book.name} ￥${value.book.price} x${value.count}`}</p>
              );
            })}
            <p>{`共计 ￥${orderSum.toFixed(2)}`}</p>
          </Modal>
        </>
      ) : (
        <Spin size={"large"}></Spin>
      )}
    </>
  );
};
