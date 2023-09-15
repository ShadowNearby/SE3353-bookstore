import { Avatar, DatePicker, Input, Table } from "antd";
import { IOrder, IOrderItem } from "../../interface";
import React, { useEffect, useState } from "react";
import { getOrders } from "../../service/get/personal/getOrder";
import moment from "moment";

const { Column } = Table;
const { RangePicker } = DatePicker;
export const Order = () => {
  const [orders, setOrders] = useState<IOrder[]>([]);
  const [showedOrders, setShowedOrders] = useState<IOrder[]>([]);
  useEffect(() => {
    getOrders().then((res: IOrder[]) => {
      let newList: IOrder[] = [];
      res.forEach((value) => {
        if (value.orderItemList.length) newList.push(value);
      });
      newList.sort((a, b) =>
        a.orderTime === b.orderTime ? 0 : a.orderTime > b.orderTime ? -1 : 1
      );
      setOrders(newList);
      setShowedOrders(newList);
    });
  }, []);
  const searchByName = (target: string) => {
    let newList: IOrder[] = [];
    orders.forEach((order) => {
      order.orderItemList.forEach((orderItem) => {
        if (orderItem.book.name.match(target)) {
          newList.push(order);
          return;
        }
      });
    });
    setShowedOrders(newList);
  };
  return (
    <>
      <Input.Search
        placeholder={"搜索图书"}
        style={{ width: "80%", marginTop: 20 }}
        onSearch={searchByName}
      ></Input.Search>
      <RangePicker
        placeholder={["起始日期", "结束日期"]}
        style={{ marginTop: 10, marginBottom: 20 }}
        showTime
        allowEmpty={[false, false]}
        onChange={(value) => {
          if (value === null) {
            setShowedOrders(orders);
          } else {
            const [start, end] = value;
            const startDate = start ? start.toDate() : new Date(0);
            const endDate = end ? end.toDate() : new Date();
            let newList: IOrder[] = [];
            orders.forEach((order) => {
              if (
                new Date(order.orderTime) >= startDate &&
                new Date(order.orderTime) <= endDate
              )
                newList.push(order);
            });
            setShowedOrders(newList);
          }
        }}
      ></RangePicker>
      {orders ? (
        <Table dataSource={showedOrders}>
          <Column
            title={"购买时间"}
            key={"id"}
            render={(order: IOrder) =>
              moment(new Date(order.orderTime)).format("YYYY-MM-DD HH:mm")
            }
          ></Column>
          <Column
            title={"图书信息"}
            key={"id"}
            render={(order: IOrder) => (
              <>
                {order.orderItemList.map((orderItem: IOrderItem) => (
                  <p>
                    <Avatar
                      src={orderItem.book.image}
                      shape={"square"}
                      size={50}
                    ></Avatar>
                    {orderItem.book.name}
                  </p>
                ))}
              </>
            )}
          ></Column>
          <Column
            title={"单价"}
            key={"id"}
            render={(order: IOrder) => (
              <>
                {order.orderItemList.map((orderItem: IOrderItem) => (
                  <p style={{ fontSize: 15 }}>
                    {"￥"}
                    {orderItem.book.price}
                  </p>
                ))}
              </>
            )}
          ></Column>
          <Column
            title={"数量"}
            key={"id"}
            render={(order: IOrder) => (
              <>
                {order.orderItemList.map((orderItem: IOrderItem) => (
                  <p>
                    {"X"}
                    {orderItem.count}
                  </p>
                ))}
              </>
            )}
          ></Column>
          <Column
            title={"价格"}
            key={"id"}
            render={(order: IOrder) => (
              <>
                {order.orderItemList.map((orderItem: IOrderItem) => (
                  <p style={{ fontSize: 18, color: "red" }}>
                    {"￥"}
                    {orderItem.count * orderItem.book.price}
                  </p>
                ))}
              </>
            )}
          ></Column>
          <Column
            title={"订单总价"}
            key={"id"}
            render={(order: IOrder) => {
              let sum = 0;
              order.orderItemList.forEach((orderItem: IOrderItem) => {
                sum += orderItem.count * orderItem.book.price;
              });
              return (
                <p style={{ fontSize: 21, color: "red" }}>
                  {"￥"}
                  {sum}
                </p>
              );
            }}
          ></Column>
        </Table>
      ) : (
        <></>
      )}
      {/*<div style={{padding: 24, minHeight: 380}}><Button>{"全部下单"}</Button></div>*/}
    </>
  );
};
